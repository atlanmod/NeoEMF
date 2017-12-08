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

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * A tester for {@link DataMapper} instances that redirects all calls according to a defined {@link RedirectionType}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unchecked")
class DataMapperRedirector {

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
     * Constructs a new {@code DataMapperRedirector} on the {@code delegate} mapper, with the given redirection {@code type}.
     *
     * @param delegate the delegated mapper where to execute queries
     * @param type     the type of redirection
     */
    public DataMapperRedirector(DataMapper delegate, RedirectionType type) {
        this.delegate = delegate;
        this.type = type;
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(SingleFeatureBean)} ou {@link DataMapper#referenceOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Maybe<V> get(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueOf(key);
        }
        else {
            return (Maybe<V>) delegate.referenceOf(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(SingleFeatureBean, Object)} ou {@link DataMapper#referenceFor(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Completable set(SingleFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(key, value);
        }
        else {
            return delegate.referenceFor(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(SingleFeatureBean)} ou {@link DataMapper#removeReference(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public Completable remove(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeValue(key);
        }
        else {
            return delegate.removeReference(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(ManyFeatureBean)} ou {@link DataMapper#referenceOf(ManyFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Maybe<V> get(ManyFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueOf(key);
        }
        else {
            return (Maybe<V>) delegate.referenceOf(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#allValuesOf(SingleFeatureBean)} ou {@link DataMapper#allReferencesOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test subscriber on the result
     */
    @Nonnull
    public <V> Flowable<V> getAll(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.allValuesOf(key);
        }
        else {
            return (Flowable<V>) delegate.allReferencesOf(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(ManyFeatureBean, Object)} ou {@link DataMapper#referenceFor(ManyFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Completable set(ManyFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(key, value);
        }
        else {
            return delegate.referenceFor(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addValue(ManyFeatureBean, Object)} ou {@link DataMapper#addReference(ManyFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Completable add(ManyFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.addValue(key, value);
        }
        else {
            return delegate.addReference(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addAllValues(ManyFeatureBean, List)} ou {@link DataMapper#addAllReferences(ManyFeatureBean, List)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <V> Completable addAll(ManyFeatureBean key, List<? extends V> values) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.addAllValues(key, values);
        }
        else {
            return delegate.addAllReferences(key, (List<Id>) values);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendValue(SingleFeatureBean, Object)} ou {@link DataMapper#appendReference(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Single<Integer> append(SingleFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.appendValue(key, value);
        }
        else {
            return delegate.appendReference(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendAllValues(SingleFeatureBean, List)} ou {@link DataMapper#appendAllReferences(SingleFeatureBean, List)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <V> Single<Integer> appendAll(SingleFeatureBean key, List<? extends V> values) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.appendAllValues(key, values);
        }
        else {
            return delegate.appendAllReferences(key, (List<Id>) values);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(ManyFeatureBean)} ou {@link DataMapper#removeReference(ManyFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public <V> Maybe<V> remove(ManyFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeValue(key);
        }
        else {
            return (Maybe<V>) delegate.removeReference(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeAllValues(SingleFeatureBean)} ou {@link DataMapper#removeAllReferences(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public Completable removeAll(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeAllValues(key);
        }
        else {
            return delegate.removeAllReferences(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#sizeOfValue(SingleFeatureBean)} ou {@link DataMapper#sizeOfReference(SingleFeatureBean)} according to the redirection type.
     *
     * @return a test observer on the result
     */
    @Nonnull
    public Maybe<Integer> sizeOf(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.sizeOfValue(key);
        }
        else {
            return delegate.sizeOfReference(key);
        }
    }
}
