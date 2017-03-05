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
import fr.inria.atlanmod.neoemf.data.PersistenceConfiguration;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.tg.BlueprintsTgConfiguration;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.Log;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A factory that creates instances of {@link BlueprintsBackend}.
 * <p>
 * As other implementations of {@link PersistenceBackendFactory}, this class can create transient and persistent
 * databases. Persistent back-end creation can be configured using {@link PersistentResource#save(Map)} and {@link
 * PersistentResource#load(Map)} option maps.
 * <p>
 * The factory handles transient back-ends by creating an in-memory {@link TinkerGraph} instance. Persistent back-ends
 * are created according to the provided resource options (see {@link BlueprintsResourceOptions}. Default back-end
 * configuration (store directory and graph type) is called dynamically according to the provided Blueprints
 * implementation {@link BlueprintsTgConfiguration}.
 *
 * @see PersistentResource
 * @see BlueprintsBackend
 * @see BlueprintsOptionsBuilder
 * @see BlueprintsResourceOptions
 */
@ParametersAreNonnullByDefault
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

        checkArgument(uri.isFile(), "NeoEMF/Blueprints only supports file URIs");
        File file = new File(uri.toFileString());

        PersistenceConfiguration configuration = getOrCreateBlueprintsConfiguration(file, options);

        try {
            Graph graph = GraphFactory.open(configuration.asMap());

            if (graph.getFeatures().supportsKeyIndices) {
                backend = new BlueprintsBackendIndices((KeyIndexableGraph) graph);
            }
            else {
                throw new InvalidDataStoreException("Graph type " + file.getAbsolutePath() + " does not support key indices");
            }
        }
        catch (RuntimeException e) {
            throw new InvalidDataStoreException(e);
        }
        finally {
            configuration.save();
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
    private PersistenceConfiguration getOrCreateBlueprintsConfiguration(File directory, Map<?, ?> options) throws InvalidDataStoreException {
        Path path = Paths.get(directory.getAbsolutePath()).resolve(BLUEPRINTS_CONFIG_FILE);
        PersistenceConfiguration configuration = PersistenceConfiguration.load(path.toFile());

        // Initialize value if the configuration file has just been created
        if (!configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            configuration.setProperty(BlueprintsResourceOptions.GRAPH_TYPE, BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        }
        else if (options.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            // The file already exists, verify that the problem options are not conflicting.
            String savedGraphType = configuration.getProperty(BlueprintsResourceOptions.GRAPH_TYPE).toString();
            String issuedGraphType = options.get(BlueprintsResourceOptions.GRAPH_TYPE).toString();
            if (!Objects.equals(savedGraphType, issuedGraphType)) {
                throw new InvalidDataStoreException(String.format("Unable to create graph as type %s , expected graph type was %s)", issuedGraphType, savedGraphType));
            }
        }

        // Copy the Blueprints options to the configuration
        for (Map.Entry<?, ?> e : options.entrySet()) {
            if (e.getKey().toString().startsWith("blueprints.")) {
                configuration.setProperty(e.getKey().toString(), e.getValue().toString());
            }
        }

        // Check we have a valid graph type, it is needed to get the graph name
        if (!configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            throw new InvalidDataStoreException(String.format("Graph type is undefined for %s", directory.getAbsolutePath()));
        }
        String graphType = configuration.getProperty(BlueprintsResourceOptions.GRAPH_TYPE).toString();

        // Define the configuration
        String[] segments = graphType.split("\\.");
        if (segments.length >= 2) {
            String graphName = segments[segments.length - 2];
            String upperCaseGraphName = Character.toUpperCase(graphName.charAt(0)) + graphName.substring(1);
            String configClassName = String.format("Blueprints%sConfiguration", upperCaseGraphName);
            String configClassQualifiedName = String.format("%s.%s.%s", BlueprintsBackendFactory.class.getPackage().getName(), graphName, configClassName);

            try {
                ClassLoader classLoader = BlueprintsBackendFactory.class.getClassLoader();
                Class<?> configClass = classLoader.loadClass(configClassQualifiedName);
                Method configClassInstanceMethod = configClass.getMethod("getInstance");
                BlueprintsConfiguration blueprintsConfig = (BlueprintsConfiguration) configClassInstanceMethod.invoke(configClass);
                blueprintsConfig.putDefaultConfiguration(configuration, directory);
            }
            catch (ClassNotFoundException e) {
                Log.warn(e, "Unable to find the configuration class {0}", configClassQualifiedName);
            }
            catch (NoSuchMethodException e) {
                Log.warn(e, "Unable to find configuration methods in class {0}", configClassName);
            }
            catch (InvocationTargetException | IllegalAccessException e) {
                Log.warn(e, "An error occurs during the execution of a configuration method");
            }
        }
        else {
            Log.warn("Unable to compute graph type name from {0}", graphType);
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
