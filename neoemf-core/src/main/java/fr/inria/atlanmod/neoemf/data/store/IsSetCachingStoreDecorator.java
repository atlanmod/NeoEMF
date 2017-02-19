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
 * A {@link PersistentStore} wrapper that caches the presence of a value.
 */
@ParametersAreNonnullByDefault
public class IsSetCachingStoreDecorator extends AbstractPersistentStoreDecorator<PersistentStore> {

    /**
     * In-memory cache that holds presence of a value, identified by the associated {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Boolean> isSetCache;

    /**
     * Constructs a new {@code IsSetCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public IsSetCachingStoreDecorator(PersistentStore store) {
        this(store, 10_000);
    }

    /**
     * Constructs a new {@code IsSetCachingStoreDecorator} with the given {@code cacheSize}.
     *
     * @param store     the underlying store
     * @param cacheSize the size of the cache
     */
    public IsSetCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.isSetCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        Optional<V> value = super.valueOf(key);
        isSetCache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        isSetCache.put(key, true);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        isSetCache.put(key, false);
        super.unsetValue(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasValue(FeatureKey key) {
        return isSetCache.get(key, super::hasValue);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        Optional<V> value = super.valueOf(key);
        isSetCache.put(key.withoutPosition(), value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        isSetCache.put(key.withoutPosition(), true);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetAllValues(FeatureKey key) {
        isSetCache.put(key, false);
        super.unsetAllValues(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasAnyValue(FeatureKey key) {
        return isSetCache.get(key, super::hasAnyValue);
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
        isSetCache.put(key.withoutPosition(), true);
        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        isSetCache.put(key, true);
        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        isSetCache.invalidate(key.withoutPosition());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        isSetCache.invalidate(key);
        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        OptionalInt size = super.sizeOfValue(key);
        isSetCache.put(key, size.isPresent() && size.getAsInt() != 0);
        return size;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        Optional<Id> value = super.referenceOf(key);
        isSetCache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        isSetCache.put(key, true);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        isSetCache.put(key, false);
        super.unsetReference(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasReference(FeatureKey key) {
        return isSetCache.get(key, super::hasReference);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        Optional<Id> value = super.referenceOf(key);
        isSetCache.put(key.withoutPosition(), value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id reference) {
        isSetCache.put(key.withoutPosition(), true);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        isSetCache.put(key, false);
        super.unsetAllReferences(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasAnyReference(FeatureKey key) {
        return isSetCache.get(key, super::hasAnyReference);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id reference) {
        isSetCache.put(key.withoutPosition(), true);
        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        isSetCache.put(key, true);
        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultiFeatureKey key) {
        isSetCache.invalidate(key.withoutPosition());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        isSetCache.invalidate(key);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        OptionalInt size = super.sizeOfReference(key);
        isSetCache.put(key, size.isPresent() && size.getAsInt() != 0);
        return size;
    }
}
