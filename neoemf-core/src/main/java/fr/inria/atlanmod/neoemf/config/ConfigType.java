/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A configuration type used to ease the creation of a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper} from a
 * configuration, using reflection. It represents the constructor of a type.
 */
@ParametersAreNonnullByDefault
public class ConfigType<T> implements Comparable<ConfigType<T>> {

    /**
     * The fully-qualified name of the represented type.
     */
    @Nonnull
    private final String typeName;

    /**
     * The optional parameters of the type.
     */
    @Nonnull
    private final List<String> parameters;

    /**
     * The priority of the type. A bigger priority will place the type in front of the others in a chain.
     */
    private final int priority;

    /**
     * Constructs a new {@code ConfigType} for the specified {@code typeName}.
     *
     * @param typeName      the fully-qualified name of the type
     * @param parameterKeys the keys used to retrieve the constructor parameters of the type in a configuration
     */
    public ConfigType(String typeName, String... parameterKeys) {
        this(typeName, 0, parameterKeys);
    }

    /**
     * Constructs a new {@code ConfigType} for the specified {@code typeName}.
     *
     * @param typeName      the fully-qualified name of the type
     * @param priority      the priority of the type; a bigger priority will place the type in front of the others in a
     *                      chain
     * @param parameterKeys the keys used to retrieve the constructor parameters of the type in a configuration
     */
    public ConfigType(String typeName, int priority, String... parameterKeys) {
        checkNotNull(typeName, "typeName");

        this.typeName = typeName;
        this.parameters = Arrays.asList(parameterKeys);
        this.priority = priority;
    }

    /**
     * Returns the fully-qualified name of the represented type.
     *
     * @return the type
     */
    @Nonnull
    public String typeName() {
        return typeName;
    }

    /**
     * Returns the keys used to retrieve the constructor parameters of the type in a configuration.
     *
     * @return an immutable list
     */
    @Nonnull
    public List<String> parameterKeys() {
        return parameters;
    }

    @Override
    public int compareTo(@Nonnull ConfigType<T> o) {
        return Integer.compare(o.priority, priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfigType<?> that = ConfigType.class.cast(o);
        return Objects.equals(typeName, that.typeName);
    }
}
