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
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
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
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
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
     * @throws IOException            if the {@code directory} does not have configuration file
     * @throws InvalidOptionException if the information stored in the configuration does not permit to retrieve the
     *                                {@link UriBuilder}
     */
    private UriBuilder getUriBuilder(Path directory) throws IOException {
        Path configFile = directory.resolve(BackendFactory.CONFIG_FILE);

        if (Files.notExists(configFile)) {
            throw new FileNotFoundException(
                    String.format("Unable to find %s file", BackendFactory.CONFIG_FILE));
        }

        String backendType = Configuration.load(directory.resolve(BackendFactory.CONFIG_FILE)).get(BackendFactory.BACKEND_PROPERTY);

        if (isNull(backendType)) {
            throw new InvalidOptionException(
                    String.format("%s does not contain %s property", BackendFactory.CONFIG_FILE, BackendFactory.BACKEND_PROPERTY));
        }

        if (Objects.equals(backendType, MapDbBackendFactory.NAME)) {
            return MapDbUri.builder();
        }
        else if (Objects.equals(backendType, BlueprintsBackendFactory.NAME)) {
            return BlueprintsUri.builder();
        }
        else if (Objects.equals(backendType, BerkeleyDbBackendFactory.NAME)) {
            return BerkeleyDbUri.builder();
        }

        throw new InvalidOptionException(String.format("Unknown backend: %s", backendType));
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
