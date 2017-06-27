/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data;

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

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A simple file-based configuration for {@link Backend}s.
 */
@ParametersAreNonnullByDefault
public final class BackendConfiguration {

    /**
     * The header of the configuration file.
     */
    private static final String HEADER = "NeoEMF Configuration";

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
     * Constructs a new {@code BackendConfiguration} on the given {@code configuration}.
     *
     * @param properties the inner properties
     * @param file       the file to store the properties
     */
    private BackendConfiguration(Properties properties, Path file) {
        this.properties = checkNotNull(properties);
        this.file = checkNotNull(file);
    }

    /**
     * Creates a new {@code BackendConfiguration} and loads it from the specified {@code file} if it exists.
     *
     * @param file the properties file to load
     *
     * @return a new configuration
     */
    @Nonnull
    public static BackendConfiguration load(Path file) throws IOException {
        checkNotNull(file);

        Properties properties = new Properties();

        if (Files.exists(file)) {
            try (Reader reader = Files.newBufferedReader(file)) {
                properties.load(reader);
            }
        }

        return new BackendConfiguration(properties, file);
    }

    /**
     * Stores this configuration.
     */
    public void save() throws IOException {
        if (!Files.exists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        }

        try (Writer writer = Files.newBufferedWriter(file)) {
            properties.store(writer, HEADER);
        }
    }

    /**
     * Check if this configuration contains the specified {@code key}.
     *
     * @param key the key whose presence in this configuration is to be tested
     *
     * @return {@code true} if the configuration contains a value for this key, {@code false} otherwise
     */
    public boolean has(String key) {
        checkNotNull(key);

        return properties.containsKey(key) && nonNull(get(key));
    }

    /**
     * Retrieves a property.
     *
     * @param key the key of the property to retrieve
     *
     * @return the value to which this configuration maps the specified {@code key}, or {@code null} if the
     * configuration contains no mapping for this {@code key}
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
    public void set(String key, String value) {
        checkNotNull(key);
        checkNotNull(value);

        properties.setProperty(key, value);
    }

    /**
     * Defines a property if the {@code key} is not already defined.
     *
     * @param key   the key of the property to change
     * @param value the value
     */
    public void setIfAbsent(String key, String value) {
        checkNotNull(key);
        checkNotNull(value);

        if (!has(key)) {
            set(key, value);
        }
    }

    /**
     * Removes a property.
     *
     * @param key the key of the property to remove
     */
    public void remove(String key) {
        checkNotNull(key);

        properties.remove(key);
    }

    /**
     * Returns a copy of this configuration, as a {@link Map}.
     *
     * @return the map representation of this configuration
     */
    @Nonnull
    public Map<String, String> asMap() {
        return properties.keySet().stream()
                .map(Object::toString)
                .collect(Collectors.toMap(k -> k, this::get));
    }
}
