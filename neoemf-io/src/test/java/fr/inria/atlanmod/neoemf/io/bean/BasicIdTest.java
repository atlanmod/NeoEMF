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

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link BasicId}.
 */
public class BasicIdTest extends AbstractTest {

    @Test
    public void testOriginal() {
        String value0 = "id0";

        BasicId id0 = BasicId.original(value0);
        assertThat(id0.value()).isEqualTo(value0);
        assertThat(id0.isGenerated()).isFalse();
    }

    @Test
    public void testGenerated() {
        String value0 = "id0";

        BasicId id0 = BasicId.generated(value0);
        assertThat(id0.value()).isEqualTo(value0);
        assertThat(id0.isGenerated()).isTrue();
    }

    @Test
    public void testHashCode() {
        String value0 = "id0";
        String value1 = "id1";

        BasicId id0 = BasicId.original(value0);
        BasicId id0Bis = BasicId.generated(value0);
        BasicId id1 = BasicId.original(value1);

        assertThat(id0.hashCode()).isEqualTo(id0Bis.hashCode());
        assertThat(id0.hashCode()).isNotEqualTo(id1.hashCode());
        assertThat(id1.hashCode()).isNotEqualTo(id0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        String value0 = "id0";
        String value1 = "id1";

        BasicId id0 = BasicId.original(value0);
        BasicId id0Bis = BasicId.generated(value0);
        BasicId id1 = BasicId.original(value1);

        assertThat(id0).isEqualTo(id0Bis);
        assertThat(id0).isNotEqualTo(id1);
        assertThat(id1).isNotEqualTo(id0Bis);
    }
}