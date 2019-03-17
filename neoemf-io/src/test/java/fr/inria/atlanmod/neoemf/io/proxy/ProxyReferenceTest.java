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
 * A test-case about {@link ProxyReference}.
 */
@ParametersAreNonnullByDefault
class ProxyReferenceTest extends AbstractTest {

    @Test
    void testName() {
        String name0 = "reference0";
        String name1 = "reference1";

        ProxyReference ref0 = new ProxyReference();
        ref0.setName(name0);
        assertThat(ref0.getName()).isEqualTo(name0);

        ProxyReference ref1 = new ProxyReference();
        ref1.setName(name1);
        assertThat(ref1.getName()).isEqualTo(name1);

        assertThat(ref0.getName()).isNotEqualTo(ref1.getName());
    }

    @Test
    void testId() {
        ProxyReference ref0 = new ProxyReference();
        ref0.setName("reference0");
        assertThat(ref0.getOwner()).isNull();

        Id id0 = Id.getProvider().fromLong(42);
        Id id1 = Id.getProvider().fromLong(44);

        ref0.setOwner(id0);
        assertThat(ref0.getOwner()).isEqualTo(id0);

        ref0.setOwner(id1);
        assertThat(ref0.getOwner()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    void testMany() {
        ProxyReference ref0 = new ProxyReference();
        ref0.setName("reference0");
        assertThat(ref0.isMany()).isFalse();

        ref0.setMany(true);
        assertThat(ref0.isMany()).isTrue();

        ref0.setMany(false);
        assertThat(ref0.isMany()).isFalse();
    }

    @Test
    void testIdReference() {
        ProxyReference ref0 = new ProxyReference();
        ref0.setName("reference0");
        assertThat(ref0.getValue().isPresent()).isFalse();

        Id idRef0 = Id.getProvider().fromLong(42);
        Id idRef1 = Id.getProvider().fromLong(44);

        ref0.setValue(ProxyValue.resolved(idRef0));
        assertThat(ref0.getValue().getResolved()).isEqualTo(idRef0);

        ref0.setValue(ProxyValue.resolved(idRef1));
        assertThat(ref0.getValue().getResolved()).isNotEqualTo(idRef0).isEqualTo(idRef1);
    }

    @Test
    void testContainment() {
        ProxyReference ref0 = new ProxyReference();
        ref0.setName("reference0");
        assertThat(ref0.isContainment()).isFalse();

        ref0.isContainment(true);
        assertThat(ref0.isContainment()).isTrue();

        ref0.isContainment(false);
        assertThat(ref0.isContainment()).isFalse();
    }

    @Test
    void testHashCode() {
        ProxyReference ref0 = new ProxyReference();
        ref0.setName("reference0");
        ProxyReference ref0Bis = new ProxyReference();
        ref0Bis.setName("reference0");
        ProxyReference ref1 = new ProxyReference();
        ref1.setName("reference1");

        assertThat(ref0.hashCode()).isEqualTo(ref0Bis.hashCode());
        assertThat(ref0.hashCode()).isNotEqualTo(ref1.hashCode());
        assertThat(ref1.hashCode()).isNotEqualTo(ref0Bis.hashCode());
    }

    @Test
    void testEquals() {
        ProxyReference ref0 = new ProxyReference();
        ref0.setName("reference0");
        ProxyReference ref0Bis = new ProxyReference();
        ref0Bis.setName("reference0");
        ProxyReference ref1 = new ProxyReference();
        ref1.setName("reference1");

        assertThat(ref0).isEqualTo(ref0Bis);
        assertThat(ref0).isNotEqualTo(ref1);
        assertThat(ref1).isNotEqualTo(ref0Bis);

        assertThat(ref0).isEqualTo(ref0);
        assertThat(ref0).isNotEqualTo(null);
        assertThat(ref0).isNotEqualTo(0);
    }
}