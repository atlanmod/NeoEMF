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

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

public class LoadedObjectCounterLoggingDelegatedEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {

    private Set<InternalEObject> loadedObjects = new HashSet<>();

    public LoadedObjectCounterLoggingDelegatedEStoreImpl(
            SearcheableResourceEStore eStore) {
        super(eStore);
        NeoLogger.log(Level.INFO, "Using LoadedObjectCounter logger");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NeoLogger.log(
                        Level.INFO,
                        MessageFormat
                                .format("{0} objects loaded during the execution",
                                        LoadedObjectCounterLoggingDelegatedEStoreImpl.this.loadedObjects
                                                .size()));
            }
        });
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature,
            int index) {
        loadedObjects.add(object);
        Object result = super.get(object, feature, index);
        if(result instanceof InternalEObject) {
            loadedObjects.add((InternalEObject)result);
        }
        return result;
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature,
            int index, Object value) {
        loadedObjects.add(object);
        return super.set(object, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        return super.isSet(object, feature);
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        super.unset(object, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        return super.isEmpty(object, feature);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        return super.size(object, feature);
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature,
            Object value) {
        loadedObjects.add(object);
        return super.contains(object, feature, value);
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature,
            Object value) {
        loadedObjects.add(object);
        return super.indexOf(object, feature, value);
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature,
            Object value) {
        loadedObjects.add(object);
        return super.lastIndexOf(object, feature, value);
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature,
            int index, Object value) {
        loadedObjects.add(object);
        super.add(object, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature,
            int index) {
        loadedObjects.add(object);
        return super.remove(object, feature, index);
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature,
            int targetIndex, int sourceIndex) {
        loadedObjects.add(object);
        return super.move(object, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        super.clear(object, feature);
    }

    @Override
    public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        return super.toArray(object, feature);
    }

    @Override
    public <T> T[] toArray(InternalEObject object, EStructuralFeature feature,
            T[] array) {
        loadedObjects.add(object);
        return super.toArray(object, feature, array);
    }

    @Override
    public int hashCode(InternalEObject object, EStructuralFeature feature) {
        loadedObjects.add(object);
        return super.hashCode(object, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject object) {
        loadedObjects.add(object);
        return super.getContainer(object);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject object) {
        loadedObjects.add(object);
        return super.getContainingFeature(object);
    }

    @Override
    public EObject create(EClass eClass) {
        return super.create(eClass);
    }

    @Override
    public Resource resource() {
        return super.resource();
    }

    @Override
    public EObject eObject(Id id) {
        return super.eObject(id);
    }

}
