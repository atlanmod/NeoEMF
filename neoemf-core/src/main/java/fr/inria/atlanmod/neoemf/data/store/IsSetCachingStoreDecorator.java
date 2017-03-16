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

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches the presence of a value.
 */
@ParametersAreNonnullByDefault
public class IsSetCachingStoreDecorator extends AbstractCachingStoreDecorator<Boolean> {

    /**
     * Constructs a new {@code IsSetCachingStoreDecorator} with the default cache size.
     *
     * @param store the inner store
     */
    public IsSetCachingStoreDecorator(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        Optional<V> value = super.valueOf(key);
        cache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        cache.put(key, true);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        cache.put(key, false);
        super.unsetValue(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasValue(FeatureKey key) {
        return cache.get(key, super::hasValue);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        Optional<Id> value = super.referenceOf(key);
        cache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        cache.put(key, true);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        cache.put(key, false);
        super.unsetReference(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasReference(FeatureKey key) {
        return cache.get(key, super::hasReference);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        Optional<V> value = super.valueOf(key);
        cache.put(key.withoutPosition(), value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        cache.put(key.withoutPosition(), true);
        return super.valueFor(key, value);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasAnyValue(FeatureKey key) {
        return cache.get(key, super::hasAnyValue);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        cache.put(key.withoutPosition(), true);
        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        cache.put(key, true);
        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        cache.invalidate(key.withoutPosition());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        cache.invalidate(key);
        super.removeAllValues(key);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        OptionalInt size = super.sizeOfValue(key);
        cache.put(key, size.isPresent() && size.getAsInt() != 0);
        return size;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        Optional<Id> value = super.referenceOf(key);
        cache.put(key.withoutPosition(), value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        cache.put(key.withoutPosition(), true);
        return super.referenceFor(key, reference);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasAnyReference(FeatureKey key) {
        return cache.get(key, super::hasAnyReference);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        cache.put(key.withoutPosition(), true);
        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        cache.put(key, true);
        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        cache.invalidate(key.withoutPosition());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        cache.invalidate(key);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        OptionalInt size = super.sizeOfReference(key);
        cache.put(key, size.isPresent() && size.getAsInt() != 0);
        return size;
    }
}
