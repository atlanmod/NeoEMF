/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A class that handles options that can be used as benchmarks parameters.
 */
@ParametersAreNonnullByDefault
final class ConfigParser {

    /**
     * The option for caching features.
     */
    private static final String CACHE_FEATURES = "F";

    /**
     * The option for size caching.
     */
    private static final String CACHE_SIZES = "S";

    /**
     * The option for metaclass caching.
     */
    private static final String CACHE_METACLASSES = "M";

    /**
     * The option for container caching.
     */
    private static final String CACHE_CONTAINERS = "C";

    /**
     * The option for logging database calls.
     */
    private static final String LOG = "L";

    /**
     * The option for auto-saving.
     */
    private static final String AUTO_SAVE = "A";

    /**
     * Parses the given {@code text} and returns the associated configuration.
     *
     * @param text the text containg the configuration to define
     *
     * @return an immutable configuration
     */
    @Nonnull
    public static ImmutableConfig parse(String text) {
        Config options = new BaseConfig<>();
        String upperText = text.toUpperCase();

        // Cache features
        if (upperText.contains(ConfigParser.CACHE_FEATURES)) {
            options.cacheFeatures();
        }

        // Cache sizes
        if (upperText.contains(ConfigParser.CACHE_SIZES)) {
            options.cacheSizes();
        }

        // Cache metaclasses (Defined by default)
        if (upperText.contains(ConfigParser.CACHE_METACLASSES)) {
            options.cacheMetaClasses();
        }

        // Cache containers (Defined by default)
        if (upperText.contains(ConfigParser.CACHE_CONTAINERS)) {
            options.cacheContainers();
        }

        // Logging
        if (upperText.contains(ConfigParser.LOG)) {
            options.log();
        }

        // Auto-saving
        if (upperText.contains(AUTO_SAVE)) {
            options.autoSave();
        }

        return options;
    }
}
