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

package fr.inria.atlanmod.common.log;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object used for identifying the severity of an event.
 * <p>
 * Levels are organized from most specific to least: <ul> <li>{@link #ERROR} (most specific, little data)</li>
 * <li>{@link #WARN}</li> <li>{@link #INFO}</li> <li>{@link #DEBUG}</li> <li>{@link #TRACE} (least specific, a lot of
 * data)</li> </ul>
 * <p>
 * Typically, configuring a level in a filter or on a {@link Logger} will cause logging events of that level and those
 * that are more specific to pass through the filter.
 */
@ParametersAreNonnullByDefault
public enum Level {

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    TRACE(java.util.logging.Level.FINER),

    /**
     * A general debugging event.
     */
    DEBUG(java.util.logging.Level.FINE),

    /**
     * An event for informational purposes.
     */
    INFO(java.util.logging.Level.INFO),

    /**
     * An event that might possible lead to an error.
     */
    WARN(java.util.logging.Level.WARNING),

    /**
     * An error in the application, possibly recoverable.
     */
    ERROR(java.util.logging.Level.SEVERE);

    /**
     * The associated Java Logging level.
     */
    private final java.util.logging.Level level;

    /**
     * Constructs a new {@code Level} with its associated {@code level}.
     *
     * @param level the associated Java Logging level
     */
    Level(java.util.logging.Level level) {
        this.level = level;
    }

    /**
     * Returns the Java Logging {@link java.util.logging.Level} associated with this {@code Level}.
     *
     * @return the Java Logging level
     */
    java.util.logging.Level level() {
        return level;
    }
}
