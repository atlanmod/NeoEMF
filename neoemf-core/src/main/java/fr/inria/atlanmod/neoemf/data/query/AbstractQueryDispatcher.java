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
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;

/**
 * An abstract {@link QueryDispatcher}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractQueryDispatcher implements QueryDispatcher {

    @Nonnull
    public final CompletableTransformer dispatchCompletable() {
        return q -> scheduleCompletable().apply(q.cache());
    }

    @Nonnull
    @Override
    public final <T> MaybeTransformer<T, T> dispatchMaybe() {
        return q -> this.<T>scheduleMaybe().apply(q.cache());
    }

    @Nonnull
    @Override
    public final <T> SingleTransformer<T, T> dispatchSingle() {
        return q -> this.<T>scheduleSingle().apply(q.cache());
    }

    @Nonnull
    @Override
    public final <T> ObservableTransformer<T, T> dispatchObservable() {
        return q -> this.<T>scheduleObservable().apply(q.cache());
    }

    @Nonnull
    @Override
    public final <T> FlowableTransformer<T, T> dispatchFlowable() {
        return q -> this.<T>scheduleFlowable().apply(q.cache());
    }

    /**
     * Schedules a {@link Completable} instance.
     *
     * @return the scheduled completable
     */
    @Nonnull
    protected abstract CompletableTransformer scheduleCompletable();

    /**
     * Schedules a {@link Maybe} instance.
     *
     * @return the scheduled maybe
     */
    @Nonnull
    protected abstract <T> MaybeTransformer<T, T> scheduleMaybe();

    /**
     * Schedules a {@link Single} instance.
     *
     * @return the scheduled single
     */
    @Nonnull
    protected abstract <T> SingleTransformer<T, T> scheduleSingle();

    /**
     * Schedules an {@link Observable} instance.
     *
     * @return the scheduled observable
     */
    @Nonnull
    protected abstract <T> ObservableTransformer<T, T> scheduleObservable();

    /**
     * Schedules a {@link Flowable} instance.
     *
     * @return the scheduled flowable
     */
    @Nonnull
    protected abstract <T> FlowableTransformer<T, T> scheduleFlowable();
}
