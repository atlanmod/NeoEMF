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
import org.eclipse.core.resources.IFile;

import java.util.function.Consumer;

/**
 * An abstract {@link AbstractHandler} that process metamodels.
 */
abstract class AbstractMetamodelCommand extends AbstractSelectionManyHandler<IFile> implements Consumer<String> {

    public AbstractMetamodelCommand() {
        super(IFile.class, f -> f.getFileExtension().equals("ecore"));
    }

    @Override
    protected void execute(ExecutionEvent event, Iterable<? extends IFile> selectedFiles) {
        for (IFile selectedFile : selectedFiles) {
            accept(selectedFile.getFullPath().toOSString());
        }
    }
}
