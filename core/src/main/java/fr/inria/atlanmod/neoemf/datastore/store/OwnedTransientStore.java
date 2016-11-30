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

package fr.inria.atlanmod.neoemf.datastore.store;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A {@link AbstractTransientStore} that belongs to a single {@link EObject} owner.
 */
public class OwnedTransientStore extends AbstractTransientStore {

    private final EObject owner;

    public OwnedTransientStore(EObject owner) {
        this.owner = owner;
    }

    @Override
    public Object get(InternalEObject eObject, EStructuralFeature feature, int index) {
        checkOwner(eObject);
        return super.get(eObject, feature, index);
    }

    @Override
    public Object set(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
        checkOwner(eObject);
        return super.set(eObject, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        return super.isSet(eObject, feature);
    }

    @Override
    public void unset(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        super.unset(eObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        return super.isEmpty(eObject, feature);
    }

    @Override
    public int size(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        return super.size(eObject, feature);
    }

    @Override
    public boolean contains(InternalEObject eObject, EStructuralFeature feature, Object value) {
        checkOwner(eObject);
        return super.contains(eObject, feature, value);
    }

    @Override
    public int indexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
        checkOwner(eObject);
        return super.indexOf(eObject, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject eObject, EStructuralFeature feature, Object value) {
        checkOwner(eObject);
        return super.lastIndexOf(eObject, feature, value);
    }

    @Override
    public void add(InternalEObject eObject, EStructuralFeature feature, int index, Object value) {
        checkOwner(eObject);
        super.add(eObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject eObject, EStructuralFeature feature, int index) {
        checkOwner(eObject);
        return super.remove(eObject, feature, index);
    }

    @Override
    public Object move(InternalEObject eObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        checkOwner(eObject);
        return super.move(eObject, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        super.clear(eObject, feature);
    }

    @Override
    public Object[] toArray(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        return super.toArray(eObject, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject eObject, EStructuralFeature feature, T[] array) {
        checkOwner(eObject);
        return super.toArray(eObject, feature, array);
    }

    @Override
    public int hashCode(InternalEObject eObject, EStructuralFeature feature) {
        checkOwner(eObject);
        return super.hashCode(eObject, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject eObject) {
        checkOwner(eObject);
        return super.getContainer(eObject);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject eObject) {
        checkOwner(eObject);
        return super.getContainingFeature(eObject);
    }

    private void checkOwner(InternalEObject eObject) {
        checkArgument(owner == eObject);
    }
}
