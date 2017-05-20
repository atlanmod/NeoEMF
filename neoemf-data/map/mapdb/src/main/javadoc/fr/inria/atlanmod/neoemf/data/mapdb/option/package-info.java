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
 * Provides utility classes to define specific behaviors of MapDB data persistence.
 * <p>
 * This package defines two APIs extending {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions} and {@link
 * fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder}: <ul> <li>{@link fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbStoreOptions}:
 * defines MapDB mapping options, such as explicit indice serialization vs. full collection serialization. HBase
 * specific options can be combined with generic options defined in {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions}.</li>
 * <li>{@link fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder}: a fluent API allowing to easily set MapDB
 * specific mapping options in client applications. Since the builder implements {@link
 * fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder} it can be used to combiner MapDB specific and generic
 * options.</li> </ul>
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to use a MapDB backend with an explicit representation of collection
 * indices, and a cache the accessed features to retrieve them efficiently. The explicit indice representation is a
 * MapDB specific option, while the caching behavior is defined at the core level.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = MapDBOptionsBuilder.newBuilder()
 *     .directWriteIndices()
 *     .cacheFeatures()
 *     .asMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with a MapDB backend representing explicitly collection indices and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.mapdb.option;