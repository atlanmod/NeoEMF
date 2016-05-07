package fr.inria.atlanmod.neoemf.tests;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Checks that adding a transient containment sub-tree to an
 * existing PersistentResource add all its elements to the
 * resource.
 */
public class SetContainmentAfterNonContainmentTest extends AllContainmentTest {

	@Test
	public void testAddContainmentSubtreeToPersistentResourceMapDB() {
		addContainmentSubtreeToPersistentResource(mapResource, DirectWriteMapResourceEStoreImpl.class);
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceNeo4j() {
		addContainmentSubtreeToPersistentResource(neo4jResource, DirectWriteBlueprintsResourceEStoreImpl.class);
	}
	
	@Test
	public void testAddContainmentSubtreeToPersistentResourceTinker() {
		addContainmentSubtreeToPersistentResource(tinkerResource, DirectWriteBlueprintsResourceEStoreImpl.class);
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

	private void addContainmentSubtreeToPersistentResource(PersistentResource persistentResource, Class<?> eStoreClass) {
		createResourceContent(persistentResource);
		InternalPersistentEObject icom1 = (InternalPersistentEObject)com1;

		assertThat(icom1.eStore(), instanceOf(eStoreClass));
		assertThat(icom1.resource(), sameInstance((Resource.Internal) persistentResource));

		// Check that the element has a container (it cannot be in the resource if it does not)
		assertThat(icom1.eContainer(), sameInstance((EObject) pc1));
		assertThat(icom1.eInternalContainer(), sameInstance((EObject) pc1));

		// Check that the element is in the containment reference list of its parent
		assertThat(pc1.getContainmentNoOppositeRefComment().contains(com1), is(true));

		// Check everything is accessible from the resource
		assertThat(resourceContentCount(persistentResource), equalTo(4));
	}
	
}
