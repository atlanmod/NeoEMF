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

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend}.
 */
@ParametersAreNonnullByDefault
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
        return BlueprintsBackendFactory.getInstance();
    }

    /**
     * An {@link BlueprintsAdapter} using TinkerGraph.
     */
    @ParametersAreNonnullByDefault
    public static class Tinker extends BlueprintsAdapter {

        /**
         * Constructs a new {@code BlueprintsAdapter.Tinker}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public Tinker() {
            super("tinker");
        }

        @Nonnull
        @Override
        public Map<String, Object> getOptions() {
            return BlueprintsOptions.builder()
                    .asMap();
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
        @SuppressWarnings("unused") // Called dynamically
        public Neo4j() {
            super("neo4j");
        }

        @Nonnull
        @Override
        public Map<String, Object> getOptions() {
            return BlueprintsNeo4jOptions.builder()
                    .asMap();
        }
    }
}
