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

import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;

import fr.inria.atlanmod.neoemf.data.Configuration;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsConfiguration;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An internal class that sets Blueprints {@link Neo4jGraph} default configuration properties in the current NeoEMF
 * {@link Configuration}.
 * <p>
 * <b>Note:</b> This class is called dynamically by {@link BlueprintsBackendFactory} if Neo4j implementation is used to
 * store the underlying database.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public final class BlueprintsNeo4jConfiguration implements BlueprintsConfiguration {

    /**
     * The property to define the directory of the {@link Neo4jGraph} instance.
     */
    private static final String DIRECTORY = "blueprints.neo4j.directory";

    /**
     * Constructs a new {@code BlueprintsNeo4jConfiguration}.
     */
    private BlueprintsNeo4jConfiguration() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BlueprintsConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void putDefaultConfiguration(Configuration configuration, File directory) {
        if (!configuration.containsKey(DIRECTORY)) {
            configuration.setProperty(DIRECTORY, directory.getAbsolutePath());
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BlueprintsConfiguration INSTANCE = new BlueprintsNeo4jConfiguration();
    }
}
