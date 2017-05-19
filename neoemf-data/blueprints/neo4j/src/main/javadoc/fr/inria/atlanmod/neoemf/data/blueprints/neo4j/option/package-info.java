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

/**
 * Provides utility classes to define specific behaviors of Neo4j data persistence.
 * <p>
 * This package defines two APIs extending {@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions} and {@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder}:
 * <ul>
 * <li>{@link fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions}: defines Neo4j-specific options, such as the database cache strategy (weak, soft, strong), the size of the internal buffers, or the usage of memory mapped files. Note that Neo4j specific options can be combined with Blueprints and generic options defined in {@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions} and {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions}</li>
 * <li>{@link fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder}: a fluent API allowing to easily set Neo4j specific options in client applications. Since the builder extends {@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder} it can be used to combine Neo4j, Blueprints, and generic options.</li>
 * </ul>
 * 
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following code creates a map that tells the framework to use a Neo4j backend with a database <i>soft</i> cache strategy and a generic cache storing accessed features to retrieve them efficiently. Note that the database cache pôlicy is a Neo4j specific option, while
 * the feature caching behavior is defined at the core level. In addition, using {@link fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder} to create the option map automatically sets the graph backend type to "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph".
 * {@code
 * <pre>
 * // create the option map
 * Map<String, Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder()
 *  .softCache()
 *  .cacheFeatures()
 *  .asMap();
 * // load the resource with the specified options
 * myResource.load(options);
 * 
 * // manipulate the resource with a Neo4j backend using an internal soft cache and a feature cache
 * myResource.getContents() [...]
 * </pre>
 * }
 */

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option;