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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.OwnedTransientStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
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

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The default implementation of a {@link PersistentEObject}.
 * <p>
 * This class extends {@link MinimalEStoreEObjectImpl} that delegates {@link EStructuralFeature} accesses
 * to an underlying {@link EStore} that interacts with the database used to store the model.
 * <p>
 * {@link DefaultPersistentEObject}s is backend-agnostic, and is as an EMF-level element wrapper in all
 * existing database implementations.
 */
public class DefaultPersistentEObject extends MinimalEStoreEObjectImpl implements PersistentEObject {

    /**
     * ???
     */
    private static final int UNSETTED_FEATURE_ID = -1;

    /**
     * The identifier of this object.
     */
    @Nonnull
    private Id id;

    /**
     * The resource containing this object.
     */
    private Resource.Internal resource;

    /**
     * Whether this object is mapped to an entity in a database.
     */
    private boolean isMapped;

    /**
     * The internal cached value of the eContainer.
     * <p>
     * This information should be also maintained in the underlying {@link Store}.
     */
    private InternalEObject eContainer;

    /**
     * ???
     */
    private int eContainerFeatureId;

    /**
     * ???
     */
    private Store store;

    /**
     * Constructs a new {@code DefaultPersistentEObject} with a generated {@link Id} using {@link StringId#generate()}.
     */
    protected DefaultPersistentEObject() {
        this(StringId.generate());
    }

    /**
     * Constructs a new {@code DefaultPersistentEObject} with the given {@code id}.
     *
     * @param id the identifier of this object
     */
    protected DefaultPersistentEObject(@Nonnull Id id) {
        this.id = checkNotNull(id);
        this.eContainerFeatureId = UNSETTED_FEATURE_ID;
        this.isMapped = false;
    }

    @Nonnull
    @Override
    public Id id() {
        return id;
    }

    @Override
    public void id(@Nonnull Id id) {
        this.id = checkNotNull(id);
    }

    @Override
    public boolean isMapped() {
        return isMapped;
    }

    @Override
    public PersistentEObject setMapped(boolean mapped) {
        this.isMapped = mapped;
        return this;
    }

    @Override
    @Nullable
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public void resource(Resource.Internal resource) {
        this.resource = resource;

        EStore previousStore = store;

        // Set the new store
        if (resource instanceof PersistentResource) {
            store = ((PersistentResource) resource).store();
        }
        else {
            store = new OwnedTransientStore(this);
        }

        // Move contents from the previous store to the new
        if (nonNull(previousStore) && nonNull(store) && store != previousStore) {
            copyStore(previousStore, store);
        }
    }

    /**
     * Move the content from the {@code source} {@link org.eclipse.emf.ecore.InternalEObject.EStore} to the
     * {@code target}.
     *
     * @param source the {@link org.eclipse.emf.ecore.InternalEObject.EStore} to copy
     * @param target the {@link org.eclipse.emf.ecore.InternalEObject.EStore} where to store data
     */
    private void copyStore(EStore source, EStore target) {
        // If the new store is different, initialize the new store with the data stored in the old store
        for (EStructuralFeature feature : eClass().getEAllStructuralFeatures()) {
            if (source.isSet(this, feature)) {
                if (!feature.isMany()) {
                    Optional.ofNullable(adaptValue(source, feature, Store.NO_INDEX))
                            .ifPresent(v -> target.set(this, feature, Store.NO_INDEX, v));
                }
                else {
                    target.clear(this, feature);

                    IntStream.range(0, source.size(this, feature)).forEach(i ->
                            Optional.ofNullable(adaptValue(source, feature, i))
                                    .ifPresent(v -> target.add(this, feature, i, v)));
                }
            }
        }
    }

    /**
     * Retrieves the value from the {@code store}.
     *
     * @param store   the store to look for the value
     * @param feature the feature
     * @param index   the index. If {@code feature.isMany() == true}, then it's ignored.
     *
     * @return the adapted value, or {@code null} if the value doesn't exist in the {@code store}
     *
     * @see EStore#get(InternalEObject, EStructuralFeature, int)
     */
    @Nullable
    private Object adaptValue(EStore store, EStructuralFeature feature, int index) {
        Object value = store.get(this, feature, index);

        // FIXME Same code as in `DirectWriteStore#object()`
        if (nonNull(value)) {
            if (feature instanceof EReference) {
                EReference eRef = (EReference) feature;
                if (eRef.isContainment()) {
                    PersistentEObject internalElement = PersistentEObject.from(value);
                    if (internalElement.resource() != resource()) {
                        internalElement.resource(resource());
                    }
                }
            }
        }
        return value;
    }

    @Override
    public EObject eContainer() {
        EObject container;
        if (resource instanceof PersistentResource) {
            /*
             * If the resource is not distributed and if the value of the eContainer field is set, it is not needed to
             * get it from the backend. This is not true in a distributed context when another client can the database
             * without notifying others.
             */
            if (!((PersistentResource) resource).isDistributed() && nonNull(eContainer)) {
                container = eContainer;
            }
            else {
                InternalEObject internalContainer = eStore().getContainer(this);
                eBasicSetContainer(internalContainer);
                eBasicSetContainerFeatureID(eContainerFeatureID());
                container = internalContainer;
            }
        }
        else {
            container = super.eContainer();
        }
        return container;
    }

