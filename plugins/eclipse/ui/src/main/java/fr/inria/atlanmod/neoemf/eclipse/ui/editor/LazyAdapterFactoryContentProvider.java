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

package fr.inria.atlanmod.neoemf.eclipse.ui.editor;

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

import java.util.Optional;
import java.util.OptionalInt;

public class LazyAdapterFactoryContentProvider extends AdapterFactoryContentProvider implements ILazyTreeContentProvider, INotifyChangedListener, ITreeContentProvider, IPropertySourceProvider {

    public LazyAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
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
        sizeOf(element).ifPresent(s -> {
            TreeViewer.class.cast(viewer).setChildCount(element, s);
        });
    }

    /**
     * Returns the size of the given {@code element}.
     *
     * @param element the object to calculate the size
     *
     * @return an {@link OptionalInt} containing the size, of {@link OptionalInt#empty()} if the {@code element} is
     * not supported
     */
    private OptionalInt sizeOf(Object element) {
        if (ResourceSet.class.isInstance(element)) {
            return OptionalInt.of(ResourceSet.class.cast(element).getResources().size());
        }

        if (Resource.class.isInstance(element)) {
            return OptionalInt.of(Resource.class.cast(element).getContents().size());
        }

        if (EObject.class.isInstance(element)) {
            return OptionalInt.of(EObject.class.cast(element).eContents().size());
        }

        return OptionalInt.empty();
    }

    /**
     * Retrieves the child from the {@code parent} object at the given {@code index}.
     *
     * @param parent the parent of the child to look for
     * @param index  the index of the child to look for in its {@code parent}
     *
     * @return an {@link Optional} containing the child, or {@link Optional#empty()} if the child cannot be retrieved
     */
    private Optional<Object> childOf(Object parent, int index) {
        if (ResourceSet.class.isInstance(parent)) {
            return Optional.of(ResourceSet.class.cast(parent).getResources().get(index));
        }

        if (Resource.class.isInstance(parent)) {
            return Optional.of(Resource.class.cast(parent).getContents().get(index));
        }

        if (EObject.class.isInstance(parent)) {
            return Optional.of(EObject.class.cast(parent).eContents().get(index));
        }

        return Optional.empty();
    }
}
