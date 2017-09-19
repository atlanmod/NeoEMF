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

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Tree;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * An {@link EcoreEditor} that allows modifications on a {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
public class NeoEditor extends EcoreEditor {

    /**
     * The identifier of the {@code NeoEditor}.
     */
    public static final String EDITOR_ID = NeoEditor.class.getName();

    @Override
    public void createModel() {
        try {
            URI uri = EditUIUtil.getURI(getEditorInput());
            uri = UriBuilder.forScheme(uri.scheme()).fromUri(uri);

            ResourceSet resourceSet = getEditingDomain().getResourceSet();
            resourceSet.eAdapters().add(problemIndicationAdapter);

            Resource resource = resourceSet.createResource(uri);

            // Create the store options depending of the backend
            Map<String, Object> options = PersistenceOptions.forScheme(resource.getURI().scheme())
                    .cacheContainers()
                    .cacheMetaClasses()
                    .autoSave()
//                    .log(Level.INFO)
                    .asMap();

            resource.load(options);
        }
        catch (Exception e) {
            Log.error(e, "Unable to create model");
            closeAll();
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void createPages() {
        Instant begin = Instant.now();
        createModel();

        // Only creates the other pages if there is something that can be edited
        if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {

            // Create a page for the selection tree view.
            Tree tree = new Tree(getContainer(), SWT.VIRTUAL | SWT.FULL_SELECTION);

            selectionViewer = new TreeViewer(tree);
            {
                selectionViewer.setContentProvider(new LazyAdapterFactoryContentProvider(adapterFactory));
                selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
                selectionViewer.setUseHashlookup(true);
                selectionViewer.setInput(editingDomain.getResourceSet());
            }
            setCurrentViewer(selectionViewer);
            createContextMenuFor(selectionViewer);

            setPageText(addPage(tree), EcoreEditorPlugin.INSTANCE.getString("_UI_SelectionPage_label"));

            getSite().getShell().getDisplay().asyncExec(() -> setActivePage(0));
        }

        // Ensures that this editor will only display the page's tab area if there are more than one page
        getContainer().addControlListener(new ControlAdapter() {
            boolean guard = false;

            @Override
            public void controlResized(ControlEvent event) {
                if (!guard) {
                    guard = true;
                    hideTabs();
                    guard = false;
                }
            }
        });

        getSite().getShell().getDisplay().asyncExec(this::updateProblemIndication);

        Instant end = Instant.now();

        Log.debug("NeoEMF editor opened in {0}", Duration.between(begin, end));
    }

    @Override
    public void setSelection(ISelection selection) {
        try {
            super.setSelection(selection);
        }
        // FIXME See issue #52
        catch (UnsupportedOperationException e) {
            Log.warn(e.getMessage());
        }
    }

    @Override
    public void dispose() {
        Log.debug("Disposing NeoEMF editor");

        closeAll();
        super.dispose();
    }

    /**
     * Closes all opened resources.
     */
    private void closeAll() {
        for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
            if (PersistentResource.class.isInstance(resource)) {
                PersistentResource.class.cast(resource).close();
            }
            else {
                resource.unload();
            }
        }
    }

    /**
     *
     */
    private static class LazyAdapterFactoryContentProvider extends AdapterFactoryContentProvider implements ILazyTreeContentProvider {

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
         * @return an {@link Optional} containing the child, or {@link Optional#empty()} if the child cannot be
         * retrieved
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
}
