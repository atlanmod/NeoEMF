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

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        CallInfoBuilder callInfo = CallInfo.forMethod("valueFor").withKey(key).withValue(value);

        return super.valueFor(key, value)
                .doOnComplete(() -> onSuccess(callInfo))
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
    public Completable referenceFor(SingleFeatureBean key, Id reference) {
        CallInfoBuilder callInfo = CallInfo.forMethod("referenceFor").withKey(key).withValue(reference);

        return super.referenceFor(key, reference)
                .doOnComplete(() -> onSuccess(callInfo))
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
    public <V> Completable valueFor(ManyFeatureBean key, V value) {
        CallInfoBuilder callInfo = CallInfo.forMethod("valueFor").withKey(key).withValue(value);

        return super.valueFor(key, value)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addValue(ManyFeatureBean key, V value) {
        CallInfoBuilder callInfo = CallInfo.forMethod("addValue").withKey(key).withValue(value);

        return super.addValue(key, value)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        CallInfoBuilder callInfo = CallInfo.forMethod("addAllValues").withKey(key).withValue(values);

        return super.addAllValues(key, values)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendValue(SingleFeatureBean key, V value) {
        CallInfoBuilder callInfo = CallInfo.forMethod("appendValue").withKey(key).withValue(value);

        return super.appendValue(key, value)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendAllValues(SingleFeatureBean key, List<? extends V> values) {
        CallInfoBuilder callInfo = CallInfo.forMethod("appendAllValues").withKey(key).withValue(values);

        return super.appendAllValues(key, values)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Boolean> removeValue(ManyFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeValue").withKey(key);

        return super.removeValue(key)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllValues(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeAllValues").withKey(key);

        return super.removeAllValues(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
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
    public Completable referenceFor(ManyFeatureBean key, Id reference) {
        CallInfoBuilder callInfo = CallInfo.forMethod("referenceFor").withKey(key).withValue(reference);

        return super.referenceFor(key, reference)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable addReference(ManyFeatureBean key, Id reference) {
        CallInfoBuilder callInfo = CallInfo.forMethod("addReference").withKey(key).withValue(reference);

        return super.addReference(key, reference)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        CallInfoBuilder callInfo = CallInfo.forMethod("addAllReferences").withKey(key).withValue(references);

        return super.addAllReferences(key, references)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendReference(SingleFeatureBean key, Id reference) {
        CallInfoBuilder callInfo = CallInfo.forMethod("appendReference").withKey(key).withValue(reference);

        return super.appendReference(key, reference)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendAllReferences(SingleFeatureBean key, List<Id> references) {
        CallInfoBuilder callInfo = CallInfo.forMethod("appendAllReferences").withKey(key).withValue(references);

        return super.appendAllReferences(key, references)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Boolean> removeReference(ManyFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeReference").withKey(key);

        return super.removeReference(key)
                .doOnSuccess(r -> onSuccess(callInfo.withResult(r)))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllReferences(SingleFeatureBean key) {
        CallInfoBuilder callInfo = CallInfo.forMethod("removeAllReferences").withKey(key);

        return super.removeAllReferences(key)
                .doOnComplete(() -> onSuccess(callInfo))
                .doOnError(e -> onFailure(callInfo.withThrownException(e)))
                .cache();
    }

    @Nonnull
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
