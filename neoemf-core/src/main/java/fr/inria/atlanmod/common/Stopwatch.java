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

package fr.inria.atlanmod.common;

import java.time.Duration;
import java.time.Instant;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkState;
import static java.util.Objects.isNull;

/**
 * An object that measures elapsed time using {@link Instant}.
 */
@ParametersAreNonnullByDefault
public final class Stopwatch {

    /**
     * The start instant.
     */
    private Instant start;

    /**
     * The end instant.
     */
    private Instant end;

    /**
     * Whether {@link #start()} has been called, and {@link #stop()} has been not called since the last call to {@link
     * #start()}.
     */
    private boolean isRunning;

    /**
     * Constructs a new {@code Stopwatch}.
     */
    private Stopwatch() {
    }

    /**
     * Creates (but does not start) a new stopwatch.
     *
     * @return a new stopwatch
     */
    @Nonnull
    public static Stopwatch createUnstarted() {
        return new Stopwatch();
    }

    /**
     * Creates (and starts) a new stopwatch.
     *
     * @return a new stopwatch
     */
    @Nonnull
    public static Stopwatch createStarted() {
        return new Stopwatch().start();
    }

    /**
     * Checks whether this stopwatch is currently running.
     *
     * @return {@code true} if {@link #start()} has been called, and {@link #stop()} has been not called since the last
     * call to {@link #start()}
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Starts this stopwatch.
     *
     * @return this stopwatch
     *
     * @throws IllegalStateException if this stopwatch is already running
     */
    @Nonnull
    public Stopwatch start() {
        checkState(!isRunning, "This stopwatch is already running");
        isRunning = true;
        start = Instant.now();
        return this;
    }

    /**
     * Stops this stopwatch.
     *
     * @return this stopwatch
     *
     * @throws IllegalStateException if this stopwatch is already stopped
     */
    @Nonnull
    public Stopwatch stop() {
        checkState(isRunning, "This stopwatch is already stopped");
        isRunning = false;
        end = Instant.now();
        return this;
    }

    /**
     * Sets the elapsed time for this stopwatch to {@code 0}, and places it in a stopped state.
     *
     * @return this stopwatch
     */
    @Nonnull
    public Stopwatch reset() {
        start = null;
        end = null;
        isRunning = false;
        return this;
    }

    /**
     * Returns the current elapsed time shown on this stopwatch. If this stopwatch is currently running, the duration is
     * processed according to the starting point until now.
     *
     * @return the duration
     */
    @Nonnull
    public Duration elapsed() {
        if (isRunning) {
            return Duration.between(start, Instant.now());
        }

        if (isNull(start)) {
            return Duration.ofNanos(0);
        }

        return Duration.between(start, end);
    }
}
