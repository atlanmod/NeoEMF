/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.annotation.VisibleForTesting;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * A {@link Backend} that does nothing.
 */
@VisibleForTesting
@ParametersAreNonnullByDefault
public final class NoopBackend implements Backend, AllReferenceAs<Long> {

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void copyTo(DataMapper mapper) {
        // Do nothing
    }

    @Override
    public void close() {
        // Do nothing
    }

    @Override
    public void save() {
        // Do nothing
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        return Maybe.empty();
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        return Completable.complete();
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        return Completable.complete();
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        return Maybe.empty();
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        return Completable.complete();
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        return Flowable.empty();
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return Flowable.empty();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        return Maybe.empty();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        return Maybe.empty();
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        return Completable.complete();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        return Maybe.empty();
    }

    @Nonnull
    @Override
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        return Flowable.empty();
    }

    @Nonnull
    @Override
    public <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        return Single.just(value);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        // Do nothing
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        // Do nothing
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return Optional.empty();
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        // Do nothing
    }

    @Nonnull
    @Override
    public Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        return Maybe.empty();
    }

    @Nonnull
    @Override
    public Converter<Id, Long> referenceConverter() {
        return IdConverters.withLong();
    }
}
