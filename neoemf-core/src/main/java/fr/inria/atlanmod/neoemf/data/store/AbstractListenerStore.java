/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class AbstractListenerStore extends AbstractStore {

    /**
     * Constructs a new {@code AbstractListenerStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected AbstractListenerStore(Store store) {
        super(store);
    }

    @Override
    public void close() {
        onCall(super::close);
    }

    @Override
    public void save() {
        onCall(super::save);
    }

    @Override
    public void copyTo(DataMapper target) {
        onCall(super::copyTo, target);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        return onCallResult(super::containerOf, id);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        onCall(super::containerFor, id, container);
    }

    @Override
    public void removeContainer(Id id) {
        onCall(super::removeContainer, id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        return onCallResult(super::metaClassOf, id);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return onCallResult(super::metaClassFor, id, metaClass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        return onCallResult(super::valueOf, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        return onCallResult(super::valueFor, key, value);
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        onCall(super::removeValue, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        return onCallResult(super::referenceOf, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return onCallResult(super::referenceFor, key, reference);
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        onCall(super::removeReference, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        return onCallResult(super::valueOf, key);
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        return onCallResult(super::allValuesOf, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        return onCallResult(super::valueFor, key, value);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        onCall(super::addValue, key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        onCall(super::addAllValues, key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        return onCallResult(super::appendValue, key, value);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        return onCallResult(super::appendAllValues, key, collection);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return onCallResult(super::removeValue, key);
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        onCall(super::removeAllValues, key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return onCallResult(super::sizeOfValue, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        return onCallResult(super::referenceOf, key);
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        return onCallResult(super::allReferencesOf, key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return onCallResult(super::referenceFor, key, reference);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        onCall(super::addReference, key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        onCall(super::addAllReferences, key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        return onCallResult(super::appendReference, key, reference);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return onCallResult(super::appendAllReferences, key, collection);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        return onCallResult(super::removeReference, key);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        onCall(super::removeAllReferences, key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return onCallResult(super::sizeOfReference, key);
    }

    /**
     * Logs the call of a method.
     *
     * @param runnable the method to call
     */
    private void onCall(Runnable runnable) {
        onCallResult((k, v) -> {
            runnable.run();
            return null;
        }, null, null);
    }

    /**
     * Logs the call of a method.
     *
     * @param consumer the method to call
     * @param key      the key used during the call
     */
    private <K> void onCall(Consumer<K> consumer, @Nullable K key) {
        onCallResult((k, v) -> {
            consumer.accept(k);
            return null;
        }, key, null);
    }

    /**
     * Logs the call of a method.
     *
     * @param consumer the method to call
     * @param key      the key used during the call
     * @param value    the value of the key
     */
    private <K, V> void onCall(BiConsumer<K, V> consumer, @Nullable K key, @Nullable V value) {
        onCallResult((k, v) -> {
            consumer.accept(k, v);
            return null;
        }, key, value);
    }

    /**
     * Logs the call of a method and returns the result.
     *
     * @param function the method to call
     * @param key      the key used during the call
     *
     * @return the result of the call
     */
    private <K, R> R onCallResult(Function<K, R> function, @Nullable K key) {
        return onCallResult((k, v) -> function.apply(k), key, null);
    }

    /**
     * Logs the call of a method and returns the result.
     *
     * @param function the method to call
     * @param key      the key used during the call
     * @param value    the value of the key
     *
     * @return the result of the call
     */
    @SuppressWarnings("unchecked")
    private <K, V, R> R onCallResult(BiFunction<K, V, R> function, @Nullable K key, @Nullable V value) {
        try {
            R result = function.apply(key, value);
            Object resultToLog = result;

            // Clone the stream
            if (Stream.class.isInstance(result)) {
                Stream<?> stream = Stream.class.cast(result);
                List<?> list = stream.collect(Collectors.toList());

                result = (R) list.stream();
                resultToLog = list;
            }

            onSuccess(new CallInfo<>(getCallingMethod(), key, value, resultToLog));
            return result;
        }
        catch (RuntimeException e) {
            onFailure(new CallInfo<>(getCallingMethod(), key, value, e));
            throw e;
        }
    }

    /**
     * Handle a succeeded call.
     *
     * @param info information about the call
     * @param <K>  the type of the key used during the call; nullable
     * @param <V>  the type of the value used during the call; nullable
     * @param <R>  the type of the result of the call; nullable
     */
    protected abstract <K, V, R> void onSuccess(CallInfo<K, V, R> info);

    /**
     * Handle a failed call.
     *
     * @param info information about the call
     * @param <K>  the type of the key used during the call; nullable
     * @param <V>  the type of the value used during the call; nullable
     */
    protected abstract <K, V> void onFailure(CallInfo<K, V, ?> info);

    /**
     * Returns the name of the calling method.
     *
     * @return the name
     */
    @Nonnull
    private String getCallingMethod() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stack.length; i++) {
            String methodName = stack[i].getMethodName();
            if (!methodName.startsWith("onCall")) {
                return methodName;
            }
        }

        throw new IllegalStateException(); // Should never happen
    }

    /**
     * An object representing a call.
     *
     * @param <K> the type of the key used during the call
     * @param <V> the type of the value used during the call
     * @param <R> the type of the result of the call
     */
    @ParametersAreNonnullByDefault
    protected static class CallInfo<K, V, R> {

        /**
         * The name of the called method.
         */
        @Nonnull
        private final String method;

        /**
         * The key used during the call.
         */
        @Nullable
        private final K key;

        /**
         * The value used during the call.
         */
        @Nullable
        private final V value;

        /**
         * The result of the call.
         */
        @Nullable
        private final R result;

        /**
         * The exception thrown during the call.
         */
        @Nullable
        private final Throwable thrownException;

        /**
         * Constructs a new succeeded call information.
         *
         * @param method the name of the called method
         * @param key    the key used during the call
         * @param value  the value used during the call
         * @param result the result of the call
         */
        public CallInfo(String method, @Nullable K key, @Nullable V value, R result) {
            this.method = method;
            this.key = key;
            this.value = value;
            this.result = result;
            this.thrownException = null;
        }

        /**
         * Constructs a new failed call information.
         *
         * @param method the name of the called method
         * @param key    the key used during the call
         * @param value  the value used during the call
         * @param e      the exception thrown during the call
         */
        public CallInfo(String method, @Nullable K key, @Nullable V value, Throwable e) {
            this.method = method;
            this.key = key;
            this.value = value;
            this.result = null;
            this.thrownException = e;
        }

        /**
         * Returns the name of the called method.
         *
         * @return the name of the called method
         */
        @Nonnull
        public String method() {
            return method;
        }

        /**
         * Returns the key used during the call.
         *
         * @return the key used during the call
         */
        @Nullable
        public K key() {
            return key;
        }

        /**
         * Returns the value used during the call.
         *
         * @return the value used during the call
         */
        @Nullable
        public V value() {
            return value;
        }

        /**
         * Returns the result of the call.
         *
         * @return the result of the call
         */
        @Nullable
        public R result() {
            return result;
        }

        /**
         * Returns the exception thrown during the call.
         *
         * @return the exception thrown during the call
         */
        @Nullable
        public Throwable thrownException() {
            return thrownException;
        }
    }
}
