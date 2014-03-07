package fr.inria.atlanmod.neo4emf.drivers.tests;

import java.io.File;
import java.util.ArrayList;
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
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.index.lucene.LuceneKernelExtensionFactory;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.impl.cache.CacheProvider;
import org.neo4j.kernel.impl.cache.SoftCacheProvider;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.drivers.NEConnection;
import fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

public class NEConnectionTest {
	
	private GraphDatabaseService db;
	private NEConnection connection;
	private static final File DB_FOLDER = new File("/tmp/NEConnectionTest/");
    private static final URI uri = URI.createURI("neo4emf:"+DB_FOLDER.getAbsolutePath());
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
		
		NEConfiguration conf = new NEConfiguration(TestPackage.eINSTANCE, uri, Collections.<String,String>emptyMap());
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
			System.out.println(n.getProperty(IPersistenceService.ECLASS_NAME));
			assert n != null : "Missing Node for Eclass";
		}
	}

	@Test
	public void testAddNode() {
		TestFactory factory = TestFactory.eINSTANCE;
		Vertex v = factory.createVertex();
		connection.open();
		NETransaction tx = connection.createTransaction();
		try {
			connection.addObject(v);
			tx.success();
		} catch(Exception e) {
			tx.abort();
		} finally {
			tx.commit();
			connection.close();
		}
		
		
	}

	@Test
	public void stCreateTransaction() {
		//fail("Not yet implemented");
	}


    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // At this point the plugin "Neo4emfPlugin" has been already stopped
                // Don't use the shared instance!
                // Shutdown the DB
                graphDb.shutdown();
            }
        });
    }

}
