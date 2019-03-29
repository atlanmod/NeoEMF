/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

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
public class SizeCachingStore extends AbstractCachingStore<SingleFeatureBean, Optional<Integer>> {

    @Override
    public void removeValue(SingleFeatureBean feature) {
        cacheSize(feature, 0);
        super.removeValue(feature);
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        cacheSize(feature, 0);
        super.removeReference(feature);
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        cacheSize(feature.withoutPosition(), sizeOfValue(feature.withoutPosition()).orElse(0) + 1);
        super.addValue(feature, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        cacheSize(feature.withoutPosition(), sizeOfValue(feature.withoutPosition()).orElse(0) + collection.size());
        super.addAllValues(feature, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        int position = super.appendValue(feature, value);
        cacheSize(feature, position + 1);
        return position;
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> collection) {
        int firstPosition = super.appendAllValues(feature, collection);
        cacheSize(feature, firstPosition + collection.size());
        return firstPosition;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        sizeOfValue(feature.withoutPosition()).ifPresent(s -> cacheSize(feature.withoutPosition(), s - 1));
        return super.removeValue(feature);
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        cacheSize(feature, 0);
        super.removeAllValues(feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        return cache.get(feature, super::sizeOfValue);
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        cacheSize(feature.withoutPosition(), sizeOfReference(feature.withoutPosition()).orElse(0) + 1);
        super.addReference(feature, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        cacheSize(feature.withoutPosition(), sizeOfReference(feature.withoutPosition()).orElse(0) + collection.size());
        super.addAllReferences(feature, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean feature, Id reference) {
        int position = super.appendReference(feature, reference);
        cacheSize(feature, position + 1);
        return position;
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean feature, List<Id> collection) {
        int firstPosition = super.appendAllReferences(feature, collection);
        cacheSize(feature, firstPosition + collection.size());
        return firstPosition;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        sizeOfReference(feature.withoutPosition()).ifPresent(s -> cacheSize(feature.withoutPosition(), s - 1));
        return super.removeReference(feature);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        cacheSize(feature, 0);
        super.removeAllReferences(feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        return cache.get(feature, super::sizeOfReference);
    }

    /**
     * Defines the number of elements of the given {@code feature}.
     *
     * @param feature the feature to define the size
     * @param size    the size
     */
    private void cacheSize(SingleFeatureBean feature, @Nonnegative int size) {
        cache.put(feature, size != 0 ? Optional.of(size) : Optional.empty());
    }
}
