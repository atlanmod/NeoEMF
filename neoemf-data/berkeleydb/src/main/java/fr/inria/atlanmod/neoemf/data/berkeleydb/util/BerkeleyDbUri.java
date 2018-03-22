/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriBuilder} that creates BerkeleyDB specific resource URIs.
 *
 * @see BerkeleyDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@FactoryBinding(factory = BerkeleyDbBackendFactory.class)
@ParametersAreNonnullByDefault
public class BerkeleyDbUri extends AbstractUriBuilder {

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static UriBuilder builder() {
        return new BerkeleyDbUri();
    }

    @Override
    public boolean supportsFile() {
        return true;
    }

    @Override
    public boolean supportsServer() {
        return false;
    }
}
