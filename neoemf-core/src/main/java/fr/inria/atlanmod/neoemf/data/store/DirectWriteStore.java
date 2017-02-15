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
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
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
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link PersistentStore} that translates model-level operations into datastore calls.
 */
@ParametersAreNonnullByDefault
public class DirectWriteStore extends AbstractPersistentStore implements PersistentStore {

    /**
     * The persistence back-end used to store the model.
     */
    @Nonnull
    protected final PersistenceBackend backend;

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    @Nonnull
    private final LoadingCache<Id, PersistentEObject> persistentObjectsCache = Caffeine.newBuilder()
            .softValues()
            .initialCapacity(1_000)
            .maximumSize(10_000)
            .build(new PersistentObjectLoader());

    /**
     * The resource to persist and access.
     */
    @Nullable
    private final PersistentResource resource;

    /**
     * Constructs a new {@code DirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteStore(@Nullable PersistentResource resource, PersistenceBackend backend) {
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
    protected static Object deserialize(EAttribute attribute, String property) {
        return EcoreUtil.createFromString(attribute.getEAttributeType(), property);
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
    protected static String serialize(EAttribute attribute, Object value) {
        return EcoreUtil.convertToString(attribute.getEAttributeType(), value);
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
        return StreamSupport.stream(backend.allInstances(metaclass, strict).spliterator(), false)
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

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return getAttribute(object, (EAttribute) feature, index);
        }
        else {
            return getReference(object, (EReference) feature, index);
        }
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

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

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return isSetAttribute(object, (EAttribute) feature);
        }
        else {
            return isSetReference(object, (EReference) feature);
        }
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

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
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkArgument(feature.isMany(), "Cannot compute isEmpty() of a single-valued feature");

        return size(internalObject, feature) == 0;
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        FeatureKey key = FeatureKey.from(internalObject, feature);

        OptionalInt size;
        if (isAttribute(feature)) {
            size = backend.sizeOfValue(key);
        }
        else {
            size = backend.sizeOfReference(key);
        }
        return size.orElse(0);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
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

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
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

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
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

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(value);
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

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkArgument(feature.isMany(), "Cannot compute remove() of a single-valued feature");
        checkElementIndex(index, size(internalObject, feature));

        PersistentEObject object = PersistentEObject.from(internalObject);
        if (isAttribute(feature)) {
            return removeAttribute(object, (EAttribute) feature, index);
        }
        else {
            EReference reference = (EReference) feature;
            PersistentEObject removedElement = removeReference(object, reference, index);

            if (reference.isContainment()) {
                removedElement.eBasicSetContainer(null, -1, null);
                removedElement.resource(null);
            }

            return removedElement;
        }
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkArgument(feature.isMany(), "Cannot compute move() of a single-valued feature");

        Object movedElement = remove(internalObject, feature, sourceIndex);
        add(internalObject, feature, targetIndex, movedElement);
        return movedElement;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);
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

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        return toArray(internalObject, feature, null);
    }

    @Override
    @SuppressWarnings("unchecked") // Unchecked cast 'Object' to 'T'
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, @Nullable T[] array) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey key = FeatureKey.from(internalObject, feature);

        Stream<Object> stream;
        if (feature instanceof EReference) {
            Iterable<Id> references;

            if (feature.isMany()) {
                references = backend.allReferencesOf(key);
            }
            else {
                references = backend.referenceOf(key).map(Collections::singleton).orElseGet(Collections::emptySet);
            }

            stream = StreamSupport.stream(references.spliterator(), false)
                    .map(this::eObject);
        }
        else {
            Iterable<String> values;

            if (feature.isMany()) {
                values = backend.allValuesOf(key);
            }
            else {
                values = backend.<String>valueOf(key).map(Collections::singleton).orElseGet(Collections::emptySet);
            }

            stream = StreamSupport.stream(values.spliterator(), false)
                    .map(v -> deserialize((EAttribute) feature, v));
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
        Optional<ContainerDescriptor> container = backend.containerOf(object.id());
        return container
                .map(containerValue -> eObject(containerValue.id()))
                .orElse(null);
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);

        return backend.containerOf(object.id())
                .map(containerValue -> eObject(containerValue.id()).eClass().getEStructuralFeature(containerValue.name()))
                .orElse(null);
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
            Optional<ContainerDescriptor> container = backend.containerOf(referencedObject.id());
            if (!container.isPresent() || !Objects.equals(container.get().id(), object.id())) {
                backend.containerFor(referencedObject.id(), ContainerDescriptor.from(object, reference));
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
    protected Optional<EClass> resolveInstanceOf(Id id) {
        checkNotNull(id);

        return backend.metaclassOf(id)
                .map(MetaclassDescriptor::eClass);
    }

    /**
     * Computes the type of the {@code object} in a {@link MetaclassDescriptor} object and persists it in the database.
     *
     * @param object the {@link PersistentEObject} to store the instance-of information from
     *
     * @note The type is not updated if {@code object} was previously mapped to another type.
     */
    protected void updateInstanceOf(PersistentEObject object) {
        checkNotNull(object);

        Optional<MetaclassDescriptor> metaclass = backend.metaclassOf(object.id());
        if (!metaclass.isPresent()) {
            backend.metaclassFor(object.id(), MetaclassDescriptor.from(object));
        }
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

        Optional<String> value;
        if (!attribute.isMany()) {
            value = backend.valueOf(key);
        }
        else {
            checkElementIndex(index, size(object, attribute));
            value = backend.valueOf(key.withPosition(index));
        }

        return value
                .map(v -> deserialize(attribute, v))
                .orElse(null);
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
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        FeatureKey key = FeatureKey.from(object, reference);

        Optional<Id> value;
        if (!reference.isMany()) {
            value = backend.referenceOf(key);
        }
        else {
            checkElementIndex(index, size(object, reference));
            value = backend.referenceOf(key.withPosition(index));
        }

        return value
                .map(this::eObject)
                .orElse(null);
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

        Optional<String> previousValue;
        if (!attribute.isMany()) {
            previousValue = backend.valueFor(key, serialize(attribute, value));
        }
        else {
            checkElementIndex(index, size(object, attribute));
            previousValue = backend.valueFor(key.withPosition(index), serialize(attribute, value));
        }

        return previousValue
                .map(v -> deserialize(attribute, v))
                .orElse(null);
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
    protected PersistentEObject setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        persist(object, reference, value);

        FeatureKey key = FeatureKey.from(object, reference);

        Optional<Id> previousId;
        if (!reference.isMany()) {
            previousId = backend.referenceFor(key, value.id());
        }
        else {
            checkElementIndex(index, size(object, reference));
            previousId = backend.referenceFor(key.withPosition(index), value.id());
        }

        return previousId
                .map(this::eObject)
                .orElse(null);
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
            return backend.hasAnyValue(key);
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
            return backend.hasAnyReference(key);
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
            backend.unsetAllValues(key);
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
            backend.unsetAllReferences(key);
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
        return backend.containsValue(key, serialize(attribute, value));
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
        return backend.indexOfValue(key, serialize(attribute, value)).orElse(NO_INDEX);
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
        return backend.indexOfReference(key, value.id()).orElse(NO_INDEX);
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
        return backend.lastIndexOfValue(key, serialize(attribute, value)).orElse(NO_INDEX);
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
        return backend.lastIndexOfReference(key, value.id()).orElse(NO_INDEX);
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

        MultiFeatureKey key = MultiFeatureKey.from(object, attribute, index);
        backend.addValue(key, serialize(attribute, serialize(attribute, value)));
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

        MultiFeatureKey key = MultiFeatureKey.from(object, reference, index);
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
        MultiFeatureKey key = MultiFeatureKey.from(object, attribute, index);

        return backend.<String>removeValue(key)
                .map(v -> deserialize(attribute, v))
                .orElse(null);
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
    protected PersistentEObject removeReference(PersistentEObject object, EReference reference, int index) {
        MultiFeatureKey key = MultiFeatureKey.from(object, reference, index);

        return backend.removeReference(key)
                .map(this::eObject)
                .orElse(null);
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
        backend.cleanValues(key);
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
        backend.cleanReferences(key);
    }

    /**
     * A {@link CacheLoader} to retrieve a {@link PersistentEObject} stored in the database.
     */
    private class PersistentObjectLoader implements CacheLoader<Id, PersistentEObject> {

        @SuppressWarnings("JavaDoc")
        private final PersistenceFactory persistenceFactory = PersistenceFactory.getInstance();

        @Override
        public PersistentEObject load(@Nonnull Id id) throws Exception {
            Optional<EClass> eClass = resolveInstanceOf(id);
            if (eClass.isPresent()) {
                EObject eObject;

                if (Objects.equals(eClass.get().getEPackage().getClass(), EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = persistenceFactory.create(eClass.get());
                }
                else {
                    eObject = EcoreUtil.create(eClass.get());
                }

                return create(eObject, id);
            }
            else {
                throw new RuntimeException("Element " + id + " does not have an associated EClass");
            }
        }

        /**
         * Creates an {@link PersistentEObject} from the given {@code eObject} and the {@code id}.
         *
         * @param eObject the {@link EObject} from which to create the {@link PersistentEObject}
         * @param id      the identifier of the created {@link PersistentEObject}
         *
         * @return a new {@link PersistentEObject}
         */
        private PersistentEObject create(EObject eObject, Id id) {
            PersistentEObject persistentObject = PersistentEObject.from(eObject);

            persistentObject.id(id);
            persistentObject.setMapped(true);

            return persistentObject;
        }
    }
}
