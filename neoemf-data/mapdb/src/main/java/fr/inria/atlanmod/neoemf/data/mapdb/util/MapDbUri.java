/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb.util;

import fr.inria.atlanmod.neoemf.bind.annotation.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link UriBuilder} that creates MapDB specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@FactoryBinding(MapDbBackendFactory.class)
@ParametersAreNonnullByDefault
public class MapDbUri extends AbstractUriBuilder {

    /**
     * Constructs a new {@code MapDbUri}.
     */
    private MapDbUri() {
    }

    /**
     * Creates a new {@link UriBuilder} with the pre-configured scheme.
     *
     * @return a new builder
     */
    @Nonnull
    public static UriBuilder builder() {
        return new MapDbUri();
    }

    @Override
    protected boolean supportsFile() {
        return true;
    }

    @Override
    protected boolean supportsServer() {
        return false;
    }
}
