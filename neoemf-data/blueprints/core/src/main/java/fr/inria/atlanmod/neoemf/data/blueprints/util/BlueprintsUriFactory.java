/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriFactory} that creates Blueprints specific resource URIs.
 *
 * @see BlueprintsBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@FactoryBinding(factory = BlueprintsBackendFactory.class)
@ParametersAreNonnullByDefault
public class BlueprintsUriFactory extends AbstractUriFactory {

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static UriFactory builder() {
        return new BlueprintsUriFactory();
    }

    @Override
    public boolean supportsLocalUris() {
        return true;
    }

    @Override
    public boolean supportsRemoteUris() {
        return false;
    }
}
