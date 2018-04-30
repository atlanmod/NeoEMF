/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides utility classes related to HBase.
 * <p>
 * This package defines the {@link fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUri} class, that extends {@link
 * fr.inria.atlanmod.neoemf.util.UriBuilder} to create HBase specific URIs. {@link
 * fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUri}s are convenience wrappers of EMF URIs that set a dedicated
 * protocol that is parsed by NeoEMF to create the appropriate database.
 * <p>
 * {@link fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUri}s are created using the following code. The {@link
 * fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUri} allows to easily set the address and port of the remote HBase
 * server that contains a model.
 * <pre>{@code
 * // Create a HBase URI referencing the HBase server running on localhost:1234 and containing the resource myModel
 * URI uri = HBaseUriFactory.createRemoteUri("localhost", 1234, "myModel");
 *
 * // The created URI can be used as a regular EMF URI to create a NeoEMF resource
 * Resource resource = resourceSet.createResource(uri);
 *
 * // And accessed as a regular EMF resource
 * resource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.hbase.util;