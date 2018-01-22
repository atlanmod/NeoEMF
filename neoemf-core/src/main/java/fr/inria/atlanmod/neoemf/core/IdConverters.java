/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.function.Converter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A static factory that creates {@link Converter} instances related to {@link Id}s.
 */
@Static
@ParametersAreNonnullByDefault
public final class IdConverters {

    /**
     * The {@link Converter} to use a long representation instead of {@link Id}.
     */
    @Nonnull
    private static final Converter<Id, Long> WITH_LONG = Converter.from(
            Id::toLong,
            Id.getProvider()::fromLong);

    /**
     * The {@link Converter} to use a hexadecimal representation instead of {@link Id}.
     */
    @Nonnull
    private static final Converter<Id, String> WITH_HEX_STRING = Converter.from(
            Id::toHexString,
            Id.getProvider()::fromHexString);

    private IdConverters() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Returns the {@link Converter} to use a long representation instead of {@link Id}.
     *
     * @return a converter
     *
     * @see Id#toLong()
     * @see IdProvider#fromLong(long)
     */
    @Nonnull
    public static Converter<Id, Long> withLong() {
        return WITH_LONG;
    }

    /**
     * Returns the {@link Converter} to use a hexadecimal representation instead of {@link Id}.
     *
     * @return a converter
     *
     * @see Id#toHexString()
     * @see IdProvider#fromHexString(String)
     */
    @Nonnull
    public static Converter<Id, String> withHexString() {
        return WITH_HEX_STRING;
    }
}
