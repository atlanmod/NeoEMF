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

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches {@link EStructuralFeature}.
 */
@ParametersAreNonnullByDefault
public class FeatureCachingStoreDecorator extends AbstractStoreDecorator {

    /**
     * In-memory cache that holds loaded features, identified by their {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Object> cache = CacheBuilder.newBuilder()
            .maximumSize()
            .build();

    /**
     * Constructs a new {@code FeatureCachingStoreDecorator}.
     *
     * @param store the inner store
     */
    public FeatureCachingStoreDecorator(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public <V> Optional<V> valueOf(FeatureKey key) {
        return Optional.ofNullable((V) cache.get(key, k -> super.valueOf(k).orElse(null)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        cache.put(key, value);

        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        cache.invalidate(key);

        super.unsetValue(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public Optional<Id> referenceOf(FeatureKey key) {
        return Optional.ofNullable((Id) cache.get(key, k -> super.referenceOf(k).orElse(null)));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        cache.put(key, reference);

        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        cache.invalidate(key);

        super.unsetReference(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        return Optional.ofNullable((V) cache.get(key, k -> super.valueOf((ManyFeatureKey) k).orElse(null)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        cache.put(key, value);

        return super.valueFor(key, value);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        cache.put(key, value);

        IntStream.range(key.position() + 1, sizeOfValue(key).orElse(key.position() + 1))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        cache.put(key, value);

        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        IntStream.range(key.position(), sizeOfValue(key).orElse(key.position()))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        return Optional.ofNullable((Id) cache.get(key, k -> super.referenceOf((ManyFeatureKey) k).orElse(null)));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        cache.put(key, reference);

        return super.referenceFor(key, reference);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        cache.put(key, reference);

        IntStream.range(key.position() + 1, sizeOfReference(key).orElse(key.position() + 1))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        cache.put(key, reference);

        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        IntStream.range(key.position(), sizeOfReference(key).orElse(key.position()))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        IntStream.range(0, sizeOfReference(key).orElse(0))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.removeAllReferences(key);
    }
}
