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

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.store.ClosedStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkElementIndex;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The abstract implementation of a {@code StoreAdapter}.
 */
@Immutable
@ParametersAreNonnullByDefault
public abstract class AbstractStoreAdapter implements StoreAdapter {

    /**
     * The (de)serializer used to transform the value of {@link EAttribute}s.
     */
    @Nonnull
    private final EAttributeSerializer serializer = new EAttributeSerializer();

    /**
     * The adapted store.
     */
    @Nonnull
    private Store store;

    /**
     * The resource to store and access.
     */
    @Nullable
    private Resource.Internal resource;

    /**
     * Constructs a new {@code AbstractStoreAdapter} on the given {@code store}.
     *
     * @param store    the inner store
     * @param resource the resource to store and access
     */
    protected AbstractStoreAdapter(Store store, @Nullable Resource.Internal resource) {
        this.store = store;
        this.resource = resource;
    }

    @Override
    public void close() {
        store.close();
        store = new ClosedStore();
    }

    @Override
    public void save() {
        store.save();
    }

    @Nonnull
    @Override
    public Store store() {
        return store;
    }

    @Nullable
    @Override
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        this.resource = resource;
    }

    @Nonnull
    @Override
    public final PersistentEObject resolve(Id id) {
        checkNotNull(id);

        PersistentEObject object = cache().get(id, k ->
                resolveInstanceOf(k)
                        .map(c -> PersistenceFactory.getInstance().create(c, k))
                        .<IllegalStateException>orElseThrow(IllegalStateException::new)); // Should never happen

        Resource.Internal resource = resource();
        if (nonNull(resource)) {
            object.resource(resource);
        }

        return object;
    }

    @Nullable
    @Override
    public final Object get(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index) {
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            Optional<String> value;
            if (!feature.isMany()) {
                value = store.valueOf(key);
            }
            else {
                value = store.valueOf(key.withPosition(index));
            }

            return value
                    .map(v -> serializer.deserialize(EObjects.asAttribute(feature), v))
                    .orElse(null);
        }
        else {
            Optional<Id> reference;
            if (!feature.isMany()) {
                reference = store.referenceOf(key);
            }
            else {
                reference = store.referenceOf(key.withPosition(index));
            }

            return reference
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Nullable
    @Override
    public final Object set(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index, @Nullable Object value) {
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            Optional<String> previousValue;
            if (!feature.isMany()) {
                previousValue = store.valueFor(key, serializer.serialize(EObjects.asAttribute(feature), value));
            }
            else {
                previousValue = store.valueFor(key.withPosition(index), serializer.serialize(EObjects.asAttribute(feature), value));
            }

            return previousValue
                    .map(v -> serializer.deserialize(EObjects.asAttribute(feature), v))
                    .orElse(null);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            Optional<Id> previousReference;
            if (!feature.isMany()) {
                previousReference = store.referenceFor(key, referencedObject.id());
            }
            else {
                previousReference = store.referenceFor(key.withPosition(index), referencedObject.id());
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            if (!feature.isMany()) {
                return store.hasValue(key);
            }
            else {
                return store.hasAnyValue(key);
            }
        }
        else {
            if (!feature.isMany()) {
                return store.hasReference(key);
            }
            else {
                return store.hasAnyReference(key);
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            if (!feature.isMany()) {
                store.unsetValue(key);
            }
            else {
                store.removeAllValues(key);
            }
        }
        else {
            if (!feature.isMany()) {
                store.unsetReference(key);
            }
            else {
                store.removeAllReferences(key);
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Optional<Integer> size;
        if (EObjects.isAttribute(feature)) {
            size = store.sizeOfValue(key);
        }
        else {
            size = store.sizeOfReference(key);
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            return store.containsValue(key, serializer.serialize(EObjects.asAttribute(feature), value));
        }
        else {
            return store.containsReference(key, PersistentEObject.from(value).id());
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Optional<Integer> index;
        if (EObjects.isAttribute(feature)) {
            index = store.indexOfValue(key, serializer.serialize(EObjects.asAttribute(feature), value));
        }
        else {
            index = store.indexOfReference(key, PersistentEObject.from(value).id());
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Optional<Integer> index;
        if (EObjects.isAttribute(feature)) {
            index = store.lastIndexOfValue(key, serializer.serialize(EObjects.asAttribute(feature), value));
        }
        else {
            index = store.lastIndexOfReference(key, PersistentEObject.from(value).id());
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(value);

        checkArgument(feature.isMany(), "Cannot compute add() of a single-valued feature");

        if (index != EStore.NO_INDEX) {
            checkPositionIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            if (index == EStore.NO_INDEX) {
                store.appendValue(key, serializer.serialize(EObjects.asAttribute(feature), value));
            }
            else {
                store.addValue(key.withPosition(index), serializer.serialize(EObjects.asAttribute(feature), value));
            }
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            if (index == EStore.NO_INDEX) {
                store.appendReference(key, referencedObject.id());
            }
            else {
                store.addReference(key.withPosition(index), referencedObject.id());
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

        ManyFeatureBean key = ManyFeatureBean.from(object, feature, index);

        if (EObjects.isAttribute(feature)) {
            return store.<String>removeValue(key)
                    .map(v -> serializer.deserialize(EObjects.asAttribute(feature), v))
                    .orElse(null);
        }
        else {
            return store.removeReference(key)
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

        ManyFeatureBean sourceKey = ManyFeatureBean.from(object, feature, sourceIndex);
        ManyFeatureBean targetKey = sourceKey.withPosition(targetIndex);

        if (EObjects.isAttribute(feature)) {
            return store.<String>moveValue(sourceKey, targetKey)
                    .map(v -> serializer.deserialize(EObjects.asAttribute(feature), v))
                    .orElse(null);
        }
        else {
            return store.moveReference(sourceKey, targetKey)
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

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            store.removeAllValues(key);
        }
        else {
            store.removeAllReferences(key);
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

        List<T> values = (List<T>) getAll(internalObject, feature);

        return (T[]) (isNull(array) ? values.toArray() : values.toArray(array));
    }

    @Override
    public final int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return getAll(internalObject, feature).hashCode();
    }

    @Nullable
    @Override
    public final PersistentEObject getContainer(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);

        return store.containerOf(object.id())
                .map(c -> resolve(c.owner()))
                .orElse(null);
    }

    @Nullable
    @Override
    public final EReference getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);

        return store.containerOf(object.id())
                .map(c -> resolve(c.owner()).eClass().getEStructuralFeature(c.id()))
                .map(EObjects::asReference)
                .orElse(null);
    }

    @Nonnull
    @Override
    public List<Object> getAll(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);

        PersistentEObject object = PersistentEObject.from(internalObject);

        if (!exists(object.id())) {
            return Collections.emptyList();
        }

        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            List<String> value;
            if (!feature.isMany()) {
                value = store.<String>valueOf(key)
                        .map(Collections::singletonList)
                        .orElseGet(Collections::emptyList);
            }
            else {
                value = store.allValuesOf(key);
            }

            EAttribute attribute = EObjects.asAttribute(feature);

            return value.stream()
                    .map(v -> serializer.deserialize(attribute, v))
                    .collect(Collectors.toList());
        }
        else {
            List<Id> reference;
            if (!feature.isMany()) {
                reference = store.referenceOf(key)
                        .map(Collections::singletonList)
                        .orElseGet(Collections::emptyList);
            }
            else {
                reference = store.allReferencesOf(key);
            }

            return reference.stream()
                    .map(this::resolve)
                    .collect(Collectors.toList());
        }
    }

    @Override
    // TODO Implement this method
    public void setAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(values);

        checkArgument(feature.isMany(), "Cannot compute setAll() of a single-valued feature");

        unset(internalObject, feature);

        addAll(internalObject, feature, NO_INDEX, values);
    }

    @Override
    // TODO Implement this method
    public int addAll(InternalEObject internalObject, EStructuralFeature feature, int index, Collection<?> values) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkNotNull(values);

        checkArgument(feature.isMany(), "Cannot compute addAll() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            EAttribute attribute = EObjects.asAttribute(feature);

            List<String> vs = values.stream()
                    .map(v -> serializer.serialize(attribute, v))
                    .collect(Collectors.toList());

            if (index == NO_INDEX) {
                return store.appendAllValues(key, vs);
            }
            else {
                store.addAllValues(key.withPosition(index), vs);
                return index;
            }
        }
        else {
            List<Id> rs = values.stream()
                    .map(PersistentEObject::from)
                    .peek(this::updateInstanceOf)
                    .map(PersistentEObject::id)
                    .collect(Collectors.toList());

            if (index == NO_INDEX) {
                return store.appendAllReferences(key, rs);
            }
            else {
                store.addAllReferences(key.withPosition(index), rs);
                return index;
            }
        }
    }

    @Override
    // TODO Implement this method
    public void removeAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateContainment(PersistentEObject object, EReference containerReference, PersistentEObject container) {
        updateInstanceOf(object);
        updateInstanceOf(container);

        Optional<SingleFeatureBean> containerDesc = store.containerOf(object.id());

        if (!containerDesc.isPresent() || !Objects.equals(containerDesc.get().owner(), container.id())) {
            store.containerFor(object.id(), SingleFeatureBean.from(container, containerReference));
        }
    }

    @Override
    public void removeContainment(PersistentEObject object) {
        updateInstanceOf(object);

        store.unsetContainer(object.id());
    }

    @Override
    public void copyTo(StoreAdapter target) {
        store.copyTo(target.store());
    }

    /**
     * Returns the in-memory cache holding recently loaded {@link PersistentEObject}s.
     *
     * @return the cache
     */
    @Nonnull
    protected abstract Cache<Id, PersistentEObject> cache();

    /**
     * Checks whether the specified {@code id} already exists in this {@link StoreAdapter}.
     *
     * @param id the identifier to check
     *
     * @return {@code true} if the {@code id} exists, {@code false} otherwise.
     */
    private boolean exists(Id id) {
        return nonNull(cache().get(id)) || store.exists(id);
    }

    /**
     * Compute the {@link EClass} associated to the model element with the provided {@link Id}.
     *
     * @param id the {@link Id} of the model element to compute the {@link EClass} from
     *
     * @return an {@link EClass} representing the meta-class of the element
     */
    @Nonnull
    private Optional<EClass> resolveInstanceOf(Id id) {
        Optional<EClass> instanceOf = store.metaClassOf(id).map(ClassBean::get);

        if (!instanceOf.isPresent()) {
            throw new NoSuchElementException(String.format("Element '%s' does not have an associated EClass", id));
        }

        return instanceOf;
    }

    /**
     * Creates the instance of the {@code object} in a {@link ClassBean} object and persists it in the database.
     * <p>
     * <b>Note:</b> The type is not updated if {@code object} was previously mapped to another type.
     *
     * @param object the object to store the instance-of information from
     */
    private void updateInstanceOf(PersistentEObject object) {
        // If the object is already present in the cache, then the meta-class is defined
        if (nonNull(cache().get(object.id()))) {
            return;
        }

        Optional<ClassBean> metaClass = store.metaClassOf(object.id());

        if (!metaClass.isPresent()) {
            store.metaClassFor(object.id(), ClassBean.from(object));
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

    /**
     * A serializer that transforms the value of {@link EAttribute}s.
     */
    @ParametersAreNonnullByDefault
    private class EAttributeSerializer {

        /**
         * Converts an instance of the {@code attribute} to a string literal representation.
         *
         * @param attribute the attribute to instantiate
         * @param value     the value of the attribute
         *
         * @return the string literal representation of the value
         *
         * @see EcoreUtil#convertToString(EDataType, Object)
         */
        public String serialize(EAttribute attribute, @Nullable Object value) {
            if (isNull(value)) {
                return null;
            }

            final EDataType dataType = attribute.getEAttributeType();

            if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
                return serializeEntry(attribute, FeatureMap.Entry.class.cast(value));
            }

            return EcoreUtil.convertToString(dataType, value);
        }

        private String serializeEntry(EAttribute attribute, FeatureMap.Entry entry) {
            throw new UnsupportedOperationException("FeatureMap.Entry are not supported yet");
        }

        /**
         * Creates an instance of the {@code attribute} from a string literal representation.
         *
         * @param attribute the attribute to instantiate
         * @param value     the string literal representation of the value
         *
         * @return the value of the attribute
         *
         * @see EcoreUtil#createFromString(EDataType, String)
         */
        public Object deserialize(EAttribute attribute, @Nullable String value) {
            if (isNull(value)) {
                return null;
            }

            final EDataType dataType = attribute.getEAttributeType();

            if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
                return deserializeEntry(attribute, value);
            }

            return EcoreUtil.createFromString(dataType, value);
        }

        private FeatureMap.Entry deserializeEntry(EAttribute attribute, String value) {
            throw new UnsupportedOperationException("FeatureMap.Entry are not supported yet");
        }
    }
}
