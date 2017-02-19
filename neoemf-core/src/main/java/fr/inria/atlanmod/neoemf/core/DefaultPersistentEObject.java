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

import fr.inria.atlanmod.neoemf.data.store.OwnedTransientStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.DelegatedContentsList;
import fr.inria.atlanmod.neoemf.util.DelegatedStoreList;
import fr.inria.atlanmod.neoemf.util.DelegatedStoreMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

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
     * This information should be also maintained in the underlying {@link EStore}.
     */
    private InternalEObject eContainer;

    /**
     * ???
     */
    private int eContainerFeatureId;

    /**
     * ???
     */
    private EStore store;

    /**
     * Constructs a new {@code DefaultPersistentEObject} with a generated {@link Id} using {@link StringId#generate()}.
     */
    public DefaultPersistentEObject() {
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
    public void setMapped(boolean mapped) {
        this.isMapped = mapped;
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
        store = resource instanceof PersistentResource ? ((PersistentResource) resource).eStore() : new OwnedTransientStore(this);

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
                    Optional.ofNullable(adaptValue(source, feature, PersistentStore.NO_INDEX))
                            .ifPresent(v -> target.set(this, feature, PersistentStore.NO_INDEX, v));
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
     */
    @Nullable
    private Object adaptValue(EStore store, EStructuralFeature feature, int index) {
        Object value = store.get(this, feature, index);

        // FIXME Same code as in `DirectWriteStore#eObject()`
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
             * If the resource is not distributed and if the value of the eContainer field
             * is set it is not needed to get it from the backend.
             * This is not true in a distributed context when another client can the database
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
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append('@');
        sb.append(Integer.toHexString(hashCode()));

        if (eIsProxy()) {
            sb.append(" (eProxyURI: ");
            sb.append(eProxyURI());
            if (nonNull(eDynamicClass())) {
                sb.append(" eClass: ");
                sb.append(eDynamicClass());
            }
            sb.append(')');
        }
        else if (nonNull(eDynamicClass())) {
            sb.append(" (eClass: ");
            sb.append(eDynamicClass());
            sb.append(')');
        }
        else if (nonNull(eStaticClass())) {
            sb.append(" (eClass: ");
            sb.append(eStaticClass());
            sb.append(')');
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
        final EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
        final EClassifier eType = feature.getEType();
        if (feature.isMany()) {
            if (Objects.equals(eType.getInstanceClassName(), java.util.Map.Entry.class.getName())) {
                value = new DelegatedStoreMap<>(this, feature);
            }
            else {
                value = new DelegatedStoreList<>(this, feature);
            }
        }
        else {
            value = eStore().get(this, feature, PersistentStore.NO_INDEX);
        }
        return value;
    }

    @Override
    public void dynamicSet(int dynamicFeatureId, Object value) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
        if (feature.isMany()) {
            /*
             * TODO This operation should be atomic.
		     * Reset the old value in case the operation fails in the middle
		     */
            eStore().unset(this, feature);
            @SuppressWarnings("rawtypes")
            EList collection = (EList) value;
            for (int index = 0; index < collection.size(); index++) {
                eStore().set(this, feature, index, collection.get(index));
            }
        }
        else {
            eStore().set(this, feature, PersistentStore.NO_INDEX, value);
        }
    }

    @Override
    public void dynamicUnset(int dynamicFeatureId) {
        EStructuralFeature feature = eDynamicFeature(dynamicFeatureId);
        eStore().unset(this, feature);
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
        // Do not load the container from the store here: it creates an important overhead and performance loss
        return isNull(eContainer) ? super.eInternalContainer() : eContainer;
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
                    eBasicSetContainerFeatureID(
                            InternalEObject.EOPPOSITE_FEATURE_BASE
                                    - eInternalContainer().eClass().getFeatureID(containingFeature));
                }
            }
        }
        return eContainerFeatureId;
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
        if (isNull(o) || getClass() != o.getClass()) {
            return false;
        }
        PersistentEObject other = (PersistentEObject) o;
        return Objects.equals(id, other.id());
    }
}
