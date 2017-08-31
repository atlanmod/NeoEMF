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

package fr.inria.atlanmod.commons.primitive;

import fr.inria.atlanmod.commons.annotation.Static;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * Static utility methods related to {@code float} and {@link Float}.
 */
@Static
@ParametersAreNonnullByDefault
public final class Floats {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Floats() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Encodes a {@code float} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @see Ints#toBytes(int)
     * @see Float#floatToIntBits(float)
     */
    @Nonnull
    public static byte[] toBytes(final float value) {
        // Encoded to integer
        return Ints.toBytes(Float.floatToIntBits(value));
    }

    /**
     * Encodes a {@link Float} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException if the {@code value} is {@code null}
     */
    @Nonnull
    public static byte[] toBytes(final Float value) {
        checkNotNull(value);

        return toBytes(value.floatValue());
    }
}
