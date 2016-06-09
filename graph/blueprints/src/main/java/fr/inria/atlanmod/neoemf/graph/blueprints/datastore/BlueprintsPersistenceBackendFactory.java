/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
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
import fr.inria.atlanmod.neoemf.datastore.estores.DirectWriteResourceEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AutocommitEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions.EStoreGraphOption;
import fr.inria.atlanmod.neoemf.graph.blueprints.tg.config.AbstractBlueprintsConfig;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkArgument;

public class BlueprintsPersistenceBackendFactory extends AbstractPersistenceBackendFactory {

	/**
	 * The configuration file name. This file stores the metadata information
	 * about the underlying graph, i.e., graph type and other configuration
	 * options.
	 */
	private static final String CONFIG_FILE = "config.properties";
	public static final String BLUEPRINTS_BACKEND = "blueprints";

	private static final String GRAPH_SEPERATOR = "\\.";

	@Override
	public PersistenceBackend createTransientBackend() {
		return new BlueprintsPersistenceBackend(new TinkerGraph());
	}
	
	@Override
	public SearcheableResourceEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend) {
		checkArgument(backend instanceof BlueprintsPersistenceBackend,
				"Trying to create a Graph-based EStore with an invalid backend");

		return new DirectWriteBlueprintsResourceEStoreImpl(resource, (BlueprintsPersistenceBackend)backend);
	}
	
	@Override
	public BlueprintsPersistenceBackend createPersistentBackend(File file, Map<?, ?> options) throws InvalidDataStoreException {
		BlueprintsPersistenceBackend graphDb = null;
		PropertiesConfiguration neoConfig = null;
		PropertiesConfiguration configuration = null;
		try {
			// Try to load previous configurations
			Path path = Paths.get(file.getAbsolutePath()).resolve(CONFIG_FILE);
			try {
				configuration = new PropertiesConfiguration(path.toFile());
			} catch (ConfigurationException e) {
				throw new InvalidDataStoreException(e);
			}
			// Initialize value if the config file has just been created
			if (!configuration.containsKey(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE)) {
				configuration.setProperty(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT);
			} else if (options.containsKey(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE)) {
				// The file already existed, check that the issued options
				// are not conflictive
				String savedGraphType = configuration.getString(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE);
				String issuedGraphType = options.get(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE).toString();
				if (!savedGraphType.equals(issuedGraphType)) {
				    NeoLogger.error("Unable to create graph as type {0}, expected graph type was {1})", issuedGraphType, savedGraphType);
					throw new InvalidDataStoreException("Unable to create graph as type " + issuedGraphType + ", expected graph type was " + savedGraphType + ')');
				}
			}

			// Copy the options to the configuration
			for (Entry<?, ?> e : options.entrySet()) {
				configuration.setProperty(e.getKey().toString(), e.getValue().toString());
			}

			// Check we have a valid graph type, it is needed to get the
			// graph name
			String graphType = configuration.getString(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE);
			if (graphType == null) {
				throw new InvalidDataStoreException("Graph type is undefined for " + file.getAbsolutePath());
			}
			
			String[] segments = graphType.split(GRAPH_SEPERATOR);
			if(segments.length >= 2) {
    			String graphName = segments[segments.length - 2];
    			String upperCaseGraphName = Character.toUpperCase(graphName.charAt(0))+graphName.substring(1);
    			String configClassQualifiedName = MessageFormat.format("fr.inria.atlanmod.neoemf.graph.blueprints.{0}.config.Blueprints{1}Config", graphName, upperCaseGraphName);
    			try {
                    ClassLoader classLoader = BlueprintsPersistenceBackendFactory.class
                            .getClassLoader();
                    Class<?> configClass = classLoader.loadClass(configClassQualifiedName);
                    Field configClassInstanceField = configClass.getField("eINSTANCE");
                    AbstractBlueprintsConfig configClassInstance = (AbstractBlueprintsConfig) configClassInstanceField
                            .get(configClass);
                    Method configMethod = configClass.getMethod("putDefaultConfiguration",
                            Configuration.class, File.class);
                    configMethod.invoke(configClassInstance, configuration, file);
                    Method setGlobalSettingsMethod = configClass.getMethod("setGlobalSettings");
                    setGlobalSettingsMethod.invoke(configClassInstance);
                } catch (ClassNotFoundException e1) {
                    NeoLogger.warn("Unable to find the configuration class {0}", configClassQualifiedName);
                    e1.printStackTrace();
                } catch (NoSuchFieldException e2) {
                    NeoLogger.warn("Unable to find the static field eINSTANCE in class Blueprints{0}Config", upperCaseGraphName);
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    NeoLogger.error("Unable to find configuration methods in class Blueprints{0}Config", upperCaseGraphName);
                    e3.printStackTrace();
                } catch (InvocationTargetException | IllegalAccessException e4) {
                    NeoLogger.error("An error occurs during the execution of a configuration method", upperCaseGraphName);
                    e4.printStackTrace();
                }
            }
			else {
			    NeoLogger.warn("Unable to compute graph type name from {0}", graphType);
			}

			Graph baseGraph;
			try {
			    baseGraph = GraphFactory.open(configuration);
			}catch(RuntimeException e) {
			    throw new InvalidDataStoreException(e);
			}
			if (baseGraph instanceof KeyIndexableGraph) {
				graphDb = new BlueprintsPersistenceBackend((KeyIndexableGraph) baseGraph);
			} else {
			    NeoLogger.error("Graph type {0} does not support Key Indices", file.getAbsolutePath());
				throw new InvalidDataStoreException("Graph type "+file.getAbsolutePath()+" does not support Key Indices");
			}
			// Save the neoconfig file
			Path neoConfigPath = Paths.get(file.getAbsolutePath()).resolve(NEO_CONFIG_FILE);
            try {
                neoConfig= new PropertiesConfiguration(neoConfigPath.toFile());
            } catch (ConfigurationException e) {
                throw new InvalidDataStoreException(e);
            }
            if (!neoConfig.containsKey(BACKEND_PROPERTY)) {
                neoConfig.setProperty(BACKEND_PROPERTY, BLUEPRINTS_BACKEND);
            }
		} finally {
			if (configuration != null) {
				try {
					configuration.save();
				} catch (ConfigurationException e) {
					// Unable to save configuration, supposedly it's a minor error,
					// so we log it without rising an exception
					NeoLogger.error(e);
				}
			}
			if(neoConfig != null) {
			    try {
			        neoConfig.save();
			    } catch(ConfigurationException e) {
			        NeoLogger.error(e);
			    }
			}
		}
		return graphDb;
	}
	
	@Override
	protected SearcheableResourceEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException {
		checkArgument(backend instanceof BlueprintsPersistenceBackend,
				"Trying to create a Graph-based EStore with an invalid backend");

		DirectWriteResourceEStore eStore = null;
		@SuppressWarnings("unchecked")
		List<PersistentResourceOptions.StoreOption> storeOptions = (List<PersistentResourceOptions.StoreOption>)options.get(PersistentResourceOptions.STORE_OPTIONS);
		// Store
		if(storeOptions == null || storeOptions.isEmpty() || storeOptions.contains(EStoreGraphOption.DIRECT_WRITE) || (storeOptions.size() == 1 && storeOptions.contains(EStoreGraphOption.AUTOCOMMIT))) {
			eStore = new DirectWriteBlueprintsResourceEStoreImpl(resource, (BlueprintsPersistenceBackend)backend);
    	}
		// Autocommit
    	if(eStore != null) {
    	    if(storeOptions != null && storeOptions.contains(EStoreGraphOption.AUTOCOMMIT)) {
    	        if(options.containsKey(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_AUTOCOMMIT_CHUNK)) {
    	            int autoCommitChunk = Integer.parseInt((String)options.get(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_AUTOCOMMIT_CHUNK));
					eStore = new AutocommitEStoreImpl(eStore, autoCommitChunk);
    	        }
    	        else {
					eStore = new AutocommitEStoreImpl(eStore);
    	        }
    	    }
    	} else {
	        throw new InvalidDataStoreException();
    	}
		return eStore;
	}
	
	@Override
	public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
		checkArgument(from instanceof BlueprintsPersistenceBackend,
				"Trying to use Graph backend copy on non Graph databases");
		checkArgument(to instanceof BlueprintsPersistenceBackend,
				"Trying to use Graph backend copy on non Graph databases");

		BlueprintsPersistenceBackend bFrom = (BlueprintsPersistenceBackend)from;
		BlueprintsPersistenceBackend bTo = (BlueprintsPersistenceBackend)to;
	    GraphHelper.copyGraph(bFrom, bTo);
	    bTo.initMetaClassesIndex(bFrom.getIndexedEClasses());
	}
}
