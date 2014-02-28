package fr.inria.atlanmod.neo4emf.tests.reflection;

import java.io.IOException;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphPackage;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;

public class LoadTest {

	private INeo4emfResource resource;
	
	@Before
	public void setUp() {
		//Neo4emfResourceUtil.deleteDirectoryIfExists(new File("./MyFirstneo4emfDB"));
		// Create the resourceSet
		ResourceSet resourceSet = new ResourceSetImpl();
		// Create an URI with neo4emf as protocol 
		URI uri = URI.createURI("neo4emf:/./data/output/ResourceSave");
		// attach this protocol to INeo4emfResourceFactory 
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("neo4emf", 
				INeo4emfResourceFactory.eINSTANCE.setRelationshipsMap(mgraph.reltypes
						.ReltypesMappings.getInstance().getMap()));
		// Create the resource
		resource = (INeo4emfResource) resourceSet.createResource(uri);
		// Register the package
		EPackage.Registry.INSTANCE.put(MgraphPackage.eINSTANCE.getNsURI(), MgraphPackage.eINSTANCE);
		
		/*MGraph mg = MgraphFactory.eINSTANCE.createMGraph();
		mg.setName("myGraph");
		MNode n1 = MgraphFactory.eINSTANCE.createMNode();
		MNode n2 = MgraphFactory.eINSTANCE.createMNode();
		n1.setName("N1");
		n1.setGraph(mg);
		n2.setName("N2");
		n2.setGraph(mg);
		MEdge edge = MgraphFactory.eINSTANCE.createMEdge();
		edge.setName("E1");
		edge.setGraph(mg);
		n1.getTo().add(edge);
		n2.getFrom().add(edge);
		resource.save();*/
	}
	
	@Test
	public void testLoadGraph() {
		try {
			resource.load(null);
			EList<EObject> resourceContents = resource.getContents();
			assert resourceContents.size() == 1 : "Wrong resource content count : found " + resource.getContents().size() + ", expected 1";
			assert resourceContents.get(0) != null : "Resource root content is null, expected MGraph instance";
			assert resourceContents.get(0).eClass().equals(MgraphPackage.Literals.MGRAPH) : "Resource root is not a MGraph instance, found " + resource.getContents().get(0).eClass().toString();
			MGraph graph = (MGraph)resourceContents.get(0);
			String graphName = graph.getName();
			assert graphName != null : "Graph name is null";
			assert graphName.equals("myGraph") : "Wrong graph name : found " + graphName + ", expected \"myGraph\"";
			// Test graph node contents
			EList<MNode> graphNodes = graph.getNodes();
			assert graphNodes != null : "Graph node list is null";
			assert graphNodes.size() == 2 : "Wrong graph node list size : found " + graphNodes.size() + ", expected 2";
			// Test node names, not order
			MNode n1 = graphNodes.get(0);
			assert n1 != null : "Node N1 is null";
			String n1Name = n1.getName();
			assert n1Name != null : "Node N1 name is null";
			assert n1Name.equals("N1") || n1Name.equals("N2") : "Wrong N1 node name : found " + n1Name + ", expected N1 or N2";
			MNode n2 = graphNodes.get(1);
			assert n2 != null : "Node N2 is null";
			String n2Name = n2.getName();
			assert n2Name != null : "Node N2 name is null";
			assert n2Name.equals("N1") || n2Name.equals("N2") : "Wrong N2 node name : found " + n2Name + ", expected N1 or N2";
			// Test graph edge contents
			EList<MEdge> graphEdges = graph.getEdges();
			assert graphEdges != null : "Graph edge list is null";
			assert graphEdges.size() == 1 : "Wrong graph edge list size : found " + graphEdges.size() + ", expected 1";
			// Test edge name
			MEdge edge = graphEdges.get(0);
			assert edge != null : "Edge is null";
			String edgeName = edge.getName();
			assert edgeName != null : "Edge name is null";
			assert edgeName.equals("E1") : "Wrong edge name : found " + edgeName + ", expected E1";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			resource.shutdown();
		}
	}
	
