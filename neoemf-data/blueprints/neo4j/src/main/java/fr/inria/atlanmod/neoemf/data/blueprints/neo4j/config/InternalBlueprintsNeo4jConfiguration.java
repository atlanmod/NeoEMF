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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config;

import fr.inria.atlanmod.neoemf.data.blueprints.config.InternalBlueprintsConfiguration;

import org.apache.commons.configuration.Configuration;

import java.io.File;

import static java.util.Objects.isNull;

@SuppressWarnings("unused") // Called dynamically
public final class InternalBlueprintsNeo4jConfiguration implements InternalBlueprintsConfiguration {

    private static final String DIRECTORY = "blueprints.neo4j.directory";

    private InternalBlueprintsNeo4jConfiguration() {
    }

    /**
     * Returns the instance of this {@link InternalBlueprintsConfiguration}.
     */
    public static InternalBlueprintsConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) {
        if (isNull(currentConfiguration.getString(DIRECTORY))) {
            currentConfiguration.addProperty(DIRECTORY, dbLocation.getAbsolutePath());
        }
    }

    private static class Holder {

        private static final InternalBlueprintsConfiguration INSTANCE = new InternalBlueprintsNeo4jConfiguration();
    }
}
