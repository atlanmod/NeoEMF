/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

import fr.inria.atlanmod.neoemf.core.Id;

import org.atlanmod.commons.AbstractTest;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link ProxyElement}.
 */
@ParametersAreNonnullByDefault
class ProxyElementTest extends AbstractTest {

    @Test
    void testName() {
        String name0 = "element0";
        String name1 = "element1";

        ProxyElement elt0 = new ProxyElement();
        elt0.setName(name0);
        assertThat(elt0.getName()).isEqualTo(name0);

        ProxyElement elt1 = new ProxyElement();
        elt1.setName(name1);
        assertThat(elt1.getName()).isEqualTo(name1);

        assertThat(elt0.getName()).isNotEqualTo(elt1.getName());
    }

    @Test
    void testId() {
        ProxyElement elt0 = new ProxyElement();
        assertThat(elt0.getId().isPresent()).isFalse();

        Id id0 = Id.getProvider().fromLong(42);
        Id id1 = Id.getProvider().fromLong(44);

        elt0.setId(ProxyValue.resolved(id0));
        assertThat(elt0.getId().getResolved()).isEqualTo(id0);

        elt0.setId(ProxyValue.resolved(id1));
        assertThat(elt0.getId().getResolved()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    void testMetaclass() {
        ProxyPackage ns = ProxyPackage.DEFAULT;

        ProxyElement elt0 = new ProxyElement();
        assertThat(elt0.getMetaClass()).isNull();

        ProxyClass mc0 = new ProxyClass(ns, "mc0");
        ProxyClass mc1 = new ProxyClass(ns, "mc1");

        elt0.setMetaClass(mc0);
        assertThat(elt0.getMetaClass()).isEqualTo(mc0);

        elt0.setMetaClass(mc1);
        assertThat(elt0.getMetaClass()).isNotEqualTo(mc0).isEqualTo(mc1);
    }

    @Test
    void testRoot() {
        ProxyElement elt0 = new ProxyElement();
        assertThat(elt0.isRoot()).isFalse();

        elt0.setRoot(true);
        assertThat(elt0.isRoot()).isTrue();

        elt0.setRoot(false);
        assertThat(elt0.isRoot()).isFalse();
    }

    @Test
    void testHashCode() {
        ProxyElement elt0 = new ProxyElement();
        elt0.setId(ProxyValue.resolved(Id.getProvider().fromLong(42)));

        ProxyElement elt0Bis = new ProxyElement();
        elt0Bis.setId(ProxyValue.resolved(Id.getProvider().fromLong(42)));

        ProxyElement elt1 = new ProxyElement();
        elt1.setId(ProxyValue.resolved(Id.getProvider().fromLong(44)));

        assertThat(elt0.hashCode()).isEqualTo(elt0Bis.hashCode());
        assertThat(elt0.hashCode()).isNotEqualTo(elt1.hashCode());
        assertThat(elt1.hashCode()).isNotEqualTo(elt0Bis.hashCode());
    }

    @Test
    void testEquals() {
        ProxyElement elt0 = new ProxyElement();
        elt0.setId(ProxyValue.resolved(Id.getProvider().fromLong(42)));

        ProxyElement elt0Bis = new ProxyElement();
        elt0Bis.setId(ProxyValue.resolved(Id.getProvider().fromLong(42)));

        ProxyElement elt1 = new ProxyElement();
        elt1.setId(ProxyValue.resolved(Id.getProvider().fromLong(44)));

        assertThat(elt0).isEqualTo(elt0Bis);
        assertThat(elt0).isNotEqualTo(elt1);
        assertThat(elt1).isNotEqualTo(elt0Bis);

        assertThat(elt0).isEqualTo(elt0);
        assertThat(elt0).isNotEqualTo(null);
        assertThat(elt0).isNotEqualTo(0);
    }

    @Test
    void testToString() {
        ProxyElement elt0 = new ProxyElement();
        elt0.setName("Element0");

        assertThat(elt0).hasToString("Element0");
    }
}