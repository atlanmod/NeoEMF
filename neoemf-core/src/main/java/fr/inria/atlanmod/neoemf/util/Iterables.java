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
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

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
     * @param <T>       the type of elements of the {@code iterable}
     *
     * @return an {@link OptionalInt} containing the first index, or {@link OptionalInt#empty()} if the {@code iterable}
     * has no such elements
     */
    @Nonnull
    public static <T> OptionalInt firstIndexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        Iterator<T> iter = iterable.iterator();
        for (int i = 0; iter.hasNext(); i++) {
            if (predicate.test(iter.next())) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    /**
     * Returns the index in {@code iterable} of the last element that satisfies the provided {@code predicate}.
     *
     * @param iterable  the iterable
     * @param predicate the predicate to check the elements
     * @param <T>       the type of elements of the {@code iterable}
     *
     * @return an {@link OptionalInt} containing the last index, or {@link OptionalInt#empty()} if the {@code iterable}
     * has no such elements
     */
    @Nonnull
    public static <T> OptionalInt lastIndexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        if (iterable instanceof List) {
            List<T> list = (List<T>) iterable;
            for (int i = list.size() - 1; i > 0; i--) {
                if (predicate.test(list.get(i))) {
                    return OptionalInt.of(i);
                }
            }
            return OptionalInt.empty();
        }
        else {
            Iterator<T> iter = iterable.iterator();
            OptionalInt last = null;
            for (int i = 0; iter.hasNext(); i++) {
                if (predicate.test(iter.next())) {
                    last = OptionalInt.of(i);
                }
            }
            return isNull(last) ? OptionalInt.empty() : last;
        }
    }
}
