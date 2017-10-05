/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides utility classes to define specific behaviors of MapDB data persistence.
 * <p>
 * This package defines the configuration by extending {@link fr.inria.atlanmod.neoemf.config.Config}: {@link
 * fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig} is a fluent API allowing to easily set MapDB specific mapping
 * configuration in client applications. Since the configuration implements {@link
 * fr.inria.atlanmod.neoemf.config.Config} it can be used to combiner MapDB specific and generic configuration.
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to use a MapDB backend with an explicit representation of collection
 * indices, and a cache the accessed features to retrieve them efficiently. The explicit indice representation is a
 * MapDB specific option, while the caching behavior is defined at the core level.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = MapDBOptions.newConfig()
 *     .withIndices()
 *     .cacheFeatures()
 *     .toMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with a MapDB backend representing explicitly collection indices and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.mapdb.config;