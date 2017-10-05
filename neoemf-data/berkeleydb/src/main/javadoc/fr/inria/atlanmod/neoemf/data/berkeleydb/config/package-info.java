/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides utility classes to define specific behaviors of BerkeleyDB data persistence.
 * <p>
 * This package provides implementations of the {@link fr.inria.atlanmod.neoemf.data.store.Store} interface that use a
 * BerkeleyDB database to store the result of modeling operations and retrieve model elements.
 * <p>
 * NeoEMF BerkeleyDB implementation does not provide additional <i>store decorators</i>. Generic decorators defined at
 * the core level (see {@link fr.inria.atlanmod.neoemf.data.store.AbstractStoreDecorator} subclasses) can be combined on
 * top of BerkeleyDB stores to provide additional behavior, such as autocommit or feature caching.
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.config;