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

package fr.inria.atlanmod.neoemf.hbase.datastore.store.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.AbstractDirectWriteEStore;
import fr.inria.atlanmod.neoemf.hbase.datastore.HBasePersistenceBackend;
import fr.inria.atlanmod.neoemf.hbase.util.NeoHBaseUtil;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.protobuf.generated.ZooKeeperProtos;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.util.Objects.isNull;

// TODO Continue cleaning, there is still code duplication
public class DirectWriteHBaseEStore extends AbstractDirectWriteEStore<HBasePersistenceBackend> {

    protected static final byte[] PROPERTY_FAMILY = Bytes.toBytes("p");

    private static final byte[] TYPE_FAMILY = Bytes.toBytes("t");
    private static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");
    private static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");
    private static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");
    private static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");
    private static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

    private static final int ATTEMP_TIMES_DEFAULT = 10;
    private static final long SLEEP_DEFAULT = 1L;

    private final Cache<Object, PersistentEObject> loadedEObjects = CacheBuilder.newBuilder().softValues().build();

    protected HTable table;

    public DirectWriteHBaseEStore(Resource.Internal resource) throws IOException {
        super(resource, null);

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", resource.getURI().host());
        conf.set("hbase.zookeeper.property.clientPort", isNull(resource.getURI().port()) ? "2181" : resource.getURI().port());

        TableName tableName = TableName.valueOf(NeoHBaseUtil.formatURI(resource.getURI()));
        HBaseAdmin admin = new HBaseAdmin(conf);

        table = initTable(conf, tableName, admin);
    }

    protected HTable initTable(Configuration conf, TableName tableName, HBaseAdmin admin) throws IOException {
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
        return new HTable(conf, tableName);
    }

