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
import fr.inria.atlanmod.neoemf.core.StringId;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link BasicReference}.
 */
public class BasicReferenceTest extends AbstractTest {

    @Test
    public void testName() {
        String name0 = "reference0";
        String name1 = "reference1";

        BasicReference ref0 = new BasicReference();
        ref0.name(name0);
        assertThat(ref0.name()).isEqualTo(name0);

        BasicReference ref1 = new BasicReference();
        ref1.name(name1);
        assertThat(ref1.name()).isEqualTo(name1);

        assertThat(ref0.name()).isNotEqualTo(ref1.name());
    }

    @Test
    public void testId() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        assertThat(ref0.owner()).isNull();

        Id id0 = StringId.of("id0");
        Id id1 = StringId.of("id1");

        ref0.owner(id0);
        assertThat(ref0.owner()).isEqualTo(id0);

        ref0.owner(id1);
        assertThat(ref0.owner()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    public void testMany() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        assertThat(ref0.isMany()).isFalse();

        ref0.isMany(true);
        assertThat(ref0.isMany()).isTrue();

        ref0.isMany(false);
        assertThat(ref0.isMany()).isFalse();
    }

    @Test
    public void testIdReference() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        assertThat(ref0.value()).isNull();

        Id idRef0 = StringId.of("idRef0");
        Id idRef1 = StringId.of("idRef1");

        ref0.value(idRef0);
        assertThat(ref0.value()).isEqualTo(idRef0);

        ref0.value(idRef1);
        assertThat(ref0.value()).isNotEqualTo(idRef0).isEqualTo(idRef1);
    }

    @Test
    public void testContainment() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        assertThat(ref0.isContainment()).isFalse();

        ref0.isContainment(true);
        assertThat(ref0.isContainment()).isTrue();

        ref0.isContainment(false);
        assertThat(ref0.isContainment()).isFalse();
    }

    @Test
    public void testHashCode() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        BasicReference ref0Bis = new BasicReference();
        ref0Bis.name("reference0");
        BasicReference ref1 = new BasicReference();
        ref1.name("reference1");

        assertThat(ref0.hashCode()).isEqualTo(ref0Bis.hashCode());
        assertThat(ref0.hashCode()).isNotEqualTo(ref1.hashCode());
        assertThat(ref1.hashCode()).isNotEqualTo(ref0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        BasicReference ref0Bis = new BasicReference();
        ref0Bis.name("reference0");
        BasicReference ref1 = new BasicReference();
        ref1.name("reference1");

        assertThat(ref0).isEqualTo(ref0Bis);
        assertThat(ref0).isNotEqualTo(ref1);
        assertThat(ref1).isNotEqualTo(ref0Bis);
    }
}