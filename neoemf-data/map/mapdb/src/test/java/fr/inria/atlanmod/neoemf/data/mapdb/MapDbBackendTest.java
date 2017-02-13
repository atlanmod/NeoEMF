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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import static org.assertj.core.api.Assertions.assertThat;

public class MapDbBackendTest extends AbstractTest {

    @Test
    public void testStoreFeature() {
        DB db = DBMaker.memoryDB().make();
        MapDbBackend backend = new MapDbBackendIndices(db);
        SingleFeatureKey key = SingleFeatureKey.of(StringId.of("object1"), "name");
        backend.valueFor(key, "value");

        assertThat("value").isEqualTo(backend.valueOf(key).orElse(null));
    }

    @Test
    public void testStoreMultivaluedFeature() {
        final int TIMES = 10;
        DB db = DBMaker.memoryDB().make();
        MapDbBackend backend = new MapDbBackendIndices(db);

        MultiFeatureKey[] keys = new MultiFeatureKey[TIMES];
        SingleFeatureKey featureKey = SingleFeatureKey.of(StringId.of("object"), "name");

        for (int i = 0; i < 10; i++) {
            keys[i] = featureKey.withPosition(i);
            backend.addValue(keys[i], i);
        }

        for (int i = 0; i < TIMES; i++) {
            assertThat(i).isEqualTo(backend.valueOf(keys[i]).orElse(null));
        }
    }

    @Test
    @SuppressWarnings("unchecked") // Unchecked cast: 'GroupSerializer' to 'Serializer<...>'
    public void testSerialize() throws Exception {
        DataOutput2 out = new DataOutput2();
        SingleFeatureKey key1 = SingleFeatureKey.of(StringId.of("object1"), "name");

        Serializer<SingleFeatureKey> ser = Serializer.JAVA;
        SingleFeatureKey key2 = ser.clone(key1);

        assertThat(key1).isEqualTo(key2);

        ser.serialize(out, key1);

        SingleFeatureKey key3 = ser.deserialize(new DataInput2.ByteArray(out.copyBytes()), out.pos);

        assertThat(key1).isEqualTo(key3);
    }

    @Test
    public void testHashCode() {
        SingleFeatureKey key1 = SingleFeatureKey.of(StringId.of("object1"), "name");
        SingleFeatureKey key2 = SingleFeatureKey.of(StringId.of("object1"), "name");

        assertThat(key1.hashCode()).isEqualTo(key2.hashCode());
    }
}
