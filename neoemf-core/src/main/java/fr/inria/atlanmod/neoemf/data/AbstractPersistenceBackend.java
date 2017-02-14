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
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The abstract implementation of {@link PersistenceBackend}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceBackend implements PersistenceBackend {

    /**
     * Constructs a new {@code AbstractPersistenceBackend}.
     */
    protected AbstractPersistenceBackend() {
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        unsetValue(key);
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        return hasValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public void unsetAllReferences(SingleFeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean hasAnyReference(SingleFeatureKey key) {
        return hasReference(key);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id id) {
        addValue(key, id);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultiFeatureKey key) {
        return removeValue(key);
    }

    @Override
    public void cleanReferences(SingleFeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, Id id) {
        return containsValue(key, id);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(SingleFeatureKey key, Id id) {
        return indexOfValue(key, id);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(SingleFeatureKey key, Id id) {
        return lastIndexOfValue(key, id);
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(SingleFeatureKey key) {
        return valuesAsList(key);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(SingleFeatureKey key) {
        return sizeOfValue(key);
    }

    @Override
    public void unsetAllValues(SingleFeatureKey key) {
        unsetValue(key);
    }

    @Override
    public boolean hasAnyValue(SingleFeatureKey key) {
        return hasValue(key);
    }

    @Override
    public void cleanValues(SingleFeatureKey key) {
        unsetValue(key);
    }
}
