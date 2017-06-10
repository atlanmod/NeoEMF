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

package fr.inria.atlanmod.neoemf.demo.importer;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;

public class BerkeleyDbImporter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        BackendFactoryRegistry.register(BerkeleyDbURI.SCHEME, BerkeleyDbBackendFactory.getInstance());

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BerkeleyDbURI.SCHEME, PersistentResourceFactory.getInstance());

        try (PersistentResource persistentResource = (PersistentResource) rSet.createResource(BerkeleyDbURI.newBuilder().fromFile(new File("models/sample.berkeleydb")))) {
            Map<String, Object> options = BerkeleyDbOptions.newBuilder()
                    .autoSave()
                    .cacheIsSet()
                    .cacheSizes()
                    .asMap();

            persistentResource.save(options);

            Instant start = Instant.now();

            Resource xmiResource = rSet.createResource(URI.createURI("models/sample.xmi"));
            xmiResource.load(Collections.emptyMap());

            persistentResource.getContents().addAll(EcoreUtil.copyAll(xmiResource.getContents()));
            persistentResource.save(options);

            Instant end = Instant.now();
            Log.info("MapDB Model created in {0} seconds", Duration.between(start, end).getSeconds());

//            Helpers.compare(xmiResource, persistentResource);
        }
    }
}
