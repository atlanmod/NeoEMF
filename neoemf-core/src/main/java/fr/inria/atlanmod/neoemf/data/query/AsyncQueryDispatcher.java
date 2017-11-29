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
import io.reactivex.functions.Action;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link QueryDispatcher} that dispatches and executes queries asynchronously using an {@link ExecutorService}.
 */
@ParametersAreNonnullByDefault
public class AsyncQueryDispatcher implements QueryDispatcher {

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
    public Completable submit(Completable query) {
        return submit(Functions.EMPTY_ACTION, query);
    }

    @Nonnull
    @Override
    public Completable submit(Action before, Completable query) {
        checkNotNull(query, "query");

        Completable scheduledQuery = query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());

        return executeBefore(before)
                .andThen(scheduledQuery)
                .cache();
    }

    @Nonnull
    @Override
    public <T> Maybe<T> submit(Maybe<T> query) {
        return submit(Functions.EMPTY_ACTION, query);
    }

    @Nonnull
    @Override
    public <T> Maybe<T> submit(Action before, Maybe<T> query) {
        checkNotNull(query, "query");

        Maybe<T> scheduledQuery = query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());

        return executeBefore(before)
                .andThen(scheduledQuery)
                .cache();
    }

    @Nonnull
    @Override
    public <T> Single<T> submit(Single<T> query) {
        return submit(Functions.EMPTY_ACTION, query);
    }

    @Nonnull
    @Override
    public <T> Single<T> submit(Action before, Single<T> query) {
        checkNotNull(query, "query");

        Single<T> scheduledQuery = query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());

        return executeBefore(before)
                .andThen(scheduledQuery)
                .cache();
    }

    @Nonnull
    @Override
    public <T> Observable<T> submit(Observable<T> query) {
        return submit(Functions.EMPTY_ACTION, query);
    }

    @Nonnull
    @Override
    public <T> Observable<T> submit(Action before, Observable<T> query) {
        checkNotNull(query, "query");

        Observable<T> scheduledQuery = query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());

        return executeBefore(before)
                .andThen(scheduledQuery)
                .cache();
    }

    @Nonnull
    @Override
    public <T> Flowable<T> submit(Flowable<T> query) {
        return submit(Functions.EMPTY_ACTION, query);
    }

    @Nonnull
    @Override
    public <T> Flowable<T> submit(Action before, Flowable<T> query) {
        checkNotNull(query, "query");

        Flowable<T> scheduledQuery = query
                .observeOn(scheduler.get())
                .subscribeOn(scheduler.get())
                .unsubscribeOn(scheduler.get());

        return executeBefore(before)
                .andThen(scheduledQuery)
                .cache();
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

    /**
     * Executes the given tasks before executing any asynchronous task.
     *
     * @param before the pre-processing method to execute before any database call
     *
     * @return the deferred computation
     */
    @Nonnull
    private Completable executeBefore(Action before) {
        checkNotNull(before, "before");

        return Completable.fromAction(before);
    }
}
