package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Checks that adding a transient containment sub-tree to an
 * existing PersistentResource add all its elements to the
 * resource.
 */
public class AddContainmentSubtreeTest extends AllContainmentTest {

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

    private void addContainmentSubtreeToPersistentResource(PersistentResource persistentResource, Class<?> eStoreClass) {
        createResourceContent(persistentResource);

        InternalPersistentEObject ip1 = (InternalPersistentEObject) p1;
        InternalPersistentEObject ip2 = (InternalPersistentEObject) p2;
        InternalPersistentEObject ip3 = (InternalPersistentEObject) p3;
        InternalPersistentEObject ipc1 = (InternalPersistentEObject) pc1;

        assertThat(ip1.eStore(), instanceOf(eStoreClass));
        assertThat(ip2.eStore(), instanceOf(eStoreClass));
        assertThat(ip3.eStore(), instanceOf(eStoreClass));
        assertThat(ipc1.eStore(), instanceOf(eStoreClass));

        assertThat(ip1.resource(), sameInstance((Resource.Internal) persistentResource));
        assertThat(ip2.resource(), sameInstance((Resource.Internal) persistentResource));
        assertThat(ip3.resource(), sameInstance((Resource.Internal) persistentResource));
        assertThat(ipc1.resource(), sameInstance((Resource.Internal) persistentResource));
    }
	
}
