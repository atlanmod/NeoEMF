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

import fr.inria.atlanmod.commons.LazyObject;
import fr.inria.atlanmod.commons.LazyReference;
import fr.inria.atlanmod.neoemf.core.internal.ContentsCopier;
import fr.inria.atlanmod.neoemf.core.internal.ContentsList;
import fr.inria.atlanmod.neoemf.core.internal.LazyStoreFeatureMap;
import fr.inria.atlanmod.neoemf.core.internal.LazyStoreList;
import fr.inria.atlanmod.neoemf.core.internal.LazyStoreMap;
import fr.inria.atlanmod.neoemf.data.BoundTransientBackend;
import fr.inria.atlanmod.neoemf.data.TransientBackend;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.adapter.TransientStoreAdapter;
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.EList;
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
 * @see BoundTransientBackend
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentEObject extends MinimalEStoreEObjectImpl implements PersistentEObject {

    /**
     * The {@link StoreAdapter} where this object is stored.
     */
    @Nonnull
    private final LazyObject<StoreAdapter> lazyStore = LazyObject.with(() -> getOrCreateStore(resource()));

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
    protected DefaultPersistentEObject() {
        this(Id.UNDEFINED);
    }

    /**
     * Constructs a new {@code DefaultPersistentEObject} with the given {@code id}.
     *
     * @param id the identifier of this object
     */
    protected DefaultPersistentEObject(Id id) {
        this.id = checkNotNull(id);
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
        this.id = checkNotNull(newId);
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
        eStore(newStore);

        locked = false;
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
        sb.append(getClass().getName()).append('#').append(id());

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
    public StoreAdapter eStore() {
        return lazyStore.get();
    }

    @Nullable
    @Override
    public Object dynamicGet(int dynamicFeatureId) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);

        if (FeatureMapUtil.isFeatureMap(feature)) {
            return new LazyStoreFeatureMap(this, feature);
        }

        if (feature.isMany()) {
            if (Objects.equals(feature.getEType().getInstanceClassName(), java.util.Map.Entry.class.getName())) {
                return new LazyStoreMap<>(this, feature);
            }
            else {
                return new LazyStoreList<>(this, feature);
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
            TransientBackend backend = new BoundTransientBackend(id());
            Store baseStore = StoreFactory.getInstance().createStore(backend, CommonOptions.noOption());
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
    private void eStore(StoreAdapter newStore) {
        checkNotNull(newStore);

        if (lazyStore.isLoaded()) {
            StoreAdapter currentStore = lazyStore.get();

            if (!Objects.equals(currentStore, newStore)) {
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
