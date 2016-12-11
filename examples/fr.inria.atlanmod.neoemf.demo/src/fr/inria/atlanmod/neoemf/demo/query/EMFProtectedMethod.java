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
package fr.inria.atlanmod.neoemf.demo.query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

public class EMFProtectedMethod {
    
    public static void main(String[] args) throws IOException {
        PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());
        PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, HBasePersistenceBackendFactory.getInstance());
        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(HBaseURI.SCHEME, PersistentResourceFactory.getInstance());
        
        Resource graphResource = rSet.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")));
        graphResource.load(Collections.emptyMap());
        long begin = System.currentTimeMillis();
        EList<MethodDeclaration> qResult1 = getProtectedMethodDeclarations(graphResource);
        long end = System.currentTimeMillis();
        NeoLogger.info("[ProtectedMethods - GraphDB] Done, found " + qResult1.size() + " elements in " + ((end-begin)/1000) + " seconds");
        
        Resource mapResource = rSet.createResource(MapDbURI.createFileURI(new File("models/sample.mapdb")));
        mapResource.load(Collections.emptyMap());
        begin = System.currentTimeMillis();
        EList<MethodDeclaration> qResult2 = getProtectedMethodDeclarations(mapResource);
        end = System.currentTimeMillis();
        NeoLogger.info("[ProtectedMethods - MapDB] Done, found " + qResult2.size() + " elements in " + ((end-begin)/1000) + " seconds");
        
        Resource hbaseResource = rSet.createResource(HBaseURI.createURI("localhost", "2181", URI.createURI("sample.hbase")));
        hbaseResource.load(Collections.emptyMap());
        begin = System.currentTimeMillis();
        EList<MethodDeclaration> qResult3 = getProtectedMethodDeclarations(hbaseResource);
        end = System.currentTimeMillis();
        NeoLogger.info("[ProtectedMethods - HBase] Done, found " + qResult3.size() + " elements in " + ((end-begin)/1000) + " seconds");
        
        ((DefaultPersistentResource)graphResource).close();
        ((DefaultPersistentResource)mapResource).close();
        ((DefaultPersistentResource)hbaseResource).close();
    }
    
    public static EList<MethodDeclaration>  getProtectedMethodDeclarations(Resource resource) {
        EList<MethodDeclaration> methodDeclarations = new BasicEList<MethodDeclaration>();
        List<? extends EObject> allClasses = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
            for (EObject cls : allClasses) {
                for (BodyDeclaration  method : ((ClassDeclaration) cls).getBodyDeclarations()) {
                    
                    if (!(method instanceof MethodDeclaration)) {
                        continue;
                    }
                    if (method.getModifier() == null ){
                        continue;
                    }
                    if (((MethodDeclaration) method).getModifier().getVisibility() 
                            == VisibilityKind.PROTECTED) {            
                        methodDeclarations.add((MethodDeclaration) method);
                    }
                }
            }
        return methodDeclarations;
    }
    
    private static List<EObject> getAllInstances(Resource resource, EClass eClass) {
        // Compute allInstances by traversing the entire resource
        List<EObject> result = new ArrayList<EObject>();
        Iterator<EObject> it = resource.getAllContents();
        while(it.hasNext()) {
            EObject eObj = it.next();
            if(eClass.isInstance(eObj)) {
                result.add(eObj);
            }
        }
        return result;
    }
    
}
