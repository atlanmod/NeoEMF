/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
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
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithLists;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.BytesWriter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link InMemoryBackend} that provides the default behavior of containers and meta-classes management.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractInMemoryBackend extends AbstractBackend implements InMemoryBackend, ManyValueWithLists, AllReferenceAs<Long> {

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
        checkNotNull(key, "key");
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        Action checkFunc = () -> checkNotNull(id, "id");

        // Define the container
        Callable<SingleFeatureBean> getFunc = () -> containers().get(id);

        // The composed query to execute on the database
        Maybe<SingleFeatureBean> databaseQuery = Maybe.fromCallable(getFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        Action checkFunc = () -> {
            checkNotNull(id, "id");
            checkNotNull(container, "container");
        };

        // Define the container
        Action setFunc = () -> containers().put(id, container);

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(setFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        Action checkFunc = () -> checkNotNull(id, "id");

        // Remove the container
        Action removeFunc = () -> containers().remove(id);

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(removeFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        Action checkFunc = () -> checkNotNull(id, "id");

        // Retrieve the meta-class
        Callable<ClassBean> getFunc = () -> instances().get(id);

        // The composed query to execute on the database
        Maybe<ClassBean> databaseQuery = Maybe.fromCallable(getFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        Action checkFunc = () -> {
            checkNotNull(id, "id");
            checkNotNull(metaClass, "metaClass");
        };

        // Define the meta-class, if it does not already exist
        Callable<ClassBean> setFunc = () -> instances().putIfAbsent(id, metaClass);

        // The composed query to execute on the database
        Completable databaseQuery = Maybe.fromCallable(setFunc)
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .ignoreElement();

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Observable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        // The composed query to execute on the database
        Observable<Id> databaseQuery = Maybe.fromCallable(() -> instances().entrySet())
                .flattenAsObservable(Functions.identity())
                .filter(e -> metaClasses.contains(e.getValue()))
                .map(Map.Entry::getKey);

        return dispatcher().submit(databaseQuery);
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
        checkNotNull(value, "value");

        return Optional.ofNullable(cast(features().put(key, value)));
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
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
