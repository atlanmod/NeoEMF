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

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link HashCode}.
 */
public class HashCodeTest extends AbstractTest {

    private final static HashCode HASH = new HashCode("HashCode0".getBytes(StandardCharsets.UTF_8));

    @Test
    public void testBits() {
        assertThat(HASH.bits()).isEqualTo(72);
    }

    @Test
    public void testToBytes() {
        assertThat(HASH.toBytes()).isEqualTo("HashCode0".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testToHexString() {
        assertThat(HASH.toHexString()).isEqualToIgnoringCase("48617368436f646530");
    }

    @Test
    public void testHashCode() {
        final byte[] bytes = HASH.toBytes();
        int hashCode = (bytes[0] & 0xff)
                | ((bytes[1] & 0xff) << 8)
                | ((bytes[2] & 0xff) << 16)
                | ((bytes[3] & 0xff) << 24);

        assertThat(HASH.hashCode()).isEqualTo(hashCode);

        HashCode littleHash = new HashCode("HC".getBytes(Charset.forName("UTF-8")));
        assertThat(littleHash.bits()).isLessThan(32);
        assertThat(littleHash.hashCode()).isEqualTo(17224);
    }

    @Test
    public void testEquals() {
        //noinspection EqualsWithItself,EqualsReplaceableByObjectsCall
        assertThat(HASH.equals(HASH)).isTrue();

        //noinspection ObjectEqualsNull,EqualsReplaceableByObjectsCall
        assertThat(HASH.equals(null)).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(HASH.equals(new HashCode("HashCode0".getBytes(Charset.forName("UTF-8"))))).isTrue();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(HASH.equals(new HashCode("HC".getBytes(Charset.forName("UTF-8"))))).isFalse();
    }
}