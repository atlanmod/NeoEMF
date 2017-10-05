/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides utility classes to define specific behaviors of Blueprints data persistence.
 * <p>
 * Configuration defined using the classes inside this package are usable for each Blueprints implementation. For
 * backend-specific configuration refers to the corresponding package in the backend plugin.
 * <p>
 * This package defines the configuration by extending {@link fr.inria.atlanmod.neoemf.config.Config}: {@link
 * fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig} is a fluent API allowing to easily set
 * Blueprints specific configuration in client applications. Since the configuration implements {@link
 * fr.inria.atlanmod.neoemf.config.Config} it can be used to combine Blueprints specific and generic configuration.
 * <p>
 * Configuration defined using the classes inside this package are usable for each Blueprints implementation. For
 * backend-specific configuration refers to the corresponding package in the backend plugin ({@code
 * fr.inria.atlanmod.neoemf.data.blueprints.[backend]}).
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to use a direct write approach (meaning that modifications are directly
 * stored in the underlying database) and cache the accessed features to retrieve them efficiently. The direct write
 * behavior is a Blueprints specific option, while the caching behavior is defined at the core level.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = BlueprintsTinkerConfig.newConfig()
 *     .cacheFeatures()
 *     .toMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with a direct write approach and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.blueprints.config;