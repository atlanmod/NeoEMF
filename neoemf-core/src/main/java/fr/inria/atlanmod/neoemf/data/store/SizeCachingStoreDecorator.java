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

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentStore} wrapper that caches the size data.
 */
@ParametersAreNonnullByDefault
public class SizeCachingStoreDecorator extends AbstractPersistentStoreDecorator<PersistentStore> {

    /**
     * The size of an empty element.
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static final OptionalInt EMPTY = OptionalInt.of(0);

    /**
     * In-memory cache that holds recently processed sizes, identified by the associated {@link FeatureKey}.
     */
    private final Cache<FeatureKey, OptionalInt> sizesCache;

    /**
     * Constructs a new {@code SizeCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public SizeCachingStoreDecorator(PersistentStore store) {
        this(store, 10_000);
    }

    /**
     * Constructs a new {@code SizeCachingStoreDecorator} with the given {@code cacheSize}.
     *
     * @param store     the underlying store
     * @param cacheSize the size of the cache
     */
    public SizeCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.sizesCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        sizesCache.put(key, EMPTY);
        super.unsetValue(key);
    }

    @Override
    public <V> void unsetAllValues(FeatureKey key) {
        sizesCache.put(key, EMPTY);
        super.unsetAllValues(key);
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
        Optional.ofNullable(sizesCache.getIfPresent(key.withoutPosition()))
                .ifPresent(s -> sizesCache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) + 1)));

        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        Optional.ofNullable(sizesCache.getIfPresent(key))
                .ifPresent(s -> sizesCache.put(key, OptionalInt.of(s.orElse(0) + 1)));

        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        Optional.ofNullable(sizesCache.getIfPresent(key.withoutPosition()))
                .ifPresent(s -> sizesCache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) - 1)));

        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        sizesCache.put(key, EMPTY);
        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        return sizesCache.get(key, super::sizeOfValue);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        sizesCache.put(key, EMPTY);
        super.unsetReference(key);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        sizesCache.put(key, EMPTY);
        super.unsetAllReferences(key);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id reference) {
        Optional.ofNullable(sizesCache.getIfPresent(key.withoutPosition()))
                .ifPresent(s -> sizesCache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) + 1)));

        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        Optional.ofNullable(sizesCache.getIfPresent(key))
                .ifPresent(s -> sizesCache.put(key, OptionalInt.of(s.orElse(0) + 1)));

        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultiFeatureKey key) {
        Optional.ofNullable(sizesCache.getIfPresent(key.withoutPosition()))
                .ifPresent(s -> sizesCache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) - 1)));

        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        sizesCache.put(key, EMPTY);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public OptionalInt sizeOfReference(FeatureKey key) {
        return sizesCache.get(key, super::sizeOfReference);
    }
}
