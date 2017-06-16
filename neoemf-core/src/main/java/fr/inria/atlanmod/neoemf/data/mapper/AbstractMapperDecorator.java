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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract {@link DataMapper} wrapper that delegates method calls to an internal {@link DataMapper}.
 *
 * @param <M> the type of the inner {@link DataMapper}
 */
@ParametersAreNonnullByDefault
public class AbstractMapperDecorator<M extends DataMapper> implements DataMapper {

    /**
     * The inner mapper.
     */
    private final M next;

    /**
     * Constructs a new {@code AbstractStoreDecorator} on the given {@code mapper}.
     *
     * @param mapper the inner mapper
     */
    protected AbstractMapperDecorator(M mapper) {
        this.next = checkNotNull(mapper);

        Log.debug("{0} created", getClass().getSimpleName());
    }

    /**
     * Returns the inner mapper.
     *
     * @return the inner mapper
     */
    @Nonnull
    protected M next() {
        return next;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void close() {
        next.close();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void save() {
        next.save();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void copyTo(DataMapper target) {
        next.copyTo(target);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean exists(Id id) {
        return next.exists(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<SingleFeatureKey> containerOf(Id id) {
        return next.containerOf(id);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void containerFor(Id id, SingleFeatureKey container) {
        next.containerFor(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        next.unsetContainer(id);
    }

    @Override
    public boolean hasContainer(Id id) {
        return next.hasContainer(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        return next.metaclassOf(id);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        next.metaclassFor(id, metaclass);
    }

    @Override
    public boolean hasMetaclass(Id id) {
        return next.hasMetaclass(id);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Iterable<Id> allInstancesOf(ClassDescriptor metaclass, boolean strict) {
        return next.allInstancesOf(metaclass, strict);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        return next.valueFor(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void unsetValue(SingleFeatureKey key) {
        next.unsetValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean hasValue(SingleFeatureKey key) {
        return next.hasValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void unsetReference(SingleFeatureKey key) {
        next.unsetReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean hasReference(SingleFeatureKey key) {
        return next.hasReference(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> List<V> allValuesOf(SingleFeatureKey key) {
        return next.allValuesOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return next.valueFor(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean hasAnyValue(SingleFeatureKey key) {
        return next.hasAnyValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void addValue(ManyFeatureKey key, V value) {
        next.addValue(key, value);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendValue(SingleFeatureKey key, V value) {
        return next.appendValue(key, value);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> int appendAllValues(SingleFeatureKey key, List<V> values) {
        return next.appendAllValues(key, values);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return next.removeValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void removeAllValues(SingleFeatureKey key) {
        next.removeAllValues(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        return next.moveValue(source, target);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean containsValue(SingleFeatureKey key, @Nullable V value) {
        return next.containsValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        return next.indexOfValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        return next.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        return next.sizeOfValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public List<Id> allReferencesOf(SingleFeatureKey key) {
        return next.allReferencesOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean hasAnyReference(SingleFeatureKey key) {
        return next.hasAnyReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void addReference(ManyFeatureKey key, Id reference) {
        next.addReference(key, reference);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendReference(SingleFeatureKey key, Id reference) {
        return next.appendReference(key, reference);
    }

    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public int appendAllReferences(SingleFeatureKey key, List<Id> references) {
        return next.appendAllReferences(key, references);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return next.removeReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllReferences(SingleFeatureKey key) {
        next.removeAllReferences(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        return next.moveReference(source, target);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        return next.containsReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        return next.indexOfReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        return next.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        return next.sizeOfReference(key);
    }
}
