/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates MongoDb specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@Component(service = Config.class, scope = ServiceScope.PROTOTYPE)
@FactoryBinding(factory = MongoDbBackendFactory.class)
@ParametersAreNonnullByDefault
public class MongoDbConfig extends BaseConfig<MongoDbConfig> {

    /**
     * Constructs a new {@code MongoDbConfig}.
     */
    public MongoDbConfig() {
        withDefault();
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
     *
     * @return this configuration (for chaining)
     */
    protected MongoDbConfig withDefault() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.mongodb.DefaultMongoDbBackend", false);
    }
}
