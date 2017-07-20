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

import fr.inria.atlanmod.common.Bytes;

import java.io.Serializable;
import java.security.MessageDigest;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An immutable hash code of arbitrary bit length.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class HashCode implements Serializable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -576482022539994714L;

    /**
     * The bytes representation of this hash code.
     */
    private final byte[] bytes;

    /**
     * Constructs a new {@code HashCode} with the given {@code hashCode}.
     *
     * @param bytes the bytes representation of this hash code
     */
    public HashCode(byte[] bytes) {
        this.bytes = checkNotNull(bytes);
    }

    /**
     * Returns the number of bits in this hash code; a positive multiple of 8.
     *
     * @return the number of bits
     */
    @Nonnegative
    public int bits() {
        return bytes.length * 8;
    }

    /**
     * Returns the value of this hash code as a byte array.
     *
     * @return a byte array
     */
    @Nonnull
    public byte[] toBytes() {
        return bytes.clone();
    }

    /**
     * Returns a string containing each byte of {@link #toBytes()}, in order, as a two-digit unsigned hexadecimal number
     * in lower case.
     *
     * @return a string
     *
     * @see Bytes#toStringBinary(byte[])
     */
    @Nonnull
    public String toHexString() {
        return Bytes.toStringBinary(bytes);
    }

    @Override
    public int hashCode() {
        if (bits() >= 32) {
            return (bytes[0] & 0xff)
                    | ((bytes[1] & 0xff) << 8)
                    | ((bytes[2] & 0xff) << 16)
                    | ((bytes[3] & 0xff) << 24);
        }

        byte[] bytes = toBytes();

        int value = bytes[0] & 0xFF;
        for (int i = 1; i < bytes.length; i++) {
            value |= (bytes[i] & 0xFF) << i * 8;
        }
        return value;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!HashCode.class.isInstance(o)) {
            return false;
        }

        HashCode that = HashCode.class.cast(o);
        return MessageDigest.isEqual(bytes, that.bytes);
    }

    @Override
    public String toString() {
        return String.format("HashCode {%s}", toHexString());
    }
}
