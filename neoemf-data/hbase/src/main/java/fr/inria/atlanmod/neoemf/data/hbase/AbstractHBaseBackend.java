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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Bytes;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistentBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.serializer.BeanSerializerFactory;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link HBaseBackend} that provides overall behavior for the management of a HBase database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend extends AbstractPersistentBackend implements HBaseBackend {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
     */
    protected static final BeanSerializerFactory SERIALIZER_FACTORY = BeanSerializerFactory.getInstance();

    /**
     * The column family holding properties.
     */
    protected static final byte[] PROPERTY_FAMILY = Strings.toBytes("p");

    /**
     * The column family holding instances.
     */
    protected static final byte[] TYPE_FAMILY = Strings.toBytes("t");

    /**
     * The column family holding containments.
     */
    protected static final byte[] CONTAINMENT_FAMILY = Strings.toBytes("c");

    /**
     * The column qualifier holding the URI of meta-models.
     */
    private static final byte[] METAMODEL_QUALIFIER = Strings.toBytes("m");

    /**
     * The column qualifier holding the name of classes.
     */
    private static final byte[] ECLASS_QUALIFIER = Strings.toBytes("e");

    /**
     * The column qualifier holding the identifier of containers.
     */
    private static final byte[] CONTAINER_QUALIFIER = Strings.toBytes("n");

    /**
     * The column qualifier holding the name of the feature used to retrieve the containment.
     */
    private static final byte[] CONTAINING_FEATURE_QUALIFIER = Strings.toBytes("g");

    /**
     * The HBase table used to access the model.
     */
    protected final Table table;

    /**
     * Constructs a new {@code AbstractHBaseBackend} on the given {@code table}.
     *
     * @param table the HBase table
     */
    protected AbstractHBaseBackend(Table table) {
        checkNotNull(table);

        this.table = table;
    }

    @Override
    public void save() {
        // TODO Implement this method
    }

    @Override
    public void copyTo(DataMapper target) {
        Log.warn("NeoEMF/HBase doesn't support copy backend feature");
    }

    @Override
    public boolean isDistributed() {
        return true;
    }

    @Override
    protected void innerClose() throws IOException {
        table.close();
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id);

        return resultFrom(id)
                .map(r -> {
                    byte[] byteId = r.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER);
                    byte[] byteName = r.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);
                    if (nonNull(byteId) && nonNull(byteName)) {
                        return SingleFeatureBean.of(StringId.of(Bytes.toString(byteId)), Bytes.toString(byteName));
                    }
                    return null;
                });
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id);
        checkNotNull(container);

        try {
            Put put = new Put(Strings.toBytes(id.toString()))
                    .addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Strings.toBytes(container.owner().toString()))
                    .addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Strings.toBytes(container.id()));

            table.put(put);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unsetContainer(Id id) {
        checkNotNull(id);

        try {
            Delete delete = new Delete(Strings.toBytes(id.toString()))
                    .addColumns(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER)
                    .addColumns(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);

            table.delete(delete);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id);

        return resultFrom(id)
                .map(result -> {
                    byte[] byteName = result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER);
                    byte[] byteUri = result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER);

                    return nonNull(byteName) && nonNull(byteUri)
                            ? ClassBean.of(Bytes.toString(byteName), Bytes.toString(byteUri))
                            : null;
                });
    }

    @Override
    public void metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id);
        checkNotNull(metaClass);

        try {
            Put put = new Put(Strings.toBytes(id.toString()))
                    .addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Strings.toBytes(metaClass.name()))
                    .addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Strings.toBytes(metaClass.uri()));

            table.put(put);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key);

        return resultFrom(key.owner())
                .map(r -> r.getValue(PROPERTY_FAMILY, Strings.toBytes(key.id())))
                .map(v -> {
                    try {
                        return SERIALIZER_FACTORY.<V>forAny().deserialize(v);
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);

        try {
            Put put = new Put(Strings.toBytes(key.owner().toString()))
                    .addColumn(PROPERTY_FAMILY, Strings.toBytes(key.id()), SERIALIZER_FACTORY.<V>forAny().serialize(value));

            table.put(put);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return previousValue;
    }

    @Override
    public void unsetValue(SingleFeatureBean key) {
        checkNotNull(key);

        try {
            Delete delete = new Delete(Strings.toBytes(key.owner().toString()))
                    .addColumns(PROPERTY_FAMILY, Strings.toBytes(key.id()));

            table.delete(delete);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a raw value from the {@link Table} according to the given {@code id}.
     *
     * @param id the identifier of the {@link Result} to retrieve
     *
     * @return on {@link Optional} containing the {@link Result}, or an empty {@link Optional} if the element has not
     * been found
     */
    private Optional<Result> resultFrom(Id id) {
        try {
            Get get = new Get(Strings.toBytes(id.toString()));

            Result result = table.get(get);
            return !result.isEmpty() ? Optional.of(result) : Optional.empty();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
