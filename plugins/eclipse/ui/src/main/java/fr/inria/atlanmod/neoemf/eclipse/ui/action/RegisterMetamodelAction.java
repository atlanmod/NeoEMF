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
import fr.inria.atlanmod.neoemf.eclipse.ui.MetamodelRegistry;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * A {@link IActionDelegate} that registers metamodels.
 */
public class RegisterMetamodelAction implements IActionDelegate {

    /**
     * The current selection.
     */
    private IStructuredSelection currentSelection;

    @Override
    public void run(IAction action) {
        if (isNull(currentSelection)) {
            return;
        }

        // Register all
        MoreIterables.<Object>stream(currentSelection::iterator)
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .map(file -> file.getFullPath().toOSString())
                .forEach(fileName -> MetamodelRegistry.getInstance().register(fileName));
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.currentSelection = Optional.ofNullable(selection)
                .filter(s -> !s.isEmpty())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .orElse(null);
    }
}
