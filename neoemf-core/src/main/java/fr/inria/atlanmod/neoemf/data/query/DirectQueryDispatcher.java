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

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

/**
 * A {@link QueryDispatcher} that directly executes queries, on the current {@link Thread}.
 */
@ParametersAreNonnullByDefault
public final class DirectQueryDispatcher extends AbstractQueryDispatcher {

    @Nonnull
    protected CompletableTransformer scheduleCompletable() {
        return q -> q;
    }

    @Nonnull
    @Override
    protected <T> MaybeTransformer<T, T> scheduleMaybe() {
        return q -> q;
    }

    @Nonnull
    @Override
    protected <T> SingleTransformer<T, T> scheduleSingle() {
        return q -> q;
    }

    @Nonnull
    @Override
    protected <T> ObservableTransformer<T, T> scheduleObservable() {
        return q -> q;
    }

    @Nonnull
    @Override
    protected <T> FlowableTransformer<T, T> scheduleFlowable() {
        return q -> q;
    }

    @Override
    public void close() {
        // Do nothing
    }
}
