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

import fr.inria.atlanmod.neoemf.eclipse.ui.GenModels;
import fr.inria.atlanmod.neoemf.eclipse.ui.importer.NeoModelImporter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * A {@link org.eclipse.core.commands.IHandler} that migrates a model to another.
 */
public class MigrateCommand extends AbstractHandler {

    /**
     * The current selection.
     */
    private ISelection currentFile;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        currentFile = HandlerUtil.getActiveWorkbenchWindowChecked(event)
                .getSelectionService()
                .getSelection();

        new MigrateJob().schedule();

        return null;
    }

    /**
     * Retrieves the current {@link IFile} from the {@link #currentFile}.
     *
     * @return an {@link Optional} containing the file, or {@link Optional#empty()} if the selection does not
     * represents a file
     */
    private Optional<IFile> currentFile() {
        return Optional.ofNullable(currentFile)
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .map(IStructuredSelection::getFirstElement)
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .filter(selected -> Objects.equals("genmodel", selected.getFileExtension()));
    }

    /**
     * Retrieves the {@link GenModel} from the specified {@code file}.
     *
     * @param file the genmodel file
     *
     * @return an {@link Optional} containing the {@link GenModel}, or {@link Optional#empty()} if the {@code file}
     * does not contains a generator model
     */
    private Optional<GenModel> genModelFrom(IFile file) {
        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new XMIResourceFactoryImpl());

        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
        Resource resource = resourceSet.getResource(uri, true);

        return Optional.ofNullable(resource.getContents())
                .filter(c -> !c.isEmpty())
                .map(c -> c.get(0))
                .filter(GenModel.class::isInstance)
                .map(GenModel.class::cast);
    }

    /**
     * A {@link Job} that migrates a model to another.
     */
    private class MigrateJob extends Job {

        /**
         * Constructs a new {@code MigrateJob}.
         */
        public MigrateJob() {
            super("Migrating EMF model");
        }

        @Override
        protected IStatus run(IProgressMonitor monitor) {
            try {
                Optional<IFile> file = currentFile();
                if (file.isPresent()) {
                    Optional<GenModel> genModel = genModelFrom(file.get());
                    if (genModel.isPresent()) {
                        String msg = GenModels.adjust(genModel.get());
                        if (nonNull(msg)) {
                            genModel.get().eResource().save(Collections.emptyMap());
                            showMessage("The selected generator model has been migrated:\n" + msg, false);
                        }
                        else {
                            showMessage("The selected generator model was already migrated.", false);
                        }
                    }
                    else {
                        showMessage("The selected file does not contain a generator model.", true);
                    }
                }
                else {
                    showMessage("The selected element is not a *.genmodel file.", true);
                }
            }
            catch (Exception e) {
                return new Status(IStatus.ERROR, NeoModelImporter.IMPORTER_ID, "Problem while migrating EMF model", e);
            }

            return Status.OK_STATUS;
        }

        /**
         * Displays a {@code message}.
         *
         * @param msg   the message to display
         * @param error {@code true} if the message is an error
         */
        private void showMessage(String msg, boolean error) {
            Display display = PlatformUI.getWorkbench().getDisplay();

            display.syncExec(() -> {
                Shell shell = new Shell(display);
                if (error) {
                    MessageDialog.openError(shell, "NeoEMF Migrator", msg);
                }
                else {
                    MessageDialog.openInformation(shell, "NeoEMF Migrator", msg);
                }
            });
        }
    }
}
