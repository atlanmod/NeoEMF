/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend}.
 */
@ParametersAreNonnullByDefault
public abstract class BlueprintsAdapter extends AbstractPersistentLocalAdapter {

    /**
     * An {@link BlueprintsAdapter} using TinkerGraph.
     */
    @AdapterName("tinker")
    public static class Tinker extends BlueprintsAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new BlueprintsTinkerConfig();
        }
    }

    /**
     * An {@link BlueprintsAdapter} using Neo4j.
     */
    @AdapterName("neo4j")
    public static class Neo4j extends BlueprintsAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new BlueprintsNeo4jConfig();
        }
    }
}
