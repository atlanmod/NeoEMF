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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.Configuration;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbUri;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUri;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoUIPlugin;
import fr.inria.atlanmod.neoemf.eclipse.ui.editor.NeoEditor;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class OpenBackendCommand extends AbstractHandler {

    private IFolder folder;

    @Override
    public Void execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

        folder = Optional.ofNullable(window.getSelectionService().getSelection())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .map(IStructuredSelection::getFirstElement)
                .filter(IFolder.class::isInstance)
                .map(IFolder.class::cast)
                .orElse(null);

        if (nonNull(folder)) {
            new CreateDynamicInstanceJob(window).schedule();
        }

        return null;
    }

    private class CreateDynamicInstanceJob extends UIJob {

        public CreateDynamicInstanceJob(IWorkbenchWindow window) {
            super(window.getShell().getDisplay(), "Create Dynamic Instance");
        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            Path root = Paths.get(folder.getRawLocation().toOSString());
            File configurationFile = root.resolve(BackendFactory.CONFIG_FILE).toFile();

            Log.info("Running at: {0}", configurationFile.toString());

            if (!configurationFile.exists()) {
                Log.error("Unable to find {0} file", BackendFactory.CONFIG_FILE);
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open the editor", null);
            }

            String backendType = Configuration.load(configurationFile.toPath())
                    .getProperty(BackendFactory.BACKEND_PROPERTY);

            if (isNull(backendType)) {
                Log.error("{0} does not contain {1} property", BackendFactory.CONFIG_FILE, BackendFactory.BACKEND_PROPERTY);
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open editor");
            }

            UriBuilder uriBuilder;
            if (Objects.equals(backendType, MapDbBackendFactory.NAME)) {
                uriBuilder = MapDbUri.builder();
            }
            else if (Objects.equals(backendType, BlueprintsBackendFactory.NAME)) {
                uriBuilder = BlueprintsUri.builder();
            }
            else if (Objects.equals(backendType, BerkeleyDbBackendFactory.NAME)) {
                uriBuilder = BerkeleyDbUri.builder();
            }
            else {
                Log.error("Unknown backend: {0}", backendType);
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open editor");
            }

            IWorkbenchPage page = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage();

            try {
                page.openEditor(new URIEditorInput(uriBuilder.fromFile(root.toFile())), NeoEditor.EDITOR_ID);
            }
            catch (PartInitException e) {
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open editor", e);
            }

            return Status.OK_STATUS;
        }
    }
}
