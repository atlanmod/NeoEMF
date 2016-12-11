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

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

public class HBaseImporter {

	public static void main(String[] args) throws IOException {
		JavaPackage.eINSTANCE.eClass();
		
		PersistenceBackendFactoryRegistry.register(
				HBaseURI.SCHEME,
				HBasePersistenceBackendFactory.getInstance());
		
		ResourceSet rSet = new ResourceSetImpl();

		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		rSet.getResourceFactoryRegistry()
				.getProtocolToFactoryMap()
				.put(HBaseURI.SCHEME,
						PersistentResourceFactory.getInstance());
		
		Resource hbaseResource = rSet.createResource(HBaseURI
				.createURI("localhost", "2181", URI.createURI("sample.hbase")));

		hbaseResource.save(Collections.emptyMap());
		
	    long beginTimer = System.currentTimeMillis();
	    
		Resource xmiResource = rSet.createResource(URI
                .createURI("models/sample.xmi"));
        xmiResource.load(Collections.emptyMap());
		hbaseResource.getContents().addAll(xmiResource.getContents());
        hbaseResource.save(Collections.emptyMap());
        
		long endTimer = System.currentTimeMillis();
		System.out.println("HBase Model created in " + ((endTimer - beginTimer)/1000) + " seconds");
		
		((DefaultPersistentResource)hbaseResource).close();
	}
	
}

