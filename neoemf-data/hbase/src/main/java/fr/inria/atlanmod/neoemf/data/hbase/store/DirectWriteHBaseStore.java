/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.hbase.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackend;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseEncoderUtil;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

// TODO Continue cleaning, there is still code duplication
public class DirectWriteHBaseStore extends AbstractDirectWriteStore<HBasePersistenceBackend> {

    protected static final byte[] PROPERTY_FAMILY = Bytes.toBytes("p");

    private static final byte[] TYPE_FAMILY = Bytes.toBytes("t");
    private static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");
    private static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");
    private static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");
    private static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");
    private static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

    private static final int ATTEMP_TIMES_DEFAULT = 10;
    private static final long SLEEP_DEFAULT = 1L;

    private final Cache<Id, PersistentEObject> persistentObjectsCache;

    protected Table table;

    public DirectWriteHBaseStore(Resource.Internal resource) throws IOException {
        super(resource, null);

        this.persistentObjectsCache = Caffeine.newBuilder().maximumSize(10000).build();

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", resource.getURI().host());
        conf.set("hbase.zookeeper.property.clientPort", isNull(resource.getURI().port()) ? "2181" : resource.getURI().port());

        TableName tableName = TableName.valueOf(HBaseURI.format(resource.getURI()));

        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();

        this.table = initTable(connection, tableName, admin);
    }

    protected Table initTable(Connection connection, TableName tableName, Admin admin) throws IOException {
        if (!admin.tableExists(tableName)) {
            HTableDescriptor desc = new HTableDescriptor(tableName);
            HColumnDescriptor propertyFamily = new HColumnDescriptor(PROPERTY_FAMILY);
            HColumnDescriptor typeFamily = new HColumnDescriptor(TYPE_FAMILY);
            HColumnDescriptor containmentFamily = new HColumnDescriptor(CONTAINMENT_FAMILY);
            desc.addFamily(propertyFamily);
            desc.addFamily(typeFamily);
            desc.addFamily(containmentFamily);
            admin.createTable(desc);
        }
        return connection.getTable(tableName);
    }

    private void addAsAppend(PersistentEObject object, EReference eReference, boolean atEnd, PersistentEObject referencedObject) throws IOException {
        Append append = new Append(Bytes.toBytes(object.id().toString()));
        append.add(PROPERTY_FAMILY,
                Bytes.toBytes(eReference.getName()),
                atEnd ? Bytes.toBytes(HBaseEncoderUtil.VALUE_SEPERATOR_DEFAULT + referencedObject.id().toString()) :
                        Bytes.toBytes(referencedObject.id().toString() + HBaseEncoderUtil.VALUE_SEPERATOR_DEFAULT));

        table.append(append);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject eObject = PersistentEObject.from(object);
        String[] array = (String[]) getFromTable(eObject, feature);
        return isNull(array) ? 0 : array.length;
    }

    @Override
    public InternalEObject getContainer(InternalEObject object) {
        PersistentEObject neoEObject = PersistentEObject.from(object);

        try {
            Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
            String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
            String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));

            if (nonNull(containerId) && nonNull(containingFeatureName)) {
                return (InternalEObject) eObject(new StringId(containerId));
            }

        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", neoEObject);
        }
        return null;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject object) {
        PersistentEObject neoEObject = PersistentEObject.from(object);

        try {
            Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
            String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
            String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));

            if (nonNull(containerId) && nonNull(containingFeatureName)) {
                EObject container = eObject(new StringId(containerId));
                return container.eClass().getEStructuralFeature(containingFeatureName);
            }

        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", neoEObject);
        }
        return null;
    }

    @Override
    public EObject eObject(Id id) {
        EClass eClass = resolveInstanceOf(id);
        PersistentEObject persistentEObject = persistentObjectsCache.get(id, new PersistentEObjectCacheLoader(eClass));
        if (persistentEObject.resource() != resource()) {
            persistentEObject.resource(resource());
        }
        return persistentEObject;
    }

    private EClass resolveInstanceOf(Id id) {
        try {
            Result result = table.get(new Get(Bytes.toBytes(id.toString())));
            String nsURI = Bytes.toString(result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER));
            String className = Bytes.toString(result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER));
            if (nonNull(nsURI) && nonNull(className)) {
                return (EClass) Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get instance of information for {0}", id);
        }
        return null;
    }

    private void updateLoadedEObjects(PersistentEObject eObject) {
        persistentObjectsCache.put(eObject.id(), eObject);
    }

    protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
        if (eReference.isContainment()) {
            // Remove from root
            try {
                Put put = new Put(Bytes.toBytes(referencedObject.id().toString()));
                put.addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(object.id().toString()));
                put.addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(eReference.getName()));
                // No need to use the CAS mech
