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

package fr.inria.atlanmod.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * Static utility methods related to {@code byte} arrays.
 */
@ParametersAreNonnullByDefault
public final class Bytes {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Bytes() {
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
        return new byte[]{(byte) (value ? 1 : 0)};
    }

    /**
     * Encodes a {@link Boolean} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Boolean value) {
        checkNotNull(value);

        return toBytes(value.booleanValue());
    }

    /**
     * Decodes a {@code byte} array to a {@code boolean}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code boolean}
     */
    public static boolean toBoolean(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == 1, "Array has wrong size: %d", bytes.length);

        return bytes[0] != (byte) 0;
    }

    /**
     * Encodes a {@code short} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final short value) {
        byte[] bytes = new byte[Short.BYTES];

        bytes[0] = (byte) (value >> 8);
        bytes[1] = (byte) (value);

        return bytes;
    }

    /**
     * Encodes a {@link Short} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Short value) {
        checkNotNull(value);

        return toBytes(value.shortValue());
    }

    /**
     * Decodes a {@code byte} array to a {@code short}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code short}
     */
    public static short toShort(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == Short.BYTES, "Array has wrong size: %d", bytes.length);

        short value = 0;

        value |= (bytes[0] & 0xff) << 8;
        value |= (bytes[1] & 0xff);

        return value;
    }

    /**
     * Encodes a {@code char} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @see #toBytes(short)
     */
    @Nonnull
    public static byte[] toBytes(final char value) {
        // Encoded as short
        return toBytes((short) value);
    }

    /**
     * Encodes a {@link Character} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Character value) {
        checkNotNull(value);

        return toBytes(value.charValue());
    }

    /**
     * Decodes a {@code byte} array to a {@code char}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code char}
     *
     * @see #toShort(byte[])
     */
    public static char toChar(final byte[] bytes) {
        // Decoded from short
        return (char) toShort(bytes);
    }

    /**
     * Encodes an {@code int} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final int value) {
        byte[] bytes = new byte[Integer.BYTES];

        bytes[0] = (byte) (value >> 24);
        bytes[1] = (byte) (value >> 16);
        bytes[2] = (byte) (value >> 8);
        bytes[3] = (byte) (value);

        return bytes;
    }

    /**
     * Encodes an {@link Integer} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Integer value) {
        checkNotNull(value);

        return toBytes(value.intValue());
    }

    /**
     * Decodes a {@code byte} array to an {@code int}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return an {@code int}
     */
    public static int toInt(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == Integer.BYTES, "Array has wrong size: %d", bytes.length);

        int value = 0;

        value |= (bytes[0] & 0xff) << 24;
        value |= (bytes[1] & 0xff) << 16;
        value |= (bytes[2] & 0xff) << 8;
        value |= (bytes[3] & 0xff);

        return value;
    }

    /**
     * Encodes a {@code long} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final long value) {
        byte[] bytes = new byte[Long.BYTES];

        bytes[0] = (byte) (value >> 56);
        bytes[1] = (byte) (value >> 48);
        bytes[2] = (byte) (value >> 40);
        bytes[3] = (byte) (value >> 32);
        bytes[4] = (byte) (value >> 24);
        bytes[5] = (byte) (value >> 16);
        bytes[6] = (byte) (value >> 8);
        bytes[7] = (byte) (value);

        return bytes;
    }

    /**
     * Encodes a {@link Long} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Long value) {
        checkNotNull(value);

        return toBytes(value.longValue());
    }

    /**
     * Decodes a {@code byte} array to a {@code long}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code long}
     */
    public static long toLong(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == Long.BYTES, "Array has wrong size: %d", bytes.length);

        long value = 0L;

        value |= (bytes[0] & 0xffL) << 56;
        value |= (bytes[1] & 0xffL) << 48;
        value |= (bytes[2] & 0xffL) << 40;
        value |= (bytes[3] & 0xffL) << 32;
        value |= (bytes[4] & 0xffL) << 24;
        value |= (bytes[5] & 0xffL) << 16;
        value |= (bytes[6] & 0xffL) << 8;
        value |= (bytes[7] & 0xffL);

        return value;
    }

    /**
     * Encodes a {@code float} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @see #toBytes(int)
     * @see Float#floatToIntBits(float)
     */
    @Nonnull
    public static byte[] toBytes(final float value) {
        // Encoded to integer
        return toBytes(Float.floatToIntBits(value));
    }

    /**
     * Encodes a {@link Float} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Float value) {
        checkNotNull(value);

        return toBytes(value.floatValue());
    }

    /**
     * Decodes a {@code byte} array to a {@code float}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code float}
     *
     * @see #toInt(byte[])
     * @see Float#intBitsToFloat(int)
     */
    public static float toFloat(final byte[] bytes) {
        checkNotNull(bytes);

        // Decoded from integer
        return Float.intBitsToFloat(toInt(bytes));
    }

    /**
     * Encodes a {@code double} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @see #toBytes(long)
     * @see Double#doubleToLongBits(double)
     */
    @Nonnull
    public static byte[] toBytes(final double value) {
        // Encoded as long
        return toBytes(Double.doubleToLongBits(value));
    }

    /**
     * Encodes a {@link Double} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final Double value) {
        checkNotNull(value);

        return toBytes(value.doubleValue());
    }

    /**
     * Decodes a {@code byte} array to a {@code double}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code double}
     *
     * @see #toLong(byte[])
     * @see Double#longBitsToDouble(long)
     */
    public static double toDouble(final byte[] bytes) {
        checkNotNull(bytes);

        // Decoded from long
        return Double.longBitsToDouble(toLong(bytes));
    }

    /**
     * Encodes a {@link String} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static byte[] toBytes(final String value) {
        checkNotNull(value);

        try {
            return value.getBytes(StandardCharsets.UTF_8);
        }
        catch (UnsupportedCharsetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Decodes a {@code byte} array to a {@link String}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@link String}
     */
    @Nonnull
    public static String toString(final byte[] bytes) {
        checkNotNull(bytes);

        try {
            return new String(bytes, StandardCharsets.UTF_8);
        }
        catch (UnsupportedCharsetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Encodes an {@link Object} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     */
    @Nonnull
    public static <T> byte[] toBytes(final T value) {
        checkNotNull(value);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(value);
            out.flush();
            return baos.toByteArray();
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Decodes a {@code byte} array to an {@link Object}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return an {@link Object}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T toObject(final byte[] bytes) {
        checkNotNull(bytes);

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInputStream in = new ObjectInputStream(bais)) {
            return (T) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
