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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.io.serializer.Serializers;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link HBaseBackend} that provides overall behavior for the management of a HBase database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend implements HBaseBackend {

    /**
     * The column family holding properties.
     */
    protected static final byte[] PROPERTY_FAMILY = Bytes.toBytes("p");

    /**
     * The column family holding instances.
     */
    protected static final byte[] TYPE_FAMILY = Bytes.toBytes("t");

    /**
     * The column family holding containments.
     */
    protected static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");

    /**
     * The column qualifier holding the URI of meta-models.
     */
    private static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");

    /**
     * The column qualifier holding the name of classes.
     */
    private static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");

    /**
     * The column qualifier holding the identifier of containers.
     */
    private static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");

    /**
     * The column qualifier holding the name of the feature used to retrieve the containment.
     */
    private static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

    /**
     * The HBase table used to access the model.
     */
    protected final Table table;

    /**
     * Whether this back-end is closed.
     */
    private boolean isClosed;

    /**
     * Constructs a new {@code AbstractHBaseBackend} on the given {@code table}.
     *
     * @param table the HBase table
     */
    protected AbstractHBaseBackend(Table table) {
        this.table = checkNotNull(table);
        this.isClosed = false;
    }

    @Override
    public void save() {
        // TODO Implement this method
    }

    @Override
    public void close() {
        if (isClosed) {
            return;
        }

        isClosed = true;

        try {
            table.close();
        }
        catch (IOException ignored) {
        }
    }

    @Override
    public void copyTo(DataMapper target) {
        Log.warn("NeoEMF/HBase doesn't support copy backend feature");
    }

    @Override
    public boolean isDistributed() {
        return true;
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return resultFrom(id)
                .map(result -> {
                    byte[] byteId = result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER);
                    byte[] byteName = result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);
                    if (nonNull(byteId) && nonNull(byteName)) {
                        return Optional.of(ContainerDescriptor.of(StringId.of(Bytes.toString(byteId)), Bytes.toString(byteName)));
                    }
                    return Optional.<ContainerDescriptor>empty();
                })
                .orElseGet(Optional::empty);
    }

    @Override
    public void containerFor(Id id, @Nullable ContainerDescriptor container) {
        checkNotNull(id);

        try {
            if (nonNull(container)) {
                Put put = new Put(Bytes.toBytes(id.toString()))
                        .addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(container.id().toString()))
                        .addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(container.name()));

                table.put(put);
            }
            else {
                Delete delete = new Delete(Bytes.toBytes(id.toString()))
                        .addColumns(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER)
                        .addColumns(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);

                table.delete(delete);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return resultFrom(id)
                .map(result -> {
                    byte[] byteName = result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER);
                    byte[] byteUri = result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER);
                    if (nonNull(byteName) && nonNull(byteUri)) {
                        return Optional.of(ClassDescriptor.of(Bytes.toString(byteName), Bytes.toString(byteUri)));
                    }
                    return Optional.<ClassDescriptor>empty();
                })
                .orElseGet(Optional::empty);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        try {
            Put put = new Put(Bytes.toBytes(id.toString()))
                    .addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(metaclass.name()))
                    .addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(metaclass.uri()));

            table.put(put);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        checkNotNull(key);

        return resultFrom(key.id())
                .map(result -> Optional.ofNullable(result.getValue(PROPERTY_FAMILY, Bytes.toBytes(key.name())))
                        .map(value -> Optional.of(Serializers.<V>forGenerics().deserialize(value)))
                        .orElseGet(Optional::empty))
                .orElseGet(Optional::empty);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);

        try {
            Put put = new Put(Bytes.toBytes(key.id().toString()))
                    .addColumn(PROPERTY_FAMILY, Bytes.toBytes(key.name()), Serializers.<V>forGenerics().serialize(value));

            table.put(put);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return previousValue;
    }

    @Override
    public void unsetValue(FeatureKey key) {
        checkNotNull(key);

        try {
            Delete delete = new Delete(Bytes.toBytes(key.id().toString()))
                    .addColumns(PROPERTY_FAMILY, Bytes.toBytes(key.name()));

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
            Get get = new Get(Bytes.toBytes(id.toString()));

            Result result = table.get(get);
            return !result.isEmpty() ? Optional.of(result) : Optional.empty();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
