/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import java.util.List;
import java.util.ListIterator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A stateless helper to iterate on a {@link ListIterator} in a defined direction.
 *
 * @see ContentsListIterator
 */
@ParametersAreNonnullByDefault
abstract class IterationHelper {

    /**
     * The ascending iteration helper that helps to iterate in the forward direction.
     */
    @Nonnull
    public static final IterationHelper ASCENDING = new AscendingIterationHelper();

    /**
     * The descending iteration helper that helps to iterate in the backward direction.
     */
    @Nonnull
    public static final IterationHelper DESCENDING = new DescendingIterationHelper();

    /**
     * Constructs a new {@code IterationHelper}.
     */
    protected IterationHelper() {
    }

    /**
     * Returns the direction of this helper. It defines the behavior of all other methods.
     *
     * @return the direction
     */
    @Nonnull
    public abstract IterationDirection direction();

    /**
     * Returns {@code true} if the {@code iterator} has more elements when traversing the list in the defined direction.
     *
     * @param iterator an iterator
     *
     * @return {@code true} if the {@code iterator} has more elements
     *
     * @see ListIterator#hasNext()
     * @see ListIterator#hasPrevious()
     */
    public abstract <E> boolean hasMoreElements(ListIterator<E> iterator);

    /**
     * Returns the next element in the {@code iterator} when traversing the list in the defined direction.
     *
     * @param iterator an iterator
     *
     * @return the next element
     *
     * @see ListIterator#next()
     * @see ListIterator#previous()
     */
    @Nonnull
    public abstract <E> E advance(ListIterator<E> iterator);

    /**
     * Moves back the {@code iterator}. This is the reverse operation of {@link #advance(ListIterator)}.
     *
     * @param iterator an iterator
     */
    public abstract <E> void reverse(ListIterator<E> iterator);

    /**
     * Adapts the current cursor of an iterator when traversing the list in the defined direction.
     *
     * @param cursor the cursor; the current position on an iterator
     *
     * @return the adapted cursor
     */
    @Nonnegative
    public abstract int adaptCursor(int cursor);

    /**
     * Returns the next cursor of an iterator when traversing the list in the defined direction.
     *
     * @param cursor the cursor; the current position on an iterator
     *
     * @return the next cursor
     */
    @Nonnegative
    public abstract int advanceCursor(int cursor);

    /**
     * Returns the first position to use when creating a new {@link ListIterator}.
     *
     * @param list the list on which the list iterator will be created
     *
     * @return the first position
     *
     * @see List#listIterator(int)
     */
    @Nonnegative
    public abstract int getFirstIndex(List<?> list);

    /**
     * The ascending iteration helper that helps to iterate in the forward direction.
     */
    @ParametersAreNonnullByDefault
    private static final class AscendingIterationHelper extends IterationHelper {

        @Nonnull
        @Override
        public IterationDirection direction() {
            return IterationDirection.ASCENDING;
        }

        @Override
        public <E> boolean hasMoreElements(ListIterator<E> iterator) {
            return iterator.hasNext();
        }

        @Nonnull
        @Override
        public <E> E advance(ListIterator<E> iterator) {
            return iterator.next();
        }

        @Override
        public <E> void reverse(ListIterator<E> iterator) {
            iterator.previous();
        }

        @Nonnegative
        @Override
        public int adaptCursor(int cursor) {
            return cursor;
        }

        @Nonnegative
        @Override
        public int advanceCursor(int cursor) {
            return cursor + 1;
        }

        @Nonnegative
        @Override
        public int getFirstIndex(List<?> list) {
            return 0;
        }
    }

    /**
     * The descending iteration helper that helps to iterate in the backward direction.
     */
    @ParametersAreNonnullByDefault
    private static class DescendingIterationHelper extends IterationHelper {

        @Nonnull
        @Override
        public IterationDirection direction() {
            return IterationDirection.DESCENDING;
        }

        @Override
        public <E> boolean hasMoreElements(ListIterator<E> iterator) {
            return iterator.hasPrevious();
        }

        @Nonnull
        @Override
        public <E> E advance(ListIterator<E> iterator) {
            return iterator.previous();
        }

        @Override
        public <E> void reverse(ListIterator<E> iterator) {
            iterator.next();
        }

        @Nonnegative
        @Override
        public int adaptCursor(int cursor) {
            return cursor - 1;
        }

        @Nonnegative
        @Override
        public int advanceCursor(int cursor) {
            return cursor - 1;
        }

        @Nonnegative
        @Override
        public int getFirstIndex(List<?> list) {
            return list.size();
        }
    }
}
