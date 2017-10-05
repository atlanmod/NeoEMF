/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides utility classes to create Blueprints specific URIs.
 * <p>
 * This package defines the {@link fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri} class, that extends
 * {@link fr.inria.atlanmod.neoemf.util.UriBuilder} to create Blueprints specific URIs. {@link
 * fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri}s are convenience wrappers of EMF {@link
 * org.eclipse.emf.common.util.URI}s that set a dedicated protocol that is parsed by NeoEMF to create the appropriate
 * database.
 * <p>
 * {@link fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri}s are created using the following code:
 * <pre>{@code
 * URI blueprintsURI = BlueprintsUri.createFileURI(new File("model_path"));
 *
 * // The created URI can be used as a regular EMF URI to create a NeoEMF resource
 * Resource neoEMFResource = resourceSet.createResource(blueprintsURI);
 *
 * // And accessed as a regular EMF resource
 * neoEMFResource.getContents() [...]
 * }</pre>
 */

package fr.inria.atlanmod.neoemf.data.blueprints.util;