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
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case about {@link IdProvider} and {@link StringId}.
 */
public class IdProviderTest extends AbstractTest {

    @Test
    public void testCreate() throws Exception {
        String value = "thisIsAnId";

        Id id = IdProvider.create(value);

        assertThat(id.toString()).isEqualTo(value);
    }

    @Test
    public void testGenerate() throws Exception {
        Id id0 = IdProvider.generate();
        Id id1 = IdProvider.generate();

        assertThat(id0.toString().trim()).isNotEmpty();
        assertThat(id1.toString().trim()).isNotEmpty();

        assertThat(id0).isNotEqualTo(id1);
    }

    @Test
    public void testCompareTo() throws Exception {
        Id id0 = IdProvider.create("aaa");
        Id id0Bis = IdProvider.create("aaa");
        Id id1 = IdProvider.create("ZZZ");

        assertThat(id0).isEqualByComparingTo(id0Bis);
        assertThat(id0).isLessThan(id1);
        assertThat(id1).isGreaterThan(id0Bis);
    }

    @Test
    public void testToLong() throws Exception {
        assertThat(catchThrowable(() ->
                IdProvider.generate().toLong()
        )).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testHashCode() throws Exception {
        Id id0 = IdProvider.create("aaa");
        Id id0Bis = IdProvider.create("aaa");
        Id id1 = IdProvider.create("ZZZ");

        assertThat(id0.hashCode()).isEqualTo(id0Bis.hashCode());
        assertThat(id1.hashCode()).isNotEqualTo(id0Bis.hashCode());
        assertThat(id0.hashCode()).isNotEqualTo(id1.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        Id id0 = IdProvider.create("aaa");
        Id id0Bis = IdProvider.create("aaa");
        Id id1 = IdProvider.create("ZZZ");

        assertThat(id0).isEqualTo(id0Bis);
        assertThat(id1).isNotEqualTo(id0Bis);
        assertThat(id0).isNotEqualTo(id1);
    }

    @Test
    public void testToString() throws Exception {
        String value = "thisIsAnId";

        Id id = IdProvider.create(value);

        assertThat(id).hasToString(value);
    }
}