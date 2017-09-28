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

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.Converter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A provider of {@link Id} instances.
 */
@ParametersAreNonnullByDefault
public interface IdProvider {

    /**
     * The {@link Converter} to use a long representation instead of {@link Id}.
     *
     * @see Id#toLong()
     * @see IdProvider#fromLong(long)
     */
    @Nonnull
    Converter<Id, Long> AS_LONG = Converter.from(
            Id::toLong,
            Id.getProvider()::fromLong);

    /**
     * The {@link Converter} to use a hexadecimal representation instead of {@link Id}.
     *
     * @see Id#toHexString()
     * @see IdProvider#fromHexString(String)
     */
    @Nonnull
    Converter<Id, String> AS_HEXA = Converter.from(
            Id::toHexString,
            Id.getProvider()::fromHexString);

    /**
     * Creates a new {@link Id} from a long representation.
     *
     * @param value the long representation of the new identifier
     *
     * @return a new instance of an {@link Id}
     *
     * @see Id#toLong()
     */
    @Nonnull
    Id fromLong(long value);

    /**
     * Creates a new {@link Id} from an hexadecimal representation.
     *
     * @param hexValue the hexadecimal representation of the new identifier
     *
     * @return a new instance of an {@link Id}
     *
     * @throws IllegalArgumentException if the {@code hexValue} does not represent an hexadecimal value
     * @see Id#toHexString()
     */
    @Nonnull
    Id fromHexString(String hexValue);

    /**
     * Generates a new instance of an {@link Id} initialized with a random value.
     *
     * @return a new instance of an {@link Id}
     */
    @Nonnull
    Id generate();

    /**
     * Generates a new instance of an {@link Id} from a {@code baseValue}.
     * <p>
     * Several calls to this method with the same {@code baseValue} should return the same identifier.
     *
     * @param baseValue the base value of the identifier
     *
     * @return a new instance of an {@link Id}
     */
    @Nonnull
    Id generate(String baseValue);
}
