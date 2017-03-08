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
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.mapper.PersistenceMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 *
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend extends AbstractPersistenceBackend implements HBaseBackend {

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
     * ???
     */
    private boolean isClosed;

    /**
     * Constructs a new {@code AbstractHBaseBackend} on th given {@code table}
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

        try {
            table.close();
        }
        catch (IOException ignore) {
        }
        finally {
            isClosed = true;
        }
    }

    @Override
    public void copyTo(PersistenceMapper target) {
        Log.warn("NeoEMF/HBase doesn't support copy backend feature");
    }

    @Override
    public boolean isDistributed() {
        return true;
    }

    @Override
    public boolean exists(Id id) {
        return metaclassOf(id).isPresent();
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return get(id)
                .map(result -> {
                    byte[] byteId = result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER);
                    byte[] byteName = result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);
                    if (nonNull(byteId) && nonNull(byteName)) {
                        return Optional.of(ContainerDescriptor.of(Serializer.deserialize(byteId), Serializer.deserialize(byteName)));
                    }
                    return Optional.<ContainerDescriptor>empty();
                })
                .orElse(Optional.empty());
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        checkNotNull(id);
        checkNotNull(container);

        try {
            Put put = new Put(Serializer.serialize(id));
            put.addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Serializer.serialize(container.id()));
            put.addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Serializer.serialize(container.name()));
            table.put(put);
        }
        catch (IOException ignore) {
        }
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return get(id)
                .map(result -> {
                    byte[] byteName = result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER);
                    byte[] byteUri = result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER);
                    if (nonNull(byteName) && nonNull(byteUri)) {
                        return Optional.of(ClassDescriptor.of(Serializer.deserialize(byteName), Serializer.deserialize(byteUri)));
                    }
                    return Optional.<ClassDescriptor>empty();
                })
                .orElse(Optional.empty());
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        try {
            Put put = new Put(Serializer.serialize(id));
            put.addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Serializer.serialize(metaclass.name()));
            put.addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Serializer.serialize(metaclass.uri()));
            table.put(put);
        }
        catch (IOException ignore) {
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        checkNotNull(key);

        return get(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);
        put(key, value);
        return previousValue;
    }

    @Override
    public void unsetValue(FeatureKey key) {
        checkNotNull(key);

        delete(key);
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        checkNotNull(key);

        return get(key).isPresent();
    }

    /**
     * Retrieves a value from the {@link Table} according to the given {@code key}.
     *
     * @param key the key of the element to retrieve
     *
     * @return on {@link Optional} containing the element, or an empty {@link Optional} if the element has not been
     * found
     */
    protected <V> Optional<V> get(FeatureKey key) {
        return get(key.id())
                .map(result -> {
                    Optional<byte[]> value = Optional.ofNullable(result.getValue(PROPERTY_FAMILY, Serializer.serialize(key.name())));
                    return value.map(bytes -> Optional.<V>of(Serializer.deserialize(bytes))).orElse(Optional.empty());
                })
                .orElse(Optional.empty());
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the {@link Table}.
     *
     * @param key   the key of the element to save
     * @param value the value to save
     */
    protected <V> void put(FeatureKey key, V value) {
        try {
            Put put = new Put(Serializer.serialize(key.id()));
            put.addColumn(PROPERTY_FAMILY, Serializer.serialize(key.name()), Serializer.serialize(value));
            table.put(put);
        }
        catch (IOException ignore) {
        }
    }

    /**
     * Removes a value from the {@link Table} according to its {@code key}.
     *
     * @param key the key of the element to remove
     */
    protected void delete(FeatureKey key) {
        try {
            Delete delete = new Delete(Serializer.serialize(key.id()));
            delete.addColumn(PROPERTY_FAMILY, Serializer.serialize(key.name()));
            table.delete(delete);
        }
        catch (IOException ignore) {
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
    private Optional<Result> get(Id id) {
        Optional<Result> value = Optional.empty();

        try {
            Get get = new Get(Serializer.serialize(id));
            Result result = table.get(get);
            if (!result.isEmpty()) {
                value = Optional.of(result);
            }
        }
        catch (IOException ignore) {
        }

        return value;
    }

    /**
     * Utility class that is responsible of {@link Object} to {@link Byte} encoding.
     */
    @ParametersAreNonnullByDefault
    protected static class Serializer {

        /**
         * This class should not be instantiated.
         *
         * @throws IllegalStateException every time
         */
        private Serializer() {
            throw new IllegalStateException("This class should not be instantiated");
        }

        /**
         * Serializes an {@code Object} to a byte array for storage/serialization.
         *
         * @param value the object to serialize to bytes
         *
         * @return the serialized object as a byte array
         */
        @Nonnull
        public static <T> byte[] serialize(T value) {
            checkNotNull(value);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(baos)) {
                out.writeObject(value);
                out.flush();
                return baos.toByteArray();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Deserializes a single {@code Object} from an array of bytes.
         *
         * @param data the serialized object as a byte array
         *
         * @return the deserialized object
         */
        @SuppressWarnings("unchecked")
        @Nonnull
        public static <T> T deserialize(byte[] data) {
            checkNotNull(data);

            try (ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(data))) {
                return (T) in.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
