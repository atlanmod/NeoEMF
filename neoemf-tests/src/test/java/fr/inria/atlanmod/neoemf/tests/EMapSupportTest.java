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
import fr.inria.atlanmod.neoemf.tests.models.mapSample.K;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.V;

import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;

public class EMapSupportTest extends AbstractBackendTest {

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetMapStringStringEmpty() {
        PersistentResource resource = createPersistentStore();
        resource.getContents().add(EFACTORY.createSampleModel());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.getMap()).isInstanceOf(EMap.class);

        EMap<String, String> map = model.getMap();
        assertThat(map).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testPutMapStringString() {
        PersistentResource resource = createPersistentStore();
        resource.getContents().add(EFACTORY.createSampleModel());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        EMap<String, String> map = model.getMap();
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
        PersistentResource resource = createPersistentStore();
        resource.getContents().add(EFACTORY.createSampleModel());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.getKvMap()).isInstanceOf(EMap.class);

        EMap<K, V> map = model.getKvMap();
        assertThat(map).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testPutMapKV() {
        PersistentResource resource = createPersistentStore();
        resource.getContents().add(EFACTORY.createSampleModel());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        EMap<K, V> map = model.getKvMap();

        K k1 = EFACTORY.createK();
        k1.setKName(KEY1);
        k1.setKInt(10);

        K k2 = EFACTORY.createK();
        k2.setKName(KEY2);
        k2.setKInt(100);

        V v1 = EFACTORY.createV();
        v1.setVName(VALUE1);
        v1.setVInt(1);

        V v2 = EFACTORY.createV();
        v2.setVName(VALUE2);
        v2.setVInt(5);

        map.put(k1, v1);
        map.put(k2, v2);

        assertThat(map.containsKey(k1)).isTrue();
        assertThat(map.containsKey(k2)).isTrue();

        assertThat(map.get(k1)).isEqualTo(v1);
        assertThat(map.get(k2)).isEqualTo(v2);
    }
}
