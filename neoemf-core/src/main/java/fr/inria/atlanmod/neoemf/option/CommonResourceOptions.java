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

package fr.inria.atlanmod.neoemf.option;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents common options related to resource-level features.
 */
@ParametersAreNonnullByDefault
public interface CommonResourceOptions extends PersistentResourceOptions {

    /**
     * The option key to define the number of operations between each save in auto-save mode.
     */
    String AUTO_SAVE_CHUNK = "store.autosave.chunk";

    /**
     * The option key to define the logging level in log mode.
     */
    String LOGGING_LEVEL = "store.logging.level";
}
