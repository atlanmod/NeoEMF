package fr.inria.atlanmod.neoemf.tests;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContentComment;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent;

/**
 * Checks that adding a transient containment sub-tree to an
 * existing PersistentResource add all its elements to the
 * resource.
 */
public class SetContainmentAfterNonContainmentTest extends AllBackendTest{

    protected MapSampleFactory factory;
	
	protected Pack p1;
	protected Pack p2;
	protected PackContent pc1;
	protected AbstractPackContentComment com1;
	
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
		pc1 = null;
		com1 = null;
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceMapDB() {
	    createResourceContent(mapResource);
        InternalPersistentEObject icom1 = (InternalPersistentEObject)com1;
        
        assert icom1.eStore() instanceof DirectWriteMapResourceEStoreImpl;
        assert icom1.resource() == mapResource;
        // Check that the element has a container (it cannot be in the resource
        // if it does not)
        assert icom1.eContainer() == pc1;
        assert icom1.eInternalContainer() == pc1;
        // Check that the element is in the containment reference list of its
        // parent
        assert pc1.getContainmentNoOppositeRefComment().contains(com1);
        // Check everything is accessible from the resource
        assert resourceContentCount(mapResource) == 4;
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceNeo4j() {
	    createResourceContent(neo4jResource);
	    InternalPersistentEObject icom1 = (InternalPersistentEObject)com1;
        
        assert icom1.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert icom1.resource() == neo4jResource;
        // Check that the element has a container (it cannot be in the resource
        // if it does not)
        assert icom1.eContainer() == pc1;
        assert icom1.eInternalContainer() == pc1;
        // Check that the element is in the containment reference list of its
        // parent
        assert pc1.getContainmentNoOppositeRefComment().contains(com1);
     // Check everything is accessible from the resource
        assert resourceContentCount(neo4jResource) == 4;
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceTinker() {
	    createResourceContent(tinkerResource);
	    InternalPersistentEObject icom1 = (InternalPersistentEObject)com1;
        
        assert icom1.eStore() instanceof DirectWriteBlueprintsResourceEStoreImpl;
        assert icom1.resource() == tinkerResource;
        // Check that the element has a container (it cannot be in the resource
        // if it does not)
        assert icom1.eContainer() == pc1;
        assert icom1.eInternalContainer() == pc1;
        // Check that the element is in the containment reference list of its
        // parent
        assert pc1.getContainmentNoOppositeRefComment().contains(com1);
     // Check everything is accessible from the resource
        assert resourceContentCount(tinkerResource) == 4;
	}
	
	protected void createResourceContent(PersistentResource r) {
	    p1 = factory.createPack();
        p1.setName("p1");
        
        r.getContents().add(p1);
        
        p2 = factory.createPack();
        p2.setName("p2");
        p1.getPacks().add(p2);
        pc1 = factory.createPackContent();
        pc1.setName("pc1");
        p2.getOwnedContents().add(pc1);
        
        com1 = factory.createAbstractPackContentComment();
        com1.setContent("My Content");
        
        // Add using the non-containment reference
        p2.getNonContainmentRefComments().add(com1);
        // Then add the element to the resource tree using the containment reference
        pc1.getContainmentNoOppositeRefComment().add(com1);
	}
	
	protected int resourceContentCount(PersistentResource r) {
	    int count = 0;
	    Iterator<EObject> it = r.getAllContents();
	    while(it.hasNext()) {
	        count++;
	        it.next();
	    }
	    return count;
	}
	
}
