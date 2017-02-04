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

package fr.inria.atlanmod.neoemf.data.map.core.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.DefaultDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Collection;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * An abstract {@link DefaultDirectWriteStore} that redirects certain methods according to the instance of the
 * encountered {@link EStructuralFeature}. If the subclass does not re-implement the inherited methods of EMF, the call
 * is automatically redirected to the associated method that begins with the same name.
 */
public class DirectWriteMapStore extends DefaultDirectWriteStore<PersistenceBackend> {

    /**
     * Constructs a new {@code DirectWriteMapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapStore(PersistentResource resource, PersistenceBackend backend) {
        super(resource, backend);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return backend.hasValue(featureKey);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.unsetValue(featureKey);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object[] array = (Object[]) getFromMap(object, feature);
        return isNull(array) ? 0 : array.length;
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.debug("DirectWriteMapStore::contains({1}, {2})", feature.getName(), value);
        checkNotNull(internalObject);
        checkNotNull(feature);

        return indexOf(internalObject, feature, value) != PersistentStore.NO_INDEX;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(value);

        int index;
        PersistentEObject object = PersistentEObject.from(internalObject);
        NeoLogger.debug("DirectWriteMapStore::indexOf({})");

        Object[] array = (Object[]) getFromMap(object, feature);
        if (isNull(array)) {
            index = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            index = ArrayUtils.indexOf(array, serialize((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            index = ArrayUtils.indexOf(array, childEObject.id());
        }
        return index;
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(value);

        int index;
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object[] array = (Object[]) getFromMap(object, feature);
        if (isNull(array)) {
            index = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            index = ArrayUtils.lastIndexOf(array, serialize((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            index = ArrayUtils.lastIndexOf(array, childEObject.id());
        }
        return index;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.valueFor(featureKey, new Object[]{});
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object value = getFromMap(object, feature);
        if (feature.isMany()) {
            int valueLength = ((Object[]) value).length;
            return internalToArray(value, feature, new Object[valueLength]);
        }
        else {
            return internalToArray(value, feature, new Object[1]);
        }
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object value = getFromMap(object, feature);
        return internalToArray(value, feature, array);
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        checkNotNull(object);
        checkNotNull(attribute);

        Object soughtAttribute = getFromMap(object, attribute);
        if (attribute.isMany()) {
            Object[] array = (Object[]) soughtAttribute;
            checkPositionIndex(index, array.length, "Invalid get index " + index);
            soughtAttribute = deserialize(attribute, array[index]);
        }
        else {
            soughtAttribute = deserialize(attribute, soughtAttribute);
        }
        return soughtAttribute;
    }

    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        checkNotNull(object);
        checkNotNull(reference);

        PersistentEObject result;
        Object value = getFromMap(object, reference);
        if (isNull(value)) {
            result = null;
        }
        else {
            if (reference.isMany()) {
                Object[] array = (Object[]) value;
                checkPositionIndex(index, array.length, "Invalid get index " + index);
                result = eObject((Id) array[index]);
            }
            else {
                result = eObject((Id) value);
            }
        }
        return result;
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        checkNotNull(object);
        checkNotNull(attribute);
        checkNotNull(value);

        Object old;
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        if (!attribute.isMany()) {
            old = backend.valueFor(featureKey, serialize(attribute, value)).orElse(null);
            old = deserialize(attribute, old);
        }
        else {
            Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            old = array[index];
            array[index] = serialize(attribute, value);
            backend.valueFor(featureKey, array);
            old = deserialize(attribute, old);
        }
        return old;
    }

    @Override
    protected PersistentEObject setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        checkNotNull(object);
        checkNotNull(reference);
        checkNotNull(value);

        PersistentEObject old;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        updateContainment(object, reference, value);
        updateInstanceOf(value);
        if (!reference.isMany()) {
            Optional<Object> oldId = backend.valueFor(featureKey, value.id());
            old = oldId.isPresent() ? eObject((Id) oldId.get()) : null;
        }
        else {
            Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            Object oldId = array[index];
            array[index] = value.id();
            backend.valueFor(featureKey, array);
            old = isNull(oldId) ? null : eObject((Id) oldId);
        }
        return old;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        checkNotNull(object);
        checkNotNull(attribute);
        checkNotNull(value);

        FeatureKey featureKey = FeatureKey.from(object, attribute);
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            index = size(object, attribute);
        }
        Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index");
        array = ArrayUtils.add(array, index, serialize(attribute, value));
        backend.valueFor(featureKey, array);
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        checkNotNull(object);
        checkNotNull(reference);
        checkNotNull(value);

        FeatureKey featureKey = FeatureKey.from(object, reference);
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            index = size(object, reference);
        }
        updateContainment(object, reference, value);
        updateInstanceOf(value);
        Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index");
        array = ArrayUtils.add(array, index, value.id());
        backend.valueFor(featureKey, array);
        persistentObjectsCache.put(value.id(), value);
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        checkNotNull(object);
        checkNotNull(attribute);

        FeatureKey featureKey = FeatureKey.from(object, attribute);
        Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object old = array[index];
        array = ArrayUtils.remove(array, index);
        backend.valueFor(featureKey, array);
        return deserialize(attribute, old);
    }

    @Override
    protected PersistentEObject removeReference(PersistentEObject object, EReference reference, int index) {
        checkNotNull(object);
        checkNotNull(reference);

        FeatureKey featureKey = FeatureKey.from(object, reference);
        Object[] array = (Object[]) backend.valueOf(featureKey).orElse(null);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object oldId = array[index];
        array = ArrayUtils.remove(array, index);
        backend.valueFor(featureKey, array);
        return eObject((Id) oldId);
    }

    /**
     * Returns the value associated to the {@code featureKey} in the underlying database.
     *
     * @param featureKey the {@link FeatureKey} to look for
     *
     * @return the {@link Object} stored in the database if it exists, {@code null} otherwise. Note that the returned
     * {@link Object} can be a single element or a {@link Collection}.
     */
    public Object getFromMap(FeatureKey featureKey) {
        checkNotNull(featureKey);

        return backend.valueOf(featureKey).orElse(null);
    }

