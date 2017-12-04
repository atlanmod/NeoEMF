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
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

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
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        Action setFunc = () -> cache.put(key, value);

        Consumer<V> replaceFunc = Functions.actionConsumer(setFunc);

        return super.valueFor(key, value)
                .doOnComplete(setFunc)
                .doOnSuccess(replaceFunc)
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
    public Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        Action setFunc = () -> cache.put(key, reference);

        Consumer<Id> replaceFunc = Functions.actionConsumer(setFunc);

        return super.referenceFor(key, reference)
                .doOnComplete(setFunc)
                .doOnSuccess(replaceFunc)
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
    public <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        Action setFunc = () -> cache.put(key, value);

        Consumer<V> replaceFunc = Functions.actionConsumer(setFunc);

        return super.valueFor(key, value)
                .doOnSuccess(replaceFunc)
                .cache();
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        cache.put(key, value);

        IntStream.range(key.position() + 1, sizeOfValue(key.withoutPosition()).orElseGet(() -> key.position() + 1))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        int firstPosition = key.position();

        IntStream.range(0, collection.size())
                .forEach(i -> cache.put(key.withPosition(firstPosition + i), collection.get(i)));

        IntStream.range(firstPosition + collection.size(), sizeOfValue(key.withoutPosition()).orElseGet(() -> firstPosition + collection.size()))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        int position = super.appendValue(key, value);

        cache.put(key.withPosition(position), value);

        return position;
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        int firstPosition = super.appendAllValues(key, collection);

        IntStream.range(0, collection.size())
                .forEach(i -> cache.put(key.withPosition(firstPosition + i), collection.get(i)));

        return firstPosition;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        IntStream.range(key.position(), sizeOfValue(key.withoutPosition()).orElseGet(key::position))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        return super.removeValue(key);
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
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
    public Single<Id> referenceFor(ManyFeatureBean key, Id reference) {
        Action setFunc = () -> cache.put(key, reference);

        Consumer<Id> replaceFunc = Functions.actionConsumer(setFunc);

        return super.referenceFor(key, reference)
                .doOnSuccess(replaceFunc)
                .cache();
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        cache.put(key, reference);

        IntStream.range(key.position() + 1, sizeOfReference(key.withoutPosition()).orElseGet(() -> key.position() + 1))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        int firstPosition = key.position();

        IntStream.range(0, collection.size())
                .forEach(i -> cache.put(key.withPosition(firstPosition + i), collection.get(i)));

        IntStream.range(firstPosition + collection.size(), sizeOfReference(key.withoutPosition()).orElseGet(() -> firstPosition + collection.size()))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        int position = super.appendReference(key, reference);

        cache.put(key.withPosition(position), reference);

        return position;
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        int firstPosition = super.appendAllReferences(key, collection);

        IntStream.range(0, collection.size())
                .forEach(i -> cache.put(key.withPosition(firstPosition + i), collection.get(i)));

        return firstPosition;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        IntStream.range(key.position(), sizeOfReference(key.withoutPosition()).orElseGet(key::position))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        IntStream.range(0, sizeOfReference(key).orElse(0))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.removeAllReferences(key);
    }
}
