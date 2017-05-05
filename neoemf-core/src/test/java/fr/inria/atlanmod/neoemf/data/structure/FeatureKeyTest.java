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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test cases about {@link FeatureKey}.
 */
public class FeatureKeyTest extends AbstractTest {

    /**
     * Checks the comparison of 2 {@link FeatureKey}s. In this case, they are equal.
     */
    @Test
    public void testCompareEqualTo() {
        FeatureKey key1 = FeatureKey.of(StringId.of("myobject"), "aaa");
        FeatureKey key2 = FeatureKey.of(StringId.of("myobject"), "aaa");

        assertThat(key1.compareTo(key2)).isEqualTo(0);
    }

    /**
     * Checks the comparison of 2 {@link FeatureKey}s. In this case, the first is lower than the second.
     */
    @Test
    public void testCompareLowerThan() {
        FeatureKey key1 = FeatureKey.of(StringId.of("myobject"), "aaa");
        FeatureKey key2 = FeatureKey.of(StringId.of("myobject"), "bbb");

        assertThat(key1.compareTo(key2)).isLessThan(0);
    }

    /**
     * Checks the comparison of 2 {@link FeatureKey}s. In this case, the first is greater than the second.
     */
    @Test
    public void testCompareGreaterThan() {
        FeatureKey key1 = FeatureKey.of(StringId.of("AAA"), "aaa");
        FeatureKey key2 = FeatureKey.of(StringId.of("BBB"), "zzz");

        assertThat(key1.compareTo(key2)).isGreaterThan(0);
    }

    /**
     * Checks the serialization/deserialization of a {@link FeatureKey}.
     *
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     * @throws IOException            if an I/O error occurs during the (de)serialization
     */
    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        FeatureKey key1 = FeatureKey.of(StringId.of("AAA"), "aaa");
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(key1);

            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
                FeatureKey key2 = FeatureKey.class.cast(in.readObject());
                assertThat(key1).isEqualTo(key2);
            }
        }
    }
}
