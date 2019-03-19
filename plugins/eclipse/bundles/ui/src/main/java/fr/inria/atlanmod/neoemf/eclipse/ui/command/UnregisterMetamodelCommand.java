/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import fr.inria.atlanmod.neoemf.eclipse.ui.MetamodelRegistry;

/**
 * A {@link org.eclipse.core.commands.IHandler} that unregisters metamodels.
 */
public class UnregisterMetamodelCommand extends AbstractMetamodelCommand {

    @Override
    public void accept(String filePath) {
        MetamodelRegistry.getInstance().unregister(filePath);
    }
}
