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

package fr.inria.atlanmod.neoemf.map.datastore.store.impl;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureKey;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


public class DirectWriteMapListsStore extends DirectWriteMapStore {

    // TODO: Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 1000;

    private final LoadingCache<FeatureKey, Object> mapCache;

    public DirectWriteMapListsStore(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
        this.mapCache = Caffeine.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build(new Tuple2CacheLoader());
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        int returnValue;
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        List<Object> list = manyValueFrom(getFromMap(persistentEObject, feature));
        if (isNull(list)) {
            returnValue = -1;
        }
        else if (feature instanceof EAttribute) {
            returnValue = list.indexOf(serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            returnValue = list.indexOf(childEObject.id());
        }
        return returnValue;
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        int returnValue;
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        List<Object> list = manyValueFrom(getFromMap(persistentEObject, feature));
        if (isNull(list)) {
            returnValue = -1;
        }
        else if (feature instanceof EAttribute) {
            returnValue = list.lastIndexOf(serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            returnValue = list.lastIndexOf(childEObject.id());
        }
        return returnValue;
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        persistenceBackend.storeValue(featureKey, new ArrayList<>());
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        Object value = getFromMap(object, eAttribute);
        if (eAttribute.isMany()) {
            value = manyValueFrom(value).get(index);
        }
        return parseProperty(eAttribute, value);
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        Object value = getFromMap(object, eReference);
        if (eReference.isMany()) {
            value = eObject((Id) manyValueFrom(value).get(index));
        }
        return eObject((Id) value);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        Object oldValue;
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        if (!eAttribute.isMany()) {
            oldValue = persistenceBackend.storeValue(featureKey, serializeToProperty(eAttribute, value));
        }
        else {
            List<Object> list = manyValueFrom(getFromMap(featureKey));
            oldValue = list.get(index);
            list.set(index, serializeToProperty(eAttribute, value));
            persistenceBackend.storeValue(featureKey, list.toArray());
            oldValue = parseProperty(eAttribute, oldValue);
        }
        return parseProperty(eAttribute, oldValue);
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        Object oldId;
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        updateContainment(object, eReference, value);
        updateInstanceOf(value);
        if (!eReference.isMany()) {
            oldId = persistenceBackend.storeValue(featureKey, value.id());
        }
        else {
            List<Object> list = manyValueFrom(getFromMap(featureKey));
            oldId = list.get(index);
            list.set(index, value.id());
            persistenceBackend.storeValue(featureKey, list.toArray());
        }
        return isNull(oldId) ? null : eObject((Id) oldId);
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        List<Object> list = manyValueFrom(getFromMap(featureKey));
        list.add(index, serializeToProperty(eAttribute, value));
        persistenceBackend.storeValue(featureKey, list.toArray());
    }

    @Override
    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        updateContainment(object, eReference, referencedObject);
        updateInstanceOf(referencedObject);
        List<Object> list = manyValueFrom(getFromMap(featureKey));
        list.add(index, referencedObject.id());
        persistenceBackend.storeValue(featureKey, list.toArray());
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        List<Object> list = manyValueFrom(getFromMap(featureKey));
        Object oldValue = list.get(index);
        list.remove(index);
        persistenceBackend.storeValue(featureKey, list.toArray());
        return parseProperty(eAttribute, oldValue);
    }

    @Override
    protected Object removeReference(PersistentEObject object, EReference eReference, int index) {
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        List<Object> list = manyValueFrom(getFromMap(featureKey));
        Object oldId = list.get(index);
        list.remove(index);
        persistenceBackend.storeValue(featureKey, list.toArray());
        return eObject((Id) oldId);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        List<Object> list = manyValueFrom(getFromMap(persistentEObject, feature));
        return isNull(list) ? 0 : list.size();
    }

    @Override
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        Object returnValue = null;
        FeatureKey featureKey = FeatureKey.from(object, feature);
        if (!feature.isMany()) {
            returnValue = persistenceBackend.valueOf(featureKey);
        }
        else {
            try {
                returnValue = mapCache.get(featureKey);
            }
            catch (Exception e) {
                NeoLogger.warn(e);
            }
        }
        return returnValue;
    }

    @SuppressWarnings("unchecked") // Unchecked cast: 'Object' to 'List<...>'
    private List<Object> manyValueFrom(Object value) {
        return (List<Object>) value;
    }

    private class Tuple2CacheLoader implements CacheLoader<FeatureKey, Object> {

        private static final int ARRAY_SIZE_OFFSET = 10;

        @Override
        public Object load(FeatureKey key) throws Exception {
            Object returnValue;
            Object value = persistenceBackend.valueOf(key);
            if (isNull(value)) {
                returnValue = new ArrayList<>();
            }
            else if (value instanceof Object[]) {
                Object[] array = (Object[]) value;
                List<Object> list = new ArrayList<>(array.length + ARRAY_SIZE_OFFSET);
                CollectionUtils.addAll(list, array);
                returnValue = list;
            }
            else {
                returnValue = value;
            }
            return returnValue;
        }
    }
}
