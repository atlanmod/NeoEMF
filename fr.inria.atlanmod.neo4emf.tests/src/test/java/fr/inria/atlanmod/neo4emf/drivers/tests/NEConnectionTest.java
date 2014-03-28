package fr.inria.atlanmod.neo4emf.drivers.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.index.lucene.LuceneKernelExtensionFactory;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.impl.cache.CacheProvider;
import org.neo4j.kernel.impl.cache.SoftCacheProvider;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.drivers.NEConnection;
import fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction;
import fr.inria.atlanmod.neo4emf.testdata.Temperature;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

public class NEConnectionTest {

	private GraphDatabaseService db;
	private NEConnection connection;
	private static final File DB_FOLDER = new File("/tmp/NEConnectionTest/");
	private static final URI uri = URI.createURI("neo4emf:"
			+ DB_FOLDER.getAbsolutePath());
	private static GraphDatabaseFactory gdbf;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);

		// the cache providers
		List<CacheProvider> cacheList = new ArrayList<CacheProvider>();
		cacheList.add(new SoftCacheProvider());

		// the kernel extensions
		LuceneKernelExtensionFactory lucene = new LuceneKernelExtensionFactory();
		List<KernelExtensionFactory<?>> extensions = new ArrayList<KernelExtensionFactory<?>>();
		extensions.add(lucene);

		// the database setup
		gdbf = new GraphDatabaseFactory();
		gdbf.setKernelExtensions(extensions);
		gdbf.setCacheProviders(cacheList);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileUtils.forceDelete(DB_FOLDER);
	}

	@Before
	public void setUp() throws Exception {

		db = gdbf.newEmbeddedDatabase(DB_FOLDER.getAbsolutePath());
		registerShutdownHook(db);

		NEConfiguration conf = new NEConfiguration(TestPackage.eINSTANCE, uri,
				Collections.<String, String> emptyMap());
		connection = new NEConnection(db, conf);
		connection.open();
	}

	@After
	public void tearDown() throws Exception {
		connection.close();
	}

	@Test
	public void testOpen() {

		IndexManager im = db.index();

		assert im.existsForNodes(IPersistenceService.META_ELEMENTS) : "Meta index not created";
		Index<Node> index = im.forNodes(IPersistenceService.META_ELEMENTS);

		for (EClassifier each : TestPackage.eINSTANCE.getEClassifiers()) {
			String id = each.getEPackage().getName() + "_"
					+ each.getClassifierID();
			Node n = index.get(IPersistenceService.ID_META, id).getSingle();
			// System.out.println(n.getProperty(IPersistenceService.ECLASS_NAME));
			assert n != null : "Missing Node for Eclass";
		}
	}

	@Test
	public void testAddNode() {
		TestFactory factory = TestFactory.eINSTANCE;
		Vertex v = factory.createVertex();

		NETransaction tx = connection.createTransaction();
		try {
			Node n = connection.addObject(v);
			
			assert n.getId() == v.getNodeId() : "Wrong node id";
			tx.success();
		} catch (Exception e) {
			tx.abort();
		} finally {
			tx.commit();
			// connection.close();
		}

	}

	@Test
	public void testAddRootObject() {

		TestFactory factory = TestFactory.eINSTANCE;
		Vertex v = factory.createVertex();

		NETransaction tx = connection.createTransaction();
		try {
			assert connection.addRootObject(v) : "Root object not added.";
			Iterable<Relationship> rels = this.resourceNode().getRelationships(IPersistenceService.IS_ROOT);
			
			Node newNode = db.getNodeById(v.getNodeId());
			
			boolean found = false;
			for(Relationship each : rels) {
				if (each.getEndNode().getId() == newNode.getId()) {
					found = true;
					break;
				}
			}
			assert found : "Node node found"; 
			tx.success();
		} catch (Exception e) {
			tx.abort();
		} finally {
			tx.commit();
			// connection.close();
		}
	}
	
	/**
	 * Asserts that attributes are correctly saved as node properties.
	 */
	@Test
	public void testSaveObjectProperties() {
		String name = "My Object Special Name";
		Temperature temperature = Temperature.HOT;
		BigDecimal decimal = BigDecimal.valueOf(1000.09);
		BigInteger integer = BigInteger.valueOf(42);
		
		boolean[] booleanArray = {true, true, false, false, true, false};
		String[] stringArray = {"My", "very", "complex", "string","array"};
		double[] doubleArray = {1.2, 3.4, 999999.27, 4444.42};
		long[] longArray = {1,2,3,6,Long.MAX_VALUE, Long.MIN_VALUE};
		
		TestFactory factory = TestFactory.eINSTANCE;
		Vertex v = factory.createVertex();

		NETransaction tx = connection.createTransaction();
		try {
			Node n = connection.addObject(v);
			v.setABoolean(true);
			v.setName(name);
			v.setTemperature(Temperature.HOT);
			v.setAReal(decimal);
			v.setATransientInteger(integer);
			for (boolean each : booleanArray) {
				v.getANonOrderedArray().add(each);
			}
			for(String each : stringArray) {
				v.getAStringArray().add(each);
			}
			for(double each : doubleArray) {
				v.getANonUniqueArray().add(BigDecimal.valueOf(each));
			}
			for(long each : longArray) {
				v.getAnIntegerArray().add(BigInteger.valueOf(each));
			}
			
			connection.saveAllAttributes(v);
			
			
			assert (boolean) n.getProperty("aBoolean") : "Wrong boolean";
			assert temperature == Temperature.get((int) n.getProperty("temperature")) : "Wrong enumeration value";
			assert name.equals(n.getProperty("name")) : "Wrong String";
			assert decimal.doubleValue() == (double) n.getProperty("aReal") : "Wrong Real";
			assert integer.longValue() == (long) n.getProperty("aTransientInteger") : "Wrong Integer";
			
			boolean[] resultArray = (boolean[]) n.getProperty("aNonOrderedArray");
			assert Arrays.equals(booleanArray, resultArray) : "Wrong Array Value" ;
			
			String[] resultStringArray = (String[]) n.getProperty("aStringArray");
			assert Arrays.equals(stringArray, resultStringArray) : "Wrong String Array Value";
			
			double[] resultDecimalArray = (double[]) n.getProperty("aNonUniqueArray");
			assert Arrays.equals(doubleArray, resultDecimalArray) : "Wrong Double Array Value";
			
			long[] resultLongArray = (long[]) n.getProperty("anIntegerArray");
			assert Arrays.equals(longArray,resultLongArray);
			
			tx.success();	
		} catch (Exception e) {
			e.printStackTrace();
			fail("Something went wrong during database transaction");
			tx.abort();
			
		} finally {
			tx.commit();
			// connection.close();
		}
	}	
	
	
	@Test
	public void testRetrieveObject() {
		String name = "Vertex Name";
		TestFactory factory = TestFactory.eINSTANCE;
		Vertex v = factory.createVertex();
		v.setName(name);

		NETransaction tx = connection.createTransaction();
		try {
			Node n = connection.addObject(v);
			connection.saveAllAttributes(v);
			
			Vertex retrieved = (Vertex) connection.retrieveObject(n.getId());
			connection.loadAttributes(retrieved);
			
			assert retrieved.getNodeId() == v.getNodeId() : "Wrong node id";
			assert retrieved.eClass() == v.eClass() : "Wrong Type (EClass)";
			assert name.equals(retrieved.getName());

			
			tx.success();	
		} catch (Exception e) {
			e.printStackTrace();
			fail("Something went wrong during database transaction");
			tx.abort();
			
		} finally {
			tx.commit();
			// connection.close();
		}
	}
	
	@Test
	public void testRetrieveCachedObject() {
		String name = "Vertex Name";
		TestFactory factory = TestFactory.eINSTANCE;
		Vertex v = factory.createVertex();
		v.setName(name);

		NETransaction tx = connection.createTransaction();
		try {
			Node n = connection.addObject(v);
			connection.saveAllAttributes(v);
			
			Vertex first = (Vertex) connection.retrieveObject(n.getId());
			Vertex second = (Vertex) connection.retrieveObject(n.getId());
			connection.loadAttributes(first);
			
			assert first == second : "Object created twice";
			
			tx.success();	
		} catch (Exception e) {
			e.printStackTrace();
			fail("Something went wrong during database transaction");
			tx.abort();
			
		} finally {
			tx.commit();
			// connection.close();
		}
	}
	
	
	
	private Node resourceNode() {
		IndexManager manager = db.index();
		Index<Node> metaIndex = manager.forNodes(IPersistenceService.META_ELEMENTS);
		
		Node resourceNode = metaIndex.get(IPersistenceService.ID_META,
				IPersistenceService.RESOURCE_NODE).getSingle();
		
		return resourceNode; 
	}

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// At this point the plugin "Neo4emfPlugin" has been already
				// stopped
				// Don't use the shared instance!
				// Shutdown the DB
				graphDb.shutdown();
			}
		});
	}

}
