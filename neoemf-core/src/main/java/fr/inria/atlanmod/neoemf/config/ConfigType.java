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
    private final List<ConfigParameter<?>> parameters;

    /**
     * The priority of the type. A bigger priority will place the type in front of the others in a chain.
     */
    private final int priority;

    /**
     * Constructs a new {@code ConfigType} for the specified {@code typeName}.
     *
     * @param typeName   the fully-qualified name of the type
     * @param parameters the optional parameters of the constructor
     */
    public ConfigType(String typeName, ConfigParameter<?>... parameters) {
        this(typeName, 0, parameters);
    }

    /**
     * Constructs a new {@code ConfigType} for the specified {@code typeName}.
     *
     * @param typeName   the fully-qualified name of the type
     * @param priority   the priority of the type; a bigger priority will place the type in front of the others in a
     *                   chain
     * @param parameters the optional parameters of the constructor
     */
    public ConfigType(String typeName, int priority, ConfigParameter<?>... parameters) {
        checkNotNull(typeName);

        this.typeName = typeName;
        this.parameters = Arrays.asList(parameters);
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
     * Returns the optional parameters of the type.
     *
     * @return an immutable list
     */
    @Nonnull
    public List<ConfigParameter<?>> parameters() {
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
