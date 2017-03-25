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

import fr.inria.atlanmod.neoemf.data.BoundedTransientBackend;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreAdapter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.notify.NotificationChain;
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
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
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
     * The identifier of the {@link EReference} used to link this object to its container, when this object has not
     * container.
     */
    private static final int UNSETTED_REFERENCE_ID = -1;

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
     * Whether this object is mapped to an entity in a {@link fr.inria.atlanmod.neoemf.data.Backend}.
     */
    private boolean isPersistent;

    /**
     * The internal cached value of the container.
     * <p>
     * This information should be also maintained in the {@link #store}.
     */
    @Nullable
    private InternalEObject container;

    /**
     * The identifier of the {@link EReference} used to link this object to its container.
     */
    private int containerReferenceId;

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
        this.containerReferenceId = UNSETTED_REFERENCE_ID;
        this.isPersistent = false;
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
    public boolean isPersistent() {
        return isPersistent;
    }

    @Override
    public PersistentEObject isPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
        return this;
    }

    @Override
    @Nullable
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        StoreAdapter newStore = null;

        if (resource instanceof PersistentResource) {
            // The resource store may have been changed (persistent <-> transient)
            newStore = ((PersistentResource) resource).store();
        }
        else if (this.resource != resource) {
            newStore = createBoundedStore(resource);
        }

        this.resource = resource;

        // Move contents from the previous store to the new
        if (nonNull(newStore) && newStore != store) {
            if (nonNull(store)) {
                copyStore(store, newStore);

                // Close the previous store if it's not attached to a persistent resource
                // Otherwise the resource will close it
                if (!store.isPersistent()) {
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
    // TODO Move the copy at DataMapper level
    private void copyStore(StoreAdapter source, StoreAdapter target) {
        eClass().getEAllStructuralFeatures().forEach(f -> {
            if (source.isSet(this, f)) {
                if (!f.isMany()) {
                    getValueFrom(source, f, EStore.NO_INDEX)
                            .ifPresent(v -> target.set(this, f, EStore.NO_INDEX, v));
                }
                else {
                    target.clear(this, f);

                    IntStream.range(0, source.size(this, f)).forEach(i ->
                            getValueFrom(source, f, i)
                                    .ifPresent(v -> target.add(this, f, i, v)));
                }
            }
        });
    }

    /**
     * Retrieves the value from the {@code store}, and attach the value to {@link #resource()} if necessary.
     *
     * @param store   the store to look for the value
     * @param feature the feature
     * @param index   the index
     *
     * @return an {@link Optional} containing the adapted value, or {@link Optional#empty()} if the value doesn't exist
     * in the {@code store}
     *
     * @see StoreAdapter#get(InternalEObject, EStructuralFeature, int)
     */
    @Nonnull
    private Optional<Object> getValueFrom(StoreAdapter store, EStructuralFeature feature, int index) {
        Optional<Object> value = Optional.ofNullable(store.get(this, feature, index));

        if (value.isPresent() && feature instanceof EReference && ((EReference) feature).isContainment()) {
            PersistentEObject object = PersistentEObject.from(value.get());
            if (object.resource() != resource) {
                object.resource(resource);
            }
        }
        return value;
    }

    @Nullable
    @Override
    public EObject eContainer() {
        return Optional.<EObject>ofNullable(container).orElseGet(() -> {
            Optional<EObject> internalContainer = Optional.ofNullable(eStore().getContainer(this));
            if (internalContainer.isPresent()) {
                eBasicSetContainer((InternalEObject) internalContainer.get(), eContainerFeatureID());
            }
            else {
                eBasicSetContainer(null, UNSETTED_REFERENCE_ID);
            }

            return internalContainer.<EObject>orElseGet(super::eContainer);
        });
    }

    @Nullable
    @Override
    public Resource eResource() {
        return Optional.<Resource>ofNullable(resource).orElseGet(super::eResource);
    }

    @Override
    public NotificationChain eSetResource(Resource.Internal resource, NotificationChain notifications) {
        resource(resource);
        return super.eSetResource(resource, notifications);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName()).append('@').append(Integer.toHexString(hashCode()));

        if (eIsProxy()) {
            sb.append(" (eProxyURI: ").append(eProxyURI());
            if (nonNull(eDynamicClass())) {
                sb.append(" eClass: ").append(eDynamicClass());
            }
            sb.append(')');
        }
        else if (nonNull(eDynamicClass())) {
            sb.append(" (eClass: ").append(eDynamicClass()).append(')');
        }
        else if (nonNull(eStaticClass())) {
            sb.append(" (eClass: ").append(eStaticClass()).append(')');
        }

        return sb.toString();
    }

    @Override
    protected void eBasicSetContainer(@Nullable InternalEObject container) {
        this.container = container;

        // Update the containment in the store
        eStore().updateContainment(this, nonNull(container) ? (EReference) eContainingFeature() : null, PersistentEObject.from(container));

        // Update the resource according to the container
        if (nonNull(container) && container.eResource() != resource) {
            resource((Resource.Internal) container.eResource());
        }
    }

    @Override
    protected void eBasicSetContainerFeatureID(int containerReferenceId) {
        // This method is always called before #eBasicSetContainer(InternalEObject) : no need to update the store here
        this.containerReferenceId = containerReferenceId;
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
            store = createBoundedStore(resource);
        }
        return store;
    }

    @Override
    protected boolean eIsCaching() {
        return false;
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
            // TODO This operation should be atomic.
            // Reset the old value in case the operation fails in the middle
            eStore().unset(this, feature);

            @SuppressWarnings("unchecked")
            List<Object> collection = (List<Object>) value;

            IntStream.range(0, collection.size()).forEach(i ->
                    eStore().set(this, feature, i, collection.get(i)));
        }
    }

    @Override
    public void dynamicUnset(int dynamicFeatureId) {
        eStore().unset(this, eDynamicFeature(dynamicFeatureId));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the container of this {@link PersistentEObject}.
     * <p>
     * Do not return the same value as standard EMF implementation if the container has not been accessed with the
     * public method {@link #eContainer()} before.
     *
     * @return the container of this object.
     */
    @Nullable
    @Override
    public InternalEObject eInternalContainer() {
        return Optional.ofNullable(container).orElseGet(super::eInternalContainer);
    }

    @Override
    public int eContainerFeatureID() {
        if (containerReferenceId == UNSETTED_REFERENCE_ID) {
            Optional.ofNullable(eStore().getContainingFeature(this)).ifPresent(cf -> {
                EReference oppositeReference = cf.getEOpposite();
                if (nonNull(oppositeReference)) {
                    eBasicSetContainerFeatureID(eClass().getFeatureID(oppositeReference));
                }
                else if (nonNull(container)) {
                    eBasicSetContainerFeatureID(EOPPOSITE_FEATURE_BASE - container.eClass().getFeatureID(cf));
                }
                else {
                    throw new IllegalStateException("The containing reference is defined, but cannot be retrieved");
                }
            });
        }
        return containerReferenceId;
    }

    /**
     * Creates a new {@link Store} bounded to this object.
     *
     * @param resource the resource to attach the store
     *
     * @return a new {@link Store}
     */
    @Nonnull
    private StoreAdapter createBoundedStore(@Nullable Resource.Internal resource) {
        if (isNull(store) || store.isPersistent()) {
            return StoreAdapter.adapt(new DirectWriteStore(new BoundedTransientBackend(id), resource));
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersistentEObject)) {
            return false;
        }

        PersistentEObject that = (PersistentEObject) o;
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
    private static class DelegatedContentsList<E> extends EContentsEList<E> implements EList<E>, InternalEList<E> {

        /**
         * The instance of an empty {@code DelegatedContentsList}.
         */
        private static final DelegatedContentsList<?> EMPTY = new EmptyDelegatedContentsList<>();

        /**
         * The owner of this list.
         */
        private final PersistentEObject owner;

        /**
         * Constructs a new {@code DelegatedContentsList} with the given {@code owner}.
         *
         * @param owner the owner of this list
         */
        protected DelegatedContentsList(PersistentEObject owner) {
            super(owner);
            this.owner = owner;
        }

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
        @SuppressWarnings("unchecked") // Unchecked cast: 'DelegatedContentsList<?>' to 'DelegatedContentsList<E>'
        public static <E> DelegatedContentsList<E> empty() {
            return (DelegatedContentsList<E>) EMPTY;
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
            EStructuralFeature[] containments = ((EClassImpl.FeatureSubsetSupplier) owner.eClass().getEAllStructuralFeatures()).containments();

            if (isNull(containments)) {
                return DelegatedContentsList.empty();
            }
            else {
                return new DelegatedContentsList<>(owner, containments);
            }
        }

        @Override
        @SuppressWarnings("unchecked") // Unchecked cast: 'Object' to 'E'
        public E get(int index) {
            checkNotNull(eStructuralFeatures, "index=" + index + ", size=0");

            // Find the feature to look for
            int featureSize = 0;

            for (EStructuralFeature feature : eStructuralFeatures) {
                int localFeatureSize;

                if (feature.isMany()) {
                    localFeatureSize = owner.eStore().size(owner, feature);
                }
                else {
                    localFeatureSize = owner.eStore().isSet(owner, feature) ? 1 : 0;
                }

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

        @Nonnull
        @Override
        public Object[] toArray() {
            return delegateToArray();
        }

        @Nonnull
        @Override
        public <T> T[] toArray(T[] array) {
            return delegateToArray(array);
        }

        @Override
        public boolean contains(Object object) {
            return delegateContains(object);
        }

        @Override
        public int indexOf(Object object) {
            return delegateIndexOf(object);
        }

        @Override
        public int lastIndexOf(Object object) {
            return delegateLastIndexOf(object);
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
                // index = NO_INDEX results as a call to #append() in store
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
            super((EClass) feature.getEType(), Entry.class, null);

            this.delegateEList = new EntriesList(DefaultPersistentEObject.this, feature);
            this.size = delegateEList.size();
        }

        /**
         * A {@link List} that holds entries of this map.
         */
        private class EntriesList extends EStoreEObjectImpl.BasicEStoreEList<Entry<K, V>> {

            @SuppressWarnings("JavaDoc")
            private static final long serialVersionUID = 3373155561238654363L;

            /**
             * Constructs a new {@code EntriesList} with the given {@code feature}.
             *
             * @param owner   the owner of this list
             * @param feature the feature associated with this list
             */
            public EntriesList(PersistentEObject owner, EStructuralFeature feature) {
                super(owner, feature);
            }

            @Override
            protected void didSet(int index, Entry<K, V> newObject, Entry<K, V> oldObject) {
                didRemove(index, oldObject);
                didAdd(index, newObject);
            }

            @Override
            protected void didAdd(int index, Entry<K, V> newObject) {
                doPut(newObject);
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
