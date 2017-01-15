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

package fr.inria.atlanmod.neoemf.demo.query;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EMFProtectedMethod {

    public static void main(String[] args) throws IOException {
        PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());
        PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, HBasePersistenceBackendFactory.getInstance());

        ResourceSet rSet = new ResourceSetImpl();

        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(HBaseURI.SCHEME, PersistentResourceFactory.getInstance());

        long begin, end;

        try (PersistentResource resource = (PersistentResource) rSet.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")))) {
            resource.load(Collections.emptyMap());
            begin = System.currentTimeMillis();
            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            end = System.currentTimeMillis();
            NeoLogger.info("[ProtectedMethods - GraphDB] Done, found {0} elements in {1} seconds", result.size(), (end - begin) / 1000);
        }

        try (PersistentResource resource = (PersistentResource) rSet.createResource(MapDbURI.createFileURI(new File("models/sample.mapdb")))) {
            resource.load(Collections.emptyMap());
            begin = System.currentTimeMillis();
            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            end = System.currentTimeMillis();
            NeoLogger.info("[ProtectedMethods - MapDB] Done, found {0} elements in {1} seconds", result.size(), (end - begin) / 1000);
        }

        try (PersistentResource resource = (PersistentResource) rSet.createResource(HBaseURI.createHierarchicalURI("localhost", "2181", URI.createURI("sample.hbase")))) {
            resource.load(Collections.emptyMap());
            begin = System.currentTimeMillis();
            EList<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            end = System.currentTimeMillis();
            NeoLogger.info("[ProtectedMethods - HBase] Done, found {0} elements in {1} seconds", result.size(), (end - begin) / 1000);
        }
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
