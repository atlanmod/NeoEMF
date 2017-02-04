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

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link DirectWriteStore} that redirects certain methods according to the instance of the encountered
 * {@link EStructuralFeature}. If the subclass does not re-implement the inherited methods of EMF, the call is
 * automatically redirected to the associated method that begins with the same name.
 *
 * @param <P> the type of the supported {@link PersistenceBackend}
 */
public class DefaultDirectWriteStore<P extends PersistenceBackend> extends AbstractPersistentStore implements DirectWriteStore {

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    protected final LoadingCache<Id, PersistentEObject> persistentObjectsCache = Caffeine.newBuilder()
            .softValues()
            .initialCapacity(1_000)
            .maximumSize(10_000)
            .build(new PersistentObjectLoader());

    /**
     * The persistence back-end used to store the model.
     */
    protected final P backend;

    /**
     * The resource to persist and access.
     */
    private final PersistentResource resource;

    /**
     * Constructs a new {@code DefaultDirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DefaultDirectWriteStore(PersistentResource resource, P backend) {
        this.resource = resource;
        this.backend = backend;
    }

    /**
     * Checks whether the {@code feature} is an {@link EAttribute}.
     *
     * @param feature the feature for which to check the type
     *
     * @return {@code true} if the {@code feature} is an {@link EAttribute}, {@code false} is it is a {@link EReference}
     */
    private static boolean isAttribute(EStructuralFeature feature) {
        return EAttribute.class.isInstance(feature);
    }

    @Override
    public void save() {
        backend.save();
    }

    @Override
    public PersistentResource resource() {
        return resource;
    }

    @Override
    public PersistentEObject eObject(Id id) {
        if (isNull(id)) {
            return null;
        }

        PersistentEObject object = persistentObjectsCache.get(id);
        if (object.resource() != resource()) {
            // TODO Check if it's not the 'ROOT' before setting the resource
            object.resource(resource());
        }
        return object;
    }

    /**
     * Computes efficiently {@code allInstances} operation by using underlying graph facilities. This method uses
     * database indices to avoid costly traversal of the entire model.
     *
     * @param metaclass the {@link EClass} to get the instances of
     * @param strict    set to {@code true} if the method should look for instances of {@code eClass} only, set to
     *                  {@code false} if the method should also return elements that are subclasses of {@code eClass}
     */
    @Override
    public Iterable<EObject> getAllInstances(EClass metaclass, boolean strict) {
        return StreamSupport.stream(backend.getAllInstances(metaclass, strict).spliterator(), false)
                .map(this::eObject)
                .collect(Collectors.toList());
    }

    /**
     * Creates the given {@code object} in the underlying database if it doesn't already exist.
     *
     * @param object the object to persist
     *
     * @see #updateInstanceOf(PersistentEObject)
     */
    protected void persist(PersistentEObject object) {
        if (!backend.has(object.id())) {
            backend.create(object.id());
            persistentObjectsCache.put(object.id(), object);
            updateInstanceOf(object);
        }
    }