    @Override
    public Resource eResource() {
        return Optional.<Resource>ofNullable(resource)
                .orElseGet(super::eResource);
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
    protected void eBasicSetContainer(InternalEObject eContainer) {
        this.eContainer = eContainer;
        if (nonNull(eContainer) && eContainer.eResource() != resource) {
            resource((Resource.Internal) this.eContainer.eResource());
        }
    }

    @Override
    protected void eBasicSetContainerFeatureID(int eContainerFeatureId) {
        this.eContainerFeatureId = eContainerFeatureId;
    }

    @Override
    public EList<EObject> eContents() {
        return DelegatedContentsList.newList(this);
    }

    @Override
    public EStore eStore() {
        if (isNull(store)) {
            store = new OwnedTransientStore(this);
        }
        return store;
    }

    @Override
    protected boolean eIsCaching() {
        return false;
    }

    @Override
    public Object dynamicGet(int dynamicFeatureId) {
        Object value;

        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
        EClassifier eType = feature.getEType();

        if (feature.isMany()) {
            if (Objects.equals(eType.getInstanceClassName(), java.util.Map.Entry.class.getName())) {
                value = new DelegatedStoreMap<>(feature);
            }
            else {
                value = new DelegatedStoreList<>(feature);
            }
        }
        else {
            value = eStore().get(this, feature, Store.NO_INDEX);
        }

        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void dynamicSet(int dynamicFeatureId, Object value) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);

        if (feature.isMany()) {
            // TODO This operation should be atomic. Reset the old value in case the operation fails in the middle
            eStore().unset(this, feature);

            List<Object> collection = (List<Object>) value;
            IntStream.range(0, collection.size()).forEach(i -> eStore().set(this, feature, i, collection.get(i)));
        }
        else {
            eStore().set(this, feature, Store.NO_INDEX, value);
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
    @Override
    public InternalEObject eInternalContainer() {
        // Don't load the container from the store here: it creates an important overhead and performance loss.
        // [Update 21-02-2017] Don't call super.eInternalContainer() either: it will delegate to the store.
        return eContainer;
//        return isNull(eContainer) ? super.eInternalContainer() : eContainer;
    }

    @Override
    public int eContainerFeatureID() {
        if (eContainerFeatureId == UNSETTED_FEATURE_ID && resource instanceof PersistentResource) {
            EReference containingFeature = (EReference) eStore().getContainingFeature(this);
            if (nonNull(containingFeature)) {
                EReference oppositeFeature = containingFeature.getEOpposite();
                if (nonNull(oppositeFeature)) {
                    eBasicSetContainerFeatureID(eClass().getFeatureID(oppositeFeature));
                }
                else {
                    eBasicSetContainerFeatureID(EOPPOSITE_FEATURE_BASE - eInternalContainer().eClass().getFeatureID(containingFeature));
                }
            }
        }
        return eContainerFeatureId;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (isNull(o) || getClass() != o.getClass()) {
            return false;
        }
        PersistentEObject other = (PersistentEObject) o;
        return Objects.equals(id, other.id());
    }

    /**
     * An {@link EContentsEList} implementation that delegates its operations to the associated {@link PersistentStore}.
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
        protected DelegatedContentsList(EObject owner, EStructuralFeature[] features) {
            super(owner, features);
            this.owner = PersistentEObject.from(owner);
        }

        /**
         * Returns an empty {@code DelegatedContentsList}.
         *
         * @param <E> the type of elements in this list
         *
         * @return an empty list
         */
        @SuppressWarnings("unchecked") // Unchecked cast: 'DelegatedContentsList<?>' to 'DelegatedContentsList<...>'
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
            DelegatedContentsList<E> list;

            EStructuralFeature[] containments = ((EClassImpl.FeatureSubsetSupplier) owner.eClass().getEAllStructuralFeatures()).containments();
            if (isNull(containments)) {
                list = DelegatedContentsList.empty();
            }
            else {
                list = new DelegatedContentsList<>(owner, containments);
            }

            return list;
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
                super(null, null);
            }

            @Override
            public List<E> basicList() {
                return this;
            }
        }
    }

    /**
     * ???
     *
     * @param <E> the type of elements in this list
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

        @Override
        public boolean contains(Object object) {
            return delegateContains(object);
        }

        /**
         * {@inheritDoc}
         * <p>
         * Overrides the default implementation which relies on {@link #size()} and {@link EStore#get(InternalEObject, EStructuralFeature, int)}
         * by delegating the call to the {@link EStore#toArray(InternalEObject, EStructuralFeature)} implementation.
         */
        @Override
        public Object[] toArray() {
            return eStore().toArray(owner, getEStructuralFeature());
        }

        /**
         * {@inheritDoc}
         * <p>
         * Overrides the default implementation which relies on {@link #size()} and {@link EStore#get(InternalEObject, EStructuralFeature, int)}
         * by delegating the call to the {@link EStore#toArray(InternalEObject, EStructuralFeature, Object[])} implementation.
         */
        @Override
        public <T> T[] toArray(T[] array) {
            return eStore().toArray(owner, getEStructuralFeature(), array);
        }

        /**
         * {@inheritDoc}
         * <p>
         * Override the default implementation which relies on {@link #size()} to compute the insertion index by
         * providing a custom {@link Store#NO_INDEX} features, meaning that the {@link PersistenceBackend} has
         * to append the result to the existing list.
         * <p>
         * This behavior allows fast write operation on {@link PersistenceBackend} which would otherwise need to
         * deserialize the underlying list to add the element at the specified index.
         */
        @Override
        public boolean add(E object) {
            if (isUnique() && contains(object)) {
                return false;
            }
            else {
                if (eStructuralFeature instanceof EAttribute) {
                    addUnique(object);
                }
                else {
                    int index = size() == 0 ? 0 : Store.NO_INDEX;
                    addUnique(index, object);
                }
                return true;
            }
        }
    }

    /**
     * ???
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
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
         * ???
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
