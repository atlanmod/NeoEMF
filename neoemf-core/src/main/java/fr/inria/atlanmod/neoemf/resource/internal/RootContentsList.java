/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource.internal;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * A {@link java.util.List} representing the content of the root of a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource},
 * that delegates its operations to the associated {@link fr.inria.atlanmod.neoemf.data.store.Store}.
 *
 * @see RootObject
 * @see RootContentsReference
 */
@ParametersAreNonnullByDefault
public class RootContentsList<E> extends EStoreEObjectImpl.BasicEStoreEList<E> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 4130828923851153715L;

    /**
     * The resource that holds the owner of this list.
     */
    @Nonnull
    private final transient DefaultPersistentResource resource;

    /**
     * Constructs a new {@code RootContentsList}.
     *
     * @param resource
     */
    public RootContentsList(DefaultPersistentResource resource) {
        super(new RootObject(resource), new RootContentsReference());
        this.resource = resource;
    }

    @Nonnull
    @Override
    protected StoreAdapter eStore() {
        return resource.eStore();
    }

    // region Delegating methods

    @Override
    protected void delegateAdd(int index, Object value) {
        hardAllContents(PersistentEObject.from(value)).forEach(e -> e.resource(resource));
        super.delegateAdd(index, value);
    }

    @Override
    protected void delegateAdd(Object object) {
        delegateAdd(InternalEObject.EStore.NO_INDEX, object);
    }

    @Override
    protected E delegateRemove(int index) {
        E previousValue = super.delegateRemove(index);
        hardAllContents(PersistentEObject.from(previousValue)).forEach(e -> e.resource(null));
        return previousValue;
    }

    // endregion

    @Override
    protected E validate(int index, E object) {
        checkArgument(canContainNull() || nonNull(object), "The 'no null' constraint is violated");
        return object;
    }

    @Override
    public Object getNotifier() {
        return resource;
    }

    @Override
    public int getFeatureID() {
        return PersistentResource.ROOT_REFERENCE_ID;
    }

    @Override
    protected boolean isNotificationRequired() {
        return resource.eNotificationRequired();
    }

    @Override
    public NotificationChain inverseAdd(E object, NotificationChain notifications) {
        PersistentEObject eObject = PersistentEObject.from(object);
        notifications = eObject.eSetResource(resource, notifications);
        resource.attached(eObject);
        return notifications;
    }

    @Override
    public NotificationChain inverseRemove(E object, NotificationChain notifications) {
        PersistentEObject eObject = PersistentEObject.from(object);
        if (resource.isLoaded()) {
            resource.detached(eObject);
        }
        return eObject.eSetResource(null, notifications);
    }

    @Override
    protected boolean useEquals() {
        return false;
    }

    @Override
    protected boolean isUnique() {
        return true;
    }

    @Override
    protected boolean hasInverse() {
        return true;
    }

    // region Notifications

    @Override
    protected void didSet(int index, E newObject, E oldObject) {
        super.didSet(index, newObject, oldObject);
        notifyModified();
    }

    @Override
    protected void didAdd(int index, E object) {
        super.didAdd(index, object);
        if (index == size() - 1) {
            notifyLoaded();
        }
        notifyModified();
    }

    @Override
    protected void didRemove(int index, E object) {
        super.didRemove(index, object);
        notifyModified();
    }

    @Override
    protected void didClear(int oldSize, Object[] oldData) {
        if (oldSize == 0) {
            notifyLoaded();
        }
        else {
            super.didClear(oldSize, oldData);
        }
    }

    /**
     * Notifies that this resource has been loaded.
     */
    private void notifyLoaded() {
        if (!resource.isLoaded()) {
            Optional.ofNullable(resource.setLoaded(true)).ifPresent(resource::eNotify);
        }
    }

    /**
     * Notifies that this resource has been modified.
     */
    private void notifyModified() {
        if (resource.isTrackingModification()) {
            resource.setModified(true);
        }
    }

    // endregion

    /**
     * Retrieves all the content of the specified {@code rootObject} and stores it in a {@link List}.
     * <p>
     * By iterating using the hard links list instead the {@link PersistentResource#getAllContents()}, we ensure that
     * the content is not taken out by JIT compiler.
     *
     * @param rootObject the object from which to retrieve the content
     *
     * @return the content of {@code rootObject}
     */
    @Nonnull
    private Iterable<PersistentEObject> hardAllContents(PersistentEObject rootObject) {
        Collection<PersistentEObject> allContents = new ArrayDeque<>();
        allContents.add(rootObject);

        MoreIterables.stream(rootObject::eAllContents)
                .map(PersistentEObject::from)
                .collect(Collectors.toCollection(() -> allContents));

        return allContents;
    }
}
