/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A result prepared by iterating a {@link ListIterator} in a defined {@link IterationDirection direction}.
 *
 * @param <E> the type of the value
 *
 * @see ContentsListIterator
 */
@ParametersAreNonnullByDefault
class IterationResult<E> {

    /**
     * The instance of an undefined {@code IterationResult}.
     */
    @Nonnull
    private static final IterationResult<?> UNDEFINED = new EmptyIterationResult<>();

    /**
     * The result value.
     */
    private final E value;

    /**
     * The feature associated to the {@link #value}.
     */
    private final EStructuralFeature feature;

    /**
     * The iteration direction used to prepare this result.
     */
    @Nonnull
    private final IterationDirection direction;

    /**
     * Constructs a new {@code IterationResult} with a value and a feature.
     *
     * @param direction the iteration direction used to prepare this result
     * @param value     the result value
     * @param feature   the feature associated to the {@code value}
     */
    @SuppressWarnings("unchecked")
    public IterationResult(IterationDirection direction, Object value, EStructuralFeature feature) {
        checkNotNull(direction, "direction");

        this.direction = direction;
        this.value = (E) value;
        this.feature = feature;
    }

    /**
     * Constructs a new {@code IterationResult} from an {@code entry}.
     *
     * @param direction the iteration direction used to prepare this result
     * @param entry     the entry from which to retrieve the result and its associated feature
     */
    public IterationResult(IterationDirection direction, FeatureMap.Entry entry) {
        this(direction, entry.getValue(), entry.getEStructuralFeature());
    }

    /**
     * Returns an undefined {@code IterationResult}, i.e. not initialized.
     *
     * @param <E> the type of the result
     *
     * @return an undefined result
     *
     * @see #isInitialized()
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E> IterationResult<E> undefined() {
        return (IterationResult<E>) UNDEFINED;
    }

    /**
     * Returns an empty {@code IterationResult}.
     *
     * @param direction the iteration direction used to prepared this result
     * @param <E>       the type of the result
     *
     * @return an empty result
     */
    @Nonnull
    public static <E> IterationResult<E> empty(IterationDirection direction) {
        return new EmptyIterationResult<>(direction);
    }

    /**
     * Returns the result value.
     *
     * @return the result value
     */
    @Nonnull
    public E value() {
        return value;
    }

    /**
     * Returns the feature associated to the result.
     *
     * @return the feature associated to the result
     */
    @Nonnull
    public EStructuralFeature feature() {
        return feature;
    }

    /**
     * Returns {@code true} if the iteration direction used to prepare this result is {@code expectedDirection}.
     *
     * @param expectedDirection the expected iteration direction
     *
     * @return {@code true} if the iteration direction used to prepare this result is {@code expectedDirection}
     */
    public boolean hasDirection(IterationDirection expectedDirection) {
        return direction == expectedDirection;
    }

    /**
     * Returns {@code true} if this result has no value.
     *
     * @return {@code true} if this result has no value
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns {@code true} if this result has been initialized.
     *
     * @return {@code true} if this result has been initialized
     */
    public boolean isInitialized() {
        return direction != IterationDirection.UNDEFINED;
    }

    /**
     * A {@link IterationResult} with no value.
     *
     * @param <E> the type of the value
     */
    @ParametersAreNonnullByDefault
    private static final class EmptyIterationResult<E> extends IterationResult<E> {

        /**
         * Constructs a new {@code Empty}.
         */
        public EmptyIterationResult() {
            this(IterationDirection.UNDEFINED);
        }

        /**
         * Constructs a new {@code Empty}.
         *
         * @param direction the iteration direction used to prepared this result
         */
        @SuppressWarnings("ConstantConditions") // null values in constructor
        public EmptyIterationResult(IterationDirection direction) {
            super(direction, null, null);
        }

        @Nonnull
        @Override
        public E value() {
            throw new NoSuchElementException();
        }

        @Nonnull
        @Override
        public EStructuralFeature feature() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
