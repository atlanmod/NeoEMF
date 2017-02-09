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
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * {@link PersistenceBackend} that do not provide transient layer.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
@ParametersAreNonnullByDefault
public final class InvalidBackend implements PersistenceBackend {

    /**
     * The message of the exceptions thrown when calling methods.
     */
    private static final String MSG = "The back-end you are using doesn't provide a transient layer. You must save/load your resource before using it";

    /**
     * Constructs a new {@code InvalidBackend}.
     */
    public InvalidBackend() {
        super();
        NeoLogger.warn(MSG);
    }

    @Override
    public void save() {
        // Do nothing
    }

    @Override
    public void close() {
        // Do nothing
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void create(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean has(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void unsetAllValues(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean hasAnyValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void cleanValues(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOf(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }
}
