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
import java.util.Set;

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

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable save() {
        return next.save();
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
    @OverridingMethodsMustInvokeSuper
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
    @OverridingMethodsMustInvokeSuper
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
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
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
    public Completable referenceFor(SingleFeatureBean key, Id reference) {
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
    public <V> Completable valueFor(ManyFeatureBean key, V value) {
        return next.valueFor(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Completable addValue(ManyFeatureBean key, V value) {
        return next.addValue(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        return next.addAllValues(key, values);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Single<Integer> appendValue(SingleFeatureBean key, V value) {
        return next.appendValue(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Single<Integer> appendAllValues(SingleFeatureBean key, List<? extends V> values) {
        return next.appendAllValues(key, values);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Maybe<V> removeValue(ManyFeatureBean key) {
        return next.removeValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable removeAllValues(SingleFeatureBean key) {
        return next.removeAllValues(key);
    }

    @Nonnull
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
    public Completable referenceFor(ManyFeatureBean key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable addReference(ManyFeatureBean key, Id reference) {
        return next.addReference(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        return next.addAllReferences(key, references);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Single<Integer> appendReference(SingleFeatureBean key, Id reference) {
        return next.appendReference(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Single<Integer> appendAllReferences(SingleFeatureBean key, List<Id> references) {
        return next.appendAllReferences(key, references);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Id> removeReference(ManyFeatureBean key) {
        return next.removeReference(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Completable removeAllReferences(SingleFeatureBean key) {
        return next.removeAllReferences(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        return next.sizeOfReference(key);
    }
}
