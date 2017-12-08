/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * A {@link Store} wrapper that caches {@link EStructuralFeature}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class FeatureCacheStore extends AbstractCacheStore<FeatureBean, Object> {

    /**
     * Constructs a new {@code FeatureCacheStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public FeatureCacheStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        Callable<V> getFunc = () -> (V) cache.get(key);

        Consumer<V> replaceFunc = v -> cache.put(key, v);

        Maybe<V> ifEmptyFunc = super.<V>valueOf(key)
                .doOnSuccess(replaceFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        Action setFunc = () -> cache.put(key, value);

        return super.valueFor(key, value)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        Action removeFunc = () -> cache.invalidate(key);

        return super.removeValue(key)
                .doOnComplete(removeFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(SingleFeatureBean key) {
        Callable<Id> getFunc = () -> (Id) cache.get(key);

        Consumer<Id> replaceFunc = v -> cache.put(key, v);

        Maybe<Id> ifEmptyFunc = super.<Id>referenceOf(key)
                .doOnSuccess(replaceFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable referenceFor(SingleFeatureBean key, Id reference) {
        Action setFunc = () -> cache.put(key, reference);

        return super.referenceFor(key, reference)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        Action removeFunc = () -> cache.invalidate(key);

        return super.removeReference(key)
                .doOnComplete(removeFunc)
                .cache();
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        Callable<V> getFunc = () -> (V) cache.get(key);

        Consumer<V> replaceFunc = v -> cache.put(key, v);

        Maybe<V> ifEmptyFunc = super.<V>valueOf(key)
                .doOnSuccess(replaceFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        AtomicInteger index = new AtomicInteger();

        return super.<V>allValuesOf(key)
                .doOnNext(v -> cache.put(key.withPosition(index.getAndIncrement()), v))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(ManyFeatureBean key, V value) {
        Action setFunc = () -> cache.put(key, value);

        return super.valueFor(key, value)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addValue(ManyFeatureBean key, V value) {
        final int defaultSize = key.position() + 1;

        final int size = sizeOfValue(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        Action setFunc = () -> cache.put(key, value);

        return super.addValue(key, value)
                .doOnComplete(removeFunc)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        final int defaultSize = key.position() + values.size();

        final int size = sizeOfValue(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        Action setFunc = () -> IntStream.range(0, values.size())
                .forEach(i -> cache.put(key.withPosition(key.position() + i), values.get(i)));

        return super.addAllValues(key, values)
                .doOnComplete(removeFunc)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendValue(SingleFeatureBean key, V value) {
        Consumer<Integer> setFunc = position -> cache.put(key.withPosition(position), value);

        return super.appendValue(key, value)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendAllValues(SingleFeatureBean key, List<? extends V> values) {
        Consumer<Integer> setFunc = firstPosition -> IntStream.range(0, values.size())
                .forEach(i -> cache.put(key.withPosition(firstPosition + i), values.get(i)));

        return super.appendAllValues(key, values)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Boolean> removeValue(ManyFeatureBean key) {
        final int defaultSize = key.position();

        final int size = sizeOfValue(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        return super.removeValue(key)
                .doOnSuccess(removed -> {
                    if (removed) {
                        removeFunc.run();
                    }
                })
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllValues(SingleFeatureBean key) {
        final int defaultSize = 0;

        final int size = sizeOfValue(key)
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        return super.removeAllValues(key)
                .doOnComplete(removeFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(ManyFeatureBean key) {
        Callable<Id> getFunc = () -> (Id) cache.get(key);

        Consumer<Id> replaceFunc = v -> cache.put(key, v);

        Maybe<Id> ifEmptyFunc = super.<Id>referenceOf(key)
                .doOnSuccess(replaceFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        AtomicInteger index = new AtomicInteger();

        Consumer<Id> setFunc = r -> cache.put(key.withPosition(index.getAndIncrement()), r);

        return super.allReferencesOf(key)
                .doOnNext(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable referenceFor(ManyFeatureBean key, Id reference) {
        Action setFunc = () -> cache.put(key, reference);

        return super.referenceFor(key, reference)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable addReference(ManyFeatureBean key, Id reference) {
        final int defaultSize = key.position() + 1;

        final int size = sizeOfReference(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        Action setFunc = () -> cache.put(key, reference);

        return super.addReference(key, reference)
                .doOnComplete(removeFunc)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        final int defaultSize = key.position() + references.size();

        final int size = sizeOfReference(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        Action setFunc = () -> IntStream.range(0, references.size())
                .forEach(i -> cache.put(key.withPosition(key.position() + i), references.get(i)));

        return super.addAllReferences(key, references)
                .doOnComplete(removeFunc)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendReference(SingleFeatureBean key, Id reference) {
        Consumer<Integer> setFunc = position -> cache.put(key.withPosition(position), reference);

        return super.appendReference(key, reference)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendAllReferences(SingleFeatureBean key, List<Id> references) {
        Consumer<Integer> setFunc = firstPosition -> IntStream.range(0, references.size())
                .forEach(i -> cache.put(key.withPosition(firstPosition + i), references.get(i)));

        return super.appendAllReferences(key, references)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Boolean> removeReference(ManyFeatureBean key) {
        final int defaultSize = key.position();

        final int size = sizeOfReference(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(key.position(), size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        return super.removeReference(key)
                .doOnSuccess(removed -> {
                    if (removed) {
                        removeFunc.run();
                    }
                })
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllReferences(SingleFeatureBean key) {
        final int defaultSize = 0;

        final int size = sizeOfReference(key)
                .toSingle(defaultSize)
                .blockingGet();

        Action removeFunc = () -> IntStream.range(defaultSize, size)
                .mapToObj(key::withPosition)
                .forEach(cache::invalidate);

        return super.removeAllReferences(key)
                .doOnComplete(removeFunc)
                .cache();
    }
}
