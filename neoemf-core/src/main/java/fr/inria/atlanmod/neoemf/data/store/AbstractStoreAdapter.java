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
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.MoreArrays;
import fr.inria.atlanmod.neoemf.util.cache.Cache;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkElementIndex;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The abstract implementation of a {@code StoreAdapter}.
 */
@Immutable
@ParametersAreNonnullByDefault
public abstract class AbstractStoreAdapter extends AbstractStoreDecorator implements StoreAdapter {

    /**
     * A set that holds all stores that need to be closed when the JVM will shutdown.
     */
    @Nonnull
    private static final Set<AbstractStoreAdapter> ADAPTERS = new HashSet<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Iterator<AbstractStoreAdapter> allAdapters = ADAPTERS.iterator();
            while (allAdapters.hasNext()) {
                allAdapters.next().safeClose();
                allAdapters.remove();
            }
        }));
    }

    /**
     * Constructs a new {@code AbstractStoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected AbstractStoreAdapter(Store store) {
        super(store);

        ADAPTERS.add(this);
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

    /**
     * Returns the in-memory cache holding recently loaded {@link PersistentEObject}s.
     *
     * @return the cache
     */
    @Nonnull
    protected abstract Cache<Id, PersistentEObject> cache();

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public void close() {
        if (ADAPTERS.remove(this)) {
            safeClose();
        }
    }

    @Override
    public boolean exists(Id id) {
        return nonNull(cache().get(id)) || super.exists(id);
    }

    /**
     * Cleanly closes this mapper, clear all data in-memory and releases any system resources associated with it. All
     * modifications are saved before closing.
     */
    public void safeClose() {
        try {
            next().close();
        }
        catch (RuntimeException ignored) {
        }
    }

    @Nullable
    @Override
    public final Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        if (feature.isMany()) {
            checkElementIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return null;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            Optional<String> value;
            if (!feature.isMany()) {
                value = valueOf(key);
            }
            else {
                value = valueOf(key.withPosition(index));
            }

            return value
                    .map(v -> deserialize(EAttribute.class.cast(feature), v))
                    .orElse(null);
        }
        else {
            Optional<Id> reference;
            if (!feature.isMany()) {
                reference = referenceOf(key);
            }
            else {
                reference = referenceOf(key.withPosition(index));
            }

            return reference
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Nullable
    @Override
    public final Object set(InternalEObject internalObject, EStructuralFeature feature, int index, @Nullable Object value) {
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
        updateInstanceOf(object);

        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            Optional<String> previousValue;
            if (!feature.isMany()) {
                previousValue = valueFor(key, serialize(EAttribute.class.cast(feature), value));
            }
            else {
                previousValue = valueFor(key.withPosition(index), serialize(EAttribute.class.cast(feature), value));
            }

            return previousValue
                    .map(v -> deserialize(EAttribute.class.cast(feature), v))
                    .orElse(null);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            Optional<Id> previousReference;
            if (!feature.isMany()) {
                previousReference = referenceFor(key, referencedObject.id());
            }
            else {
                previousReference = referenceFor(key.withPosition(index), referencedObject.id());
            }

            return previousReference
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Override
    public final boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return false;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

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

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

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

    @Nonnegative
    @Override
    public final int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return 0;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

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
    public final boolean contains(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute contains() of a single-valued feature");

        if (isNull(value)) {
            return false;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return false;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            return containsValue(key, serialize(EAttribute.class.cast(feature), value));
        }
        else {
            return containsReference(key, PersistentEObject.from(value).id());
        }
    }

    @Override
    public final int indexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return EStore.NO_INDEX;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

        OptionalInt index;
        if (isAttribute(feature)) {
            index = indexOfValue(key, serialize(EAttribute.class.cast(feature), value));
        }
        else {
            index = indexOfReference(key, PersistentEObject.from(value).id());
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return EStore.NO_INDEX;
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

        OptionalInt index;
        if (isAttribute(feature)) {
            index = lastIndexOfValue(key, serialize(EAttribute.class.cast(feature), value));
        }
        else {
            index = lastIndexOfReference(key, PersistentEObject.from(value).id());
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final void add(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(value);

        checkArgument(feature.isMany(), "Cannot compute add() of a single-valued feature");

        if (index != EStore.NO_INDEX) {
            checkPositionIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            if (index == EStore.NO_INDEX) {
                appendValue(key, serialize(EAttribute.class.cast(feature), value));
            }
            else {
                addValue(key.withPosition(index), serialize(EAttribute.class.cast(feature), value));
            }
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            if (index == EStore.NO_INDEX) {
                appendReference(key, referencedObject.id());
            }
            else {
                addReference(key.withPosition(index), referencedObject.id());
            }
        }
    }

    @Nullable
    @Override
    public final Object remove(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute remove() of a single-valued feature");

        checkElementIndex(index, size(internalObject, feature));

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return null;
        }

        refresh(object);

        ManyFeatureKey key = ManyFeatureKey.from(object, feature, index);

        if (isAttribute(feature)) {
            return this.<String>removeValue(key)
                    .map(v -> deserialize(EAttribute.class.cast(feature), v))
                    .orElse(null);
        }
        else {
            return removeReference(key)
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Nullable
    @Override
    public final Object move(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int targetIndex, @Nonnegative int sourceIndex) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        checkArgument(feature.isMany(), "Cannot compute move() of a single-valued feature");

        int size = size(internalObject, feature);
        checkElementIndex(sourceIndex, size);
        checkPositionIndex(targetIndex, size);

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return null;
        }

        refresh(object);

        ManyFeatureKey sourceKey = ManyFeatureKey.from(object, feature, sourceIndex);
        ManyFeatureKey targetKey = sourceKey.withPosition(targetIndex);

        if (isAttribute(feature)) {
            return this.<String>moveValue(sourceKey, targetKey)
                    .map(v -> deserialize(EAttribute.class.cast(feature), v))
                    .orElse(null);
        }
        else {
            return moveReference(sourceKey, targetKey)
                    .map(this::resolve)
                    .orElse(null);
        }
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

        refresh(object);

        FeatureKey key = FeatureKey.from(object, feature);

        if (isAttribute(feature)) {
            removeAllValues(key);
        }
        else {
            removeAllReferences(key);
        }
    }

    @Nonnull
    @Override
    public final Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        return toArray(internalObject, feature, null);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public final <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, @Nullable T[] array) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return nonNull(array) ? array : MoreArrays.newArray(Object.class, 0);
        }

        refresh(object);

        FeatureKey key = FeatureKey.from(internalObject, feature);

        Stream<Object> stream;
        if (isAttribute(feature)) {
            List<String> allValues;

            if (feature.isMany()) {
                allValues = allValuesOf(key);
            }
            else {
                allValues = this.<String>valueOf(key)
                        .map(Collections::singletonList)
                        .orElseGet(Collections::emptyList);
            }

            stream = allValues.stream()
                    .map(v -> deserialize(EAttribute.class.cast(feature), v));
        }
        else {
            List<Id> allReferences;

            if (feature.isMany()) {
                allReferences = allReferencesOf(key);
            }
            else {
                allReferences = referenceOf(key)
                        .map(Collections::singletonList)
                        .orElseGet(Collections::emptyList);
            }

            stream = allReferences.stream()
                    .map(this::resolve);
        }

        if (isNull(array)) {
            return (T[]) stream.toArray();
        }
        else {
            return stream.toArray(size -> {
                if (array.length < size) {
                    throw new IllegalArgumentException(String.format("The given array is smaller than expected (array = %d, size = %d)", array.length, size));
                }
                return array;
            });
        }
    }

    @Override
    public final int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return Arrays.hashCode(toArray(internalObject, feature));
    }

    @Nullable
    @Override
    public final PersistentEObject getContainer(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);

        return containerOf(object.id())
                .map(c -> resolve(c.id()))
                .orElse(null);
    }

    @Nullable
    @Override
    public final EReference getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);

        return containerOf(object.id())
                .map(c -> resolve(c.id()).eClass().getEStructuralFeature(c.name()))
                .map(EReference.class::cast)
                .orElse(null);
    }

    @Override
    public void updateContainment(PersistentEObject object, EReference containerReference, PersistentEObject container) {
        updateInstanceOf(object);
        updateInstanceOf(container);

        Optional<ContainerDescriptor> containerDesc = containerOf(object.id());

        if (!containerDesc.isPresent() || !Objects.equals(containerDesc.get().id(), container.id())) {
            containerFor(object.id(), ContainerDescriptor.from(container, containerReference));
        }
    }

    @Override
    public void removeContainment(PersistentEObject object) {
        updateInstanceOf(object);

        unsetContainer(object.id());
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
     * Creates the instance of the {@code object} in a {@link ClassDescriptor} object and persists it in the database.
     * <p>
     * <b>Note:</b> The type is not updated if {@code object} was previously mapped to another type.
     *
     * @param object the object to store the instance-of information from
     */
    private void updateInstanceOf(PersistentEObject object) {
        // If the object is already present in the cache, then the metaclass is defined
        if (nonNull(cache().get(object.id()))) {
            return;
        }

        Optional<ClassDescriptor> metaclass = metaclassOf(object.id());

        if (!metaclass.isPresent()) {
            metaclassFor(object.id(), ClassDescriptor.from(object));
        }

        refresh(object);
    }

    /**
     * Refreshes the {@code object} with its {@link Id} in the cache, only it does not already exist.
     *
     * @param object the object to refresh
     */
    private void refresh(PersistentEObject object) {
        cache().putIfAbsent(object.id(), object);
    }

    @Nonnull
    @Override
    public final PersistentEObject resolve(Id id) {
        checkNotNull(id);

        PersistentEObject object = cache().get(id, k -> resolveInstanceOf(k)
                .map(c -> PersistenceFactory.getInstance().create(c, k))
                .<IllegalStateException>orElseThrow(IllegalStateException::new)); // Should never happen

        if (nonNull(resource())) {
            object.resource(resource());
        }

        return object;
    }
}
