/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.BytesWriter;
import net.openhft.chronicle.map.ChronicleMap;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link InMemoryBackend} that provides the default behavior of containers and meta-classes management.
 */
@ParametersAreNonnullByDefault
abstract class AbstractInMemoryBackend extends AbstractBackend implements InMemoryBackend, ManyValueWithArrays, AllReferenceAs<Long> {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
     */
    @Nonnull
    protected final BeanSerializerFactory serializers = BeanSerializerFactory.getInstance();

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

    @Nonnull
    @Override
    protected Action blockingSave() {
        // No need to save anything
        return Functions.EMPTY_ACTION;
    }

    /**
     * Returns the map that holds all containers.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract ChronicleMap<Id, SingleFeatureBean> allContainers();

    /**
     * Returns the map that holds all instances.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract ChronicleMap<Id, ClassBean> allInstances();

    /**
     * Returns the map that holds single-features.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract ChronicleMap<SingleFeatureBean, Object> allFeatures();

    /**
     * Checks the specified {@code key} before using it.
     *
     * @param key the key to check
     */
    protected void checkKey(FeatureBean key) {
        checkNotNull(key, "key");
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        Callable<SingleFeatureBean> getFunc = () -> allContainers().get(id);

        Maybe<SingleFeatureBean> query = Maybe.fromCallable(getFunc);

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        Action setFunc = () -> allContainers().put(id, container);

        Completable query = Completable.fromAction(setFunc);

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        checkNotNull(id, "id");

        Action removeFunc = () -> allContainers().remove(id);

        Completable query = Completable.fromAction(removeFunc);

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        Callable<ClassBean> getFunc = () -> allInstances().get(id);

        Maybe<ClassBean> query = Maybe.fromCallable(getFunc);

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        Callable<ClassBean> setFunc = () -> allInstances().putIfAbsent(id, metaClass);

        Completable query = Maybe.fromCallable(setFunc)
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .ignoreElement();

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        Flowable<Id> query = Maybe.fromCallable(() -> allInstances().entrySet())
                .flattenAsFlowable(Functions.identity())
                .filter(e -> metaClasses.contains(e.getValue()))
                .map(Map.Entry::getKey);

        return query.compose(dispatcher().dispatchFlowable());
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        checkKey(key);

        Callable<V> getFunc = () -> cast(allFeatures().get(key));

        Maybe<V> query = Maybe.fromCallable(getFunc);

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        checkKey(key);
        checkNotNull(value, "value");

        Action setFunc = () -> allFeatures().put(key, value);

        Completable query = Completable.fromAction(setFunc);

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        checkKey(key);

        Action removeFunc = () -> allFeatures().remove(key);

        Completable query = Completable.fromAction(removeFunc);

        return query.compose(dispatcher().dispatchCompletable());
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
    protected static final class DataSizes {

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
        private DataSizes() {
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
