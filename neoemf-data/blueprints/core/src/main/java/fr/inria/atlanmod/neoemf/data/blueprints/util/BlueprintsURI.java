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

package fr.inria.atlanmod.neoemf.data.blueprints.util;

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.util.URIBuilder;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link URIBuilder} that creates Blueprints specific resource {@link URI}s.
 * <p>
 * The class defines a BerkeleyDB specific {@link URI} scheme that is used to register {@link
 * fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory} in {@link BackendFactoryRegistry} and configure
 * the {@code protocol to factory} map of an existing {@link org.eclipse.emf.ecore.resource.ResourceSet} with a {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory}.
 *
 * @see BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@ParametersAreNonnullByDefault
public class BlueprintsURI extends URIBuilder {

    /**
     * The scheme associated to the URI. This scheme is used to register {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory}
     * and provide a {@link fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory} to an existing {@link
     * org.eclipse.emf.ecore.resource.ResourceSet}.
     *
     * @see BackendFactoryRegistry
     * @see fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory
     * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
     */
    @Nonnull
    public static final String SCHEME = formatScheme(BlueprintsBackendFactory.getInstance());

    /**
     * Constructs a new {@code BlueprintsURI}.
     */
    private BlueprintsURI() {
        super();
    }

    /**
     * Creates a new {@code BlueprintsURI} with the pre-configured scheme.
     *
     * @return a new builder
     */
    @Nonnull
    public static URIBuilder newBuilder() {
        return new BlueprintsURI().withScheme(SCHEME);
    }

    @Nonnull
    @Override
    public URI fromServer(String host, int port, URI model) {
        throw new UnsupportedOperationException("BlueprintsURI does not support server-based URIs");
    }
}