    /**
     * Creates the given {@code object} and {@code referencedObject} in the underlying back-end if they don't already
     * exist, and update the containment link between them.
     *
     * @param object           the object to persist
     * @param reference        the reference to link the two objects
     * @param referencedObject the referenced object to persist
     *
     * @see #persist(PersistentEObject)
     * @see #updateContainment(PersistentEObject, EReference, PersistentEObject)
     */
    protected void persist(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        persist(object);
        persist(referencedObject);
        updateContainment(object, reference, referencedObject);
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #getAttribute(PersistentEObject, EAttribute, int)
     * @see #getReference(PersistentEObject, EReference, int)
     */
    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return getAttribute(object, (EAttribute) feature, index);
        }
        else {
            return getReference(object, (EReference) feature, index);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #setAttribute(PersistentEObject, EAttribute, int, Object)
     * @see #setReference(PersistentEObject, EReference, int, PersistentEObject)
     */
    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        if (isNull(value)) {
            Object previousValue = get(internalObject, feature, index);
            unset(internalObject, feature);
            return previousValue;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return setAttribute(object, (EAttribute) feature, index, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return setReference(object, (EReference) feature, index, referencedObject);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #isSetAttribute(PersistentEObject, EAttribute)
     * @see #isSetReference(PersistentEObject, EReference)
     */
    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return isSetAttribute(object, (EAttribute) feature);
        }
        else {
            return isSetReference(object, (EReference) feature);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #unsetAttribute(PersistentEObject, EAttribute)
     * @see #unsetReference(PersistentEObject, EReference)
     */
    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            unsetAttribute(object, (EAttribute) feature);
        }
        else {
            unsetReference(object, (EReference) feature);
        }
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute isEmpty() of a single-valued feature");

        return size(internalObject, feature) == 0;
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        FeatureKey key = FeatureKey.from(internalObject, feature);
        return backend.sizeOf(key);
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #containsAttribute(PersistentEObject, EAttribute, Object)
     * @see #containsReference(PersistentEObject, EReference, PersistentEObject)
     */
    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkArgument(feature.isMany(), "Cannot compute contains() of a single-valued feature");

        if (isNull(value)) {
            return false;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!backend.has(object.id())) {
            return false;
        }

        if (isAttribute(feature)) {
            return containsAttribute(object, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return containsReference(object, (EReference) feature, referencedObject);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #indexOfAttribute(PersistentEObject, EAttribute, Object)
     * @see #indexOfReference(PersistentEObject, EReference, PersistentEObject)
     */
    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkArgument(feature.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return indexOfAttribute(object, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return indexOfReference(object, (EReference) feature, referencedObject);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #lastIndexOfAttribute(PersistentEObject, EAttribute, Object)
     * @see #lastIndexOfReference(PersistentEObject, EReference, PersistentEObject)
     */
    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkArgument(feature.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return lastIndexOfAttribute(object, (EAttribute) feature, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            return lastIndexOfReference(object, (EReference) feature, referencedObject);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #addAttribute(PersistentEObject, EAttribute, int, Object)
     * @see #addReference(PersistentEObject, EReference, int, PersistentEObject)
     */
    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkArgument(feature.isMany(), "Cannot compute add() of a single-valued feature");

        if (index == NO_INDEX) {
            index = size(internalObject, feature);
        }
        else {
            checkPositionIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            addAttribute(object, (EAttribute) feature, index, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            addReference(object, (EReference) feature, index, referencedObject);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #removeAttribute(PersistentEObject, EAttribute, int)
     * @see #removeReference(PersistentEObject, EReference, int)
     */
    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkArgument(feature.isMany(), "Cannot compute remove() of a single-valued feature");
        checkElementIndex(index, size(internalObject, feature));

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return removeAttribute(object, (EAttribute) feature, index);
        }
        else {
            return removeReference(object, (EReference) feature, index);
        }
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        checkArgument(feature.isMany(), "Cannot compute move() of a single-valued feature");

        Object movedElement = remove(internalObject, feature, sourceIndex);
        add(internalObject, feature, targetIndex, movedElement);
        return movedElement;
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default, calls the associated methods depending on the type of the {@code feature}.
     *
     * @see #clearAttribute(PersistentEObject, EAttribute)
     * @see #clearReference(PersistentEObject, EReference)
     */
    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute clear() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!backend.has(object.id())) {
            return;
        }

        if (isAttribute(feature)) {
            clearAttribute(object, (EAttribute) feature);
        }
        else {
            clearReference(object, (EReference) feature);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation that takes benefit of the underlying backend to get all the linked
     * elements once and return it as an array, avoiding multiple {@code get()} operations.
     */
    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        return toArray(internalObject, feature, null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation that takes benefit of the underlying backend to get all the linked
     * elements once and return it as an array, avoiding multiple {@code get()} operations.
     */
    @Override
    @SuppressWarnings("unchecked") // Unchecked cast 'Object' to 'T'
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        Stream<Object> stream;
        if (feature instanceof EReference) {
            Iterable<Id> references;

            if (feature.isMany()) {
                references = backend.referenceAtIndexAsList(key);
            }
            else {
                references = backend.referenceAsList(key);
            }

            stream = StreamSupport.stream(references.spliterator(), false)
                    .map(this::eObject);
        }
        else {
            Iterable<Object> values;

            if (feature.isMany()) {
                values = backend.valueAtIndexAsList(key);
            }
            else {
                values = backend.valueAsList(key);
            }

            stream = StreamSupport.stream(values.spliterator(), false)
                    .map(v -> parseProperty((EAttribute) feature, v));
        }

        if (isNull(array)) {
            return (T[]) stream.toArray();
        }
        else {
            array = stream.collect(Collectors.toList()).toArray(array);
            return array;
        }
    }

    @Override
    public int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return Arrays.hashCode(toArray(internalObject, feature));
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

    /**
     * Creates a new container edge between {@code object} and {@code referencedObject}, and deletes any
     * container edge previously linked to {@code referencedObject}. Tells the underlying database to put the
     * {@code referencedObject} in the containment list of the {@code object}.
     * <p>
     * The method checks if an existing container is stored and update it if needed.
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
     * Compute the {@link EClass} associated to the model element with the provided {@link Id}.
     *
     * @param id the {@link Id} of the model element to compute the {@link EClass} from
     *
     * @return an {@link EClass} representing the metaclass of the element
     */
    protected EClass resolveInstanceOf(Id id) {
        checkNotNull(id);

        ClassInfo classInfo = backend.metaclassFor(id);
        if (nonNull(classInfo)) {
            return classInfo.eClass();
        }
        return null;
    }

    /**
     * Computes the type of the {@code object} in a {@link ClassInfo} object and persists it in the database.
     *
     * @param object the {@link PersistentEObject} to store the instance-of information from
     *
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
     * Creates an instance of the {@code attribute}.
     *
     * @param attribute the attribute to instantiate
     * @param property  the string value of the attribute
     *
     * @return an instance of the attribute
     *
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    protected Object parseProperty(EAttribute attribute, Object property) {
        return isNull(property) ? null : EcoreUtil.createFromString(attribute.getEAttributeType(), property.toString());
    }

    /**
     * Converts an instance of the {@code attribute} to a string literal representation.
     *
     * @param attribute the attribute to instantiate
     * @param value     a value of the attribute
     *
     * @return the string literal representation of the value
     *
     * @see EcoreUtil#convertToString(EDataType, Object)
     */
    protected Object serializeToProperty(EAttribute attribute, Object value) {
        return isNull(value) ? null : EcoreUtil.convertToString(attribute.getEAttributeType(), value);
    }

    /**
     * Returns the value at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     *
     * @return the value
     *
     * @see #get(InternalEObject, EStructuralFeature, int)
     */
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        FeatureKey key = FeatureKey.from(object, attribute);

        Object value;
        if (!attribute.isMany()) {
            value = backend.getValue(key);
        }
        else {
            checkElementIndex(index, size(object, attribute));
            value = backend.getValueAtIndex(key.withPosition(index));
        }
        return parseProperty(attribute, value);
    }

    /**
     * Returns the value at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     *
     * @return the value
     *
     * @see #get(InternalEObject, EStructuralFeature, int)
     */
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        FeatureKey key = FeatureKey.from(object, reference);

        Id value;
        if (!reference.isMany()) {
            value = backend.getReference(key);
        }
        else {
            checkElementIndex(index, size(object, reference));
            value = backend.getReferenceAtIndex(key.withPosition(index));
        }

        return eObject(value);
    }

    /**
     * Sets the value at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     * @param value     the new value
     *
     * @return the previous value
     *
     * @see #set(InternalEObject, EStructuralFeature, int, Object)
     */
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        persist(object);

        FeatureKey key = FeatureKey.from(object, attribute);

        Object previousValue;
        if (!attribute.isMany()) {
            previousValue = backend.setValue(key, value);
        }
        else {
            checkElementIndex(index, size(object, attribute));
            previousValue = backend.setValueAtIndex(key.withPosition(index), value);
        }
        return parseProperty(attribute, previousValue);
    }

    /**
     * Sets the value at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     * @param index     an index within the content or {@link PersistentStore#NO_INDEX}
     * @param value     the new value
     *
     * @return the previous value
     *
     * @see #set(InternalEObject, EStructuralFeature, int, Object)
     */
    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        persist(object, reference, value);

        FeatureKey key = FeatureKey.from(object, reference);

        Id previousId;
        if (!reference.isMany()) {
            previousId = backend.setReference(key, value.id());
        }
        else {
            checkElementIndex(index, size(object, reference));
            previousId = backend.setReferenceAtIndex(key.withPosition(index), value.id());
        }
        return eObject(previousId);
    }

    /**
     * Returns whether the {@code attribute} of the {@code object} is considered set.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     *
     * @return {@code true} if the attribute is considered set
     *
     * @see #isSet(InternalEObject, EStructuralFeature)
     */
    protected boolean isSetAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey key = FeatureKey.from(object, attribute);

        if (!attribute.isMany()) {
            return backend.hasValue(key);
        }
        else {
            return backend.hasValueAtIndex(key);
        }
    }

    /**
     * Returns whether the {@code reference} of the {@code object} is considered set.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     *
     * @return {@code true} if the reference is considered set
     *
     * @see #isSet(InternalEObject, EStructuralFeature)
     */
    protected boolean isSetReference(PersistentEObject object, EReference reference) {
        FeatureKey key = FeatureKey.from(object, reference);

        if (!reference.isMany()) {
            return backend.hasReference(key);
        }
        else {
            return backend.hasReferenceAtIndex(key);
        }
    }

    /**
     * Unsets the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute an attribute of the {@code object}
     *
     * @see #unset(InternalEObject, EStructuralFeature)
     */
    protected void unsetAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey key = FeatureKey.from(object, attribute);

        if (!attribute.isMany()) {
            backend.unsetValue(key);
        }
        else {
            backend.unsetValueAtIndex(key);
        }
    }

    /**
     * Unsets the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a reference of the {@code object}
     *
     * @see #unset(InternalEObject, EStructuralFeature)
     */
    protected void unsetReference(PersistentEObject object, EReference reference) {
        FeatureKey key = FeatureKey.from(object, reference);

        if (!reference.isMany()) {
            backend.unsetReference(key);
        }
        else {
            backend.unsetReferenceAtIndex(key);
        }
    }

    /**
     * Returns whether the content of the {@code attribute} of the {@code object} contains the given {@code value}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param value     the value to look for
     *
     * @return {@code true} if the attribute contains the given value
     *
     * @see #contains(InternalEObject, EStructuralFeature, Object)
     */
    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        FeatureKey key = FeatureKey.from(object, attribute);
        return backend.containsValue(key, value);
    }

    /**
     * Returns whether the content of the {@code reference} of the {@code object} contains the given {@code value}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param value     the value to look for
     *
     * @return {@code true} if the reference contains the given value
     *
     * @see #contains(InternalEObject, EStructuralFeature, Object)
     */
    protected boolean containsReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        FeatureKey key = FeatureKey.from(object, reference);
        return backend.containsReference(key, value.id());
    }

    /**
     * Returns the first index of the given {@code value} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param value     the value to look for
     *
     * @return the first index of the given value, or {@link PersistentStore#NO_INDEX} if it is not found
     *
     * @see #indexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int indexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        FeatureKey key = FeatureKey.from(object, attribute);
        return backend.indexOfValue(key, value);
    }

    /**
     * Returns the first index of the given {@code value} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param value     the value to look for
     *
     * @return the first index of the given value, or {@link PersistentStore#NO_INDEX} if it is not found
     *
     * @see #indexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int indexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        FeatureKey key = FeatureKey.from(object, reference);
        return backend.indexOfReference(key, value.id());
    }

    /**
     * Returns the last index of the given {@code value} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param value     the value to look for
     *
     * @return the last index of the given value, or {@link PersistentStore#NO_INDEX} if it is not found
     *
     * @see #lastIndexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        FeatureKey key = FeatureKey.from(object, attribute);
        return backend.lastIndexOfValue(key, value);
    }

    /**
     * Returns the last index of the given {@code value} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param value     the value to look for
     *
     * @return the last index of the given value, or {@link PersistentStore#NO_INDEX} if it is not
     *
     * @see #lastIndexOf(InternalEObject, EStructuralFeature, Object)
     */
    protected int lastIndexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        FeatureKey key = FeatureKey.from(object, reference);
        return backend.lastIndexOfReference(key, value.id());
    }

