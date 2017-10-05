/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.RegEx;

/**
 * A class that handles options that can be used as benchmarks parameters.
 */
@ParametersAreNonnullByDefault
final class ConfigParser {

    /**
     * The regex of a number argument, as {@code {0189}}.
     */
    @RegEx
    private static final String ARG_NUMBER = "\\{(-?[0-9]+)\\}";

    /**
     * The regex of a text argument, as {@code {ABYZ}}.
     */
    @RegEx
    private static final String ARG_TEXT = "\\{([A-Z]+)\\}";

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
     * The option for recording stats.
     */
    private static final String RECORD_STATS = "R";

    /**
     * The option for logging database calls.
     */
    private static final String LOG = "L";

    /**
     * The option for auto-saving.
     */
    private static final String AUTO_SAVE = "A";

    /**
     * The pattern for auto-saving, with a specified chunk.
     */
    private static final Pattern AUTO_SAVE_CHUCK = Pattern.compile(AUTO_SAVE + ARG_NUMBER, Pattern.CASE_INSENSITIVE);

    /**
     * Parses the given {@code text} and returns the associated {@link Config}.
     *
     * @param text the text containg the configuration to define
     *
     * @return a {@link Config}
     */
    @Nonnull
    public static Config parse(String text) {
        Config options = BaseConfig.newConfig();

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

        // Stats recording
        if (upperText.contains(ConfigParser.RECORD_STATS)) {
            options.recordStats();
        }

        // Logging
        if (upperText.contains(ConfigParser.LOG)) {
            options.log();
        }

        // Auto-saving
        Matcher chuckMatcher = ConfigParser.AUTO_SAVE_CHUCK.matcher(upperText);
        if (chuckMatcher.find()) {
            options.autoSave(Long.parseLong(chuckMatcher.group(1)));
        }
        else if (upperText.contains(AUTO_SAVE)) {
            options.autoSave();
        }

        return options;
    }
}
