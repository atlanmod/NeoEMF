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

import com.google.common.util.concurrent.MoreExecutors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class NeoLogger {

    private static final Logger log = LogManager.getRootLogger();

    private static final ExecutorService pool = MoreExecutors.getExitingExecutorService(
            (ThreadPoolExecutor) Executors.newFixedThreadPool(1),
            1, TimeUnit.MILLISECONDS);

    public static void debug(String msg) {
        logMessage(Level.DEBUG, null, msg);
    }

    public static void debug(String pattern, Object... args) {
        logMessage(Level.DEBUG, null, pattern, args);
    }

    public static void info(String msg) {
        logMessage(Level.INFO, null, msg);
    }

    public static void info(String pattern, Object... args) {
        logMessage(Level.INFO, null, pattern, args);
    }

    public static void warn(String msg) {
        logMessage(Level.WARN, null, msg);
    }

    public static void warn(String pattern, Object... args) {
        logMessage(Level.WARN, null, pattern, args);
    }

    public static void warn(Throwable e) {
        logMessage(Level.WARN, e, null);
    }

    public static void warn(Throwable e, String msg) {
        logMessage(Level.WARN, e, msg);
    }

    public static void warn(Throwable e, String pattern, Object... args) {
        logMessage(Level.WARN, e, pattern, args);
    }

    public static void error(String msg) {
        logMessage(Level.ERROR, null, msg);
    }

    public static void error(String pattern, Object... args) {
        logMessage(Level.ERROR, null, pattern, args);
    }

    public static void error(Throwable e) {
        logMessage(Level.ERROR, e, null);
    }

    public static void error(Throwable e, String msg) {
        logMessage(Level.ERROR, e, msg);
    }

    public static void error(Throwable e, String pattern, Object... args) {
        logMessage(Level.ERROR, e, pattern, args);
    }

    private static void logMessage(Level level, Throwable throwable, String pattern, Object... args) {
        Runnable loggerCall = () -> {
            try {
                log.log(level, () -> MessageFormat.format(pattern, args), throwable);
            } catch (Exception ignore) {
            }
        };

        try {
            // Asynchronous call
            pool.submit(loggerCall);
        }
        catch (RejectedExecutionException e) {
            // Synchronous call
            loggerCall.run();
        }
    }
}
