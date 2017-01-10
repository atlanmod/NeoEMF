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

package fr.inria.atlanmod.neoemf.logging;

/**
 * Levels used for identifying the severity of an event.
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
 * Typically, configuring a level in a filter or on a logger will cause logging events of that level and those that are
 * more specific to pass through the filter.
 */
public enum Level {
    TRACE(org.apache.logging.log4j.Level.TRACE),
    DEBUG(org.apache.logging.log4j.Level.DEBUG),
    INFO(org.apache.logging.log4j.Level.INFO),
    WARN(org.apache.logging.log4j.Level.WARN),
    ERROR(org.apache.logging.log4j.Level.ERROR),
    FATAL(org.apache.logging.log4j.Level.FATAL);

    private final org.apache.logging.log4j.Level level;

    Level(org.apache.logging.log4j.Level level) {
        this.level = level;
    }

    org.apache.logging.log4j.Level level() {
        return level;
    }
}
