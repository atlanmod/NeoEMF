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

package fr.inria.atlanmod.neoemf.util.logging;

import com.google.common.util.concurrent.MoreExecutors;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A {@link Logger} that asynchronously invokes logging operations, respecting the order of invocation.
 */
class AsyncLogger extends AbstractLogger {

    /**
     * The number of threads in the pool. Use a single thread to ensure the log order.
     */
    private static final int THREADS = 1;

    /**
     * The time, in millis, to wait for the executor to finish before terminating the JVM.
     */
    private static final int TERMINATION_TIMEOUT_MS = 100;

    /**
     * The concurrent pool.
     */
    private static final ExecutorService pool =
            MoreExecutors.getExitingExecutorService(
                    (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS),
                    TERMINATION_TIMEOUT_MS, TimeUnit.MILLISECONDS);

    /**
     * Constructs a new {@code AsyncLogger} with the given {@code name}.
     *
     * @param name the name of this logger
     */
    public AsyncLogger(String name) {
        super(name);
    }

    @Override
    public void log(Level level, Throwable e, CharSequence message, Object... params) {
        execute(() -> {
            try {
                logger().log(level.level(), () -> MessageFormat.format(Optional.ofNullable(message).orElse("").toString(), params), e);
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
