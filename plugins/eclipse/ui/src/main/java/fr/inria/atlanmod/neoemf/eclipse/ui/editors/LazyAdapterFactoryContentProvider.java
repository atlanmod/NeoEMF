/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.eclipse.ui.editors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

public class LazyAdapterFactoryContentProvider extends AdapterFactoryContentProvider implements
        ILazyTreeContentProvider, INotifyChangedListener, ITreeContentProvider,
        IPropertySourceProvider {

    public LazyAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public void updateElement(Object parent, int index) {
        TreeViewer tViewer = (TreeViewer)viewer;
        if(parent instanceof ResourceSet) {
            ResourceSet rSet = (ResourceSet)parent;
            Resource childResource = rSet.getResources().get(index);
            tViewer.replace(parent, index, childResource);
            tViewer.setChildCount(childResource, childResource.getContents().size());
        }
        if(parent instanceof Resource) {
            Resource r = (Resource)parent;
            EObject child = r.getContents().get(index);
            tViewer.replace(parent, index, child);
            tViewer.setChildCount(child, getChildCount(child));
        }
        if(parent instanceof PersistentEObject) {
            PersistentEObject e = (PersistentEObject)parent;
            EObject child = e.eContents().get(index);
            tViewer.replace(parent, index, child);
            tViewer.setChildCount(child, getChildCount(child));
        }

    }

    @Override
    public void updateChildCount(Object element, int currentChildCount) {
        TreeViewer tViewer = (TreeViewer)viewer;
        if(element instanceof ResourceSet) {
            ResourceSet rSet = (ResourceSet)element;
            tViewer.setChildCount(element, rSet.getResources().size());
        }
        if(element instanceof Resource) {
            Resource r = (Resource)element;
            tViewer.setChildCount(element, r.getContents().size());
        }
        if(element instanceof PersistentEObject) {
            PersistentEObject e = (PersistentEObject)element;
            tViewer.setChildCount(element, getChildCount(e));
        }
    }
    
    private int getChildCount(EObject eObject) {
        return eObject.eContents().size();
    }

}
