/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

/**
 * Provides utility classes to define specific behaviors of Neo4j data persistence.
 * <p>
 * Configuration defined using the classes inside this package are only usable with Neo4j as the concrete implementation
 * of the Blueprints API. Neo4j specific configuration can be combined with Blueprints generic configuration.
 * <p>
 * This package defines the configuration by extending {@link fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig}:
 * {@link fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig} is a fluent API allowing to
 * easily set Neo4j specific configuration in client applications. Since the configuration extends {@link
 * fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig} it can be used to combine Neo4j, Blueprints,
 * and generic configuration.
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to use a Neo4j backend with a database <i>soft</i> cache strategy and a
 * generic cache storing accessed features to retrieve them efficiently. The database cache policy is a Neo4j specific
 * option, while the feature caching behavior is defined at the core level. In addition, using {@link
 * fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig} to create the option map automatically
 * sets the graph backend type to "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph".
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = BlueprintsNeo4jConfig.newConfig()
 *     .softCache()
 *     .cacheFeatures()
 *     .toMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with a Neo4j backend using an internal soft cache and a feature cache
 * myResource.getContents() [...]
 * }</pre>
 *
 * @see fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig
 */

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config;