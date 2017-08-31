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

package fr.inria.atlanmod.commons.log;

import fr.inria.atlanmod.commons.concurrent.MoreExecutors;
import fr.inria.atlanmod.commons.primitive.Strings;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static java.util.Objects.isNull;

/**
 * A {@link Logger} that asynchronously invokes logging operations, respecting the order of invocation.
 */
@ThreadSafe
@Immutable
@ParametersAreNonnullByDefault
class AsyncLogger implements Logger {

    /**
     * The concurrent pool.
     * <p>
     * A single thread pool is used for keeping events order.
     */
    @Nonnull
    private static final ExecutorService POOL = MoreExecutors.newFixedThreadPool(1);

    static {
        final String loggingManagerProperty = "java.util.logging.manager";
        final String loggingManagerLog4j = "org.apache.logging.log4j.jul.LogManager";

        // Don't modify the logging manager if it is already defined
        if (isNull(System.getProperty(loggingManagerProperty))) {
            try {
                // Defines the Log4j manager if the dependencies are in the classpath
                Class.forName(loggingManagerLog4j, false, Log.class.getClassLoader());
                System.setProperty(loggingManagerProperty, loggingManagerLog4j);
            }
            catch (ClassNotFoundException ignored) {
                // Use the default Java logging manager
            }
        }
    }

    /**
     * The internal logger.
     */
    @Nonnull
    private final org.apache.logging.log4j.Logger logger;

    /**
     * Constructs a new {@code AsyncLogger} with the given {@code name}.
     *
     * @param name the name of this logger
     */
    public AsyncLogger(String name) {
        this.logger = org.apache.logging.log4j.LogManager.getLogger(name);
    }

    @Override
    public void log(Level level, @Nullable Throwable e, @Nullable CharSequence message, @Nullable Object... params) {
        if (!logger.isEnabled(level.level())) {
            // Don't send the request if the associated level is not enabled
            return;
        }

        execute(() -> {
            try {
                String formattedMessage = Optional.ofNullable(message)
                        .map(m -> MessageFormat.format(m.toString(), params))
                        .orElse(Strings.EMPTY);

                logger.log(level.level(), formattedMessage, e);
            }
            catch (Exception fe) {
                logger.log(org.apache.logging.log4j.Level.ERROR, fe); // Format exception
            }
        });
    }

    /**
     * Executes a {@link Runnable} in a concurrent pool to run asynchronously the logging methods. If the pool rejects
     * the task, then it is executed synchronously.
     *
     * @param runnable the function to execute
     *
     * @see ExecutorService#submit(Runnable)
     */
    private void execute(Runnable runnable) {
        try {
            // Asynchronous call
            POOL.submit(runnable);
        }
        catch (RejectedExecutionException e) {
            // Synchronous call
            runnable.run();
        }
    }
}
