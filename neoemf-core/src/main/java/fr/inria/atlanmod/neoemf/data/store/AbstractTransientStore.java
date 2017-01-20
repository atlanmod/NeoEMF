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

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A abstract {@link TransientStore} that uses collections to store data in memory.
 */
public abstract class AbstractTransientStore implements TransientStore {

    /**
     * Map that holds loaded single-valued features.
     */
    protected Map<FeatureKey, Object> singleMap;

    /**
     * Map that holds loaded multi-valued features.
     */
    protected Map<FeatureKey, List<Object>> manyMap;

    /**
     * Constructs a new {@code AbstractTransientStore}.
     */
    public AbstractTransientStore() {
        singleMap = new HashMap<>();
        manyMap = new HashMap<>();
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        Object soughtValue;
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        if (index == PersistentStore.NO_INDEX) {
            soughtValue = singleMap.get(featureKey);
        }
        else {
            List<Object> saved = manyMap.get(featureKey);
            if (nonNull(saved)) {
                soughtValue = saved.get(index);
            }
            else {
                // The list is empty (since it is not persisted in the manyMap object)
                throw new IndexOutOfBoundsException();
            }
        }
        return soughtValue;
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return index == PersistentStore.NO_INDEX ? singleMap.put(featureKey, value) : manyMap.get(featureKey).set(index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return !feature.isMany() ? singleMap.containsKey(featureKey) : manyMap.containsKey(featureKey);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        Map<FeatureKey, ?> concernedMap = !feature.isMany() ? singleMap : manyMap;
        concernedMap.remove(featureKey);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> res = manyMap.get(featureKey);
        return isNull(res) || res.isEmpty();
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        return isNull(list) ? 0 : list.size();
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        return nonNull(list) && list.contains(value);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        return isNull(list) ? PersistentStore.NO_INDEX : list.indexOf(value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        return isNull(list) ? PersistentStore.NO_INDEX : list.lastIndexOf(value);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> saved = manyMap.get(featureKey);
        if (nonNull(saved)) {
            if (index == PersistentStore.NO_INDEX) {
                /*
                 * Handle NO_INDEX index, which represent direct-append feature.
		         * The call to size should not cause an overhead because it would have been done in regular
		         * addUnique() otherwise.
		         */
                index = size(internalObject, feature);
            }
            saved.add(index, value);
        }
        else {
            List<Object> list = createValue();
            list.add(value);
            manyMap.put(featureKey, list);
        }
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> saved = manyMap.get(featureKey);
        if (nonNull(saved)) {
            return saved.remove(index);
        }
        else {
            // The list is empty (since it is not persisted in the manyMap object)
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        Object movedObject = list.remove(sourceIndex);
        list.add(targetIndex, movedObject);
        return movedObject;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        if (nonNull(list)) {
            list.clear();
        }
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        return isNull(list) ? new Object[]{} : list.toArray();
    }

    @Override
    @SuppressWarnings("unchecked") // Unchecked cast: List<Object> to List<T>
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<T> list = (List<T>) manyMap.get(featureKey);
        return isNull(list) ? Arrays.copyOf(array, 0) : list.toArray(array);
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        List<Object> list = manyMap.get(featureKey);
        // Return the default hashCode value if the list is empty
        return isNull(list) ? 1 : list.hashCode();
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        return null;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        throw new IllegalStateException("This method should not be called");
    }

    @Override
    public EObject create(EClass eClass) {
        throw new IllegalStateException("This method should not be called");
    }

    /**
     * Creates a new value that will be stored in the {@link #manyMap}.
     *
     * @return a new {@link List}
     */
    protected List<Object> createValue() {
        return new ArrayList<>();
    }
}
