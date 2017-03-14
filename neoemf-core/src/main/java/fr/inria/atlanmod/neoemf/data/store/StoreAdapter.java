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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdResolver;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.Iterables;
import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkElementIndex;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Store} used as bridge between a {@link org.eclipse.emf.ecore.InternalEObject.EStore} and a {@link Store}.
 */
@ParametersAreNonnullByDefault
public final class StoreAdapter extends AbstractStoreDecorator implements InternalEObject.EStore, Store, IdResolver {

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    @Nonnull
    private final Cache<Id, PersistentEObject> cache = CacheBuilder.newBuilder()
            .softValues()
            .maximumSize()
            .build(id -> resolveInstanceOf(id)
                    .map(c -> PersistenceFactory.getInstance().create(c, id).isPersistent(true))
                    .<NoSuchElementException>orElseThrow(() ->
                            new NoSuchElementException(String.format("%s does not have an associated metaclass", id))));

    /**
     * Constructs a new {@code StoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    private StoreAdapter(Store store) {
        super(store);
    }

    /**
     * Adapts the given {@code store} as a {@code StoreAdapter}.
     *
     * @param store the store to adapt
     *
     * @return the adapted {@code store}
     */
    public static StoreAdapter adapt(Store store) {
        return checkNotNull(store) instanceof StoreAdapter ? (StoreAdapter) store : new StoreAdapter(store);
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
    private static Object deserialize(EAttribute attribute, String property) {
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
    private static String serialize(EAttribute attribute, Object value) {
        return EcoreUtil.convertToString(attribute.getEAttributeType(), value);
    }

    @Override
    public final Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        if (feature.isMany()) {
            checkElementIndex(index, size(internalObject, feature));
        }

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return null;
        }

        if (isAttribute(feature)) {
            Optional<String> value;
            if (!feature.isMany()) {
                value = valueOf(key);
            }
            else {
                value = valueOf(key.withPosition(index));
            }

            return value
                    .map(v -> deserialize((EAttribute) feature, v))
                    .orElse(null);
        }
        else {
            Optional<Id> value;
            if (!feature.isMany()) {
                value = referenceOf(key);
            }
            else {
                value = referenceOf(key.withPosition(index));
            }

            return value
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Override
    public final Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        if (isNull(value)) {
            Object previousValue = get(internalObject, feature, index);
            unset(internalObject, feature);
            return previousValue;
        }

        if (feature.isMany()) {
            checkElementIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            persist(object);

            Optional<String> previousValue;
            if (!feature.isMany()) {
                previousValue = valueFor(key, serialize((EAttribute) feature, value));
            }
            else {
                previousValue = valueFor(key.withPosition(index), serialize((EAttribute) feature, value));
            }

            return previousValue
                    .map(v -> deserialize((EAttribute) feature, v))
                    .orElse(null);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            persist(object, (EReference) feature, referencedObject);

            Optional<Id> previousId;
            if (!feature.isMany()) {
                previousId = referenceFor(key, referencedObject.id());
            }
            else {
                previousId = referenceFor(key.withPosition(index), referencedObject.id());
            }

            return previousId
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Override
    public final boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return false;
        }

        if (isAttribute(feature)) {
            if (!feature.isMany()) {
                return hasValue(key);
            }
            else {
                return hasAnyValue(key);
            }
        }
        else {
            if (!feature.isMany()) {
                return hasReference(key);
            }
            else {
                return hasAnyReference(key);
            }
        }
    }

    @Override
    public final void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return;
        }

