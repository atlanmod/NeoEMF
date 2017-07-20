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

package fr.inria.atlanmod.common.primitive;

import fr.inria.atlanmod.common.annotation.Static;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * Static utility methods related to {@code boolean} and {@link Boolean}.
 */
@Static
@ParametersAreNonnullByDefault
public final class Booleans {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Booleans() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Encodes a {@code boolean} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final boolean value) {
        return new byte[]{value ? (byte) 1 : (byte) 0};
    }

    /**
     * Encodes a {@link Boolean} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException if the {@code value} is {@code null}
     */
    @Nonnull
    public static byte[] toBytes(final Boolean value) {
        checkNotNull(value);

        return toBytes(value.booleanValue());
    }
}
