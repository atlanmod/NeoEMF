/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */
package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Utility class which can be used by backend-specific implementations that does not provide a transient layer.
 */
public class InvalidTransientResourceEStoreImpl implements PersistentEStore {

    @Override
    public void add(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }
    
    @Override
    public void clear(InternalEObject eObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }
    
    @Override
    public boolean contains(InternalEObject eObject, EStructuralFeature feature, Object value) {
        throw unsupportedOperation();
    }
    
    @Override
    public EObject create(EClass eClass) {
        throw unsupportedOperation();
    }
    
    @Override
    public Object get(InternalEObject eObject, EStructuralFeature feature, int index) {
        throw unsupportedOperation();
    }
    
    @Override
    public InternalEObject getContainer(InternalEObject eObject) {
        throw unsupportedOperation();
    }
    
    @Override
    public EStructuralFeature getContainingFeature(InternalEObject eObject) {
        throw unsupportedOperation();
    }
    
    @Override
    public int indexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
        throw unsupportedOperation();
    }
    
    @Override
    public boolean isEmpty(InternalEObject eObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }
    
    @Override
    public boolean isSet(InternalEObject eObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }
    
    @Override
    public int lastIndexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
        throw unsupportedOperation();
    }
    
    @Override
    public Object move(InternalEObject eObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        throw unsupportedOperation();
    }
    
    @Override
    public Object remove(InternalEObject eObject, EStructuralFeature feature, int index) {
        throw unsupportedOperation();
    }
    
    @Override
    public Object set(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }
    
    @Override
    public int size(InternalEObject eObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }
    
    @Override
    public Object[] toArray(InternalEObject eObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }
    
    @Override
    public <T> T[] toArray(InternalEObject eObject, EStructuralFeature feature, T[] array) {
        throw unsupportedOperation();
    }
    
    @Override
    public void unset(InternalEObject eObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public int hashCode(InternalEObject object, EStructuralFeature feature) {
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
    public PersistentEStore getEStore() {
        throw unsupportedOperation();
    }

    @Override
    public void save() {
        throw unsupportedOperation();
    }

    private UnsupportedOperationException unsupportedOperation() {
        return new UnsupportedOperationException("The backend you are using does not provide a transient layer. " +
                                                         "You must save/load your resource before using it");
    }
}
