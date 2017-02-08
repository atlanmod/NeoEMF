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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Mock {@link PersistenceBackend} implementation for HBase to fit core architecture.
 * <p>
 * This class does not access HBase database, but is here to fit the requirement of the core architecture. For
 * historical reasons the real access to the HBase Table is done in {@link DirectWriteHBaseStore}.
 * <p>
 * Moving HBase access to this class to fit NeoEMF back-end architecture is planned in a future release.
 *
 * @see DirectWriteHBaseStore
 */
@ParametersAreNonnullByDefault
class DefaultHBaseBackend extends AbstractHBaseBackend {

    /**
     * Constructs a new {@code DefaultHBaseBackend}.
     */
    protected DefaultHBaseBackend() {
        super();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unsetValue(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unsetReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unsetAllValues(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean hasAnyValue(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cleanValues(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}