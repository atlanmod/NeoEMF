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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
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
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

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
public abstract class MapStore<P extends MapBackend> extends AbstractDirectWriteStore<P> {

    /**
     * The default cache size (10 000).
     */
    // TODO Find the more predictable maximum cache size
    protected static final int DEFAULT_CACHE_SIZE = 10000;

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    protected final Cache<Id, PersistentEObject> persistentObjectsCache = Caffeine.newBuilder()
            .maximumSize(DEFAULT_CACHE_SIZE).build();

    /**
     * Constructs a new {@code MapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public MapStore(Resource.Internal resource, P backend) {
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
        NeoLogger.debug("MapStore::contains({1}, {2})", new Object[] {
                feature.getName(), value});
        checkNotNull(internalObject);
        checkNotNull(feature);

        return indexOf(internalObject, feature, value) != PersistentStore.NO_INDEX;
    }

    /**
     * Computes the type of the {@code object} in a {@link ClassInfo} object and persists it in the database.
     * <p>
     * As for {@link #updateContainment(PersistentEObject, EReference, PersistentEObject)}, instance-of information are
     * handled in a dedicated {@link Map}, easing their access. The method checks that the {@link Map} doesn't contain
     * another type information for {@code object} and save it.
     *
     * @param object the {@link PersistentEObject} to store the instance-of information from
     * @note The type is not updated if {@code object} was previously mapped to another type.
     */
    protected void updateInstanceOf(PersistentEObject object) {
        checkNotNull(object);

        ClassInfo info = backend.metaclassFor(object.id());
        if (isNull(info)) {
            backend.storeMetaclass(object.id(), ClassInfo.from(object));
        }
    }

    /**
     * Tells the underlying database to put the {@code referencedObject} in the containment list of the {@code object}.
     * <p>
     * Containment and containers information are persisted in a dedicated {@link Map}. The method checks if an existing
     * container is stored and update it if needed.
     *
     * @param object           the container {@link PersistentEObject}
     * @param reference        the containment {@link EReference}
     * @param referencedObject the {@link PersistentEObject} to add in the containment list of {@code object}
     */
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
    public EObject eObject(Id id) {
        checkNotNull(id);

        PersistentEObject object = null;
        if (nonNull(id)) {
            EClass eClass = resolveInstanceOf(id);
            object = persistentObjectsCache.get(id, new PersistentEObjectCacheLoader(eClass));
            if (object.resource() != resource()) {
                object.resource(resource());
            }
        }
        return object;
    }

    /**
     * Compute the {@link EClass} associated to the model element with the provided {@link Id}.
     *
     * @param id the {@link Id} of the model element to compute the {@link EClass} from
     * @return an {@link EClass} representing the metaclass of the element
     */
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

        InternalEObject container = null;
        PersistentEObject object = PersistentEObject.from(internalObject);
        ContainerInfo info = backend.containerFor(object.id());
        if (nonNull(info)) {
            container = (InternalEObject) eObject(info.id());
        }
        return container;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);
        ContainerInfo info = backend.containerFor(object.id());
        if (nonNull(info)) {
            EObject container = eObject(info.id());
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
        NeoLogger.debug("MapStore::indexOf({})");

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

        Object result;
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

    /**
     * A cache loader to retrieve a {@link PersistentEObject} stored in the database.
     */
    protected static class PersistentEObjectCacheLoader implements Function<Id, PersistentEObject> {

        /**
         * The class associated with the object to retrieve.
         */
        private final EClass eClass;

        /**
         * Constructs a new {@code PersistentEObjectCacheLoader} with the given {@code eClass}.
         *
         * @param eClass the class associated with the object to retrieve
         */
        public PersistentEObjectCacheLoader(EClass eClass) {
            this.eClass = eClass;
        }

        @Override
        public PersistentEObject apply(Id id) {
            PersistentEObject object;
            if (nonNull(eClass)) {
                EObject eObject;
                if (Objects.equals(eClass.getEPackage().getClass(), EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.getInstance().create(eClass);
                } else {
                    eObject = EcoreUtil.create(eClass);
                }
                object = PersistentEObject.from(eObject);
                object.id(id);
                object.setMapped(true);
            } else {
                throw new RuntimeException("Element " + id + " does not have an associated EClass");
            }
            return object;
        }
    }
}
