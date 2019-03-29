/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;

/**
 * A query to execute on the content of a {@link Resource}.
 *
 * @param <V> the result type of method {@code call}
 */
@FunctionalInterface
public interface Query<V> {

    /**
     * Executes this query on the given {@code resource}.
     *
     * @param resource the resource where to execute this query
     *
     * @return the result of the query
     */
    @Nonnull
    V executeOn(Resource resource);
}
