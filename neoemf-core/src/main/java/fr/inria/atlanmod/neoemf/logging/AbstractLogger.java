/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.logging;

import org.apache.logging.log4j.LogManager;

abstract class AbstractLogger implements Logger {

    private final org.apache.logging.log4j.Logger logger;

    protected AbstractLogger(String name) {
        this.logger = LogManager.getLogger(name);
    }

    protected org.apache.logging.log4j.Logger logger() {
        return logger;
    }
}
