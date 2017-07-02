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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.data.BackendConfiguration;

import java.nio.file.Path;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A configuration class that sets graph-specific default configuration properties in the current NeoEMF {@link
 * BackendConfiguration}.
 * <p>
 * Implementations of this interface are called dynamically by {@link BlueprintsBackendFactory} during
 * database creation.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
public interface BlueprintsConfiguration {

    /**
     * Adds specific properties about the Blueprints database in the given {@code config}.
     *
     * @param config    the {@link BackendConfiguration} that holds the resource properties
     * @param directory the path that contains the Blueprints database
     */
    void putDefaultConfiguration(BackendConfiguration config, Path directory);
}
