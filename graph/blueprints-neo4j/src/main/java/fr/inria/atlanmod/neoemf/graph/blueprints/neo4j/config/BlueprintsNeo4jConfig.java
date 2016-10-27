/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.config;

import fr.inria.atlanmod.neoemf.graph.blueprints.BlueprintsConfig;

import org.apache.commons.configuration.Configuration;

import java.io.File;

import static java.util.Objects.isNull;

public final class BlueprintsNeo4jConfig implements BlueprintsConfig {

    private static BlueprintsConfig INSTANCE;

    /**
     * Returns the instance of this {@link BlueprintsConfig configuration}.
     */
    public static BlueprintsConfig getInstance() {
        if (isNull(INSTANCE)) {
            INSTANCE = new BlueprintsNeo4jConfig();
        }
        return INSTANCE;
    }

    private static final String BLUEPRINTS_NEO4J_DIRECTORY = "blueprints.neo4j.directory";

    private BlueprintsNeo4jConfig() {
    }
    
    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) {
        if(isNull(currentConfiguration.getString(BLUEPRINTS_NEO4J_DIRECTORY))) {
            currentConfiguration.addProperty(BLUEPRINTS_NEO4J_DIRECTORY, dbLocation.getAbsolutePath());
        }
    }

    @Override
    public void setGlobalSettings() {

    }    
}
