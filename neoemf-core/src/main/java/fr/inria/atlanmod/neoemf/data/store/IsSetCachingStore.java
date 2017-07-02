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

import fr.inria.atlanmod.common.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

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
public class IsSetCachingStore extends AbstractCachingStore<SingleFeatureBean, Boolean> {

    /**
     * Constructs a new {@code IsSetCachingStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public IsSetCachingStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        Optional<V> value = super.valueOf(key);
        cache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        cache.put(key, true);
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        cache.put(key, false);
        super.unsetValue(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasValue(SingleFeatureBean key) {
        return cache.get(key, super::hasValue);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        Optional<Id> value = super.referenceOf(key);
        cache.put(key, value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        cache.put(key, true);
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(SingleFeatureBean key) {
        cache.put(key, false);
        super.unsetReference(key);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasReference(SingleFeatureBean key) {
        return cache.get(key, super::hasReference);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        Optional<V> value = super.valueOf(key);
        cache.put(key.withoutPosition(), value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        cache.put(key.withoutPosition(), true);
        return super.valueFor(key, value);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public <V> boolean hasAnyValue(SingleFeatureBean key) {
        return cache.get(key, super::hasAnyValue);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        cache.put(key.withoutPosition(), true);
        super.addValue(key, value);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        cache.put(key.withoutPosition(), true);
        super.addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        cache.put(key, true);
        return super.appendValue(key, value);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        cache.put(key, true);
        return super.appendAllValues(key, collection);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        cache.invalidate(key.withoutPosition());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(SingleFeatureBean key) {
        cache.invalidate(key);
        super.removeAllValues(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        Optional<Integer> size = super.sizeOfValue(key);
        cache.put(key, size.isPresent() && size.get() != 0);
        return size;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        Optional<Id> value = super.referenceOf(key);
        cache.put(key.withoutPosition(), value.isPresent());
        return value;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        cache.put(key.withoutPosition(), true);
        return super.referenceFor(key, reference);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public boolean hasAnyReference(SingleFeatureBean key) {
        return cache.get(key, super::hasAnyReference);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        cache.put(key.withoutPosition(), true);
        super.addReference(key, reference);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        cache.put(key.withoutPosition(), true);
        super.addAllReferences(key, collection);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        cache.put(key, true);
        return super.appendReference(key, reference);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        cache.put(key, true);
        return super.appendAllReferences(key, collection);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        cache.invalidate(key.withoutPosition());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        cache.invalidate(key);
        super.removeAllReferences(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        Optional<Integer> size = super.sizeOfReference(key);
        cache.put(key, size.isPresent() && size.get() != 0);
        return size;
    }
}
