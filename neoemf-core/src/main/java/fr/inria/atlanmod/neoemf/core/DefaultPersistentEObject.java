/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.internal.ContentsCopier;
import fr.inria.atlanmod.neoemf.core.internal.collect.AllContentsIterator;
import fr.inria.atlanmod.neoemf.core.internal.collect.ContentsList;
import fr.inria.atlanmod.neoemf.core.internal.collect.DirectStoreFeatureMap;
import fr.inria.atlanmod.neoemf.core.internal.collect.DirectStoreList;
import fr.inria.atlanmod.neoemf.core.internal.collect.DirectStoreMap;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.im.BoundInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.adapter.TransientStoreAdapter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.atlanmod.commons.Lazy;
import org.atlanmod.commons.LazyReference;
import org.atlanmod.commons.annotation.VisibleForTesting;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkInstanceOf;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default implementation of a {@link PersistentEObject}.
 * <p>
 * This class extends {@link org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl} that delegates {@link
 * org.eclipse.emf.ecore.EStructuralFeature} accesses to an underlying {@link fr.inria.atlanmod.neoemf.data.store.Store}
 * that interacts with the database used to store the model.
 * <p>
 * {@code DefaultPersistentEObject}s are backend-agnostic, and are as an EMF-level element wrapper in all existing
 * database implementations.
 *
 * @see TransientStoreAdapter
 * @see BoundInMemoryBackend
 * @see PersistenceFactory
 */
//TODO Save values of transient features in a `BoundInMemoryBackend` instead of a persistent one
@ParametersAreNonnullByDefault
public class DefaultPersistentEObject extends MinimalEStoreEObjectImpl implements PersistentEObject {

    /**
     * The {@link StoreAdapter} where this object is stored.
     */
    @Nonnull
    private final Lazy<StoreAdapter> lazyStore = Lazy.with(() -> getOrCreateStore(resource()));

