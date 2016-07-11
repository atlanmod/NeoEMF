/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.ReflectionUtil;

import java.text.MessageFormat;

public class NeoLogger {

    private static final int CALLER_CLASS_DEPTH = 4;
    private static final boolean CLASSNAME_NEEDED = false;

    private static final Logger ROOT_LOGGER = LogManager.getLogger();

    private NeoLogger() {}

    public static void debug(String msg) {
        internalLog(Level.DEBUG, msg, null);
    }

    public static void debug(String pattern, Object... args) {
        internalLog(Level.DEBUG, MessageFormat.format(pattern, args), null);
    }

    public static void info(String msg) {
        internalLog(Level.INFO, msg, null);
    }

    public static void info(String pattern, Object... args) {
        internalLog(Level.INFO, MessageFormat.format(pattern, args), null);
    }

    public static void warn(String msg) {
        internalLog(Level.WARN, msg, null);
    }

    public static void warn(String pattern, Object... args) {
        internalLog(Level.WARN, MessageFormat.format(pattern, args), null);
    }

    public static void warn(Throwable e) {
        internalLog(Level.WARN, null, e);
    }

    public static void error(String msg) {
        internalLog(Level.ERROR, msg, null);
    }

    public static void error(String pattern, Object... args) {
        internalLog(Level.ERROR, MessageFormat.format(pattern, args), null);
    }

    public static void error(Throwable e) {
        internalLog(Level.ERROR, null, e);
    }

    public static void error(Throwable e, String pattern, Object... args) {
        internalLog(Level.ERROR, MessageFormat.format(pattern, args), e);
    }

    public static void fatal(String msg) {
        internalLog(Level.FATAL, msg, null);
    }

    public static void fatal(String pattern, Object... args) {
        internalLog(Level.FATAL, MessageFormat.format(pattern, args), null);
    }

    public static void fatal(Throwable e) {
        internalLog(Level.FATAL, null, e);
    }

    private static void internalLog(Level level, String msg, Throwable e) {
        getLogger().log(level, msg, e);
    }

    private static Logger getLogger() {
        if (CLASSNAME_NEEDED) {
            return LogManager.getLogger(ReflectionUtil.getCallerClass(CALLER_CLASS_DEPTH));
        } else {
            return ROOT_LOGGER;
        }
    }
}
