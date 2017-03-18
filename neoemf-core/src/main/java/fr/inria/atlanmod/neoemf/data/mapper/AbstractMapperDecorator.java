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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.log.Log;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

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
        this.next = mapper;
        Log.debug("{0} created", getClass().getSimpleName());
    }

    /**
     * Returns the inner mapper.
     *
     * @return the inner mapper
     */
    protected M next() {
        return next;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void save() {
        next.save();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void close() {
        next.close();
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
    public Optional<ContainerDescriptor> containerOf(Id id) {
        return next.containerOf(id);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void containerFor(Id id, ContainerDescriptor container) {
        next.containerFor(id, container);
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

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Iterable<Id> allInstancesOf(ClassDescriptor metaclass, boolean strict) {
        return next.allInstancesOf(metaclass, strict);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueOf(FeatureKey key) {
        return next.valueOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return next.valueFor(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void unsetValue(FeatureKey key) {
        next.unsetValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean hasValue(FeatureKey key) {
        return next.hasValue(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceOf(FeatureKey key) {
        return next.referenceOf(key);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return next.referenceFor(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void unsetReference(FeatureKey key) {
        next.unsetReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean hasReference(FeatureKey key) {
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
    public <V> List<V> allValuesOf(FeatureKey key) {
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
    public <V> boolean hasAnyValue(FeatureKey key) {
        return next.hasAnyValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void addValue(ManyFeatureKey key, V value) {
        next.addValue(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void appendValue(FeatureKey key, V value) {
        next.appendValue(key, value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void appendAllValues(FeatureKey key, List<V> values) {
        next.appendAllValues(key, values);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return next.removeValue(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> void removeAllValues(FeatureKey key) {
        next.removeAllValues(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        return next.containsValue(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        return next.indexOfValue(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        return next.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
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
    public List<Id> allReferencesOf(FeatureKey key) {
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
    public boolean hasAnyReference(FeatureKey key) {
        return next.hasAnyReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void addReference(ManyFeatureKey key, Id reference) {
        next.addReference(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void appendReference(FeatureKey key, Id reference) {
        next.appendReference(key, reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void appendAllReferences(FeatureKey key, List<Id> references) {
        next.appendAllReferences(key, references);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return next.removeReference(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void removeAllReferences(FeatureKey key) {
        next.removeAllReferences(key);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        return next.containsReference(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        return next.indexOfReference(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        return next.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public OptionalInt sizeOfReference(FeatureKey key) {
        return next.sizeOfReference(key);
    }
}
