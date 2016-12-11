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

public class NeoLogger {

    private static final Cache<String, Logger> LOGGERS = Caffeine.newBuilder().build();

    private static Logger async() {
        return AsyncLogger.getRootLogger();
    }

    public static Logger custom(String name) {
        return LOGGERS.get(name, AsyncLogger::new);
    }

    public static void trace(String msg) {
        async().trace(msg);
    }

    public static void trace(String pattern, Object... args) {
        async().trace(pattern, args);
    }

    public static void debug(String msg) {
        async().debug(msg);
    }

    public static void debug(String pattern, Object... args) {
        async().debug(pattern, args);
    }

    public static void info(String msg) {
        async().info(msg);
    }

    public static void info(String pattern, Object... args) {
        async().info(pattern, args);
    }

    public static void warn(String msg) {
        async().warn(msg);
    }

    public static void warn(String pattern, Object... args) {
        async().warn(pattern, args);
    }

    public static void warn(Throwable e) {
        async().warn(e);
    }

    public static void warn(Throwable e, String msg) {
        async().warn(e, msg);
    }

    public static void warn(Throwable e, String pattern, Object... args) {
        async().warn(e, pattern, args);
    }

    public static void error(String msg) {
        async().error(msg);
    }

    public static void error(String pattern, Object... args) {
        async().error(pattern, args);
    }

    public static void error(Throwable e) {
        async().error(e);
    }

    public static void error(Throwable e, String msg) {
        async().error(e, msg);
    }

    public static void error(Throwable e, String pattern, Object... args) {
        async().error(e, pattern, args);
    }
}
