/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.configuration.InternalBlueprintsConfiguration;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsStoreOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.tg.configuration.InternalBlueprintsTgConfiguration;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A factory that creates instances of {@link BlueprintsBackend}.
 * <p>
 * As other implementations of {@link PersistenceBackendFactory}, this class can create transient and persistent
 * databases. Persistent back-end creation can be configured using {@link PersistentResource#save(Map)} and {@link
 * PersistentResource#load(Map)} option maps.
 * <p>
 * The factory handles transient back-ends by creating an in-memory {@link TinkerGraph} instance. Persistent back-ends
 * are created according to the provided resource options (see {@link BlueprintsResourceOptions} and {@link
 * BlueprintsStoreOptions}). Default back-end configuration (store directory and graph type) is called dynamically
 * according to the provided Blueprints implementation {@link InternalBlueprintsTgConfiguration}.
 *
 * @see PersistentResource
 * @see BlueprintsBackend
 * @see BlueprintsOptionsBuilder
 * @see BlueprintsResourceOptions
 * @see BlueprintsStoreOptions
 */
public class BlueprintsBackendFactory extends AbstractPersistenceBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = BlueprintsBackend.NAME;

    /**
     * The configuration file name.
     * <p>
     * This file stores the metadata information about the underlying graph, i.e., graph type and other configuration
     * options.
     */
    private static final String BLUEPRINTS_CONFIG_FILE = "config.properties";

    /**
     * Constructs a new {@code BlueprintsBackendFactory}.
     */
    protected BlueprintsBackendFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static PersistenceBackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Nonnull
    @Override
    public PersistenceBackend createTransientBackend() {
        return new BlueprintsBackendIndices(new TinkerGraph());
    }

    @Nonnull
    @Override
    public BlueprintsBackend createPersistentBackend(URI uri, Map<?, ?> options) throws InvalidDataStoreException {
        BlueprintsBackend backend;
        PropertiesConfiguration configuration = null;

        checkArgument(uri.isFile(), "NeoEMF/Blueprints only supports file URIs");
        File file = FileUtils.getFile(uri.toFileString());

        try {
            configuration = getOrCreateBlueprintsConfiguration(file, options);

            try {
                Graph baseGraph = GraphFactory.open(configuration);

                if (baseGraph instanceof KeyIndexableGraph) {
                    backend = new BlueprintsBackendIndices((KeyIndexableGraph) baseGraph);
                }
                else {
                    NeoLogger.error("Graph type {0} does not support Key Indices", file.getAbsolutePath());
                    throw new InvalidDataStoreException("Graph type " + file.getAbsolutePath() + " does not support Key Indices");
                }
            }
            catch (RuntimeException e) {
                throw new InvalidDataStoreException(e);
            }
        }
        finally {
            if (nonNull(configuration)) {
                try {
                    configuration.save();
                }
                catch (ConfigurationException e) {
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

    /**
     * Creates and saves the Blueprints configuration.
     *
     * @param directory the directory where the configuration must be stored
     * @param options   options to define the behavior of Blueprints
     *
     * @return the created configuration
     *
     * @throws InvalidDataStoreException if the configuration cannot be created in the {@code directory}, or if some
     *                                   {@code options} are missing or invalid.
     */
    private PropertiesConfiguration getOrCreateBlueprintsConfiguration(File directory, Map<?, ?> options) throws InvalidDataStoreException {
        PropertiesConfiguration configuration;

        // Try to load previous configurations
        Path path = Paths.get(directory.getAbsolutePath()).resolve(BLUEPRINTS_CONFIG_FILE);
        try {
            configuration = new PropertiesConfiguration(path.toFile());
        }
        catch (ConfigurationException e) {
            throw new InvalidDataStoreException(e);
        }

        // Initialize value if the configuration file has just been created
        if (!configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            configuration.setProperty(BlueprintsResourceOptions.GRAPH_TYPE, BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        }
        else if (options.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            // The file already exists, verify that the problem options are not conflicting.
            String savedGraphType = configuration.getString(BlueprintsResourceOptions.GRAPH_TYPE);
            String issuedGraphType = options.get(BlueprintsResourceOptions.GRAPH_TYPE).toString();
            if (!Objects.equals(savedGraphType, issuedGraphType)) {
                NeoLogger.error("Unable to create graph as type {0}, expected graph type was {1})", issuedGraphType, savedGraphType);
                throw new InvalidDataStoreException("Unable to create graph as type " + issuedGraphType + ", expected graph type was " + savedGraphType + ')');
            }
        }

        // Copy the options to the configuration
        for (Entry<?, ?> e : options.entrySet()) {
            configuration.setProperty(e.getKey().toString(), e.getValue().toString());
        }

        // Check we have a valid graph type, it is needed to get the graph name
        String graphType = configuration.getString(BlueprintsResourceOptions.GRAPH_TYPE);
        if (isNull(graphType)) {
            throw new InvalidDataStoreException("Graph type is undefined for " + directory.getAbsolutePath());
        }

        // Define the configuration
        String[] segments = graphType.split("\\.");
        if (segments.length >= 2) {
            String graphName = segments[segments.length - 2];
            String upperCaseGraphName = Character.toUpperCase(graphName.charAt(0)) + graphName.substring(1);
            String configClassName = MessageFormat.format("InternalBlueprints{0}Configuration", upperCaseGraphName);
            String configClassQualifiedName = MessageFormat.format("{0}.{1}.configuration.{2}", BlueprintsBackendFactory.class.getPackage().getName(), graphName, configClassName);

            try {
                ClassLoader classLoader = BlueprintsBackendFactory.class.getClassLoader();
                Class<?> configClass = classLoader.loadClass(configClassQualifiedName);
                Method configClassInstanceMethod = configClass.getMethod("getInstance");
                InternalBlueprintsConfiguration blueprintsConfig = (InternalBlueprintsConfiguration) configClassInstanceMethod.invoke(configClass);
                blueprintsConfig.putDefaultConfiguration(configuration, directory);
            }
            catch (ClassNotFoundException e) {
                NeoLogger.warn(e, "Unable to find the configuration class {0}", configClassQualifiedName);
            }
            catch (NoSuchMethodException e) {
                NeoLogger.warn(e, "Unable to find configuration methods in class {0}", configClassName);
            }
            catch (InvocationTargetException | IllegalAccessException e) {
                NeoLogger.warn(e, "An error occurs during the execution of a configuration method");
            }
        }
        else {
            NeoLogger.warn("Unable to compute graph type name from {0}", graphType);
        }

        return configuration;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceBackendFactory INSTANCE = new BlueprintsBackendFactory();
    }
}
