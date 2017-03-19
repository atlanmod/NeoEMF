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

package fr.inria.atlanmod.neoemf.util.log;

import org.apache.logging.log4j.LogManager;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

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
    private static final String NO_MESSAGE = "";

    /**
     * The concurrent pool.
     */
    private static final ExecutorService pool = createService();

    /**
     * The internal logger.
     */
    private final org.apache.logging.log4j.Logger logger;

    /**
     * Constructs a new {@code AsyncLogger} with the given {@code name}.
     *
     * @param name the name of this logger
     */
    public AsyncLogger(String name) {
        this.logger = LogManager.getLogger(name);
    }

    /**
     * Creates a new {@link ExecutorService} that will be closed when the application will exit.
     *
     * @return a new service
     */
    private static ExecutorService createService() {
        ExecutorService service = Executors.unconfigurableExecutorService(
                Executors.newFixedThreadPool(1, r -> {
                    Thread thread = Executors.defaultThreadFactory().newThread(r);
                    thread.setDaemon(true);
                    return thread;
                }));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                service.shutdown();

                if (service.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    service.shutdownNow().forEach(Runnable::run);
                }
            }
            catch (InterruptedException ignored) {
            }
        }));

        return service;
    }

    @Override
    public void log(Level level, @Nullable Throwable e, @Nullable CharSequence message, @Nullable Object... params) {
        execute(() -> {
            try {
                logger.log(level.level(), () -> MessageFormat.format(Optional.ofNullable(message).orElse(NO_MESSAGE).toString(), params), e);
            }
            catch (Exception ignore) {
            }
        });
    }

    /**
     * Executes a {@link Runnable} in a concurrent pool to run asynchronously the logging methods.
     *
     * @param runnable the function to execute
     */
    private void execute(Runnable runnable) {
        try {
            // Asynchronous call
            pool.submit(runnable);
        }
        catch (RejectedExecutionException e) {
            // Synchronous call
            runnable.run();
        }
    }
}
