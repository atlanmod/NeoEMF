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
 * Provides utility classes to define specific behaviors of Blueprints data persistence.
 * <p>
 * This package defines two APIs extending {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions} and {@link fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder}:
 * <ul>
 * <li>{@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions}: defines generic Blueprints options (not tailored to a specific graph implementation), such as the automatic commit of pending transaction, and the default graph backend to use. Note that Blueprints specific options can be combined with generic options defined in {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions}</li>
 * <li>{@link fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder}: a fluent API allowing to easily set Blueprints specific options in client applications. Since the builder implements {@link fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder} it can be used to combine Blueprints specific and generic options.</li>
 * </ul>
 * 
 * @note Options defined using the classes inside this package are usable for each Blueprints implementation. For
 * backend-specific configuration refers to the corresponding package in the backend plugin (fr.inria.atlanmod.neoemf.data.blueprints.[backend]).
 * 
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following code creates a map that tells the framework to use a Neo4j backend and cache the accessed features to retrieve them efficiently. Note that defining the graph backend is a Blueprints specific option, while
 * the caching behavior is defined at the core level.
 * {@code
 * <pre>
 * // create the option map
 * Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
 *  .graph("com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph")
 *  .cacheFeatures()
 *  .asMap();
 * // load the resource with the specified options
 * myResource.load(options);
 * 
 * // manipulate the resource with a Neo4j backend and feature cache enabled
 * myResource.getContents() [...]
 * </pre>
 * }
 */

package fr.inria.atlanmod.neoemf.data.blueprints.option;