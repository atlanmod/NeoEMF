package main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphFactory;
import mgraph.MgraphPackage;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.NESession;

public class Test {

	private static final File DB_FOLDER = new File("/tmp/Test/ResourceSave");
	
	public static void main(String[] args) throws Exception {
		if(DB_FOLDER.isDirectory()) {
			FileUtils.forceDelete(DB_FOLDER);
		}
		FileUtils.forceMkdir(DB_FOLDER);
		NESession session = new NESession(MgraphPackage.eINSTANCE);
		INeo4emfResource resource = session.createResource(URI.createURI("neo4emf:"+DB_FOLDER.getAbsolutePath()), 1000);
		MgraphFactory factory = MgraphFactory.eINSTANCE;
		
		MGraph graph = factory.createMGraph();
		graph.setName("graph");
		MNode node = factory.createMNode();
		node.setName("node");
		
		graph.getNodes().add(node);
		resource.getContents().add(graph);
		
		MEdge notInResourceEdge = factory.createMEdge();
		notInResourceEdge.setName("notInResourceEdge");
		node.getTo().add(notInResourceEdge);
		
		tmpSave(resource);
		
		Common.clearAllSoftReferences();
	}
	
	private static void tmpSave(INeo4emfResource resource) {
		Map<String,Object> options = new HashMap<String,Object>();
		options.put("tmp_save", true);
		try {
			resource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
