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
 * A test-case that checks the behavior of {@link BasicElement}.
 */
public class BasicElementTest extends AbstractTest {

    @Test
    public void testName() {
        String name0 = "element0";
        String name1 = "element1";

        BasicNamespace ns = BasicNamespace.getDefault();

        BasicElement elt0 = new BasicElement(ns, name0);
        assertThat(elt0.name()).isEqualTo(name0);

        BasicElement elt1 = new BasicElement(ns, name1);
        assertThat(elt1.name()).isEqualTo(name1);

        assertThat(elt0.name()).isNotEqualTo(elt1.name());
    }

    @Test
    public void testId() {
        BasicElement elt0 = new BasicElement(BasicNamespace.getDefault(), "element0");
        assertThat(elt0.id()).isNull();

        BasicId id0 = BasicId.original("id0");
        BasicId id1 = BasicId.generated("id1");

        elt0.id(id0);
        assertThat(elt0.id()).isEqualTo(id0);

        elt0.id(id1);
        assertThat(elt0.id()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    public void testClassName() {
        BasicElement elt0 = new BasicElement(BasicNamespace.getDefault(), "element0");
        assertThat(elt0.className()).isNull();

        String className0 = "cn0";
        String className1 = "cn1";

        elt0.className(className0);
        assertThat(elt0.className()).isEqualTo(className0);

        elt0.className(className1);
        assertThat(elt0.className()).isNotEqualTo(className0).isEqualTo(className1);
    }

    @Test
    public void testMetaclass() {
        BasicNamespace ns = BasicNamespace.getDefault();

        BasicElement elt0 = new BasicElement(ns, "element0");
        assertThat(elt0.metaClass()).isNull();

        BasicMetaclass mc0 = new BasicMetaclass(ns, "mc0");
        BasicMetaclass mc1 = new BasicMetaclass(ns, "mc1");

        elt0.metaClass(mc0);
        assertThat(elt0.metaClass()).isEqualTo(mc0);

        elt0.metaClass(mc1);
        assertThat(elt0.metaClass()).isNotEqualTo(mc0).isEqualTo(mc1);
    }

    @Test
    public void testRoot() {
        BasicElement elt0 = new BasicElement(BasicNamespace.getDefault(), "element0");
        assertThat(elt0.isRoot()).isFalse();

        elt0.isRoot(true);
        assertThat(elt0.isRoot()).isTrue();

        elt0.isRoot(false);
        assertThat(elt0.isRoot()).isFalse();
    }

    @Test
    public void testHashCode() {
        BasicNamespace ns = BasicNamespace.getDefault();

        BasicElement elt0 = new BasicElement(ns, "element0");
        BasicElement elt0Bis = new BasicElement(ns, "element0");
        BasicElement elt1 = new BasicElement(ns, "element1");

        assertThat(elt0.hashCode()).isEqualTo(elt0Bis.hashCode());
        assertThat(elt0.hashCode()).isNotEqualTo(elt1.hashCode());
        assertThat(elt1.hashCode()).isNotEqualTo(elt0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        BasicNamespace ns = BasicNamespace.getDefault();

        BasicElement elt0 = new BasicElement(ns, "element0");
        BasicElement elt0Bis = new BasicElement(ns, "element0");
        BasicElement elt1 = new BasicElement(ns, "element1");

        assertThat(elt0).isEqualTo(elt0Bis);
        assertThat(elt0).isNotEqualTo(elt1);
        assertThat(elt1).isNotEqualTo(elt0Bis);
    }
}