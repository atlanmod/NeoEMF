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

package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.core.StringId;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FeatureKeyTest extends AbstractTest {

    @Test
    public void testCompareEqualTo() {
        FeatureKey key1 = FeatureKey.of(new StringId("myobject"), "aaa");
        FeatureKey key2 = FeatureKey.of(new StringId("myobject"), "aaa");

        assertThat(key1.compareTo(key2)).isEqualTo(0);
    }

    @Test
    public void testCompareLowerThan() {
        FeatureKey key1 = FeatureKey.of(new StringId("myobject"), "aaa");
        FeatureKey key2 = FeatureKey.of(new StringId("myobject"), "bbb");

        assertThat(key1.compareTo(key2)).isLessThan(0);
    }

    @Test
    public void testCompareGreaterThan() {
        FeatureKey key1 = FeatureKey.of(new StringId("AAA"), "aaa");
        FeatureKey key2 = FeatureKey.of(new StringId("BBB"), "zzz");

        assertThat(key1.compareTo(key2)).isGreaterThan(0);
    }

    @Test
    public void testSerializable() throws Exception {
        FeatureKey key1 = FeatureKey.of(new StringId("AAA"), "aaa");
        ByteArrayOutputStream os = new ByteArrayOutputStream(20);
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(key1);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(os.toByteArray()));

        FeatureKey key2 = (FeatureKey) in.readObject();

        assertThat(key1).isEqualTo(key2);
    }
}