    /**
     * Returns the value associated to ({@code object}, {@code feature}) in the underlying database.
     * <p>
     * This method behaves like: {@code getFromMap(FeatureKey.from(object, feature)}.
     *
     * @param object  the {@link PersistentEObject} to look for
     * @param feature the {@link EStructuralFeature} of {@code object} to look for
     *
     * @return the {@link Object} stored in the database if it exists, {@code null} otherwise. Note that the returned
     * {@link Object} can be a single element or a {@link Collection}.
     */
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        checkNotNull(object);
        checkNotNull(feature);

        return backend.valueOf(FeatureKey.from(object, feature)).orElse(null);
    }

    /**
     * Reifies the element(s) in {@code value} and put them into {@code output}.
     *
     * @param value   the backend record to reify
     * @param feature the {@link EStructuralFeature} used to reify {@code value}
     * @param output  the array to fill
     *
     * @return {@code output} filled with the reified values
     */
    @SuppressWarnings("unchecked")
    private <T> T[] internalToArray(Object value, EStructuralFeature feature, T[] output) {
        if (feature.isMany()) {
            Object[] storedArray = (Object[]) value;
            if (feature instanceof EReference) {
                for (int i = 0; i < storedArray.length; i++) {
                    output[i] = (T) eObject((Id) storedArray[i]);
                }
            }
            else { // EAttribute
                for (int i = 0; i < storedArray.length; i++) {
                    output[i] = (T) deserialize((EAttribute) feature, storedArray[i]);
                }
            }
        }
        else {
            if (feature instanceof EReference) {
                output[0] = (T) eObject((Id) value);
            }
            else { // EAttribute
                output[0] = (T) deserialize((EAttribute) feature, value);
            }
        }
        return output;
    }
}
