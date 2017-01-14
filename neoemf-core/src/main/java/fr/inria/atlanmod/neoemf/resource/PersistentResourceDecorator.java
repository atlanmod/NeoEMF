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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * A {@link PersistentResource} wrapper that delegates all method calls to the decorated resource.
 * <p>
 * This class can be extended by external tools to add behavior to an existing {@link PersistentResource}.
 */
public class PersistentResourceDecorator implements PersistentResource {

    /**
     * The underlying resource.
     */
    protected final PersistentResource resource;

    /**
     * Constructs a {@code PersistentResourceDecorator} on the given {@code resource}.
     *
     * @param resource the underlying resource
     */
    public PersistentResourceDecorator(PersistentResource resource) {
        this.resource = resource;
    }

    @Override
    public ResourceSet getResourceSet() {
        return resource.getResourceSet();
    }

    @Override
    public URI getURI() {
        return resource.getURI();
    }

    @Override
    public void setURI(URI uri) {
        resource.setURI(uri);
    }

    @Override
    public long getTimeStamp() {
        return resource.getTimeStamp();
    }

    @Override
    public void setTimeStamp(long timeStamp) {
        resource.setTimeStamp(timeStamp);
    }

    @Override
    public EList<EObject> getContents() {
        return resource.getContents();
    }

    @Override
    public TreeIterator<EObject> getAllContents() {
        return resource.getAllContents();
    }

    @Override
    public String getURIFragment(EObject eObject) {
        return resource.getURIFragment(eObject);
    }

    @Override
    public EObject getEObject(String uriFragment) {
        return resource.getEObject(uriFragment);
    }

    @Override
    public void save(Map<?, ?> options) throws IOException {
        resource.save(options);
    }

    @Override
    public void load(Map<?, ?> options) throws IOException {
        resource.load(options);
    }

    @Override
    public void save(OutputStream outputStream, Map<?, ?> options) throws IOException {
        resource.save(options);
    }

    @Override
    public void load(InputStream inputStream, Map<?, ?> options) throws IOException {
        resource.load(options);
    }

    @Override
    public boolean isTrackingModification() {
        return resource.isTrackingModification();
    }

    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        resource.setTrackingModification(isTrackingModification);
    }

    @Override
    public boolean isModified() {
        return resource.isModified();
    }

    @Override
    public void setModified(boolean isModified) {
        resource.setModified(isModified);
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public void unload() {
        resource.unload();
    }

    @Override
    public void delete(Map<?, ?> options) throws IOException {
        resource.delete(options);
    }

    @Override
    public EList<Diagnostic> getErrors() {
        return resource.getErrors();
    }

    @Override
    public EList<Diagnostic> getWarnings() {
        return resource.getWarnings();
    }

    @Override
    public EList<Adapter> eAdapters() {
        return resource.eAdapters();
    }

    @Override
    public boolean eDeliver() {
        return resource.eDeliver();
    }

    @Override
    public void eSetDeliver(boolean deliver) {
        resource.eSetDeliver(deliver);
    }

    @Override
    public void eNotify(Notification notification) {
        resource.eNotify(notification);
    }

    @Override
    public void attached(EObject eObject) {
        resource.attached(eObject);
    }

    @Override
    public void detached(EObject eObject) {
        resource.detached(eObject);
    }

    @Override
    public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
        return resource.basicSetResourceSet(resourceSet, notifications);
    }

    @Override
    public boolean isLoading() {
        return resource.isLoading();
    }

    @Override
    public final void close() {
        resource.close();
    }

    @Override
    public EStore eStore() {
        return resource.eStore();
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass) {
        return resource.getAllInstances(eClass);
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        return resource.getAllInstances(eClass, strict);
    }

}
