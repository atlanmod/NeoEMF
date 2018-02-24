/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.commons.collect.MoreStreams;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.store.ClosedStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.util.EFeatures;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.isNull;

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
     * The converter used to transform the value of {@link EReference}s.
     */
    @Nonnull
    private final ReferenceConverter refConverter = new ReferenceConverter(this);

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
     * Creates a new in-memory cache to store {@link fr.inria.atlanmod.neoemf.core.Id}s and their associated {@link
     * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
     *
     * @return a new cache
     *
     * @see #getCache()
     */
    @Nonnull
    protected static Cache<Id, PersistentEObject> createCache() {
        return CacheBuilder.builder()
                .softValues()
                .build();
    }

    /**
     * Returns the in-memory cache holding recently loaded {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s.
     *
     * @return the cache
     */
    @Nonnull
    protected abstract Cache<Id, PersistentEObject> getCache();

    /**
     * Refreshes the {@code object} with its {@link Id} in the cache, only it does not already exist.
     *
     * @param object the object to refresh
     */
    private void refresh(PersistentEObject object) {
        getCache().putIfAbsent(refConverter.convert(object), object);
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

        PersistentEObject object = getCache().get(id, this::rebuild);

        // Define the resource of the object
        Optional.ofNullable(resource).ifPresent(object::resource);

        return object;
    }

    @Nullable
    @Override
    public final Object get(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            Optional<Object> value;
            if (!feature.isMany()) {
                value = store.valueOf(bean);
            }
            else {
                value = store.valueOf(bean.withPosition(index));
            }

            return value
                    .map(v -> attrConverter.revert(v, EFeatures.asAttribute(feature)))
                    .orElse(null);
        }
        else {
            Optional<Id> reference;
            if (!feature.isMany()) {
                reference = store.referenceOf(bean);
            }
            else {
                reference = store.referenceOf(bean.withPosition(index));
            }

            return reference
                    .map(refConverter::revert)
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

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            Optional<Object> previousValue;
            if (!feature.isMany()) {
                previousValue = store.valueFor(bean, attrConverter.convert(value, EFeatures.asAttribute(feature)));
            }
            else {
                previousValue = store.valueFor(bean.withPosition(index), attrConverter.convert(value, EFeatures.asAttribute(feature)));
            }

            return previousValue
                    .map(v -> attrConverter.revert(v, EFeatures.asAttribute(feature)))
                    .orElse(null);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            Optional<Id> previousReference;
            if (!feature.isMany()) {
                previousReference = store.referenceFor(bean, refConverter.convert(referencedObject));
            }
            else {
                previousReference = store.referenceFor(bean.withPosition(index), refConverter.convert(referencedObject));
            }

            return previousReference
                    .map(refConverter::revert)
                    .orElse(null);
        }
    }

    @Override
    public final boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            if (!feature.isMany()) {
                return store.valueOf(bean).isPresent();
            }
            else {
                return store.sizeOfValue(bean).isPresent();
            }
        }
        else {
            if (!feature.isMany()) {
                return store.referenceOf(bean).isPresent();
            }
            else {
                return store.sizeOfReference(bean).isPresent();
            }
        }
    }

    @Override
    public final void unset(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            if (!feature.isMany()) {
                store.removeValue(bean);
            }
            else {
                store.removeAllValues(bean);
            }
        }
        else {
            if (!feature.isMany()) {
                store.removeReference(bean);
            }
            else {
                store.removeAllReferences(bean);
            }
        }
    }

    @Override
    public final boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute isEmpty() of a single-valued feature");

        return size(internalObject, feature) == 0;
    }

    @Nonnegative
    @Override
    public final int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute size() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        Optional<Integer> size;
        if (EFeatures.isAttribute(feature)) {
            size = store.sizeOfValue(bean);
        }
        else {
            size = store.sizeOfReference(bean);
        }
        return size.orElse(0);
    }

    @Override
    public final boolean contains(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute contains() of a single-valued feature");

        if (isNull(value)) {
            return false;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            return store.allValuesOf(bean).anyMatch(attrConverter.convert(value, EFeatures.asAttribute(feature))::equals);
        }
        else {
            return store.allReferencesOf(bean).anyMatch(refConverter.convert(PersistentEObject.from(value))::equals);
        }
    }

    @Override
    public final int indexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        Optional<Integer> index;
        if (EFeatures.isAttribute(feature)) {
            index = MoreStreams.indexOf(store.allValuesOf(bean), attrConverter.convert(value, EFeatures.asAttribute(feature)));
        }
        else {
            index = MoreStreams.indexOf(store.allReferencesOf(bean), refConverter.convert(PersistentEObject.from(value)));
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, @Nullable Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return EStore.NO_INDEX;
        }

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        Optional<Integer> index;
        if (EFeatures.isAttribute(feature)) {
            index = MoreStreams.lastIndexOf(store.allValuesOf(bean), attrConverter.convert(value, EFeatures.asAttribute(feature)));
        }
        else {
            index = MoreStreams.lastIndexOf(store.allReferencesOf(bean), refConverter.convert(PersistentEObject.from(value)));
        }
        return index.orElse(EStore.NO_INDEX);
    }

    @Override
    public final void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        checkState(feature.isMany(), "Cannot compute add() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            if (index == EStore.NO_INDEX) {
                store.appendValue(bean, attrConverter.convert(value, EFeatures.asAttribute(feature)));
            }
            else {
                store.addValue(bean.withPosition(index), attrConverter.convert(value, EFeatures.asAttribute(feature)));
            }
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateInstanceOf(referencedObject);

            if (index == EStore.NO_INDEX) {
                store.appendReference(bean, refConverter.convert(referencedObject));
            }
            else {
                store.addReference(bean.withPosition(index), refConverter.convert(referencedObject));
            }
        }
    }

    @Nullable
    @Override
    public final Object remove(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int index) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute remove() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        ManyFeatureBean bean = ManyFeatureBean.from(object, feature, index);

        if (EFeatures.isAttribute(feature)) {
            return store.removeValue(bean)
                    .map(v -> attrConverter.revert(v, EFeatures.asAttribute(feature)))
                    .orElse(null);
        }
        else {
            return store.removeReference(bean)
                    .map(refConverter::revert)
                    .orElse(null);
        }
    }

    @Override
    public final Object move(InternalEObject internalObject, EStructuralFeature feature, @Nonnegative int targetIndex, @Nonnegative int sourceIndex) {
        Object moved = remove(internalObject, feature, sourceIndex);
        checkNotNull(moved, "inconsistency issue");

        add(internalObject, feature, targetIndex, moved);

        return moved;
    }

    @Override
    public final void clear(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        checkState(feature.isMany(), "Cannot compute clear() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            store.removeAllValues(bean);
        }
        else {
            store.removeAllReferences(bean);
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

        return store.containerOf(refConverter.convert(object))
                .map(c -> refConverter.revert(c.owner()))
                .orElse(null);
    }

    @Override
    public final EReference getContainingFeature(InternalEObject internalObject) {
        checkNotNull(internalObject, "internalObject");

        PersistentEObject object = PersistentEObject.from(internalObject);

        return store.containerOf(refConverter.convert(object))
                .map(c -> refConverter.revert(c.owner()).eClass().getEStructuralFeature(c.id()))
                .map(EFeatures::asReference)
                .orElse(null);
    }

    @Nonnull
    @Override
    public List<Object> getAll(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        refresh(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            Stream<Object> stream;
            if (!feature.isMany()) {
                stream = store.valueOf(bean)
                        .map(Stream::of)
                        .orElseGet(Stream::empty);
            }
            else {
                stream = store.allValuesOf(bean);
            }

            EAttribute attribute = EFeatures.asAttribute(feature);

            return stream
                    .map(v -> attrConverter.revert(v, attribute))
                    .collect(Collectors.toList());
        }
        else {
            Stream<Id> stream;
            if (!feature.isMany()) {
                stream = store.referenceOf(bean)
                        .map(Stream::of)
                        .orElseGet(Stream::empty);
            }
            else {
                stream = store.allReferencesOf(bean);
            }

            return stream
                    .map(refConverter::revert)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void setAll(InternalEObject internalObject, EStructuralFeature feature, Collection<?> values) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");
        checkNotNull(values, "values");

        checkState(feature.isMany(), "Cannot compute setAll() of a single-valued feature");

        unset(internalObject, feature);

        addAll(internalObject, feature, NO_INDEX, values);
    }

    @Override
    public int addAll(InternalEObject internalObject, EStructuralFeature feature, int index, Collection<?> values) {
        checkNotNull(internalObject, "internalObject");
        checkNotNull(feature, "feature");
        checkNotNull(values, "values");

        checkState(feature.isMany(), "Cannot compute addAll() of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        updateInstanceOf(object);

        SingleFeatureBean bean = SingleFeatureBean.from(object, feature);

        if (EFeatures.isAttribute(feature)) {
            EAttribute attribute = EFeatures.asAttribute(feature);

            List<Object> valuesToAdd = values.stream()
                    .map(v -> attrConverter.convert(v, attribute))
                    .collect(Collectors.toList());

            if (index == NO_INDEX) {
                return store.appendAllValues(bean, valuesToAdd);
            }
            else {
                store.addAllValues(bean.withPosition(index), valuesToAdd);
                return index;
            }
        }
        else {
            List<Id> referencesToAdd = values.stream()
                    .map(PersistentEObject::from)
                    .peek(this::updateInstanceOf)
                    .map(refConverter::convert)
                    .collect(Collectors.toList());

            if (index == NO_INDEX) {
                return store.appendAllReferences(bean, referencesToAdd);
            }
            else {
                store.addAllReferences(bean.withPosition(index), referencesToAdd);
                return index;
            }
        }
    }

    @Override
    public void updateContainment(PersistentEObject object, EReference containerReference, PersistentEObject container) {
        updateInstanceOf(object);
        updateInstanceOf(container);

        final Id id = refConverter.convert(object);
        Optional<SingleFeatureBean> containerDesc = store.containerOf(id);

        if (!containerDesc.isPresent() || !Objects.equals(containerDesc.get().owner(), refConverter.convert(container))) {
            store.containerFor(id, SingleFeatureBean.from(container, containerReference));
        }
    }

    @Override
    public void removeContainment(PersistentEObject object) {
        updateInstanceOf(object);

        store.removeContainer(refConverter.convert(object));
    }

    @Nonnull
    @Override
    public Optional<EClass> resolveInstanceOf(Id id) {
        Optional<EClass> instanceOf = store.metaClassOf(id).map(ClassBean::get);

        if (!instanceOf.isPresent()) {
            throw new NoSuchElementException(String.format("Element '%s' does not have an associated EClass", id.toHexString()));
        }

        return instanceOf;
    }

    @Override
    public void updateInstanceOf(PersistentEObject object) {
        final Id id = refConverter.convert(object);

        // If the object is already present in the cache, then the meta-class is defined
        if (getCache().contains(id)) {
            return;
        }

        store.metaClassFor(id, ClassBean.from(object));
        refresh(object);
    }

    @Override
    public void copyTo(StoreAdapter target) {
        store.copyTo(target.store());
    }

    /**
     * Rebuilds the {@link PersistentEObject} from the specified {@code id}.
     *
     * @param id the identifier of the object to rebuild
     *
     * @return the object
     */
    @Nonnull
    private PersistentEObject rebuild(Id id) {
        final EClass eClass = resolveInstanceOf(id).<IllegalStateException>orElseThrow(IllegalStateException::new); // Should never happen
        return PersistenceFactory.getInstance().create(eClass, id);
    }
}
