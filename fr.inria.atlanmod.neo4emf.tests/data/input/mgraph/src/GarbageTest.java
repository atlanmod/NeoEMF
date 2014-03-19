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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.RemoveLink;
import fr.inria.atlanmod.neo4emf.drivers.NESession;
import fr.inria.atlanmod.neo4emf.resourceUtil.Neo4emfResourceUtil;


public class GarbageTest {
	
	private static MgraphFactory factory;

	private static void deleteDirectoryIfExists(final File file)
	{ 
		if (! file.exists()) return;
		if (file.isDirectory()) {
			for (File child : file.listFiles())
				deleteDirectoryIfExists(child);
		}
		else file.delete();
	}
	
	private static void clearSoftReferences() {
		try {
		    Object[] ignored = new Object[(int) Runtime.getRuntime().maxMemory()];
		} catch (Throwable e) {
		    // Ignore OME
		}
	}
	
	public static void save(INeo4emfResource resource) {
		try {
			resource.save(null);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		deleteDirectoryIfExists(new File("./TestGarbage"));
		URI uri = URI.createURI("neo4emf:./TestGarbage");
		// NESession initialization (simpler than previous one)
		NESession session = new NESession(MgraphPackage.eINSTANCE);
		INeo4emfResource resource = session.createResource(uri,1000);
		
		factory = MgraphFactory.eINSTANCE;
		
		MGraph graph = factory.createMGraph();
		graph.setName("graph");
		MNode node1 = factory.createMNode();
		node1.setName("n1");
		graph.getNodes().add(node1);
		MNode node2 = factory.createMNode();
		node2.setName("n2");
		MNode node3 = factory.createMNode();
		node3.setName("n3");
		node3.setGraph(graph);
		
		resource.getContents().add(graph);
		
		EcoreUtil.delete(node3);
		
		MEdge edge1 = factory.createMEdge();
		edge1.setName("edge1");
		edge1.setGraph(graph);
		edge1.setInComing(node1);
		edge1.setOutGoing(node1);
		
		
		graph.getNodes().add(node2);
		MEdge testEdge = graph.getEdges().get(0);
		
		// Check the different way node2 is accessible and check if
		// it is the same Object each time
		assert testEdge.getInComing() == graph.getEdges().get(0).getInComing();
		assert testEdge.getInComing() == edge1.getInComing();
		assert graph.getEdges().get(0).getInComing() == edge1.getInComing();
		
		EcoreUtil.delete(edge1);
		
		clearSoftReferences();
		
		
		MNode node4 = factory.createMNode();
		node4.setName("node4");
		node4.setGraph(graph);
		
		clearSoftReferences();
		System.out.println("done");
		// Remove the last strong reference to node4 and force the gc to
		// collect all the SoftReferences.
		node4 = null;
		clearSoftReferences();
		
		// Create a new element to show the weak proxy map. The map should not contain
		// node4 (previously removed by gc).
		System.out.println("test");
		MEdge showProxyEdge = factory.createMEdge();
		showProxyEdge.setName("showProxyMap");
		showProxyEdge.setGraph(graph);
		
		save(resource);
	}
}
