/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches {@link org.eclipse.emf.ecore.EStructuralFeature} values.
 */
@ParametersAreNonnullByDefault
public class FeatureCachingStore extends AbstractCachingStore<FeatureBean, Object> {

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        return Optional.ofNullable((V) cache.get(feature, k -> super.valueOf((SingleFeatureBean) k).orElse(null)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        cache.put(feature, value);

        return super.valueFor(feature, value);
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        cache.invalidate(feature);

        super.removeValue(feature);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean feature) {
        return Optional.ofNullable((Id) cache.get(feature, k -> super.referenceOf((SingleFeatureBean) k).orElse(null)));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        cache.put(feature, reference);

        return super.referenceFor(feature, reference);
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        cache.invalidate(feature);

        super.removeReference(feature);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        return Optional.ofNullable((V) cache.get(feature, k -> super.valueOf((ManyFeatureBean) k).orElse(null)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        cache.put(feature, value);

        return super.valueFor(feature, value);
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        cache.put(feature, value);

        IntStream.range(feature.position() + 1, sizeOfValue(feature.withoutPosition()).orElseGet(() -> feature.position() + 1))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        super.addValue(feature, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        int firstPosition = feature.position();

        IntStream.range(0, collection.size())
                .forEachOrdered(i -> cache.put(feature.withPosition(firstPosition + i), collection.get(i)));

        IntStream.range(firstPosition + collection.size(), sizeOfValue(feature.withoutPosition()).orElseGet(() -> firstPosition + collection.size()))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        super.addAllValues(feature, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        int position = super.appendValue(feature, value);

        cache.put(feature.withPosition(position), value);

        return position;
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> collection) {
        int firstPosition = super.appendAllValues(feature, collection);

        IntStream.range(0, collection.size())
                .forEachOrdered(i -> cache.put(feature.withPosition(firstPosition + i), collection.get(i)));

        return firstPosition;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        IntStream.range(feature.position(), sizeOfValue(feature.withoutPosition()).orElseGet(feature::position))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        return super.removeValue(feature);
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        IntStream.range(0, sizeOfValue(feature).orElse(0))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        super.removeAllValues(feature);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean feature) {
        return Optional.ofNullable((Id) cache.get(feature, k -> super.referenceOf((ManyFeatureBean) k).orElse(null)));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        cache.put(feature, reference);

        return super.referenceFor(feature, reference);
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        cache.put(feature, reference);

        IntStream.range(feature.position() + 1, sizeOfReference(feature.withoutPosition()).orElseGet(() -> feature.position() + 1))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        super.addReference(feature, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        int firstPosition = feature.position();

        IntStream.range(0, collection.size())
                .forEachOrdered(i -> cache.put(feature.withPosition(firstPosition + i), collection.get(i)));

        IntStream.range(firstPosition + collection.size(), sizeOfReference(feature.withoutPosition()).orElseGet(() -> firstPosition + collection.size()))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        super.addAllReferences(feature, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean feature, Id reference) {
        int position = super.appendReference(feature, reference);

        cache.put(feature.withPosition(position), reference);

        return position;
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean feature, List<Id> collection) {
        int firstPosition = super.appendAllReferences(feature, collection);

        IntStream.range(0, collection.size())
                .forEachOrdered(i -> cache.put(feature.withPosition(firstPosition + i), collection.get(i)));

        return firstPosition;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        IntStream.range(feature.position(), sizeOfReference(feature.withoutPosition()).orElseGet(feature::position))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        return super.removeReference(feature);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        IntStream.range(0, sizeOfReference(feature).orElse(0))
                .forEachOrdered(i -> cache.invalidate(feature.withPosition(i)));

        super.removeAllReferences(feature);
    }
}
