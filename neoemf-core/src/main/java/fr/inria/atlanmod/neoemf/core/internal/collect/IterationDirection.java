/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal.collect;

import java.util.List;
import java.util.ListIterator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An iteration direction.
 */
@ParametersAreNonnullByDefault
enum IterationDirection implements IterationHelper {

    /**
     * Undefined direction. All methods throw an {@link UnsupportedOperationException}.
     */
    UNDEFINED {
        @Override
        public <E> boolean hasMoreElements(ListIterator<E> iterator) {
            throw new UnsupportedOperationException("hasMoreElements");
        }

        @Nonnull
        @Override
        public <E> E advance(ListIterator<E> iterator) {
            throw new UnsupportedOperationException("advance");
        }

        @Override
        public <E> void reverse(ListIterator<E> iterator) {
            throw new UnsupportedOperationException("reverse");
        }

        @Override
        public int adaptCursor(int cursor) {
            throw new UnsupportedOperationException("adaptCursor");
        }

        @Override
        public int advanceCursor(int cursor) {
            throw new UnsupportedOperationException("advanceCursor");
        }

        @Override
        public int getFirstIndex(List<?> list) {
            throw new UnsupportedOperationException("getFirstIndex");
        }
    },

    /**
     * Forward direction; from start to end.
     */
    FORWARD {
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
    },

    /**
     * Backward direction; from end to start.
     */
    BACKWARD {
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
