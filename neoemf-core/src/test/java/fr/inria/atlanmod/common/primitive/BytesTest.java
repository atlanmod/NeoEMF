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

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link Bytes}.
 */
public class BytesTest extends AbstractTest {

    @Test
    public void booleanToBytes() {
        final byte zero = 0;

        byte[] actual0 = Booleans.toBytes(Boolean.TRUE);
        assertThat(actual0[0]).isNotEqualTo(zero);

        byte[] actual1 = Booleans.toBytes(Boolean.FALSE);
        assertThat(actual1[0]).isEqualTo(zero);
    }

    @Test
    public void bytesToBoolean() {
        boolean actual0 = Bytes.toBoolean(Booleans.toBytes(Boolean.TRUE));
        assertThat(actual0).isTrue();

        boolean actual1 = Bytes.toBoolean(Booleans.toBytes(Boolean.FALSE));
        assertThat(actual1).isFalse();
    }

    @Test
    public void shortToBytes() {
        final Short short0 = 28433;
        byte[] actual0 = Shorts.toBytes(short0);
        byte[] expected0 = ByteBuffer.allocate(Short.BYTES).putShort(short0).array();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToShort() {
        final short expected0 = 28433;
        byte[] bytes = ByteBuffer.allocate(Short.BYTES).putShort(expected0).array();
        short actual0 = Bytes.toShort(bytes);
        assertThat(actual0).isEqualTo(expected0);
    }

    @Test
    public void charToBytes() {
        final Character chart0 = 'N';
        byte[] actual0 = Chars.toBytes(chart0);
        byte[] expected0 = ByteBuffer.allocate(Character.BYTES).putChar(chart0).array();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToChar() {
        final char expected0 = 'N';
        byte[] bytes = ByteBuffer.allocate(Character.BYTES).putChar(expected0).array();
        char actual0 = Bytes.toChar(bytes);
        assertThat(actual0).isEqualTo(expected0);
    }

    @Test
    public void intToBytes() {
        final Integer int0 = 1654125381;
        byte[] actual0 = Ints.toBytes(int0);
        byte[] expected0 = ByteBuffer.allocate(Integer.BYTES).putInt(int0).array();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToInt() {
        final int expected0 = 1654125381;
        byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(expected0).array();
        int actual0 = Bytes.toInt(bytes);
        assertThat(actual0).isEqualTo(expected0);
    }

    @Test
    public void longToBytes() {
        final Long long0 = 1354566516474223156L;
        byte[] actual0 = Longs.toBytes(long0);
        byte[] expected0 = ByteBuffer.allocate(Long.BYTES).putLong(long0).array();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToLong() {
        final long long0 = 1354566516474223156L;
        byte[] bytes = ByteBuffer.allocate(Long.BYTES).putLong(long0).array();
        long actual0 = Bytes.toLong(bytes);
        assertThat(actual0).isEqualTo(long0);
    }

    @Test
    public void floatToBytes() {
        final Float float0 = 139895433915.09579569E18f;
        byte[] actual0 = Floats.toBytes(float0);
        byte[] expected0 = ByteBuffer.allocate(Float.BYTES).putFloat(float0).array();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToFloat() {
        final float expected0 = 139895433915.09579569E18f;
        byte[] bytes = ByteBuffer.allocate(Float.BYTES).putFloat(expected0).array();
        float actual0 = Bytes.toFloat(bytes);
        assertThat(actual0).isEqualTo(expected0);
    }

    @Test
    public void doubleToBytes() {
        final Double double0 = 19876412.08910810486479E196;
        byte[] actual0 = Doubles.toBytes(double0);
        byte[] expected0 = ByteBuffer.allocate(Double.BYTES).putDouble(double0).array();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToDouble() {
        final double expected0 = 19876412.08910810486479E196;
        byte[] bytes = ByteBuffer.allocate(Double.BYTES).putDouble(expected0).array();
        double actual0 = Bytes.toDouble(bytes);
        assertThat(actual0).isEqualTo(expected0);
    }

    @Test
    public void stringToBytes() {
        final String string0 = "NeoEMFisAwesome!";
        byte[] actual0 = Strings.toBytes(string0);
        byte[] expected0 = string0.getBytes();
        assertThat(actual0).containsExactly(expected0);
    }

    @Test
    public void bytesToString() {
        final String expected0 = "NeoEMFisAwesome!";
        byte[] bytes = expected0.getBytes();
        String actual0 = Bytes.toString(bytes);
        assertThat(actual0).isEqualTo(expected0);
    }

    @Test
    public void objectToBytes() {
        // TODO
    }

    @Test
    public void bytesToObject() {
        // TODO
    }

    @Test
    public void stringToBytesBinaryAndReverse() {
        String expected0 = "NeoEMFisAwesome!";
        byte[] bytes = Strings.toBytes(expected0);

        String actual0 = Bytes.toStringBinary(bytes);
        assertThat(actual0).isEqualTo("4e656f454d466973417765736f6d6521");

        byte[] actualBytes0 = Strings.toBytesBinary(actual0);
        assertThat(actualBytes0).contains(bytes);

        assertThat(Bytes.toString(actualBytes0)).isEqualTo(expected0);
    }
}