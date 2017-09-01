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

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;

/**
 * Imports an existing model stored in a XMI files into a HBase-based {@link PersistentResource}.
 */
public class HBaseImporter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

        URI sourceUri = URI.createURI("models/sample.xmi");
        URI targetUri = HBaseUri.builder().fromServer("localhost", 2181, "sample.hbase");

        Map<String, Object> options = HBaseOptions.noOption();

        try (PersistentResource targetResource = (PersistentResource) resourceSet.createResource(targetUri)) {
            targetResource.save(options);

            Instant start = Instant.now();

            Resource sourceResource = resourceSet.createResource(sourceUri);
            sourceResource.load(Collections.emptyMap());

            targetResource.getContents().addAll(EcoreUtil.copyAll(sourceResource.getContents()));
            targetResource.save(options);

            Instant end = Instant.now();
            Log.info("Model created in {0} seconds", Duration.between(start, end).getSeconds());

//            Helpers.compare(sourceResource, targetResource);
        }
    }
}

