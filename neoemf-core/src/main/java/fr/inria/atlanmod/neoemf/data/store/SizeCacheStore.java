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
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * A {@link Store} wrapper that caches the size data.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class SizeCacheStore extends AbstractCacheStore<SingleFeatureBean, Integer> {

    /**
     * Constructs a new {@code SizeCacheStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public SizeCacheStore(Store store) {
        super(store);
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
    public Completable removeReference(SingleFeatureBean key) {
        Action removeFunc = () -> cache.invalidate(key);

        return super.removeReference(key)
                .doOnComplete(removeFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addValue(ManyFeatureBean key, V value) {
        final int defaultSize = 0;

        final int size = sizeOfValue(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action setFunc = () -> cache.put(key.withoutPosition(), size + 1);

        return super.addValue(key, value)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        final int defaultSize = 0;

        final int size = sizeOfValue(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action setFunc = () -> cache.put(key.withoutPosition(), size + values.size());

        return super.addAllValues(key, values)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendValue(SingleFeatureBean key, V value) {
        Consumer<Integer> setFunc = position -> cache.put(key, position + 1);

        return super.appendValue(key, value)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendAllValues(SingleFeatureBean key, List<? extends V> values) {
        Consumer<Integer> setFunc = firstPosition -> cache.put(key, firstPosition + values.size());

        return super.appendAllValues(key, values)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Boolean> removeValue(ManyFeatureBean key) {
        final Optional<Integer> size = sizeOfValue(key.withoutPosition())
                .filter(s -> key.position() < s)
                .to(CommonQueries::toOptional);

        Action setFunc = () -> size.ifPresent(s -> cache.put(key.withoutPosition(), s - 1));

        return super.removeValue(key)
                .doOnSuccess(removed -> {
                    if (removed) {
                        setFunc.run();
                    }
                })
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllValues(SingleFeatureBean key) {
        Action removeFunc = () -> cache.invalidate(key);

        return super.removeAllValues(key)
                .doOnComplete(removeFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        Callable<Integer> getFunc = () -> cache.get(key);

        Consumer<Integer> setFunc = size -> cache.put(key, size);

        Maybe<Integer> ifEmptyFunc = super.sizeOfValue(key)
                .doOnSuccess(setFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .filter(s -> s > 0)
                .cache();
    }

    @Nonnull
    @Override
    public Completable addReference(ManyFeatureBean key, Id reference) {
        final int defaultSize = 0;

        final int size = sizeOfReference(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action setFunc = () -> cache.put(key.withoutPosition(), size + 1);

        return super.addReference(key, reference)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        final int defaultSize = 0;

        final int size = sizeOfReference(key.withoutPosition())
                .toSingle(defaultSize)
                .blockingGet();

        Action setFunc = () -> cache.put(key.withoutPosition(), size + references.size());

        return super.addAllReferences(key, references)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendReference(SingleFeatureBean key, Id reference) {
        Consumer<Integer> setFunc = position -> cache.put(key, position + 1);

        return super.appendReference(key, reference)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendAllReferences(SingleFeatureBean key, List<Id> references) {
        Consumer<Integer> setFunc = firstPosition -> cache.put(key, firstPosition + references.size());

        return super.appendAllReferences(key, references)
                .doOnSuccess(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Single<Boolean> removeReference(ManyFeatureBean key) {
        final Optional<Integer> size = sizeOfReference(key.withoutPosition())
                .filter(s -> key.position() < s)
                .to(CommonQueries::toOptional);

        Action setFunc = () -> size.ifPresent(s -> cache.put(key.withoutPosition(), s - 1));

        return super.removeReference(key)
                .doOnSuccess(removed -> {
                    if (removed) {
                        setFunc.run();
                    }
                })
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllReferences(SingleFeatureBean key) {
        Action removeFunc = () -> cache.invalidate(key);

        return super.removeAllReferences(key)
                .doOnComplete(removeFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        Callable<Integer> getFunc = () -> cache.get(key);

        Consumer<Integer> setFunc = size -> cache.put(key, size);

        Maybe<Integer> ifEmptyFunc = super.sizeOfReference(key)
                .doOnSuccess(setFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .filter(s -> s > 0)
                .cache();
    }
}
