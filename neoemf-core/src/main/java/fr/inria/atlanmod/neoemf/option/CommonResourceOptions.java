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

/**
 * Represents common options related to resource-level features.
 */
public interface CommonResourceOptions extends PersistentResourceOptions {

    /**
     * The option key to define the number of operations between each commit in autocommit mode.
     */
    String AUTOCOMMIT_CHUNK = "store.autocommit.chunk";

    /**
     * The option key to define the logging level in log mode.
     */
    String LOGGING_LEVEL = "store.logging.level";
}
