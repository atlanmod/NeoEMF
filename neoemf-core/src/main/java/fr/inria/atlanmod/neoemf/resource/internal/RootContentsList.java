/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.resource.internal;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

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
 * A {@link java.util.List} that delegates its operations to a {@link Store} for supporting {@link
 * Resource#getContents}.
 *
 * @see RootObject
 * @see RootContentsReference
 */
@ParametersAreNonnullByDefault
// TODO Reimplements `iterator()` and `listIterator()` to use batch methods
public class RootContentsList<E> extends EStoreEObjectImpl.BasicEStoreEList<E> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 4130828923851153715L;

    @Nonnull
    private final transient DefaultPersistentResource resource;

    /**
     * Constructs a new {@code RootContentsList}.
     */
    public RootContentsList(DefaultPersistentResource resource) {
        super(new RootObject(resource), new RootContentsReference());
        this.resource = resource;
    }

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

    @Override
    protected void didSet(int index, E newObject, E oldObject) {
        super.didSet(index, newObject, oldObject);
        modified();
    }

    @Override
    protected void didAdd(int index, E object) {
        super.didAdd(index, object);
        if (index == size() - 1) {
            loaded();
        }
        modified();
    }

    @Override
    protected void didRemove(int index, E object) {
        super.didRemove(index, object);
        modified();
    }

    @Override
    protected void didClear(int oldSize, Object[] oldData) {
        if (oldSize == 0) {
            loaded();
        }
        else {
            super.didClear(oldSize, oldData);
        }
    }

    /**
     * Notifies that this resource has been loaded.
     */
    private void loaded() {
        if (!resource.isLoaded()) {
            Optional.ofNullable(resource.setLoaded(true)).ifPresent(resource::eNotify);
        }
    }

    /**
     * Notifies that this resource has been modified.
     */
    private void modified() {
        if (resource.isTrackingModification()) {
            resource.setModified(true);
        }
    }

    @Override
    protected StoreAdapter eStore() {
        return resource.eStore();
    }

    @Override
    protected void delegateAdd(int index, Object value) {
        hardAllContents(PersistentEObject.from(value)).forEach(e -> e.resource(resource));
        super.delegateAdd(index, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E delegateRemove(int index) {
        E previousValue = super.delegateRemove(index);
        hardAllContents(PersistentEObject.from(previousValue)).forEach(e -> e.resource(null));
        return previousValue;
    }

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
