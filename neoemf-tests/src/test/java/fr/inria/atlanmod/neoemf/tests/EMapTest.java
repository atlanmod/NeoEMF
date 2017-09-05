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

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.ETypes;
import fr.inria.atlanmod.neoemf.tests.sample.Type;
import fr.inria.atlanmod.neoemf.tests.sample.Value;

import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the support of {@link EMap}.
 */
public class EMapTest extends AbstractBackendTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetMapStringStringEmpty() {
        PersistentResource resource = newPersistentStore();
        resource.getContents().add(EFACTORY.createETypes());

        ETypes eTypes = ETypes.class.cast(resource.getContents().get(0));
        assertThat(eTypes.getStringValues()).isInstanceOf(EMap.class);

        EMap<String, String> map = eTypes.getStringValues();
        assertThat(map).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testPutMapStringString() {
        PersistentResource resource = newPersistentStore();
        resource.getContents().add(EFACTORY.createETypes());

        ETypes eTypes = ETypes.class.cast(resource.getContents().get(0));
        EMap<String, String> map = eTypes.getStringValues();
        map.put(KEY1, VALUE1);
        map.put(KEY2, VALUE2);

        assertThat(map.containsKey(KEY1)).isTrue();
        assertThat(map.containsKey(KEY2)).isTrue();

        assertThat(map.get(KEY1)).isEqualTo(VALUE1);
        assertThat(map.get(KEY2)).isEqualTo(VALUE2);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetMapKVEmpty() {
        PersistentResource resource = newPersistentStore();
        resource.getContents().add(EFACTORY.createETypes());

        ETypes eTypes = ETypes.class.cast(resource.getContents().get(0));
        assertThat(eTypes.getValues()).isInstanceOf(EMap.class);

        EMap<Type, Value> map = eTypes.getValues();
        assertThat(map).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testPutMapKV() {
        PersistentResource resource = newPersistentStore();
        resource.getContents().add(EFACTORY.createETypes());

        ETypes eTypes = ETypes.class.cast(resource.getContents().get(0));
        EMap<Type, Value> map = eTypes.getValues();

        Type k1 = EFACTORY.createType();
        k1.setName(KEY1);

        Type k2 = EFACTORY.createType();
        k2.setName(KEY2);

        Value v1 = EFACTORY.createValue();
        v1.setValue(1);

        Value v2 = EFACTORY.createValue();
        v2.setValue(2);

        map.put(k1, v1);
        map.put(k2, v2);

        assertThat(map.containsKey(k1)).isTrue();
        assertThat(map.containsKey(k2)).isTrue();

        assertThat(map.get(k1)).isEqualTo(v1);
        assertThat(map.get(k2)).isEqualTo(v2);
    }
}
