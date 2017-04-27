package graph.usage;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import graph.Edge;
import graph.Graph;
import graph.GraphFactory;
import graph.Vertice;

public class Main {

	public static void main(String[] args) throws IOException {

		PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
				BlueprintsPersistenceBackendFactory.getInstance());
		ResourceSet resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME,
				PersistentResourceFactory.getInstance());

		Resource resource = resSet.createResource(BlueprintsURI.createFileURI(new File("models/myGraph.graphdb")));

		// MapDB
		PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());

		resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME,
				PersistentResourceFactory.getInstance());
		Resource mapResource = resSet.createResource(MapDbURI.createFileURI(new File("models/model.myGraph.madb")));

		// HBase
		/*
		 * PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME,
		 * HBasePersistenceBackendFactory.getInstance());
		 * resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(
		 * HBaseURI.SCHEME, PersistentResourceFactory.getInstance()); Resource
		 * hbaseResource =
		 * resSet.createResource(HBaseURI.createHierarchicalURI("localhost",
		 * "2181", URI.createURI("myModel.hbase")));
		 */

		write(mapResource);
		read(mapResource);

	}

	public static void read(Resource resource) throws IOException {
		resource.load(null);
		Graph graph = (Graph) resource.getContents().get(0);
		for (Edge each : graph.getEdges()) {
			System.out.println(each.getFrom().getLabel() + "--->" + each.getTo().getLabel());
		}
	}

	public static void write(Resource resource) throws IOException {
		GraphFactory factory = GraphFactory.eINSTANCE;
		Graph graph = factory.createGraph();

		/*
		 * String id = EcoreUtil.getID(graph); EcoreUtil.setID(graph, "id");
		 * System.out.println(id);
		 */

		Adapter adapter = new AdapterImpl() {
			public void notifyChanged(Notification notification) {
				System.out.println("Notfication received from the data model: " + notification.getEventType());
			}
		};
		graph.eAdapters().add(adapter);
		Vertice v = factory.createVertice();
		v.eAdapters().add(adapter);
		v.setLabel("a label");

		for (int i = 0; i < 100; i++) {
			Vertice v1 = factory.createVertice();
			v1.setLabel("Vertice " + i + "a");
			Vertice v2 = factory.createVertice();
			v2.setLabel("Vertice " + i + "b");
			Edge e = factory.createEdge();
			e.setFrom(v1);
			e.setTo(v2);
			graph.getEdges().add(e);
			graph.getVertices().add(v1);
			graph.getVertices().add(v2);
		}
		resource.getContents().add(graph);
		resource.save(null);
	}

}
