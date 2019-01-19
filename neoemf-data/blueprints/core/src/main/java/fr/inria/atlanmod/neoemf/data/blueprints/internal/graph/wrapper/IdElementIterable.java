/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.wrapper;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Element;

import org.atlanmod.commons.collect.DelegatedIterator;

import java.util.Iterator;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Iterable} able to map the result of each method call to a dedicated implementation.
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdVertexIterable} and {@link
 * com.tinkerpop.blueprints.util.wrappers.id.IdEdgeIterable}.
 *
 * @param <T> the type of the base elements
 * @param <U> the type of this index, and the elements after mapping
 */
@ParametersAreNonnullByDefault
class IdElementIterable<T extends Element, U extends T> extends AbstractBasedObject<Iterable<T>> implements CloseableIterable<U> {

    /**
     * The function to create a new dedicated element from another.
     */
    @Nonnull
    private final Function<T, U> mappingFunc;

    /**
     * Constructs a new {@code IdElementIterable}.
     *
     * @param baseIterable the base iterable
     * @param mappingFunc  the function to create a new dedicated element from another
     */
    protected IdElementIterable(Iterable<T> baseIterable, Function<T, U> mappingFunc) {
        super(baseIterable);

        this.mappingFunc = mappingFunc;
    }

    @Override
    public void close() {
        if (CloseableIterable.class.isInstance(base)) {
            CloseableIterable.class.cast(base).close();
        }
    }

    @Nonnull
    @Override
    public Iterator<U> iterator() {
        return new DelegatedIterator<>(base.iterator(), mappingFunc);
    }
}
