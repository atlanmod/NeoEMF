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
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

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
    public Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        return hasValue(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    public Iterable<Id> allReferencesOf(FeatureKey key) {
        return allValuesOf(key);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
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
    public void cleanReferences(FeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        return containsValue(key, id);
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return indexOfValue(key, id);
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return lastIndexOfValue(key, id);
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(FeatureKey key) {
        return sizeOfValue(key);
    }

    @Override
    public void unsetAllValues(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public boolean hasAnyValue(FeatureKey key) {
        return hasValue(key);
    }

    @Override
    public void cleanValues(FeatureKey key) {
        unsetValue(key);
    }
}
