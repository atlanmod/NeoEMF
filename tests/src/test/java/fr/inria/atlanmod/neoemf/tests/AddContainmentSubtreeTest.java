package fr.inria.atlanmod.neoemf.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent;

/**
 * Checks that adding a transient containment sub-tree to an
 * existing PersistentResource add all its elements to the
 * resource.
 */
public class AddContainmentSubtreeTest extends AllBackendTest{

    protected MapSampleFactory factory;
	
	protected Pack p1;
	protected Pack p2;
	protected Pack p3;
	protected PackContent pc1;
	
	@Before
	public void setUp() throws Exception {
		factory = MapSampleFactory.eINSTANCE;
		ePackage = MapSamplePackage.eINSTANCE;
		super.setUp();
		super.createPersistentStores();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		p1 = null;
		p2 = null;
		p3 = null;
		pc1 = null;
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceMapDB() {
	    createResourceContent(mapResource);
        InternalPersistentEObject ip1 = (InternalPersistentEObject)p1;
        InternalPersistentEObject ip2 = (InternalPersistentEObject)p2;
        InternalPersistentEObject ip3 = (InternalPersistentEObject)p3;
        InternalPersistentEObject ipc1 = (InternalPersistentEObject)pc1;
        
        assert ip1.eStore() instanceof DirectWriteMapResourceEStoreImpl;
        assert ip2.eStore() instanceof DirectWriteMapResourceEStoreImpl;
        assert ip3.eStore() instanceof DirectWriteMapResourceEStoreImpl;
        assert ipc1.eStore() instanceof DirectWriteMapResourceEStoreImpl;
        
        assert ip1.resource() == mapResource;
        assert ip2.resource() == mapResource;
        assert ip3.resource() == mapResource;
        assert ipc1.resource() == mapResource;
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceNeo4j() {
	    createResourceContent(neo4jResource);
	    InternalPersistentEObject ip1 = (InternalPersistentEObject)p1;
        InternalPersistentEObject ip2 = (InternalPersistentEObject)p2;
        InternalPersistentEObject ip3 = (InternalPersistentEObject)p3;
        InternalPersistentEObject ipc1 = (InternalPersistentEObject)pc1;
        
        assert ip1.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert ip2.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert ip3.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert ipc1.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        
        assert ip1.resource() == neo4jResource;
        assert ip2.resource() == neo4jResource;
        assert ip3.resource() == neo4jResource;
        assert ipc1.resource() == neo4jResource;
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceTinker() {
	    createResourceContent(tinkerResource);
	    InternalPersistentEObject ip1 = (InternalPersistentEObject)p1;
        InternalPersistentEObject ip2 = (InternalPersistentEObject)p2;
        InternalPersistentEObject ip3 = (InternalPersistentEObject)p3;
        InternalPersistentEObject ipc1 = (InternalPersistentEObject)pc1;
        
        assert ip1.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert ip2.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert ip3.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert ipc1.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        
        assert ip1.resource() == tinkerResource;
        assert ip2.resource() == tinkerResource;
        assert ip3.resource() == tinkerResource;
        assert ipc1.resource() == tinkerResource;
	}
	
	public void createResourceContent(PersistentResource r) {
	    p1 = factory.createPack();
        p1.setName("p1");
        
        r.getContents().add(p1);
        
        p2 = factory.createPack();
        p2.setName("p2");
        pc1 = factory.createPackContent();
        p3 = factory.createPack();
        p3.setName("p3");
        p2.getPacks().add(p3);
        pc1.setName("pc1");
        p3.getOwnedContents().add(pc1);
        
        p1.getPacks().add(p2);
	}
	
}
