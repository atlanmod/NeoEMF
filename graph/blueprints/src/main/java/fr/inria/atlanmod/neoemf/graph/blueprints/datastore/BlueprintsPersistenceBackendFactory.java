/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.datastore;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.GraphHelper;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AutocommitEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.config.InternalBlueprintsConfiguration;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.CachedManyDirectWriteBlueprintsRespirceEStoreImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions.*;
import static fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions.EStoreGraphOption.*;
import static java.util.Objects.isNull;

public final class BlueprintsPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

	private static PersistenceBackendFactory INSTANCE;

	public static PersistenceBackendFactory getInstance() {
		if (isNull(INSTANCE)) {
			INSTANCE = new BlueprintsPersistenceBackendFactory();
		}
		return INSTANCE;
	}

	/**
	 * The configuration file name.
	 * <p/>
	 * This file stores the metadata information about the underlying graph, i.e., graph type and other configuration
	 * options.
	 */
	private static final String BLUEPRINTS_CONFIG_FILE = "config.properties";

	public static final String BLUEPRINTS_BACKEND = "blueprints";

	private BlueprintsPersistenceBackendFactory() {
	}

	@Override
	public String getName() {
		return BLUEPRINTS_BACKEND;
	}

	@Override
	public PersistenceBackend createTransientBackend() {
		return new BlueprintsPersistenceBackend(new TinkerGraph());
	}
	
	@Override
	public PersistentEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend) {
		checkArgument(backend instanceof BlueprintsPersistenceBackend,
					  "Trying to create a Graph-based EStore with an invalid backend");

		return new DirectWriteBlueprintsResourceEStoreImpl(resource, (BlueprintsPersistenceBackend)backend);
	}
	
	@Override
	public BlueprintsPersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
		BlueprintsPersistenceBackend backend;
		PropertiesConfiguration configuration = null;

		try {
			configuration = getOrCreateBlueprintsConfiguration(file, options);

			try {
				Graph baseGraph = GraphFactory.open(configuration);

				if (baseGraph instanceof KeyIndexableGraph) {
					backend = new BlueprintsPersistenceBackend((KeyIndexableGraph) baseGraph);
				}
				else {
					NeoLogger.error("Graph type {0} does not support Key Indices", file.getAbsolutePath());
					throw new InvalidDataStoreException("Graph type " + file.getAbsolutePath() + " does not support Key Indices");
				}
			}
			catch (RuntimeException e) {
				throw new InvalidDataStoreException(e);
			}
		} finally {
			if (!isNull(configuration)) {
				try {
					configuration.save();
				} catch (ConfigurationException e) {
					/*
			 		 * Unable to save configuration.
					 * Supposedly it's a minor error, so we log it without rising an exception.
					 */
					NeoLogger.warn(e);
				}
			}
		}

		processGlobalConfiguration(file);

		return backend;
	}
	
	@Override
	protected PersistentEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException {
		checkArgument(backend instanceof BlueprintsPersistenceBackend,
				"Trying to create a Graph-based EStore with an invalid backend");

		PersistentEStore eStore;
		List<PersistentResourceOptions.StoreOption> storeOptions = storeOptionsFrom(options);

		// Store
		if(isNull(storeOptions) || storeOptions.isEmpty()) {
			eStore = new DirectWriteBlueprintsResourceEStoreImpl(resource, (BlueprintsPersistenceBackend)backend);
    	}
		else {
		    if(storeOptions.contains(DIRECT_WRITE)) {
		        eStore = new DirectWriteBlueprintsResourceEStoreImpl(resource, (BlueprintsPersistenceBackend)backend);
		    }
		    else if(storeOptions.contains(MANY_CACHE)) {
		        eStore = new CachedManyDirectWriteBlueprintsRespirceEStoreImpl(resource, (BlueprintsPersistenceBackend)backend);
		    }
		    else {
		        throw new InvalidDataStoreException("No valid base EStore found in the options");
		    }
		}
		// Autocommit
		if(!isNull(storeOptions) && storeOptions.contains(AUTOCOMMIT)) {
		    if(options.containsKey(OPTIONS_BLUEPRINTS_AUTOCOMMIT_CHUNK)) {
		        int autoCommitChunk = Integer.parseInt((String)options.get(OPTIONS_BLUEPRINTS_AUTOCOMMIT_CHUNK));
		        eStore = new AutocommitEStoreDecorator(eStore, autoCommitChunk);
		    }
		    else {
		        eStore =  new AutocommitEStoreDecorator(eStore);
		    }
		}
		return eStore;
	}
	
	@Override
	public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
		checkArgument(from instanceof BlueprintsPersistenceBackend && to instanceof BlueprintsPersistenceBackend,
				"Trying to use Graph backend copy on non Graph databases");

		BlueprintsPersistenceBackend source = (BlueprintsPersistenceBackend) from;
		BlueprintsPersistenceBackend target = (BlueprintsPersistenceBackend) to;

	    GraphHelper.copyGraph(source, target);
	    target.initMetaClassesIndex(source.getIndexedEClasses());
	}

	private PropertiesConfiguration getOrCreateBlueprintsConfiguration(File directory, Map<?, ?> options) throws InvalidDataStoreException {
		PropertiesConfiguration configuration;

		// Try to load previous configurations
		Path path = Paths.get(directory.getAbsolutePath()).resolve(BLUEPRINTS_CONFIG_FILE);
		try {
			configuration = new PropertiesConfiguration(path.toFile());
		} catch (ConfigurationException e) {
			throw new InvalidDataStoreException(e);
		}

		// Initialize value if the config file has just been created
		if (!configuration.containsKey(OPTIONS_BLUEPRINTS_GRAPH_TYPE)) {
			configuration.setProperty(OPTIONS_BLUEPRINTS_GRAPH_TYPE, OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT);
		} else if (options.containsKey(OPTIONS_BLUEPRINTS_GRAPH_TYPE)) {
			// The file already existed, check that the issued options are not conflictive
			String savedGraphType = configuration.getString(OPTIONS_BLUEPRINTS_GRAPH_TYPE);
			String issuedGraphType = options.get(OPTIONS_BLUEPRINTS_GRAPH_TYPE).toString();
			if (!savedGraphType.equals(issuedGraphType)) {
				NeoLogger.error("Unable to create graph as type {0}, expected graph type was {1})", issuedGraphType, savedGraphType);
				throw new InvalidDataStoreException("Unable to create graph as type " + issuedGraphType + ", expected graph type was " + savedGraphType + ')');
			}
		}

		// Copy the options to the configuration
		for (Entry<?, ?> e : options.entrySet()) {
			configuration.setProperty(e.getKey().toString(), e.getValue().toString());
		}

		// Check we have a valid graph type, it is needed to get the graph name
		String graphType = configuration.getString(OPTIONS_BLUEPRINTS_GRAPH_TYPE);
		if (isNull(graphType)) {
			throw new InvalidDataStoreException("Graph type is undefined for " + directory.getAbsolutePath());
		}

		// Define the configuration
		String[] segments = graphType.split("\\.");
		if(segments.length >= 2) {
			String graphName = segments[segments.length - 2];
			String upperCaseGraphName = Character.toUpperCase(graphName.charAt(0)) + graphName.substring(1);
			String configClassName = MessageFormat.format("InternalBlueprints{0}Configuration", upperCaseGraphName);
			String configClassQualifiedName = MessageFormat.format("fr.inria.atlanmod.neoemf.graph.blueprints.{0}.config.{1}", graphName, configClassName);

			try {
				ClassLoader classLoader = BlueprintsPersistenceBackendFactory.class.getClassLoader();
				Class<?> configClass = classLoader.loadClass(configClassQualifiedName);
				Method configClassInstanceMethod = configClass.getMethod("getInstance");
				InternalBlueprintsConfiguration blueprintsConfig = (InternalBlueprintsConfiguration) configClassInstanceMethod.invoke(configClass);
				blueprintsConfig.putDefaultConfiguration(configuration, directory);
				blueprintsConfig.setGlobalSettings();
			} catch (ClassNotFoundException e) {
				NeoLogger.warn(e, "Unable to find the configuration class {0}", configClassQualifiedName);
			} catch (NoSuchMethodException e) {
				NeoLogger.warn(e, "Unable to find configuration methods in class {0}", configClassName);
			} catch (InvocationTargetException | IllegalAccessException e) {
				NeoLogger.warn(e, "An error occurs during the execution of a configuration method");
			}
		}
		else {
			NeoLogger.warn("Unable to compute graph type name from {0}", graphType);
		}

		return configuration;
	}
}
