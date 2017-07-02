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

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.BoundTransientBackend;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.adapter.SharedStoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreEMap;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The default implementation of a {@link PersistentEObject}.
 * <p>
 * This class extends {@link MinimalEStoreEObjectImpl} that delegates {@link EStructuralFeature} accesses
 * to an underlying {@link Store} that interacts with the database used to store the model.
 * <p>
 * {@link DefaultPersistentEObject}s is backend-agnostic, and is as an EMF-level element wrapper in all
 * existing database implementations.
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentEObject extends MinimalEStoreEObjectImpl implements PersistentEObject {

    /**
     * The identifier of this object.
     */
    @Nonnull
    private Id id;

    /**
     * The resource containing this object.
     */
    @Nullable
    private Resource.Internal resource;

    /**
     * The {@link StoreAdapter} where this object is stored.
     */
    private StoreAdapter store;

    /**
     * Constructs a new {@code DefaultPersistentEObject} with a generated {@link Id}, using {@link StringId#generate()}.
     */
    protected DefaultPersistentEObject() {
        this(StringId.generate());
    }

    /**
     * Constructs a new {@code DefaultPersistentEObject} with the given {@code id}.
     *
     * @param id the identifier of this object
     */
    protected DefaultPersistentEObject(Id id) {
        this.id = checkNotNull(id);

        Log.trace("DefaultPersistentEObject created with ID {0}", id);
    }

    @Nonnull
    @Override
    public Id id() {
        return id;
    }

    @Override
    public void id(Id id) {
        this.id = checkNotNull(id);
    }

    @Override
    @Nullable
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        StoreAdapter newStore = null;

        if (PersistentResource.class.isInstance(resource)) {
            // The resource store may have been changed (persistent <-> transient)
            newStore = PersistentResource.class.cast(resource).store();
        }
        else if (this.resource != resource) {
            newStore = createBoundStore(resource);
        }

        this.resource = resource;

        // Move contents from the previous store to the new
        if (nonNull(newStore) && newStore != store) {
            if (nonNull(store)) {
                if (nonNull(resource)) {
                    copyStore(store, newStore);
                }
                //else: Resource is unloading, no need to copy the stores

                // Close the previous store if it's not attached to a persistent resource
                // Otherwise the resource will close it
                if (!PersistentResource.class.isInstance(store.resource())) {
                    store.close();
                }
            }
            store = newStore;
        }
    }

    /**
     * Move the content from the {@code source} {@link StoreAdapter} to the {@code target}.
     *
     * @param source the store to copy
     * @param target the store where to store data
     */
    private void copyStore(StoreAdapter source, StoreAdapter target) {
        Optional<PersistentEObject> container = Optional.ofNullable(source.getContainer(this));

        if (container.isPresent()) {
            //noinspection ConstantConditions
            target.updateContainment(this, source.getContainingFeature(this), container.get());
        }
        else {
            target.removeContainment(this);
        }

        eClass().getEAllStructuralFeatures().forEach(feature -> {
            // Don't call EStore#isSet(): this acts the same as a call to EStore#get() with result checking
            if (!feature.isMany()) {
                Optional<Object> value = valueFrom(source, feature, EStore.NO_INDEX);
                value.ifPresent(o -> target.set(this, feature, EStore.NO_INDEX, o));
            }
            else {
                List<Object> allValues = allValuesFrom(source, feature);
                if (!allValues.isEmpty()) {
                    target.setAll(this, feature, allValues);
                }
            }
        });
    }

    /**
     * Retrieves the value from the {@code store}, and attach the value to the {@link #resource() resource} if
     * necessary.
     *
     * @param store   the store to look for the value
     * @param feature the feature
     * @param index   the index
     *
     * @return an {@link Optional} containing the value, or {@link Optional#empty()} if the value doesn't exist in
     * the {@code store}
     *
     * @see StoreAdapter#get(InternalEObject, EStructuralFeature, int)
     * @see #attach(Object)
     */
    @Nonnull
    private Optional<Object> valueFrom(StoreAdapter store, EStructuralFeature feature, int index) {
        Optional<Object> value = Optional.ofNullable(store.get(this, feature, index));

        if (value.isPresent() && EObjects.isReference(feature) && EObjects.asReference(feature).isContainment()) {
            return value.map(this::attach);
        }

        return value;
    }

    /**
     * Retrieves all values from the {@code store}, and attach each value to the {@link #resource() resource} if
     * necessary.
     *
     * @param store   the store to look for the values
     * @param feature the feature
     *
     * @return a list containing all values
     *
     * @see StoreAdapter#getAll(InternalEObject, EStructuralFeature)
     * @see #attach(Object)
     */
    @Nonnull
    private List<Object> allValuesFrom(StoreAdapter store, EStructuralFeature feature) {
        List<Object> values = store.getAll(this, feature);

        if (!values.isEmpty() && EObjects.isReference(feature) && EObjects.asReference(feature).isContainment()) {
            return values.stream().map(this::attach).collect(Collectors.toList());
        }

        return values;
    }

    /**
     * Attachs the {@code value} to {@link #resource()} if it is assignable to a {@link PersistentEObject}.
     *
     * @param value the value to attach
     *
     * @return the {@code value}
     *
     * @see #resource(Resource.Internal)
     */
    @Nullable
    private Object attach(@Nullable Object value) {
        PersistentEObject object = PersistentEObject.from(value);

        if (isNull(object)) {
            return value;
        }

        object.resource(resource);
        return object;
    }

    @Nullable
    @Override
    public Resource.Internal eInternalResource() {
        return Optional.ofNullable(resource).orElseGet(super::eInternalResource);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Display the identifier of this object instead of its hashCode
        sb.append(getClass().getName()).append('#').append(id);

        if (eIsProxy()) {
            sb.append(" (eProxyURI: ").append(eProxyURI());
            if (nonNull(eDynamicClass())) {
                sb.append(" eClass: ");
                sb.append(eDynamicClass().getClass().getCanonicalName());
            }
            sb.append(')');
        }
        else if (nonNull(eDynamicClass())) {
            sb.append(" (eClass: ");
            sb.append(eDynamicClass().getClass().getCanonicalName());
            sb.append(')');
        }
        else if (nonNull(eStaticClass())) {
            sb.append(" (eClass: ");
            sb.append(eStaticClass().getClass().getCanonicalName());
            sb.append(')');
        }

        return sb.toString();
    }

    /**
     * Defines the container of this object, and attaches it to the resource of its {@code newContainer}.
     *
     * @param newContainer          the new container of this object
     * @param newContainmentFeature the reference used to link the container to this object
     */
    private void setContainer(PersistentEObject newContainer, EReference newContainmentFeature) {
        eStore().updateContainment(this, newContainmentFeature, newContainer);
        resource(newContainer.resource());
    }

    /**
     * Removes the container of this object, and detaches it from its resource.
     */
    private void removeContainer() {
        eStore().removeContainment(this);
        resource(null);
    }

    /**
     * @see #setContainer(PersistentEObject, EReference)
     * @see #removeContainer()
     */
    @Override
    protected void eBasicSetContainer(@Nullable InternalEObject newContainer, int newContainerFeatureID) {
        if (nonNull(newContainer)) {
            PersistentEObject container = PersistentEObject.from(newContainer);
            EReference containmentFeature = eContainmentFeature(this, container, newContainerFeatureID);

            setContainer(container, containmentFeature);
        }
        else {
            removeContainer();
        }
    }

    @Override
    public void eSetDirectResource(@Nullable Resource.Internal resource) {
        resource(resource);

        super.eSetDirectResource(resource);
    }

    @Nonnull
    @Override
    public EList<EObject> eContents() {
        return DelegatedContentsList.newList(this);
    }

    @Nonnull
    @Override
    public StoreAdapter eStore() {
        if (isNull(store)) {
            store = createBoundStore(resource);
        }
        return store;
    }

    @Nullable
    @Override
    public Object dynamicGet(int dynamicFeatureId) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);

        if (!feature.isMany()) {
            return eStore().get(this, feature, EStore.NO_INDEX);
        }
        else {
            if (Objects.equals(feature.getEType().getInstanceClassName(), java.util.Map.Entry.class.getName())) {
                return new DelegatedStoreMap<>(feature);
            }
            else {
                return new DelegatedStoreList<>(feature);
            }
        }
    }

    @Override
    public void dynamicSet(int dynamicFeatureId, Object value) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);

        if (!feature.isMany()) {
            eStore().set(this, feature, EStore.NO_INDEX, value);
        }
        else {
            eStore().setAll(this, feature, (Collection<?>) value);
        }
    }

    @Override
    public void dynamicUnset(int dynamicFeatureId) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);

        eStore().unset(this, feature);
    }

    /**
     * Creates a new {@link Store} bound to this object.
     *
     * @param resource the resource to attach the store
     *
     * @return a new {@link Store}
     */
    @Nonnull
    private StoreAdapter createBoundStore(@Nullable Resource.Internal resource) {
        if (isNull(store) || PersistentResource.class.isInstance(store.resource())) {
            return SharedStoreAdapter.adapt(BoundTransientBackend.forId(id), resource);
        }
        else {
            store.resource(resource);
            return store;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!PersistentEObject.class.isInstance(o)) {
            return false;
        }

        PersistentEObject that = PersistentEObject.class.cast(o);
        return Objects.equals(id, that.id());
    }

    /**
     * A {@link List} that delegates its operations to the associated {@link Store}.
     * <p>
     * Instances of this class are created by {@link PersistentResource#getContents()} and allows to access the content
     * of a {@link PersistentResource} by lazily loading the elements.
     *
     * @param <E> the type of elements in this list
     */
    private static class DelegatedContentsList<E> extends EContentsEList<E> {

        /**
         * The instance of an empty {@code DelegatedContentsList}.
         */
        private static final DelegatedContentsList<?> EMPTY = new EmptyDelegatedContentsList<>();

        /**
         * The owner of this list.
         */
        private final PersistentEObject owner;

        /**
         * Constructs a new {@code DelegatedContentsList} with the given {@code owner} and {@code features}.
         *
         * @param owner    the owner of this list
         * @param features the containment features that are handled by this list
         */
        protected DelegatedContentsList(PersistentEObject owner, EStructuralFeature[] features) {
            super(owner, features);
            this.owner = owner;
        }

        /**
         * Returns an empty {@code DelegatedContentsList}.
         *
         * @param <E> the type of elements in this list
         *
         * @return an empty list
         */
        @SuppressWarnings("unchecked")
        public static <E> DelegatedContentsList<E> empty() {
            return DelegatedContentsList.class.cast(EMPTY);
        }

        /**
         * Creates a new {@code DelegatedContentsList} with the given {@code owner}.
         *
         * @param owner the owner of this list
         * @param <E>   the type of elements in this list
         *
         * @return a new list
         */
        public static <E> DelegatedContentsList<E> newList(PersistentEObject owner) {
            EStructuralFeature[] containments = EClassImpl.FeatureSubsetSupplier.class.cast(owner.eClass().getEAllStructuralFeatures()).containments();

            if (nonNull(containments)) {
                return new DelegatedContentsList<>(owner, containments);
            }

            return DelegatedContentsList.empty();
        }

        @Override
        @SuppressWarnings("unchecked")
        public E get(int index) {
            checkNotNull(eStructuralFeatures, "index=" + index + ", size=0");

            // Find the feature to look for
            int featureSize = 0;

            for (EStructuralFeature feature : eStructuralFeatures) {
                int localFeatureSize;

                localFeatureSize = feature.isMany()
                        ? owner.eStore().size(owner, feature)
                        : owner.eStore().isSet(owner, feature) ? 1 : 0;

                featureSize += localFeatureSize;

                if (featureSize > index) {
                    // The correct feature has been found
                    return (E) owner.eStore().get(owner, feature, (index - (featureSize - localFeatureSize)));
                }
            }

            throw new IndexOutOfBoundsException("index=" + index + ",size=" + featureSize);
        }

        /**
         * An empty {@code DelegatedContentsList}.
         *
         * @param <E> the type of elements in this list
         */
        private static class EmptyDelegatedContentsList<E> extends DelegatedContentsList<E> {

            /**
             * Constructs a new {@code EmptyDelegatedContentsList}.
             */
            public EmptyDelegatedContentsList() {
                //noinspection ConstantConditions
                super(null, null);
            }

            @Override
            public List<E> basicList() {
                return this;
            }
        }
    }

    /**
     * A {@link List} representing a multi-valued feature which behaves as a proxy and that delegates its operations to
     * the associated {@link Store}.
     *
     * @param <E> the type of elements in this list
     *
     * @see #eStore()
     */
    @ParametersAreNonnullByDefault
    private class DelegatedStoreList<E> extends EStoreEObjectImpl.BasicEStoreEList<E> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = 2630358403343923944L;

        /**
         * Constructs a new {@code DelegatedStoreList} with the given {@code feature}.
         *
         * @param feature the feature associated with this list
         */
        public DelegatedStoreList(EStructuralFeature feature) {
            super(DefaultPersistentEObject.this, feature);
        }

        @Override
        protected StoreAdapter eStore() {
            return DefaultPersistentEObject.this.eStore();
        }

        @Override
        // TODO Re-implement this method
        public void replaceAll(UnaryOperator<E> operator) {
            super.replaceAll(operator);
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            if (collection.size() <= 1) {
                return contains(collection.iterator().next());
            }

            List<Object> values = eStore().getAll(owner, eStructuralFeature);
            return values.containsAll(collection);
        }

        @Override
        // TODO Re-implement this method
        public boolean retainAll(Collection<?> collection) {
            return super.retainAll(collection);
        }

        @Nonnull
        @Override
        public Object[] toArray() {
            return eStore().toArray(owner, eStructuralFeature);
        }

        @Nonnull
        @Override
        public <T> T[] toArray(T[] array) {
            return eStore().toArray(owner, eStructuralFeature, array);
        }

        @Override
        public boolean contains(Object object) {
            return eStore().contains(owner, eStructuralFeature, object);
        }

        @Override
        public int indexOf(Object object) {
            return eStore().indexOf(owner, eStructuralFeature, object);
        }

        @Override
        public int lastIndexOf(Object object) {
            return eStore().lastIndexOf(owner, eStructuralFeature, object);
        }

        @Override
        protected boolean doAddAllUnique(Collection<? extends E> collection) {
            return doAddAllUnique(EStore.NO_INDEX, collection);
        }

        @Override
        protected boolean doAddAllUnique(int index, Collection<? extends E> collection) {
            ++modCount;

            if (collection.isEmpty()) {
                return false;
            }

            int i = eStore().addAll(owner, eStructuralFeature, index, collection);

            for (E object : collection) {
                didAdd(i, object);
                didChange();
                i++;
            }

            return true;
        }

        @Override
        // TODO Re-implement this method
        public boolean removeAll(Collection<?> collection) {
            return super.removeAll(collection);
        }

        /**
         * {@inheritDoc}
         * <p>
         * Override the default implementation which relies on {@link #size()} to compute the insertion index by
         * providing a custom {@link StoreAdapter#NO_INDEX} features, meaning that the {@link
         * fr.inria.atlanmod.neoemf.data.Backend} has to append the result to the existing list.
         * <p>
         * This behavior allows fast write operation on {@link fr.inria.atlanmod.neoemf.data.Backend} which
         * would otherwise need to deserialize the underlying list to add the element at the specified index.
         */
        @Override
        public boolean add(E object) {
            if (isUnique() && contains(object)) {
                return false;
            }
            else {
                // index = NO_INDEX results as a call to #append() in store, without checking the size
                addUnique(EStore.NO_INDEX, object);
                return true;
            }
        }
    }

    /**
     * A {@link java.util.Map} representing a multi-valued feature which behaves as a proxy and that delegates its
     * operations to the associated {@link Store}.
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     *
     * @see #eStore()
     */
    private class DelegatedStoreMap<K, V> extends EcoreEMap<K, V> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = 9173875843551606055L;

        /**
         * Constructs a {@code DelegatedStoreMap} with the given {@code owner} and {@code feature}.
         *
         * @param feature the feature associated with this map
         */
        public DelegatedStoreMap(EStructuralFeature feature) {
            super(EClass.class.cast(feature.getEType()), Entry.class, null);

            this.delegateEList = new EntryList(feature);
            this.size = delegateEList.size();
        }

        /**
         * A {@link List} that holds entries of this map.
         */
        private class EntryList extends EStoreEObjectImpl.BasicEStoreEList<Entry<K, V>> {

            @SuppressWarnings("JavaDoc")
            private static final long serialVersionUID = 3373155561238654363L;

            /**
             * Constructs a new {@code EntryList} with the given {@code feature}.
             *
             * @param feature the feature associated with this list
             */
            public EntryList(EStructuralFeature feature) {
                super(DefaultPersistentEObject.this, feature);
            }

            @Override
            protected void didSet(int index, Entry<K, V> newObject, Entry<K, V> oldObject) {
                didRemove(index, oldObject);
                didAdd(index, newObject);
            }

            @Override
            protected void didAdd(int index, Entry<K, V> newObject) {
                DelegatedStoreMap.this.doPut(newObject);
            }

            @Override
            protected void didRemove(int index, Entry<K, V> oldObject) {
                DelegatedStoreMap.this.doRemove(oldObject);
            }

            @Override
            protected void didClear(int size, Object[] oldObjects) {
                DelegatedStoreMap.this.doClear();
            }

            @Override
            protected void didMove(int index, Entry<K, V> movedObject, int oldIndex) {
                DelegatedStoreMap.this.doMove(movedObject);
            }
        }
    }
}
