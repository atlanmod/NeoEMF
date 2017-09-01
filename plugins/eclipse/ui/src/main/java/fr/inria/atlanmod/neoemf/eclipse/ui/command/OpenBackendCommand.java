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

import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * A {@link org.eclipse.core.commands.IHandler} that opens an existing {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
public class OpenBackendCommand extends AbstractHandler {

    /**
     * The current root directory.
     */
    private IFolder currentDirectory;

    @Override
    public Void execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

        currentDirectory = Optional.ofNullable(window.getSelectionService().getSelection())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .map(IStructuredSelection::getFirstElement)
                .filter(IFolder.class::isInstance)
                .map(IFolder.class::cast)
                .orElse(null);

        if (nonNull(currentDirectory)) {
            new OpenBackendJob(window.getShell().getDisplay()).schedule();
        }

        return null;
    }

    /**
     * Retrieves the {@link UriBuilder} to use from the NeoEMF configuration in the given {@code directory}.
     *
     * @param directory the directory where to find the configuration
     *
     * @return the {@link UriBuilder}
     *
     * @throws IOException             if the {@code directory} does not have configuration file
     * @throws InvalidBackendException if the information stored in the configuration does not permit to retrieve the
     *                                 {@link UriBuilder}
     */
    private UriBuilder getUriBuilder(Path directory) throws IOException {
        Path configFile = directory.resolve(BackendConfig.DEFAULT_FILENAME);

        if (Files.notExists(configFile)) {
            throw new FileNotFoundException(
                    String.format("Unable to find %s file", BackendConfig.DEFAULT_FILENAME));
        }

        BackendConfig config = BackendConfig.load(configFile);

        if (!config.has(BackendConfig.BACKEND_PROPERTY)) {
            throw new InvalidBackendException(
                    String.format("%s does not contain %s property", configFile, BackendConfig.BACKEND_PROPERTY));
        }

        return UriBuilder.forName(config.get(BackendConfig.BACKEND_PROPERTY));
    }

    /**
     * A {@link org.eclipse.core.runtime.jobs.Job} that opens the {@link NeoEditor} on an existing {@link
     * fr.inria.atlanmod.neoemf.data.Backend}.
     */
    private class OpenBackendJob extends UIJob {

        /**
         * Constructs a new {@code OpenBackendJob} with the given {@code display}.
         *
         * @param display the display to execute the asyncExec in
         */
        public OpenBackendJob(Display display) {
            super(display, "Create Dynamic Instance");
        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            Path root = Paths.get(currentDirectory.getRawLocation().toOSString());

            try {
                URI uri = getUriBuilder(root).fromFile(root.toFile());

                PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow()
                        .getActivePage()
                        .openEditor(new URIEditorInput(uri), NeoEditor.EDITOR_ID);
            }
            catch (Exception e) {
                return new Status(IStatus.ERROR, NeoUIPlugin.PLUGIN_ID, "Unable to open editor", e);
            }

            return Status.OK_STATUS;
        }
    }
}
