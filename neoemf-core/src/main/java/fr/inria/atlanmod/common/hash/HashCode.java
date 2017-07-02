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

import java.io.Serializable;
import java.security.MessageDigest;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static fr.inria.atlanmod.common.Preconditions.checkState;

/**
 * An immutable hash code of arbitrary bit length.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class HashCode implements Serializable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -576482022539994714L;

    /**
     * The valid hexadecimal values.
     *
     * @see #toString()
     */
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

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
     * Returns the first four bytes of {@link #toBytes()}, converted to an {@code int} value in little-endian order.
     *
     * @return an integer
     */
    public int toInt() {
        checkState(bytes.length >= 4, "HashCode#toInt() requires at least 4 bytes (it only has %s bytes).", bytes.length);

        return (bytes[0] & 0xFF)
                | ((bytes[1] & 0xFF) << 8)
                | ((bytes[2] & 0xFF) << 16)
                | ((bytes[3] & 0xFF) << 24);
    }

    /**
     * Returns the first eight bytes of {@link #toBytes()}, converted to a {@code long} value in little-endian order.
     *
     * @return a long
     */
    public long toLong() {
        checkState(bytes.length >= 8, "HashCode#toLong() requires at least 8 bytes (it only has %s bytes).", bytes.length);

        long value = bytes[0] & 0xFF;
        for (int i = 1; i < Math.min(bytes.length, 8); i++) {
            value |= (bytes[i] & 0xFFL) << (i * 8);
        }

        return value;
    }

    @Override
    public int hashCode() {
        if (bits() >= 32) {
            return toInt();
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

    /**
     * Returns a string containing each byte of {@link #toBytes()}, in order, as a two-digit unsigned hexadecimal number
     * in lower case.
     *
     * @return a string
     */
    @Nonnull
    public String toString() {
        byte[] bytes = toBytes();

        StringBuilder sb = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            sb.append(HEX_DIGITS[(b >> 4) & 0xf]).append(HEX_DIGITS[b & 0xf]);
        }

        return sb.toString();
    }
}
