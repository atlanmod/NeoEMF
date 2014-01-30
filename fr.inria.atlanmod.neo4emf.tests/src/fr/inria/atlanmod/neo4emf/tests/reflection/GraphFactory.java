package fr.inria.atlanmod.neo4emf.tests.reflection;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphFactory;

public class GraphFactory {

	private MgraphFactory factory;
	private int graphCount;
	private int nodeCount;
	private int edgeCount;
	
	private MGraph[] graphs;
	private MNode[] nodes;
	private MEdge[] edges;
	
	public GraphFactory(int graphCount, int nodeCount, int edgeCount, MgraphFactory factory) {
		this.graphCount = graphCount;
		this.nodeCount = nodeCount;
		this.edgeCount = edgeCount;
		this.factory = factory;
		createMGraph();
	}
	
	private void createMGraph() {
		graphs = new MGraph[graphCount];
		for(int i = 0; i < graphCount; i++) {
			graphs[i] = factory.createMGraph();
			graphs[i].setName("MyGraph");
			createNodes(graphs[i]);
			createEdges(graphs[i]);
			//neo4emfRoot.getContents().add(graphs[i]);
		}
	}
	
	private void createNodes(MGraph inGraph) {
		nodes = new MNode[nodeCount];
		for(int i = 0; i < nodeCount; i++) {
			MNode node = factory.createMNode();
			node.setName("Node"+i);
			node.setGraph(inGraph);
			nodes[i] = node;
		}
	}
	
	private void createEdges(MGraph inGraph) {
		edges = new MEdge[edgeCount];
		for(int i = 0; i < edgeCount; i++) {
			MEdge edge = factory.createMEdge();
			edge.setName("Edge"+i);
			edge.setGraph(inGraph);
			// Compute in node
			edge.setInComing(nodes[(int)(Math.random()*nodeCount)]);
			// Compute out node (may be a loop)
			edge.setOutGoing(nodes[(int)(Math.random()*nodeCount)]);
			edges[i] = edge;
		}
	}

	public MGraph[] getGraphs() {
		return graphs;
	}
	
	public MNode[] getNodes() {
		return nodes;
	}
	
	public MEdge[] getEdges() {
		return edges;
	}
	
}
