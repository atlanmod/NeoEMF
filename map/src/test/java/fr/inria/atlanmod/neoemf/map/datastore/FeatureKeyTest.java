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
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.FeatureKey;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sunye on 13/10/2016.
 */
public class FeatureKeyTest {


    @Test
    public void testCompareEqualTo() {
        FeatureKey key1 = new FeatureKey(new StringId("myobject"), "aaa");
        FeatureKey key2 = new FeatureKey(new StringId("myobject"), "aaa");

        assert key1.compareTo(key2) == 0;
    }

    @Test
    public void testCompareLowerThan() {
        FeatureKey key1 = new FeatureKey(new StringId("myobject"), "aaa");
        FeatureKey key2 = new FeatureKey(new StringId("myobject"), "bbb");

        assert key1.compareTo(key2) < 0;
    }

    @Test
    public void testCompareGreaterThan() {
        FeatureKey key1 = new FeatureKey(new StringId("AAA"), "aaa");
        FeatureKey key2 = new FeatureKey(new StringId("BBB"), "zzz");

        System.out.println(key1.compareTo(key2) );
        assert key1.compareTo(key2) > 0 ;
    }

    @Test
    public void testSerializable() throws Exception {
        FeatureKey key1 = new FeatureKey(new StringId("AAA"), "aaa");
        ByteArrayOutputStream os = new ByteArrayOutputStream(20);
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(key1);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(os.toByteArray()));

        FeatureKey key2 = (FeatureKey) in.readObject();

        assert key1.equals(key2);
    }
}
