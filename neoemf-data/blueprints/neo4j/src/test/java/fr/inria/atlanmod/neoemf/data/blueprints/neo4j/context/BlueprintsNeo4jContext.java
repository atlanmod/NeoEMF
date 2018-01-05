/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the Blueprints Neo4j implementation.
 */
@ParametersAreNonnullByDefault
public abstract class BlueprintsNeo4jContext extends BlueprintsContext {

    /**
     * Creates a new {@code BlueprintsContext} with a mapping with indices.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new Default();
    }

    @Nonnull
    @Override
    public String name() {
        return super.name() + "#Neo4j";
    }

    @ParametersAreNonnullByDefault
    private static class Default extends BlueprintsNeo4jContext {

        @Nonnull
        @Override
        public Config config() {
            return BlueprintsNeo4jConfig.newConfig();
        }
    }
}
