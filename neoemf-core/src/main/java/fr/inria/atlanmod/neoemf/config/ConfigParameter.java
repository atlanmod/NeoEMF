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

package fr.inria.atlanmod.neoemf.config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A configuration parameter used to ease the creation of a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}
 * from a configuration, using reflection. It represents a constructor or a method parameter.
 * <p>
 * The {@link #name()} represents a key in a configuration, used to retrieve and build a {@link ConfigValue}.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class ConfigParameter<T> {

    /**
     * The name of the parameter in a {@link Config}.
     */
    @Nonnull
    private final String name;

    /**
     * The declared type of the parameter.
     */
    @Nonnull
    private final Class<? extends T> type;

    /**
     * Constructs a new {@code ConfigParameter} with the value, and the declared type.
     *
     * @param name the name of this parameter in a configuration
     * @param type the declared type of the parameter
     */
    public ConfigParameter(String name, Class<? extends T> type) {
        this.name = checkNotNull(name);
        this.type = checkNotNull(type);
    }

    /**
     * The name of the parameter in a {@link Config}.
     *
     * @return the name/key
     *
     * @see Config#getOption(String)
     * @see Config#addOption(String, Object)
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * The declared type of the parameter.
     *
     * @return the type
     */
    @Nonnull
    public Class<? extends T> type() {
        return type;
    }

    /**
     * Finds the value of the parameter in the {@code config}.
     *
     * @param config the configuration where to find the value of the parameter
     *
     * @return the value of the parameter
     */
    @Nonnull
    public ConfigValue<? extends T> findValue(ImmutableConfig config) {
        T value = config.<T>getOption(name)
                .<InvalidConfigException>orElseThrow(InvalidConfigException::new);

        return new ConfigValue<>(value, type);
    }
}
