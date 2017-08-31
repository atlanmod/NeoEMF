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

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A {@link PersistentResource} wrapper that delegates all method calls to the decorated resource.
 * <p>
 * This class can be extended by external tools to add behavior to an existing {@link PersistentResource}.
 */
@ParametersAreNonnullByDefault
public class PersistentResourceDecorator implements PersistentResource {

    /**
     * The underlying resource.
     */
    @Nonnull
    protected final PersistentResource resource;

    /**
     * Constructs a {@code PersistentResourceDecorator} on the given {@code resource}.
     *
     * @param resource the underlying resource
     */
    public PersistentResourceDecorator(PersistentResource resource) {
        this.resource = checkNotNull(resource);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public ResourceSet getResourceSet() {
        return resource.getResourceSet();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public URI getURI() {
        return resource.getURI();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void setURI(URI uri) {
        resource.setURI(uri);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public long getTimeStamp() {
        return resource.getTimeStamp();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void setTimeStamp(long timeStamp) {
        resource.setTimeStamp(timeStamp);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public EList<EObject> getContents() {
        return resource.getContents();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public TreeIterator<EObject> getAllContents() {
        return resource.getAllContents();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public String getURIFragment(EObject eObject) {
        return resource.getURIFragment(eObject);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public EObject getEObject(String uriFragment) {
        return resource.getEObject(uriFragment);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void save(OutputStream outputStream, Map<?, ?> options) throws IOException {
        resource.save(outputStream, options);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void load(InputStream inputStream, Map<?, ?> options) throws IOException {
        resource.load(inputStream, options);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean isTrackingModification() {
        return resource.isTrackingModification();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void setTrackingModification(boolean isTrackingModification) {
        resource.setTrackingModification(isTrackingModification);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean isModified() {
        return resource.isModified();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void setModified(boolean isModified) {
        resource.setModified(isModified);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void unload() {
        resource.unload();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void delete(Map<?, ?> options) throws IOException {
        resource.delete(options);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public EList<Diagnostic> getErrors() {
        return resource.getErrors();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public EList<Diagnostic> getWarnings() {
        return resource.getWarnings();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public final void close() {
        resource.close();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void save(Map<?, ?> options) throws IOException {
        resource.save(options);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void load(Map<?, ?> options) throws IOException {
        resource.load(options);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public StoreAdapter store() {
        return resource.store();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean isPersistent() {
        return resource.isPersistent();
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Iterable<EObject> allInstancesOf(EClass eClass) {
        return resource.allInstancesOf(eClass);
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public Iterable<EObject> allInstancesOf(EClass eClass, boolean strict) {
        return resource.allInstancesOf(eClass, strict);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public EList<Adapter> eAdapters() {
        return resource.eAdapters();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean eDeliver() {
        return resource.eDeliver();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void eSetDeliver(boolean deliver) {
        resource.eSetDeliver(deliver);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void eNotify(Notification notification) {
        resource.eNotify(notification);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void attached(EObject eObject) {
        resource.attached(eObject);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void detached(EObject eObject) {
        resource.detached(eObject);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
        return resource.basicSetResourceSet(resourceSet, notifications);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean isLoading() {
        return resource.isLoading();
    }
}
