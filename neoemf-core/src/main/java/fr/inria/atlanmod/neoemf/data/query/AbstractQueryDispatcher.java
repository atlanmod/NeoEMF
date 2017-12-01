/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.query;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link QueryDispatcher}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractQueryDispatcher implements QueryDispatcher {

    @Nonnull
    @Override
    public final Completable submit(Completable query) {
        checkNotNull(query, "query");

        return map(query).cache();
    }

    @Nonnull
    @Override
    public final <T> Maybe<T> submit(Maybe<T> query) {
        checkNotNull(query, "query");

        return map(query).cache();
    }

    @Nonnull
    @Override
    public final <T> Single<T> submit(Single<T> query) {
        checkNotNull(query, "query");

        return map(query).cache();
    }

    @Nonnull
    @Override
    public final <T> Observable<T> submit(Observable<T> query) {
        checkNotNull(query, "query");

        return map(query).cache();
    }

    @Nonnull
    @Override
    public final <T> Flowable<T> submit(Flowable<T> query) {
        checkNotNull(query, "query");

        return map(query).cache();
    }

    /**
     * Maps a {@link Completable} instance.
     *
     * @param query the query to map
     *
     * @return the mapped query
     */
    @Nonnull
    protected abstract Completable map(Completable query);

    /**
     * Maps a {@link Maybe} instance.
     *
     * @param query the query to map
     *
     * @return the mapped query
     */
    @Nonnull
    protected abstract <T> Maybe<T> map(Maybe<T> query);

    /**
     * Maps a {@link Single} instance.
     *
     * @param query the query to map
     *
     * @return the mapped query
     */
    @Nonnull
    protected abstract <T> Single<T> map(Single<T> query);

    /**
     * Maps an {@link Observable} instance.
     *
     * @param query the query to map
     *
     * @return the mapped query
     */
    @Nonnull
    protected abstract <T> Observable<T> map(Observable<T> query);

    /**
     * Maps a {@link Flowable} instance.
     *
     * @param query the query to map
     *
     * @return the mapped query
     */
    @Nonnull
    protected abstract <T> Flowable<T> map(Flowable<T> query);
}
