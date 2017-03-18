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

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches the size data.
 */
@ParametersAreNonnullByDefault
public class SizeCachingStoreDecorator extends AbstractCachingStoreDecorator<OptionalInt> {

    /**
     * Constructs a new {@code SizeCachingStoreDecorator}.
     *
     * @param store the inner store
     */
    public SizeCachingStoreDecorator(Store store) {
        super(store);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        cacheSize(key, 0);
        super.unsetValue(key);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        cacheSize(key, 0);
        super.unsetReference(key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        cacheSize(key.withoutPosition(), sizeOfValue(key.withoutPosition()).orElse(0) + 1);
        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        cacheSize(key, sizeOfValue(key).orElse(0) + 1);
        super.appendValue(key, value);
    }

    @Override
    public <V> void appendAllValues(FeatureKey key, List<V> values) {
        cacheSize(key, sizeOfValue(key).orElse(0) + values.size());
        super.appendAllValues(key, values);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        sizeOfValue(key.withoutPosition()).ifPresent(s -> cacheSize(key.withoutPosition(), s - 1));
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        cacheSize(key, 0);
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
        cacheSize(key.withoutPosition(), sizeOfReference(key.withoutPosition()).orElse(0) + 1);
        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        cacheSize(key, sizeOfReference(key).orElse(0) + 1);
        super.appendReference(key, reference);
    }

    @Override
    public void appendAllReferences(FeatureKey key, List<Id> references) {
        cacheSize(key, sizeOfReference(key).orElse(0) + references.size());
        super.appendAllReferences(key, references);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        sizeOfReference(key.withoutPosition()).ifPresent(s -> cacheSize(key.withoutPosition(), s - 1));
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        cacheSize(key, 0);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public OptionalInt sizeOfReference(FeatureKey key) {
        return cache.get(key, super::sizeOfReference);
    }

    /**
     * Defines the number of elements of the given {@code key}.
     *
     * @param key  the key to define the size
     * @param size the size
     */
    private void cacheSize(FeatureKey key, @Nonnegative int size) {
        cache.put(key, size == 0 ? OptionalInt.empty() : OptionalInt.of(size));
    }
}
