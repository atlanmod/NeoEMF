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
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * Static utility methods related to {@link Iterable} instances.
 */
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

    /**
     * Returns the index in {@code iterable} of the first element that satisfies the provided {@code predicate}.
     *
     * @param iterable  the iterable
     * @param predicate the predicate to check the elements
     * @param mapper    the mapper to map items to their index
     * @param <T>       the type of elements of the {@code iterable}
     *
     * @return an {@link OptionalInt} containing the first index, or {@link OptionalInt#empty()} if the {@code iterable}
     * has no such elements
     */
    public static <T> OptionalInt firstIndexOf(Iterable<T> iterable, Predicate<? super T> predicate, ToIntFunction<? super T> mapper) {
        if (iterable instanceof List) {
            List<T> edgesList = (List<T>) iterable;

            int size = edgesList.size();
            for (int i = 0; i < size; i++) {
                if (predicate.test(edgesList.get(i))) {
                    return OptionalInt.of(i);
                }
            }
        }
        else {
            return Streams.stream(iterable)
                    .filter(predicate)
                    .mapToInt(mapper)
                    .min();
        }
        return OptionalInt.empty();
    }

    /**
     * Returns the index in {@code iterable} of the last element that satisfies the provided {@code predicate}.
     *
     * @param iterable  the iterable
     * @param predicate the predicate to check the elements
     * @param mapper    the mapper to map items to their index
     * @param <T>       the type of elements of the {@code iterable}
     *
     * @return an {@link OptionalInt} containing the last index, or {@link OptionalInt#empty()} if the {@code iterable}
     * has no such elements
     */
    public static <T> OptionalInt lastIndexOf(Iterable<T> iterable, Predicate<? super T> predicate, ToIntFunction<? super T> mapper) {
        if (iterable instanceof List) {
            List<T> list = (List<T>) iterable;

            int size = list.size();
            for (int i = size - 1; i > 0; i--) {
                if (predicate.test(list.get(i))) {
                    return OptionalInt.of(i);
                }
            }

            return OptionalInt.empty();
        }
        else {
            return Streams.stream(iterable)
                    .filter(predicate)
                    .mapToInt(mapper)
                    .max();
        }
    }
}
