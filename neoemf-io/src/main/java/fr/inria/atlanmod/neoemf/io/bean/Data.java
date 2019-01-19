/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * A value that can be read, and resolved according to a context.
 */
@ParametersAreNonnullByDefault
// FIXME Find a better name for this class!
public final class Data<V> {

    /**
     * An empty data.
     */
    @Nonnull
    private static final Data<?> EMPTY = new Data<>();

    /**
     * The value, resolved or not according to {@link #resolved}.
     */
    private Object value;

    /**
     * {@code true} if the {@link #value} has been resolved, and is in its final state.
     */
    private boolean resolved;

    /**
     * Constructs a new empty {@code Data}.
     */
    private Data() {
    }

    /**
     * Constructs a new {@code Data} with its {@code value} and its state.
     *
     * @param value    the value
     * @param resolved {@code true} if the {@link #value} has been resolved
     */
    private Data(Object value, boolean resolved) {
        this.value = value;
        this.resolved = resolved;
    }

    /**
     * Returns an empty {@code Data}.
     *
     * @param <V> the type of data
     *
     * @return an empty data
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <V> Data<V> empty() {
        return (Data<V>) EMPTY;
    }

    /**
     * Returns a resolved {@code Data} with its {@code value}. A resolved value corresponds to a value in its final
     * state.
     *
     * @param value the resolved value
     * @param <V>   the type of the value
     *
     * @return a resolved data
     */
    @Nonnull
    public static <V> Data<V> resolved(V value) {
        return new Data<>(value, true);
    }

    /**
     * Returns a raw {@code Data} with its {@code value}.
     *
     * @param value the raw value
     * @param <V>   the type of the value
     *
     * @return a raw data
     */
    @Nonnull
    public static <V> Data<V> raw(Object value) {
        return new Data<>(value, false);
    }

    /**
     * Returns the resolved value.
     *
     * @return the resolved value
     */
    @SuppressWarnings("unchecked")
    public V getResolved() {
        checkState(!isPresent() || resolved, "The value has not been resolved");
        return (V) value;
    }

    /**
     * Returns the raw value.
     *
     * @param <U> the type of the raw value
     *
     * @return the raw value
     */
    @SuppressWarnings("unchecked")
    public <U> U getRaw() {
        checkState(!isPresent() || !resolved, "The value has been resolved");
        return (U) value;
    }

    /**
     * Returns {@code true} if the value is resolved.
     *
     * @return {@code true} if the value is resolved
     */
    public boolean isResolved() {
        return resolved && isPresent();
    }

    /**
     * Returns {@code true} if a value has been defined, resolved or not.
     *
     * @return {@code true} if a value has been defined
     */
    public boolean isPresent() {
        return nonNull(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Data<?> that = Data.class.cast(o);
        return resolved == that.resolved && Objects.equals(value, that.value);
    }
}
