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
 * Provides utility classes to provide modeling and persistence level options to NeoEMF.
 * <p>
 * NeoEMF can be customized by using specific options that are provided to the {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource#save(java.util.Map)} and {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource#load(java.util.Map)} methods. This package defines two APIs:
 * <ul> <li>{@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions}: defines the generic options available for
 * each implementation. This interface is implemented in backend-specific packages to provide backend-related
 * options.</li> <li>{@link fr.inria.atlanmod.neoemf.option.PersistenceOptions}: a fluent API allowing to easily
 * set NeoEMF options in client applications. Backend-specific builders are also provided to integrate backend-related
 * options.</li> </ul>
 * <p>
 * These classes are used to create <i>option maps</i> used in EMF save and load methods. For example, the following
 * code creates a map that tells the framework to commit the pending transaction after 10000 operations, and cache the
 * accessed features to retrieve them efficiently. <i>PersistenceOptions</i> corresponds to a
 * backend-specific implementation of {@link fr.inria.atlanmod.neoemf.option.PersistenceOptions}.
 * <pre>{@code
 * // Create the option map
 * Map<String, Object> options = PersistenceOptions.builder()
 *     .autocommit(10000)
 *     .cacheFeatures()
 *     .asMap();
 *
 * // Load the resource with the specified options
 * myResource.load(options);
 *
 * // Manipulate the resource with autocommit and feature cache enabled
 * myResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.option;