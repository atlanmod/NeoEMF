package main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphFactory;
import mgraph.MgraphPackage;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.NESession;

public class UnloadTest {

	private INeo4emfResource resource;
	private MgraphFactory factory;
	
	private MGraph graph;
	private MNode node1;
	private MNode node2;
	private MEdge edge1;
	private MEdge edge2;
	
	private static final File DB_FOLDER = new File("/tmp/UnloadTest/output/ResourceSave");
	
	@Before
	public void setUp() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);
		
		NESession session = new NESession(MgraphPackage.eINSTANCE);
		resource = session.createResource(URI.createURI("neo4emf:"+DB_FOLDER.getAbsolutePath()), 1000);
		factory = MgraphFactory.eINSTANCE;
		
		graph = factory.createMGraph();
		graph.setName("graph");
		node1 = factory.createMNode();
		node1.setName("node1");
		node2 = factory.createMNode();
		node2.setName("node2");
		graph.getNodes().add(node1);
		node2.setGraph(graph);
		edge1 = factory.createMEdge();
		edge1.setName("edge1");
		edge2 = factory.createMEdge();
		edge2.setName("edge2");
		edge1.setGraph(graph);
		graph.getEdges().add(edge2);
	}
	
	@After
	public void tearDown() throws Exception {
		resource.shutdown();
		FileUtils.forceDelete(DB_FOLDER);
	}
	
	private void assertNothingHasBeenSaved() {
		/*
		 * First do the verifications that may imply a load from the 
		 * database
		 */
		assert graph.getNodes().size() 	== 2 : 		"Invalid Node count in Graph";
		assert graph.getEdges().size() 	== 2 : 		"Invalid Edge count in Graph";
		assert graph.eContents().size() == 4 : 		"Invalid eContents size in Graph";
		assert node1.getGraph() 		!= null : 	"Node1 getGraph is null";
		assert node1.eContainer() 		!= null : 	"Node1 eContainer is null";
		assert node2.getGraph() 		!= null : 	"Node2 getGraph is null";
		assert node2.eContainer() 		!= null : 	"Node2 eContainer is null";
		assert edge1.getGraph() 		!= null : 	"Edge1 getGraph is null";
		assert edge1.eContainer() 		!= null : 	"Edge1 eContainer is null";
		assert edge2.getGraph() 		!= null : 	"Edge2 getGraph is null";
		assert edge2.eContainer() 		!= null : 	"Edge2 eContainer is null";
		/*
		 * Then check that nothing has been loaded
		 */
		String savedError = " has been saved in the database";
		assert !graph.isLoaded() : "Graph" + savedError;
		assert !node1.isLoaded() : "Node1" + savedError;
		assert !node2.isLoaded() : "Node2" + savedError;
		assert !edge1.isLoaded() : "Edge1" + savedError;
		assert !edge2.isLoaded() : "Edge2" + savedError;
	}
	
	private void assertAllHasBeenSaved() {
		/*
		 * Check if all the base objects have been loaded before the
		 * verifications that may eventually load them
		 */
		String savedError = " hasn't been saved in the database";
		assert graph.isLoaded() : "Graph" + savedError;
		assert node1.isLoaded() : "Node1" + savedError;
		assert node2.isLoaded() : "Node2" + savedError;
		assert edge1.isLoaded() : "Edge1" + savedError;
		assert edge2.isLoaded() : "Edge2" + savedError;
		/*
		 * Then do the load verifications
		 */
		Iterator<MNode> nodeIt = graph.getNodes().iterator();
		while(nodeIt.hasNext()) {
			MNode innerNode = nodeIt.next();
			assert innerNode.isLoaded() : "Node " + innerNode.getName() + " in Graph data list hasn't been saved in the database";
		}
		Iterator<MEdge> edgeIt = graph.getEdges().iterator();
		while(edgeIt.hasNext()) {
			MEdge innerEdge = edgeIt.next();
			assert innerEdge.isLoaded() : "Edge " + innerEdge.getName() + " in Graph data list hasn't been saved in the database";
		}
		Iterator<EObject> contentsIt = graph.eContents().iterator();
		while(contentsIt.hasNext()) {
			INeo4emfObject innerContent = (INeo4emfObject)contentsIt.next();
			assert innerContent.isLoaded() : "Graph eContent " + innerContent.toString() + " hasn't been saved in the database";
		}
	}
	
	private void saveTmp() {
		Map<String,Object> options = new HashMap<String,Object>();
		options.put("tmp_save", true);
		try {
			resource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateMgraphEndAddNoCleaning() {
		resource.getContents().add(graph);
		assertNothingHasBeenSaved();
	}
	
	@Test
	public void testCreateGraphAddEndCleaningBeforeAdd() {
		Common.clearAllSoftReferences();
		resource.getContents().add(graph);
		assertNothingHasBeenSaved();
	}
	
	@Test
	public void testCreateGraphAddEndCleaningAfterAdd() {
		resource.getContents().add(graph);
		Common.clearAllSoftReferences();
		assertNothingHasBeenSaved();
	}
	
	@Test
	public void testCreateGraphAddEndTmpSaveAndCleaningAfterSave() {
		resource.getContents().add(graph);
		saveTmp();
		Common.clearAllSoftReferences();
		assertAllHasBeenSaved();
	}
	
	@Test
	public void testCreateGraphAddFirstCleaningAfterCreation() {
		graph = factory.createMGraph();
		resource.getContents().add(graph);
		graph.setName("graph");
		node1 = factory.createMNode();
		node1.setName("node1");
		graph.getNodes().add(node1);
		node2 = factory.createMNode();
		node2.setGraph(graph);
		node2.setName("node2");
		edge1 = factory.createMEdge();
		edge1.setGraph(graph);
		edge1.setName("edge1");
		edge2 = factory.createMEdge();
		edge2.setName("edge2");
		graph.getEdges().add(edge2);
		Common.clearAllSoftReferences();
		assertNothingHasBeenSaved();
	}
	
	@Test
	public void testCreateGraphAddFirstTmpSaveAndCleaningAfterSave() {
		graph = factory.createMGraph();
		resource.getContents().add(graph);
		graph.setName("graph");
		node1 = factory.createMNode();
		node1.setName("node1");
		graph.getNodes().add(node1);
		node2 = factory.createMNode();
		node2.setGraph(graph);
		node2.setName("node2");
		edge1 = factory.createMEdge();
		edge1.setGraph(graph);
		edge1.setName("edge1");
		edge2 = factory.createMEdge();
		edge2.setName("edge2");
		graph.getEdges().add(edge2);
		saveTmp();
		Common.clearAllSoftReferences();
		assertAllHasBeenSaved();
	}
	
	/**
	 * The idea is to check that if a part of the resource is temporary saved
	 * the other objects contained in the resource are not released.
	 */
	@Test
	public void testCreateGraphAddFirstTmpSaveNodesAndCleaningAfterAllCreation() {
		graph = factory.createMGraph();
		resource.getContents().add(graph);
		graph.setName("graph");
		node1 = factory.createMNode();
		node1.setName("node1");
		graph.getNodes().add(node1);
		node2 = factory.createMNode();
		node2.setGraph(graph);
		node2.setName("node2");
		saveTmp();
		edge1 = factory.createMEdge();
		edge1.setGraph(graph);
		edge1.setName("edge1");
		edge2 = factory.createMEdge();
		edge2.setName("edge2");
		graph.getEdges().add(edge2);
		Common.clearAllSoftReferences();
		/*
		 * Check if the MNode objects has been saved, then check that
		 * the MEdge objects hasn't (they have been created after the 
		 * save call)
		 */
		assert graph.isLoaded() : "Graph hasn't been saved in the database";
		assert node1.isLoaded() : "Node1 hasn't been saved in the database";
		assert node2.isLoaded() : "Node2 hasn't been saved in the database";
		Iterator<MNode> nodeIt = graph.getNodes().iterator();
		while(nodeIt.hasNext()) {
			MNode innerNode = nodeIt.next();
			assert innerNode == node1 || innerNode == node2 : "MNode object in Graph getNodes is different from the created Nodes";
			// If they are the same then it is not necessary to check if
			// it is loaded
		}
		assert !edge1.isLoaded() : "Edge1 has been saved in the database";
		assert !edge2.isLoaded() : "Edge2 has been saved in the database";
		Iterator<MEdge> edgeIt = graph.getEdges().iterator();
		while(edgeIt.hasNext()) {
			MEdge innerEdge = edgeIt.next();
			assert innerEdge == edge1 || innerEdge == edge2 : "MEdge object in Graph getEdges is different from the created Edges";
			// If they are the same then it is not necessary to check if
			// it is loaded
		}
	}
}
