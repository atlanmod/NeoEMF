/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package};

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import ${package}.config.${databaseName}Config;

import java.net.URL;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BackendFactory} that creates {@link ${databaseName}Backend} instances.
 */
@ParametersAreNonnullByDefault
public class ${databaseName}BackendFactory extends AbstractBackendFactory<${databaseName}Config> {

    /**
     * Constructs a new {@code ${databaseName}BackendFactory}.
     */
    public ${databaseName}BackendFactory() {
        super("${databaseName.toLowerCase()}");
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, ${databaseName}Config config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }

    @Nonnull
    @Override
    protected Backend createRemoteBackend(URL url, ${databaseName}Config config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }
}
