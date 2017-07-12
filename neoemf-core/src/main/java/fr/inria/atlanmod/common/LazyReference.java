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

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An object that automatically loads an object of type {@link T} by using a {@link Supplier}.
 * The value is wrapped in a {@link Reference} that is automatically refreshed on-demand when it has been cleared,
 * either by the program or by the garbage collector.
 *
 * @param <T> the type of the embed value
 *
 * @see Reference
 */
@ParametersAreNonnullByDefault
public abstract class LazyReference<T> {

    /**
     * The function used to refresh the value.
     */
    @Nonnull
    private final Supplier<T> refreshFunction;

    /**
     * The reference of the value.
     */
    @Nullable
    private Reference<T> reference;

    /**
     * Whether this reference has a value.
     */
    private boolean isPresent;

    /**
     * Constructs a new {@code LazyReference}.
     *
     * @param refreshFunction the function used to refresh the value
     */
    public LazyReference(Supplier<T> refreshFunction) {
        this.refreshFunction = refreshFunction;
    }

    /**
     * Creates a new lazy reference that uses {@link SoftReference}.
     *
     * @param refreshFunction the function used to refresh the value
     * @param <T>             the type of the embed value
     *
     * @return a new lazy reference
     */
    public static <T> LazyReference<T> soft(Supplier<T> refreshFunction) {
        return new Soft<>(refreshFunction);
    }

    /**
     * Creates a new lazy reference that uses {@link WeakReference}.
     *
     * @param refreshFunction the function used to refresh the value
     * @param <T>             the type of the embed value
     *
     * @return a new lazy reference
     */
    public static <T> LazyReference<T> weak(Supplier<T> refreshFunction) {
        return new Weak<>(refreshFunction);
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public T get() {
        if (nonNull(reference)) { // Has been loaded at least once
            T value = reference.get();
            if (isPresent && isNull(value)) { // The object has been cleared
                return refresh();
            }
            return value;
        }

        return refresh();
    }

    /**
     * Updates the value.
     *
     * @param newValue the new value
     */
    public void update(@Nullable T newValue) {
        reference = newReference(newValue);
        isPresent = nonNull(newValue);
    }

    /**
     * Refreshes the value.
     *
     * @return the refreshed value
     */
    @Nullable
    private T refresh() {
        T value = refreshFunction.get();
        update(value);
        return value;
    }

    /**
     * Creates a new {@link Reference} for the given {@code value}.
     *
     * @param value the value to embed
     *
     * @return a reference
     */
    @Nonnull
    protected abstract Reference<T> newReference(@Nullable T value);

    /**
     * A {@link LazyReference} that wraps its value in a {@link SoftReference}.
     *
     * @param <T> the type of the embed value
     *
     * @see SoftReference
     */
    @ParametersAreNonnullByDefault
    private static final class Soft<T> extends LazyReference<T> {

        /**
         * Constructs a new {@code Soft}.
         *
         * @param refreshFunction the function used to refresh the value
         */
        public Soft(Supplier<T> refreshFunction) {
            super(refreshFunction);
        }

        @Nonnull
        @Override
        protected Reference<T> newReference(@Nullable T value) {
            return new SoftReference<>(value);
        }
    }

    /**
     * A {@link LazyReference} that wraps its value in a {@link WeakReference}.
     *
     * @param <T> the type of the embed value
     *
     * @see WeakReference
     */
    @ParametersAreNonnullByDefault
    private static final class Weak<T> extends LazyReference<T> {

        /**
         * Constructs a new {@code Weak}.
         *
         * @param refreshFunction the function used to refresh the value
         */
        public Weak(Supplier<T> refreshFunction) {
            super(refreshFunction);
        }

        @Nonnull
        @Override
        protected Reference<T> newReference(@Nullable T value) {
            return new WeakReference<>(value);
        }
    }
}
