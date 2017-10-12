/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A provider of {@link Id} instances.
 */
@ParametersAreNonnullByDefault
public interface IdProvider {

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
