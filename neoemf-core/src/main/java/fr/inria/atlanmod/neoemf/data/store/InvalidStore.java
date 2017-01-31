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
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * {@link PersistentStore} which can be used by back-end implementations that do not provide transient layer.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
public class InvalidStore implements PersistentStore {

    /**
     * The message of the exceptions thrown when calling methods.
     */
    private static final String MSG = "The backend you are using does not provide a transient layer. You must save/load your resource before using it";

    /**
     * Constructs a new {@code InvalidStore}.
     */
    public InvalidStore() {
        super();
        NeoLogger.warn(MSG);
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public EObject create(EClass eClass) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public Resource resource() {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public PersistentEObject eObject(Id id) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        throw new UnsupportedOperationException(MSG);
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException(MSG);
    }
}
