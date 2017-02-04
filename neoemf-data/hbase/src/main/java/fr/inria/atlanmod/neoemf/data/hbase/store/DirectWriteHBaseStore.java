/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.hbase.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackend;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseEncoderUtil;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DefaultDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

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
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An {@link DefaultDirectWriteStore} that translates model-level operations to HBase operations.
 * <p>
 * This class implements the {@link PersistentStore} interface that defines a set of operations to implement in order to
 * allow EMF persistence delegation. If this store is used, every method call and property access on {@link
 * PersistentEObject} is forwarded to this class, that takes care of the serialization/deserialization from/to HBase.
 *
 * @note For historical purposes this class does not use a {@link HBasePersistenceBackend}, instead, it accesses HBase
 * directly using the low-level database API.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 * @see PersistentEObject
 * @see HBasePersistenceBackend
 * @see DefaultDirectWriteStore
 */
// TODO Continue cleaning, there is still code duplication
public class DirectWriteHBaseStore extends DefaultDirectWriteStore<HBasePersistenceBackend> {

    /**
     * The column family holding properties.
     */
    protected static final byte[] PROPERTY_FAMILY = Bytes.toBytes("p");

    /**
     * ???
     */
    private static final byte[] TYPE_FAMILY = Bytes.toBytes("t");

    /**
     * ???
     */
    private static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");

    /**
     * ???
     */
    private static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");

    /**
     * ???
     */
    private static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");

    /**
     * ???
     */
    private static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");

    /**
     * ???
     */
    private static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

    /**
     * ???
     */
    private static final int ATTEMP_TIMES_DEFAULT = 10;

    /**
     * ???
     */
    private static final long SLEEP_DEFAULT = 1L;

    /**
     * The HBase table used to access the model.
     */
    protected Table table;

    /**
     * Constructs a new {@code DirectWriteHBaseStore} on the given {@code resource}.
     *
     * @param resource the resource to persist and access
     *
     * @throws IOException if the HBase server cannot be found
     */
    public DirectWriteHBaseStore(PersistentResource resource) throws IOException {
        super(resource, null);

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", resource.getURI().host());
        configuration.set("hbase.zookeeper.property.clientPort", isNull(resource.getURI().port()) ? "2181" : resource.getURI().port());

        TableName tableName = TableName.valueOf(HBaseURI.format(resource.getURI()));

        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        this.table = initTable(connection, tableName, admin);
    }

