/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.commons.annotation.Static;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

/**
 * A static helper class for ReactiveX test-based.
 */
@Static
@ParametersAreNonnullByDefault
public final class RxTestUtils {

    /**
     * The maximum amount of time to execute a query.
     */
    @Nonnegative
    private static final int TIMEOUT = 20;

    /**
     * The time-unit of the timeout.
     */
    @Nonnull
    private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS;

    @SuppressWarnings("JavaDoc")
    private RxTestUtils() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Subscribes to the {@code completable} with a defined timeout.
     *
     * @param completable the completable to subscribe
     *
     * @return the test observer on the {@code completable}
     */
    @Nonnull
    public static TestObserver<Void> submit(Completable completable) {
        return completable.timeout(TIMEOUT, TIMEOUT_UNIT).test();
    }

    /**
     * Subscribes to the {@code completable} with a defined timeout and awaits the end of its execution.
     *
     * @param completable the completable to subscribe
     *
     * @return the test observer on the {@code completable}
     */
    @Nonnull
    public static TestObserver<Void> await(Completable completable) {
        return submit(completable).awaitDone(TIMEOUT, TIMEOUT_UNIT);
    }

    /**
     * Subscribes to the {@code maybe} with a defined timeout.
     *
     * @param maybe the maybe to subscribe
     *
     * @return the test observer on the {@code maybe}
     */
    @Nonnull
    public static <V> TestObserver<V> submit(Maybe<V> maybe) {
        return maybe.timeout(TIMEOUT, TIMEOUT_UNIT).test();
    }

    /**
     * Subscribes to the {@code maybe} with a defined timeout and awaits the end of its execution.
     *
     * @param maybe the maybe to subscribe
     *
     * @return the test observer on the {@code maybe}
     */
    @Nonnull
    public static <V> TestObserver<V> await(Maybe<V> maybe) {
        return submit(maybe).awaitDone(TIMEOUT, TIMEOUT_UNIT);
    }

    /**
     * Subscribes to the {@code single} with a defined timeout.
     *
     * @param single the single to subscribe
     *
     * @return the test observer on the {@code single}
     */
    @Nonnull
    public static <V> TestObserver<V> submit(Single<V> single) {
        return single.timeout(TIMEOUT, TIMEOUT_UNIT).test();
    }

    /**
     * Subscribes to the {@code single} with a defined timeout and awaits the end of its execution.
     *
     * @param single the single to subscribe
     *
     * @return the test observer on the {@code single}
     */
    @Nonnull
    public static <V> TestObserver<V> await(Single<V> single) {
        return submit(single).awaitDone(TIMEOUT, TIMEOUT_UNIT);
    }

    /**
     * Subscribes to the {@code flowable} with a defined timeout.
     *
     * @param flowable the flowable to subscribe
     *
     * @return the test subscriber on the {@code flowable}
     */
    @Nonnull
    public static <V> TestSubscriber<V> submit(Flowable<V> flowable) {
        return flowable.timeout(TIMEOUT, TIMEOUT_UNIT).test();
    }

    /**
     * Subscribes to the {@code flowable} with a defined timeout and awaits the end of its execution.
     *
     * @param flowable the flowable to subscribe
     *
     * @return the test subscriber on the {@code flowable}
     */
    @Nonnull
    public static <V> TestSubscriber<V> await(Flowable<V> flowable) {
        return submit(flowable).awaitDone(TIMEOUT, TIMEOUT_UNIT);
    }
}
