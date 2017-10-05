/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.config;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates Blueprints TinkerGraph specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public class BlueprintsTinkerConfig extends BaseBlueprintsConfig<BlueprintsTinkerConfig> {

    /**
     * The base prefix for all natives options related to TinkerGraph under Blueprints.
     */
    static final String TINKER_PREFIX = createKey(BLUEPRINTS_PREFIX, "tg");

    /**
     * The default option value to define TinkerGraph as the graph implementation to use.
     */
    static final String GRAPH_TINKER = "com.tinkerpop.blueprints.impls.tg.TinkerGraph";

    /**
     * Constructs a new {@code BlueprintsTinkerConfig}.
     */
    protected BlueprintsTinkerConfig() {
        setGraph(GRAPH_TINKER).addOption(createKey(TINKER_PREFIX, "file-type"), "GRAPHML");
    }

    /**
     * Constructs a new {@code BlueprintsTinkerConfig} instance with default settings.
     *
     * @return a new configuration
     */
    @Nonnull
    public static BlueprintsTinkerConfig newConfig() {
        return new BlueprintsTinkerConfig();
    }

    @Nonnull
    @Override
    protected Predicate<String> readOnlyKeys() {
        return super.readOnlyKeys()
                .or(s -> s.equals(createKey(TINKER_PREFIX, "file-type")));
    }
}
