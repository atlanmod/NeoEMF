/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.action;

import fr.inria.atlanmod.neoemf.eclipse.ui.MetamodelRegistry;

import org.eclipse.ui.IActionDelegate;

import java.util.function.Consumer;

/**
 * A {@link IActionDelegate} that registers metamodels.
 */
public class RegisterMetamodelAction extends AbstractMetamodelAction {

    @Override
    protected Consumer<String> consume() {
        return fn -> MetamodelRegistry.getInstance().register(fn);
    }
}
