/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.config;

import java.io.File;

import org.apache.commons.configuration.Configuration;

import fr.inria.atlanmod.neoemf.graph.blueprints.tg.config.AbstractBlueprintsConfig;

public class BlueprintsNeo4jConfig extends AbstractBlueprintsConfig {
    
    public static AbstractBlueprintsConfig eINSTANCE = new BlueprintsNeo4jConfig();
    
    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) {
        if(currentConfiguration.getString("blueprints.neo4j.directory") == null) {
            currentConfiguration.addProperty("blueprints.neo4j.directory", dbLocation.getAbsolutePath());
        }
    }

    @Override
    public void setGlobalSettings() {

    }    
}
