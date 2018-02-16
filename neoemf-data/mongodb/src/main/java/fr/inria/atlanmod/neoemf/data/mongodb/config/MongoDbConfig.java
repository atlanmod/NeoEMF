/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;

import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates MongoDb specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@FactoryBinding(factory = MongoDbBackendFactory.class)
@ParametersAreNonnullByDefault
public class MongoDbConfig extends BaseConfig<MongoDbConfig> {

    //TODO Parse host and port from configuration file (~/.neoemf/config/mongodb)
    private String host;
    private int port;

    /**
     * @return the MongoDb server host
     */
    public String getHost()
    {
        return host;
    }

    /**
     * @return the MongoDb server port
     */
    public int getPort()
    {
        return port;
    }

    /**
     * Constructs a new {@code MongoDbConfig}.
     */
    protected MongoDbConfig() {
        withDefault();

        this.host = "localhost";
        this.port = 27017;
    }

    /**
     * Constructs a new {@code MongoDbConfig} instance with default settings.
     *
     * @return a new configuration
     */
    @Nonnull
    public static MongoDbConfig newConfig() {
        return new MongoDbConfig();
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
     *
     * @return this configuration (for chaining)
     */
    protected MongoDbConfig withDefault() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.mongodb.DefaultMongoDbBackend", false);
    }

    // TODO Add mapping declarations
}
