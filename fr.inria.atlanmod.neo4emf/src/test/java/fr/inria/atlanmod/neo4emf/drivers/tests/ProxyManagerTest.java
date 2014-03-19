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

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.IProxyManager;
import fr.inria.atlanmod.neo4emf.drivers.NESession;
import fr.inria.atlanmod.neo4emf.testdata.Container;
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
	public void testCreateElementNoSave() {
		Container c = factory.createContainer();
		resource.getContents().add(c);
		assert proxy.getNode(c) == -1;
	}
	
	@Test
	public void testCreateElementTmpSave() {
		Container c = factory.createContainer();
		Vertex v1 = factory.createVertex();
		c.getNodes().add(v1);
		// Don't add this one to check if it has been put in the proxy
		Vertex v2 = factory.createVertex();
		resource.getContents().add(c);
		tmpSave();
		assert c.getNodeId() > -1;
		assert proxy.getNode(c) == c.getNodeId();
		assert v1.getNodeId() > -1;
		assert proxy.getNode(v1) == v1.getNodeId();
		assert v2.getNodeId() == -1;
		assert proxy.getNode(v2) == -1;
	}
	
	@Test
	public void testCreateElementSave() {
		Container c = factory.createContainer();
		c.setName("container");
		Vertex v1 = factory.createVertex();
		c.getNodes().add(v1);
		// Don't add this one to check if it has been put in the proxy
		Vertex v2 = factory.createVertex();
		resource.getContents().add(c);
		save();
		assert c.getNodeId() > -1;
		assert proxy.getNode(c) == c.getNodeId();
		assert v1.getNodeId() > -1;
		assert proxy.getNode(v1) == v1.getNodeId();
		assert v2.getNodeId() == -1;
		assert proxy.getNode(v2) == -1;
	}
	
	private void tmpSave() {
		Map<String,Object> tmpOptions = new HashMap<String,Object>();
		tmpOptions.put("tmp_save", true);
		try {
			resource.save(tmpOptions);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try {
			resource.save(null);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
