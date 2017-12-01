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
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static java.util.Objects.nonNull;

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
     */
    public AsyncQueryDispatcher() {
        this(null);
    }

    /**
     * Constructs a new {@code AsyncQueryDispatcher}.
     *
     * @param name the name of threads
     */
    public AsyncQueryDispatcher(@Nullable String name) {
        this(DEFAULT_THREAD_COUNT, name);
    }

    /**
     * Constructs a new {@code AsyncQueryDispatcher}.
     *
     * @param nThreads the number of threads to use
     *
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public AsyncQueryDispatcher(@Nonnegative int nThreads) {
        this(nThreads, null);
    }

    /**
     * Constructs a new {@code AsyncQueryDispatcher}.
     *
     * @param nThreads the number of threads to use
     * @param name     the name of threads
     *
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public AsyncQueryDispatcher(@Nonnegative int nThreads, @Nullable String name) {
        this.pool = LazyObject.with(() -> {
            ThreadFactory threadFactory = MoreThreads.newThreadFactory(nonNull(name) ? name : getClass().getSimpleName() + '@' + hashCode());
            return Executors.newFixedThreadPool(nThreads, threadFactory);
        });

        this.scheduler = LazyObject.with(() -> Schedulers.from(pool.get()));
    }

    @Nonnull
    @Override
    protected Completable map(Completable query) {
        return query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());
    }

    @Nonnull
    @Override
    protected <T> Maybe<T> map(Maybe<T> query) {
        return query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());
    }

    @Nonnull
    @Override
    protected <T> Single<T> map(Single<T> query) {
        return query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());
    }

    @Nonnull
    @Override
    protected <T> Observable<T> map(Observable<T> query) {
        return query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());
    }

    @Nonnull
    @Override
    protected <T> Flowable<T> map(Flowable<T> query) {
        return query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());
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
