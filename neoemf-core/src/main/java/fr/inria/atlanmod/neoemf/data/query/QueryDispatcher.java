/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.query;

import fr.inria.atlanmod.neoemf.data.Backend;

import java.io.Closeable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

/**
 * An object that dispatches and executes queries on a {@link Backend} instance.
 * <p>
 * <b>NOTE:</b> Each query is executed once.
 */
@ParametersAreNonnullByDefault
public interface QueryDispatcher extends Closeable {

    /**
     * Asynchronously executes the given {@code query}, using the {@code backend}.
     *
     * @param query the query to execute on the database
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable submit(Completable query);

    /**
     * Asynchronously executes the given tasks, using the {@code backend}.
     *
     * @param before the pre-processing method to execute before any database call; synchronous
     * @param query  the query to execute on the database
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable submit(Action before, Completable query);

    /**
     * Asynchronously executes the given {@code query}, using the {@code backend}.
     *
     * @param query the query to execute on the database
     * @param <T>   the type of the expected result
     *
     * @return the deferred computation with a result
     */
    @Nonnull
    <T> Maybe<T> submit(Maybe<T> query);

    /**
     * Asynchronously executes the given tasks, using the {@code backend}.
     *
     * @param before the pre-processing method to execute before any database call; synchronous
     * @param query  the query to execute on the database
     * @param <T>    the type of the expected result
     *
     * @return the deferred computation with a result
     */
    @Nonnull
    <T> Maybe<T> submit(Action before, Maybe<T> query);

    /**
     * Asynchronously executes the given {@code query}, using the {@code backend}.
     *
     * @param query the query to execute on the database
     *
     * @return the deferred computation with a result
     */
    @Nonnull
    <T> Single<T> submit(Single<T> query);

    /**
     * Asynchronously executes the given tasks, using the {@code backend}.
     *
     * @param before the pre-processing method to execute before any database call; synchronous
     * @param query  the query to execute on the database
     * @param <T>    the type of the expected result
     *
     * @return the deferred computation with a result
     */
    @Nonnull
    <T> Single<T> submit(Action before, Single<T> query);

    /**
     * Asynchronously executes the given {@code query}, using the {@code backend}.
     *
     * @param query the query to execute on the database
     * @param <T>   the type of the expected result
     *
     * @return the deferred computation with results
     */
    @Nonnull
    <T> Observable<T> submit(Observable<T> query);

    /**
     * Asynchronously executes the given tasks, using the {@code backend}.
     *
     * @param before the pre-processing method to execute before any database call; synchronous
     * @param query  the query to execute on the database
     * @param <T>    the type of the expected result
     *
     * @return the deferred computation with results
     */
    @Nonnull
    <T> Observable<T> submit(Action before, Observable<T> query);

    /**
     * Asynchronously executes the given {@code query}, using the {@code backend}.
     *
     * @param query the query to execute on the database
     * @param <T>   the type of the expected result
     *
     * @return the deferred computation with results
     */
    @Nonnull
    <T> Flowable<T> submit(Flowable<T> query);

    /**
     * Asynchronously executes the given tasks, using the {@code backend}.
     *
     * @param before the pre-processing method to execute before any database call; synchronous
     * @param query  the query to execute on the database
     * @param <T>    the type of the expected result
     *
     * @return the deferred computation with results
     */
    @Nonnull
    <T> Flowable<T> submit(Action before, Flowable<T> query);
}
