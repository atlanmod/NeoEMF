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

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BasicMetaclass}.
 */
@ParametersAreNonnullByDefault
public class BasicMetaclassTest extends AbstractTest {

    @Test
    public void testGetDefault() throws Exception {
        BasicMetaclass mc0 = BasicMetaclass.getDefault();
        assertThat(mc0).isNotNull();
        assertThat(mc0.name()).isEqualTo("EObject");
        assertThat(mc0.ns()).isSameAs(BasicNamespace.getDefault());

        BasicMetaclass mc1 = BasicMetaclass.getDefault();
        assertThat(mc0).isSameAs(mc1);
    }

    @Test
    public void testName() throws Exception {
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
    public void testNs() throws Exception {
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
    public void testHashCode() throws Exception {
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
    public void testEquals() throws Exception {
        String name0 = "mc0";
        String name1 = "mc1";

        BasicNamespace ns0 = BasicNamespace.getDefault();

        BasicMetaclass mc0 = new BasicMetaclass(ns0, name0);
        BasicMetaclass mc0Bis = new BasicMetaclass(ns0, name0);
        BasicMetaclass mc1 = new BasicMetaclass(ns0, name1);

        assertThat(mc0).isEqualTo(mc0Bis);
        assertThat(mc0).isNotEqualTo(mc1);
        assertThat(mc1).isNotEqualTo(mc0Bis);

        assertThat(mc0).isEqualTo(mc0);
        assertThat(mc0).isNotEqualTo(null);
        assertThat(mc0).isNotEqualTo(0);
    }

    @Test
    public void testToString() throws Exception {
        String name0 = "mc0";
        String prefix0 = "uri0";

        BasicNamespace ns0 = BasicNamespace.Registry.getInstance().register(prefix0, "uri0");

        BasicMetaclass mc0 = new BasicMetaclass(ns0, name0);

        assertThat(mc0).hasToString("uri0:mc0");
    }
}