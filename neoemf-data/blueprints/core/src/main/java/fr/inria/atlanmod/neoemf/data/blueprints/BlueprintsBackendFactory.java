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
import com.tinkerpop.blueprints.util.wrappers.readonly.ReadOnlyKeyIndexableGraph;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.Configuration;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.tg.BlueprintsTgConfiguration;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;

/**
 * A factory that creates {@link BlueprintsBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent
 * databases. Persistent back-end creation can be configured using {@link PersistentResource#save(Map)} and {@link
 * PersistentResource#load(Map)} option maps.
 * <p>
 * The factory handles transient back-ends by creating an in-memory {@code TinkerGraph} instance. Persistent back-ends
 * are created according to the provided resource options (see {@link BlueprintsResourceOptions}. Default back-end
 * configuration (store directory and graph type) is called dynamically according to the provided Blueprints
 * implementation {@link BlueprintsTgConfiguration}.
 *
 * @see PersistentResource
 * @see BlueprintsBackend
 * @see BlueprintsOptions
 * @see BlueprintsResourceOptions
 */
@ParametersAreNonnullByDefault
public class BlueprintsBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = BlueprintsBackend.NAME.toLowerCase();

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
    public static BackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Nonnull
    @Override
    public PersistentBackend createPersistentBackend(URI uri, Map<String, Object> options) {
        BlueprintsBackend backend;

        checkArgument(uri.isFile(), "BlueprintsBackendFactory only supports file-based URIs");

        boolean readOnly = StoreFactory.isDefined(options, PersistentStoreOptions.READ_ONLY);

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

            Configuration configuration = getOrCreateBlueprintsConfiguration(baseDirectory, options);

            Graph graph = GraphFactory.open(configuration.asMap());
            if (!graph.getFeatures().supportsKeyIndices) {
                throw new InvalidBackendException(String.format("%s does not support key indices", graph.getClass().getSimpleName()));
            }

            configuration.save();

            if (readOnly) {
                graph = new ReadOnlyKeyIndexableGraph<>(KeyIndexableGraph.class.cast(graph));
            }

            String mapping = mappingFrom(options);
            backend = newInstanceOf(mapping,
                    new ConstructorParameter(graph, KeyIndexableGraph.class));

            processGlobalConfiguration(baseDirectory, mapping);
        }
        catch (Exception e) {
            throw new InvalidBackendException(e);
        }

        return backend;
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        try {
            return new BlueprintsBackendIndices(new TinkerGraph());
        }
        catch (Exception e) {
            throw new InvalidBackendException(e);
        }
    }

    /**
     * Creates and saves the Blueprints configuration.
     *
     * @param directory the directory where the configuration must be stored
     * @param options   options to define the behavior of Blueprints
     *
     * @return the created configuration
     *
     * @throws InvalidBackendException if the configuration cannot be created in the {@code directory}, or if some
     *                                 {@code options} are missing or invalid.
     */
    private Configuration getOrCreateBlueprintsConfiguration(Path directory, Map<String, Object> options) {
        Path path = directory.resolve(BLUEPRINTS_CONFIG_FILE);

        Configuration configuration;

        try {
            configuration = Configuration.load(path);
        }
        catch (IOException e) {
            throw new InvalidBackendException(e);
        }

        // Initialize value if the configuration file has just been created
        if (!configuration.contains(BlueprintsResourceOptions.GRAPH_TYPE)) {
            configuration.putIfAbsent(BlueprintsResourceOptions.GRAPH_TYPE, BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        }
        else if (options.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            // The file already exists, verify that the problem options are not conflicting.
            String savedGraphType = configuration.get(BlueprintsResourceOptions.GRAPH_TYPE);
            String issuedGraphType = options.get(BlueprintsResourceOptions.GRAPH_TYPE).toString();
            if (!Objects.equals(savedGraphType, issuedGraphType)) {
                throw new InvalidBackendException(String.format("Unable to create Graph as %s, expected graph was %s)", issuedGraphType, savedGraphType));
            }
        }

        // Copy the Blueprints options to the configuration
        options.entrySet().stream()
                .filter(e -> e.getKey().startsWith("blueprints."))
                .forEach(e -> configuration.put(e.getKey(), e.getValue().toString()));

        // Check we have a valid graph graphType, it is needed to get the graph name
        if (!configuration.contains(BlueprintsResourceOptions.GRAPH_TYPE)) {
            throw new InvalidBackendException(String.format("Graph is undefined for %s", directory));
        }

        String graphType = configuration.get(BlueprintsResourceOptions.GRAPH_TYPE);

        configurationFor(graphType).putDefaultConfiguration(configuration, directory);

        return configuration;
    }

    /**
     * Retrieves the {@link BlueprintsConfiguration} instance from the {@code graphType}.
     *
     * @param graphType the type of the graph to use
     *
     * @return a {@link BlueprintsConfiguration} instance
     */
    private BlueprintsConfiguration configurationFor(String graphType) {
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

                return BlueprintsConfiguration.class.cast(configClassInstanceMethod.invoke(configClass));
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new InvalidOptionException(String.format("Unable to retrieve the Graph name from '%s'", graphType));
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactory INSTANCE = new BlueprintsBackendFactory();
    }
}
