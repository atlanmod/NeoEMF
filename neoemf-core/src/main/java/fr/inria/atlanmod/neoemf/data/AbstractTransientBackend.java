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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithLists;
import fr.inria.atlanmod.neoemf.data.mapping.ReferenceAs;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.BytesWriter;

import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link TransientBackend} that provides the default behavior of containers and meta-classes management.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractTransientBackend extends AbstractBackend implements TransientBackend, ManyValueWithLists, AllReferenceAs<String> {

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
    public void unsetContainer(Id id) {
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
    public <V> void unsetValue(SingleFeatureBean key) {
        checkKey(key);

        features().remove(key);
    }

    @Nonnull
    @Override
    public Converter<Id, String> referenceConverter() {
        return ReferenceAs.DEFAULT_CONVERTER;
    }

    /**
     * Some statistics about the size of key and values.
     */
    protected interface Sizes {

        /**
         * The estimated number of entries in maps.
         */
        long ENTRIES = powerTwo(20);

        /**
         * The estimated size of an {@link Id}.
         */
        long ID = powerTwo(5);

        /**
         * The estimated size of a {@link ClassBean}.
         */
        long CLASS = powerTwo(7);

        /**
         * The estimated size of a {@link FeatureBean}.
         */
        long FEATURE = powerTwo(6);

        /**
         * The estimated size of a feature value.
         */
        long FEATURE_VALUE = powerTwo(10);

        /**
         * Returns {@code 2}<sup>{@code exponent}</sup>.
         */
        @Nonnegative
        static long powerTwo(int exponent) {
            double value = Math.pow(2, exponent);

            if (value > Long.MAX_VALUE) {
                throw new ArithmeticException(String.format("%f > %d", value, Long.MAX_VALUE));
            }

            return (long) value;
        }
    }

    /**
     * @param <T>
     */
    @ParametersAreNonnullByDefault
    static final class BeanMarshaller<T> implements BytesWriter<T>, BytesReader<T> {

        private final Serializer<T> serializer;

        public BeanMarshaller(Serializer<T> serializer) {
            this.serializer = serializer;
        }

        @Nonnull
        @Override
        public T read(@SuppressWarnings("rawtypes") Bytes in, @Nullable T using) {
            try {
                return serializer.deserialize(new DataInputStream(in.inputStream()));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void write(@SuppressWarnings("rawtypes") Bytes out, @NotNull T value) {
            try {
                serializer.serialize(value, new DataOutputStream(out.outputStream()));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
