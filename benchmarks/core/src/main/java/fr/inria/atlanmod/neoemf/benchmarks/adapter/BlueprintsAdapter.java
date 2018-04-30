/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public abstract class BlueprintsAdapter extends AbstractNeoAdapter {

    /**
     * Constructs a new {@code BlueprintsAdapter}.
     *
     * @param storeExtension the extension of the resource, used for benchmarks
     */
    protected BlueprintsAdapter(String storeExtension) {
        super(storeExtension);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return new BlueprintsBackendFactory();
    }

    /**
     * An {@link BlueprintsAdapter} using TinkerGraph.
     */
    @ParametersAreNonnullByDefault
    public static class Tinker extends BlueprintsAdapter {

        /**
         * Constructs a new {@code BlueprintsAdapter.Tinker}.
         */
        public Tinker() {
            super("tinker");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return new BlueprintsTinkerConfig().toMap();
        }
    }

    /**
     * An {@link BlueprintsAdapter} using Neo4j.
     */
    @ParametersAreNonnullByDefault
    public static class Neo4j extends BlueprintsAdapter {

        /**
         * Constructs a new {@code BlueprintsAdapter.Neo4j}.
         */
        public Neo4j() {
            super("neo4j");
        }

        @Nonnull
        @Override
        public Map<String, ?> getOptions() {
            return new BlueprintsNeo4jConfig().toMap();
        }
    }
}
