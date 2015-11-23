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
package fr.inria.atlanmod.neoemf.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.EPackage;
import org.junit.After;
import org.junit.Before;

import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;
import fr.inria.atlanmod.neoemf.test.commons.BlueprintsResourceBuilder;
import fr.inria.atlanmod.neoemf.test.commons.MapResourceBuilder;

public class AllBackendTest {
    
    protected PersistentResource mapResource;
    protected PersistentResource neo4jResource;
    protected PersistentResource tinkerResource;
    
    protected MapResourceBuilder mapBuilder;
    protected BlueprintsResourceBuilder blueprintsBuilder;
    
    protected File mapFile;
    protected File neo4jFile;
    protected File tinkerFile;
    
    protected EPackage ePackage;
    
    @Before
    public void setUp() throws Exception {
        assert this.ePackage != null : "EPackage not set";
        String className = this.getClass().getSimpleName();
        mapFile     = new File("/tmp/"+className+"MapDB");
        neo4jFile   = new File("/tmp/"+className+"Neo4j");
        tinkerFile  = new File("/tmp/"+className+"Tinker");
        
        mapBuilder               = new MapResourceBuilder(ePackage);
        blueprintsBuilder = new BlueprintsResourceBuilder(ePackage);
        
    }
    
    public void createPersistentStores() throws IOException {
        mapResource     = mapBuilder.persistent().file(mapFile).build();
        neo4jResource   = blueprintsBuilder.neo4j().persistent().file(neo4jFile).build();
        tinkerResource  = blueprintsBuilder.tinkerGraph().persistent().file(tinkerFile).build();
    }
    
    public void createTransientStores() throws IOException {
        mapResource = mapBuilder.file(mapFile).build();
        neo4jResource = blueprintsBuilder.neo4j().file(neo4jFile).build();
        tinkerResource = blueprintsBuilder.file(tinkerFile).build();
    }

    @After
    public void tearDown() throws Exception {
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)mapResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)neo4jResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)tinkerResource);
        
        if(mapFile.exists()) {
            FileUtils.forceDelete(mapFile);
        }
        if(neo4jFile.exists()) {
            FileUtils.forceDelete(neo4jFile);
        }
        if(tinkerFile.exists()) {
            FileUtils.forceDelete(tinkerFile);
        }
    }

}
