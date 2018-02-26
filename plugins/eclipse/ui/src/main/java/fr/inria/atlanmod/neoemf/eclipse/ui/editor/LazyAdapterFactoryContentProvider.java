/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

import java.util.List;
import java.util.Optional;

/**
 *
 */
class LazyAdapterFactoryContentProvider extends AdapterFactoryContentProvider implements ILazyTreeContentProvider {

    /**
     * Constructs a new {@code LazyAdapterFactoryContentProvider} on the {@code factory}.
     *
     * @param factory the delegated factory
     */
    public LazyAdapterFactoryContentProvider(AdapterFactory factory) {
        super(factory);
    }

    @Override
    public void updateElement(Object parent, int index) {
        childOf(parent, index).ifPresent(c -> {
            TreeViewer treeViewer = TreeViewer.class.cast(viewer);
            treeViewer.replace(parent, index, c);
            sizeOf(c).ifPresent(s -> treeViewer.setChildCount(c, s));
        });
    }

    @Override
    public void updateChildCount(Object element, int currentChildCount) {
        sizeOf(element).ifPresent(s -> TreeViewer.class.cast(viewer).setChildCount(element, s));
    }

    /**
     * Returns the size of the given {@code element}.
     *
     * @param element the object to calculate the size
     *
     * @return the size
     */
    private Optional<Integer> sizeOf(Object element) {
        return Optional.ofNullable(getContents(element)).map(List::size);
    }

    /**
     * Retrieves the child from the {@code parent} object at the given {@code index}.
     *
     * @param parent the parent of the child to look for
     * @param index  the index of the child to look for in its {@code parent}
     *
     * @return the child
     */
    private Optional<Object> childOf(Object parent, int index) {
        return Optional.ofNullable(getContents(parent)).map(l -> l.get(index));
    }

    /**
     * Retrieves the content of the {@code parent} according to its type.
     *
     * @param parent the containing object
     *
     * @return the content of the parent
     */
    private List<?> getContents(Object parent) {
        if (ResourceSet.class.isInstance(parent)) {
            return ResourceSet.class.cast(parent).getResources();
        }

        if (Resource.class.isInstance(parent)) {
            return Resource.class.cast(parent).getContents();
        }

        if (EObject.class.isInstance(parent)) {
            return EObject.class.cast(parent).eContents();
        }

        return null;
    }
}
