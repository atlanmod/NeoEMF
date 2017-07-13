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

package fr.inria.atlanmod.common.hash;

import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that calculates a {@link HashCode} from a value.
 */
@FunctionalInterface
@ParametersAreNonnullByDefault
public interface Hasher {

    /**
     * Calculates the {@link HashCode} of the given {@code byte[]}.
     *
     * @param data the data to hash
     *
     * @return a new hash code
     */
    @Nonnull
    HashCode hash(byte[] data);

    /**
     * Calculates the {@link HashCode} of the given {@code int}.
     *
     * @param value the value to hash
     *
     * @return a new hash code
     */
    @Nonnull
    default HashCode hash(int value) {
        byte[] bytesValue = new byte[]{(byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value};

        return hash(bytesValue);
    }

    /**
     * Calculates the {@link HashCode} of the given {@code long}.
     *
     * @param value the value to hash
     *
     * @return a new hash code
     */
    @Nonnull
    default HashCode hash(long value) {
        byte[] bytesValue = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bytesValue[i] = (byte) (value & 0xffL);
            value >>= 8;
        }

        return hash(bytesValue);
    }

    /**
     * Calculates the {@link HashCode} of the given {@link String}.
     *
     * @param value the value to hash
     *
     * @return a new hash code
     */
    @Nonnull
    default HashCode hash(String value) {
        byte[] bytesValue = value.getBytes(StandardCharsets.UTF_8);

        return hash(bytesValue);
    }
}
