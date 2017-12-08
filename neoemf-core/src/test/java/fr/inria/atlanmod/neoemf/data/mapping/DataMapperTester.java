/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

/**
 * A tester for {@link DataMapper} instances that redirects all calls according to a defined {@link RedirectionType}.
 * <p>
 * <b>NOTE:</b> All calls wait for a result (value, complete, error) before returning any {@link TestObserver} or {@link TestSubscriber}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unchecked")
class DataMapperTester {

    /**
     * The delegated mapper where to execute queries.
     */
    @Nonnull
    private final DataMapper delegate;

    /**
     * The type of redirection.
     */
    @Nonnull
    private final RedirectionType type;

    /**
     * Constructs a new {@code DataMapperTester} on the {@code delegate} mapper, with the given redirection {@code type}.
     *
     * @param delegate the delegated mapper where to execute queries
     * @param type     the type of redirection
     */
    public DataMapperTester(DataMapper delegate, RedirectionType type) {
        this.delegate = delegate;
        this.type = type;
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(SingleFeatureBean)} ou {@link DataMapper#referenceOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<V> get(SingleFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.<V>valueOf(key).test().await();
        }
        else {
            return (TestObserver<V>) delegate.referenceOf(key).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(SingleFeatureBean, Object)} ou {@link DataMapper#referenceFor(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<Void> set(SingleFeatureBean key, V value) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(key, value).test().await();
        }
        else {
            return delegate.referenceFor(key, (Id) value).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(SingleFeatureBean)} ou {@link DataMapper#removeReference(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public TestObserver<Void> remove(SingleFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeValue(key).test().await();
        }
        else {
            return delegate.removeReference(key).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(ManyFeatureBean)} ou {@link DataMapper#referenceOf(ManyFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<V> get(ManyFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.<V>valueOf(key).test().await();
        }
        else {
            return (TestObserver<V>) delegate.referenceOf(key).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#allValuesOf(SingleFeatureBean)} ou {@link DataMapper#allReferencesOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test subscriber on the result
     */
    @Nonnull
    public <V> TestSubscriber<V> getAll(SingleFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.<V>allValuesOf(key).test().await();
        }
        else {
            return (TestSubscriber<V>) delegate.allReferencesOf(key).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(ManyFeatureBean, Object)} ou {@link DataMapper#referenceFor(ManyFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<Void> set(ManyFeatureBean key, V value) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(key, value).test().await();
        }
        else {
            return delegate.referenceFor(key, (Id) value).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addValue(ManyFeatureBean, Object)} ou {@link DataMapper#addReference(ManyFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<Void> add(ManyFeatureBean key, V value) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.addValue(key, value).test().await();
        }
        else {
            return delegate.addReference(key, (Id) value).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addAllValues(ManyFeatureBean, List)} ou {@link DataMapper#addAllReferences(ManyFeatureBean, List)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <V> TestObserver<Void> addAll(ManyFeatureBean key, List<? extends V> values) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.addAllValues(key, values).test().await();
        }
        else {
            return delegate.addAllReferences(key, (List<Id>) values).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendValue(SingleFeatureBean, Object)} ou {@link DataMapper#appendReference(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<Integer> append(SingleFeatureBean key, V value) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.appendValue(key, value).test().await();
        }
        else {
            return delegate.appendReference(key, (Id) value).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendAllValues(SingleFeatureBean, List)} ou {@link DataMapper#appendAllReferences(SingleFeatureBean, List)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <V> TestObserver<Integer> appendAll(SingleFeatureBean key, List<? extends V> values) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.appendAllValues(key, values).test().await();
        }
        else {
            return delegate.appendAllReferences(key, (List<Id>) values).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(ManyFeatureBean)} ou {@link DataMapper#removeReference(ManyFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> TestObserver<V> remove(ManyFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.<V>removeValue(key).test().await();
        }
        else {
            return (TestObserver<V>) delegate.removeReference(key).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeAllValues(SingleFeatureBean)} ou {@link DataMapper#removeAllReferences(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public TestObserver<Void> removeAll(SingleFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeAllValues(key).test().await();
        }
        else {
            return delegate.removeAllReferences(key).test().await();
        }
    }

    /**
     * Redirects the call to {@link DataMapper#sizeOfValue(SingleFeatureBean)} ou {@link DataMapper#sizeOfReference(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public TestObserver<Integer> sizeOf(SingleFeatureBean key) throws InterruptedException {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.sizeOfValue(key).test().await();
        }
        else {
            return delegate.sizeOfReference(key).test().await();
        }
    }
}
