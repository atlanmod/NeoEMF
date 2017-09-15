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

import fr.inria.atlanmod.neoemf.bind.annotation.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link UriBuilder} that creates Blueprints specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see BlueprintsBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@FactoryBinding(BlueprintsBackendFactory.class)
@ParametersAreNonnullByDefault
public class BlueprintsUri extends AbstractUriBuilder {

    /**
     * Constructs a new {@code BlueprintsUri}.
     */
    private BlueprintsUri() {
    }

    /**
     * Creates a new {@link UriBuilder} with the pre-configured scheme.
     *
     * @return a new builder
     */
    @Nonnull
    public static UriBuilder builder() {
        return new BlueprintsUri();
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
