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
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NeoLogger {

    private static final Logger log = LogManager.getRootLogger();

    private static final ExecutorService pool = Executors.newSingleThreadExecutor();

    private NeoLogger() {
    }

    public static void debug(final String msg) {
        pool.submit(() -> log.debug(msg));
    }

    public static void debug(String pattern, Object... args) {
        pool.submit(() -> log.debug(MessageFormat.format(pattern, args)));
    }

    public static void info(String msg) {
        pool.submit(() -> log.info(msg));
    }

    public static void info(String pattern, Object... args) {
        pool.submit(() -> log.info(MessageFormat.format(pattern, args)));
    }

    public static void warn(String msg) {
        pool.submit(() -> log.warn(msg));
    }

    public static void warn(String pattern, Object... args) {
        pool.submit(() -> log.warn(MessageFormat.format(pattern, args)));
    }

    public static void warn(Throwable e) {
        pool.submit(() -> log.warn(e));
    }

    public static void warn(Throwable e, String pattern, Object... args) {
        pool.submit(() -> log.warn(MessageFormat.format(pattern, args), e));
    }

    public static void error(String msg) {
        pool.submit(() -> log.error(msg));
    }

    public static void error(String pattern, Object... args) {
        pool.submit(() -> log.error(MessageFormat.format(pattern, args)));
    }

    public static void error(Throwable e) {
        pool.submit(() -> log.error(e));
    }

    public static void error(Throwable e, String pattern, Object... args) {
        pool.submit(() -> log.error(MessageFormat.format(pattern, args), e));
    }
}
