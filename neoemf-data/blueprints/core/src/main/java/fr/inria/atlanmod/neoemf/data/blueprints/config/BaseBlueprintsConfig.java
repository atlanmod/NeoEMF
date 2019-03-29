/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.config;

import com.tinkerpop.blueprints.Graph;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.InvalidConfigException;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.nio.file.Path;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The base {@link fr.inria.atlanmod.neoemf.config.Config} that provides utility methods to create generic Blueprints
 * configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 *
 * @param <C> the "self"-type of this {@link fr.inria.atlanmod.neoemf.config.Config}
 */
@Component(service = Config.class, scope = ServiceScope.PROTOTYPE)
@FactoryBinding(factory = BlueprintsBackendFactory.class, concrete = false)
@ParametersAreNonnullByDefault
public class BaseBlueprintsConfig<C extends BaseBlueprintsConfig<C>> extends BaseConfig<C> {

    /**
     * The base prefix for all options related to Blueprints, whatever the implementation.
     */
    public static final String BLUEPRINTS_PREFIX = "blueprints";

    /**
     * Constructs a new {@code BaseBlueprintsConfig}.
     */
    public BaseBlueprintsConfig() {
        withDefault();
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend}.
     * <p>
     * This mapping corresponds to a simple representation of multi-valued features, by using the {@link
     * fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean#position()}.
     * <p>
     * <b>NOTE:</b> This is the default mapping.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    protected C withDefault() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.blueprints.DefaultBlueprintsBackend", false);
    }

    /**
     * Defines the {@code graph} implementation of the Blueprints database.
     *
     * @param graph the fully qualified name of the class of the Blueprints graph
     *
     * @throws InvalidConfigException if the graph is already defined and its value is different from the given {@code
     *                                graph}
     */
    protected void setGraph(Class<? extends Graph> graph) {
        addOption(createKey(BLUEPRINTS_PREFIX, "graph"), graph.getName());
    }

    /**
     * Defines the location of the Blueprints database.
     * <p>
     * <b>NOTE:</b> This option is intended to be used internally by the {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory}.
     *
     * @param directory the location of the database
     */
    public void setLocation(Path directory) {
        throw new UnsupportedOperationException("This implementation should not be used directly");
    }

    @Nonnull
    @Override
    protected Predicate<String> isPersistentKey() {
        return super.isPersistentKey()
                .or(s -> s.startsWith(BLUEPRINTS_PREFIX));
    }

    @Nonnull
    @Override
    protected Predicate<String> isReadOnlyKey() {
        return super.isReadOnlyKey()
                .or(createKey(BLUEPRINTS_PREFIX, "graph")::equals);
    }
}
