/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.commons.Throwables;

import java.util.List;
import java.util.ListIterator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A factory of {@link IterationHelper} instances.
 */
@ParametersAreNonnullByDefault
final class IterationHelpers {

    /**
     * An ascending iteration helper.
     */
    @Nonnull
    public static final IterationHelper ASCENDING = new IterationHelperAscending();

    /**
     * A descending iteration helper.
     */
    @Nonnull
    public static final IterationHelper DESCENDING = new IterationHelperDescending();

    private IterationHelpers() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * An ascending {@link IterationHelper}.
     */
    @ParametersAreNonnullByDefault
    private static final class IterationHelperAscending implements IterationHelper {

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
     * An descending {@link IterationHelper}.
     */
    @ParametersAreNonnullByDefault
    private static class IterationHelperDescending implements IterationHelper {

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
