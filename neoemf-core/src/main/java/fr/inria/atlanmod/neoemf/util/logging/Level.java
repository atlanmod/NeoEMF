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

package fr.inria.atlanmod.neoemf.util.logging;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object used for identifying the severity of an event.
 * <p>
 * Levels are organized from most specific to least:
 * <ul>
 * <li>{@link #FATAL} (most specific, little data)</li>
 * <li>{@link #ERROR}</li>
 * <li>{@link #WARN}</li>
 * <li>{@link #INFO}</li>
 * <li>{@link #DEBUG}</li>
 * <li>{@link #TRACE} (least specific, a lot of data)</li>
 * </ul>
 * <p>
 * Typically, configuring a level in a filter or on a {@link Logger} will cause logging events of that level and those
 * that are more specific to pass through the filter.
 */
@ParametersAreNonnullByDefault
public enum Level {

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    TRACE(org.apache.logging.log4j.Level.TRACE),

    /**
     * A general debugging event.
     */
    DEBUG(org.apache.logging.log4j.Level.DEBUG),

    /**
     * An event for informational purposes.
     */
    INFO(org.apache.logging.log4j.Level.INFO),

    /**
     * An event that might possible lead to an error.
     */
    WARN(org.apache.logging.log4j.Level.WARN),

    /**
     * An error in the application, possibly recoverable.
     */
    ERROR(org.apache.logging.log4j.Level.ERROR),

    /**
     * A severe error that will prevent the application from continuing.
     */
    FATAL(org.apache.logging.log4j.Level.FATAL);

    /**
     * The associated Log4j level.
     */
    private final org.apache.logging.log4j.Level level;

    /**
     * Constructs a new {@code Level} with its associated {@code level}.
     *
     * @param level the associated Log4j level
     */
    Level(org.apache.logging.log4j.Level level) {
        this.level = level;
    }

    /**
     * Returns the Log4j {@link org.apache.logging.log4j.Level} associated with this {@code Level}.
     *
     * @return the Log4j level
     */
    org.apache.logging.log4j.Level level() {
        return level;
    }
}
