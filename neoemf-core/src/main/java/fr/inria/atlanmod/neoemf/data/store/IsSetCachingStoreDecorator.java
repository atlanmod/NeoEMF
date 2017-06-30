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

import fr.inria.atlanmod.common.annotations.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches the presence of a value.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class IsSetCachingStoreDecorator extends AbstractCachingStoreDecorator<SingleFeatureKey, Boolean> {

    /**
     * Constructs a new {@code IsSetCachingStoreDecorator}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public IsSetCachingStoreDecorator(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        Optional<V> value = super.valueOf(key);
        cache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        cache.put(key, true);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        cache.put(key, false);
        super.unsetValue(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasValue(SingleFeatureKey key) {
        return cache.get(key, super::hasValue);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        Optional<Id> value = super.referenceOf(key);
        cache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        cache.put(key, true);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        cache.put(key, false);
        super.unsetReference(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasReference(SingleFeatureKey key) {
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
    public <V> boolean hasAnyValue(SingleFeatureKey key) {
        return cache.get(key, super::hasAnyValue);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        cache.put(key.withoutPosition(), true);
        super.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureKey key, List<? extends V> collection) {
        cache.put(key.withoutPosition(), true);
        super.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureKey key, V value) {
        cache.put(key, true);
        return super.appendValue(key, value);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureKey key, List<? extends V> collection) {
        cache.put(key, true);
        return super.appendAllValues(key, collection);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        cache.invalidate(key.withoutPosition());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(SingleFeatureKey key) {
        cache.invalidate(key);
        super.removeAllValues(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        Optional<Integer> size = super.sizeOfValue(key);
        cache.put(key, size.isPresent() && size.get() != 0);
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
    public boolean hasAnyReference(SingleFeatureKey key) {
        return cache.get(key, super::hasAnyReference);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        cache.put(key.withoutPosition(), true);
        super.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureKey key, List<Id> collection) {
        cache.put(key.withoutPosition(), true);
        super.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureKey key, Id reference) {
        cache.put(key, true);
        return super.appendReference(key, reference);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureKey key, List<Id> collection) {
        cache.put(key, true);
        return super.appendAllReferences(key, collection);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        cache.invalidate(key.withoutPosition());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(SingleFeatureKey key) {
        cache.invalidate(key);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        Optional<Integer> size = super.sizeOfReference(key);
        cache.put(key, size.isPresent() && size.get() != 0);
        return size;
    }
}
