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
import fr.inria.atlanmod.neoemf.core.Id;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BasicAttribute}.
 */
public class BasicAttributeTest extends AbstractTest {

    @Test
    public void testName() {
        String name0 = "attribute0";
        String name1 = "attribute1";

        BasicAttribute attr0 = new BasicAttribute();
        attr0.name(name0);
        assertThat(attr0.name()).isEqualTo(name0);

        BasicAttribute attr1 = new BasicAttribute();
        attr1.name(name1);
        assertThat(attr1.name()).isEqualTo(name1);

        assertThat(attr0.name()).isNotEqualTo(attr1.name());
    }

    @Test
    public void testId() {
        BasicAttribute attr0 = new BasicAttribute();
        attr0.name("attribute0");
        assertThat(attr0.owner()).isNull();

        Id id0 = Id.getProvider().fromLong(0);
        Id id1 = Id.getProvider().fromLong(1);

        attr0.owner(id0);
        assertThat(attr0.owner()).isEqualTo(id0);

        attr0.owner(id1);
        assertThat(attr0.owner()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    public void testMany() {
        BasicAttribute attr0 = new BasicAttribute();
        attr0.name("attribute0");
        assertThat(attr0.isMany()).isFalse();

        attr0.isMany(true);
        assertThat(attr0.isMany()).isTrue();

        attr0.isMany(false);
        assertThat(attr0.isMany()).isFalse();
    }

    @Test
    public void testValue() throws Exception {
        BasicAttribute attr0 = new BasicAttribute();
        attr0.name("attribute0");
        assertThat(attr0.value()).isNull();

        String value0 = "value0";
        String value1 = "value1";

        attr0.value(value0);
        assertThat(attr0.value()).isEqualTo(value0);

        attr0.value(value1);
        assertThat(attr0.value()).isNotEqualTo(value0).isEqualTo(value1);
    }

    @Test
    public void testHashCode() {
        BasicAttribute attr0 = new BasicAttribute();
        attr0.name("attribute0");
        BasicAttribute attr0Bis = new BasicAttribute();
        attr0Bis.name("attribute0");
        BasicAttribute attr1 = new BasicAttribute();
        attr1.name("attribute1");

        assertThat(attr0.hashCode()).isEqualTo(attr0Bis.hashCode());
        assertThat(attr0.hashCode()).isNotEqualTo(attr1.hashCode());
        assertThat(attr1.hashCode()).isNotEqualTo(attr0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        BasicAttribute attr0 = new BasicAttribute();
        attr0.name("attribute0");
        BasicAttribute attr0Bis = new BasicAttribute();
        attr0Bis.name("attribute0");
        BasicAttribute attr1 = new BasicAttribute();
        attr1.name("attribute1");

        assertThat(attr0).isEqualTo(attr0Bis);
        assertThat(attr0).isNotEqualTo(attr1);
        assertThat(attr1).isNotEqualTo(attr0Bis);
    }
}