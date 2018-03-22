/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BaseBlueprintsConfig;

import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link BlueprintsBackend} instances.
 */
@ParametersAreNonnullByDefault
public class BlueprintsBackendFactory extends AbstractBackendFactory<BaseBlueprintsConfig<?>> {

    /**
     * The literal description of the factory.
     */
    private static final String NAME = "blueprints";

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static BackendFactory getInstance() {
        return new BlueprintsBackendFactory();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, BaseBlueprintsConfig<?> config) {
        config.setLocation(directory);

        Graph graph = GraphFactory.open(config.getOptions(s -> s.startsWith(BaseBlueprintsConfig.BLUEPRINTS_PREFIX)));
        checkArgument(graph.getFeatures().supportsKeyIndices, "%s does not support key indices", graph.getClass().getSimpleName());

        if (config.isReadOnly()) {
            graph = new ReadOnlyKeyIndexableGraph<>(KeyIndexableGraph.class.cast(graph));
        }

        return createMapper(config.getMapping(), graph);
    }
}
