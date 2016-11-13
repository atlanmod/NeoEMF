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

package fr.inria.atlanmod.neoemf.map.datastore;

import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.key.FeatureKey;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.key.MultivaluedFeatureKey;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import static org.assertj.core.api.Assertions.assertThat;

public class MapPersistenceBackendTest {

    @Test
    public void testStoreFeature() {
        DB db = DBMaker.memoryDB().make();
        MapPersistenceBackend backend = new MapPersistenceBackend(db);
        FeatureKey key = new FeatureKey(new StringId("object1"), "name");
        backend.storeValue(key, "value");

        assertThat("value").isEqualTo(backend.valueOf(key));
    }

    @Test
    public void testStoreMultivaluedFeature() {
        final int TIMES = 10;
        DB db = DBMaker.memoryDB().make();
        MapPersistenceBackend backend = new MapPersistenceBackend(db);

        MultivaluedFeatureKey[] keys = new MultivaluedFeatureKey[TIMES];

        for (int i = 0; i < 10; i++) {
            keys[i] = new MultivaluedFeatureKey(new StringId("object"), "name", i);
            backend.storeValueAtIndex(keys[i], i);
        }

        for (int i = 0; i < TIMES; i++) {
            assertThat(i).isEqualTo(backend.valueAtIndex(keys[i]));
        }
    }

    @Test
    @SuppressWarnings("unchecked") // Unchecked cast: 'GroupSerializer' to 'Serializer<...>'
    public void testSerialize() throws Exception {
        DataOutput2 out = new DataOutput2();
        FeatureKey key1 = new FeatureKey(new StringId("object1"), "name");

        Serializer<FeatureKey> ser = Serializer.JAVA;
        FeatureKey key2 = ser.clone(key1);

        assertThat(key1).isEqualTo(key2);

        ser.serialize(out, key1);

        FeatureKey key3 = ser.deserialize(new DataInput2.ByteArray(out.copyBytes()), out.pos);

        assertThat(key1).isEqualTo(key3);
    }

    @Test
    public void testHashCode() {
        FeatureKey key1 = new FeatureKey(new StringId("object1"), "name");
        FeatureKey key2 = new FeatureKey(new StringId("object1"), "name");

        assertThat(key1.hashCode()).isEqualTo(key2.hashCode());
    }
}
