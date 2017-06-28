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

import fr.inria.atlanmod.common.concurrent.MoreExecutors;

import org.apache.logging.log4j.LogManager;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * A {@link Logger} that asynchronously invokes logging operations, respecting the order of invocation.
 */
@ThreadSafe
@Immutable
@ParametersAreNonnullByDefault
class AsyncLogger implements Logger {

    /**
     * An empty message.
     */
    @Nonnull
    private static final String NO_MESSAGE = "";

    /**
     * The concurrent pool.
     */
    @Nonnull
    private static final ExecutorService POOL = MoreExecutors.newFixedThreadPool(1);

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
        this.logger = LogManager.getLogger(name);
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
                        .orElse(NO_MESSAGE);

                logger.log(level.level(), formattedMessage, e);
            }
            catch (Exception fe) {
                logger.error(fe); // Format exception
            }
        });
    }

    /**
     * Executes a {@link Runnable} in a concurrent pool to run asynchronously the logging methods.
     * If the pool rejects the task, then it is executed synchronously.
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
