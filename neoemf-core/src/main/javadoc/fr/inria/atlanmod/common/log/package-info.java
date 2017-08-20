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

/**
 * Provides classes for logging information and events.
 * <p>
 * This package contains the classes related to NeoEMF logging service. The {@link fr.inria.atlanmod.common.log.Log}
 * class defines a set of static methods to log information, warnings, or errors. It is possible to use {@link
 * java.text.MessageFormat} style in logged information.
 * <p>
 * Client code can use the NeoEMF logger to log application-level information if needed using the following code:
 * <pre>{@code
 * Log.trace("a trace message");
 * Log.debug("a debug message");
 * Log.info("an information message");
 * Log.warn("a warning message");
 * Log.error("an error message");
 * }</pre>
 * <p>
 * {@link fr.inria.atlanmod.common.log.AsyncLogger} uses Java Logging as its internal logger. A Log4j adapter is
 * preconfigured, but remains optional (see <a href="https://logging.apache.org/log4j/2.x/log4j-jul/">Log4j JDK Logging
 * Adapter</a> for more information about its integration).
 */

package fr.inria.atlanmod.common.log;