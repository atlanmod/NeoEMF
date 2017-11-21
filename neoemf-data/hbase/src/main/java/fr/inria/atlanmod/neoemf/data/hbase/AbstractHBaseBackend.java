/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.commons.primitive.Bytes;
import fr.inria.atlanmod.commons.primitive.Ints;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link HBaseBackend} that provides overall behavior for the management of a HBase database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend extends AbstractBackend implements HBaseBackend {

    /**
     * The column family holding properties.
     */
    protected static final byte[] FAMILY_PROPERTY = Strings.toBytes("p");

    /**
     * The column family holding instances.
     */
    protected static final byte[] FAMILY_TYPE = Strings.toBytes("t");

    /**
     * The column family holding containments.
     */
    protected static final byte[] FAMILY_CONTAINMENT = Strings.toBytes("c");

    /**
     * The column qualifier holding the URI of meta-models.
     */
    private static final byte[] QUALIFIER_CLASS_URI = Strings.toBytes("m");

    /**
     * The column qualifier holding the name of classes.
     */
    private static final byte[] QUALIFIER_CLASS_NAME = Strings.toBytes("e");

    /**
     * The column qualifier holding the identifier of containers.
     */
    private static final byte[] QUALIFIER_CONTAINER = Strings.toBytes("n");

    /**
     * The column qualifier holding the name of the feature used to retrieve the containment.
     */
    private static final byte[] QUALIFIER_CONTAINING_FEATURE = Strings.toBytes("g");

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
     */
    @Nonnull
    private static final BeanSerializerFactory SERIALIZER_FACTORY = BeanSerializerFactory.getInstance();

    /**
     * A converter to use {@code byte[]} instead of {@link Id}.
     */
    @Nonnull
    private static final Converter<Id, byte[]> AS_BYTES = Converter.from(
            id -> Strings.toBytes(IdConverters.withHexString().convert(id)),
            bs -> IdConverters.withHexString().revert(Bytes.toString(bs)));

    /**
     * The HBase table used to access the model.
     */
    @Nonnull
    protected final Table table;

    /**
     * Constructs a new {@code AbstractHBaseBackend} on the given {@code table}.
     *
     * @param table the HBase table
     */
    protected AbstractHBaseBackend(Table table) {
        checkNotNull(table, "table");

        this.table = table;
    }

    @Override
    public void save() {
        // TODO Implement this method
    }

    @Override
    protected void innerClose() throws IOException {
        table.close();
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        try {
            Get get = new Get(AS_BYTES.convert(id));
            Result result = table.get(get);

            if (result.isEmpty()) {
                return Optional.empty();
            }

            byte[] byteId = result.getValue(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER);
            byte[] byteName = result.getValue(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE);

            if (isNull(byteId) || isNull(byteName)) {
                return Optional.empty();
            }

            return Optional.of(SingleFeatureBean.of(AS_BYTES.revert(byteId), Bytes.toInt(byteName)));
        }
        catch (IOException e) {
            handleException(e);
            return Optional.empty();
        }
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        try {
            Put put = new Put(AS_BYTES.convert(id))
                    .addColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER, AS_BYTES.convert(container.owner()))
                    .addColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE, Ints.toBytes(container.id()));

            table.put(put);
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        try {
            Delete delete = new Delete(AS_BYTES.convert(id))
                    .addColumns(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER)
                    .addColumns(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE);

            table.delete(delete);
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        try {
            Get get = new Get(AS_BYTES.convert(id));
            Result result = table.get(get);

            if (result.isEmpty()) {
                return Optional.empty();
            }

            byte[] byteName = result.getValue(FAMILY_TYPE, QUALIFIER_CLASS_NAME);
            byte[] byteUri = result.getValue(FAMILY_TYPE, QUALIFIER_CLASS_URI);

            if (isNull(byteName) || isNull(byteUri)) {
                return Optional.empty();
            }

            return Optional.of(ClassBean.of(Bytes.toString(byteName), Bytes.toString(byteUri)));
        }
        catch (IOException e) {
            handleException(e);
            return Optional.empty();
        }
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        try {
            byte[] row = AS_BYTES.convert(id);

            Get get = new Get(row).addColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME);
            if (table.exists(get)) {
                return false;
            }

            Put put = new Put(row)
                    .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME, Strings.toBytes(metaClass.name()))
                    .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_URI, Strings.toBytes(metaClass.uri()));

            table.put(put);
            return true;
        }
        catch (IOException e) {
            handleException(e);
            return false;
        }
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        try {
            Get get = new Get(AS_BYTES.convert(key.owner()));
            Result result = table.get(get);

            if (result.isEmpty()) {
                return Optional.empty();
            }

            byte[] byteValue = result.getValue(FAMILY_PROPERTY, Ints.toBytes(key.id()));

            if (isNull(byteValue)) {
                return Optional.empty();
            }

            return Optional.of(SERIALIZER_FACTORY.<V>forAny().deserialize(byteValue));
        }
        catch (IOException e) {
            handleException(e);
            return Optional.empty();
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Optional<V> previousValue = valueOf(key);

        try {
            Put put = new Put(AS_BYTES.convert(key.owner()))
                    .addColumn(FAMILY_PROPERTY, Ints.toBytes(key.id()), SERIALIZER_FACTORY.<V>forAny().serialize(value));

            table.put(put);
        }
        catch (IOException e) {
            handleException(e);
        }

        return previousValue;
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        try {
            Delete delete = new Delete(AS_BYTES.convert(key.owner()))
                    .addColumns(FAMILY_PROPERTY, Ints.toBytes(key.id()));

            table.delete(delete);
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    private void handleException(IOException e) {
        throw new RuntimeException(e);
    }
}
