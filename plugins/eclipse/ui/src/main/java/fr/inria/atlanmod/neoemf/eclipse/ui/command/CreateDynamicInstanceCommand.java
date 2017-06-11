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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Create a dynamic instance of an {@link EClass}.
 */
public class CreateDynamicInstanceCommand extends AbstractHandler {

    private static final URI PLATFORM_RESOURCE = URI.createPlatformResourceURI("/", false);

    private EClass eClass;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        initializeClass(event);

        URI uri = eClass.eResource().getURI();

        IStructuredSelection selection = StructuredSelection.EMPTY;

        if (nonNull(uri) && uri.isHierarchical() && (uri.isRelative() || (uri = uri.deresolve(PLATFORM_RESOURCE)).isRelative())) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
            if (file.exists()) {
                selection = new StructuredSelection(file);
            }
        }

        // Register the EClass EPackage if it is not registered yet
        if (isNull(EPackage.Registry.INSTANCE.getEPackage(eClass.getEPackage().getNsURI()))) {
            EPackage.Registry.INSTANCE.put(eClass.getEPackage().getNsURI(), eClass.getEPackage());
        }

        DynamicModelWizard wizard = new DynamicModelWizard(eClass);
        wizard.init(PlatformUI.getWorkbench(), selection);

        new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard).open();

        return null;
    }

    private void initializeClass(ExecutionEvent event) throws ExecutionException {
        Optional<EClass> newClass = Optional.ofNullable(HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .map(IStructuredSelection::getFirstElement)
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast);

        if (newClass.isPresent()) {
            eClass = newClass.get();
            setEnabled(!eClass.isAbstract());
        }
        else {
            eClass = null;
            setEnabled(false);
        }
    }
}
