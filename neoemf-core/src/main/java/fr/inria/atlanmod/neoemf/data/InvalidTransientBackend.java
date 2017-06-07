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

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link TransientBackend} used with databases that does not support the transient state.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
@ParametersAreNonnullByDefault
public final class InvalidTransientBackend implements TransientBackend {

    /**
     * The exception thrown when calling methods.
     */
    private static final RuntimeException E = new UnsupportedOperationException(
            "The back-end you are using doesn't provide a transient layer. You must save/load your resource before using it");

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        throw E;
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        throw E;
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        throw E;
    }

    @Override
    public void unsetContainer(Id id) {
        throw E;
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
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        throw E;
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public void unsetReference(FeatureKey key) {
        throw E;
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        throw E;
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        throw E;
    }

    @Nonnegative
    @Override
    public <V> int appendValue(FeatureKey key, V value) {
        throw E;
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(FeatureKey key, List<V> values) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        throw E;
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        throw E;
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        throw E;
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        throw E;
    }

    @Nonnegative
    @Override
    public int appendReference(FeatureKey key, Id reference) {
        throw E;
    }

    @Nonnegative
    @Override
    public int appendAllReferences(FeatureKey key, List<Id> references) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        throw E;
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        throw E;
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        throw E;
    }
}
