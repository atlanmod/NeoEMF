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
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

public class MapDBImporter {

	public static void main(String[] args) throws IOException {

		JavaPackage.eINSTANCE.eClass();
		
		PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME,
				MapDbPersistenceBackendFactory.getInstance());
		
		ResourceSet rSet = new ResourceSetImpl();

		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		rSet.getResourceFactoryRegistry()
				.getProtocolToFactoryMap()
				.put(MapDbURI.SCHEME,
						PersistentResourceFactory.getInstance());
		
		Resource mapResource = rSet.createResource(MapDbURI
				.createFileURI(new File("models/sample.mapdb")));

        Map<String, Object> options = MapDbOptionsBuilder.newBuilder().directWrite().autocommit()
                .cacheIsSet().cacheSizes().asMap();

		mapResource.save(options);
		
        long beginTimer = System.currentTimeMillis();
        
        Resource xmiResource = rSet.createResource(URI
                .createURI("models/sample.xmi"));
        xmiResource.load(Collections.emptyMap());
		mapResource.getContents().addAll(xmiResource.getContents());
		mapResource.save(Collections.emptyMap());
	    
		long endTimer = System.currentTimeMillis();
		NeoLogger.info("Map Model created in " + ((endTimer - beginTimer)/1000) + " seconds");
		
		((DefaultPersistentResource)mapResource).close();
		
	}
	
}
