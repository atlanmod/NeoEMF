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

package fr.inria.atlanmod.neoemf.data.blueprints.config;

import org.apache.commons.configuration.Configuration;

import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.tg.config.InternalBlueprintsTgConfiguration;

import java.io.File;

/**
 * A configuration class that sets graph-specific default configuration properties 
 * in the current NeoEMF {@link Configuration}.
 * <p>
 * Implementations of this interface are called dynamically by {@link BlueprintsPersistenceBackendFactory}
 * during database creation.
 * 
 * @see BlueprintsPersistenceBackendFactory
 * @see InternalBlueprintsTgConfiguration
 */
public interface InternalBlueprintsConfiguration {

    void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation);
}
