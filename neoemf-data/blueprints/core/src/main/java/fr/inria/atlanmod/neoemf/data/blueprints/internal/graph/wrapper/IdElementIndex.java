/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.wrapper;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Index;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Index} able to map the result of each method call to a dedicated implementation.
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdVertexIndex} and {@link
 * com.tinkerpop.blueprints.util.wrappers.id.IdEdgeIndex}.
 *
 * @param <T> the type of the base elements
 * @param <U> the type of this index, and the elements after mapping
 */
@ParametersAreNonnullByDefault
class IdElementIndex<T extends Element, U extends T> extends AbstractBasedObject<Index<T>> implements Index<U> {

    /**
     * TODO
     */
    @Nonnull
    private final Function<T, U> mappingFunc;

    /**
     * Constructs a new {@code IdElementIndex}.
     *
     * @param baseIndex   the base index
     * @param mappingFunc the function to create a new dedicated element from another
     */
    protected IdElementIndex(Index<T> baseIndex, Function<T, U> mappingFunc) {
        super(baseIndex);

        this.mappingFunc = mappingFunc;
    }

    @Override
    public String getIndexName() {
        return base.getIndexName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<U> getIndexClass() {
        return (Class<U>) base.getIndexClass();
    }

    @Override
    public void put(String key, Object value, U element) {
        base.put(key, value, getBaseElement(element));
    }

    @Override
    public CloseableIterable<U> get(String key, Object value) {
        return new IdElementIterable<>(base.get(key, value), mappingFunc);
    }

    @Override
    public CloseableIterable<U> query(String key, Object query) {
        return new IdElementIterable<>(base.query(key, query), mappingFunc);
    }

    @Override
    public long count(String key, Object value) {
        return base.count(key, value);
    }

    @Override
    public void remove(String key, Object value, U element) {
        base.remove(key, value, getBaseElement(element));
    }

    /**
     * Returns the base element of the specified element.
     *
     * @param t the element
     *
     * @return the base element
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private T getBaseElement(U t) {
        return ((IdElement<U, ?>) t).getBaseElement();
    }
}
