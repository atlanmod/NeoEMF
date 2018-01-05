/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches the size data.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class SizeCacheStore extends AbstractCacheStore<SingleFeatureBean, Optional<Integer>> {

    /**
     * Constructs a new {@code SizeCacheStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public SizeCacheStore(Store store) {
        super(store);
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        cacheSize(key, 0);
        super.removeValue(key);
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        cacheSize(key, 0);
        super.removeReference(key);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        cacheSize(key.withoutPosition(), sizeOfValue(key.withoutPosition()).orElse(0) + 1);
        super.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        cacheSize(key.withoutPosition(), sizeOfValue(key.withoutPosition()).orElse(0) + collection.size());
        super.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        int position = super.appendValue(key, value);
        cacheSize(key, position + 1);
        return position;
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        int firstPosition = super.appendAllValues(key, collection);
        cacheSize(key, firstPosition + collection.size());
        return firstPosition;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        sizeOfValue(key.withoutPosition()).ifPresent(s -> cacheSize(key.withoutPosition(), s - 1));
        return super.removeValue(key);
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        cacheSize(key, 0);
        super.removeAllValues(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return cache.get(key, super::sizeOfValue);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        cacheSize(key.withoutPosition(), sizeOfReference(key.withoutPosition()).orElse(0) + 1);
        super.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        cacheSize(key.withoutPosition(), sizeOfReference(key.withoutPosition()).orElse(0) + collection.size());
        super.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        int position = super.appendReference(key, reference);
        cacheSize(key, position + 1);
        return position;
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        int firstPosition = super.appendAllReferences(key, collection);
        cacheSize(key, firstPosition + collection.size());
        return firstPosition;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        sizeOfReference(key.withoutPosition()).ifPresent(s -> cacheSize(key.withoutPosition(), s - 1));
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        cacheSize(key, 0);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return cache.get(key, super::sizeOfReference);
    }

    /**
     * Defines the number of elements of the given {@code key}.
     *
     * @param key  the key to define the size
     * @param size the size
     */
    private void cacheSize(SingleFeatureBean key, @Nonnegative int size) {
        cache.put(key, size != 0 ? Optional.of(size) : Optional.empty());
    }
}