    /**
     * The cached container of this object.
     */
    @Nonnull
    private final LazyReference<PersistentEObject> lazyContainer = LazyReference.soft(() -> eStore().getContainer(this));

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
     * {@code true} if this object is being attached to a resource. This avoids an infinite loop when copying a fully
     * transient backend to a persistent one.
     * <p>
     * When {@code true}, all calls to {@link #resource(Resource.Internal)} will be rejected.
     *
     * @see #resource(Resource.Internal)
     */
    private boolean locked;

    /**
     * Constructs a new {@code DefaultPersistentEObject} with an undefined {@link Id}.
     * <p>
     * It does not generate the identifier: it will be generated on the first call to {@link #id()} if it was not
     * previously defined with {@link #id(Id)}.
     *
     * @see Id#UNDEFINED
     */
    @VisibleForTesting
    public DefaultPersistentEObject() {
        this(Id.UNDEFINED);
    }

    /**
     * Constructs a new {@code DefaultPersistentEObject} with the given {@code id}.
     *
     * @param id the identifier of this object
     */
    protected DefaultPersistentEObject(Id id) {
        this.id = checkNotNull(id, "id");
    }

    @Nonnull
    @Override
    public Id id() {
        if (id == Id.UNDEFINED) { // Id#UNDEFINED is immutable
            id(Id.getProvider().generate());
        }
        return id;
    }

    @Override
    public void id(Id newId) {
        this.id = checkNotNull(newId, "newId");
    }

    @Override
    @Nullable
    public Resource.Internal resource() {
        return resource;
    }

    @Override
    public void resource(@Nullable Resource.Internal newResource) {
        if (locked || resource == newResource) {
            return;
        }

        locked = true;
        resource = newResource;

        // Refresh the container if necessary
        if (lazyStore.isLoaded()) {
            lazyContainer.get();
        }

        // Define and copy the store if necessary
        StoreAdapter newStore = getOrCreateStore(newResource);
        refreshStore(newStore);

        locked = false;
    }

    @Override
    protected void eBasicSetContainer(@Nullable InternalEObject newContainer, int newContainerFeatureID) {
        if (nonNull(newContainer)) {
            PersistentEObject container = PersistentEObject.from(newContainer);
            EReference containmentFeature = eContainmentFeature(this, container, newContainerFeatureID);

            lazyContainer.update(container);
            eStore().updateContainment(this, containmentFeature, container);
            resource(container.resource());
        }
        else {
            lazyContainer.update(null);
            eStore().removeContainment(this);
            resource(null);
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
        return ContentsList.newList(this);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public TreeIterator<EObject> eAllContents() {
        return (TreeIterator) new AllContentsIterator<>(this);
    }

    @Nullable
    @Override
    public Resource.Internal eInternalResource() {
        return Optional.ofNullable(resource).orElseGet(super::eInternalResource);
    }

    @Override
    public void eSetStore(EStore store) {
        checkNotNull(store, "store");
        checkInstanceOf(store, StoreAdapter.class, "store must be instance of %d", StoreAdapter.class.getName());
        refreshStore((StoreAdapter) store);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Display the identifier of this object instead of its hashCode
        sb.append(getClass().getName()).append('#').append(id().toHexString());

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

    @Nonnull
    @Override
    public StoreAdapter eStore() {
        return lazyStore.get();
    }

    @Nullable
    @Override
    public Object dynamicGet(int dynamicFeatureId) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);

        if (FeatureMapUtil.isFeatureMap(feature)) {
            return new DirectStoreFeatureMap(this, feature);
        }

        if (feature.isMany()) {
            if (Objects.equals(feature.getEType().getInstanceClassName(), java.util.Map.Entry.class.getName())) {
                return new DirectStoreMap<>(this, feature);
            }
            else {
                return new DirectStoreList<>(this, feature);
            }
        }

        return eStore().get(this, feature, EStore.NO_INDEX);
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

    @Override
    protected boolean eDynamicIsSet(int dynamicFeatureId, EStructuralFeature feature) {
        if (dynamicFeatureId < 0) {
            return eOpenIsSet(feature);
        }
        else {
            return eStore().isSet(this, feature);
        }
    }

    @Override
    public PersistentEObject eInternalContainer() {
        return lazyContainer.get();
    }

    @Override
    public int eContainerFeatureID() {
        final PersistentEObject container = eInternalContainer();

        if (isNull(container)) {
            return 0;
        }

        final EReference containingReference = eStore().getContainingFeature(this);

        return Optional.ofNullable(containingReference.getEOpposite())
                .map(or -> eClass().getFeatureID(or))
                .orElseGet(() -> EOPPOSITE_FEATURE_BASE - container.eClass().getFeatureID(containingReference));
    }

    /**
     * Retrieves or creates a new store bound to this object.
     *
     * @param resource the resource to attach the store
     *
     * @return a new store
     *
     * @see #resource(Resource.Internal)
     * @see #eStore()
     */
    @Nonnull
    private StoreAdapter getOrCreateStore(@Nullable Resource.Internal resource) {
        // Use the store of the resource
        if (resource instanceof PersistentResource) {
            final PersistentResource persistentResource = (PersistentResource) resource;
            return persistentResource.eStore();
        }
        // Adapt the current store
        else if (lazyStore.isLoaded()) {
            StoreAdapter currentStore = lazyStore.get();
            currentStore.resource(resource);
            return currentStore;
        }
        // Create a new transient store
        else {
            ImmutableConfig config = new BaseConfig<>();

            Backend backend = new BoundInMemoryBackend(id());
            Store baseStore = StoreFactory.getInstance().createStore(backend, config);
            return new TransientStoreAdapter(baseStore, resource);
        }
    }

    /**
     * Defines the new store of this object, and copies the content from the existing store if necessary.
     *
     * @param newStore the store of this object
     *
     * @see #resource()
     */
    private void refreshStore(StoreAdapter newStore) {
        checkNotNull(newStore, "newStore");

        if (lazyStore.isLoaded()) {
            StoreAdapter currentStore = lazyStore.get();

            if (!Objects.equals(currentStore.store().backend(), newStore.store().backend())) {
                // Copy if the resource is not being unloaded
                if (nonNull(resource)) {
                    new ContentsCopier(this).copy(currentStore, newStore);
                }

                // If the resource is persistent, it will close its store
                if (!PersistentResource.isPersistent(currentStore.resource())) {
                    currentStore.close();
                }
            }
        }

        lazyStore.update(newStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id());
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersistentEObject)) {
            return false;
        }

        PersistentEObject that = (PersistentEObject) o;
        return Objects.equals(id(), that.id());
    }
}
