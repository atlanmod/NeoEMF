/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.demo.query;

import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbUriFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUriFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUriFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.time.Stopwatch;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.VisibilityKind;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Computes a query using the EMF generated code on top of existing {@link PersistentResource}s storing models in
 * Blueprints, MapDB, and HBase.
 */
public class EMFProtectedMethod {

    public static void main(String[] args) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(new BlueprintsUriFactory().createLocalUri("databases/sample.graphdb"))) {
            resource.load(new BlueprintsNeo4jConfig().toMap());
            Stopwatch stopwatch = Stopwatch.createStarted();
            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            stopwatch.stop();
            Log.info("[ProtectedMethods - GraphDB] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
        }

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(new MapDbUriFactory().createLocalUri("databases/sample.mapdb"))) {
            resource.load(new MapDbConfig().withIndices().toMap());
            Stopwatch stopwatch = Stopwatch.createStarted();
            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            stopwatch.stop();
            Log.info("[ProtectedMethods - MapDB] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
        }

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(new BerkeleyDbUriFactory().createLocalUri("databases/sample.berkeleydb"))) {
            resource.load(new BerkeleyDbConfig().withIndices().toMap());
            Stopwatch stopwatch = Stopwatch.createStarted();
            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            stopwatch.stop();
            Log.info("[ProtectedMethods - BerkeleyB] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
        }

//        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(new HBaseUriFactory().createRemoteUri("localhost", 2181, "sample.hbase"))) {
//            resource.load(new HBaseConfig().toMap());
//            Stopwatch stopwatch = Stopwatch.createStarted();
//            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
//            stopwatch.stop();
//            Log.info("[ProtectedMethods - HBase] Done, found {0} elements in {1} seconds", result.size(), stopwatch.elapsed().getSeconds());
//        }
    }

    private static EList<MethodDeclaration> getProtectedMethodDeclarations(Resource resource) {
        EList<MethodDeclaration> methodDeclarations = new BasicEList<>();
        List<? extends EObject> allClasses = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
        for (EObject cls : allClasses) {
            for (BodyDeclaration method : ((ClassDeclaration) cls).getBodyDeclarations()) {
                if (!(method instanceof MethodDeclaration)) {
                    continue;
                }
                if (method.getModifier() == null) {
                    continue;
                }
                if (method.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                    methodDeclarations.add((MethodDeclaration) method);
                }
            }
        }
        return methodDeclarations;
    }

    /**
     * Compute allInstances by traversing the entire resource.
     */
    private static List<EObject> getAllInstances(Resource resource, EClass eClass) {
        List<EObject> result = new ArrayList<>();
        Iterable<EObject> allContents = resource::getAllContents;
        for (EObject o : allContents) {
            if (eClass.isInstance(o)) {
                result.add(o);
            }
        }
        return result;
    }
}
