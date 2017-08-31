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

        BasicId id0 = BasicId.original("id0");
        BasicId id1 = BasicId.generated("id1");

        ref0.owner(id0);
        assertThat(ref0.owner()).isEqualTo(id0);

        ref0.owner(id1);
        assertThat(ref0.owner()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    public void testIndex() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        assertThat(ref0.index()).isEqualTo(-1);

        int index0 = 42;
        int index1 = 17;

        ref0.index(index0);
        assertThat(ref0.index()).isEqualTo(index0);

        ref0.index(index1);
        assertThat(ref0.index()).isNotEqualTo(index0).isEqualTo(index1);
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
        assertThat(ref0.idReference()).isNull();

        BasicId idRef0 = BasicId.original("idRef0");
        BasicId idRef1 = BasicId.generated("idRef1");

        ref0.idReference(idRef0);
        assertThat(ref0.idReference()).isEqualTo(idRef0);

        ref0.idReference(idRef1);
        assertThat(ref0.idReference()).isNotEqualTo(idRef0).isEqualTo(idRef1);
    }

    @Test
    public void testMetaclassReference() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");
        assertThat(ref0.metaClassReference()).isNull();

        BasicNamespace ns0 = BasicNamespace.getDefault();
        BasicMetaclass metaClass0 = new BasicMetaclass(ns0, "mc0");
        BasicMetaclass metaClass1 = new BasicMetaclass(ns0, "mc1");

        ref0.metaClassReference(metaClass0);
        assertThat(ref0.metaClassReference()).isEqualTo(metaClass0);

        ref0.metaClassReference(metaClass1);
        assertThat(ref0.metaClassReference()).isNotEqualTo(metaClass0).isEqualTo(metaClass1);
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
    public void testIsReference() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");

        assertThat(ref0.isReference()).isTrue();
    }

    @Test
    public void testIsAttribute() {
        BasicReference ref0 = new BasicReference();
        ref0.name("reference0");

        assertThat(ref0.isAttribute()).isFalse();
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

    @Test
    public void testFrom() {
        BasicId id0 = BasicId.original("id0");
        int index0 = 42;
        String value0 = "value0";

        BasicAttribute attr0 = new BasicAttribute();
        attr0.name("feature0");
        attr0.owner(id0);
        attr0.index(index0);
        attr0.value(value0);

        BasicReference ref0 = BasicReference.from(attr0);
        assertThat(ref0.owner()).isEqualTo(id0);
        assertThat(ref0.index()).isEqualTo(index0);
        assertThat(ref0.idReference().value()).isEqualTo(value0);
    }
}