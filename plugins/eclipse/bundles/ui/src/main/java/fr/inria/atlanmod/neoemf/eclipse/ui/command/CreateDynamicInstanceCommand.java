/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import fr.inria.atlanmod.neoemf.eclipse.ui.wizard.DynamicModelWizard;

import org.eclipse.core.commands.ExecutionEvent;
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

import static java.util.Objects.nonNull;

/**
 * A {@link org.eclipse.core.commands.IHandler} that creates dynamic instances of {@link EClass}es.
 */
public class CreateDynamicInstanceCommand extends AbstractSelectionSingleHandler<EClass> {

    public CreateDynamicInstanceCommand() {
        super(EClass.class);
    }

    @Override
    protected void execute(ExecutionEvent event, EClass selectedClass) {
        setEnabled(!selectedClass.isAbstract());

        final EPackage ePackage = selectedClass.getEPackage();
        EPackage.Registry.INSTANCE.putIfAbsent(ePackage.getNsURI(), ePackage);

        final DynamicModelWizard wizard = new DynamicModelWizard(selectedClass);
        wizard.init(PlatformUI.getWorkbench(), getSelectedResource(selectedClass));

        new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard).open();
    }

    private IStructuredSelection getSelectedResource(EClass eClass) {
        URI uri = eClass.eResource().getURI();
        if (nonNull(uri) && uri.isHierarchical() && (uri.isRelative() || (uri = uri.deresolve(URI.createPlatformResourceURI("/", false))).isRelative())) {
            final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
            if (file.exists()) {
                return new StructuredSelection(file);
            }
        }

        return StructuredSelection.EMPTY;
    }
}
