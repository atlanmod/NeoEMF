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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;

import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsConfig;

import java.nio.file.Path;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An internal class that sets Blueprints {@code Neo4jGraph} default configuration properties in the current NeoEMF
 * {@link BackendConfig}.
 * <p>
 * <b>Note:</b> This class is called dynamically by {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory}
 * if Neo4j implementation is used to store the underlying database.
 *
 * @see fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public final class BlueprintsNeo4jConfig implements BlueprintsConfig {

    /**
     * The property to define the directory of the {@code Neo4jGraph} instance.
     */
    private static final String DIRECTORY = "blueprints.neo4j.directory";

    @Override
    public void putDefault(BackendConfig config, Path directory) {
        config.set(DIRECTORY, directory.toString());
    }
}
