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
 * Provides utility classes to provide modeling and persistence level configuration to NeoEMF.
 * <p>
 * NeoEMF can be customized by using specific options that are provided to the {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource#save(java.util.Map)} and {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource#load(java.util.Map)} methods. This package defines the base for
 * the configuration of all {@link fr.inria.atlanmod.neoemf.data.Backend}: {@link fr.inria.atlanmod.neoemf.config.Config}
 * is a fluent API allowing to easily set NeoEMF configuration in client applications. Backend-specific builders are
 * also provided to integrate backend-related configuration.
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to commit the pending transaction after 10000 operations, and cache the
 * accessed features to retrieve them efficiently. <i>MyConfig</i> corresponds to a backend-specific implementation of
 * {@link fr.inria.atlanmod.neoemf.config.Config}.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = MyConfig.newConfig()
 *     .autocommit(10000)
 *     .cacheFeatures()
 *     .toMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with autocommit and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.config;