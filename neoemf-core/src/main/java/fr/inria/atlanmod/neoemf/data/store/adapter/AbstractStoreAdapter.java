/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.cache.Cache;
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
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;
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

    /**
     * Ensures that the given parameters are not null.
     *
     * @param internalObject the object in question
     */
    private static void checkParameters(InternalEObject internalObject) {
        checkNotNull(internalObject, "internalObject");
    }

    /**
     * Ensures that the given parameters are not null.
     *
     * @param internalObject the object in question
     * @param feature        a feature of the object
     */
    private static void checkParameters(InternalEObject internalObject, EStructuralFeature feature) {
        checkParameters(internalObject);
        checkNotNull(feature, "feature");
    }

    /**
     * Ensures that the given parameters are not null.
     *
     * @param internalObject the object in question
     * @param feature        a feature of the object
     * @param value          the value
     */
    private static void checkParameters(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        checkParameters(internalObject, feature);
        checkNotNull(value, "value");
    }

    /**
     * Ensures that the given parameters are not null.
     *
     * @param internalObject the object in question
     * @param feature        a feature of the object
     * @param values         the values
     */
    private static void checkParameters(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values) {
        checkParameters(internalObject, feature);
        checkNotNull(values, "values");
        checkArgument(values.stream().noneMatch(Objects::isNull), "values contains null object");
    }

    /**
     * Ensures that {@code feature} is a multi-valued feature.
     *
     * @param method  the name of the called method
     * @param feature the feature to check
     *
     * @throws IllegalArgumentException if {@code feature} is a single-valued feature
     */
    private static void checkIsMany(String method, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "cannot compute %s() of a single-valued feature", method);
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
    // FIXME Deadlock when calling resolve() with the same subscriber (1 thread-pool)
    public final PersistentEObject resolve(Id id) {
        checkNotNull(id, "id");

        Function<Id, PersistentEObject> getOrCreateFunc = i -> resolveInstanceOf(i)
                .map(c -> PersistenceFactory.getInstance().create(c, i))
                .<IllegalStateException>orElseThrow(IllegalStateException::new); // Should never happen

        PersistentEObject resolvedObject = cache().get(id, getOrCreateFunc);

        if (nonNull(resource)) {
            resolvedObject.resource(resource);
        }

        return resolvedObject;
    }

    @Nullable
    @Override
    public final Object get(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index) {
        checkParameters(internalObject, feature);

        if (feature.isMany()) {
            checkElementIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = adaptAndRefresh(internalObject);

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
        checkParameters(internalObject, feature);

        if (isNull(value)) {
            Object previousValue = get(internalObject, feature, index);
            unset(internalObject, feature);
            return previousValue;
        }

        if (feature.isMany()) {
            checkElementIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = adaptAndUpdate(internalObject);

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
            PersistentEObject referencedObject = adaptAndUpdate(value);

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
        checkParameters(internalObject, feature);

        PersistentEObject object = adaptAndRefresh(internalObject);

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
        checkParameters(internalObject, feature);

        PersistentEObject object = adaptAndRefresh(internalObject);

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
        checkParameters(internalObject, feature);
        checkIsMany("isEmpty", feature);

        return size(internalObject, feature) == 0;
    }

    @Nonnegative
    @Override
    public final int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkParameters(internalObject, feature);
        checkIsMany("size", feature);

        PersistentEObject object = adaptAndRefresh(internalObject);

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
        checkParameters(internalObject, feature);
        checkIsMany("contains", feature);

        return indexOf(internalObject, feature, value) != NO_INDEX;
    }

    @Override
    public final int indexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkParameters(internalObject, feature);
        checkIsMany("indexOf", feature);

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = adaptAndRefresh(internalObject);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Object comparison;
        Flowable<?> flowable;

        if (EObjects.isAttribute(feature)) {
            comparison = attrConverter.convert(value, EObjects.asAttribute(feature));
            flowable = store.allValuesOf(key);
        }
        else {
            comparison = PersistentEObject.from(value).id();
            flowable = store.allReferencesOf(key);
        }

        AtomicInteger currentIndex = new AtomicInteger();

        // TODO Use async iteration
        for (Object o : flowable.blockingIterable()) {
            if (comparison.equals(o)) {
                return currentIndex.get();
            }
            currentIndex.incrementAndGet();
        }

        return NO_INDEX;
    }

    @Override
    public final int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkParameters(internalObject, feature);
        checkIsMany("lastIndexOf", feature);

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = adaptAndRefresh(internalObject);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        Object comparison;
        Flowable<?> flowable;

        if (EObjects.isAttribute(feature)) {
            comparison = attrConverter.convert(value, EObjects.asAttribute(feature));
            flowable = store.allValuesOf(key);
        }
        else {
            comparison = PersistentEObject.from(value).id();
            flowable = store.allReferencesOf(key);
        }

        AtomicInteger currentIndex = new AtomicInteger();
        AtomicInteger lastIndex = new AtomicInteger(NO_INDEX);

        // TODO Use async iteration
        for (Object o : flowable.blockingIterable()) {
            if (comparison.equals(o)) {
                lastIndex.set(currentIndex.get());
            }
            currentIndex.incrementAndGet();
        }

        return lastIndex.get();
    }

    @Override
    public final void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkParameters(internalObject, feature, value);
        checkIsMany("add", feature);

        if (index != EStore.NO_INDEX) {
            checkPositionIndex(index, size(internalObject, feature));
        }

        PersistentEObject object = adaptAndUpdate(internalObject);

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
            PersistentEObject referencedObject = adaptAndUpdate(value);

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
        checkParameters(internalObject, feature);
        checkIsMany("remove", feature);
        checkElementIndex(index, size(internalObject, feature));

        PersistentEObject object = adaptAndRefresh(internalObject);

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
        checkParameters(internalObject, feature);
        checkIsMany("move", feature);
        checkElementIndex(targetIndex, size(internalObject, feature));

        Object moved = remove(internalObject, feature, sourceIndex);
        checkState(nonNull(moved), "inconsistency issue");

        add(internalObject, feature, targetIndex, moved);

        return moved;
    }

    @Override
    public final void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkParameters(internalObject, feature);
        checkIsMany("clear", feature);

        PersistentEObject object = adaptAndRefresh(internalObject);

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
        checkParameters(internalObject, feature);

        return toArray(internalObject, feature, null);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public final <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, @Nullable T[] array) {
        checkParameters(internalObject, feature);

        List<T> values = (List<T>) getAll(internalObject, feature);

        return (T[]) (isNull(array) ? values.toArray() : values.toArray(array));
    }

    @Override
    public final int hashCode(InternalEObject internalObject, EStructuralFeature feature) {
        return getAll(internalObject, feature).hashCode();
    }

    @Override
    public final PersistentEObject getContainer(InternalEObject internalObject) {
        checkParameters(internalObject);

        PersistentEObject object = PersistentEObject.from(internalObject);

        return store.containerOf(object.id())
                .to(CommonQueries::toOptional)
                .map(c -> resolve(c.owner()))
                .orElse(null);
    }

    @Override
    public final EReference getContainingFeature(InternalEObject internalObject) {
        checkParameters(internalObject);

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
        checkParameters(internalObject, feature);

        PersistentEObject object = adaptAndRefresh(internalObject);

        SingleFeatureBean key = SingleFeatureBean.from(object, feature);

        if (EObjects.isAttribute(feature)) {
            Flowable<Object> flowable;
            if (!feature.isMany()) {
                flowable = store.valueOf(key).toFlowable();
            }
            else {
                flowable = store.allValuesOf(key);
            }

            EAttribute attribute = EObjects.asAttribute(feature);

            return flowable
                    .to(CommonQueries::toStream)
                    .map(v -> attrConverter.revert(v, attribute))
                    .collect(Collectors.toList());
        }
        else {
            Flowable<Id> flowable;
            if (!feature.isMany()) {
                flowable = store.referenceOf(key).toFlowable();
            }
            else {
                flowable = store.allReferencesOf(key);
            }

            return flowable
                    .to(CommonQueries::toStream)
                    .map(this::resolve)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void setAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values) {
        checkParameters(internalObject, feature, values);
        checkIsMany("setAll", feature);

        unset(internalObject, feature);

        addAll(internalObject, feature, NO_INDEX, values);
    }

    @Override
    public int addAll(InternalEObject internalObject, EStructuralFeature feature, int index, Collection<?> values) {
        checkParameters(internalObject, feature, values);
        checkIsMany("addAll", feature);

        PersistentEObject object = adaptAndUpdate(internalObject);

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
                    .map(this::adaptAndUpdate)
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
        checkParameters(internalObject, feature, values);
        checkIsMany("removeAll", feature);

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateContainment(PersistentEObject object, EReference containerReference, PersistentEObject container) {
        updateInstanceOf(object);
        updateInstanceOf(container);

        Action setIfAbsentFunc = () -> store
                .containerFor(object.id(), SingleFeatureBean.from(container, containerReference))
                .subscribe();

        store.containerOf(object.id())
                .map(AbstractFeatureBean::owner)
                .filter(Functions.equalsWith(container.id()))
                .doOnComplete(setIfAbsentFunc)
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
        Maybe<EClass> ifEmptyFunc = Maybe.error(() -> new NoSuchElementException(String.format("Element '%s' does not have an associated EClass", id.toHexString())));

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
     * Adapts the {@code o} into a {@link PersistentEObject}, and refreshes it with {@link #refresh(PersistentEObject)}.
     *
     * @param o the object to adapt
     *
     * @return the adapted {@code o} as a {@code PersistentEObject}
     */
    @Nonnull
    private PersistentEObject adaptAndRefresh(Object o) {
        PersistentEObject object = PersistentEObject.from(o);
        refresh(object);
        return object;
    }

    /**
     * Adapts the {@code o} into a {@link PersistentEObject}, and updates its meta-class with
     * {@link #updateInstanceOf(PersistentEObject)}.
     *
     * @param o the object to adapt
     *
     * @return the adapted {@code o} as a {@code PersistentEObject}
     */
    @Nonnull
    private PersistentEObject adaptAndUpdate(Object o) {
        PersistentEObject object = PersistentEObject.from(o);
        updateInstanceOf(object);
        return object;
    }

    /**
     * Refreshes the {@code object} with its {@link Id} in the cache, only it does not already exist.
     *
     * @param object the object to refresh
     */
    private void refresh(PersistentEObject object) {
        cache().putIfAbsent(object.id(), object);
    }
}
