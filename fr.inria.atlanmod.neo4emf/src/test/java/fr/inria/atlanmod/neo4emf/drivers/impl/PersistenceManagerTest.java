package fr.inria.atlanmod.neo4emf.drivers.impl;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;

public class PersistenceManagerTest {

	private static final File DB_FOLDER = new File("/tmp/PersistentManagerTest");
	private static PersistenceManager pm;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);
		pm = new PersistenceManager(null, DB_FOLDER.getAbsolutePath(), null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileUtils.forceDelete(DB_FOLDER);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersistenceManagerNeo4emfResourceStringMapOfStringMapOfPointRelationshipType() {
		URI uri = URI.createURI("neo4emf:/./MyFirstNeo4emfResource");
		INeo4emfResource resource = INeo4emfResourceFactory.eINSTANCE.createResource(uri);
		PersistenceManager pm = new PersistenceManager(resource, DB_FOLDER.getAbsolutePath(), null);
		assert pm != null;
	}

	@Test
	public void testPersistenceManagerNeo4emfResourceStringMapOfStringMapOfPointRelationshipTypeMapOfStringString() {
		
	}

	@Test
	public void testSaveMapOfQQ() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLoad() {
		pm.load();
		//fail("Not yet implemented");
	}

	@Test
	public void testLoadMapOfQQ() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		//fail("Not yet implemented");
	}

	@Test
	public void testBeginTx() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShutdown() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNodeById() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRelTypefromERef() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateNodefromEObject() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPutNodeId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllRootNodes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddObjectsToContents() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNodeType() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNodeContainingPackage() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFetchAttributes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPutToProxy() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPutAllToProxy() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPutAllToProxy2() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetOnDemand() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetContainerOnDemand() {
		//fail("Not yet implemented");
	}

	@Test
	public void testProxyContainsLongKey() {
		//fail("Not yet implemented");
	}

	@Test
	public void testProxyContainsObjectKey() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetObjectFromProxyLong() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateProxyManager() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsRootNode() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNewPartitionID() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsHead() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDelegate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUnloadPartition() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDoUnload() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUnload() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllInstancesOfType() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateNewPartitionHistory() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateNewFlatPartition() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateNewPartition() {
		//fail("Not yet implemented");
	}

	@Test
	public void testMoveToPartition() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetUsageTrace() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAffectedElement() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetRelationshipsMap() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetResource() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetObjectFromProxyEClassNode() {
		//fail("Not yet implemented");
	}

}
