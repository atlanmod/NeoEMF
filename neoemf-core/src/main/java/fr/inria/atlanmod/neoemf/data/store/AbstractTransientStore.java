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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (index == NO_INDEX) {
            return singleMap.get(key);
        }
        else {
            return Optional.ofNullable(manyMap.get(key))
                    .map(values -> values.get(index))
                    .orElseThrow(IndexOutOfBoundsException::new);
        }
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return index == NO_INDEX ? singleMap.put(key, value) : manyMap.get(key).set(index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return (!feature.isMany() ? singleMap : manyMap).containsKey(key);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        (!feature.isMany() ? singleMap : manyMap).remove(key);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        return size(internalObject, feature) == 0;
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(List::size)
                .orElse(0);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(v -> v.contains(value))
                .orElse(false);
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(v -> v.indexOf(value))
                .orElse(NO_INDEX);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(v -> v.lastIndexOf(value))
                .orElse(NO_INDEX);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        List<Object> values = manyMap.get(key);
        if (nonNull(values)) {
            if (index == NO_INDEX) {
                index = size(internalObject, feature);
            }
            values.add(index, value);
        }
        else {
            List<Object> list = createValue();
            list.add(value);
            manyMap.put(key, list);
        }
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(v -> v.remove(index))
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        List<Object> list = manyMap.get(key);
        Object movedObject = list.remove(sourceIndex);
        list.add(targetIndex, movedObject);

        return movedObject;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        Optional.ofNullable(manyMap.get(key))
                .ifPresent(List::clear);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(List::toArray)
                .orElse(new Object[0]);
    }

    @Override
    @SuppressWarnings("unchecked") // Unchecked cast: List<Object> to List<T>
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(v -> v.toArray(array))
                .orElse(Arrays.copyOf(array, 0));
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(manyMap.get(key))
                .map(List::hashCode)
                .orElse(0);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        return null;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
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
