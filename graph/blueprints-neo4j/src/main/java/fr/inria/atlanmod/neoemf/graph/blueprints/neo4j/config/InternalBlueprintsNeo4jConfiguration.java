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

import fr.inria.atlanmod.neoemf.graph.blueprints.config.InternalBlueprintsConfiguration;

import org.apache.commons.configuration.Configuration;

import java.io.File;

import static java.util.Objects.isNull;

public final class InternalBlueprintsNeo4jConfiguration implements InternalBlueprintsConfiguration {

    private static final String DIRECTORY = "blueprints.neo4j.directory";

    private static InternalBlueprintsConfiguration INSTANCE;

    private InternalBlueprintsNeo4jConfiguration() {
    }

    /**
     * Returns the instance of this {@link InternalBlueprintsConfiguration}.
     */
    public static InternalBlueprintsConfiguration getInstance() {
        if (isNull(INSTANCE)) {
            INSTANCE = new InternalBlueprintsNeo4jConfiguration();
        }
        return INSTANCE;
    }

    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) {
        if (isNull(currentConfiguration.getString(DIRECTORY))) {
            currentConfiguration.addProperty(DIRECTORY, dbLocation.getAbsolutePath());
        }
    }

    @Override
    public void setGlobalSettings() {

    }
}
