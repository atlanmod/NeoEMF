/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates HBase specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@FactoryBinding(factory = HBaseBackendFactory.class)
@ParametersAreNonnullByDefault
public class HBaseConfig extends BaseConfig<HBaseConfig> {

    /**
     * Constructs a new {@code HBaseConfig} with default settings.
     */
    public HBaseConfig() {
        withDefault();
    }

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static HBaseConfig newConfig() {
        return new HBaseConfig();
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.hbase.HBaseBackend}.
     * <p>
     * This mapping corresponds to: <ul> <li>an {@link Object}[] representation of multi-valued attributes</li> <li>a
     * {@link String} representation for single-valued references</li> <li>a {@link String}[] representation for
     * multi-valued references</li> </ul>
     * <p>
     * <b>NOTE:</b> This is the default mapping.
     *
     * @return this configuration (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ReferenceAs
     * @see fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays
     * @see fr.inria.atlanmod.neoemf.data.mapping.ManyReferenceMergedAs
     */
    @Nonnull
    protected HBaseConfig withDefault() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.hbase.DefaultHBaseBackend", false);
    }
}
