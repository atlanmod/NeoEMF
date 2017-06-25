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

import fr.inria.atlanmod.neoemf.binding.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link UriBuilder} that creates MapDB specific resource {@link URI}s.
 *
 * @see BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory
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
     * Creates a new {@code MapDbUri} with the pre-configured scheme.
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
