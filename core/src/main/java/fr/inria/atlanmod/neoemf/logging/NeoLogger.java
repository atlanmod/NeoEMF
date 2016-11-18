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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

public class NeoLogger {

    private static final Logger log = LogManager.getRootLogger();

    private NeoLogger() {
    }

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

    public static void warn(Throwable e, String pattern, Object... args) {
        internalLog(Level.WARN, MessageFormat.format(pattern, args), e);
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

    private static void internalLog(Level level, String msg, Throwable e) {
        log.log(level, msg, e);
    }
}
