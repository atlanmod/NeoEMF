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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A {@link TransientStore} that belongs to a single {@link EObject} owner. The ownership is checked at each method call.
 */
public class OwnedTransientStore extends AbstractTransientStore {

    private final EObject owner;

    /**
     * Constructs a new {@code OwnedTransientStore} with the given {@code owner}.
     *
     * @param owner the owner of this store
     */
    public OwnedTransientStore(EObject owner) {
        this.owner = owner;
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkOwner(internalObject);
        return super.get(internalObject, feature, index);
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkOwner(internalObject);
        return super.set(internalObject, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        return super.isSet(internalObject, feature);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        super.unset(internalObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        return super.isEmpty(internalObject, feature);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        return super.size(internalObject, feature);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkOwner(internalObject);
        return super.contains(internalObject, feature, value);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkOwner(internalObject);
        return super.indexOf(internalObject, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkOwner(internalObject);
        return super.lastIndexOf(internalObject, feature, value);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkOwner(internalObject);
        super.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkOwner(internalObject);
        return super.remove(internalObject, feature, index);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        checkOwner(internalObject);
        return super.move(internalObject, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        super.clear(internalObject, feature);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        return super.toArray(internalObject, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        checkOwner(internalObject);
        return super.toArray(internalObject, feature, array);
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        checkOwner(internalObject);
        return super.hashCode(internalObject, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        checkOwner(internalObject);
        return super.getContainer(internalObject);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        checkOwner(internalObject);
        return super.getContainingFeature(internalObject);
    }

    /**
     * Checks that the {@code internalObject} is the owner of this store.
     */
    private void checkOwner(InternalEObject internalObject) {
        checkArgument(owner == internalObject);
    }
}
