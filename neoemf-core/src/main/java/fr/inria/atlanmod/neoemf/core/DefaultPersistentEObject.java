/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.commons.LazyReference;
import fr.inria.atlanmod.commons.annotation.VisibleForTesting;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.internal.AllContentsIterator;
import fr.inria.atlanmod.neoemf.core.internal.ContentsCopier;
import fr.inria.atlanmod.neoemf.core.internal.ContentsList;
import fr.inria.atlanmod.neoemf.core.internal.DirectStoreFeatureMap;
import fr.inria.atlanmod.neoemf.core.internal.DirectStoreList;
import fr.inria.atlanmod.neoemf.core.internal.DirectStoreMap;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.im.BoundInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.adapter.TransientStoreAdapter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

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

import static fr.inria.atlanmod.commons.Preconditions.checkInstanceOf;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * The default implementation of a {@link PersistentEObject}.
 * <p>
 * This class extends {@link MinimalEStoreEObjectImpl} that delegates {@link EStructuralFeature} accesses to an
 * underlying {@link Store} that interacts with the database used to store the model.
 * <p>
 * {@link DefaultPersistentEObject}s is backend-agnostic, and is as an EMF-level element wrapper in all existing
 * database implementations.
 *
 * @see TransientStoreAdapter
 * @see BoundInMemoryBackend
 * @see PersistenceFactory
 */
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
    @VisibleForTesting
    public DefaultPersistentEObject(Id id) {
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
        return TreeIterator.class.cast(new AllContentsIterator<>(this));
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
        refreshStore(StoreAdapter.class.cast(store));
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
    public PersistentEObject eInternalContainer() {
        return lazyContainer.get();
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
        if (PersistentResource.class.isInstance(resource)) {
            return PersistentResource.class.cast(resource).eStore();
        }
        // Adapt the current store
        else if (lazyStore.isLoaded()) {
            StoreAdapter currentStore = lazyStore.get();
            currentStore.resource(resource);
            return currentStore;
        }
        // Create a new transient store
        else {
            ImmutableConfig config = BaseConfig.newConfig();

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
                if (!PersistentResource.class.isInstance(currentStore.resource())) {
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
        if (!PersistentEObject.class.isInstance(o)) {
            return false;
        }

        PersistentEObject that = PersistentEObject.class.cast(o);
        return Objects.equals(id(), that.id());
    }
}
