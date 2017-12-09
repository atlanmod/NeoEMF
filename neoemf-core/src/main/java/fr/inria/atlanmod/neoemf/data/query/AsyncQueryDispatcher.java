/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.query;

import fr.inria.atlanmod.commons.LazyObject;
import fr.inria.atlanmod.commons.concurrent.MoreExecutors;
import fr.inria.atlanmod.commons.concurrent.MoreThreads;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.InconsistencyException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

/**
 * A {@link QueryDispatcher} that dispatches and executes queries asynchronously using an {@link ExecutorService}.
 */
@ParametersAreNonnullByDefault
public class AsyncQueryDispatcher extends AbstractQueryDispatcher {

    /**
     * The default number of parallel threads for one {@link Backend} instance.
     */
    @Nonnegative
    protected static final int DEFAULT_THREAD_COUNT = 1;

    /**
     * The asynchronous pool of this backend.
     */
    @Nonnull
    private final LazyObject<ExecutorService> pool;

    /**
     * The dispatcher of this backend, associated with the {@link #pool}.
     */
    @Nonnull
    private final LazyObject<Scheduler> scheduler;

    /**
     * Constructs a new {@code AsyncQueryDispatcher}.
     *
     * @param name the name of threads
     */
    public AsyncQueryDispatcher(String name) {
        this(name, DEFAULT_THREAD_COUNT);
    }

    /**
     * Constructs a new {@code AsyncQueryDispatcher}.
     *
     * @param name     the name of threads
     * @param nThreads the number of threads to use
     *
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public AsyncQueryDispatcher(String name, @Nonnegative int nThreads) {
        this.pool = LazyObject.with(() -> {
            ThreadFactory threadFactory = MoreThreads.newThreadFactory(name);
            return Executors.newFixedThreadPool(nThreads, threadFactory);
        });

        this.scheduler = LazyObject.with(() -> Schedulers.from(pool.get()));
    }

    @Nonnull
    protected CompletableTransformer scheduleCompletable() {
        Scheduler s = scheduler.get();
        return q -> q.observeOn(s).subscribeOn(s);
    }

    @Nonnull
    @Override
    protected <T> MaybeTransformer<T, T> scheduleMaybe() {
        Scheduler s = scheduler.get();
        return q -> q.observeOn(s).subscribeOn(s);
    }

    @Nonnull
    @Override
    protected <T> SingleTransformer<T, T> scheduleSingle() {
        Scheduler s = scheduler.get();
        return q -> q.observeOn(s).subscribeOn(s);
    }

    @Nonnull
    @Override
    protected <T> ObservableTransformer<T, T> scheduleObservable() {
        Scheduler s = scheduler.get();
        return q -> q.observeOn(s).subscribeOn(s);
    }

    @Nonnull
    @Override
    protected <T> FlowableTransformer<T, T> scheduleFlowable() {
        Scheduler s = scheduler.get();
        return q -> q.observeOn(s).subscribeOn(s);
    }

    @Override
    public void close() {
        if (pool.isLoaded()) {
            final List<Runnable> unstartedTasks = MoreExecutors.shutdown(pool.get(), 60, TimeUnit.SECONDS);
            if (!unstartedTasks.isEmpty()) {
                throw new InconsistencyException(String.format("%d queries have not been executed", unstartedTasks.size()));
            }
        }
    }
}
