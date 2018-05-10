/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractRemoteContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the MongoDb implementation.
 */
@ParametersAreNonnullByDefault
public abstract class MongoDbContext extends AbstractRemoteContext {

    /**
     * Creates a new {@code MongoDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new MongoDbContext() {
            @Nonnull
            @Override
            public ImmutableConfig config() {
                return MongoDbConfig.newConfig();
            }
        };
    }

    @Override
    public boolean isInitialized() {
        return MongoDbCluster.isInitialized();
    }

    @Override
    public Context init() {
        MongoDbCluster.init();
        return this;
    }

    @Nonnull
    @Override
    public String name() {
        return "MongoDb";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return MongoDbBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    protected String getHost() {
        return MongoDbCluster.host();
    }

    @Override
    protected int getPort() {
        return MongoDbCluster.port();
    }
}
