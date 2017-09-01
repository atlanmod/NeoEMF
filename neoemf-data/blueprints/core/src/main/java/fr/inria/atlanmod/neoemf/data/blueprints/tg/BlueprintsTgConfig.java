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

package fr.inria.atlanmod.neoemf.data.blueprints.tg;

import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsConfig;

import java.nio.file.Path;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An internal class that defines Blueprints {@code TinkerGraph} default configuration properties in the current NeoEMF
 * {@link BackendConfig}.
 * <p>
 * <b>Note:</b> This class is called dynamically by {@link BlueprintsBackendFactory} if {@code TinkerGraph}
 * implementation is used to store the underlying database.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public final class BlueprintsTgConfig implements BlueprintsConfig {

    /**
     * The property to define the directory in which to store the {@code TinkerGraph}.
     */
    private static final String DIRECTORY = "blueprints.tg.directory";

    /**
     * The property to define the storage type for the data.
     */
    private static final String FILE_TYPE = "blueprints.tg.file-type";

    @Override
    public void putDefault(BackendConfig config, Path directory) {
        config.set(DIRECTORY, directory.toString());
        config.setIfAbsent(FILE_TYPE, "GRAPHML");
    }
}
