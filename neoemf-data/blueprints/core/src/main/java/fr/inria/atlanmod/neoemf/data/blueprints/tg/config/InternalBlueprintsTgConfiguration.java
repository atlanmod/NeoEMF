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

package fr.inria.atlanmod.neoemf.data.blueprints.tg.config;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.config.InternalBlueprintsConfiguration;

import org.apache.commons.configuration.Configuration;

import java.io.File;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

/**
 * An internal class that sets Blueprints {@link TinkerGraph} default configuration properties in the current NeoEMF
 * {@link Configuration}.
 * <p>
 * This class is called dynamically by {@link BlueprintsPersistenceBackendFactory} if {@link TinkerGraph} implementation
 * is used to store the underlying database.
 *
 * @see BlueprintsPersistenceBackendFactory
 */
@SuppressWarnings("unused") // Called dynamically
public final class InternalBlueprintsTgConfiguration implements InternalBlueprintsConfiguration {

    private static final String DIRECTORY = "blueprints.tg.directory";
    private static final String FILE_TYPE = "blueprints.tg.file-type";

    private InternalBlueprintsTgConfiguration() {
    }

    /**
     * Returns the instance of this class.
     */
    @Nonnull
    public static InternalBlueprintsConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Add Blueprints database directory and file-type to the current resource {@link Configuration}
     *
     * @param currentConfiguration the {@link Configuration} holding resource properties
     * @param dbLocation           the {@link File} that contains the Blueprints database
     */
    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) {
        if (isNull(currentConfiguration.getString(DIRECTORY))) {
            currentConfiguration.addProperty(DIRECTORY, dbLocation.getAbsolutePath());
        }
        if (isNull(currentConfiguration.getString(FILE_TYPE))) {
            currentConfiguration.addProperty(FILE_TYPE, "GRAPHML");
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        private static final InternalBlueprintsConfiguration INSTANCE = new InternalBlueprintsTgConfiguration();
    }
}