    private void addAsAppend(PersistentEObject object, EReference eReference, boolean atEnd, PersistentEObject referencedObject) throws IOException {
        Append append = new Append(Bytes.toBytes(object.id().toString()));
        append.add(PROPERTY_FAMILY,
                Bytes.toBytes(eReference.getName()),
                atEnd ? Bytes.toBytes(NeoHBaseUtil.EncoderUtil.VALUE_SEPERATOR_DEFAULT + referencedObject.id().toString()) :
                        Bytes.toBytes(referencedObject.id().toString() + NeoHBaseUtil.EncoderUtil.VALUE_SEPERATOR_DEFAULT));

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

            if (!isNull(containerId) && !isNull(containingFeatureName)) {
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

            if (!isNull(containerId) && !isNull(containingFeatureName)) {
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
        if (isNull(id)) {
            return null;
        }
        PersistentEObject persistentEObject = loadedEObjects.getIfPresent(id);
        if (isNull(persistentEObject)) {
            EClass eClass = resolveInstanceOf(id);
            if (!isNull(eClass)) {
                EObject eObject = EcoreUtil.create(eClass);
                if (eObject instanceof PersistentEObject) {
                    persistentEObject = (PersistentEObject) eObject;
                }
                else {
                    persistentEObject = PersistentEObject.from(eObject);
                }
                persistentEObject.id(id);
            }
            else {
                NeoLogger.error("Element {0} does not have an associated EClass", id);
            }
            if (isNull(persistentEObject)) {
                NeoLogger.error("Element {0} does not exist", id);
                return null;
            }
            else {
                loadedEObjects.put(id, persistentEObject);
            }
        }
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
            if (!isNull(nsURI) && !isNull(className)) {
                return (EClass) Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get instance of information for {0}", id);
        }
        return null;
    }

    private void updateLoadedEObjects(PersistentEObject eObject) {
        loadedEObjects.put(eObject.id(), eObject);
    }

    protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
        if (eReference.isContainment()) {
            // Remove from root
            try {
                Put put = new Put(Bytes.toBytes(referencedObject.id().toString()));
                put.add(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(object.id().toString()));
                put.add(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(eReference.getName()));
                // No need to use the CAS mech
//				table.checkAndPut(
//						Bytes.toBytes(referencedObject.id().toString()), CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, CompareOp.NOT_EQUAL,
//						Bytes.toBytes(object.id().toString()), put);
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
            put.add(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(object.eClass().getEPackage().getNsURI()));
            put.add(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(object.eClass().getName()));
            table.checkAndPut(Bytes.toBytes(object.id().toString()), TYPE_FAMILY, ECLASS_QUALIFIER, null, put);

        }
        catch (IOException e) {
            NeoLogger.error("Unable to update containment information for {0}", object);
        }
    }

    /**
     * Gets the {@link EStructuralFeature} {@code feature} from the {@link ZooKeeperProtos.Table} for the {@link
     * PersistentEObject object}
     *
     * @return The value of the {@code feature}. It can be a {@link String} for single-valued {@link
     *         EStructuralFeature}s or a {@link String}[] for many-valued {@link EStructuralFeature}s
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
                    return NeoHBaseUtil.EncoderUtil.toStrings(value);
                }
                return NeoHBaseUtil.EncoderUtil.toStringsReferences(value);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object);
        }
        return null;
    }

    @Override
    public PersistentEStore getEStore() {
        return this;
    }

    /**
     * TODO: implement this method.
     */
    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject neoEObject = PersistentEObject.from(object);
        try {
            Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
            byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
            return !isNull(value);
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
            delete.deleteColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()));
            table.delete(delete);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", neoEObject);
        }
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        return false;
        //return indexOf(object, feature, value) != -1;
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        PersistentEObject eObject = PersistentEObject.from(object);
        String[] array = (String[]) getFromTable(eObject, feature);
        if (isNull(array)) {
            return -1;
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
            return -1;
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
            put.add(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()), NeoHBaseUtil.EncoderUtil.toBytes(new String[]{}));
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
                put.add(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), Bytes.toBytes(serializeToProperty(eAttribute, value).toString()));
                table.put(put);
            }
            else {
                try {
                    String[] array;
                    boolean passed;
                    int attemp = 0;

                    do {
                        array = (String[]) getFromTable(object, eAttribute);
                        //array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));

                        Put put = new Put(Bytes.toBytes(object.id().toString())).add(
                                PROPERTY_FAMILY,
                                Bytes.toBytes(eAttribute.getName()),
                                NeoHBaseUtil.EncoderUtil.toBytes((String[]) ArrayUtils.add(array, index, serializeToProperty(eAttribute, value))));
                        passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                                PROPERTY_FAMILY,
                                Bytes.toBytes(eAttribute.getName()),
                                isNull(array) ? null : NeoHBaseUtil.EncoderUtil.toBytes(array),
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
                put.add(PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        Bytes.toBytes(referencedObject.id().toString()));
                table.put(put);
            }
            else {
                String[] array = (String[]) getFromTable(object, eReference);
                array[index] = referencedObject.id().toString();
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.add(PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        NeoHBaseUtil.EncoderUtil.toBytesReferences(array));
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
                //array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));

                Put put = new Put(Bytes.toBytes(object.id().toString())).add(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        NeoHBaseUtil.EncoderUtil.toBytes(index < 0 ?
                                (String[]) ArrayUtils.add(array, serializeToProperty(eAttribute, value)) :
                                (String[]) ArrayUtils.add(array, serializeToProperty(eAttribute, value))
                        ));
                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        isNull(array) ? null : NeoHBaseUtil.EncoderUtil.toBytes(array),
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
                    //array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());

                    Put put = new Put(Bytes.toBytes(object.id().toString())).add(
                            PROPERTY_FAMILY,
                            Bytes.toBytes(eReference.getName()),
                            NeoHBaseUtil.EncoderUtil.toBytesReferences((String[]) ArrayUtils.add(array, index, referencedObject.id().toString())));

                    passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                            PROPERTY_FAMILY,
                            Bytes.toBytes(eReference.getName()),
                            isNull(array) ? null : NeoHBaseUtil.EncoderUtil.toBytesReferences(array),
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
                //array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));

                Put put = new Put(Bytes.toBytes(object.id().toString())).add(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        NeoHBaseUtil.EncoderUtil.toBytes((String[]) ArrayUtils.remove(array, index)));
                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eAttribute.getName()),
                        NeoHBaseUtil.EncoderUtil.toBytes(array),
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
                //array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());

                Put put = new Put(Bytes.toBytes(object.id().toString())).add(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        NeoHBaseUtil.EncoderUtil.toBytesReferences((String[]) ArrayUtils.remove(array, index)));

                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(eReference.getName()),
                        NeoHBaseUtil.EncoderUtil.toBytesReferences(array),
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
}