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

    public LoadedObjectCounterStoreDecorator(PersistentStore eStore) {
        super(eStore);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature, int index) {
        setAsLoaded(object);
        Object result = super.get(object, feature, index);
        setAsLoaded(result);
        return result;
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        setAsLoaded(object);
        return super.set(object, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        return super.isSet(object, feature);
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        super.unset(object, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        return super.isEmpty(object, feature);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        return super.size(object, feature);
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        setAsLoaded(object);
        return super.contains(object, feature, value);
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        setAsLoaded(object);
        return super.indexOf(object, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        setAsLoaded(object);
        return super.lastIndexOf(object, feature, value);
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        setAsLoaded(object);
        super.add(object, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        setAsLoaded(object);
        return super.remove(object, feature, index);
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        setAsLoaded(object);
        return super.move(object, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        super.clear(object, feature);
    }

    @Override
    public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        return super.toArray(object, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
        setAsLoaded(object);
        return super.toArray(object, feature, array);
    }

    @Override
    public int hashCode(InternalEObject object, EStructuralFeature feature) {
        setAsLoaded(object);
        return super.hashCode(object, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject object) {
        setAsLoaded(object);
        return super.getContainer(object);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject object) {
        setAsLoaded(object);
        return super.getContainingFeature(object);
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
