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
 * Provides utility classes to create MapDB specific URIs.
 * <p>
 * This package defines the {@link fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI} class, that extends {@link
 * fr.inria.atlanmod.neoemf.util.PersistenceURI} to create MapDB specific URIs. {@link
 * fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI}s are convenience wrappers of EMF {@link
 * org.eclipse.emf.common.util.URI}s that set a dedicated protocol that is parsed by NeoEMF to create the appropriate
 * database.
 * <p>
 * {@link fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI}s are created using the following code:
 * <pre>{@code
 * URI mapDBURI = MapDBURI.createFileURI(new File("model_path"));
 *
 * // The created URI can be used as a regular EMF URI to create a NeoEMF resource
 * Resource neoEMFResource = resourceSet.createResource(hbaseURI);
 *
 * // And accessed as a regular EMF resource
 * neoEMFResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.mapdb.util;