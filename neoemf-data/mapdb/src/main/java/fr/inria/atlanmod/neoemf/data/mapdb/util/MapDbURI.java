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

package fr.inria.atlanmod.neoemf.data.mapdb.util;

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link PersistenceURI} that creates MapDB specific resource {@link URI}s from a {@link File} descriptor or
 * an existing {@link URI}.
 * <p>
 * The class defines a MapDB specific {@link URI} scheme that is used to register {@link
 * fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory} in {@link BackendFactoryRegistry}
 * and configure the {@code protocol-to-factory} map of an existing {@link org.eclipse.emf.ecore.resource.ResourceSet}
 * with a {@link fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory}.
 *
 * @see BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@ParametersAreNonnullByDefault
public class MapDbURI extends PersistenceURI {

    /**
     * The scheme associated to the {@link URI}. This scheme is used to register {@link
     * fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory} and provide a {@link
     * fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory} to an existing {@link
     * org.eclipse.emf.ecore.resource.ResourceSet}.
     *
     * @see BackendFactoryRegistry
     * @see fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory
     * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
     */
    @Nonnull
    public static final String SCHEME = formatScheme(MapDbBackendFactory.getInstance());

    /**
     * Constructs a new {@code MapDbURI}.
     */
    private MapDbURI() {
        super(SCHEME);
    }

    /**
     * Creates a new {@code MapDbURI}.
     *
     * @return a new builder
     */
    @Nonnull
    public static PersistenceURI newBuilder() {
        return new MapDbURI();
    }

    @Nonnull
    @Override
    public URI fromServer(String host, int port, URI model) {
        throw new UnsupportedOperationException("MapDbURI does not support server-based URIs");
    }
}