	@Test
	public void testLoadNode() {
		resource.getContents().clear();
		resource.getChangeLog().clear();
		try {
			resource.load(null);
			EList<EObject> resourceContents = resource.getContents();
			MGraph graph = (MGraph)resourceContents.get(0);
			EList<MNode> graphNodes = graph.getNodes();
			MNode n1 = graphNodes.get(0);
			MNode n2 = graphNodes.get(1);
			String n1Name = n1.getName();
			EList<MEdge> n1ToEdge = n1.getTo();
			EList<MEdge> n1FromEdge = n1.getFrom();
			String n2Name = n2.getName();
			EList<MEdge> n2ToEdge = n2.getTo();
			EList<MEdge> n2FromEdge = n2.getFrom();
			assert n1Name != null : "N1 name is null";
			assert n2Name != null : "N2 name is null";
			assert n1ToEdge != null : "To Edge list for N1 is null";
			assert n1FromEdge != null : "From Edge list for N1 is null";
			if(n1Name.equals("N1")) {
				assert n1ToEdge.size() == 1 : "Wrong Edge list size for N1 : found " + n1ToEdge.size() + ", expected 1";
				MEdge toEdge = n1ToEdge.get(0);
				assert toEdge != null : "To Edge for N1 is null";
				assert toEdge.getName() != null : "To Edge for N1 name is null";
				assert toEdge.getName().equals("E1");
				assert toEdge.getInComing() != null : "To Edge for N1 in coming is null";
				assert toEdge.getInComing().equals(n1) : "In Coming node for Edge E1 is not N1";
				assert n1FromEdge.size() == 0 : "From Edge list for N1 is not empty";
			}
			else if(n1Name.equals("N2")) {
				assert n1ToEdge.size() == 0 : "To Edge list for N2 is not empty";
				assert n1FromEdge.size() == 1 : "Wrong Edge list size for N2 : found " + n1FromEdge.size() + ", expected 1";
				MEdge fromEdge = n1FromEdge.get(0);
				assert fromEdge != null : "From Edge for N2 is null";
				assert fromEdge.getName() != null : "From Edge for N2 name is null";
				assert fromEdge.getName().equals("E1");
				assert fromEdge.getOutGoing() != null : "From Edge for N2 out going is null";
				assert fromEdge.getOutGoing().equals(n1) : "Out going node for Edge E1 is not N2";
			}
			// todo same for other nodes
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			resource.shutdown();
		}
	}
	
	@Test
	public void testLoadGraphReflective() {
		resource.getContents().clear();
		resource.getChangeLog().clear();
		try {
			resource.load(null);
			EList<EObject> resourceContents = resource.getContents();
			EObject graph = resourceContents.get(0);
			String graphName = (String)graph.eGet(graph.eClass().getEStructuralFeature("name"));
			assert graphName != null : "Graph name is null";
			assert graphName.equals("myGraph") : "Wrong graph name : found " + graphName + ", expected \"myGraph\"";
			// Test graph node contents
			EList<EObject> graphNodes = (EList<EObject>)graph.eGet(graph.eClass().getEStructuralFeature("nodes"));
			assert graphNodes != null : "Graph node list is null";
			assert graphNodes.size() == 2 : "Wrong graph node list size : found " + graphNodes.size() + ", expected 2";
			// Test node names, not order
			EObject n1 = graphNodes.get(0);
			assert n1 != null : "Node N1 is null";
			String n1Name = (String)n1.eGet(n1.eClass().getEStructuralFeature("name"));
			assert n1Name != null : "Node N1 name is null";
			assert n1Name.equals("N1") || n1Name.equals("N2") : "Wrong N1 node name : found " + n1Name + ", expected N1 or N2";
			EObject n2 = graphNodes.get(1);
			assert n2 != null : "Node N2 is null";
			String n2Name = (String)n2.eGet(n2.eClass().getEStructuralFeature("name"));
			assert n2Name != null : "Node N2 name is null";
			assert n2Name.equals("N1") || n2Name.equals("N2") : "Wrong N2 node name : found " + n2Name + ", expected N1 or N2";
			// Test graph edge contents
			EList<EObject> graphEdges = (EList<EObject>)graph.eGet(graph.eClass().getEStructuralFeature("edges"));
			assert graphEdges != null : "Graph edge list is null";
			assert graphEdges.size() == 1 : "Wrong graph edge list size : found " + graphEdges.size() + ", expected 1";
			// Test edge name
			EObject edge = graphEdges.get(0);
			assert edge != null : "Edge is null";
			String edgeName = (String)edge.eGet(edge.eClass().getEStructuralFeature("name"));
			assert edgeName != null : "Edge name is null";
			assert edgeName.equals("E1") : "Wrong edge name : found " + edgeName + ", expected E1";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			resource.shutdown();
		}
	}
	
	// TODO same tests with the generic API

}
