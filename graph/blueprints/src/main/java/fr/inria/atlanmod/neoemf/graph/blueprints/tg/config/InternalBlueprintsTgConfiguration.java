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

package fr.inria.atlanmod.neoemf.graph.blueprints.tg.config;

import fr.inria.atlanmod.neoemf.graph.blueprints.config.InternalBlueprintsConfiguration;

import org.apache.commons.configuration.Configuration;

import java.io.File;

import static java.util.Objects.isNull;

public final class InternalBlueprintsTgConfiguration implements InternalBlueprintsConfiguration {

    private static final String DIRECTORY = "blueprints.tg.directory";
    private static final String FILE_TYPE = "blueprints.tg.file-type";

    private InternalBlueprintsTgConfiguration() {
    }

    /**
     * Returns the instance of this {@link InternalBlueprintsConfiguration}.
     */
    public static InternalBlueprintsConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) throws IllegalArgumentException {
        if (isNull(currentConfiguration.getString(DIRECTORY))) {
            currentConfiguration.addProperty(DIRECTORY, dbLocation.getAbsolutePath());
        }
        if (isNull(currentConfiguration.getString(FILE_TYPE))) {
            currentConfiguration.addProperty(FILE_TYPE, "GRAPHML");
        }
    }

    @Override
    public void setGlobalSettings() {

    }

    private static class Holder {

        private static final InternalBlueprintsConfiguration INSTANCE = new InternalBlueprintsTgConfiguration();
    }
}
