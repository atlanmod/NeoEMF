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

import fr.inria.atlanmod.neoemf.data.PersistenceConfiguration;
import fr.inria.atlanmod.neoemf.data.blueprints.tg.BlueprintsTgConfiguration;

import java.io.File;

/**
 * A configuration class that sets graph-specific default configuration properties in the current NeoEMF {@link
 * PersistenceConfiguration}.
 * <p>
 * <b>Note:</b> Implementations of this interface are called dynamically by {@link BlueprintsBackendFactory} during
 * database creation.
 *
 * @see BlueprintsBackendFactory
 * @see BlueprintsTgConfiguration
 */
public interface BlueprintsConfiguration {

    /**
     * Adds specific properties about the Blueprints database in the given {@code configuration}.
     *
     * @param configuration the {@link PersistenceConfiguration} that holds the resource properties
     * @param directory     the {@link File} that contains the Blueprints database
     */
    void putDefaultConfiguration(PersistenceConfiguration configuration, File directory);
}
