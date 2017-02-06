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

package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FeatureKeySerializerTest {

    @Test
    public void testSerialize() {
        Serializer<FeatureKey> serializer = new Serializer<>();
        FeatureKey fkIn = FeatureKey.of(StringId.of("obj1"), "feature");
        FeatureKey fkOut = serializer.deserialize(serializer.serialize(fkIn));

        assertThat(fkIn).isEqualTo(fkOut);
    }

    @Test
    public void testSerializeMFK() {
        Serializer<FeatureKey> serializer = new Serializer<>();
        MultivaluedFeatureKey fkIn = FeatureKey.of(StringId.of("obj1"), "feature").withPosition(0);
        MultivaluedFeatureKey fkOut = (MultivaluedFeatureKey) serializer.deserialize(serializer.serialize(fkIn));

        assertThat(fkIn).isEqualTo(fkOut);
    }


    @Test
    public void testSerializeMFKBis() {
        Serializer<FeatureKey> serializer = new Serializer<>();
        FeatureKey fk = FeatureKey.of(StringId.of("obj1"), "feature");
        MultivaluedFeatureKey fk1 = fk.withPosition(0);
        MultivaluedFeatureKey fk2 = fk.withPosition(1);

        byte[] ser1 = serializer.serialize(fk1);
        byte[] ser2 = serializer.serialize(fk2);
        byte[] ser3 = serializer.serialize(fk);


        assertThat(Arrays.equals(ser1, ser2)).isFalse();
        assertThat(Arrays.equals(ser1, ser3)).isFalse();
        assertThat(Arrays.equals(ser2, ser3)).isFalse();
    }
}
