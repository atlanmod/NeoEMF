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

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link IdProvider} and {@link Id} implementations.
 */
public class IdProviderTest extends AbstractTest {

    @Test
    public void testCreate() throws Exception {
        String value0 = "123456789a"; // Hexadecimal form

        Id id0 = Id.getProvider().fromHexString(value0);
        assertThat(id0.toHexString()).isEqualTo("000000123456789a"); // Hexadecimal form

        long value1 = 123456789;

        Id id1 = Id.getProvider().fromLong(value1);
        assertThat(id1.toLong()).isEqualTo(value1);
    }

    @Test
    public void testGenerate() throws Exception {
        Id id0 = Id.getProvider().generate();
        Id id1 = Id.getProvider().generate();

        assertThat(id0.toHexString().trim()).isNotEmpty();
        assertThat(id1.toHexString().trim()).isNotEmpty();

        assertThat(id0).isNotEqualTo(id1);
    }

    @Test
    public void testCompareTo() throws Exception {
        Id id0 = Id.getProvider().fromLong(42);
        Id id0Bis = Id.getProvider().fromLong(42);
        Id id1 = Id.getProvider().fromLong(44);

        assertThat(id0).isEqualByComparingTo(id0Bis);
        assertThat(id0).isLessThan(id1);
        assertThat(id1).isGreaterThan(id0Bis);
    }

    @Test
    public void testToLong() throws Exception {
        long value = 123456789;

        Id id = Id.getProvider().fromLong(value);

        assertThat(id.toLong()).isEqualTo(value);
    }

    @Test
    public void testHashCode() throws Exception {
        Id id0 = Id.getProvider().fromLong(42);
        Id id0Bis = Id.getProvider().fromLong(42);
        Id id1 = Id.getProvider().fromLong(44);

        assertThat(id0.hashCode()).isEqualTo(id0Bis.hashCode());
        assertThat(id1.hashCode()).isNotEqualTo(id0Bis.hashCode());
        assertThat(id0.hashCode()).isNotEqualTo(id1.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        Id id0 = Id.getProvider().fromLong(42);
        Id id0Bis = Id.getProvider().fromLong(42);
        Id id1 = Id.getProvider().fromLong(44);

        assertThat(id0).isEqualTo(id0Bis);
        assertThat(id1).isNotEqualTo(id0Bis);
        assertThat(id0).isNotEqualTo(id1);
    }

    @Test
    public void testToHexString() throws Exception {
        String value = "123456789a"; // Hexadecimal form

        Id id = Id.getProvider().fromHexString(value);

        assertThat(id.toHexString()).isEqualTo("000000123456789a"); // Hexadecimal form
    }

    @Test
    public void testConversion() {
        long value = 123456789;

        Id id0 = Id.getProvider().fromLong(value);
        String hexValue = id0.toHexString();

        Id id1 = Id.getProvider().fromHexString(hexValue);
        assertThat(id0.toLong()).isEqualTo(id1.toLong());
    }

    @Test
    public void testRoot() {
        String value = "ROOT";

        Id id = Id.getProvider().generate(value);

        assertThat(id.toHexString()).isEqualTo("a5c969fb2da0c7ea");
        assertThat(id.toLong()).isEqualTo(-6500548059609380886L);
    }
}