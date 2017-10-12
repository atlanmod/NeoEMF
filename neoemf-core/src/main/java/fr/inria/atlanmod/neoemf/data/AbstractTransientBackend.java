/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithLists;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.BytesWriter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link TransientBackend} that provides the default behavior of containers and meta-classes management.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractTransientBackend extends AbstractBackend implements TransientBackend, ManyValueWithLists, AllReferenceAs<Long> {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
     */
    @Nonnull
    protected static final BeanSerializerFactory SERIALIZER_FACTORY = BeanSerializerFactory.getInstance();

    /**
     * Constructs a new {@code AbstractTransientBackend}.
     */
    protected AbstractTransientBackend() {
        super();
    }

    /**
     * Constructs a new {@code AbstractTransientBackend} with the given {@code name}.
     *
     * @param name the unique name of this backend
     */
    protected AbstractTransientBackend(@Nullable String name) {
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
     * Checks the specified {@code key} before using it.
     *
     * @param key the key to check
     */
    protected void checkKey(FeatureBean key) {
        checkNotNull(key);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(containers().get(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id);
        checkNotNull(container);

        containers().put(id, container);
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id);

        containers().remove(id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(instances().get(id));
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id);
        checkNotNull(metaClass);

        return isNull(instances().putIfAbsent(id, metaClass));
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return instances().entrySet().stream()
                .filter(e -> metaClasses.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkKey(key);

        return Optional.ofNullable(cast(features().get(key)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkKey(key);
        checkNotNull(value);

        return Optional.ofNullable(cast(features().put(key, value)));
    }

    @Override
    public <V> void removeValue(SingleFeatureBean key) {
        checkKey(key);

        features().remove(key);
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
        protected static final long CLASS = (long) Math.pow(2, 6);

        /**
         * The estimated size of a {@link FeatureBean}.
         */
        protected static final long FEATURE = (long) Long.BYTES + (long) Integer.BYTES;

        /**
         * The estimated size of a feature value. Can be a simple value or an array for multi-valued features.
         */
        protected static final long FEATURE_VALUE = (long) Math.pow(2, 12);

        /**
         * This class should not be instantiated.
         *
         * @throws IllegalStateException every time
         */
        private Sizes() {
            throw new IllegalStateException("This class should not be instantiated");
        }
    }

    /**
     * A ChronicleMap serializer that delegates its processing to an internal {@link Serializer}.
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
        private final Serializer<T> delegate;

        /**
         * Constructs a new {@code SerializerDecorator} on the specified {@code delegate}.
         *
         * @param delegate the serializer where to delegate the serialization process
         */
        public BeanMarshaller(Serializer<T> delegate) {
            this.delegate = delegate;
        }

        @Nonnull
        @Override
        public T read(@SuppressWarnings("rawtypes") Bytes in, @Nullable T using) {
            try {
                return delegate.deserialize(in.inputStream());
            }
            catch (IOException e) {
                throw new IllegalStateException(e); // Should never happen
            }
        }

        @Override
        public void write(@SuppressWarnings("rawtypes") Bytes out, @Nonnull T value) {
            try {
                delegate.serialize(value, out.outputStream());
            }
            catch (IOException e) {
                throw new IllegalStateException(e); // Should never happen
            }
        }
    }
}
