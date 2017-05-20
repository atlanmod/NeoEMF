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

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.log.Log;

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

// EMF Compare related imports, uncomment to enable model comparison
//import java.util.List;
//import org.eclipse.emf.compare.Comparison;
//import org.eclipse.emf.compare.Diff;
//import org.eclipse.emf.compare.EMFCompare;
//import org.eclipse.emf.compare.match.IMatchEngine;
//import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
//import org.eclipse.emf.compare.scope.DefaultComparisonScope;
//import org.eclipse.emf.compare.scope.IComparisonScope;
//import fr.inria.atlanmod.neoemf.util.emf.compare.LazyMatchEngineFactory;

/**
 * Imports an existing model stored in a XMI files into a Blueprints-based {@link PersistentResource}.
 */
public class BlueprintsImporter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        BackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsBackendFactory.getInstance());

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());

        try (PersistentResource persistentResource = (PersistentResource) rSet.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")))) {
            Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder().weakCache().autoSave().asMap();
            persistentResource.save(options);

            Instant start = Instant.now();

            Resource xmiResource = rSet.createResource(URI.createURI("models/sample.xmi"));
            xmiResource.load(Collections.emptyMap());

            persistentResource.getContents().addAll(EcoreUtil.copyAll(xmiResource.getContents()));

            persistentResource.save(options);

            Instant end = Instant.now();
            Log.info("Graph Model created in {0} seconds", Duration.between(start, end).getSeconds());
            
            /*
             * Checks that NeoEMF model contains the same elements as the input XMI.
             * This operation can take some time for large models because both input
             * and output models have to be entirely traversed.
             * This step is presented for the demonstration purpose and can be ignored
             * in real-world applications: NeoEMF ensures that created models from input 
             * XMI files contains all the input elements.
             */
//            IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
//            matchEngineRegistry.add(new LazyMatchEngineFactory());
//            IComparisonScope scope = new DefaultComparisonScope(xmiResource, persistentResource, null);
//            Comparison comparison = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build().compare(scope);
//            
//            List<Diff> diffs = comparison.getDifferences();
//            if(diffs.size() > 0) {
//                Log.error("Created model has {0} diffs compared to the input XMI", diffs.size());
//                for(Diff diff : diffs) {
//                    Log.error("\t {0}", diff.toString());
//                }
//            }
//            else {
//                Log.info("Created model contains all the elements from the input XMI");
//            }
        }
    }
}
