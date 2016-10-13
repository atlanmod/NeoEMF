/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
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
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.FeatureKey;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.MultivaluedFeatureKey;
import org.junit.Test;
import org.mapdb.*;

/**
 * Created by sunye on 13/10/2016.
 */
public class MapPersistenceBackendTest {


    @Test
    public void testStoreFeature() {
        DB db = DBMaker.memoryDB().make();
        MapPersistenceBackend backend = new MapPersistenceBackend(db);
        FeatureKey key = new FeatureKey(new StringId("object1"), "name");
        backend.storeValue(key, "value");

       assert "value".equals(backend.valueOf(key));
    }

    @Test
    public void testStoreMultivaluedFeature() {
        final int TIMES = 10;
        DB db = DBMaker.memoryDB().make();
        MapPersistenceBackend backend = new MapPersistenceBackend(db);

        MultivaluedFeatureKey[] keys = new MultivaluedFeatureKey[TIMES];

        for (int i = 0; i < 10 ; i++) {
            keys[i] = new MultivaluedFeatureKey(new StringId("object"), "name", i);
            backend.storeValueAtIndex(keys[i], new Integer(i));
        }

        for (int i = 0; i < TIMES; i++) {
            assert new Integer(i).equals(backend.valueAtIndex(keys[i]));
        }
    }

    @Test
    public void testSerialize() throws Exception{
        DataOutput2 out = new DataOutput2();
        FeatureKey key1 = new FeatureKey(new StringId("object1"), "name");

        Serializer<FeatureKey> ser = Serializer.JAVA;
        FeatureKey key2 = ser.clone(key1);

        assert key1.equals(key2);

        ser.serialize(out, key1);

        FeatureKey key3 = ser.deserialize(new DataInput2.ByteArray(out.copyBytes()), out.pos);

        assert key1.equals(key3);

    }

    @Test
    public void testHashCode() {
        FeatureKey key1 = new FeatureKey(new StringId("object1"), "name");
        FeatureKey key2 = new FeatureKey(new StringId("object1"), "name");

        assert key1.hashCode() == key2.hashCode();
    }
}
