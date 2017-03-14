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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches the size data.
 */
@ParametersAreNonnullByDefault
public class SizeCachingStoreDecorator extends AbstractStoreDecorator {

    /**
     * The size of an empty element.
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static final OptionalInt EMPTY = OptionalInt.of(0);

    /**
     * In-memory cache that holds recently processed sizes, identified by the associated {@link FeatureKey}.
     */
    private final Cache<FeatureKey, OptionalInt> cache = CacheBuilder.newBuilder()
            .maximumSize()
            .build();

    /**
     * Constructs a new {@code SizeCachingStoreDecorator} with the default cache size.
     *
     * @param store the inner store
     */
    public SizeCachingStoreDecorator(Store store) {
        super(store);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        cache.put(key, EMPTY);
        super.unsetValue(key);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        cache.put(key, EMPTY);
        super.unsetReference(key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        Optional.ofNullable(cache.get(key.withoutPosition()))
                .ifPresent(s -> cache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) + 1)));

        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        Optional.ofNullable(cache.get(key))
                .ifPresent(s -> cache.put(key, OptionalInt.of(s.orElse(0) + 1)));

        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        Optional.ofNullable(cache.get(key.withoutPosition()))
                .ifPresent(s -> cache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) - 1)));

        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        cache.put(key, EMPTY);
        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        return cache.get(key, super::sizeOfValue);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        Optional.ofNullable(cache.get(key.withoutPosition()))
                .ifPresent(s -> cache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) + 1)));

        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        Optional.ofNullable(cache.get(key))
                .ifPresent(s -> cache.put(key, OptionalInt.of(s.orElse(0) + 1)));

        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        Optional.ofNullable(cache.get(key.withoutPosition()))
                .ifPresent(s -> cache.put(key.withoutPosition(), OptionalInt.of(s.orElse(0) - 1)));

        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        cache.put(key, EMPTY);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public OptionalInt sizeOfReference(FeatureKey key) {
        return cache.get(key, super::sizeOfReference);
    }
}
