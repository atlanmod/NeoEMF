package fr.inria.atlanmod.neo4emf.impl.tests;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;
import fr.inria.atlanmod.neo4emf.Point;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.testdata.Container;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;
import fr.inria.atlanmod.neo4emf.testdata.reltypes.ReltypesMappings;

public class Neo4emfResourceTest {
	
	private static final File DB_FOLDER = new File("/tmp/Neo4emfResourceTest");
	private static final URI uri = URI.createURI("neo4emf:///"+DB_FOLDER.getAbsolutePath());
	private static ResourceSet rSet = new ResourceSetImpl(); 
	
	
	private INeo4emfResource resource;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);
		Map<String, Map<Point, RelationshipType>> mapping = ReltypesMappings.getInstance().getMap(); 
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("neo4emf", INeo4emfResourceFactory.eINSTANCE.setRelationshipsMap(mapping));

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileUtils.forceDelete(DB_FOLDER);
	}

	@Before
	public void setUp() throws Exception {
		resource = (INeo4emfResource) rSet.createResource(uri);
	}

	@After
	public void tearDown() throws Exception {
		resource.shutdown();
	}

	@Test
	public void testCreateAndSave() throws Exception {
		TestPackage pack = TestPackage.eINSTANCE;
		TestFactory factory = pack.getTestFactory();
		
		Container container = factory.createContainer();
		container.setName("My Container");
		
		
		for (int i = 1; i < 100; i++) {
			Vertex vertex = factory.createVertex();
			vertex.setName("Vertex name ["+i+"]");
			vertex.setContainer(container);
		}

		resource.attached(container);
		System.out.println("Changelog size: " + resource.getChangeLog().size());
		
		
		
		resource.save(null);
		resource.shutdown();


		INeo4emfResource loaded = (INeo4emfResource) rSet.createResource(uri);
		loaded.load(null);
		EList<EObject> contents = loaded.getContents();
		
		//assert contents.contains(container);
		loaded.shutdown();
		
	}

	@Test
	public void testNeo4emfURItoString() throws Exception {
		String str = "neo4emf:////a/b/c";
		URI uri = URI.createURI(str);
		
		//String result = Neo4emfResource.neo4emfURItoString(uri);
		//assert "/a/b/c/".equals(result);
	}
	
	
	@Test
	public void testAttached() {
		EObject obj = new Neo4emfObject();
		//resource.attached(obj);
		
	}

	@Test
	public void testDetached() {
		EObject obj = EcoreFactory.eINSTANCE.createEObject();
		//resource.detached(obj);
	}

	@Test
	public void testSaveMapOfQQ() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLoadMapOfQQ() {
		//fail("Not yet implemented");
	}

	@Test
	public void testNeo4emfResourceStringMapOfStringMapOfPointRelationshipTypeMapOfStringString() {
		//Neo4emfResource resource = new Neo4emfResource(DB_DIR, null, null);
		
		
		//assert resource != null;
	}

	@Test
	public void testNeo4emfResourceURIMapOfStringMapOfPointRelationshipTypeMapOfStringString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFetchAttributes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetOnDemand() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShutdown() {
		//fail("Not yet implemented");
	}

	@Test
	public void testNotifyGet() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUnloadInt() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllInstancesEClass() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllInstancesInt() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetContainerOnDemand() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetRelationshipsMap() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetChangeLog() {
		//fail("Not yet implemented");
	}
	
}
