/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */
package fr.inria.atlanmod.neoemf.resources.impl;

import fr.inria.atlanmod.neoemf.resources.PersistentResource;

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
 * An empty {@see PersistentResource} decorator that delegates all method calls to the decorated 
 * resource.
 * <p>
 * This class can be extended by external tools to add behavior to an existing {@see PersistentResource}.
 * </p>
 */
public class PersistentResourceDecorator implements PersistentResource {

    protected final PersistentResource base;
    
    public PersistentResourceDecorator(PersistentResource baseResource) {
        this.base = baseResource;
    }
    
    @Override
    public ResourceSet getResourceSet() {
        return base.getResourceSet();
    }

    @Override
    public URI getURI() {
        return base.getURI();
    }

    @Override
    public void setURI(URI uri) {
        base.setURI(uri);
    }

    @Override
    public long getTimeStamp() {
        return base.getTimeStamp();
    }

    @Override
    public void setTimeStamp(long timeStamp) {
        base.setTimeStamp(timeStamp);
    }

    @Override
    public EList<EObject> getContents() {
        return base.getContents();
    }

    @Override
    public TreeIterator<EObject> getAllContents() {
        return base.getAllContents();
    }

    @Override
    public String getURIFragment(EObject eObject) {
        return base.getURIFragment(eObject);
    }

    @Override
    public EObject getEObject(String uriFragment) {
        return base.getEObject(uriFragment);
    }

    @Override
    public void save(Map<?, ?> options) throws IOException {
        base.save(options);
    }

    @Override
    public void load(Map<?, ?> options) throws IOException {
        base.load(options);
    }

    @Override
    public void save(OutputStream outputStream, Map<?, ?> options) throws IOException {
        base.save(options);
    }

    @Override
    public void load(InputStream inputStream, Map<?, ?> options) throws IOException {
        base.load(options);
    }

    @Override
    public boolean isTrackingModification() {
        return base.isTrackingModification();
    }

    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        base.setTrackingModification(isTrackingModification);
    }

    @Override
    public boolean isModified() {
        return base.isModified();
    }

    @Override
    public void setModified(boolean isModified) {
        base.setModified(isModified);
    }

    @Override
    public boolean isLoaded() {
        return base.isLoaded();
    }

    @Override
    public void unload() {
        base.unload();
    }

    @Override
    public void delete(Map<?, ?> options) throws IOException {
        base.delete(options);
    }

    @Override
    public EList<Diagnostic> getErrors() {
        return base.getErrors();
    }

    @Override
    public EList<Diagnostic> getWarnings() {
        return base.getWarnings();
    }

    @Override
    public EList<Adapter> eAdapters() {
        return base.eAdapters();
    }

    @Override
    public boolean eDeliver() {
        return base.eDeliver();
    }

    @Override
    public void eSetDeliver(boolean deliver) {
        base.eSetDeliver(deliver);
    }

    @Override
    public void eNotify(Notification notification) {
        base.eNotify(notification);
    }

    @Override
    public void attached(EObject eObject) {
        base.attached(eObject);
    }

    @Override
    public void detached(EObject eObject) {
        base.detached(eObject);
    }

    @Override
    public NotificationChain basicSetResourceSet(ResourceSet resourceSet,
            NotificationChain notifications) {
        return base.basicSetResourceSet(resourceSet, notifications);
    }

    @Override
    public boolean isLoading() {
        return base.isLoading();
    }

    @Override
    public EStore eStore() {
        return base.eStore();
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass) {
        return base.getAllInstances(eClass);
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        return base.getAllInstances(eClass, strict);
    }

}
