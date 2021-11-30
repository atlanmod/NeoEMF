/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithLists;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.BytesWriter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.function.Converter;
import org.atlanmod.commons.io.serializer.BinarySerializer;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * An abstract {@link InMemoryBackend} that provides the default behavior of containers and meta-classes management.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractInMemoryBackend extends AbstractBackend implements InMemoryBackend, ManyValueWithLists, AllReferenceAs<Long> {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link BinarySerializer} instances.
     */
    @Nonnull
    protected static final BeanSerializerFactory SERIALIZER_FACTORY = BeanSerializerFactory.getInstance();

    /**
     * Constructs a new {@code AbstractInMemoryBackend}.
     */
    protected AbstractInMemoryBackend() {
        super();
    }

    /**
     * Constructs a new {@code AbstractInMemoryBackend} with the given {@code name}.
     *
     * @param name the unique name of this backend
     */
    protected AbstractInMemoryBackend(@Nullable String name) {
        super(name);
    }

    /**
     * Casts the {@code value} as expected.
     *
     * @param value the value to be cast
     * @param <V>   the expected type of the value
     *
     * @return the {@code value} after casting, or {@code null} if the {@code value} is {@code null}
     *
     * @throws ClassCastException if the {@code value} is not {@code null} and is not assignable to the type {@code V}
     */
    @Nullable
    @SuppressWarnings("unchecked")
    private static <V> V cast(@Nullable Object value) {
        return (V) value;
    }

    @Override
    public void internalSave() {
        // No need to save anything
    }

    /**
     * Returns the map that holds all containers.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, SingleFeatureBean> containers();

    /**
     * Returns the map that holds all instances.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, ClassBean> instances();

    /**
     * Returns the map that holds single-features.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<SingleFeatureBean, Object> features();

    /**
     * Checks the specified {@code feature} before using it.
     *
     * @param feature the feature to check
     */
    protected void checkFeature(FeatureBean feature) {
        checkNotNull(feature, "feature");
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        return Optional.ofNullable(containers().get(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        containers().put(id, container);
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        containers().remove(id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        return Optional.ofNullable(instances().get(id));
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        return isNull(instances().putIfAbsent(id, metaClass));
    }

    @Nonnull
    @Override
    public Stream<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return instances().entrySet().stream()
                .filter(e -> metaClasses.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .distinct();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkFeature(feature);

        return Optional.ofNullable(cast(features().get(feature)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkFeature(feature);
        checkNotNull(value, "value");

        return Optional.ofNullable(cast(features().put(feature, value)));
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkFeature(feature);

        features().remove(feature);
    }

    @Nonnull
    @Override
    public Converter<Id, Long> referenceConverter() {
        return IdConverters.withLong();
    }

    /**
     * Some statistics about the size of key and values.
     */
    @Static
    @ParametersAreNonnullByDefault
    protected static final class Sizes {

        /**
         * The estimated number of entries in maps.
         */
        protected static final long ENTRIES = (long) Math.pow(2, 20);

        /**
         * The estimated size of an {@link Id}.
         */
        protected static final long ID = (long) Long.BYTES;

        /**
         * The estimated size of a {@link ClassBean}.
         */
        protected static final long CLASS = (long) Math.pow(2, 7);

        /**
         * The estimated size of a {@link FeatureBean}.
         */
        protected static final long FEATURE = ID + (long) Integer.BYTES;

        /**
         * The estimated size of a feature value. Can be a simple value or an array for multi-valued features.
         */
        protected static final long FEATURE_VALUE = (long) Math.pow(2, 12);

        private Sizes() {
            throw Throwables.notInstantiableClass(getClass());
        }
    }

    /**
     * A ChronicleMap serializer that delegates its processing to an internal {@link BinarySerializer}.
     *
     * @param <T> the type of the (de)serialized value
     */
    @Immutable
    @ParametersAreNonnullByDefault
    static final class BeanMarshaller<T> implements BytesWriter<T>, BytesReader<T> {

        /**
         * The serializer where to delegate the serialization process.
         */
        @Nonnull
        private final BinarySerializer<T> delegate;

        /**
         * Constructs a new {@code SerializerDecorator} on the specified {@code delegate}.
         *
         * @param delegate the serializer where to delegate the serialization process
         */
        public BeanMarshaller(BinarySerializer<T> delegate) {
            this.delegate = delegate;
        }

        @Nonnull
        @Override
        public T read(@SuppressWarnings("rawtypes") Bytes in, @Nullable T using) {
            try {
                return delegate.deserialize(in.inputStream());
            }
            catch (IOException e) {
                throw Throwables.shouldNeverHappen(e);
            }
        }

        @Override
        public void write(@SuppressWarnings("rawtypes") Bytes out, @Nonnull T value) {
            try {
                delegate.serialize(value, out.outputStream());
            }
            catch (IOException e) {
                throw Throwables.shouldNeverHappen(e);
            }
        }
    }
}