    /**
     * Adds the {@code value} at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param index     an index within the content
     * @param value     the value to add
     *
     * @see #add(InternalEObject, EStructuralFeature, int, Object)
     */
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        persist(object);

        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, attribute, index);
        backend.addValue(key, serializeToProperty(attribute, value));
    }

    /**
     * Adds the {@code value} at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param index     an index within the content
     * @param value     the value to add
     *
     * @see #add(InternalEObject, EStructuralFeature, int, Object)
     */
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        persist(object, reference, value);

        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, reference, index);
        backend.addReference(key, value.id());
    }

    /**
     * Removes the value at the {@code index} in the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     * @param index     the index within the content of the value to remove
     *
     * @return the removed value
     *
     * @see #remove(InternalEObject, EStructuralFeature, int)
     */
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, attribute, index);
        return parseProperty(attribute, backend.removeValue(key));
    }

    /**
     * Removes the value at the {@code index} in the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     * @param index     the index within the content of the value to remove
     *
     * @return the removed value
     *
     * @see #remove(InternalEObject, EStructuralFeature, int)
     */
    protected Object removeReference(PersistentEObject object, EReference reference, int index) {
        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, reference, index);
        PersistentEObject previouslyReferencedObject = eObject(backend.removeReference(key));

        checkNotNull(previouslyReferencedObject);
        if (reference.isContainment()) {
            previouslyReferencedObject.eBasicSetContainer(null, -1, null);
            previouslyReferencedObject.resource(null);
        }

        return previouslyReferencedObject;
    }

    /**
     * Removes all values form the content of the {@code attribute} of the {@code object}.
     *
     * @param object    the object
     * @param attribute a many-valued attribute of the {@code object}
     *
     * @see #clear(InternalEObject, EStructuralFeature)
     */
    protected void clearAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey key = FeatureKey.from(object, attribute);
        backend.cleanValue(key);
    }

    /**
     * Removes all values form the content of the {@code reference} of the {@code object}.
     *
     * @param object    the object
     * @param reference a many-valued reference of the {@code object}
     *
     * @see #clear(InternalEObject, EStructuralFeature)
     */
    protected void clearReference(PersistentEObject object, EReference reference) {
        FeatureKey key = FeatureKey.from(object, reference);
        backend.cleanReference(key);
    }

    /**
     * A {@link CacheLoader} to retrieve a {@link PersistentEObject} stored in the database.
     */
    private class PersistentObjectLoader implements CacheLoader<Id, PersistentEObject> {

        @Override
        public PersistentEObject load(@Nonnull Id id) throws Exception {
            PersistentEObject persistentObject;

            EClass eClass = resolveInstanceOf(id);
            if (nonNull(eClass)) {
                EObject eObject;
                if (Objects.equals(eClass.getEPackage().getClass(), EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.getInstance().create(eClass);
                }
                else {
                    eObject = EcoreUtil.create(eClass);
                }
                persistentObject = PersistentEObject.from(eObject);
                persistentObject.id(id);
                persistentObject.setMapped(true);
            }
            else {
                throw new RuntimeException("Element " + id + " does not have an associated EClass");
            }

            return persistentObject;
        }
    }
}
