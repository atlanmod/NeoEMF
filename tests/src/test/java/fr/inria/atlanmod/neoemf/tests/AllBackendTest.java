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
        
        MapResourceBuilder mapBuilder               = new MapResourceBuilder(ePackage);
        BlueprintsResourceBuilder blueprintsBuilder = new BlueprintsResourceBuilder(ePackage);
        
        mapResource     = mapBuilder.persistent().file(mapFile).build();
        neo4jResource   = blueprintsBuilder.neo4j().persistent().file(neo4jFile).build();
        tinkerResource  = blueprintsBuilder.tinkerGraph().persistent().file(tinkerFile).build();
        
    }

    @After
    public void tearDown() throws Exception {
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)mapResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)neo4jResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)tinkerResource);
        
        FileUtils.forceDelete(mapFile);
        FileUtils.forceDelete(neo4jFile);
        FileUtils.forceDelete(tinkerFile);
    }

}
