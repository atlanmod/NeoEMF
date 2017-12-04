/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.collect.MoreStreams;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.bean.AbstractFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.ClassAlreadyExistsException;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;
import fr.inria.atlanmod.neoemf.data.store.ClosedStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import io.reactivex.Maybe;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkElementIndex;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@code StoreAdapter}.
 */
@Immutable
@ParametersAreNonnullByDefault
public abstract class AbstractStoreAdapter implements StoreAdapter {

    /**
     * The converter used to transform the value of {@link EAttribute}s.
     */
    @Nonnull
    private final AttributeConverter attrConverter = new AttributeConverter(new FeatureMapConverter(this));

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
        checkNotNull(id, "id");

        PersistentEObject object = cache().get(id, k -> resolveInstanceOf(k)
                .map(c -> PersistenceFactory.getInstance().create(c, k))
                .<IllegalStateException>orElseThrow(IllegalStateException::new)); // Should never happen

        Resource.Internal currentResource = resource();
        if (nonNull(currentResource)) {
            object.resource(currentResource);
        }

        return object;
    }

    @Nullable
    @Override
    public final Object get(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        if (feature.isMany()) {
            checkElementIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            Optional<Object> value;
            if (!feature.isMany()) {
                value = store.valueOf(key).to(CommonQueries::toOptional);
            }
            else {
                value = store.valueOf(key.withPosition(index)).to(CommonQueries::toOptional);
            }

            return value
                    .map(v -> attrConverter.revert(v, EObjects.asAttribute(feature)))
                    .orElse(null);
        }
        else {
            Optional<Id> reference;
            if (!feature.isMany()) {
                reference = store.referenceOf(key).to(CommonQueries::toOptional);
            }
            else {
                reference = store.referenceOf(key.withPosition(index)).to(CommonQueries::toOptional);
            }

            return reference
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Nullable
    @Override
    public final Object set(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index, @Nullable Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

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
            Optional<Object> previousValue;
            if (!feature.isMany()) {
                previousValue = store.valueFor(key, attrConverter.convert(value, EObjects.asAttribute(feature))).to(CommonQueries::toOptional);
            }
            else {
                previousValue = store.valueFor(key.withPosition(index), attrConverter.convert(value, EObjects.asAttribute(feature))).to(CommonQueries::toOptional);
            }

            return previousValue
                    .map(v -> attrConverter.revert(v, EObjects.asAttribute(feature)))
                    .orElse(null);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            Optional<Id> previousReference;
            if (!feature.isMany()) {
                previousReference = store.referenceFor(key, referencedObject.id()).to(CommonQueries::toOptional);
            }
            else {
                previousReference = store.referenceFor(key.withPosition(index), referencedObject.id()).to(CommonQueries::toOptional);
            }

            return previousReference
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Override
    public final boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            if (!feature.isMany()) {
                return store.valueOf(key).to(CommonQueries::toOptional).isPresent();
            }
            else {
                return store.sizeOfValue(key).isPresent();
            }
        }
        else {
            if (!feature.isMany()) {
                return store.referenceOf(key).to(CommonQueries::toOptional).isPresent();
            }
            else {
                return store.sizeOfReference(key).isPresent();
            }
        }
    }

    @Override
    public final void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            if (!feature.isMany()) {
                store.removeValue(key).blockingAwait();
            }
            else {
                store.removeAllValues(key);
            }
        }
        else {
            if (!feature.isMany()) {
                store.removeReference(key).blockingAwait();
            }
            else {
                store.removeAllReferences(key);
            }
        }
    }

    @Override
    public final boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute isEmpty() of a single-valued feature");

        return size(internalObject, feature) == 0;
    }

    @Nonnegative
    @Override
    public final int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
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
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute contains() of a single-valued feature");

        if (isNull(value)) {
            return false;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            return store.allValuesOf(key).anyMatch(attrConverter.convert(value, EObjects.asAttribute(feature))::equals);
        }
        else {
            return store.allReferencesOf(key).anyMatch(PersistentEObject.from(value).id()::equals);
        }
    }

    @Override
    public final int indexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Optional<Integer> index;
        if (EObjects.isAttribute(feature)) {
            index = MoreStreams.indexOf(store.allValuesOf(key), attrConverter.convert(value, EObjects.asAttribute(feature)));
        }
        else {
            index = MoreStreams.indexOf(store.allReferencesOf(key), PersistentEObject.from(value).id());
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Optional<Integer> index;
        if (EObjects.isAttribute(feature)) {
            index = MoreStreams.lastIndexOf(store.allValuesOf(key), attrConverter.convert(value, EObjects.asAttribute(feature)));
        }
        else {
            index = MoreStreams.lastIndexOf(store.allReferencesOf(key), PersistentEObject.from(value).id());
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        checkArgument(feature.isMany(), "Cannot compute add() of a single-valued feature");

        if (index != EStore.NO_INDEX) {
            checkPositionIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            if (index == EStore.NO_INDEX) {
                store.appendValue(key, attrConverter.convert(value, EObjects.asAttribute(feature)));
            }
            else {
                store.addValue(key.withPosition(index), attrConverter.convert(value, EObjects.asAttribute(feature)));
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
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute remove() of a single-valued feature");

        checkElementIndex(index, size(internalObject, feature));

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        ManyFeatureBean key = ManyFeatureBean.from(object, feature, index);

        if (EObjects.isAttribute(feature)) {
            return store.removeValue(key)
                    .map(v -> attrConverter.revert(v, EObjects.asAttribute(feature)))
                    .orElse(null);
        }
        else {
            return store.removeReference(key)
                    .map(this::resolve)
                    .orElse(null);
        }
    }

    @Override
    public final Object move(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int targetIndex, @Nonnegative int sourceIndex) {
        checkElementIndex(targetIndex, size(internalObject, feature));

        Object moved = remove(internalObject, feature, sourceIndex);
        checkState(nonNull(moved), "inconsistency issue");

        add(internalObject, feature, targetIndex, moved);

        return moved;
    }

    @Override
    public final void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkArgument(feature.isMany(), "Cannot compute clear() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
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
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        return toArray(internalObject, feature, null);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public final <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, @Nullable T[] array) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        List<T> values = (List<T>) getAll(internalObject, feature);

        return (T[]) (isNull(array) ? values.toArray() : values.toArray(array));
    }

    @Override
    public final int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return getAll(internalObject, feature).hashCode();
    }

    @Override
    public final PersistentEObject getContainer(InternalEObject internalObject) {
        checkNotNull(internalObject, "internalObject");

        PersistentEObject object = PersistentEObject.from(internalObject);

        return store.containerOf(object.id())
                .to(CommonQueries::toOptional)
                .map(c -> resolve(c.owner()))
                .orElse(null);
    }

    @Override
    public final EReference getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject, "internalObject");

        PersistentEObject object = PersistentEObject.from(internalObject);

        return store.containerOf(object.id())
                .to(CommonQueries::toOptional)
                .map(c -> resolve(c.owner()).eClass().getEStructuralFeature(c.id()))
                .map(EObjects::asReference)
                .orElse(null);
    }

    @Nonnull
    @Override
    public List<Object> getAll(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            Stream<Object> stream;
            if (!feature.isMany()) {
                stream = store.valueOf(key)
                        .to(CommonQueries::toOptional)
                        .map(Stream::of)
                        .orElseGet(Stream::empty);
            }
            else {
                stream = store.allValuesOf(key);
            }

            EAttribute attribute = EObjects.asAttribute(feature);

            return stream
                    .map(v -> attrConverter.revert(v, attribute))
                    .collect(Collectors.toList());
        }
        else {
            Stream<Id> stream;
            if (!feature.isMany()) {
                stream = store.referenceOf(key)
                        .to(CommonQueries::toOptional)
                        .map(Stream::of)
                        .orElseGet(Stream::empty);
            }
            else {
                stream = store.allReferencesOf(key);
            }

            return stream
                    .map(this::resolve)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void setAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");
        checkNotNull(values, "values");

        checkArgument(feature.isMany(), "Cannot compute setAll() of a single-valued feature");

        unset(internalObject, feature);

        addAll(internalObject, feature, NO_INDEX, values);
    }

    @Override
    public int addAll(InternalEObject internalObject, EStructuralFeature feature, int index, Collection<?> values) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");
        checkNotNull(values, "values");

        checkArgument(feature.isMany(), "Cannot compute addAll() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            EAttribute attribute = EObjects.asAttribute(feature);

            List<Object> valuesToAdd = values.stream()
                    .map(v -> attrConverter.convert(v, attribute))
                    .collect(Collectors.toList());

            if (index == NO_INDEX) {
                return store.appendAllValues(key, valuesToAdd);
            }
            else {
                store.addAllValues(key.withPosition(index), valuesToAdd);
                return index;
            }
        }
        else {
            List<Id> referencesToAdd = values.stream()
                    .map(PersistentEObject::from)
                    .peek(this::updateInstanceOf)
                    .map(PersistentEObject::id)
                    .collect(Collectors.toList());

            if (index == NO_INDEX) {
                return store.appendAllReferences(key, referencesToAdd);
            }
            else {
                store.addAllReferences(key.withPosition(index), referencesToAdd);
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

        store.containerOf(object.id())
                .map(AbstractFeatureBean::owner)
                .filter(Functions.equalsWith(container.id()))
                .doOnComplete(() -> store.containerFor(object.id(), SingleFeatureBean.from(container, containerReference)).subscribe())
                .cache()
                .ignoreElement()
                .blockingAwait();
//                .subscribe(); // TODO Use `subscribe()` for async work
    }

    @Override
    public void removeContainment(PersistentEObject object) {
        updateInstanceOf(object);

        store.removeContainer(object.id())
                .blockingAwait();
//                .subscribe(); // TODO Use `subscribe()` for async work
    }

    @Nonnull
    @Override
    public Optional<EClass> resolveInstanceOf(Id id) {
        Maybe<EClass> ifEmptyFunc = Maybe.error(new NoSuchElementException(String.format("Element '%s' does not have an associated EClass", id.toHexString())));

        return store.metaClassOf(id)
                .map(ClassBean::get)
                .switchIfEmpty(ifEmptyFunc)
                .to(CommonQueries::toOptional);
    }

    @Override
    public void updateInstanceOf(PersistentEObject object) {
        // If the object is already present in the cache, then the meta-class is defined
        if (cache().contains(object.id())) {
            return;
        }

        store.metaClassFor(object.id(), ClassBean.from(object))
                .doOnComplete(() -> refresh(object))
                .onErrorComplete(Functions.isInstanceOf(ClassAlreadyExistsException.class))
                .blockingAwait();
//                .subscribe(); // TODO Use `subscribe()` for async work
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
     * Refreshes the {@code object} with its {@link Id} in the cache, only it does not already exist.
     *
     * @param object the object to refresh
     */
    private void refresh(PersistentEObject object) {
        cache().putIfAbsent(object.id(), object);
    }
}
