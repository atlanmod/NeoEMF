/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.util.wrappers.readonly.ReadOnlyKeyIndexableGraph;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ConfigValue;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BaseBlueprintsConfig;

import org.eclipse.emf.common.util.URI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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
 * are created according to the provided {@link fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig}.
 *
 * @see BlueprintsBackend
 * @see fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class BlueprintsBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = "blueprints";

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
    public Backend createBackend(URI uri, ImmutableConfig baseConfig) {
        BlueprintsBackend backend;

        checkArgument(uri.isFile(), "%s only supports file-based URIs", getClass().getSimpleName());

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

            // Merge and check conflicts between the two configurations
            ImmutableConfig mergedConfig = Config.<BaseBlueprintsConfig<?>>load(baseDirectory)
                    .orElseGet(BaseBlueprintsConfig::newConfig)
                    .merge(baseConfig)
                    .setDirectory(baseDirectory);

            String mapping = mergedConfig.getMapping();
            boolean isReadOnly = mergedConfig.isReadOnly();

            Graph graph = GraphFactory.open(mergedConfig.getOptions(s -> s.startsWith(BaseBlueprintsConfig.BLUEPRINTS_PREFIX)));
            if (!graph.getFeatures().supportsKeyIndices) {
                throw new InvalidBackendException(String.format("%s does not support key indices", graph.getClass().getSimpleName()));
            }

            if (isReadOnly) {
                graph = new ReadOnlyKeyIndexableGraph<>(KeyIndexableGraph.class.cast(graph));
            }

            backend = createMapper(mapping,
                    new ConfigValue<>(graph, KeyIndexableGraph.class));

            mergedConfig.save(baseDirectory);
        }
        catch (Exception e) {
            throw new InvalidBackendException("Unable to open the Blueprints database", e);
        }

        return backend;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final BackendFactory INSTANCE = new BlueprintsBackendFactory();
    }
}
