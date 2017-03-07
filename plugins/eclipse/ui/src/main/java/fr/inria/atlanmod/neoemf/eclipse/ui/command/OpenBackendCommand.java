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

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoUIPlugin;
import fr.inria.atlanmod.neoemf.eclipse.ui.editor.NeoEditor;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.util.Objects.isNull;

public class OpenBackendCommand extends AbstractHandler {

    private IFolder folder;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        ISelectionService service = window.getSelectionService();
        ISelection selection = service.getSelection();

        folder = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object elt = structuredSelection.getFirstElement();
            if (elt instanceof IFolder) {
                folder = (IFolder) elt;
            }
        }
        if (isNull(folder)) {
            return null;
        }

        new CreateDynamicInstanceJob(window).schedule();

        return null;
    }

    private class CreateDynamicInstanceJob extends UIJob {

        public CreateDynamicInstanceJob(IWorkbenchWindow window) {
            super(window.getShell().getDisplay(), "Create Dynamic Instance");
        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            Path root = Paths.get(folder.getRawLocation().toOSString());
            Path path = root.resolve(PersistenceBackendFactory.CONFIG_FILE);
            Log.info("Running at: {0}", path.toString());

            PropertiesConfiguration configuration;
            try {
                configuration = new PropertiesConfiguration(path.toFile());
            }
            catch (ConfigurationException e) {
                Log.error("Unable to find {0} file", PersistenceBackendFactory.CONFIG_FILE);
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open the editor", e);
            }

            URI uri = null;
            String backendType = configuration.getString(PersistenceBackendFactory.BACKEND_PROPERTY);
            if (isNull(backendType)) {
                Log.error("{0} does not contain {1} property", PersistenceBackendFactory.CONFIG_FILE, PersistenceBackendFactory.BACKEND_PROPERTY);
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open editor");
            }
            else if (Objects.equals(backendType, MapDbBackendFactory.NAME)) {
                uri = MapDbURI.createFileURI(root.toFile());
            }
            else if (Objects.equals(backendType, BlueprintsBackendFactory.NAME)) {
                uri = BlueprintsURI.createFileURI(root.toFile());
            }
            else if (Objects.equals(backendType, BerkeleyDbBackendFactory.NAME)) {
                uri = BerkeleyDbURI.createFileURI(root.toFile());
            }

            URIEditorInput editorInput = new URIEditorInput(uri);
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();

            try {
                page.openEditor(editorInput, NeoEditor.EDITOR_ID);
            }
            catch (PartInitException e) {
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open editor", e);
            }
            return Status.OK_STATUS;
        }
    }
}
