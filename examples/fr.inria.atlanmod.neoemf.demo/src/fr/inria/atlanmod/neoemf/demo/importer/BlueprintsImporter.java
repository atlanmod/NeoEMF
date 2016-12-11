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
package fr.inria.atlanmod.neoemf.demo.importer;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

public class BlueprintsImporter {

	public static void main(String[] args) throws IOException {

		JavaPackage.eINSTANCE.eClass();
		
		PersistenceBackendFactoryRegistry.register(
				BlueprintsURI.SCHEME,
				BlueprintsPersistenceBackendFactory.getInstance());
		
		ResourceSet rSet = new ResourceSetImpl();

		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		rSet.getResourceFactoryRegistry()
				.getProtocolToFactoryMap()
				.put(BlueprintsURI.SCHEME,
						PersistentResourceFactory.getInstance());
		
		long beginTimer = System.currentTimeMillis();
		
		Resource graphResource = rSet.createResource(BlueprintsURI
				.createFileURI(new File("models/sample.graphdb")));

        Map<String, Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder().weakCache()
                .autocommit().asMap();
		
		graphResource.save(options);
	    
		Resource xmiResource = rSet.createResource(URI
                .createURI("models/sample.xmi"));
		
        xmiResource.load(Collections.emptyMap());
        
		graphResource.getContents().addAll(xmiResource.getContents());
        graphResource.save(options);
        
		long endTimer = System.currentTimeMillis();
		NeoLogger.info("Graph Model created in " + ((endTimer - beginTimer)/1000) + " seconds");
		
		((DefaultPersistentResource)graphResource).close();
		
	}
	
}
