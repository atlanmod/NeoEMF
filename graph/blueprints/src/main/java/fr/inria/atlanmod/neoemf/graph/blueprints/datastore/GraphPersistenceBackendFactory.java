/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.graph.blueprints.datastore;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.GraphHelper;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteGraphResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

public class GraphPersistenceBackendFactory extends
		AbstractPersistenceBackendFactory {

	/**
	 * The configuration file name. This file stores the metadata information
	 * about the underlying graph, i.e., graph type and other configuration
	 * options
	 */
	protected static final String CONFIG_FILE = "config.properties";

	@Override
	public PersistenceBackend createTransientBackend() {
		return new GraphPersistenceBackend(new TinkerGraph());
	}
	
	@Override
	public SearcheableResourceEStore createTransientEStore(
			PersistentResource resource, PersistenceBackend backend) {
		assert backend instanceof GraphPersistenceBackend : "Trying to create a Graph-based EStore with an invalid backend";
		return new DirectWriteGraphResourceEStoreImpl(resource, (GraphPersistenceBackend)backend);
	}
	
	@Override
	public GraphPersistenceBackend createPersistentBackend(File file, Map<?, ?> options) {
		GraphPersistenceBackend graphDB = null;
		PropertiesConfiguration configuration = null;
		String directoryProperty = null;
		try {
			// Try to load previous configurations
			Path path = Paths.get(file.getAbsolutePath()).resolve(CONFIG_FILE);
			try {
				configuration = new PropertiesConfiguration(path.toFile());
			} catch (ConfigurationException e) {
				//throw new InvalidDataStoreException(e);
				// TODO handle that properly
				e.printStackTrace();
			}
			// Initialize value if the config file has just been created
			if (!configuration.containsKey(PersistentResource.OPTIONS_GRAPH_TYPE)) {
				configuration.setProperty(PersistentResource.OPTIONS_GRAPH_TYPE, PersistentResource.OPTIONS_GRAPH_TYPE_DEFAULT);
			} else if (options.containsKey(PersistentResource.OPTIONS_GRAPH_TYPE)) {
				// The file already existed, check that the issued options
				// are not conflictive
				String savedGraphType = configuration.getString(PersistentResource.OPTIONS_GRAPH_TYPE);
				String issuedGraphType = options.get(PersistentResource.OPTIONS_GRAPH_TYPE).toString();
				if (!savedGraphType.equals(issuedGraphType)) {
					// TODO handle that properly
					System.out.println("Unable to create graph as type " + issuedGraphType + ", expected graph type was " + savedGraphType + ")");
//					throw new InvalidDataStoreException("Unable to create graph as type " + issuedGraphType + ", expected graph type was " + savedGraphType + ")");
				}
			}

			// Copy the options to the configuration
			for (Entry<?, ?> e : options.entrySet()) {
				configuration.setProperty(e.getKey().toString(), e.getValue().toString());
			}

			// Check we have a valid graph type, it is needed to get the
			// graph name
			String graphType = configuration.getString(PersistentResource.OPTIONS_GRAPH_TYPE);
			if (graphType == null) {
//				// TODO handle that properly
				System.out.println("Graph type is undefined for " + file.getAbsolutePath());
//				throw new InvalidDataStoreException("Graph type is undefined for " + file.getAbsolutePath());
			}

			// Calculate the graph name, it is needed for the directory
			// property
			String[] segments = graphType.split("\\.");
			// TODO: Add sanity check for array access
			String graphName = segments[segments.length - 2];
			directoryProperty = MessageFormat.format("blueprints.{0}.directory", graphName);
			configuration.setProperty(directoryProperty, file.getAbsolutePath());

			Graph baseGraph = GraphFactory.open(configuration);
			if (baseGraph instanceof KeyIndexableGraph) {
				graphDB = new GraphPersistenceBackend((KeyIndexableGraph) baseGraph);
			} else {
				// TODO handle that properly
//				throw new InvalidDataStoreException("Graph type "+file.getAbsolutePath()+" does not support Key Indices");
			}
		} finally {
			// Delete the directory property, it is no longer needed
			if (configuration != null && directoryProperty != null) {
				configuration.clearProperty(directoryProperty);
				try {
					// And save the properties together with the DB
					configuration.save();
				} catch (ConfigurationException e) {
					// Unable to save configuration, supposedly it's a minor error,
					// so we log it without rising an exception
					NeoLogger.log(NeoLogger.SEVERITY_ERROR, e);
				}
			}
		}
		return graphDB;
	}
	
	@Override
	public SearcheableResourceEStore createPersistentEStore(
			PersistentResource resource, PersistenceBackend backend) {
		assert backend instanceof GraphPersistenceBackend : "Trying to create a Graph-based EStore with an invalid backend";
		return new DirectWriteGraphResourceEStoreImpl(resource, (GraphPersistenceBackend)backend);
	}
	
	@Override
	public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
		assert from instanceof GraphPersistenceBackend && to instanceof GraphPersistenceBackend : "Trying to use Graph backend copy on non Graph databases";
		GraphHelper.copyGraph((GraphPersistenceBackend)from, (GraphPersistenceBackend)to);
	}
}
