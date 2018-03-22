/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates BerkeleyDB specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@FactoryBinding(factory = BerkeleyDbBackendFactory.class)
@ParametersAreNonnullByDefault
public class BerkeleyDbConfig extends BaseConfig<BerkeleyDbConfig> {

    /**
     * Constructs a new {@code BerkeleyDbConfig} with default settings.
     * <p>
     * <b>NOTE:</b> This configuration has several possible mappings: no mapping is defined by default.
     *
     * @see #withIndices()
     * @see #withLists()
     * @see #withArrays()
     */
    public BerkeleyDbConfig() {
        // Don't set a default mapping for a multi-mapping configuration.
    }

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static BerkeleyDbConfig newConfig() {
        return new BerkeleyDbConfig();
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackend}.
     * <p>
     * This mapping corresponds to a simple representation of multi-valued features, by using the {@link
     * fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean#position()}.
     * <p>
     * <b>Note:</b> This is the default mapping.
     *
     * @return this configuration (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithIndices
     */
    public BerkeleyDbConfig withIndices() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendIndices", false);
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackend}.
     * <p>
     * This mapping corresponds to an {@link Object}[] representation of multi-valued features.
     *
     * @return this configuration (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays
     */
    public BerkeleyDbConfig withArrays() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendArrays", false);
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackend}.
     * <p>
     * This mapping corresponds to a {@link java.util.List} representation of multi-valued features.
     *
     * @return this configuration (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithLists
     */
    public BerkeleyDbConfig withLists() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendLists", false);
    }
}
