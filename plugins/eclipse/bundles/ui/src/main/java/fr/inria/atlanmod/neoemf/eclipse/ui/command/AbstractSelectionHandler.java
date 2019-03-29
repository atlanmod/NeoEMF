/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import static java.util.Objects.nonNull;

/**
 * An abstract {@link org.eclipse.core.commands.IHandler} that processes events only if one or several objects are
 * selected.
 */
abstract class AbstractSelectionHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection();

        if (nonNull(selection) && !selection.isEmpty() && selection instanceof StructuredSelection) {
            execute(event, (IStructuredSelection) selection);
        }

        return null;
    }

    protected abstract void execute(ExecutionEvent event, IStructuredSelection selection) throws ExecutionException;
}