    /**
     * Initialize the HBase table by creating the columns and column families to store the model.
     *
     * @param connection the connection to the HBase server
     * @param tableName  the name of the table to access on the server
     * @param admin      the administrator client of the HBase server
     *
     * @return the created HBase table containing the columns and column families to store the model
     *
     * @throws IOException if the HBase server cannot be found
     */
    protected Table initTable(Connection connection, TableName tableName, Admin admin) throws IOException {
        if (!admin.tableExists(tableName)) {
            // FIXME Don't initialize this table in READ ONLY.
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

    /**
     * A specific implementation of {@link EStore#add(InternalEObject, EStructuralFeature, int, Object)} that takes
     * benefit of the HBase facilities to append an element in a multi-valued {@link EReference} without de-serializing
     * the entire collection.
     *
     * @param object           the source element
     * @param reference        the multi-valued {@link EReference}
     * @param atEnd            {@code true} to append {@code referencedObject} at the end of the collection, {@code
     *                         false} to put it at the beginning (if the persisted collection is empty)
     * @param referencedObject the {@link PersistentEObject} to append
     *
     * @throws IOException if the HBase database cannot be found
     */
    private void addAsAppend(PersistentEObject object, EReference reference, boolean atEnd, PersistentEObject referencedObject) throws IOException {
        Append append = new Append(Bytes.toBytes(object.id().toString()));
        append.add(PROPERTY_FAMILY,
                Bytes.toBytes(reference.getName()),
                atEnd ? Bytes.toBytes(HBaseEncoderUtil.VALUE_SEPERATOR_DEFAULT + referencedObject.id().toString()) :
                        Bytes.toBytes(referencedObject.id().toString() + HBaseEncoderUtil.VALUE_SEPERATOR_DEFAULT));

        table.append(append);
    }

    /**
     * Gets the {@link EStructuralFeature} {@code feature} from the {@link Table} for the {@link
     * PersistentEObject object}.
     *
     * @param object  the source element
     * @param feature the {@link EStructuralFeature} to get the value of
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
            NeoLogger.error("Unable to get property {0} for {1}", feature.getName(), object);
        }
        return null;
    }

    @Override
    // TODO Implement this method
    public void save() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        try {
            Result result = table.get(new Get(Bytes.toBytes(object.id().toString())));
            byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
            return nonNull(value);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get information for element {0}", object);
        }
        return false;
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        try {
            Delete delete = new Delete(Bytes.toBytes(object.id().toString()));
            delete.addColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()));
            table.delete(delete);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", object);
        }
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        String[] array = (String[]) getFromTable(object, feature);
        return isNull(array) ? 0 : array.length;
    }

    @Override
    // TODO Implement this method
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return false;
//        return indexOf(internalObject, feature, value) != -1;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        String[] array = (String[]) getFromTable(object, feature);
        if (isNull(array)) {
            return PersistentStore.NO_INDEX;
        }
        if (feature instanceof EAttribute) {
            return ArrayUtils.indexOf(array, serialize((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            return ArrayUtils.indexOf(array, childEObject.id().toString());
        }
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        String[] array = (String[]) getFromTable(object, feature);
        if (isNull(array)) {
            return PersistentStore.NO_INDEX;
        }
        if (feature instanceof EAttribute) {
            return ArrayUtils.lastIndexOf(array, serialize((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            return ArrayUtils.lastIndexOf(array, childEObject.id().toString());
        }
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        try {
            Put put = new Put(Bytes.toBytes(object.id().toString()));
            put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()), HBaseEncoderUtil.toBytes());
            table.put(put);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", object);
        }
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object value = getFromTable(object, feature);
        if (feature.isMany()) {
            int valueLength = ((Object[]) value).length;
            return internalToArray(value, feature, new Object[valueLength]);
        }
        else {
            return internalToArray(value, feature, new Object[1]);
        }
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object value = getFromTable(object, feature);
        return internalToArray(value, feature, array);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        PersistentEObject object = PersistentEObject.from(internalObject);

        try {
            Result result = table.get(new Get(Bytes.toBytes(object.id().toString())));
            String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
            String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));

            if (nonNull(containerId) && nonNull(containingFeatureName)) {
                return eObject(StringId.of(containerId));
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", object);
        }
        return null;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        PersistentEObject object = PersistentEObject.from(internalObject);

        try {
            Result result = table.get(new Get(Bytes.toBytes(object.id().toString())));
            String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
            String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));

            if (nonNull(containerId) && nonNull(containingFeatureName)) {
                PersistentEObject container = eObject(StringId.of(containerId));
                return container.eClass().getEStructuralFeature(containingFeatureName);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get containment information for {0}", object);
        }
        return null;
    }

    /**
     * Add {@code referencedObject} in the {@code reference} containment list of {@code object}. Inverse container
     * feature between {@code referencedObject} and {@code object} is also updated.
     *
     * @param object           the container element
     * @param reference        the containment {@link EReference}
     * @param referencedObject the element to add to {@code object}'s containment list
     */
    @Override
    protected void updateContainment(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        if (reference.isContainment()) {
            try {
                Put put = new Put(Bytes.toBytes(referencedObject.id().toString()));
                put.addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(object.id().toString()));
                put.addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(reference.getName()));
                table.put(put);

            }
            catch (IOException e) {
                NeoLogger.error("Unable to update containment information for {0}", object);
            }
        }
    }

