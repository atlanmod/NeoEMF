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

import fr.inria.atlanmod.neoemf.util.logging.Log;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 *
 */
@ParametersAreNonnullByDefault
public final class PersistenceConfiguration {

    /**
     * The inner properties.
     */
    @Nonnull
    private final PropertiesConfiguration properties;

    /**
     * Constructs a new {@code PersistenceConfiguration} on the given {@code configuration}.
     *
     * @param properties the inner properties
     */
    private PersistenceConfiguration(PropertiesConfiguration properties) {
        this.properties = checkNotNull(properties);
    }

    /**
     * Creates a new {@code PersistenceConfiguration} and loads it from the specified {@code file}.
     *
     * @param file the properties file to load
     *
     * @return a new configuration
     */
    @Nonnull
    public static PersistenceConfiguration load(File file) {
        checkNotNull(file);

        try {
            return new PersistenceConfiguration(new PropertiesConfiguration(file));
        }
        catch (ConfigurationException e) {
            throw new InvalidDataStoreException(e);
        }
    }

    /**
     * Save this {@code PersistenceConfiguration}.
     */
    public void save() {
        try {
            properties.save();
        }
        catch (ConfigurationException e) {
            // Supposedly it's a minor error.
            Log.warn(e);
        }
    }

    /**
     * Check if this {@code PersistenceConfiguration} contains the specified {@code key}.
     *
     * @param key the key whose presence in this configuration is to be tested
     *
     * @return {@code true} if the configuration contains a value for this key, {@code false} otherwise
     */
    public boolean containsKey(String key) {
        checkNotNull(key);
        return properties.containsKey(key) && nonNull(getProperty(key));
    }

    /**
     * Gets a property.
     *
     * @param key the key of the property to retrieve
     *
     * @return the value to which this configuration maps the specified {@code key}, or {@code null} if the
     * configuration contains no mapping for this {@code key}
     */
    public Object getProperty(String key) {
        checkNotNull(key);
        return properties.getProperty(key);
    }

    /**
     * Defines a property. This will replace any previously set values.
     *
     * @param key   the key of the property to change
     * @param value the value
     */
    public void setProperty(String key, Object value) {
        checkNotNull(key);
        checkNotNull(value);
        properties.setProperty(key, value);
    }

    /**
     * Removes a property.
     *
     * @param key the key of the property to remove
     */
    public void removeProperty(String key) {
        checkNotNull(key);
        properties.clearProperty(key);
    }

    /**
     * Returns a copy of this configuration, as a {@link Map}.
     *
     * @return the map representation of this configuration
     */
    @Nonnull
    public Map<String, Object> asMap() {
        Map<String, Object> configurationMap = new HashMap<>();
        properties.getKeys().forEachRemaining(key -> configurationMap.put(key, properties.getProperty(key)));
        return configurationMap;
    }
}
