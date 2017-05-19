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
 * Provides utility classes to define specific behaviors of HBase data persistence.
 * <p>
 * This package defines two APIs extending {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions} and {@link
 * fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder}: <ul> <li>{@link fr.inria.atlanmod.neoemf.data.hbase.option.HBaseResourceOptions}:
 * defines HBase specific options, such as the access policy of the HBase store (read-write / read-only). HBase specific
 * options can be combined with generic options defined in {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions}.</li>
 * <li>{@link fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptionsBuilder}: a fluent API allowing to easily set HBase
 * specific options in client applications. Since the builder implements {@link fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder}
 * it can be used to combine HBase specific and generic options.</li> </ul>
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to use a HBase backend in read-only mode, and a cache the accessed
 * features to retrieve them efficiently. The read-only nature is a HBase specific option, while the caching behavior is
 * defined at the core level.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = HBaseOptionsBuilder.newBuilder()
 *     .readOnly()
 *     .cacheFeatures()
 *     .asMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with a read-only HBase backend and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.hbase.option;