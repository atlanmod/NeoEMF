package fr.inria.atlanmod.neo4emf.drivers.tests;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
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
import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

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
	
	@Test
	public void testPutManyToProxy() {
		int count = 1;
		Container[] containers = new Container[count];
		Vertex[] vertices = new Vertex[count];
		Link[] links = new Link[count];
		for(int i = 0; i < count; i++) {
			containers[i] = factory.createContainer();
			vertices[i] = factory.createVertex();
			links[i] = factory.createLink();
			containers[i].setNodeId(i);
			vertices[i].setNodeId(i);
			links[i].setNodeId(i);
			proxy.putToProxy(containers[i]);
			proxy.putToProxy(vertices[i]);
			proxy.putToProxy(links[i]);
		}
		Map<EClass,Cache<Long,INeo4emfObject>> internalProxy = proxy.getInternalProxy();
		Cache<Long,INeo4emfObject> containerCache = internalProxy.get(TestPackage.eINSTANCE.getContainer());
		Cache<Long,INeo4emfObject> vertexCache = internalProxy.get(TestPackage.eINSTANCE.getVertex());
		Cache<Long,INeo4emfObject> linkCache = internalProxy.get(TestPackage.eINSTANCE.getLink());
		assert containerCache != null : "The Container EClass is not an entry of the proxy";
		assert vertexCache != null : "The Vertex EClass is not an entry of the proxy";
		assert linkCache != null : "The Link EClass is not an entry of the proxy";
		for(int i = 0; i < count; i++) {
			assert containerCache.getIfPresent((long)i) != null : "The cached Container value for id " + i + " is null";
			assert vertexCache.getIfPresent((long)i) != null : "The cached Vertex value for id " + i + " is null";
			assert linkCache.getIfPresent((long)i) != null : "The cached Link value for id " + i + " is null";
			
			assert containerCache.getIfPresent((long)i) == containers[i] : "Wrong object for id " + i + " in container cache";
			assert vertexCache.getIfPresent((long)i) == vertices[i] : "Wrong object for id " + i + " in vertex cache";
			assert linkCache.getIfPresent((long)i) == links[i] : "Wrong object for id " + i + " in link cache";
		}
	}
	
	/**
	 * The idea is to do a clear that should not release the
	 * object in the cache (there are some strong references 
	 * on this object)
	 */
	@Test
	public void testPutToProxyAndUselessClear() {
		Container c = factory.createContainer();
		proxy.putToProxy(c);
		clearSoftReferences();
		assert proxy.getInternalProxy().containsKey(c.eClass()) : "The EClass is not an entry of the proxy";
		Cache<Long,INeo4emfObject> cache = proxy.getInternalProxy().get(c.eClass());
		assert cache.getIfPresent(c.getNodeId()) != null : "The cached value for id " + c.getNodeId() + " is null";
		assert cache.getIfPresent(c.getNodeId()) == c : "Wrong cached value for id " + c.getNodeId();
	}
	
	/**
	 * The idea is to do a clear that should release the 
	 * object in the cache (there is no strong references
	 * on this object)
	 */
	@Test
	public void testPutToProxyAndClear() {
		long id = 5;
		Container c = factory.createContainer();
		c.setNodeId(id);
		proxy.putToProxy(c);
		c = null;
		clearSoftReferences();
		// There is no way to remove the EClass from the proxy (because EClasses are not cached)
		assert proxy.getInternalProxy().containsKey(TestPackage.eINSTANCE.getContainer()) : "The EClass is not an entry of the proxy";
		Cache<Long,INeo4emfObject> cache = proxy.getInternalProxy().get(TestPackage.eINSTANCE.getContainer());
		assert cache.getIfPresent(id) == null : "The entry hasn't been removed";
	}
	
	private void clearSoftReferences() {
		try {
		    Object[] ignored = new Object[(int) Runtime.getRuntime().maxMemory()];
		} catch (Throwable e) {
		    // Ignore OME
		}
	}
}
