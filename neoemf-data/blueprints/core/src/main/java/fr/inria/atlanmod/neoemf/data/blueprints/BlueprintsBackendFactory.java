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

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.bind.FactoryName;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.tg.BlueprintsTgConfig;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

import org.eclipse.emf.common.util.URI;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A factory that creates {@link BlueprintsBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent databases.
 * Persistent back-end creation can be configured using {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)}
 * and {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)} option maps.
 * <p>
 * The factory handles transient back-ends by creating an in-memory {@code TinkerGraph} instance. Persistent back-ends
 * are created according to the provided resource options (see {@link BlueprintsResourceOptions}. Default back-end
 * configuration (store directory and graph type) is called dynamically according to the provided Blueprints
 * implementation {@link BlueprintsTgConfig}.
 *
 * @see BlueprintsBackend
 * @see BlueprintsResourceOptions
 * @see fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class BlueprintsBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    @FactoryName
    public static final String NAME = "blueprints";

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

        boolean isReadOnly = StoreFactory.isDefined(options, PersistentStoreOptions.READ_ONLY);

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

            BackendConfig config = getOrCreateBlueprintsConfiguration(baseDirectory, options);

            Graph graph = GraphFactory.open(config.asMap());
            if (!graph.getFeatures().supportsKeyIndices) {
                throw new InvalidBackendException(String.format("%s does not support key indices", graph.getClass().getSimpleName()));
            }

            config.save();

            if (isReadOnly) {
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
    private BackendConfig getOrCreateBlueprintsConfiguration(Path directory, Map<String, Object> options) {
        Path path = directory.resolve(BLUEPRINTS_CONFIG_FILE);

        BackendConfig config;

        try {
            config = BackendConfig.load(path);
        }
        catch (IOException e) {
            throw new InvalidBackendException(e);
        }

        // Initialize value if the configuration file has just been created
        if (!config.has(BlueprintsResourceOptions.GRAPH_TYPE)) {
            config.set(BlueprintsResourceOptions.GRAPH_TYPE, BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        }
        else if (options.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)) {
            // The file already exists, verify that the problem options are not conflicting.
            String savedGraphType = config.get(BlueprintsResourceOptions.GRAPH_TYPE);
            String issuedGraphType = options.get(BlueprintsResourceOptions.GRAPH_TYPE).toString();
            if (!Objects.equals(savedGraphType, issuedGraphType)) {
                throw new InvalidBackendException(String.format("Unable to create Graph as %s, expected graph was %s)", issuedGraphType, savedGraphType));
            }
        }

        // Copy the Blueprints options to the configuration
        options.entrySet().stream()
                .filter(e -> e.getKey().startsWith("blueprints."))
                .forEach(e -> config.set(e.getKey(), e.getValue().toString()));

        // Check we have a valid graph graphType, it is needed to get the graph name
        if (!config.has(BlueprintsResourceOptions.GRAPH_TYPE)) {
            throw new InvalidBackendException(String.format("Graph is undefined for %s", directory));
        }

        String graphType = config.get(BlueprintsResourceOptions.GRAPH_TYPE);

        configurationFor(graphType).putDefault(config, directory);

        return config;
    }

    /**
     * Retrieves the {@link BlueprintsConfig} instance from the {@code graphType}.
     *
     * @param graphType the type of the graph to use
     *
     * @return a {@link BlueprintsConfig} instance
     */
    private BlueprintsConfig configurationFor(String graphType) {
        // Define the configuration
        String[] segments = graphType.split("\\.");

        if (segments.length >= 2) {
            String graphName = segments[segments.length - 2];
            String configClassName = String.format("Blueprints%sConfig", Character.toUpperCase(graphName.charAt(0)) + graphName.substring(1));

            String configClassQualifiedName = String.format("%s.%s.%s",
                    BlueprintsBackendFactory.class.getPackage().getName(),
                    graphName,
                    configClassName);

            try {
                Class<?> configClass = getClass().getClassLoader().loadClass(configClassQualifiedName);
                return BlueprintsConfig.class.cast(configClass.newInstance());
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
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
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactory INSTANCE = new BlueprintsBackendFactory();
    }
}
