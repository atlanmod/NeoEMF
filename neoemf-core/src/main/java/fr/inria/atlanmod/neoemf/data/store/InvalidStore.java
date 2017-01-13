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
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * {@link PersistentStore} which can be used by back-end implementations that do not provide transient layer.
 */
public class InvalidStore implements PersistentStore {

    private static final String UNSUPPORTED_MSG =
            "The backend you are using does not provide a transient layer. " +
                    "You must save/load your resource before using it";

    /**
     * Instantiates a new {@code InvalidStore}.
     */
    public InvalidStore() {
        super();
        NeoLogger.warn(UNSUPPORTED_MSG);
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        throw unsupportedOperation();
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        throw unsupportedOperation();
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        throw unsupportedOperation();
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        throw unsupportedOperation();
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        throw unsupportedOperation();
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        throw unsupportedOperation();
    }

    @Override
    public EObject create(EClass eClass) {
        throw unsupportedOperation();
    }

    @Override
    public Resource resource() {
        throw unsupportedOperation();
    }

    @Override
    public EObject eObject(Id id) {
        throw unsupportedOperation();
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        throw unsupportedOperation();
    }

    @Override
    public PersistentStore getEStore() {
        throw unsupportedOperation();
    }

    @Override
    public void save() {
        throw unsupportedOperation();
    }

    private UnsupportedOperationException unsupportedOperation() {
        return new UnsupportedOperationException(UNSUPPORTED_MSG);
    }
}
