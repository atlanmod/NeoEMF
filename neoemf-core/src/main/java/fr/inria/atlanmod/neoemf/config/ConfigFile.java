/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple file-based configuration.
 */
@ParametersAreNonnullByDefault
final class ConfigFile {

    /**
     * The name of the default configuration file.
     */
    private static final String FILENAME = "neoemf.conf";

    /**
     * The inner properties.
     */
    @Nonnull
    private final Properties properties;

    /**
     * The file path to store the configuration.
     */
    @Nonnull
    private final Path file;

    /**
     * Constructs a new {@code ConfigFile} located in the {@code file}.
     *
     * @param file the file to read/store the configuration
     *
     * @throws NullPointerException if {@code file} is {@code null}
     */
    private ConfigFile(Path file) throws IOException {
        this.file = checkNotNull(file);

        this.properties = new Properties();
        if (Files.exists(file)) {
            try (Reader reader = Files.newBufferedReader(file)) {
                properties.load(reader);
            }
        }
    }

    /**
     * Checks that a configuration file exists in the {@code directory}.
     *
     * @param directory the directory where to look for a configuration
     *
     * @return {@code true} if the {@code directory} contains a configuration file
     */
    public static boolean exists(Path directory) {
        return Files.exists(directory) && Files.exists(directory.resolve(FILENAME));
    }

    /**
     * Creates a new {@code ConfigFile} and loads it from the specified {@code directory} if it exists. If the {@code
     * directory} is a directory, the the configuration file will be resolved from it.
     * <p>
     * If the directory, or the configuration file, does not exist, they will be created on the first call to {@link
     * #save()}.
     *
     * @param directory the properties directory to load, or its base directory
     *
     * @return a new configuration
     */
    @Nonnull
    public static ConfigFile load(Path directory) throws IOException {
        return new ConfigFile(directory.resolve(FILENAME));
    }

    /**
     * Saves this configuration.
     */
    public void save() throws IOException {
        if (!Files.exists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        }

        try (Writer writer = Files.newBufferedWriter(file)) {
            properties.store(writer, "NeoEMF Configuration");
        }
    }

    /**
     * Retrieves the value of the {@code key}.
     *
     * @param key the key identifying the value
     *
     * @return the value, or {@code null} if the {@code key} does not exist
     */
    public String get(String key) {
        checkNotNull(key);

        return properties.getProperty(key);
    }

    /**
     * Defines a property. This will replace any previously set values.
     *
     * @param key   the key of the property to change
     * @param value the value
     */
    public void put(String key, String value) {
        checkNotNull(key);
        checkNotNull(value);

        properties.setProperty(key, value);
    }

    /**
     * Returns a copy of this configuration, as a {@link Map}.
     *
     * @return the map representation of this configuration
     */
    @Nonnull
    public Map<String, Object> toMap() {
        return properties.stringPropertyNames()
                .stream()
                .collect(Collectors.toMap(k -> k, this::get));
    }
}
