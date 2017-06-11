/*
 * Copyright (c) 2008-2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 *     Atlanmod INRIA LINA Mines Nantes - Adapted to NeoEMF models
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.action;

import fr.inria.atlanmod.common.collect.MoreIterables;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.eclipse.ui.MetamodelRegistry;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import static java.util.Objects.isNull;

public class RegisterMetamodelAction implements IObjectActionDelegate {

    private IStructuredSelection selection;

    @Override
    public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
        // Do nothing
    }

    @Override
    public void run(IAction action) {
        if (isNull(selection)) {
            return;
        }

        Iterable<Object> selectedObjects = selection::iterator;
        MoreIterables.stream(selectedObjects)
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .map(file -> file.getFullPath().toOSString())
                .forEach(fileName -> {
                    try {
                        MetamodelRegistry.getInstance().addMetamodel(fileName);
                        Log.info("Metamodel {0} successfully registered", fileName);
                    }
                    catch (Exception e) {
                        Log.error(e, "Metamodel {0} could not be registered", fileName);
                    }
                });
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        if (!selection.isEmpty() && IStructuredSelection.class.isInstance(selection)) {
            this.selection = IStructuredSelection.class.cast(selection);
        }
    }
}
