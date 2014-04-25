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
import fr.inria.atlanmod.neo4emf.testdata.ContainerType;
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
		ContainerType c = factory.createContainerType();
		proxy.putToProxy(c);
		assert proxy.getInternalProxy().containsKey(c.eClass()) : "The EClass is not an entry of the proxy";
		Cache<Long,INeo4emfObject> cache = proxy.getInternalProxy().get(c.eClass());
		assert cache.getIfPresent(c.getNodeId()) != null : "The cached value for id " + c.getNodeId() + " is null";
		assert cache.getIfPresent(c.getNodeId()) == c : "Wrong cached value for id " + c.getNodeId();
	}
	
	@Test
	public void testPutManyToProxy() {
		int count = 10;
		ContainerType[] containers = new ContainerType[count];
		Vertex[] vertices = new Vertex[count];
		Link[] links = new Link[count];
		for(int i = 0; i < count; i++) {
			containers[i] = factory.createContainerType();
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
		Cache<Long,INeo4emfObject> containerCache = internalProxy.get(TestPackage.eINSTANCE.getContainerType());
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
		ContainerType c = factory.createContainerType();
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
		ContainerType c = factory.createContainerType();
		c.setNodeId(id);
		proxy.putToProxy(c);
		c = null;
		clearSoftReferences();
		// There is no way to remove the EClass from the proxy (because EClasses are not cached)
		assert proxy.getInternalProxy().containsKey(TestPackage.eINSTANCE.getContainerType()) : "The EClass is not an entry of the proxy";
		Cache<Long,INeo4emfObject> cache = proxy.getInternalProxy().get(TestPackage.eINSTANCE.getContainerType());
		assert cache.getIfPresent(id) == null : "The entry hasn't been removed";
	}
	
	@Test
	public void testGetObjectFromProxyExistingElement() {
		long id = 5;
		ContainerType c = factory.createContainerType();
		c.setNodeId(id);
		proxy.putToProxy(c);
		assert proxy.getObjectFromProxy(c.eClass(), id) != null : "Cannot retrieve the added object";
		assert proxy.getObjectFromProxy(c.eClass(), id) == c : "Wrong object returned by the proxy";
	}
	
	@Test
	public void testGetObjectFromProxyNotExistingElement() {
		long cId = 5;
		long vId = 10;
		ContainerType c = factory.createContainerType();
		c.setNodeId(cId);
		proxy.putToProxy(c);
		Vertex v = factory.createVertex();
		v.setNodeId(vId);
		assert proxy.getObjectFromProxy(v.eClass(), vId) == null : "The Vertex is in the proxy";
	}
	
	@Test
	public void testGetObjectFromProxyManyExistingElements() {
		int count = 10;
		ContainerType[] containers = new ContainerType[count];
		Vertex[] vertices = new Vertex[count];
		Link[] links = new Link[count];
		// Add some EObjects to the proxy
		for(int i = 0; i < count; i++) {
			containers[i] = factory.createContainerType();
			vertices[i] = factory.createVertex();
			links[i] = factory.createLink();
			containers[i].setNodeId(i);
			vertices[i].setNodeId(i);
			links[i].setNodeId(i);
			proxy.putToProxy(containers[i]);
			proxy.putToProxy(vertices[i]);
			proxy.putToProxy(links[i]);
		}
		// Check if they all are in the proxy
		for(int i = 0; i < count; i++) {
			assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getContainerType(), (long)i) != null : "Container object with id " + i + " is null";
			assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getVertex(), (long)i) != null : "Vertex object with id " + i + " is null";
			assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getLink(), (long)i) !=null : "Link object with id " + i + " is null";
			assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getContainerType(), (long)i) == containers[i] : "Wrong Container object with id " + i;
			assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getVertex(), (long)i) == vertices[i] : "Wrong Vertex object with id " + i;
			assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getLink(), (long)i) == links[i] : "Wrong Link object with id " + i;
		}
	}
	
	/**
	 * The idea is to check that the result of the method is not
	 * modified after a useless soft reference cleaning (useless because
	 * there are some strong references on the object and the should not
	 * be released)
	 */
	@Test
	public void testGetObjectFromProxyAfterUselessClear() {
		long cId = 5;
		long vId = 10;
		ContainerType c = factory.createContainerType();
		Vertex v = factory.createVertex();
		c.setNodeId(cId);
		v.setNodeId(vId);
		proxy.putToProxy(c);
		proxy.putToProxy(v);
		clearSoftReferences();
		assert proxy.getObjectFromProxy(c.eClass(), cId) != null : "Container in proxy has been released";
		assert proxy.getObjectFromProxy(v.eClass(),vId) != null : "Vertex in proxy has been released";
		assert proxy.getObjectFromProxy(c.eClass(), cId) == c : "Wrong Container object in proxy";
		assert proxy.getObjectFromProxy(v.eClass(), vId) == v : "Wrong Vertex object in proxy";
	}
	
	@Test
	public void testGetObjectFromProxyAfterClear() {
		long cId = 5;
		long vId = 10;
		ContainerType c = factory.createContainerType();
		Vertex v = factory.createVertex();
		c.setNodeId(cId);
		v.setNodeId(vId);
		proxy.putToProxy(c);
		proxy.putToProxy(v);
		c = null;
		v = null;
		clearSoftReferences();
		assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getContainerType(), cId) == null : "Container in proxy hasn't been released";
		assert proxy.getObjectFromProxy(TestPackage.eINSTANCE.getVertex(), vId) == null : "Vertex in proxy hasn't been released";
	}
	
	private void clearSoftReferences() {
		try {
		    Object[] ignored = new Object[(int) Runtime.getRuntime().maxMemory()];
		} catch (Throwable e) {
		    // Ignore OME
		}
	}
}
