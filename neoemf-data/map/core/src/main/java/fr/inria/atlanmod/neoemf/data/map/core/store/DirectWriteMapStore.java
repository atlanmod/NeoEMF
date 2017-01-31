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
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link AbstractDirectWriteStore} that redirects certain methods according to the instance of the
 * encountered {@link EStructuralFeature}. If the subclass does not re-implement the inherited methods of EMF, the call
 * is automatically redirected to the associated method that begins with the same name.
 *
 * @param <P> the type of the supported {@link PersistenceBackend}
 */
public class DirectWriteMapStore<P extends MapBackend> extends AbstractDirectWriteStore<P> {

    /**
     * Constructs a new {@code DirectWriteMapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapStore(Resource.Internal resource, P backend) {
        super(resource, backend);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return backend.isFeatureSet(featureKey);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.removeFeature(featureKey);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.debug("DirectWriteMapStore::contains({1}, {2})", feature.getName(), value);
        checkNotNull(internalObject);
        checkNotNull(feature);

        return indexOf(internalObject, feature, value) != PersistentStore.NO_INDEX;
    }

    /**
     * {@inheritDoc}
     * <p>
     * As for {@link #updateContainment(PersistentEObject, EReference, PersistentEObject)}, instance-of information are
     * handled in a dedicated {@link Map}, easing their access. The method checks that the {@link Map} doesn't contain
     * another type information for {@code object} and save it.
     *
     * @param object the {@link PersistentEObject} to store the instance-of information from
     * @note The type is not updated if {@code object} was previously mapped to another type.
     */
    @Override
    protected void updateInstanceOf(PersistentEObject object) {
        checkNotNull(object);

        ClassInfo info = backend.metaclassFor(object.id());
        if (isNull(info)) {
            backend.storeMetaclass(object.id(), ClassInfo.from(object));
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Containment and containers information are persisted in a dedicated {@link Map}.
     *
     * @param object           the container {@link PersistentEObject}
     * @param reference        the containment {@link EReference}
     * @param referencedObject the {@link PersistentEObject} to add in the containment list of {@code object}
     */
    @Override
    protected void updateContainment(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        checkNotNull(object);
        checkNotNull(reference);
        checkNotNull(referencedObject);

        if (reference.isContainment()) {
            ContainerInfo info = backend.containerFor(referencedObject.id());
            if (isNull(info) || !Objects.equals(info.id(), object.id())) {
                backend.storeContainer(referencedObject.id(), ContainerInfo.from(object, reference));
            }
        }
    }

    /**
     * Returns the value associated to the {@code featureKey} in the underlying database.
     *
     * @param featureKey the {@link FeatureKey} to look for
     * @return the {@link Object} stored in the database if it exists, {@code null} otherwise. Note that the returned
     * {@link Object} can be a single element or a {@link Collection}.
     */
    public Object getFromMap(FeatureKey featureKey) {
        checkNotNull(featureKey);

        return backend.valueOf(featureKey);
    }

    /**
     * Returns the value associated to ({@code object}, {@code feature}) in the underlying database.
     * <p>
     * This method behaves like: {@code getFromMap(FeatureKey.from(object, feature)}.
     *
     * @param object  the {@link PersistentEObject} to look for
     * @param feature the {@link EStructuralFeature} of {@code object} to look for
     * @return the {@link Object} stored in the database if it exists, {@code null} otherwise. Note that the returned
     * {@link Object} can be a single element or a {@link Collection}.
     * @see #getFromMap(FeatureKey)
     */
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        checkNotNull(object);
        checkNotNull(feature);

        return getFromMap(FeatureKey.from(object, feature));
    }

    @Override
    public PersistentEObject eObject(Id id) {
        checkNotNull(id);

        PersistentEObject object = persistentObjectsCache.get(id);
        if (object.resource() != resource()) {
            object.resource(resource());
        }
        return object;
    }

    @Override
    protected EClass resolveInstanceOf(Id id) {
        checkNotNull(id);

        EClass eClass = null;
        ClassInfo classInfo = backend.metaclassFor(id);
        if (nonNull(classInfo)) {
            eClass = classInfo.eClass();
        }
        return eClass;
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
    public InternalEObject getContainer(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);
        ContainerInfo info = backend.containerFor(object.id());
        if (nonNull(info)) {
            return eObject(info.id());
        }
        return null;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);
        ContainerInfo info = backend.containerFor(object.id());
        if (nonNull(info)) {
            PersistentEObject container = eObject(info.id());
            return container.eClass().getEStructuralFeature(info.name());
        }
        return null;
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
        } else if (feature instanceof EAttribute) {
            index = ArrayUtils.indexOf(array, serializeToProperty((EAttribute) feature, value));
        } else {
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
        } else if (feature instanceof EAttribute) {
            index = ArrayUtils.lastIndexOf(array, serializeToProperty((EAttribute) feature, value));
        } else {
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
        backend.storeValue(featureKey, new Object[]{});
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation of
     * {@link AbstractDirectWriteStore#toArray(InternalEObject, EStructuralFeature)}
     * that takes benefit of the underlying backend to deserialize the entire
     * list once and return it as an array, avoiding multiple {@code get()}
     * operations.
     */
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
        } else {
            return internalToArray(value, feature, new Object[1]);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation of
     * {@link AbstractDirectWriteStore#toArray(InternalEObject, EStructuralFeature, Object[])}
     * that takes benefit of the underlying backend to deserialize the entire
     * list once and return it as an array, avoiding multiple {@code get()}
     * operations.
     * <p>
     * Returns the given {@code array} reference if it is not {@code null}.
     */
    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object value = getFromMap(object, feature);
        return internalToArray(value, feature, array);
    }

    /**
     * Reifies the element(s) in {@code value} and put them into {@code output}.
     * @param value the backend record to reify
     * @param feature the {@link EStructuralFeature} used to reify {@code value}
     * @param output the array to fill
     * @return {@code output} filled with the reified values
     */
    @SuppressWarnings("unchecked")
    private <T> T[] internalToArray(Object value, EStructuralFeature feature, T[] output) {
        if(feature.isMany()) {
            Object[] storedArray = (Object[])value;
            if(feature instanceof EReference) {
                for(int i = 0; i < storedArray.length; i++) {
                    output[i] = (T) eObject((Id)storedArray[i]);
                }
            }
            else { // EAttribute
                for(int i = 0; i < storedArray.length; i++) {
                    output[i] = (T) parseProperty((EAttribute)feature, storedArray[i]);
                }
            }
        }
        else {
            if(feature instanceof EReference) {
                output[0] = (T) eObject((Id)value);
            }
            else { // EAttribute
                output[0] = (T) parseProperty((EAttribute)feature, value);
            }
        }
        return output;
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        checkNotNull(object);
        checkNotNull(attribute);

        Object soughtAttribute = getFromMap(object, attribute);
        if (attribute.isMany()) {
            Object[] array = (Object[]) soughtAttribute;
            checkPositionIndex(index, array.length, "Invalid get index " + index);
            soughtAttribute = parseProperty(attribute, array[index]);
        } else {
            soughtAttribute = parseProperty(attribute, soughtAttribute);
        }
        return soughtAttribute;
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        checkNotNull(object);
        checkNotNull(reference);

        PersistentEObject result;
        Object value = getFromMap(object, reference);
        if (isNull(value)) {
            result = null;
        } else {
            if (reference.isMany()) {
                Object[] array = (Object[]) value;
                checkPositionIndex(index, array.length, "Invalid get index " + index);
                result = eObject((Id) array[index]);
            } else {
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
            old = backend.storeValue(featureKey, serializeToProperty(attribute, value));
            old = parseProperty(attribute, old);
        } else {
            Object[] array = (Object[]) getFromMap(featureKey);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            old = array[index];
            array[index] = serializeToProperty(attribute, value);
            backend.storeValue(featureKey, array);
            old = parseProperty(attribute, old);
        }
        return old;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        checkNotNull(object);
        checkNotNull(reference);
        checkNotNull(value);

        Object old;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        updateContainment(object, reference, value);
        updateInstanceOf(value);
        if (!reference.isMany()) {
            Object oldId = backend.storeValue(featureKey, value.id());
            old = isNull(oldId) ? null : eObject((Id) oldId);
        } else {
            Object[] array = (Object[]) getFromMap(featureKey);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            Object oldId = array[index];
            array[index] = value.id();
            backend.storeValue(featureKey, array);
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
        Object[] array = (Object[]) getFromMap(featureKey);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index");
        array = ArrayUtils.add(array, index, serializeToProperty(attribute, value));
        backend.storeValue(featureKey, array);
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
        Object[] array = (Object[]) getFromMap(featureKey);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index");
        array = ArrayUtils.add(array, index, value.id());
        backend.storeValue(featureKey, array);
        persistentObjectsCache.put(value.id(), value);
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        checkNotNull(object);
        checkNotNull(attribute);

        FeatureKey featureKey = FeatureKey.from(object, attribute);
        Object[] array = (Object[]) getFromMap(featureKey);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object old = array[index];
        array = ArrayUtils.remove(array, index);
        backend.storeValue(featureKey, array);
        return parseProperty(attribute, old);
    }

    @Override
    protected Object removeReference(PersistentEObject object, EReference reference, int index) {
        checkNotNull(object);
        checkNotNull(reference);

        FeatureKey featureKey = FeatureKey.from(object, reference);
        Object[] array = (Object[]) getFromMap(featureKey);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object oldId = array[index];
        array = ArrayUtils.remove(array, index);
        backend.storeValue(featureKey, array);
        return eObject((Id) oldId);
    }
}
