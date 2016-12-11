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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public final class NeoLogger {

    private static final Cache<String, Logger> LOGGERS = Caffeine.newBuilder().build();

    private NeoLogger() {
    }

    /**
     * Returns the root logger.
     *
     * @return the root logger, named "".
     */
    private static Logger root() {
        return custom(Logger.ROOT_LOGGER_NAME);
    }

    /**
     * Returns a {@link Logger} with the specified name.
     *
     * @param name the logger name
     *
     * @return the {@link Logger}
     */
    public static Logger custom(String name) {
        return LOGGERS.get(name, AsyncLogger::new);
    }

    /**
     * @see Logger#debug(CharSequence)
     */
    public static void debug(CharSequence msg) {
        root().debug(msg);
    }

    /**
     * @see Logger#debug(CharSequence, Object...)
     */
    public static void debug(CharSequence pattern, Object... args) {
        root().debug(pattern, args);
    }

    /**
     * @see Logger#info(CharSequence)
     */
    public static void info(CharSequence msg) {
        root().info(msg);
    }

    /**
     * @see Logger#info(CharSequence, Object...)
     */
    public static void info(CharSequence pattern, Object... args) {
        root().info(pattern, args);
    }

    /**
     * @see Logger#warn(CharSequence)
     */
    public static void warn(CharSequence msg) {
        root().warn(msg);
    }

    /**
     * @see Logger#warn(CharSequence, Object...)
     */
    public static void warn(CharSequence pattern, Object... args) {
        root().warn(pattern, args);
    }

    /**
     * @see Logger#warn(Throwable)
     */
    public static void warn(Throwable e) {
        root().warn(e);
    }

    /**
     * @see Logger#warn(Throwable, CharSequence)
     */
    public static void warn(Throwable e, CharSequence msg) {
        root().warn(e, msg);
    }

    /**
     * @see Logger#warn(Throwable, CharSequence, Object...)
     */
    public static void warn(Throwable e, CharSequence pattern, Object... args) {
        root().warn(e, pattern, args);
    }

    /**
     * @see Logger#error(CharSequence)
     */
    public static void error(CharSequence msg) {
        root().error(msg);
    }

    /**
     * @see Logger#error(CharSequence, Object...)
     */
    public static void error(CharSequence pattern, Object... args) {
        root().error(pattern, args);
    }

    /**
     * @see Logger#error(Throwable)
     */
    public static void error(Throwable e) {
        root().error(e);
    }

    /**
     * @see Logger#error(Throwable, CharSequence)
     */
    public static void error(Throwable e, CharSequence msg) {
        root().error(e, msg);
    }

    /**
     * @see Logger#error(Throwable, CharSequence, Object...)
     */
    public static void error(Throwable e, CharSequence pattern, Object... args) {
        root().error(e, pattern, args);
    }
}
