/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.demo.ocl;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.time.Stopwatch;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbUri;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Computes an OCL query using Eclipse MDT OCL on top of {@link PersistentResource}s storing models in Blueprints,
 * MapDB, and HBase.
 * <p>
 * This simple example shows how NeoEMF can be integrated with existing modeling tools.
 */
public class OCLProtectedMethods {

    public static void main(String[] args) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();

        JavaPackage.eINSTANCE.eClass();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(BlueprintsUri.builder().fromFile("models/sample.graphdb"))) {
            resource.load(BlueprintsNeo4jConfig.newConfig().toMap());
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            stopwatch.stop();
            Log.info("[ProtectedMethods - GraphDB] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
        }

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(MapDbUri.builder().fromFile("models/sample.mapdb"))) {
            resource.load(MapDbConfig.newConfig().withIndices().toMap());
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            stopwatch.stop();
            Log.info("[ProtectedMethods - MapDB] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
        }

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(BerkeleyDbUri.builder().fromFile("models/sample.berkeleydb"))) {
            resource.load(BerkeleyDbConfig.newConfig().withIndices().toMap());
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            stopwatch.stop();
            Log.info("[ProtectedMethods - BerkeleyDB] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
        }

//        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(HBaseUri.builder().fromServer("localhost", 2181, "sample.hbase"))) {
//            resource.load(HBaseConfig.newConfig().toMap());
//            Stopwatch stopwatch = Stopwatch.createStarted();
//            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
//            stopwatch.stop();
//            Log.info("[ProtectedMethods - HBase] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
//        }
    }

    @SuppressWarnings("unchecked")
    private static List<MethodDeclaration> getProtectedMethodDeclarations(Resource resource) {
        try {
            OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
            OCLInput oclInput = new OCLInput(new FileInputStream(new File("ocl/protectedMethods.ocl")));
            List<Constraint> constraints = ocl.parse(oclInput);
            return (List<MethodDeclaration>) ocl.createQuery(constraints.get(0)).evaluate(resource.getContents().get(0));
        }
        catch (Exception e) {
            Log.error(e);
            return Collections.emptyList();
        }
    }
}
