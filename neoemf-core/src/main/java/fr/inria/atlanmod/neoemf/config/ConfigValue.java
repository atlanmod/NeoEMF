/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A configuration value used to ease the creation of {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper} from a
 * configuration, using reflection. If represents a constructor or a method value.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class ConfigValue<T> {

    /**
     * The embed value.
     */
    @Nonnull
    private final T value;

    /**
     * The declared type of the value.
     */
    @Nonnull
    private final Class<? extends T> type;

    /**
     * Constructs a new {@code ConfigValue} with the {@code value}, and the direct type of the {@code value}.
     *
     * @param value the value
     */
    @SuppressWarnings("unchecked")
    public ConfigValue(T value) {
        this(value, (Class<T>) value.getClass());
    }

    /**
     * Constructs a new {@code ConfigValue} with the {@code value}, and the declared {@code type}.
     *
     * @param value the value
     * @param type  the declared type of the value
     */
    public ConfigValue(T value, Class<? extends T> type) {
        checkNotNull(value);
        checkNotNull(type);
        checkArgument(type.isInstance(value));

        this.value = value;
        this.type = type;
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    @Nonnull
    public T value() {
        return value;
    }

    /**
     * Returns the declared type of the value.
     *
     * @return the type
     */
    @Nonnull
    public Class<? extends T> type() {
        return type;
    }
}
