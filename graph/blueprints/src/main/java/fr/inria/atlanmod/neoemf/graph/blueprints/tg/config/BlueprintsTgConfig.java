/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.tg.config;

import org.apache.commons.configuration.Configuration;

import java.io.File;

public class BlueprintsTgConfig extends AbstractBlueprintsConfig {

    private static final String BLUEPRINTS_TG_DIRECTORY = "blueprints.tg.directory";
    private static final String BLUEPRINTS_TG_FILE_TYPE = "blueprints.tg.file-type";

    public static AbstractBlueprintsConfig eINSTANCE = new BlueprintsTgConfig();
    
    @Override
    public void putDefaultConfiguration(Configuration currentConfiguration, File dbLocation) throws IllegalArgumentException {
        if(currentConfiguration.getString(BLUEPRINTS_TG_DIRECTORY) == null) {
            currentConfiguration.addProperty(BLUEPRINTS_TG_DIRECTORY, dbLocation.getAbsolutePath());
        }
        if(currentConfiguration.getString(BLUEPRINTS_TG_FILE_TYPE) == null) {
            currentConfiguration.addProperty(BLUEPRINTS_TG_FILE_TYPE, "GRAPHML");
        }
    }
    
    @Override
    public void setGlobalSettings() {
        
    }
    
}
