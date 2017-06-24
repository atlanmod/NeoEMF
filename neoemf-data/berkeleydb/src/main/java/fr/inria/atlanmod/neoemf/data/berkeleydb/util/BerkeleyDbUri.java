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

package fr.inria.atlanmod.neoemf.data.berkeleydb.util;

import fr.inria.atlanmod.neoemf.data.BackendFactoryBinding;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link UriBuilder} that creates BerkeleyDB specific resource {@link URI}s.
 *
 * @see BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@ParametersAreNonnullByDefault
@BackendFactoryBinding(BerkeleyDbBackendFactory.class)
public class BerkeleyDbUri extends AbstractUriBuilder {

    /**
     * The scheme associated to the URI.
     */
    @Nonnull
    public static final String SCHEME = formatScheme(BerkeleyDbBackendFactory.getInstance());

    /**
     * Constructs a new {@code BerkeleyDbUri}.
     */
    private BerkeleyDbUri() {
        super(SCHEME);
    }

    /**
     * Creates a new {@code BerkeleyDbUri} with the pre-configured scheme.
     *
     * @return a new builder
     */
    @Nonnull
    public static UriBuilder builder() {
        return new BerkeleyDbUri();
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
