/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriFactory} that creates HBase specific resource URIs.
 *
 * @see HBaseBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@FactoryBinding(factory = HBaseBackendFactory.class)
@ParametersAreNonnullByDefault
public class HBaseUriFactory extends AbstractUriFactory {

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static UriFactory builder() {
        return new HBaseUriFactory();
    }

    @Override
    public boolean supportsLocalUris() {
        return false;
    }

    @Override
    public boolean supportsRemoteUris() {
        return true;
    }
}
