/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

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
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> get(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueOf(key);
        }
        else {
            return (Optional<V>) delegate.referenceOf(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(SingleFeatureBean, Object)} ou {@link DataMapper#referenceFor(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return the result
     */
    public <V> Optional<V> set(SingleFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(key, value);
        }
        else {
            return (Optional<V>) delegate.referenceFor(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(SingleFeatureBean)} ou {@link DataMapper#removeReference(SingleFeatureBean)} according to the redirection type.
     */
    public void remove(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.removeValue(key);
        }
        else {
            delegate.removeReference(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(ManyFeatureBean)} ou {@link DataMapper#referenceOf(ManyFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> get(ManyFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueOf(key);
        }
        else {
            return (Optional<V>) delegate.referenceOf(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#allValuesOf(SingleFeatureBean)} ou {@link DataMapper#allReferencesOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Stream<V> getAll(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.allValuesOf(key);
        }
        else {
            return (Stream<V>) delegate.allReferencesOf(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(ManyFeatureBean, Object)} ou {@link DataMapper#referenceFor(ManyFeatureBean, Id)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> set(ManyFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(key, value);
        }
        else {
            return (Optional<V>) delegate.referenceFor(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addValue(ManyFeatureBean, Object)} ou {@link DataMapper#addReference(ManyFeatureBean, Id)} according to the redirection type.
     */
    public <V> void add(ManyFeatureBean key, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.addValue(key, value);
        }
        else {
            delegate.addReference(key, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addAllValues(ManyFeatureBean, List)} ou {@link DataMapper#addAllReferences(ManyFeatureBean, List)} according to the redirection type.
     */
    public <V> void addAll(ManyFeatureBean key, List<? extends V> values) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.addAllValues(key, values);
        }
        else {
            delegate.addAllReferences(key, (List<Id>) values);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendValue(SingleFeatureBean, Object)} ou {@link DataMapper#appendReference(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnegative
    public <V> int append(SingleFeatureBean key, V value) {
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
     * @return the result
     */
    @Nonnegative
    public <V> int appendAll(SingleFeatureBean key, List<? extends V> values) {
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
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> remove(ManyFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeValue(key);
        }
        else {
            return (Optional<V>) delegate.removeReference(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeAllValues(SingleFeatureBean)} ou {@link DataMapper#removeAllReferences(SingleFeatureBean)} according to the redirection type.
     */
    public void removeAll(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.removeAllValues(key);
        }
        else {
            delegate.removeAllReferences(key);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#sizeOfValue(SingleFeatureBean)} ou {@link DataMapper#sizeOfReference(SingleFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public Optional<Integer> sizeOf(SingleFeatureBean key) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.sizeOfValue(key);
        }
        else {
            return delegate.sizeOfReference(key);
        }
    }
}