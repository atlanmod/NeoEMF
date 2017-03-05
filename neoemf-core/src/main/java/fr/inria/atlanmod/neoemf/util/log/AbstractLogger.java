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

package fr.inria.atlanmod.neoemf.util.log;

import org.apache.logging.log4j.LogManager;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Logger} that holds the real logger. In our case, Log4j is used.
 */
@ParametersAreNonnullByDefault
abstract class AbstractLogger implements Logger {

    /**
     * The internal logger.
     */
    private final org.apache.logging.log4j.Logger logger;

    /**
     * Constructs a new {@code AbstractLogger} with the given {@code name}.
     *
     * @param name the name of this logger
     */
    protected AbstractLogger(String name) {
        this.logger = LogManager.getLogger(name);
    }

    /**
     * Returns the internal logger.
     *
     * @return the internal logger
     */
    protected org.apache.logging.log4j.Logger logger() {
        return logger;
    }
}