//                table.checkAndPut(
//                        Bytes.toBytes(referencedObject.id().toString()), CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, CompareOp.NOT_EQUAL,
//                        Bytes.toBytes(object.id().toString()), put);
                table.put(put);

            }
            catch (IOException e) {
                NeoLogger.error("Unable to update containment information for {0}", object);
            }
        }
    }

    protected void updateInstanceOf(PersistentEObject object) {
        try {
            Put put = new Put(Bytes.toBytes(object.id().toString()));
            put.addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(object.eClass().getEPackage().getNsURI()));
            put.addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(object.eClass().getName()));
            table.checkAndPut(Bytes.toBytes(object.id().toString()), TYPE_FAMILY, ECLASS_QUALIFIER, null, put);

        }
        catch (IOException e) {
            NeoLogger.error("Unable to update containment information for {0}", object);
        }
    }

    /**
     * Gets the {@link EStructuralFeature} {@code feature} from the {@link Table} for the {@link
     * PersistentEObject object}
     *
     * @return The value of the {@code feature}. It can be a {@link String} for single-valued {@link
     * EStructuralFeature}s or a {@link String}[] for many-valued {@link EStructuralFeature}s
     */
    protected Object getFromTable(PersistentEObject object, EStructuralFeature feature) {
        try {
            Result result = table.get(new Get(Bytes.toBytes(object.id().toString())));
            byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
            if (!feature.isMany()) {
                return Bytes.toString(value);
            }
            else {
                if (feature instanceof EAttribute) {
                    return HBaseEncoderUtil.toStrings(value);
                }
                return HBaseEncoderUtil.toStringsReferences(value);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object);
        }
        return null;
    }

    @Override
    public PersistentStore getEStore() {
        return this;
    }

    @Override
    public void save() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject neoEObject = PersistentEObject.from(object);
        try {
            Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
            byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
            return nonNull(value);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get information for element ''{0}''", neoEObject);
        }
        return false;
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject neoEObject = PersistentEObject.from(object);
        try {
            Delete delete = new Delete(Bytes.toBytes(neoEObject.id().toString()));
            delete.addColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()));
            table.delete(delete);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", neoEObject);
        }
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        return false;
//        return indexOf(object, feature, value) != -1;
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        String[] array = (String[]) getFromTable(eObject, feature);
        if (isNull(array)) {
            return PersistentStore.NO_INDEX;
        }
        if (feature instanceof EAttribute) {
            return ArrayUtils.indexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            return ArrayUtils.indexOf(array, childEObject.id().toString());
        }
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        String[] array = (String[]) getFromTable(eObject, feature);
        if (isNull(array)) {
            return PersistentStore.NO_INDEX;
        }
        if (feature instanceof EAttribute) {
            return ArrayUtils.lastIndexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            return ArrayUtils.lastIndexOf(array, childEObject.id().toString());
        }
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject neoEObject = PersistentEObject.from(object);
        try {
            Put put = new Put(Bytes.toBytes(neoEObject.id().toString()));
            put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()), HBaseEncoderUtil.toBytes(new String[]{}));
            table.put(put);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", neoEObject);
        }
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        Object value = getFromTable(object, eAttribute);
        if (!eAttribute.isMany()) {
            return parseProperty(eAttribute, value);
        }
        else {
            String[] array = (String[]) value;
            return parseProperty(eAttribute, array[index]);
        }
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        if (eReference.isContainer()) {
            return getContainer(object);
        }

        Object value = getFromTable(object, eReference);
        if (isNull(value)) {
            return null;
        }
        if (!eReference.isMany()) {
            return eObject(new StringId((String) value));
        }
        else {
            String[] array = (String[]) value;
            return eObject(new StringId(array[index]));
        }
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        Object oldValue = isSet(object, eAttribute) ? get(object, eAttribute, index) : null;
        try {
            if (!eAttribute.isMany()) {
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), Bytes.toBytes(serializeToProperty(eAttribute, value).toString()));
                table.put(put);
            }
            else {
                try {
                    String[] array;
                    boolean passed;
                    int attemp = 0;

                    do {
                        array = (String[]) getFromTable(object, eAttribute);
//                        array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));

                        Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                                PROPERTY_FAMILY,
                                Bytes.toBytes(eAttribute.getName()),
                                HBaseEncoderUtil.toBytes((String[]) ArrayUtils.add(array, index, serializeToProperty(eAttribute, value))));
                        passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                                PROPERTY_FAMILY,
                                Bytes.toBytes(eAttribute.getName()),
                                isNull(array) ? null : HBaseEncoderUtil.toBytes(array),
                                put);
                        if (!passed) {
                            if (attemp > ATTEMP_TIMES_DEFAULT) {
                                throw new TimeoutException();
                            }
                            Thread.sleep((++attemp) * SLEEP_DEFAULT);
                        }

                    }
                    while (!passed);

                }
                catch (IOException e) {
                    NeoLogger.error("Unable to set ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object);
                }
                catch (TimeoutException e) {
                    NeoLogger.error("Unable to set ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", value, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT);
                }
                catch (InterruptedException e) {
                    NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
                }
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to set information for element ''{0}''", object);
        }
        return oldValue;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
        Object oldValue = isSet(object, eReference) ? get(object, eReference, index) : null;
        updateLoadedEObjects(referencedObject);
        updateContainment(object, eReference, referencedObject);
        updateInstanceOf(referencedObject);

        try {
            if (!eReference.isMany()) {
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.addColumn(PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        Bytes.toBytes(referencedObject.id().toString()));
                table.put(put);
            }
            else {
                String[] array = (String[]) getFromTable(object, eReference);
                array[index] = referencedObject.id().toString();
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.addColumn(PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        HBaseEncoderUtil.toBytesReferences(array));
                table.put(put);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to set information for element ''{0}''", object);
        }
        return oldValue;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        try {
            String[] array;
            boolean passed;
            int attemp = 0;

            do {
                array = (String[]) getFromTable(object, eAttribute);
//                array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));

                Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        HBaseEncoderUtil.toBytes(index < 0 ?
                                (String[]) ArrayUtils.add(array, serializeToProperty(eAttribute, value)) :
                                (String[]) ArrayUtils.add(array, serializeToProperty(eAttribute, value))
                        ));
                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        isNull(array) ? null : HBaseEncoderUtil.toBytes(array),
                        put);
                if (!passed) {
                    if (attemp > ATTEMP_TIMES_DEFAULT) {
                        throw new TimeoutException();
                    }
                    Thread.sleep((++attemp) * SLEEP_DEFAULT);
                }

            }
            while (!passed);

        }
        catch (IOException e) {
            NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object);
        }
        catch (TimeoutException e) {
            NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", value, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT);
        }
        catch (InterruptedException e) {
            NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
        }
    }

    @Override
    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
        try {
            /*
             * As long as the element is not attached to the resource, the containment and type  information are not
			 * stored.
			 */
            updateLoadedEObjects(referencedObject);
            updateContainment(object, eReference, referencedObject);
            updateInstanceOf(referencedObject);

            if (index == NO_INDEX) {
                addAsAppend(object, eReference, true, referencedObject);
            }
            else {

                String[] array;
                boolean passed;
                int attemp = 0;

                do {
                    array = (String[]) getFromTable(object, eReference);
//                    array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());

                    Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                            PROPERTY_FAMILY,
                            Bytes.toBytes(eReference.getName()),
                            HBaseEncoderUtil.toBytesReferences(ArrayUtils.add(array, index, referencedObject.id().toString())));

                    passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                            PROPERTY_FAMILY,
                            Bytes.toBytes(eReference.getName()),
                            isNull(array) ? null : HBaseEncoderUtil.toBytesReferences(array),
                            put);
                    if (!passed) {
                        if (attemp > ATTEMP_TIMES_DEFAULT) {
                            throw new TimeoutException();
                        }
                        Thread.sleep((++attemp) * SLEEP_DEFAULT);
                    }

                }
                while (!passed);
            }

        }
        catch (IOException e) {
            NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", referencedObject, eReference.getName(), object);
        }
        catch (TimeoutException e) {
            NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", referencedObject, eReference.getName(), object, ATTEMP_TIMES_DEFAULT);
        }
        catch (InterruptedException e) {
            NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
        }
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        Object oldValue = get(object, eAttribute, index);
        try {

            String[] array;
            boolean passed;
            int attemp = 0;

            do {
                array = (String[]) getFromTable(object, eAttribute);
//                array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));

                Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        HBaseEncoderUtil.toBytes(ArrayUtils.remove(array, index)));
                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        HBaseEncoderUtil.toBytes(array),
                        put);
                if (!passed) {
                    if (attemp > ATTEMP_TIMES_DEFAULT) {
                        throw new TimeoutException();
                    }
                    Thread.sleep((++attemp) * SLEEP_DEFAULT);
                    oldValue = get(object, eAttribute, index);
                }

            }
            while (!passed);

        }
        catch (IOException e) {
            NeoLogger.error("Unable to delete ''{0}'' to ''{1}'' for element ''{2}''", oldValue, eAttribute.getName(), object);
        }
        catch (TimeoutException e) {
            NeoLogger.error("Unable to delete ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", oldValue, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT);
        }
        catch (InterruptedException e) {
            NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
        }

        return oldValue;
    }

    @Override
    protected Object removeReference(PersistentEObject object, EReference eReference, int index) {
        Object oldValue = get(object, eReference, index);

        try {

            String[] array;
            boolean passed;
            int attemp = 0;

            do {
                array = (String[]) getFromTable(object, eReference);
//                array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());

                Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        HBaseEncoderUtil.toBytesReferences(ArrayUtils.remove(array, index)));

                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        HBaseEncoderUtil.toBytesReferences(array),
                        put);

                if (!passed) {
                    if (attemp > ATTEMP_TIMES_DEFAULT) {
                        throw new TimeoutException();
                    }
                    Thread.sleep((++attemp) * SLEEP_DEFAULT);
                }

            }
            while (!passed);

        }
        catch (IOException | TimeoutException e) {
            NeoLogger.error("Unable to delete ''{0}[{1}''] for element ''{2}''", eReference.getName(), index, object);
        }
        catch (InterruptedException e) {
            NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
        }

        return oldValue;
    }

    private static class PersistentEObjectCacheLoader implements Function<Id, PersistentEObject> {

        private final EClass eClass;

        private PersistentEObjectCacheLoader(EClass eClass) {
            this.eClass = eClass;
        }

        @Override
        public PersistentEObject apply(Id id) {
            PersistentEObject persistentEObject;
            if (nonNull(eClass)) {
                EObject eObject;
                if (Objects.equals(eClass.getEPackage().getClass(), EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.getInstance().create(eClass);
                }
                else {
                    eObject = EcoreUtil.create(eClass);
                }
                persistentEObject = PersistentEObject.from(eObject);
                persistentEObject.id(id);
                persistentEObject.setMapped(true);
            }
            else {
                throw new RuntimeException("Element " + id + " does not have an associated EClass");
            }
            return persistentEObject;
        }
    }
}