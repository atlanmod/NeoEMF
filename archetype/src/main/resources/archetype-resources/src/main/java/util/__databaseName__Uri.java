/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package}.util;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.util.AbstractUriBuilder;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import ${package}.${databaseName}BackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link UriBuilder} that creates ${databaseName} specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see ${databaseName}BackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@FactoryBinding(${databaseName}BackendFactory.class)
@ParametersAreNonnullByDefault
public class ${databaseName}Uri extends AbstractUriBuilder {

    /**
     * Constructs a new {@code ${databaseName}Uri}.
     */
    private ${databaseName}Uri() {
    }

    /**
     * Creates a new {@link UriBuilder} with the pre-configured scheme.
     *
     * @return a new builder
     */
    @Nonnull
    public static UriBuilder builder() {
        return new ${databaseName}Uri();
    }

    @Override
    protected boolean supportsFile() {
        // TODO Implement this method
        throw Throwables.notImplementedYet("supportsFile");
    }

    @Override
    protected boolean supportsServer() {
        // TODO Implement this method
        throw Throwables.notImplementedYet("supportsServer");
    }
}
