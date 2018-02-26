/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.editor;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.time.Stopwatch;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;

/**
 * An {@link EcoreEditor} that allows modifications on a persistent {@link Resource}.
 */
public class NeoEditor extends EcoreEditor {

    /**
     * The unique identifier of this editor.
     */
    public static final String EDITOR_ID = NeoEditor.class.getName();

    @Override
    public void createModel() {
        try {
            URI uri = EditUIUtil.getURI(getEditorInput());
            String uriScheme = uri.scheme();

            uri = UriBuilder.forScheme(uriScheme).fromUri(uri);

            // Create a default configuration: the exact instance will be create when loading the existing configuration
            ImmutableConfig config = Config.forScheme(uriScheme)
                    .cacheContainers()
                    .cacheMetaClasses()
                    .cacheSizes()
//                    .log()
                    .autoSave();

            ResourceSet resourceSet = getEditingDomain().getResourceSet();
            resourceSet.eAdapters().add(problemIndicationAdapter);

            Resource resource = resourceSet.createResource(uri);
            resource.load(config.toMap());
        }
        catch (Exception e) {
            Log.error(e, "Unable to create model");
            closeAll();
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void createPages() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        // Open the resource
        createModel();

        Display display = getSite().getShell().getDisplay();

        // Only creates the other pages if there is something that can be edited
        if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {
            createTree();
            display.asyncExec(() -> setActivePage(0));
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

        display.asyncExec(this::updateProblemIndication);

        Log.debug("NeoEMF Editor opened in {0}", stopwatch.stop().elapsed());
    }

    @Override
    public void setSelection(ISelection selection) {
        try {
            super.setSelection(selection);
        }
        catch (UnsupportedOperationException e) {
            Log.warn(e.getMessage());
        }
    }

    @Override
    public void dispose() {
        Log.debug("Disposing NeoEMF Editor");

        closeAll();
        super.dispose();
    }

    /**
     * Creates the tree and its viewer.
     */
    private void createTree() {
        // Create a page for the selection tree view.
        Tree tree = new Tree(getContainer(), SWT.VIRTUAL | SWT.FULL_SELECTION);

        selectionViewer = new TreeViewer(tree);
        selectionViewer.setContentProvider(new LazyAdapterFactoryContentProvider(adapterFactory));
        selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        selectionViewer.setUseHashlookup(true);
        selectionViewer.setInput(editingDomain.getResourceSet());

        setCurrentViewer(selectionViewer);
        createContextMenuFor(selectionViewer);

        setPageText(addPage(tree), EcoreEditorPlugin.INSTANCE.getString("_UI_SelectionPage_label"));
    }

    /**
     * Closes all opened resources.
     */
    private void closeAll() {
        getEditingDomain().getResourceSet().getResources().forEach(Resource::unload);
    }
}
