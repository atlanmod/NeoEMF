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

/**
 * Static utility methods related to {@link Stream} instances.
 */
public final class Streams {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Streams() {
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
}
