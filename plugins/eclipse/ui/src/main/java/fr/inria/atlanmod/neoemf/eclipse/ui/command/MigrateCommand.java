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

import fr.inria.atlanmod.neoemf.eclipse.ui.migrator.NeoImporter;
import fr.inria.atlanmod.neoemf.eclipse.ui.migrator.NeoImporterUtil;

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

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

public class MigrateCommand extends AbstractHandler {

    private ISelection selection;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        selection = HandlerUtil.getActiveWorkbenchWindowChecked(event)
                .getSelectionService()
                .getSelection();

        new MigrateJob().schedule();

        return null;
    }

    private IFile getFile() {
        return Optional.ofNullable(selection)
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .map(IStructuredSelection::getFirstElement)
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .filter(selected -> Objects.equals("genmodel", selected.getFileExtension()))
                .orElse(null);
    }

    private GenModel getGenModel(IFile file) {
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
                .map(GenModel.class::cast)
                .orElse(null);
    }

    private class MigrateJob extends Job {

        public MigrateJob() {
            super("Migrating EMF model");
        }

        @Override
        protected IStatus run(IProgressMonitor monitor) {
            try {
                IFile file = getFile();
                if (isNull(file)) {
                    showMessage("The selected element is not a *.genmodel file.", true);
                }
                else {
                    GenModel genModel = getGenModel(file);
                    if (isNull(genModel)) {
                        showMessage("The selected file does not contain a generator model.", true);
                    }
                    else {
                        String msg = NeoImporterUtil.adjustGenModel(genModel);
                        if (isNull(msg)) {
                            showMessage("The selected generator model was already migrated.", false);
                        }
                        else {
                            genModel.eResource().save(null);
                            showMessage("The selected generator model has been migrated:" + "\n\n" + msg, false);
                        }
                    }
                }
            }
            catch (Exception ex) {
                return new Status(IStatus.ERROR, NeoImporter.IMPORTER_ID, "Problem while migrating EMF model", ex);
            }

            return Status.OK_STATUS;
        }

        private void showMessage(final String msg, final boolean error) {
            try {
                final Display display = PlatformUI.getWorkbench().getDisplay();
                display.syncExec(() -> {
                    try {
                        final Shell shell = new Shell(display);
                        if (error) {
                            MessageDialog.openError(shell, "NeoEMF Migrator", msg);
                        }
                        else {
                            MessageDialog.openInformation(shell, "NeoEMF Migrator", msg);
                        }
                    }
                    catch (RuntimeException ignore) {
                    }
                });
            }
            catch (RuntimeException ignore) {
            }
        }
    }
}
