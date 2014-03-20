package fr.inria.atlanmod.neo4emf.drivers.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.cache.Cache;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.IProxyManager;
import fr.inria.atlanmod.neo4emf.drivers.NESession;
import fr.inria.atlanmod.neo4emf.testdata.Container;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;

public class ProxyManagerTest {
	
	private static final File DB_FOLDER = new File("/tmp/ProxyManagerTest");
	private INeo4emfResource resource;
	private TestFactory factory;
	private IProxyManager proxy;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	
	@Before
	public void setUp() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);
		URI uri = URI.createURI("neo4emf:"+DB_FOLDER.getAbsolutePath());
		// NESession initialization (simpler than previous one)
		NESession session = new NESession(TestPackage.eINSTANCE);
		resource = session.createResource(uri,1000);
		factory = TestFactory.eINSTANCE;
		proxy = resource.getPersistenceManager().getProxyManager();
	}

	@After
	public void tearDown() throws Exception {
		resource.shutdown();
		FileUtils.forceDeleteOnExit(DB_FOLDER);
	}
	
	@Test
	public void testPutToProxy() {
		Container c = factory.createContainer();
		proxy.putToProxy(c);
		assert proxy.getInternalProxy().containsKey(c.eClass()) : "The EClass is not an entry of the proxy";
		Cache<Long,INeo4emfObject> cache = proxy.getInternalProxy().get(c.eClass());
		assert cache.getIfPresent(c.getNodeId()) != null : "The cached value for id " + c.getNodeId() + " is null";
		assert cache.getIfPresent(c.getNodeId()) == c : "Wrong cached value for id " + c.getNodeId();
	}
}
