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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.configuration;

import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.configuration.InternalBlueprintsConfiguration;

import org.apache.commons.configuration.Configuration;

import java.io.File;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

/**
 * An internal class that sets Blueprints Neo4j default configuration properties in the current NeoEMF
 * {@link Configuration}.
 *
 * @note This class is called dynamically by {@link BlueprintsPersistenceBackendFactory} if Neo4j implementation is used
 * to store the underlying database.
 * @see BlueprintsPersistenceBackendFactory
 */
@SuppressWarnings("unused") // Called dynamically
public final class InternalBlueprintsNeo4jConfiguration implements InternalBlueprintsConfiguration {

    /**
     * The property to define the directory of the {@link com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph} instance.
     */
    private static final String DIRECTORY = "blueprints.neo4j.directory";

    /**
     * Constructs a new {@code InternalBlueprintsNeo4jConfiguration}.
     */
    private InternalBlueprintsNeo4jConfiguration() {
    }

    /**
     * Returns the instance of this class.
     */
    @Nonnull
    public static InternalBlueprintsConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void putDefaultConfiguration(Configuration configuration, File directory) {
        if (isNull(configuration.getString(DIRECTORY))) {
            configuration.addProperty(DIRECTORY, directory.getAbsolutePath());
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        private static final InternalBlueprintsConfiguration INSTANCE = new InternalBlueprintsNeo4jConfiguration();
    }
}
