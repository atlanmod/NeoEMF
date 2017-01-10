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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.HashSet;
import java.util.Set;

public class LoadedObjectCounterStoreDecorator extends AbstractPersistentStoreDecorator {

    private final Set<Id> loadedObjects = new HashSet<>();

    public LoadedObjectCounterStoreDecorator(PersistentStore store) {
        super(store);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        setAsLoaded(internalObject);
        Object soughtValue = super.get(internalObject, feature, index);
        setAsLoaded(soughtValue);
        return soughtValue;
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        setAsLoaded(internalObject);
        return super.set(internalObject, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        return super.isSet(internalObject, feature);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        super.unset(internalObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        return super.isEmpty(internalObject, feature);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        return super.size(internalObject, feature);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        setAsLoaded(internalObject);
        return super.contains(internalObject, feature, value);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        setAsLoaded(internalObject);
        return super.indexOf(internalObject, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        setAsLoaded(internalObject);
        return super.lastIndexOf(internalObject, feature, value);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        setAsLoaded(internalObject);
        super.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        setAsLoaded(internalObject);
        return super.remove(internalObject, feature, index);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        setAsLoaded(internalObject);
        return super.move(internalObject, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        super.clear(internalObject, feature);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        return super.toArray(internalObject, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        setAsLoaded(internalObject);
        return super.toArray(internalObject, feature, array);
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        setAsLoaded(internalObject);
        return super.hashCode(internalObject, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        setAsLoaded(internalObject);
        return super.getContainer(internalObject);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        setAsLoaded(internalObject);
        return super.getContainingFeature(internalObject);
    }

    private void setAsLoaded(Object object) {
        if (object instanceof PersistentEObject) {
            loadedObjects.add(((PersistentEObject) object).id());
        }
        else {
            NeoLogger.debug("Not a {0} : This object will be ignored in the final count.", PersistentEObject.class.getSimpleName());
        }
    }

    private class ShutdownHook extends Thread {

        @Override
        public void run() {
            NeoLogger.info("{0} objects loaded during the execution", loadedObjects.size());
        }
    }
}
