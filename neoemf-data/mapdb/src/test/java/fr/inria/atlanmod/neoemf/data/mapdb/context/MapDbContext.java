/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the MapDB implementation.
 */
@ParametersAreNonnullByDefault
public abstract class MapDbContext extends AbstractContext {

    /**
     * Creates a new {@code MapDbContext} with a mapping with indices.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithIndices() {
        return new MapDbContext() {
            @Nonnull
            @Override
            public String name() {
                return super.name() + "-Indices";
            }

            @Nonnull
            @Override
            public ImmutableConfig config() {
                return MapDbConfig.newConfig().withIndices();
            }
        };
    }

    /**
     * Creates a new {@code MapDbContext} with a mapping with arrays.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithArrays() {
        return new MapDbContext() {
            @Nonnull
            @Override
            public String name() {
                return super.name() + "-Arrays";
            }

            @Nonnull
            @Override
            public ImmutableConfig config() {
                return MapDbConfig.newConfig().withArrays();
            }
        };
    }

    /**
     * Creates a new {@code MapDbContext} with a mapping with lists.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithLists() {
        return new MapDbContext() {
            @Nonnull
            @Override
            public String name() {
                return super.name() + "-Lists";
            }

            @Nonnull
            @Override
            public ImmutableConfig config() {
                return MapDbConfig.newConfig().withLists();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "MapDB";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return MapDbBackendFactory.getInstance();
    }
}
