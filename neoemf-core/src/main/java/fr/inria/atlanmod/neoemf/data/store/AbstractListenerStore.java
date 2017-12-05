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
import java.util.concurrent.atomic.AtomicLong;
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

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link Store} that listen and intercept calls made on this store chain.
 */
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
        CallInfoBuilder callInfo = CallInfo.forMethod("close");

        try {
            super.close();
            onSuccess(callInfo);
        }
        catch (RuntimeException e) {
            onFailure(callInfo.withThrownException(e));
            throw e;
        }
    }

    @Override
    public void save() {
        CallInfoBuilder callInfo = CallInfo.forMethod("save");

        try {
            super.save();
            onSuccess(callInfo);
        }
        catch (RuntimeException e) {
            onFailure(callInfo.withThrownException(e));
            throw e;
        }
    }

    @Override
    public void copyTo(DataMapper target) {
        CallInfoBuilder callInfo = CallInfo.forMethod("copyTo");

        try {
            super.copyTo(target);
            onSuccess(callInfo);
        }
        catch (RuntimeException e) {
            onFailure(callInfo.withThrownException(e));
            throw e;
        }
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        CallInfoBuilder callInfo = CallInfo.forMethod("containerOf").withKey(id);

        return super.containerOf(id)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        CallInfoBuilder callInfo = CallInfo.forMethod("containerFor").withKey(id).withValue(container);

        return super.containerFor(id, container)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeContainer").withKey(id);

        return super.removeContainer(id)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        CallInfoBuilder callInfo = CallInfo.forMethod("metaClassOf").withKey(id);

        return super.metaClassOf(id)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        CallInfoBuilder callInfo = CallInfo.forMethod("metaClassFor").withKey(id).withValue(metaClass);

        return super.metaClassFor(id, metaClass)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("valueOf").withKey(key);

        return super.<V>valueOf(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        CallInfoBuilder callInfo = CallInfo.forMethod("valueFor").withKey(key).withValue(value);

        return super.valueFor(key, value)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeValue").withKey(key);

        return super.removeValue(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("referenceOf").withKey(key);

        return super.referenceOf(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        CallInfoBuilder callInfo = CallInfo.forMethod("referenceFor").withKey(key).withValue(reference);

        return super.referenceFor(key, reference)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeReference").withKey(key);

        return super.removeReference(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("valueOf").withKey(key);

        return super.<V>valueOf(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("allValuesOf").withKey(key);

        AtomicLong resultCount = new AtomicLong();

        return super.<V>allValuesOf(key)
                .doOnNext(Functions.actionConsumer(resultCount::incrementAndGet))
                .doOnComplete(() -> onSuccess(callInfo.withResult(String.format("%d values", resultCount.get()))))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        CallInfoBuilder callInfo = CallInfo.forMethod("valueFor").withKey(key).withValue(value);

        return super.valueFor(key, value)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
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
    public Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("sizeOfValue").withKey(key);

        return super.sizeOfValue(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(ManyFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("referenceOf").withKey(key);

        return super.referenceOf(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("allReferencesOf").withKey(key);

        AtomicLong resultCount = new AtomicLong();

        return super.allReferencesOf(key)
                .doOnNext(Functions.actionConsumer(resultCount::incrementAndGet))
                .doOnComplete(() -> onSuccess(callInfo.withResult(String.format("%d references", resultCount.get()))))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Id> referenceFor(ManyFeatureBean key, Id reference) {
        CallInfoBuilder callInfo = CallInfo.forMethod("referenceFor").withKey(key).withValue(reference);

        return super.referenceFor(key, reference)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
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
    public Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("sizeOfReference").withKey(key);

        return super.sizeOfReference(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
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
        CallInfoBuilder callInfo = CallInfo.forMethod(getCallingMethod()).withKey(key).withValue(value);

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

            onSuccess(callInfo.withResult(resultToLog));
            return result;
        }
        catch (RuntimeException e) {
            onFailure(callInfo.withThrownException(e));
            throw e;
        }
    }

    /**
     * Handle a succeeded call.
     *
     * @param info information about the call
     */
    protected abstract void onSuccess(CallInfo info);

    /**
     * Handle a failed call.
     *
     * @param info information about the call
     */
    protected abstract void onFailure(CallInfo info);

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
     */
    @ParametersAreNonnullByDefault
    protected static class CallInfo {

        /**
         * The name of the called method.
         */
        @Nonnull
        protected final String method;

        /**
         * The key used during the call.
         */
        @Nullable
        protected Object key;

        /**
         * The value used during the call.
         */
        @Nullable
        protected Object value;

        /**
         * The result of the call.
         */
        @Nullable
        protected Object result;

        /**
         * The exception thrown during the call.
         */
        @Nullable
        protected Throwable thrownException;

        /**
         * Constructs a new {@code CallInfo}.
         *
         * @param method the name of the related method
         */
        public CallInfo(String method) {
            this.method = checkNotNull(method);
        }

        /**
         * Creates a new builder of {@code CallInfo} for the given {@code method}.
         *
         * @param method the name of the related method
         *
         * @return a new builder
         */
        @Nonnull
        public static CallInfoBuilder forMethod(String method) {
            return new CallInfoBuilder(method);
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
        public Object key() {
            return key;
        }

        /**
         * Returns the value used during the call.
         *
         * @return the value used during the call
         */
        @Nullable
        public Object value() {
            return value;
        }

        /**
         * Returns the result of the call.
         *
         * @return the result of the call
         */
        @Nullable
        public Object result() {
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

    /**
     * A builder of {@code CallInfo} instances.
     */
    @ParametersAreNonnullByDefault
    protected static class CallInfoBuilder extends CallInfo {

        /**
         * Constructs a new {@code CallInfo.CallInfoBuilder}.
         *
         * @param method the name of the related method
         */
        private CallInfoBuilder(String method) {
            super(method);
        }

        /**
         * Defines the key used during the call.
         *
         * @param key the key used during the call
         *
         * @return this builder (for chaining)
         */
        @Nonnull
        public CallInfoBuilder withKey(@Nullable Object key) {
            this.key = key;
            return this;
        }

        /**
         * Defines the value used during the call.
         *
         * @param value the value used during the call
         *
         * @return this builder (for chaining)
         */
        @Nonnull
        public CallInfoBuilder withValue(@Nullable Object value) {
            this.value = value;
            return this;
        }

        /**
         * Defines the result of the call.
         *
         * @param result the result of the call
         *
         * @return this builder (for chaining)
         */
        @Nonnull
        public CallInfoBuilder withResult(@Nullable Object result) {
            this.result = result;
            return this;
        }

        /**
         * Defines the exception thrown during the call.
         *
         * @param e the exception thrown during the call
         *
         * @return this builder (for chaining)
         */
        @Nonnull
        public CallInfoBuilder withThrownException(@Nullable Throwable e) {
            this.thrownException = e;
            return this;
        }
    }
}