        if (isAttribute(feature)) {
            if (!feature.isMany()) {
                unsetValue(key);
            }
            else {
                removeAllValues(key);
            }
        }
        else {
            if (!feature.isMany()) {
                unsetReference(key);
            }
            else {
                removeAllReferences(key);
            }
        }
    }

    @Override
    public final boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute isEmpty() of a single-valued feature");

        return size(internalObject, feature) == 0;
    }

    @Override
    public final int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return 0;
        }

        OptionalInt size;
        if (isAttribute(feature)) {
            size = sizeOfValue(key);
        }
        else {
            size = sizeOfReference(key);
        }
        return size.orElse(0);
    }

    @Override
    public final boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute contains() of a single-valued feature");

        if (isNull(value)) {
            return false;
        }

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return false;
        }

        if (isAttribute(feature)) {
            return containsValue(key, serialize((EAttribute) feature, value));
        }
        else {
            return containsReference(key, PersistentEObject.from(value).id());
        }
    }

    @Override
    public final int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return NO_INDEX;
        }

        OptionalInt index;
        if (isAttribute(feature)) {
            index = indexOfValue(key, serialize((EAttribute) feature, value));
        }
        else {
            index = indexOfReference(key, PersistentEObject.from(value).id());
        }
        return index.orElse(NO_INDEX);
    }

    @Override
    public final int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return NO_INDEX;
        }

        OptionalInt index;
        if (isAttribute(feature)) {
            index = lastIndexOfValue(key, serialize((EAttribute) feature, value));
        }
        else {
            index = lastIndexOfReference(key, PersistentEObject.from(value).id());
        }
        return index.orElse(NO_INDEX);
    }

    @Override
    public final void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(value);
        checkArgument(feature.isMany(), "Cannot compute add() of a single-valued feature");

        if (index != NO_INDEX) {
            checkPositionIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            persist(object);

            if (index == NO_INDEX) {
                appendValue(key, serialize((EAttribute) feature, value));
            }
            else {
                addValue(key.withPosition(index), serialize((EAttribute) feature, value));
            }
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            persist(object, (EReference) feature, referencedObject);

            if (index == NO_INDEX) {
                appendReference(key, referencedObject.id());
            }
            else {
                addReference(key.withPosition(index), referencedObject.id());
            }
        }
    }

    @Override
    public final Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute remove() of a single-valued feature");

        checkElementIndex(index, size(internalObject, feature));

        ManyFeatureKey key = ManyFeatureKey.from(internalObject, feature, index);

        if (!exists(key.id())) {
            return null;
        }

        if (isAttribute(feature)) {
            return this.<String>removeValue(key)
                    .map(v -> deserialize((EAttribute) feature, v))
                    .orElse(null);
        }
        else {
            PersistentEObject removedElement = removeReference(key)
                    .map(this::resolve)
                    .orElse(null);

            if (((EReference) feature).isContainment()) {
                removedElement.eBasicSetContainer(null, -1, null);
                removedElement.resource(null);
            }

            return removedElement;
        }
    }

    @Override
    public final Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute move() of a single-valued feature");

        Object movedElement = remove(internalObject, feature, sourceIndex);
        add(internalObject, feature, targetIndex, movedElement);
        return movedElement;
    }

    @Override
    public final void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute clear() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return;
        }

        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            removeAllValues(key);
        }
        else {
            removeAllReferences(key);
        }
    }

    @Override
    public final Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        return toArray(internalObject, feature, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, @Nullable T[] array) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        FeatureKey key = FeatureKey.from(internalObject, feature);

        if (!exists(key.id())) {
            return nonNull(array) ? array : (T[]) new Object[0];
        }

        Stream<Object> stream;

        if (isAttribute(feature)) {
            Iterable<String> values;

            if (feature.isMany()) {
                values = allValuesOf(key);
            }
            else {
                values = this.<String>valueOf(key)
                        .map(Collections::singleton)
                        .orElseGet(Collections::emptySet);
            }

            stream = Iterables.stream(values)
                    .map(v -> deserialize((EAttribute) feature, v));
        }
        else {
            Iterable<Id> references;

            if (feature.isMany()) {
                references = allReferencesOf(key);
            }
            else {
                references = referenceOf(key)
                        .map(Collections::singleton)
                        .orElseGet(Collections::emptySet);
            }

            stream = Iterables.stream(references)
                    .map(this::resolve);
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
    public final int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return Arrays.hashCode(toArray(internalObject, feature));
    }

    @Override
    public final PersistentEObject getContainer(InternalEObject internalObject) {
        checkNotNull(internalObject);

        return containerOf(PersistentEObject.from(internalObject).id())
                .map(c -> resolve(c.id()))
                .orElse(null);
    }

    @Override
    public final EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject);

        return containerOf(PersistentEObject.from(internalObject).id())
                .map(c -> resolve(c.id()).eClass().getEStructuralFeature(c.name()))
                .orElse(null);
    }

    @Override
    public EObject create(EClass eClass) {
        throw new IllegalStateException("EStore#create() should not be called");
    }

    /**
     * Creates the given {@code object} in the underlying database if it doesn't already exist.
     *
     * @param object the object to persist
     *
     * @see #updateInstanceOf(PersistentEObject)
     */
    private void persist(PersistentEObject object) {
        if (!exists(object.id())) {
            updateInstanceOf(object);
            cache.put(object.id(), object);
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
    private void persist(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        persist(object);
        persist(referencedObject);

        updateContainment(object, reference, referencedObject);
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
    private void updateContainment(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        if (reference.isContainment()) {
            Optional<ContainerDescriptor> container = containerOf(referencedObject.id());

            if (!container.isPresent() || !Objects.equals(container.get().id(), object.id())) {
                containerFor(referencedObject.id(), ContainerDescriptor.from(object, reference));
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
    @Nonnull
    private Optional<EClass> resolveInstanceOf(Id id) {
        Optional<EClass> instanceOf = metaclassOf(id).map(ClassDescriptor::get);

        if (!instanceOf.isPresent()) {
            throw new NoSuchElementException("Element " + id + " does not have an associated EClass");
        }

        return instanceOf;
    }

    /**
     * Computes the type of the {@code object} in a {@link ClassDescriptor} object and persists it in the database.
     * <p>
     * <b>Note:</b> The type is not updated if {@code object} was previously mapped to another type.
     *
     * @param object the {@link PersistentEObject} to store the instance-of information from
     */
    private void updateInstanceOf(PersistentEObject object) {
        Optional<ClassDescriptor> metaclass = metaclassOf(object.id());

        if (!metaclass.isPresent()) {
            metaclassFor(object.id(), ClassDescriptor.from(object));
        }
    }

    @Nonnull
    @Override
    public final PersistentEObject resolve(Id id) {
        checkNotNull(id);

        PersistentEObject object = cache.get(id);

        if (nonNull(resource()) && object.resource() != resource()) {
            object.resource(resource());
        }

        return object;
    }
}
