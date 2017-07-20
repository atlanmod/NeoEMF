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

package fr.inria.atlanmod.common.collect;

import fr.inria.atlanmod.common.annotation.Static;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * Static utility methods related to {@link Iterator} instances.
 */
@Static
@ParametersAreNonnullByDefault
public final class MoreIterators {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private MoreIterators() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Determines if the given {@code iterator} contains no element.
     *
     * @param iterator the iterator
     *
     * @return {@code true} if the iterator contains no element
     */
    public static <E> boolean isEmpty(Iterator<E> iterator) {
        checkNotNull(iterator);

        return Collection.class.isInstance(iterator)
                ? Collection.class.cast(iterator).isEmpty()
                : !iterator.hasNext();
    }

    /**
     * Determines if the given {@code iterator} contains at least one element.
     *
     * @param iterator the iterator
     *
     * @return {@code true} if the iterator contains at least one element
     */
    public static <E> boolean notEmpty(Iterator<E> iterator) {
        return !isEmpty(iterator);
    }

    /**
     * Returns the single element contained in {@code iterator}.
     *
     * @param iterator the iterator
     *
     * @return an {@link Optional} containing the single element of the {@code iterator}, or {@link Optional#empty()}
     * if the {@code iterator} is empty.
     *
     * @throws IllegalArgumentException if the {@code iterator} contains more than one element
     */
    @Nonnull
    public static <E> Optional<E> onlyElement(Iterator<E> iterator) {
        E first = null;

        if (iterator.hasNext()) {
            first = iterator.next();

            if (iterator.hasNext()) {
                throw new IllegalArgumentException("Expected one element in the iterator");
            }
        }

        return Optional.ofNullable(first);
    }
}
