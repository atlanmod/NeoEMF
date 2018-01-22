/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package}.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;

import ${package}.${databaseName}BackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates ${databaseName} specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@FactoryBinding(${databaseName}BackendFactory.class)
@ParametersAreNonnullByDefault
public class ${databaseName}Config extends BaseConfig<${databaseName}Config> {

    /**
     * Constructs a new {@code ${databaseName}Config}.
     */
    protected ${databaseName}Config() {
        withDefault();

        // TODO Declare all default values
    }

    /**
     * Constructs a new {@code ${databaseName}Config} instance with default settings.
     *
     * @return a new configuration
     */
    @Nonnull
    public static ${databaseName}Config newConfig() {
        return new ${databaseName}Config();
    }

    /**
     * Defines the mapping to use for the created {@link ${package}.${databaseName}Backend}.
     *
     * @return this configuration (for chaining)
     */
    protected ${databaseName}Config withDefault() {
        return setMappingWithCheck("${package}.Default${databaseName}Backend", false);
    }

    // TODO Add mapping declarations

    // TODO Add methods specific to your database
}
