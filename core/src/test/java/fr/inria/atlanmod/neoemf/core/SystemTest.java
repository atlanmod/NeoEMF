package fr.inria.atlanmod.neoemf.core;


import java.io.File;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryProvider;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;
import fr.inria.atlanmod.neoemf.util.NeoURI;

/**
 * Unit test for simple App.
 */
public class SystemTest {

    @Test
    public void testCreateResource() {
    	//PersistenceBackendFactoryProvider.getFactories().put("kyanosmap",new MapPersistenceBackendFactory());
    	//ResourceSet resSet = new ResourceSetImpl();
    	//resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("kyanosmap", new PersistentResourceFactoryImpl());
    //	resSet.createResource(NeoURI.createNeoMapURI(new File("test")));
//        ResourceSet rs = new ResourceSetImpl();
//
//        Map<?,?> map = rs.getResourceFactoryRegistry().getProtocolToFactoryMap();
//
//        Resource.Factory mapdbResourceFactory = new PersistentResourceFactory(backend, mapping) // ???
//        Resource.Factory neo4jResourceFactory = new PersisntentResourceFactory(backend, mapping) // ???
//
//        map.put("mapdb", mapdbResourceFactory);
//        map.put("neo4j", neo4jResourceFactory);
//
//        URI uri1 = URI.createURI("mapdb:/MyFirstNeo4emfResource");
//        URI uri2 = URI.createURI("neo4j:/MyFirstNeo4emfResource");
//
//        Resource resource = rs.createResource(uri);
//
//        resource.save(options);
    }

}
