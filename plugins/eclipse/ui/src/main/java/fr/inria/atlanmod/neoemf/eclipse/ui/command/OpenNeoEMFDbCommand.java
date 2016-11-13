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

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoEMFUIPlugin;
import fr.inria.atlanmod.neoemf.eclipse.ui.editor.NeoEMFEditor;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;

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

import java.io.File;

import static java.util.Objects.isNull;

public class OpenNeoEMFDbCommand extends AbstractHandler {

    private IFolder folder;

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
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
            System.out.println(folder.getRawLocation().toOSString() + "/" + PersistenceBackendFactory.CONFIG_FILE);
            File neoConfigFile = new File(folder.getRawLocation().toOSString() + "/" + PersistenceBackendFactory.CONFIG_FILE);
            PropertiesConfiguration neoConfig;
            try {
                neoConfig = new PropertiesConfiguration(neoConfigFile);
            }
            catch (ConfigurationException e1) {
                NeoLogger.error("Unable to find neoconfig.properties file");
                return new Status(IStatus.ERROR, NeoEMFUIPlugin.PLUGIN_ID, "Unable to open the editor", e1);
            }
            String backendType = neoConfig.getString(PersistenceBackendFactory.BACKEND_PROPERTY);
            URI uri = null;
            if (isNull(backendType)) {
                NeoLogger.error("neoconfig.properties does not contain {0} property", PersistenceBackendFactory.BACKEND_PROPERTY);
                return new Status(IStatus.ERROR, NeoEMFUIPlugin.PLUGIN_ID, "Unable to open editor");
            }
            else if (backendType.equals(MapPersistenceBackendFactory.NAME)) {
                uri = NeoMapURI.createFileURI(new File(folder.getRawLocation().toOSString()));
            }
            else if (backendType.equals(BlueprintsPersistenceBackendFactory.NAME)) {
                uri = NeoBlueprintsURI.createFileURI(new File(folder.getRawLocation().toOSString()));
            }
            URIEditorInput editorInput = new URIEditorInput(uri);
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            try {
                page.openEditor(editorInput, NeoEMFEditor.EDITOR_ID);
            }
            catch (PartInitException e) {
                return new Status(IStatus.ERROR, NeoEMFUIPlugin.PLUGIN_ID, "Unable to open editor", e);
            }
            return Status.OK_STATUS;
        }
    }
}
