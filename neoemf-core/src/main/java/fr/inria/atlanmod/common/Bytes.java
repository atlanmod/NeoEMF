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
     * The value 0.
     *
     * @see Boolean#FALSE
     */
    private static final byte ZERO = 0;

    /**
     * The value 1.
     *
     * @see Boolean#TRUE
     */
    private static final byte ONE = 1;

    /**
     * The valid hexadecimal values.
     *
     * @see #toStringBinary(byte[])
     */
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

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
        return new byte[]{value ? ONE : ZERO};
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

    /**
     * Decodes a {@code byte} array to a {@code boolean}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@code boolean}
     *
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@code 1}
     */
    public static boolean toBoolean(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == 1, "Array has wrong size: %d", bytes.length);

        return bytes[0] != ZERO;
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

        final int lenght = Short.BYTES - 1;
        for (int i = lenght; i >= 0; i--) {
            bytes[i] = (byte) (value >> Byte.SIZE * (lenght - i));
        }

        return bytes;
    }

    /**
     * Encodes a {@link Short} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     *
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@link Short#BYTES}
     */
    public static short toShort(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == Short.BYTES, "Array has wrong size: %d", bytes.length);

        short value = 0;

        final int lenght = Short.BYTES - 1;
        for (int i = lenght; i >= 0; i--) {
            value |= (bytes[i] & 0xff) << Byte.SIZE * (lenght - i);
        }

        return value;
    }

    /**
     * Encodes a {@code char} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@link Character#BYTES}
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

        final int lenght = Integer.BYTES - 1;
        for (int i = lenght; i >= 0; i--) {
            bytes[i] = (byte) (value >> Byte.SIZE * (lenght - i));
        }

        return bytes;
    }

    /**
     * Encodes an {@link Integer} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     *
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@link Integer#BYTES}
     */
    public static int toInt(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == Integer.BYTES, "Array has wrong size: %d", bytes.length);

        int value = 0;

        final int lenght = Integer.BYTES - 1;
        for (int i = lenght; i >= 0; i--) {
            value |= (bytes[i] & 0xff) << Byte.SIZE * (lenght - i);
        }

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

        final int lenght = Long.BYTES - 1;
        for (int i = lenght; i >= 0; i--) {
            bytes[i] = (byte) (value >> Byte.SIZE * (lenght - i));
        }

        return bytes;
    }

    /**
     * Encodes a {@link Long} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     *
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@link Long#BYTES}
     */
    public static long toLong(final byte[] bytes) {
        checkNotNull(bytes);
        checkArgument(bytes.length == Long.BYTES, "Array has wrong size: %d", bytes.length);

        long value = 0L;

        final int lenght = Long.BYTES - 1;
        for (int i = lenght; i >= 0; i--) {
            value |= (bytes[i] & 0xffL) << Byte.SIZE * (lenght - i);
        }

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
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@link Float#BYTES}
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
     *
     * @throws NullPointerException if the {@code value} is {@code null}
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
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if the {@code bytes.length} is different from {@link Double#BYTES}
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
     *
     * @throws NullPointerException     if the {@code value} is {@code null}
     * @throws IllegalArgumentException if {@link StandardCharsets#UTF_8} is not a supported encoding
     */
    @Nonnull
    public static byte[] toBytes(final String value) {
        checkNotNull(value);

        try {
            return value.getBytes(StandardCharsets.UTF_8);
        }
        catch (UnsupportedCharsetException e) {
            // Should never happen
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Decodes a {@code byte} array to a {@link String}.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@link String}
     *
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if {@link StandardCharsets#UTF_8} is not a supported encoding
     */
    @Nonnull
    public static String toString(final byte[] bytes) {
        checkNotNull(bytes);

        try {
            return new String(bytes, StandardCharsets.UTF_8);
        }
        catch (UnsupportedCharsetException e) {
            // Should never happen
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Encodes a {@link String} to a {@code byte} array, where each {@code byte} is represented as a two-digit
     * unsigned hexadecimal number in lower case.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException     if the {@code value} is {@code null}
     * @throws IllegalArgumentException if the {@link String#length() length} of the {@code value} is not even.
     * @see #toStringBinary(byte[])
     */
    @Nonnull
    public static byte[] toBytesBinary(final String value) {
        checkNotNull(value);
        checkArgument(value.length() % 2 == 0);

        byte[] bytes = new byte[value.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((toHexDigit(value.charAt(i * 2)) << 4) + toHexDigit(value.charAt(i * 2 + 1)));
        }

        return bytes;
    }

    /**
     * Retrieves the digit of the given hexadecimal {@code char}.
     *
     * @param c the character to look for
     *
     * @return the digit
     *
     * @throws IllegalArgumentException if the {@code char} is not an hex digit
     */
    private static int toHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }

        char lower = Character.toLowerCase(c);
        if (lower >= 'a' && lower <= 'f') {
            return lower - 'a' + 10;
        }

        throw new IllegalArgumentException(String.format("Unexpected hex digit: %c", c));
    }

    /**
     * Decodes a {@code byte} array to a {@link String} that contains each byte, in order, as a two-digit unsigned
     * hexadecimal number in lower case.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return a {@link String}
     *
     * @throws NullPointerException if the {@code bytes} is {@code null}
     * @see #toBytesBinary(String)
     */
    @Nonnull
    public static String toStringBinary(final byte[] bytes) {
        checkNotNull(bytes);

        char[] result = new char[bytes.length * 2];

        int i = 0;
        for (byte b : bytes) {
            result[i++] = HEX_DIGITS[b >> 4 & 0xf];
            result[i++] = HEX_DIGITS[b & 0xf];
        }

        return new String(result);
    }

    /**
     * Encodes an {@link Object} to a {@code byte} array.
     *
     * @param value the value to encode
     *
     * @return a {@code byte} array
     *
     * @throws NullPointerException     if the {@code value} is {@code null}
     * @throws IllegalArgumentException if an error occurs during the encoding
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
     *
     * @throws NullPointerException     if the {@code bytes} is {@code null}
     * @throws IllegalArgumentException if an error occurs during the decoding
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
