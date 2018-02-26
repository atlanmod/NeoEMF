/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import fr.inria.atlanmod.neoemf.config.Config;
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
        return Config.load(directory)
                .map(Config::getName)
                .map(UriBuilder::forName)
                .<FileNotFoundException>orElseThrow(() -> new FileNotFoundException(String.format("Unable to find the configuration from %s", directory)));
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
            super(display, "Opening Model Database");
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
