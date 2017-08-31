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

package fr.inria.atlanmod.commons.concurrent;

import fr.inria.atlanmod.commons.annotation.Static;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * Static utility methods related to {@link java.util.concurrent.Executor} instances.
 */
@Static
@ParametersAreNonnullByDefault
public final class MoreExecutors {

    /**
     * The default thread factory, used to create {@link Thread} instances that are not attached to an {@link
     * ExecutorService}.
     *
     * @see #executeAtExit(Runnable)
     */
    @Nonnull
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = newThreadFactory();

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private MoreExecutors() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Creates a new {@link ExecutorService} using {@code nThreads} processors, that will be closed when the application
     * will exit.
     *
     * @param nThreads the number of threads in the pool
     *
     * @return a new immutable service
     *
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     * @see Executors#newFixedThreadPool(int)
     * @see #newThreadFactory()
     * @see #shutdownAtExit(ExecutorService, long, TimeUnit, boolean)
     */
    @Nonnull
    public static ExecutorService newFixedThreadPool(int nThreads) {
        ExecutorService service = Executors.newFixedThreadPool(nThreads, newThreadFactory());
        service = Executors.unconfigurableExecutorService(service);
        return shutdownAtExit(service, 100, TimeUnit.MILLISECONDS, true);
    }

    /**
     * Creates a new {@link ThreadFactory} that creates daemon threads.
     *
     * @return a new thread factory
     *
     * @see Executors#defaultThreadFactory()
     */
    @Nonnull
    public static ThreadFactory newThreadFactory() {
        return task -> {
            Thread thread = Executors.defaultThreadFactory().newThread(task);
            thread.setDaemon(true);
            return thread;
        };
    }

    /**
     * Adds a shutdown hook to cleanly closes the {@code service} when the application will exit.
     *
     * @param service           the service to close
     * @param timeout           the maximum time to wait
     * @param unit              the time unit of the timeout argument
     * @param executeUnfinished {@code true} if the remaining tasks must be executed synchronously if they have not
     *                          completed their execution after the shutdown request
     *
     * @return the {@code service}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if {@code timeout < 0}
     * @see ExecutorService#shutdown()
     * @see ExecutorService#awaitTermination(long, TimeUnit)
     * @see ExecutorService#shutdownNow()
     */
    @Nonnull
    public static ExecutorService shutdownAtExit(ExecutorService service, long timeout, TimeUnit unit, boolean executeUnfinished) {
        checkNotNull(service);
        checkNotNull(unit);
        checkArgument(timeout >= 0);

        executeAtExit(() -> {
            try {
                service.shutdown();

                if (service.awaitTermination(timeout, unit)) {
                    List<? extends Runnable> runnables = service.shutdownNow();

                    if (executeUnfinished) {
                        runnables.forEach(Runnable::run);
                    }
                }
            }
            catch (InterruptedException ignored) {
            }
        });

        return service;
    }

    /**
     * Adds a shutdown hook that will execute the {@code task} when the application will exit.
     *
     * @param task the task to execute
     *
     * @throws NullPointerException if the {@code task} is {@code null}
     */
    public static void executeAtExit(Runnable task) {
        checkNotNull(task);

        Runtime.getRuntime().addShutdownHook(DEFAULT_THREAD_FACTORY.newThread(task));
    }
}
