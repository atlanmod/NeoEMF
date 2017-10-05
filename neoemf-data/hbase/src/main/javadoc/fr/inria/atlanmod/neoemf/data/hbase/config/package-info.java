/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides utility classes to define specific behaviors of HBase data persistence.
 * <p>
 * This package defines the configuration by extending {@link fr.inria.atlanmod.neoemf.config.Config}: {@link
 * fr.inria.atlanmod.neoemf.data.hbase.config.HBaseConfig} is a fluent API allowing to easily set HBase specific
 * configuration in client applications. Since the configuration implements {@link
 * fr.inria.atlanmod.neoemf.config.Config} it can be used to combine HBase specific and generic configuration.
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to use a HBase backend in read-only mode, and a cache the accessed
 * features to retrieve them efficiently. The read-only nature is a HBase specific option, while the caching behavior is
 * defined at the core level.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = HBaseConfig.newConfig()
 *     .readOnly()
 *     .cacheFeatures()
 *     .toMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with a read-only HBase backend and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.hbase.config;