    /**
     * Compute the {@link EClass} associated to the model element with the provided {@link Id}.
     *
     * @param id the {@link Id} of the model element to compute the {@link EClass} from
     *
     * @return an {@link EClass} representing the metaclass of the element
     */
    @Override
    protected Optional<EClass> resolveInstanceOf(Id id) {
        Optional<EClass> metaclass = Optional.empty();

        try {
            Result result = table.get(new Get(Bytes.toBytes(id.toString())));
            String nsURI = Bytes.toString(result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER));
            String className = Bytes.toString(result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER));
            if (nonNull(nsURI) && nonNull(className)) {
                metaclass = Optional.ofNullable((EClass) Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className));
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to get instance of information for {0}", id);
        }
        return metaclass;
    }

    /**
     * Computes {@code object}'s metaclass information and persists them in the database.
     *
     * @param object the {@link PersistentEObject} to persist the metaclass of
     */
    @Override
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

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        Object soughtAttribute = getFromTable(object, attribute);
        if (!attribute.isMany()) {
            return deserialize(attribute, soughtAttribute);
        }
        else {
            String[] array = (String[]) soughtAttribute;
            return deserialize(attribute, array[index]);
        }
    }

    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        if (reference.isContainer()) {
            return (PersistentEObject) getContainer(object);
        }

        Object soughtReference = getFromTable(object, reference);
        if (isNull(soughtReference)) {
            return null;
        }
        if (!reference.isMany()) {
            return eObject(StringId.of((String) soughtReference));
        }
        else {
            String[] array = (String[]) soughtReference;
            return eObject(StringId.of(array[index]));
        }
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        Object old = isSet(object, attribute) ? get(object, attribute, index) : null;
        try {
            if (!attribute.isMany()) {
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(attribute.getName()), Bytes.toBytes(serialize(attribute, value).toString()));
                table.put(put);
            }
            else {
                try {
                    String[] array;
                    boolean passed;
                    int attemp = 0;
                    do {
                        array = (String[]) getFromTable(object, attribute);

                        Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                                PROPERTY_FAMILY,
                                Bytes.toBytes(attribute.getName()),
                                HBaseEncoderUtil.toBytes((String[]) ArrayUtils.add(array, index, serialize(attribute, value))));
                        passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                                PROPERTY_FAMILY,
                                Bytes.toBytes(attribute.getName()),
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
                    NeoLogger.error("Unable to set {0} to {1} for element {2}", value, attribute.getName(), object);
                }
                catch (TimeoutException e) {
                    NeoLogger.error("Unable to set {0} to {1} for element {2} after {3} times", value, attribute.getName(), object, ATTEMP_TIMES_DEFAULT);
                }
                catch (InterruptedException e) {
                    NeoLogger.error(e, "InterruptedException while updating element {0}", object);
                }
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to set information for element {0}", object);
        }
        return old;
    }

    @Override
    protected PersistentEObject setReference(PersistentEObject object, EReference reference, int index, PersistentEObject referencedObject) {
        PersistentEObject old = isSet(object, reference) ? (PersistentEObject) get(object, reference, index) : null;
        persistentObjectsCache.put(object.id(), object);
        updateContainment(object, reference, referencedObject);
        updateInstanceOf(referencedObject);

        try {
            if (!reference.isMany()) {
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.addColumn(PROPERTY_FAMILY,
                        Bytes.toBytes(reference.getName()),
                        Bytes.toBytes(referencedObject.id().toString()));
                table.put(put);
            }
            else {
                String[] array = (String[]) getFromTable(object, reference);
                array[index] = referencedObject.id().toString();
                Put put = new Put(Bytes.toBytes(object.id().toString()));
                put.addColumn(PROPERTY_FAMILY,
                        Bytes.toBytes(reference.getName()),
                        HBaseEncoderUtil.toBytesReferences(array));
                table.put(put);
            }
        }
        catch (IOException e) {
            NeoLogger.error("Unable to set information for element {0}", object);
        }
        return old;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        try {
            String[] array;
            boolean passed;
            int attemp = 0;
            do {
                array = (String[]) getFromTable(object, attribute);

                Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(attribute.getName()),
                        HBaseEncoderUtil.toBytes(index < 0 ?
                                ArrayUtils.add(array, serialize(attribute, value)) :
                                ArrayUtils.add(array, serialize(attribute, value))
                        ));
                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(attribute.getName()),
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
            NeoLogger.error("Unable to add {0} to {1} for element {2}", value, attribute.getName(), object);
        }
        catch (TimeoutException e) {
            NeoLogger.error("Unable to add {0} to {1} for element {2} after {3} times", value, attribute.getName(), object, ATTEMP_TIMES_DEFAULT);
        }
        catch (InterruptedException e) {
            NeoLogger.error(e, "InterruptedException while updating element {0}", object);
        }
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject referencedObject) {
        try {
            /*
             * As long as the element is not attached to the resource, the containment and type  information are not
			 * stored.
			 */
            persistentObjectsCache.put(object.id(), object);
            updateContainment(object, reference, referencedObject);
            updateInstanceOf(referencedObject);

            if (index == NO_INDEX) {
                addAsAppend(object, reference, true, referencedObject);
            }
            else {
                String[] array;
                boolean passed;
                int attemp = 0;
                do {
                    array = (String[]) getFromTable(object, reference);

                    Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                            PROPERTY_FAMILY,
                            Bytes.toBytes(reference.getName()),
                            HBaseEncoderUtil.toBytesReferences(ArrayUtils.add(array, index, referencedObject.id().toString())));

                    passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                            PROPERTY_FAMILY,
                            Bytes.toBytes(reference.getName()),
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
            NeoLogger.error("Unable to add {0} to {1} for element {2}", referencedObject, reference.getName(), object);
        }
        catch (TimeoutException e) {
            NeoLogger.error("Unable to add {0} to {1} for element {2} after {3} times", referencedObject, reference.getName(), object, ATTEMP_TIMES_DEFAULT);
        }
        catch (InterruptedException e) {
            NeoLogger.error(e, "InterruptedException while updating element {0}", object);
        }
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        Object old = get(object, attribute, index);
        try {
            String[] array;
            boolean passed;
            int attemp = 0;
            do {
                array = (String[]) getFromTable(object, attribute);

                Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(attribute.getName()),
                        HBaseEncoderUtil.toBytes(ArrayUtils.remove(array, index)));
                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(attribute.getName()),
                        HBaseEncoderUtil.toBytes(array),
                        put);
                if (!passed) {
                    if (attemp > ATTEMP_TIMES_DEFAULT) {
                        throw new TimeoutException();
                    }
                    Thread.sleep((++attemp) * SLEEP_DEFAULT);
                    old = get(object, attribute, index);
                }
            }
            while (!passed);
        }
        catch (IOException e) {
            NeoLogger.error("Unable to delete {0} to {1} for element {2}", old, attribute.getName(), object);
        }
        catch (TimeoutException e) {
            NeoLogger.error("Unable to delete {0} to {1} for element {2} after {3} times", old, attribute.getName(), object, ATTEMP_TIMES_DEFAULT);
        }
        catch (InterruptedException e) {
            NeoLogger.error(e, "InterruptedException while updating element {0}", object);
        }

        return old;
    }

    @Override
    protected PersistentEObject removeReference(PersistentEObject object, EReference reference, int index) {
        PersistentEObject old = (PersistentEObject) get(object, reference, index);
        try {
            String[] array;
            boolean passed;
            int attemp = 0;
            do {
                array = (String[]) getFromTable(object, reference);

                Put put = new Put(Bytes.toBytes(object.id().toString())).addColumn(
                        PROPERTY_FAMILY,
                        Bytes.toBytes(reference.getName()),
                        HBaseEncoderUtil.toBytesReferences(ArrayUtils.remove(array, index)));

                passed = table.checkAndPut(Bytes.toBytes(object.id().toString()),
                        PROPERTY_FAMILY,
                        Bytes.toBytes(reference.getName()),
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
            NeoLogger.error("Unable to delete {0}[{1}] for element {2}", reference.getName(), index, object);
        }
        catch (InterruptedException e) {
            NeoLogger.error("InterruptedException while updating element {0}", object);
        }

        return old;
    }

    /**
     * Reifies the element(s) in {@code value} and put them into {@code output}.
     *
     * @param value   the backend record to reify
     * @param feature the {@link EStructuralFeature} used to reify {@code value}
     * @param output  the array to fill
     *
     * @return {@code output} filled with the reified values
     */
    @SuppressWarnings("unchecked")
    private <T> T[] internalToArray(Object value, EStructuralFeature feature, T[] output) {
        if (feature.isMany()) {
            Object[] storedArray = (Object[]) value;
            if (feature instanceof EReference) {
                for (int i = 0; i < storedArray.length; i++) {
                    output[i] = (T) eObject(StringId.of((String) storedArray[i]));
                }
            }
            else { // EAttribute
                for (int i = 0; i < storedArray.length; i++) {
                    output[i] = (T) deserialize((EAttribute) feature, storedArray[i]);
                }
            }
        }
        else {
            if (feature instanceof EReference) {
                output[0] = (T) eObject((Id) value);
            }
            else { // EAttribute
                output[0] = (T) deserialize((EAttribute) feature, value);
            }
        }
        return output;
    }
}