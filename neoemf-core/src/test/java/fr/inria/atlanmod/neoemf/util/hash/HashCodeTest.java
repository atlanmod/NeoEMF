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

package fr.inria.atlanmod.neoemf.util.hash;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the specific behavior of {@link HashCode}.
 */
public class HashCodeTest {

    private HashCode hash;

    @Before
    public void setUp() {
        hash = new HashCode("HashCode0".getBytes(Charset.forName("UTF-8")));
    }

    @Test
    public void testBits() {
        assertThat(hash.bits()).isEqualTo(72);
    }

    @Test
    public void testToBytes() {
        assertThat(hash.toBytes()).isEqualTo("HashCode0".getBytes(Charset.forName("UTF-8")));
    }

    @Test
    public void testInvalidToInt() {
        Throwable thrown = catchThrowable(() -> new HashCode(new byte[]{}).toInt());
        assertThat(thrown).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testToInt() {
        assertThat(hash.toInt()).isEqualTo(1752392008);
    }

    @Test
    public void testToLong() {
        assertThat(hash.toLong()).isEqualTo(7306086830807671112L);
    }

    @Test
    public void testInvalidToLong() {
        Throwable thrown = catchThrowable(() -> new HashCode(new byte[]{}).toLong());
        assertThat(thrown).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testHashCode() {
        assertThat(hash.hashCode()).isEqualTo(hash.toInt());

        HashCode littleHash = new HashCode("HC".getBytes(Charset.forName("UTF-8")));
        assertThat(littleHash.bits()).isLessThan(32);
        assertThat(littleHash.hashCode()).isEqualTo(17224);
    }

    @Test
    public void testEquals() {
        assertThat(Objects.equals(hash, hash)).isTrue();

        assertThat(Objects.equals(hash, null)).isFalse();

        assertThat(Objects.equals(hash, new HashCode("HashCode0".getBytes(Charset.forName("UTF-8"))))).isTrue();
        assertThat(Objects.equals(hash, new HashCode("HC".getBytes(Charset.forName("UTF-8"))))).isFalse();
    }

    @Test
    public void testToString() {
        assertThat(hash.toString()).isEqualToIgnoringCase("48617368436f646530");
    }
}