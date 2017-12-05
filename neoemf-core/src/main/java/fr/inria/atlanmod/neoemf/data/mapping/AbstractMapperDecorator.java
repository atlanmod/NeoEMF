/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link DataMapper} wrapper that delegates method calls to an internal {@link DataMapper}.
 *
 * @param <M> the type of the inner {@link DataMapper}
 */
@ParametersAreNonnullByDefault
public class AbstractMapperDecorator<M extends DataMapper> extends AbstractDataMapper {

    /**
     * The inner mapper.
     */
    private final M next;

    /**
     * Constructs a new {@code AbstractMapperDecorator} on the given {@code mapper}.
     *
     * @param mapper the inner mapper
     */
    protected AbstractMapperDecorator(M mapper) {
        this.next = checkNotNull(mapper, "mapper");
    }

    /**
     * Returns the inner mapper.
     *
     * @return the inner mapper
     */
    @Nonnull
    protected final M next() {
        return next;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void close() {
        next.close();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void save() {
        next.save();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void copyTo(DataMapper target) {
        next.copyTo(target);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        return next.containerOf(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable containerFor(Id id, SingleFeatureBean container) {
        return next.containerFor(id, container);
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        return next.removeContainer(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<ClassBean> metaClassOf(Id id) {
        return next.metaClassOf(id);
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        return next.metaClassFor(id, metaClass);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Flowable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        return next.allInstancesOf(metaClass, strict);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return next.allInstancesOf(metaClasses);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        return next.valueFor(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable removeValue(SingleFeatureBean key) {
        return next.removeValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Id> referenceOf(SingleFeatureBean key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable removeReference(SingleFeatureBean key) {
        return next.removeReference(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        return next.allValuesOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        return next.valueFor(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void addValue(ManyFeatureBean key, V value) {
        next.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        next.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendValue(SingleFeatureBean key, V value) {
        return next.appendValue(key, value);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        return next.appendAllValues(key, collection);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return next.removeValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllValues(SingleFeatureBean key) {
        next.removeAllValues(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        return next.sizeOfValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Id> referenceOf(ManyFeatureBean key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        return next.allReferencesOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Single<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void addReference(ManyFeatureBean key, Id reference) {
        next.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        next.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendReference(SingleFeatureBean key, Id reference) {
        return next.appendReference(key, reference);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return next.appendAllReferences(key, collection);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> removeReference(ManyFeatureBean key) {
        return next.removeReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllReferences(SingleFeatureBean key) {
        next.removeAllReferences(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        return next.sizeOfReference(key);
    }
}
