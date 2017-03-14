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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An invalid {@link TransientBackend}.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
@ParametersAreNonnullByDefault
public final class InvalidTransientBackend implements TransientBackend {

    /**
     * The message of the exceptions thrown when calling methods.
     */
    private static final String MSG = "The back-end you are using doesn't provide a transient layer. You must save/load your resource before using it";

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void close() {
        // No need to close anything
    }

    @Override
    public void copyTo(DataMapper target) {
        // No need to copy anything
    }

    @Override
    public boolean exists(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> allValuesOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Iterable<Id> allReferencesOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }
}
