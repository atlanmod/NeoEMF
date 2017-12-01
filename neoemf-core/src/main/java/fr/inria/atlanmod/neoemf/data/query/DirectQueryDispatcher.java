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

/**
 * A {@link QueryDispatcher} that directly executes queries, on the current {@link Thread}.
 */
@ParametersAreNonnullByDefault
public final class DirectQueryDispatcher extends AbstractQueryDispatcher {

    @Nonnull
    @Override
    protected Completable map(Completable query) {
        return query;
    }

    @Nonnull
    @Override
    protected <T> Maybe<T> map(Maybe<T> query) {
        return query;
    }

    @Nonnull
    @Override
    protected <T> Single<T> map(Single<T> query) {
        return query;
    }

    @Nonnull
    @Override
    protected <T> Observable<T> map(Observable<T> query) {
        return query;
    }

    @Nonnull
    @Override
    protected <T> Flowable<T> map(Flowable<T> query) {
        return query;
    }

    @Override
    public void close() {
        // Do nothing
    }
}
