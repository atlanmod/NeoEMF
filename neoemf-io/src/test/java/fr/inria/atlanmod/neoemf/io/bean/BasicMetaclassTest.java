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

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link BasicMetaclass}.
 */
public class BasicMetaclassTest {

    @Test
    public void testGetDefault() {
        BasicMetaclass mc0 = BasicMetaclass.getDefault();
        assertThat(mc0).isNotNull();
        assertThat(mc0.name()).isEqualTo("EObject");
        assertThat(mc0.ns()).isSameAs(BasicNamespace.getDefault());

        BasicMetaclass mc1 = BasicMetaclass.getDefault();
        assertThat(mc0).isSameAs(mc1);
    }

    @Test
    public void testName() {
        String name0 = "mc0";
        String name1 = "mc1";

        BasicNamespace ns = BasicNamespace.getDefault();

        BasicMetaclass mc0 = new BasicMetaclass(ns, name0);
        assertThat(mc0.name()).isEqualTo(name0);

        BasicMetaclass mc1 = new BasicMetaclass(ns, name1);
        assertThat(mc1.name()).isEqualTo(name1);

        assertThat(mc0.name()).isNotEqualTo(mc1.name());
    }

    @Test
    public void testNs() {
        String name0 = "mc0";
        String name1 = "mc1";

        BasicNamespace ns0 = BasicNamespace.getDefault();
        BasicNamespace ns1 = BasicNamespace.Registry.getInstance().register("prefix0", "uri0");

        BasicMetaclass mc0 = new BasicMetaclass(ns0, name0);
        assertThat(mc0.ns()).isEqualTo(ns0);

        BasicMetaclass mc1 = new BasicMetaclass(ns1, name1);
        assertThat(mc1.ns()).isEqualTo(ns1);

        assertThat(mc0.ns()).isNotEqualTo(mc1.ns());
    }

    @Test
    public void testHashCode() {
        String name0 = "mc0";
        String name1 = "mc1";

        BasicNamespace ns0 = BasicNamespace.getDefault();

        BasicMetaclass mc0 = new BasicMetaclass(ns0, name0);
        BasicMetaclass mc0Bis = new BasicMetaclass(ns0, name0);
        BasicMetaclass mc1 = new BasicMetaclass(ns0, name1);

        assertThat(mc0.hashCode()).isEqualTo(mc0Bis.hashCode());
        assertThat(mc0.hashCode()).isNotEqualTo(mc1.hashCode());
        assertThat(mc1.hashCode()).isNotEqualTo(mc0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        String name0 = "mc0";
        String name1 = "mc1";

        BasicNamespace ns0 = BasicNamespace.getDefault();

        BasicMetaclass mc0 = new BasicMetaclass(ns0, name0);
        BasicMetaclass mc0Bis = new BasicMetaclass(ns0, name0);
        BasicMetaclass mc1 = new BasicMetaclass(ns0, name1);

        assertThat(mc0).isEqualTo(mc0Bis);
        assertThat(mc0).isNotEqualTo(mc1);
        assertThat(mc1).isNotEqualTo(mc0Bis);
    }

    @Test
    public void testToString() {
        String name0 = "mc0";
        String prefix0 = "uri0";

        BasicNamespace ns0 = BasicNamespace.Registry.getInstance().register(prefix0, "uri0");

        BasicMetaclass mc0 = new BasicMetaclass(ns0, name0);

        assertThat(mc0).hasToString("uri0:mc0");
    }
}