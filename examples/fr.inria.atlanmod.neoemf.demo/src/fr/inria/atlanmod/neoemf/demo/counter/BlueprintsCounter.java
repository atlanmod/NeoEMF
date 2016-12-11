/*
 * Copyright (c) 2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */
package fr.inria.atlanmod.neoemf.demo.counter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

public class BlueprintsCounter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();
        
        PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        
        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        
        long begin = System.currentTimeMillis();
        Resource graphResource = rSet.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")));
        
        graphResource.load(Collections.emptyMap());
        int rSize = ReaderUtil.countElements(graphResource);
        
        NeoLogger.info("Resource " + graphResource.toString() + " contains " + rSize + " elements");
        long end = System.currentTimeMillis();
        NeoLogger.info("Query computed in " + ((end - begin)) + " ms");
        
        ((DefaultPersistentResource)graphResource).close();
    }
}
