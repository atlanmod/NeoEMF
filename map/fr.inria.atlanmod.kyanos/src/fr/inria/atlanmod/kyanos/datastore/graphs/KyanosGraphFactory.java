/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.graphs;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.osgi.util.NLS;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.GraphHelper;

import fr.inria.atlanmod.kyanos.core.KyanosResource;
import fr.inria.atlanmod.kyanos.datastore.exceptions.InvalidDataStoreException;
import fr.inria.atlanmod.kyanos.logger.Logger;

public class KyanosGraphFactory {

	/**
	 * The configuration file name. This file stores the metadata information
	 * about the underlying graph, i.e., graph type and other configuration
	 * options
	 */
	protected static final String CONFIG_FILE = "config.properties";

	public static KyanosGraph createPersistenGraph(File file, Map<?, ?> options) throws InvalidDataStoreException {
		KyanosGraph graphDB = null;
		PropertiesConfiguration configuration = null;
		String directoryProperty = null;
		try {
			// Try to load previous configurations
			Path path = Paths.get(file.getAbsolutePath()).resolve(CONFIG_FILE);
			try {
				configuration = new PropertiesConfiguration(path.toFile());
			} catch (ConfigurationException e) {
				throw new InvalidDataStoreException(e);
			}
			// Initialize value if the config file has just been created
			if (!configuration.containsKey(KyanosResource.OPTIONS_GRAPH_TYPE)) {
				configuration.setProperty(KyanosResource.OPTIONS_GRAPH_TYPE, KyanosResource.OPTIONS_GRAPH_TYPE_DEFAULT);
			} else if (options.containsKey(KyanosResource.OPTIONS_GRAPH_TYPE)) {
				// The file already existed, check that the issued options
				// are not conflictive
				String savedGraphType = configuration.getString(KyanosResource.OPTIONS_GRAPH_TYPE);
				String issuedGraphType = options.get(KyanosResource.OPTIONS_GRAPH_TYPE).toString();
				if (!savedGraphType.equals(issuedGraphType)) {
					throw new InvalidDataStoreException(NLS.bind("Unable to create graph as type {0}, expected graph type was {1})", issuedGraphType,
							savedGraphType));
				}
			}

			// Copy the options to the configuration
			for (Entry<?, ?> e : options.entrySet()) {
				configuration.setProperty(e.getKey().toString(), e.getValue().toString());
			}

			// Check we have a valid graph type, it is needed to get the
			// graph name
			String graphType = configuration.getString(KyanosResource.OPTIONS_GRAPH_TYPE);
			if (graphType == null) {
				throw new InvalidDataStoreException(NLS.bind("Graph type is undefined for {0}", file.getAbsolutePath()));
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
				graphDB = new KyanosGraph((KeyIndexableGraph) baseGraph);
			} else {
				throw new InvalidDataStoreException(NLS.bind("Graph type {0} does not support Key Indices", file.getAbsolutePath()));
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
					Logger.log(Logger.SEVERITY_ERROR, e);
				}
			}
		}
		return graphDB;
	}
	
	public static KyanosGraph createTransientGraph() {
		return new KyanosGraph(new TinkerGraph());
	}
	
	public static void copyGraph(Graph from, Graph to) {
		GraphHelper.copyGraph(from, to);
	}
}
