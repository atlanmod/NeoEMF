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

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BasicElement}.
 */
@ParametersAreNonnullByDefault
public class BasicElementTest extends AbstractTest {

    @Test
    public void testName() {
        String name0 = "element0";
        String name1 = "element1";

        BasicElement elt0 = new BasicElement();
        elt0.name(name0);
        assertThat(elt0.name()).isEqualTo(name0);

        BasicElement elt1 = new BasicElement();
        elt1.name(name1);
        assertThat(elt1.name()).isEqualTo(name1);

        assertThat(elt0.name()).isNotEqualTo(elt1.name());
    }

    @Test
    public void testId() {
        BasicElement elt0 = new BasicElement();
        assertThat(elt0.id()).isNull();

        Id id0 = Id.getProvider().fromLong(42);
        Id id1 = Id.getProvider().fromLong(44);

        elt0.id(id0);
        assertThat(elt0.id()).isEqualTo(id0);

        elt0.id(id1);
        assertThat(elt0.id()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    public void testMetaclass() {
        BasicNamespace ns = BasicNamespace.getDefault();

        BasicElement elt0 = new BasicElement();
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
        BasicElement elt0 = new BasicElement();
        assertThat(elt0.isRoot()).isFalse();

        elt0.isRoot(true);
        assertThat(elt0.isRoot()).isTrue();

        elt0.isRoot(false);
        assertThat(elt0.isRoot()).isFalse();
    }

    @Test
    public void testHashCode() {
        BasicElement elt0 = new BasicElement();
        elt0.id(Id.getProvider().fromLong(42));

        BasicElement elt0Bis = new BasicElement();
        elt0Bis.id(Id.getProvider().fromLong(42));

        BasicElement elt1 = new BasicElement();
        elt1.id(Id.getProvider().fromLong(44));

        assertThat(elt0.hashCode()).isEqualTo(elt0Bis.hashCode());
        assertThat(elt0.hashCode()).isNotEqualTo(elt1.hashCode());
        assertThat(elt1.hashCode()).isNotEqualTo(elt0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        BasicElement elt0 = new BasicElement();
        elt0.id(Id.getProvider().fromLong(42));

        BasicElement elt0Bis = new BasicElement();
        elt0Bis.id(Id.getProvider().fromLong(42));

        BasicElement elt1 = new BasicElement();
        elt1.id(Id.getProvider().fromLong(44));

        assertThat(elt0).isEqualTo(elt0Bis);
        assertThat(elt0).isNotEqualTo(elt1);
        assertThat(elt1).isNotEqualTo(elt0Bis);
    }
}