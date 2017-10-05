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
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

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
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        return Optional.ofNullable((V) cache.get(key, k -> super.valueOf(SingleFeatureBean.class.cast(k)).orElse(null)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        cache.put(key, value);

        return super.valueFor(key, value);
    }

    @Override
    public <V> void removeValue(SingleFeatureBean key) {
        cache.invalidate(key);

        super.removeValue(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        return Optional.ofNullable(Id.class.cast(cache.get(key, k -> super.referenceOf(SingleFeatureBean.class.cast(k)).orElse(null))));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        cache.put(key, reference);

        return super.referenceFor(key, reference);
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        cache.invalidate(key);

        super.removeReference(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        return Optional.ofNullable((V) cache.get(key, k -> super.valueOf(ManyFeatureBean.class.cast(k)).orElse(null)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        cache.put(key, value);

        return super.valueFor(key, value);
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
    public <V> void removeAllValues(SingleFeatureBean key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        return Optional.ofNullable(Id.class.cast(cache.get(key, k -> super.referenceOf(ManyFeatureBean.class.cast(k)).orElse(null))));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        cache.put(key, reference);

        return super.referenceFor(key, reference);
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
