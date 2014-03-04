/**
 * 
 */
package fr.inria.atlanmod.neo4emf.drivers.impl;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceServiceFactory;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

/**
 * @author sunye
 * 
 */
public class PersistenceServiceTest {
	private static final File DB_FOLDER = new File(
			"/tmp/PersistentServiceTest/");

	private IPersistenceService ps;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileUtils.forceDelete(DB_FOLDER);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		URI uri = URI.createURI("neo4emf:///"+DB_FOLDER.getAbsolutePath());
		NEConfiguration conf = new NEConfiguration(TestPackage.eINSTANCE, uri, Collections.<String,String>emptyMap());
		ps = IPersistenceServiceFactory.eINSTANCE
				.createPersistenceService(conf);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ps.shutdown();
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#PersistenceService(java.lang.String, fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager)}
	 * .
	 */
	@Test
	public void testPersistenceService() {
		assert ps != null : "null persistence service";
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#createWithIndexIfNotExists(org.eclipse.emf.ecore.EClass)}
	 * .
	 */
	/*@Test
	public void testCreateWithIndexIfNotExists() {
		String className = "TestEClass";
		String uri = "TestURI";
		EClass klass = EcoreFactory.eINSTANCE.createEClass();
		klass.setName(className);
		EPackage pac = EcoreFactory.eINSTANCE.createEPackage();
		pac.getEClassifiers().add(klass);
		pac.setNsURI(uri);

		NETransaction t = ps.createTransaction();
		Node n = ps.createWithIndexIfNotExists(klass);

		assert n != null;
		assert className.equals(n.getProperty(IPersistenceService.ECLASS_NAME));
		assert uri.equals(n.getProperty(IPersistenceService.NS_URI));
		t.commit();

	}*/

	/*
	 * Test method for {@link
	 * fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService
	 * #createResourceNodeIfAbsent()}.
	 */
	@Test
	public void testCreateResourceNodeIfAbsent() {
		/*
		Transaction t = ps.beginTx();
		Node n = ps.createResourceNodeIfAbsent();
		t.success();
		assert n != null;
		assert n.equals(ps
				.getMetaIndex()
				.get(IPersistenceService.ID_META,
						IPersistenceService.RESOURCE_NODE).getSingle());
		t.finish();
		*/
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#createObjectProxyFromNode(org.neo4j.graphdb.Node)}
	 * .
	 */
	// @Test
	public void testCreateObjectProxyFromNode() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#createNodeFromEObject(org.eclipse.emf.ecore.EObject)}
	 * .
	 */
	// @Test
	public void testCreateNodeFromEObject() {
		Vertex vertex = TestFactory.eINSTANCE.createVertex();
		NETransaction t = ps.createTransaction();
		Node n = ps.createNodeFromEObject(vertex);
		n.setProperty("Name", "My first node");
		t.commit();

		assert n != null;
		assert n.getId() > 0;
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#createTmpNodeFromEObject(org.eclipse.emf.ecore.EObject)}
	 * .
	 */
	// @Test
	public void testCreateTmpNodeFromEObject() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getAllRootNodes()}
	 * .
	 */
	// @Test
	public void testGetAllRootNodes() {
		/*
		Transaction t = ps.beginTx();
		ps.createResourceNodeIfAbsent();
		List<Node> nodes = ps.getAllRootNodes();

		assert nodes.isEmpty();
		t.finish();
		*/
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getAllTmpNodes()}
	 * .
	 */
	// @Test
	public void testGetAllTmpNodes() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#deleteBaseNode(org.neo4j.graphdb.Node, org.neo4j.graphdb.Node)}
	 * .
	 */
	// @Test
	public void testDeleteBaseNode() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#setUpTraversal(long, org.neo4j.graphdb.RelationshipType, org.neo4j.graphdb.Direction)}
	 * .
	 */
	// @Test
	public void testSetUpTraversal() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getNodeType(org.neo4j.graphdb.Node)}
	 * .
	 */
	// @Test
	public void testGetNodeType() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getContainingPackage(org.neo4j.graphdb.Node)}
	 * .
	 */
	// @Test
	public void testGetContainingPackage() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getNodesOnDemand(long, org.neo4j.graphdb.RelationshipType)}
	 * .
	 */
	// @Test
	public void testGetNodesOnDemand() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#isRootNode(org.neo4j.graphdb.Node)}
	 * .
	 */
	// @Test
	public void testIsRootNode() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getAllNodesOfType(org.eclipse.emf.ecore.EClass)}
	 * .
	 */
	// @Test
	public void testGetAllNodesOfType() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#createTransaction()}
	 * .
	 */
	// @Test
	public void testBeginTx() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#createNode()}
	 * .
	 */
	@Test
	public void testCreateNode() {
		/*
		Transaction t = ps.beginTx();
		Node root = ps.createResourceNodeIfAbsent();
		root.setProperty("name", "ROOT");
		try {
			for (int i = 0; i < 100; i++) {
				Node n = ps.createNode();
				n.setProperty("ID", i);
				root.createRelationshipTo(n, RelTypes.LINK);
			}
			t.success();
		}

		finally {
			t.finish();
		}
		*/
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getAllNodes()}
	 * .
	 */
	// @Test
	public void testGetAllNodes() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getNodeById(long)}
	 * .
	 */
	@Test
	public void testGetNodeById() {
		NETransaction tx;
		int times = 100;
		Node[] nodes = new Node[times];
		tx = ps.createTransaction();
		try {
			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = ps.createNode();
			}
			tx.success();
		} finally {
			tx.commit();
		}
		
		tx = ps.createTransaction();
		for (int i = 0; i < nodes.length; i++) {
			Node n = ps.getNodeById(nodes[i].getId());
			assert n.equals(nodes[i]);
		}
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getReferenceNode()}
	 * .
	 */
	// @Test
	public void testGetReferenceNode() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getRelationshipById(long)}
	 * .
	 */
	// @Test
	public void testGetRelationshipById() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#getRelationshipTypes()}
	 * .
	 */
	// @Test
	public void testGetRelationshipTypes() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#index()}
	 * .
	 */
	// @Test
	public void testIndex() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#registerKernelEventHandler(org.neo4j.graphdb.event.KernelEventHandler)}
	 * .
	 */
	// @Test
	public void testRegisterKernelEventHandler() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#registerTransactionEventHandler(org.neo4j.graphdb.event.TransactionEventHandler)}
	 * .
	 */
	// @Test
	public void testRegisterTransactionEventHandler() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#shutdown()}
	 * .
	 */
	// @Test
	public void testShutdown() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#unregisterKernelEventHandler(org.neo4j.graphdb.event.KernelEventHandler)}
	 * .
	 */
	// @Test
	public void testUnregisterKernelEventHandler() {
		// //fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService#unregisterTransactionEventHandler(org.neo4j.graphdb.event.TransactionEventHandler)}
	 * .
	 */
	// @Test
	public void testUnregisterTransactionEventHandler() {
		// //fail("Not yet implemented");
	}

	private static enum RelTypes implements RelationshipType {
		LINK
	}

}
