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

package fr.inria.atlanmod.neoemf.demo.ocl;

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

import org.eclipse.emf.common.util.URI;
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
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class OCLProtectedMethods {

    public static void main(String[] args) throws IOException {
        PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());
        PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, HBasePersistenceBackendFactory.getInstance());

        ResourceSet rSet = new ResourceSetImpl();

        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(HBaseURI.SCHEME, PersistentResourceFactory.getInstance());

        JavaPackage.eINSTANCE.eClass();

        Instant start, end;

        try (PersistentResource resource = (PersistentResource) rSet.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")))) {
            resource.load(Collections.emptyMap());
            start = Instant.now();
            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            end = Instant.now();
            NeoLogger.info("[ProtectedMethods - GraphDB] Done, found {0} elements in {1} seconds", result.size(), Duration.between(start, end).getSeconds());
        }

        try (PersistentResource resource = (PersistentResource) rSet.createResource(MapDbURI.createFileURI(new File("models/sample.mapdb")))) {
            resource.load(Collections.emptyMap());
            start = Instant.now();
            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            end = Instant.now();
            NeoLogger.info("[ProtectedMethods - MapDB] Done, found {0} elements in {1} seconds", result.size(), Duration.between(start, end).getSeconds());
        }

        try (PersistentResource resource = (PersistentResource) rSet.createResource(HBaseURI.createHierarchicalURI("localhost", "2181", URI.createURI("sample.hbase")))) {
            resource.load(Collections.emptyMap());
            start = Instant.now();
            List<MethodDeclaration> result = getProtectedMethodDeclarations(resource);
            end = Instant.now();
            NeoLogger.info("[ProtectedMethods - HBase] Done, found {0} elements in {1} seconds", result.size(), Duration.between(start, end).getSeconds());
        }
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
            NeoLogger.error(e);
            return Collections.emptyList();
        }
    }
}
