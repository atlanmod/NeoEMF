/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link AbstractBasedObject} owned by an {@link IdGraph}.
 *
 * @param <G>
 */
@ParametersAreNonnullByDefault
public abstract class AbstractGraphBasedObject<T, G extends IdGraph<G>> extends AbstractBasedObject<T> {

    /**
     * The graph that owns this element.
     */
    @Nonnull
    protected final G graph;

    /**
     * Constructs a new {@code AbstractGraphBasedObject}.
     *
     * @param base  the base element
     * @param graph the graph that owns this element
     */
    public AbstractGraphBasedObject(T base, G graph) {
        super(base);
        this.graph = graph;
    }
}
