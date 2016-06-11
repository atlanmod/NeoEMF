/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

public class InvalidTransientEStoreImpl extends TransientEStoreImpl {

    @Override
    public void add(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public void clear(InternalEObject eObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public boolean contains(InternalEObject eObject, EStructuralFeature feature, Object value) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public EObject create(EClass eClass) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public Object get(InternalEObject eObject, EStructuralFeature feature, int index) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public InternalEObject getContainer(InternalEObject eObject) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public EStructuralFeature getContainingFeature(InternalEObject eObject) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public int indexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public boolean isEmpty(InternalEObject eObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public boolean isSet(InternalEObject eObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public int lastIndexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public Object move(InternalEObject eObject, EStructuralFeature feature, int targetIndex,
            int sourceIndex) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public Object remove(InternalEObject eObject, EStructuralFeature feature, int index) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public Object set(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public int size(InternalEObject eObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public Object[] toArray(InternalEObject eObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public <T> T[] toArray(InternalEObject eObject, EStructuralFeature feature, T[] array) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
    @Override
    public void unset(InternalEObject eObject, EStructuralFeature feature) {
        throw new UnsupportedOperationException(
                "The backend you are using does not provide a transient layer, you must save/load your resource before using it");
    }
    
}
