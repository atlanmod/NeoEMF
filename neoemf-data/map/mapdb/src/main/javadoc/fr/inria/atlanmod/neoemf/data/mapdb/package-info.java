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
 * Provides classes related to data management specific to a MapDB implementation.
 * <p>
 * This package provides {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackend} and {@link
 * fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory} classes, that correspond to HBase specific
 * implementations of {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend} and {@link
 * fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry}.
 * <p>
 * The {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory} has to be registered to enable {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource} creation with a MapDB storage:
 * <pre>{@code
 * PersistenceBackendFactoryRegistry.register(MapDBPersistenceBackendFactory.getInstance());
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.mapdb;