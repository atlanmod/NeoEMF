/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.eclipse.ui.wizard.DynamicModelWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * A {@link org.eclipse.core.commands.IHandler} that creates dynamic instances of {@link EClass}es.
 */
public class CreateDynamicInstanceCommand extends AbstractHandler {

    /**
     * The current class instance.
     */
    private EClass currentClass;

    @Override
    public Object execute(ExecutionEvent event) {
        currentClass(event);

        URI uri = currentClass.eResource().getURI();

        IStructuredSelection selection = StructuredSelection.EMPTY;

        if (nonNull(uri) && uri.isHierarchical() && (uri.isRelative() || (uri = uri.deresolve(URI.createPlatformResourceURI("/", false))).isRelative())) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
            if (file.exists()) {
                selection = new StructuredSelection(file);
            }
        }

        EPackage ePackage = currentClass.getEPackage();
        EPackage.Registry.INSTANCE.putIfAbsent(ePackage.getNsURI(), ePackage);

        DynamicModelWizard wizard = new DynamicModelWizard(currentClass);
        wizard.init(PlatformUI.getWorkbench(), selection);

        new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard).open();

        return null;
    }

    /**
     * Initialize the current {@link EClass}.
     *
     * @param event the fired event to retrieve the class
     */
    private void currentClass(ExecutionEvent event) {
        try {
            ISelection selection = HandlerUtil.getActiveWorkbenchWindowChecked(event)
                    .getSelectionService()
                    .getSelection();

            currentClass = Optional.ofNullable(selection)
                    .filter(IStructuredSelection.class::isInstance)
                    .map(IStructuredSelection.class::cast)
                    .map(IStructuredSelection::getFirstElement)
                    .filter(EClass.class::isInstance)
                    .map(EClass.class::cast)
                    .orElse(null);

            setEnabled(nonNull(currentClass) && !currentClass.isAbstract());
        }
        catch (ExecutionException e) {
            Log.error(e);
            currentClass = null;
        }
    }
}
