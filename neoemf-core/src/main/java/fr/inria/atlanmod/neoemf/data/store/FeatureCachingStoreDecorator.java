/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentStore} wrapper that caches {@link EStructuralFeature}.
 */
@ParametersAreNonnullByDefault
public class FeatureCachingStoreDecorator extends AbstractPersistentStoreDecorator<PersistentStore> {

    /**
     * In-memory cache that holds loaded features, identified by their {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Object> valuesCache;

    /**
     * Constructs a new {@code FeatureCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public FeatureCachingStoreDecorator(PersistentStore store) {
        this(store, 10_000);
    }

    /**
     * Constructs a new {@code FeatureCachingStoreDecorator} with the given {@code cacheSize}.
     *
     * @param store     the underlying store
     * @param cacheSize the size of the cache
     */
    public FeatureCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.valuesCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public <V> Optional<V> valueOf(FeatureKey key) {
        return (Optional<V>) valuesCache.get(key, super::valueOf);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        valuesCache.put(key, value);

        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        valuesCache.invalidate(key);

        super.unsetValue(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        return (Optional<V>) valuesCache.get(key, k -> super.valueOf((MultiFeatureKey) k));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        valuesCache.put(key, value);

        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetAllValues(FeatureKey key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        super.unsetAllValues(key);
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
        valuesCache.put(key, value);

        IntStream.range(key.position() + 1, sizeOfValue(key).orElse(key.position() + 1))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        valuesCache.put(key, value);

        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        IntStream.range(key.position(), sizeOfValue(key).orElse(key.position()))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public Optional<Id> referenceOf(FeatureKey key) {
        return (Optional<Id>) valuesCache.get(key, super::referenceOf);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        valuesCache.put(key, reference);

        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        valuesCache.invalidate(key);

        super.unsetReference(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        return (Optional<Id>) valuesCache.get(key, k -> super.referenceOf((MultiFeatureKey) k));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id reference) {
        valuesCache.put(key, reference);

        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        IntStream.range(0, sizeOfReference(key).orElse(0))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        super.unsetAllReferences(key);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id reference) {
        valuesCache.put(key, reference);

        IntStream.range(key.position() + 1, sizeOfReference(key).orElse(key.position() + 1))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        valuesCache.put(key, reference);

        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultiFeatureKey key) {
        IntStream.range(key.position(), sizeOfReference(key).orElse(key.position()))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        IntStream.range(0, sizeOfReference(key).orElse(0))
                .forEach(i -> valuesCache.invalidate(key.withPosition(i)));

        super.removeAllReferences(key);
    }
}
