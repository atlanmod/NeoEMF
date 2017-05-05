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

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches {@link EStructuralFeature}.
 */
@ParametersAreNonnullByDefault
public class FeatureCachingStoreDecorator extends AbstractCachingStoreDecorator<FeatureKey, Object> {

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
        return Optional.ofNullable(Id.class.cast(cache.get(key, k -> super.referenceOf(k).orElse(null))));
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
        return Optional.ofNullable((V) cache.get(key, k -> super.valueOf(ManyFeatureKey.class.cast(k)).orElse(null)));
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

        IntStream.range(key.position() + 1, sizeOfValue(key.withoutPosition()).orElseGet(() -> key.position() + 1))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        cache.put(key.withPosition(sizeOfValue(key).orElse(0)), value);

        super.appendValue(key, value);
    }

    @Override
    public <V> void appendAllValues(FeatureKey key, List<V> values) {
        int size = sizeOfValue(key).orElse(0);

        IntStream.range(0, values.size())
                .forEach(i -> cache.put(key.withPosition(size + i), values.get(i)));

        super.appendAllValues(key, values);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        IntStream.range(key.position(), sizeOfValue(key.withoutPosition()).orElseGet(key::position))
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
        return Optional.ofNullable(Id.class.cast(cache.get(key, k -> super.referenceOf(ManyFeatureKey.class.cast(k)).orElse(null))));
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

        IntStream.range(key.position() + 1, sizeOfReference(key.withoutPosition()).orElseGet(() -> key.position() + 1))
                .forEach(i -> cache.invalidate(key.withPosition(i)));

        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        cache.put(key.withPosition(sizeOfReference(key).orElse(0)), reference);

        super.appendReference(key, reference);
    }

    @Override
    public void appendAllReferences(FeatureKey key, List<Id> references) {
        int size = sizeOfReference(key).orElse(0);

        IntStream.range(0, references.size())
                .forEach(i -> cache.put(key.withPosition(size + i), references.get(i)));

        super.appendAllReferences(key, references);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        IntStream.range(key.position(), sizeOfReference(key.withoutPosition()).orElseGet(key::position))
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
