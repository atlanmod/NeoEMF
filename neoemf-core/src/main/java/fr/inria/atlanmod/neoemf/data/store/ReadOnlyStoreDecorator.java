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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * A {@link PersistentStore} that only allows read operations on the underlying database.
 * <p>
 * Read-only configuration allows to access model element faster, without checking value consistency between database
 * calls. This store re-implements all the mutators and throws an {@link UnsupportedOperationException} when they are
 * called, preventing resource corruption.
 */
public class ReadOnlyStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * The message of the exceptions thrown when calling methods.
     */
    private static final String MSG = "Unable to write to resource. Make sure that the resource is not read-only";

    /**
     * Constructs a new {@code ReadOnlyStoreDecorator} on the given {@code store}.
     *
     * @param store the underlying store
     */
    public ReadOnlyStoreDecorator(PersistentStore store) {
        super(store);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    /**
     * @throws UnsupportedOperationException every time: operation not supported in read-only mode
     */
    @Override
    public void save() {
        throw new UnsupportedOperationException(MSG);
    }
}
