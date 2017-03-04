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

import org.eclipse.emf.ecore.EObject;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A {@link PersistentStore} wrapper that belongs to a single {@link EObject} owner. The ownership is checked at each
 * method call.
 */
@ParametersAreNonnullByDefault
public class OwnedStoreDecorator extends AbstractPersistentStoreDecorator<PersistentStore> {

    /**
     * The owner of this store.
     */
    private final Id ownerId;

    /**
     * Constructs a new {@code OwnedStoreDecorator} with the given {@code owner}.
     *
     * @param store   the underlying store
     * @param ownerId the identifier of the owner of this store
     */
    public OwnedStoreDecorator(PersistentStore store, Id ownerId) {
        super(store);
        this.ownerId = ownerId;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        checkOwner(key.id());
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkOwner(key.id());
        return super.valueFor(key, value);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        checkOwner(key.id());
        super.unsetValue(key);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        checkOwner(key.id());
        return super.hasValue(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        checkOwner(key.id());
        return super.valueOf(key);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> allValuesOf(FeatureKey key) {
        checkOwner(key.id());
        return super.allValuesOf(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        checkOwner(key.id());
        return super.valueFor(key, value);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        checkOwner(key.id());
        return super.hasAnyValue(key);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        checkOwner(key.id());
        super.addValue(key, value);
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        checkOwner(key.id());
        super.appendValue(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        checkOwner(key.id());
        return super.removeValue(key);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        checkOwner(key.id());
        super.removeAllValues(key);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        checkOwner(key.id());
        return super.containsValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        checkOwner(key.id());
        return super.indexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        checkOwner(key.id());
        return super.lastIndexOfValue(key, value);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(FeatureKey key) {
        checkOwner(key.id());
        return super.sizeOfValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        checkOwner(key.id());
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        checkOwner(key.id());
        return super.referenceFor(key, reference);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        checkOwner(key.id());
        super.unsetReference(key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        checkOwner(key.id());
        return super.hasReference(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        checkOwner(key.id());
        return super.referenceOf(key);
    }

    @Nonnull
    @Override
    public Iterable<Id> allReferencesOf(FeatureKey key) {
        checkOwner(key.id());
        return super.allReferencesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        checkOwner(key.id());
        return super.referenceFor(key, reference);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        checkOwner(key.id());
        return super.hasAnyReference(key);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        checkOwner(key.id());
        super.addReference(key, reference);
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        checkOwner(key.id());
        super.appendReference(key, reference);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        checkOwner(key.id());
        return super.removeReference(key);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        checkOwner(key.id());
        super.removeAllReferences(key);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        checkOwner(key.id());
        return super.containsReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        checkOwner(key.id());
        return super.indexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        checkOwner(key.id());
        return super.lastIndexOfReference(key, reference);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        checkOwner(key.id());
        return super.sizeOfReference(key);
    }

    /**
     * Checks that the {@code id} is the same as the owner of that store.
     *
     * @param id the identifier to check the ownership
     *
     * @throws IllegalArgumentException if the {@code id} is not {@link #ownerId}
     */
    private void checkOwner(Id id) {
        checkArgument(Objects.equals(ownerId, id), "%s is not the owner of this store (%s)", id, ownerId);
    }
}
