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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An abstract {@link PersistentStore} wrapper that delegates method calls to an internal {@link PersistentStore}.
 */
public abstract class AbstractPersistentStoreDecorator extends AbstractPersistentStore {

    /**
     * The underlying store.
     */
    private final PersistentStore store;

    /**
     * Constructs a new {@code AbstractPersistentStoreDecorator} on the given {@code store}.
     *
     * @param store the underlying store
     */
    public AbstractPersistentStoreDecorator(PersistentStore store) {
        this.store = store;
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        return store.get(internalObject, feature, index);
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        return store.set(internalObject, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        return store.isSet(internalObject, feature);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        store.unset(internalObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        return store.isEmpty(internalObject, feature);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        return store.size(internalObject, feature);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return store.contains(internalObject, feature, value);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return store.indexOf(internalObject, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return store.lastIndexOf(internalObject, feature, value);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        store.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        return store.remove(internalObject, feature, index);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        return store.move(internalObject, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        store.clear(internalObject, feature);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        return store.toArray(internalObject, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        return store.toArray(internalObject, feature, array);
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return store.hashCode(internalObject, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        return store.getContainer(internalObject);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        return store.getContainingFeature(internalObject);
    }

    @Override
    public EObject create(EClass eClass) {
        return store.create(eClass);
    }

    @Override
    public Resource resource() {
        return store.resource();
    }

    @Override
    public PersistentEObject eObject(Id id) {
        return store.eObject(id);
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        return store.getAllInstances(eClass, strict);
    }

    @Override
    public void save() {
        store.save();
    }
}
