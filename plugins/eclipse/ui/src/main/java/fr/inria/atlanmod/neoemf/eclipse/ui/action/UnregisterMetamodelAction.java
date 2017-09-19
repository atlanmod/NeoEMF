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

import fr.inria.atlanmod.neoemf.eclipse.ui.MetamodelRegistry;

import org.eclipse.ui.IActionDelegate;

import java.util.function.Consumer;

/**
 * A {@link IActionDelegate} that unregisters metamodels.
 */
public class UnregisterMetamodelAction extends AbstractMetamodelAction {

    @Override
    protected Consumer<String> consume() {
        return fn -> MetamodelRegistry.getInstance().unregister(fn);
    }
}
