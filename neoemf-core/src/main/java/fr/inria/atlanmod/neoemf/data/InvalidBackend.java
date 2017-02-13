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
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;
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
    }

    @Override
    public void save() {
        // Do nothing
        NeoLogger.warn(MSG);
    }

    @Override
    public void close() {
        // Do nothing
        NeoLogger.warn(MSG);
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
    public void copyTo(PersistenceBackend target) {
        // Do nothing
        NeoLogger.warn(MSG);
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
    public Optional<ContainerDescriptor> containerOf(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<MetaclassDescriptor> metaclassOf(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void metaclassFor(Id id, MetaclassDescriptor metaclass) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean hasValue(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void unsetAllValues(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> void cleanValues(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(SingleFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(SingleFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void unsetAllReferences(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean hasAnyReference(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultiFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void cleanReferences(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(SingleFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(SingleFeatureKey key, Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(SingleFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }
}
