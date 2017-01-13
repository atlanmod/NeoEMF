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

package fr.inria.atlanmod.neoemf.data.mapdb.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An {@link AbstractDirectWriteStore} subclass that translates model-level operations
 * to MapDB operations.
 * <p>
 * This class implements the {@link PersistentStore} interface that defines a set of operations to implement in order
 * to allow EMF persistence delegation. If this store is used, every method call and property access on
 * {@link PersistentEObject} is forwarded to this class, that takes care of the database serialization and deserialization
 * using its embedded {@link MapDbPersistenceBackend}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 * 
 * @see PersistentEObject
 * @see MapDbPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteMapDbStore extends AbstractDirectWriteStore<MapDbPersistenceBackend> {

    /**
     * An in-memory cache for persistent EObjects. This cache reduces database accesses and
     * speed-up element retrieval. The cache can contains up to 10000 model elements, that are
     * discarded if more elements have to be cached.
     */
    protected final Cache<Id, PersistentEObject> persistentObjectsCache;

    /**
     * Constructs a new {@code DirectWriteMapDbStore} between the given {@code resource} and
     * the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend the persistence backend used to store the model
     */
    public DirectWriteMapDbStore(Resource.Internal resource, MapDbPersistenceBackend backend) {
        super(resource, backend);
        this.persistentObjectsCache = Caffeine.newBuilder().maximumSize(10000).build();
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return backend.isFeatureSet(featureKey);
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.removeFeature(featureKey);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return indexOf(internalObject, feature, value) != PersistentStore.NO_INDEX;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        int index;
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object[] array = (Object[]) getFromMap(object, feature);
        if (isNull(array)) {
            index = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            index = ArrayUtils.indexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            index = ArrayUtils.indexOf(array, childEObject.id());
        }
        return index;
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        int index;
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object[] array = (Object[]) getFromMap(object, feature);
        if (isNull(array)) {
            index = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            index = ArrayUtils.lastIndexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            index = ArrayUtils.lastIndexOf(array, childEObject.id());
        }
        return index;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.storeValue(featureKey, new Object[]{});
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        Object soughtAttribute = getFromMap(object, attribute);
        if (attribute.isMany()) {
            Object[] array = (Object[]) soughtAttribute;
            checkPositionIndex(index, array.length, "Invalid get index " + index);
            soughtAttribute = parseProperty(attribute, array[index]);
        }
        else {
            soughtAttribute = parseProperty(attribute, soughtAttribute);
        }
        return soughtAttribute;
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        Object soughtReference;
        Object value = getFromMap(object, reference);
        if (reference.isMany()) {
            Object[] array = (Object[]) value;
            checkPositionIndex(index, array.length, "Invalid get index " + index);
            soughtReference = eObject((Id) array[index]);
        }
        else {
            soughtReference = eObject((Id) value);
        }
        return soughtReference;
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        Object old;
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        if (!attribute.isMany()) {
            old = backend.storeValue(featureKey, serializeToProperty(attribute, value));
            old = parseProperty(attribute, old);
        }
        else {
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
        Object old;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        updateContainment(object, reference, value);
        updateInstanceOf(value);
        if (!reference.isMany()) {
            Object oldId = backend.storeValue(featureKey, value.id());
            old = isNull(oldId) ? null : eObject((Id) oldId);
        }
        else {
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
        FeatureKey featureKey = FeatureKey.from(object, reference);
        Object[] array = (Object[]) getFromMap(featureKey);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object oldId = array[index];
        array = ArrayUtils.remove(array, index);
        backend.storeValue(featureKey, array);
        return eObject((Id) oldId);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");
        PersistentEObject object = PersistentEObject.from(internalObject);
        Object[] array = (Object[]) getFromMap(object, feature);
        return isNull(array) ? 0 : array.length;
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
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
        PersistentEObject object = PersistentEObject.from(internalObject);
        ContainerInfo info = backend.containerFor(object.id());
        if (nonNull(info)) {
            EObject container = eObject(info.id());
            return container.eClass().getEStructuralFeature(info.name());
        }
        return null;
    }

    @Override
    public EObject eObject(Id id) {
        EClass eClass = resolveInstanceOf(id);
        PersistentEObject object = persistentObjectsCache.get(id, new PersistentEObjectCacheLoader(eClass));
        if (object.resource() != resource()) {
            object.resource(resource());
        }
        return object;
    }

    private EClass resolveInstanceOf(Id id) {
        EClass eClass = null;
        ClassInfo classInfo = backend.metaclassFor(id);
        if (nonNull(classInfo)) {
            eClass = classInfo.eClass();
        }
        return eClass;
    }

    /**
     * Tells the underlying database to put {@code referencedObject} in the containment list of {@code object}.
     * <p>
     * Containment and containers informations are persisted in a dedicated {@link Map}. The method 
     * checks if an existing container is stored and update it if needed.
     * @param object the container {@link PersistentEObject}
     * @param reference the containment {@link EReference}
     * @param referencedObject the {@link PersistentEObject} to add in the containment list of {@code object}
     */
    protected void updateContainment(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        if (reference.isContainment()) {
            ContainerInfo info = backend.containerFor(referencedObject.id());
            if (isNull(info) || !Objects.equals(info.id(), object.id())) {
                backend.storeContainer(referencedObject.id(), ContainerInfo.from(object, reference));
            }
        }
    }

    /**
     * Computes the type of {@code object} in a {@link ClassInfo} object and persists it in the database.
     * <p>
     * As for {@link DirectWriteMapDbStore#updateContainment(PersistentEObject, EReference, PersistentEObject)},
     * instance-of informations are handled in a dedicated {@link Map}, easing their access. The method 
     * checks that the {@link Map} doesn't contain another type information for {@code object} and save it.
     *
     * @note The type is not updated if {@code object} was previously mapped to another type.
     * @param object the {@link PersistentEObject} to store the instance-of information from
     */
    protected void updateInstanceOf(PersistentEObject object) {
        ClassInfo info = backend.metaclassFor(object.id());
        if (isNull(info)) {
            backend.storeMetaclass(object.id(), ClassInfo.from(object));
        }
    }

    /**
     * Get the value associated to {@code featureKey} in the underlying database.
     * @param featureKey the {@link FeatureKey} to look for
     * @return the {@link Object} stored in the database if it exists, {@code null} otherwise. Note that
     * the returned {@link Object} can be a single element or a {@link Collection}.
     */
    protected Object getFromMap(FeatureKey featureKey) {
        return backend.valueOf(featureKey);
    }

    /**
     * Get the value associated to ({@code object}, {@code feature}) in the underlying database.
     * <p>
     * This method is a wrapper for {@link DirectWriteMapDbStore#getFromMap(FeatureKey)}. A {@link FeatureKey}
     * is computed for the given {@code object} and {@code feature} using {@link FeatureKey#from(PersistentEObject, EStructuralFeature)}.
     * @param object the {@link PersistentEObject} to look for
     * @param feature the {@link EStructuralFeature} of {@code object} to look for
     * @return the {@link Object} stored in the database if it exists, {@code null} otherwise. Note that
     * the returned {@link Object} can be a single element or a {@link Collection}.
     * 
     * @see DirectWriteMapDbStore#getFromMap(FeatureKey)
     */
    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        return getFromMap(FeatureKey.from(object, feature));
    }

    private static class PersistentEObjectCacheLoader implements Function<Id, PersistentEObject> {

        private final EClass eClass;

        private PersistentEObjectCacheLoader(EClass eClass) {
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
                }
                else {
                    eObject = EcoreUtil.create(eClass);
                }
                object = PersistentEObject.from(eObject);
                object.id(id);
                object.setMapped(true);
            }
            else {
                throw new RuntimeException("Element " + id + " does not have an associated EClass");
            }
            return object;
        }
    }
}
