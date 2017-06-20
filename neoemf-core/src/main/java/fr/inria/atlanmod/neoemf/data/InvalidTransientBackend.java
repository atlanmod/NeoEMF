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
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.List;
import java.util.Optional;

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
            "The back-end you are using doesn't provide a transient layer. " +
                    "You must save/load your resource before using it");

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
    public Optional<SingleFeatureKey> containerOf(Id id) {
        throw E;
    }

    @Override
    public void containerFor(Id id, SingleFeatureKey container) {
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
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public <V> boolean hasValue(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> void addAllValues(ManyFeatureKey key, List<? extends V> collection) {
        throw E;
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureKey key, V value) {
        throw E;
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureKey key, List<? extends V> collection) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        throw E;
    }

    @Override
    public <V> void removeAllValues(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        throw E;
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public boolean hasAnyReference(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public void addAllReferences(ManyFeatureKey key, List<Id> collection) {
        throw E;
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureKey key, Id reference) {
        throw E;
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureKey key, List<Id> collection) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        throw E;
    }

    @Override
    public void removeAllReferences(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        throw E;
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        throw E;
    }
}
