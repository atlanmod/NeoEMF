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
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An invalid {@link DataMapper} that throws a {@link RuntimeException} at each call.
 */
@ParametersAreNonnullByDefault
public class InvalidDataMapper implements DataMapper {

    /**
     * The exception thrown when calling methods.
     */
    @Nonnull
    protected final Supplier<RuntimeException> e;

    /**
     * Constructs a new {@code InvalidDataMapper} with the exception thrown when calling unsupported methods.
     *
     * @param e the exception thrown when calling unsupported methods
     */
    public InvalidDataMapper(Supplier<RuntimeException> e) {
        this.e = checkNotNull(e, "e");
    }

    @Override
    public void copyTo(DataMapper target) {
        throw e.get();
    }

    @Override
    public void close() {
        // Do nothing (see PersistentResource#unload())
    }

    @Override
    public void save() {
        // Do nothing (see PersistentResource#save())
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        return Completable.error(e::get);
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        return Completable.error(e::get);
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        return Completable.error(e::get);
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        return Flowable.error(e::get);
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return Flowable.error(e::get);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        return Completable.error(e::get);
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(SingleFeatureBean key) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        return Completable.error(e::get);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        return Flowable.error(e::get);
    }

    @Nonnull
    @Override
    public <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        return Single.error(e::get);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        throw e.get();
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        throw e.get();
    }

    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        throw e.get();
    }

    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        throw e.get();
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        throw e.get();
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(ManyFeatureBean key) {
        return Maybe.error(e::get);
    }

    @Nonnull
    @Override
    public Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        return Flowable.error(e::get);
    }

    @Nonnull
    @Override
    public Single<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return Single.error(e::get);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        throw e.get();
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        throw e.get();
    }

    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        throw e.get();
    }

    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        throw e.get();
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        throw e.get();
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        throw e.get();
    }
}
