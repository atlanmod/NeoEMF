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

package fr.inria.atlanmod.neoemf.util;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Static utility methods related to {@link Iterable} instances.
 */
@ParametersAreNonnullByDefault
public final class Iterables {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Iterables() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns a sequential {@link Stream} of the contents of {@code iterable}, delegating to
     * {@link Collection#stream} if possible.
     *
     * @param iterable the iterable
     *
     * @return a sequential {@link Stream} of the contents of {@code iterable}
     */
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return (iterable instanceof Collection)
                ? ((Collection<T>) iterable).stream()
                : StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Determines if the given {@code iterable} contains no element.
     *
     * @param iterable the iterable
     *
     * @return {@code true} if the iterable contains no element
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    /**
     * Determines if the given {@code iterable} contains at least one element.
     *
     * @param iterable the iterable
     *
     * @return {@code true} if the iterable contains at least one element
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return !isEmpty(iterable);
    }
}
