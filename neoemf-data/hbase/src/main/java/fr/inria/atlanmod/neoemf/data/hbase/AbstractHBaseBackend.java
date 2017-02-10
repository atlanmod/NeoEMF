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

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

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
     * ???
     */
    protected static final byte[] TYPE_FAMILY = Bytes.toBytes("t");

    /**
     * ???
     */
    protected static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");

    /**
     * ???
     */
    protected static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");

    /**
     * ???
     */
    protected static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");

    /**
     * ???
     */
    protected static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");

    /**
     * ???
     */
    protected static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

    /**
     * The HBase table used to access the model.
     */
    protected final Table table;

    /**
     * Constructs a new {@code AbstractHBaseBackend} on th given {@code table}
     *
     * @param table the HBase table
     */
    protected AbstractHBaseBackend(Table table) {
        this.table = checkNotNull(table);
    }

    @Override
    public void save() {
        // TODO Implement this method
    }

    @Override
    public void close() {
        // TODO Implement this method
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isDistributed() {
        return true;
    }

    @Override
    public void create(Id id) {
        // Do nothing
    }

    @Override
    public boolean has(Id id) {
        return false;
    }

    @Nonnull
    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        return fromDatabase(id)
                .map(result -> {
                    byte[] byteId = result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER);
                    byte[] byteName = result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);
                    if (isNull(byteId) || isNull(byteName)) {
                        return Optional.<ContainerValue>empty();
                    }
                    return Optional.of(ContainerValue.of(StringId.of(Bytes.toString(byteId)), Bytes.toString(byteName)));
                })
                .orElse(Optional.empty());
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        try {
            Put put = new Put(Bytes.toBytes(id.toString()));
            put.addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(container.id().toString()));
            put.addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(container.name()));
            table.put(put);
        }
        catch (IOException ignore) {
        }
    }

    @Nonnull
    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        return fromDatabase(id)
                .map(result -> {
                    byte[] byteName = result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER);
                    byte[] byteUri = result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER);
                    if (isNull(byteName) || isNull(byteUri)) {
                        return Optional.<MetaclassValue>empty();
                    }
                    return Optional.of(MetaclassValue.of(Bytes.toString(byteName), Bytes.toString(byteUri)));
                })
                .orElse(Optional.empty());
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        try {
            byte[] idAsBytes = Bytes.toBytes(id.toString());
            Put put = new Put(idAsBytes);
            put.addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(metaclass.uri()));
            put.addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(metaclass.name()));
            table.checkAndPut(idAsBytes, TYPE_FAMILY, ECLASS_QUALIFIER, null, put);
        }
        catch (IOException ignore) {
        }
    }

    /**
     * Retrieves a value from the {@link Table} according to the given {@code key}.
     *
     * @param key the key of the element to retrieve
     *
     * @return on {@link Optional} containing the element, or an empty {@link Optional} if the element has not been
     * found
     */
    protected Optional<byte[]> fromDatabase(FeatureKey key) {
        return fromDatabase(key.id())
                .map(result -> Optional.ofNullable(result.getValue(PROPERTY_FAMILY, Bytes.toBytes(key.name()))))
                .orElse(Optional.empty());
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the {@link Table}.
     *
     * @param key   the key of the element to save
     * @param value the value to save
     */
    protected void toDatabase(FeatureKey key, @Nullable byte[] value) {
        try {
            byte[] idAsBytes = Bytes.toBytes(key.id().toString());
            Put put = new Put(idAsBytes);
            put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(key.name()), value);
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
    protected void outDatabase(FeatureKey key) {
        try {
            byte[] idAsBytes = Bytes.toBytes(key.id().toString());
            Delete delete = new Delete(idAsBytes);
            delete.addColumn(PROPERTY_FAMILY, Bytes.toBytes(key.name()));
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
    private Optional<Result> fromDatabase(Id id) {
        Optional<Result> value = Optional.empty();

        try {
            byte[] idAsBytes = Bytes.toBytes(id.toString());
            Get get = new Get(idAsBytes);
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
     * Utility class that is responsible of {@link Object} to {@link Byte} encoding. This class is used to ensure that
     * HBase keys have the same size, and provides an uniformized API to encode strings and {@link EReference}.
     */
    @ParametersAreNonnullByDefault
    protected static class Serializer {

        /**
         * Expected length (in {@code bytes}) of stored elements.
         */
        public static final int UUID_LENGTH = 23;

        /**
         * The default separator used to serialize {@link Collection}s.
         */
        public static final char VALUE_SEPERATOR_DEFAULT = ',';

        /**
         * This class should not be instantiated.
         *
         * @throws IllegalStateException every time
         */
        private Serializer() {
            throw new IllegalStateException("This class should not be instantiated");
        }

        /**
         * Encodes the provided {@link String} array into an array of {@code bytes} that can be stored in the database.
         *
         * @param values an array of {@link String}s representing the {@link EReference}s to encode.
         *
         * @return an array of {@code bytes}
         *
         * @throws NullPointerException if the value to encode is {@code null}
         * @see Serializer#deserializeReferences(byte[])
         */
        @Nonnull
        public static byte[] serializeReferences(String[] values) {
            checkNotNull(values);

            return Bytes.toBytes(Joiner.on(VALUE_SEPERATOR_DEFAULT).join(values));
        }

        /**
         * Decodes the provided {@code byte} array into an array of {@link String} representing {@link EReference}s.
         *
         * @param data the HBase bytes to decode
         *
         * @return an array of {@link String}s representing the {@link EReference}s decoded from the database
         *
         * @throws NullPointerException     if the given {@code bytes} is null
         * @throws IllegalArgumentException if the length of {@code bytes} is not a multiple of {@code UUID_LENGTH}
         * @see Serializer#serializeReferences(String[])
         */
        @Nonnull
        public static String[] deserializeReferences(byte[] data) {
            checkNotNull(data);

            checkArgument(data.length % (UUID_LENGTH + 1) == UUID_LENGTH);

            int length = (data.length + 1) / (UUID_LENGTH + 1);

            String[] strings = new String[length];
            int index = 0;

            for (String s : Splitter.on(VALUE_SEPERATOR_DEFAULT).split(Bytes.toString(data))) {
                strings[index++] = s;
            }

            return strings;
        }

        /**
         * Encodes an array of {@link String}s into an array of {@code bytes} that can be stored in the database.
         *
         * @param values the array to encode
         *
         * @return the encoded {@code byte} array
         *
         * @see Serializer#deserializeValues(byte[])
         */
        @Nonnull
        public static <V> byte[] serializeValues(V[] values) {
            checkNotNull(values);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream stream = new ObjectOutputStream(baos)) {
                stream.writeObject(values);
                stream.flush();
                return baos.toByteArray();
            }
            catch (IOException e) {
                NeoLogger.error("Unable to convert String[] to byte[]");
                throw new RuntimeException(e);
            }
        }

        /**
         * Decodes an array of bytes into an array of {@link String}s.
         *
         * @param data the {@code byte} array to decode
         *
         * @return the decoded {@link String} array
         *
         * @throws NullPointerException if the given array is {@code null}
         * @see Serializer#serializeValues(Object[])
         */
        @Nonnull
        @SuppressWarnings("unchecked")
        public static <V> V[] deserializeValues(byte[] data) {
            checkNotNull(data);

            try (ByteArrayInputStream baos = new ByteArrayInputStream(data); ObjectInputStream stream = new ObjectInputStream(baos)) {
                return (V[]) stream.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                NeoLogger.error("Unable to convert byte[] to String[]");
                throw new RuntimeException(e);
            }
        }
    }
}
