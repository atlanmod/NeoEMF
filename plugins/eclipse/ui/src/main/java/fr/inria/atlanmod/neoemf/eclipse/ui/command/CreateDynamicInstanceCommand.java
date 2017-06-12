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

        EPackage.Registry.INSTANCE.putIfAbsent(
                currentClass.getEPackage().getNsURI(),
                currentClass.getEPackage());

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

            Optional<EClass> newClass = Optional.ofNullable(selection)
                    .filter(IStructuredSelection.class::isInstance)
                    .map(IStructuredSelection.class::cast)
                    .map(IStructuredSelection::getFirstElement)
                    .filter(EClass.class::isInstance)
                    .map(EClass.class::cast);

            currentClass = newClass.orElse(null);
            setEnabled(newClass.isPresent() && !currentClass.isAbstract());
        }
        catch (ExecutionException e) {
            Log.error(e);
            currentClass = null;
        }
    }
}
