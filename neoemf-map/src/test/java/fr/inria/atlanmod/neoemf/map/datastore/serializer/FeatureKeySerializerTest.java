/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.map.datastore.serializer;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.datastore.structure.FeatureKey;

import org.junit.Test;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;

import java.io.IOException;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class FeatureKeySerializerTest {

    @Test
    public void testSerialize() throws IOException {
        DataOutput2 out = new DataOutput2();

        FeatureKeySerializer serializer = new FeatureKeySerializer();
        FeatureKey key1 = FeatureKey.of(new StringId("anObject"), "anAttribute");

        serializer.serialize(out, key1);
        FeatureKey key2 = serializer.deserialize(new DataInput2.ByteArray(out.copyBytes()), 0);

        assertThat(key1).isEqualTo(key2);
    }
}
