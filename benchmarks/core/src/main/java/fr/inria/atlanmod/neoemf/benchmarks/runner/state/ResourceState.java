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

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BaseState} that provides the preloaded resource file.
 */
@ParametersAreNonnullByDefault
public class ResourceState extends BaseState {

    // region JMH parameters

    /**
     * The name of the current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    @Param({
            "set1",
            "set2",
            "set3",
    })
    protected String r;

    /**
     * The name of the current store chain.
     */
    @Param("A")
    protected String o;

    /**
     * {@code "true"} if the direct import has to be used when creating or importing resources.
     */
    @Param("true")
    protected String direct;

    // endregion

    /**
     * The current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    private File resourceFile;

    /**
     * {@code true} if the direct import has to be used when creating or importing resources.
     */
    private boolean useDirectImport;

    /**
     * The options to use with the defined adapter.
     */
    private ImmutableConfig baseConfig;

    // region Getters

    /**
     * Returns the current resource file.
     */
    @Nonnull
    public File resourceFile() {
        return resourceFile;
    }

    /**
     * Returns {@code true} if the direct import has to be used when creating or importing resources.
     */
    public boolean useDirectImport() {
        return useDirectImport;
    }

    /**
     * Returns the options to use with the defined adapter.
     */
    @Nonnull
    public ImmutableConfig baseConfig() {
        return baseConfig;
    }

    // endregion

    /**
     * Initializes all defined arguments.
     */
    @Setup(Level.Trial)
    public void initArguments() throws IOException {
        baseConfig = ConfigParser.parse(o);
        useDirectImport = Boolean.valueOf(direct);
        resourceFile = adapter().getOrCreateResource(r);
    }

    /**
     * A class that handles options that can be used as benchmarks parameters.
     */
    @ParametersAreNonnullByDefault
    static final class ConfigParser {

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
}