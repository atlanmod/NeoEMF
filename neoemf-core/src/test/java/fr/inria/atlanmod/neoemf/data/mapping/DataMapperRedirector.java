/*
 * Copyright (c) 2013 Atlanmod.
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
     * Constructs a new {@code DataMapperRedirector} on the {@code delegate} mapper, with the given redirection {@code
     * type}.
     *
     * @param delegate the delegated mapper where to execute queries
     * @param type     the type of redirection
     */
    public DataMapperRedirector(DataMapper delegate, RedirectionType type) {
        this.delegate = delegate;
        this.type = type;
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(SingleFeatureBean)} ou {@link
     * DataMapper#referenceOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> get(SingleFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueOf(feature);
        }
        else {
            return (Optional<V>) delegate.referenceOf(feature);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(SingleFeatureBean, Object)} ou {@link
     * DataMapper#referenceFor(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return the result
     */
    public <V> Optional<V> set(SingleFeatureBean feature, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(feature, value);
        }
        else {
            return (Optional<V>) delegate.referenceFor(feature, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(SingleFeatureBean)} ou {@link
     * DataMapper#removeReference(SingleFeatureBean)} according to the redirection type.
     */
    public void remove(SingleFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.removeValue(feature);
        }
        else {
            delegate.removeReference(feature);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueOf(ManyFeatureBean)} ou {@link DataMapper#referenceOf(ManyFeatureBean)}
     * according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> get(ManyFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueOf(feature);
        }
        else {
            return (Optional<V>) delegate.referenceOf(feature);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#allValuesOf(SingleFeatureBean)} ou {@link
     * DataMapper#allReferencesOf(SingleFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Stream<V> getAll(SingleFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.allValuesOf(feature);
        }
        else {
            return (Stream<V>) delegate.allReferencesOf(feature);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#valueFor(ManyFeatureBean, Object)} ou {@link
     * DataMapper#referenceFor(ManyFeatureBean, Id)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> set(ManyFeatureBean feature, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.valueFor(feature, value);
        }
        else {
            return (Optional<V>) delegate.referenceFor(feature, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addValue(ManyFeatureBean, Object)} ou {@link
     * DataMapper#addReference(ManyFeatureBean, Id)} according to the redirection type.
     */
    public <V> void add(ManyFeatureBean feature, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.addValue(feature, value);
        }
        else {
            delegate.addReference(feature, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#addAllValues(ManyFeatureBean, List)} ou {@link
     * DataMapper#addAllReferences(ManyFeatureBean, List)} according to the redirection type.
     */
    public <V> void addAll(ManyFeatureBean feature, List<? extends V> values) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.addAllValues(feature, values);
        }
        else {
            delegate.addAllReferences(feature, (List<Id>) values);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendValue(SingleFeatureBean, Object)} ou {@link
     * DataMapper#appendReference(SingleFeatureBean, Id)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnegative
    public <V> int append(SingleFeatureBean feature, V value) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.appendValue(feature, value);
        }
        else {
            return delegate.appendReference(feature, (Id) value);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#appendAllValues(SingleFeatureBean, List)} ou {@link
     * DataMapper#appendAllReferences(SingleFeatureBean, List)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnegative
    public <V> int appendAll(SingleFeatureBean feature, List<? extends V> values) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.appendAllValues(feature, values);
        }
        else {
            return delegate.appendAllReferences(feature, (List<Id>) values);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeValue(ManyFeatureBean)} ou {@link
     * DataMapper#removeReference(ManyFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public <V> Optional<V> remove(ManyFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.removeValue(feature);
        }
        else {
            return (Optional<V>) delegate.removeReference(feature);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#removeAllValues(SingleFeatureBean)} ou {@link
     * DataMapper#removeAllReferences(SingleFeatureBean)} according to the redirection type.
     */
    public void removeAll(SingleFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            delegate.removeAllValues(feature);
        }
        else {
            delegate.removeAllReferences(feature);
        }
    }

    /**
     * Redirects the call to {@link DataMapper#sizeOfValue(SingleFeatureBean)} ou {@link
     * DataMapper#sizeOfReference(SingleFeatureBean)} according to the redirection type.
     *
     * @return the result
     */
    @Nonnull
    public Optional<Integer> sizeOf(SingleFeatureBean feature) {
        if (type == RedirectionType.ATTRIBUTE) {
            return delegate.sizeOfValue(feature);
        }
        else {
            return delegate.sizeOfReference(feature);
        }
    }
}