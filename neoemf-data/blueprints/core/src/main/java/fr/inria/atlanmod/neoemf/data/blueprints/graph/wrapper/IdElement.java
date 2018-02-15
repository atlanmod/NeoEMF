/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.util.ElementHelper;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdElement}.
 *
 * @param <E>
 * @param <G>
 */
@ParametersAreNonnullByDefault
public abstract class IdElement<E extends Element, G extends IdGraph<G>> extends AbstractGraphBasedObject<E, G> implements Element {

    /**
     * {@code true} if the graph supports property-based identifiers.
     */
    protected final boolean propertyBased;

    /**
     * Constructs a new {@code IdElement}.
     *
     * @param baseElement   the base element
     * @param graph         the graph that owns this element
     * @param propertyBased {@code true} if the graph supports property-based identifiers
     */
    protected IdElement(E baseElement, G graph, boolean propertyBased) {
        super(baseElement, graph);

        this.propertyBased = propertyBased;
    }

    @Override
    public <T> T getProperty(String key) {
        return propertyBased && key.equals(IdGraph.ID) ? null : base.getProperty(key);
    }

    @Override
    public Set<String> getPropertyKeys() {
        if (propertyBased) {
            final Set<String> keys = new HashSet<>(base.getPropertyKeys());
            keys.remove(IdGraph.ID);
            return keys;
        }
        else {
            return base.getPropertyKeys();
        }
    }

    @Override
    public void setProperty(String key, Object value) {
        checkArgument(!propertyBased || !key.equals(IdGraph.ID), "Unable to set value for reserved property %s", IdGraph.ID);

        base.setProperty(key, value);
    }

    @Override
    public <T> T removeProperty(String key) {
        checkArgument(!propertyBased || !key.equals(IdGraph.ID), "Unable to remove value for reserved property %s", IdGraph.ID);

        return base.removeProperty(key);
    }

    @Override
    public Object getId() {
        return propertyBased ? base.getProperty(IdGraph.ID) : base.getId();
    }

    @Override
    public int hashCode() {
        return this.base.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return ElementHelper.areEqual(this, object);
    }
}
