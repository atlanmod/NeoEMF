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

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that only allows read operations on the underlying database.
 * <p>
 * Read-only configuration allows to access model element faster, without checking value consistency between database
 * calls. This store re-implements all the mutators and throws an {@link UnsupportedOperationException} when they are
 * called, preventing resource corruption.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("MethodDoesntCallSuperMethod")
public class ReadOnlyStoreDecorator extends AbstractStoreDecorator {

    /**
     * The message of the exceptions thrown when calling methods.
     */
    private static final String MSG = "Unable to write to resource. Make sure that the resource is not read-only";

    /**
     * Constructs a new {@code ReadOnlyStoreDecorator} on the given {@code store}.
     *
     * @param store the inner store
     */
    public ReadOnlyStoreDecorator(Store store) {
        super(store);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void save() {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public <V> void unsetValue(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void unsetReference(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public <V> void appendAllValues(FeatureKey key, List<V> values) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public <V> void removeAllValues(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void appendReference(FeatureKey key, Id reference) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void appendAllReferences(FeatureKey key, List<Id> references) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void removeAllReferences(FeatureKey key) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
}
