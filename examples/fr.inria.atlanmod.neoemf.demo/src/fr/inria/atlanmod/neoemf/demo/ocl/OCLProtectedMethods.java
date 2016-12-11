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
package fr.inria.atlanmod.neoemf.demo.ocl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

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
        
        Resource graphResource = rSet.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")));
        graphResource.load(Collections.emptyMap());
        long begin = System.currentTimeMillis();
        List<MethodDeclaration> qResult1 = getProtectedMethodDeclarations(graphResource);
        long end = System.currentTimeMillis();
        NeoLogger.info("[ProtectedMethods - GraphDB] Done, found " + qResult1.size() + " elements in " + ((end-begin)/1000) + " seconds");
        
        Resource mapResource = rSet.createResource(MapDbURI.createFileURI(new File("models/sample.mapdb")));
        mapResource.load(Collections.emptyMap());
        begin = System.currentTimeMillis();
        List<MethodDeclaration> qResult2 = getProtectedMethodDeclarations(mapResource);
        end = System.currentTimeMillis();
        NeoLogger.info("[ProtectedMethods - MapDB] Done, found " + qResult2.size() + " elements in " + ((end-begin)/1000) + " seconds");
        
        Resource hbaseResource = rSet.createResource(HBaseURI.createURI("localhost", "2181", URI.createURI("sample.hbase")));
        hbaseResource.load(Collections.emptyMap());
        begin = System.currentTimeMillis();
        List<MethodDeclaration> qResult3 = getProtectedMethodDeclarations(hbaseResource);
        end = System.currentTimeMillis();
        NeoLogger.info("[ProtectedMethods - HBase] Done, found " + qResult3.size() + " elements in " + ((end-begin)/1000) + " seconds");
        
        ((DefaultPersistentResource)graphResource).close();
        ((DefaultPersistentResource)mapResource).close();
        ((DefaultPersistentResource)hbaseResource).close();
    }
    
    @SuppressWarnings("unchecked")
    public static List<MethodDeclaration>  getProtectedMethodDeclarations(Resource resource) {
        try {
            OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
            OCLInput oclInput = new OCLInput(new FileInputStream(new File("ocl/protectedMethods.ocl")));
            List<Constraint> constraints = ocl.parse(oclInput);
            List<MethodDeclaration> result = (List<MethodDeclaration>)ocl.createQuery(constraints.get(0)).evaluate(resource.getContents().get(0));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
