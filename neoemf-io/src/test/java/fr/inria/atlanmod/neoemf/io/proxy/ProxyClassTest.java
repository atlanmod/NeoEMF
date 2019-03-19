/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

import org.atlanmod.commons.AbstractTest;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link ProxyClass}.
 */
@ParametersAreNonnullByDefault
class ProxyClassTest extends AbstractTest {

    @Test
    void testGetDefault() {
        ProxyClass mc0 = ProxyClass.DEFAULT;
        assertThat(mc0).isNotNull();
        assertThat(mc0.getName()).isEqualTo("EObject");
        assertThat(mc0.getNamespace()).isEqualTo(ProxyPackage.DEFAULT);
    }

    @Test
    void testName() {
        String name0 = "mc0";
        String name1 = "mc1";

        ProxyPackage ns = ProxyPackage.DEFAULT;

        ProxyClass mc0 = new ProxyClass(ns, name0);
        assertThat(mc0.getName()).isEqualTo(name0);

        ProxyClass mc1 = new ProxyClass(ns, name1);
        assertThat(mc1.getName()).isEqualTo(name1);

        assertThat(mc0.getName()).isNotEqualTo(mc1.getName());
    }

    @Test
    void testNs() {
        String name0 = "mc0";
        String name1 = "mc1";

        ProxyPackage ns0 = ProxyPackage.DEFAULT;
        ProxyPackage ns1 = ProxyPackage.Registry.getInstance().register("prefix0", "uri0");

        ProxyClass mc0 = new ProxyClass(ns0, name0);
        assertThat(mc0.getNamespace()).isEqualTo(ns0);

        ProxyClass mc1 = new ProxyClass(ns1, name1);
        assertThat(mc1.getNamespace()).isEqualTo(ns1);

        assertThat(mc0.getNamespace()).isNotEqualTo(mc1.getNamespace());
    }

    @Test
    void testHashCode() {
        String name0 = "mc0";
        String name1 = "mc1";

        ProxyPackage ns0 = ProxyPackage.DEFAULT;

        ProxyClass mc0 = new ProxyClass(ns0, name0);
        ProxyClass mc0Bis = new ProxyClass(ns0, name0);
        ProxyClass mc1 = new ProxyClass(ns0, name1);

        assertThat(mc0.hashCode()).isEqualTo(mc0Bis.hashCode());
        assertThat(mc0.hashCode()).isNotEqualTo(mc1.hashCode());
        assertThat(mc1.hashCode()).isNotEqualTo(mc0Bis.hashCode());
    }

    @Test
    void testEquals() {
        String name0 = "mc0";
        String name1 = "mc1";

        ProxyPackage ns0 = ProxyPackage.DEFAULT;

        ProxyClass mc0 = new ProxyClass(ns0, name0);
        ProxyClass mc0Bis = new ProxyClass(ns0, name0);
        ProxyClass mc1 = new ProxyClass(ns0, name1);

        assertThat(mc0).isEqualTo(mc0Bis);
        assertThat(mc0).isNotEqualTo(mc1);
        assertThat(mc1).isNotEqualTo(mc0Bis);

        assertThat(mc0).isEqualTo(mc0);
        assertThat(mc0).isNotEqualTo(null);
        assertThat(mc0).isNotEqualTo(0);
    }

    @Test
    void testToString() {
        String name0 = "mc0";
        String prefix0 = "uri0";

        ProxyPackage ns0 = ProxyPackage.Registry.getInstance().register(prefix0, "uri0");

        ProxyClass mc0 = new ProxyClass(ns0, name0);

        assertThat(mc0).hasToString("uri0:mc0");
    }
}