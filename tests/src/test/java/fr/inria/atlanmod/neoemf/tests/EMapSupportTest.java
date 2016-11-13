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

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.K;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.V;

import org.eclipse.emf.common.util.EMap;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EMapSupportTest extends AllBackendTest {

    private static final String KEY1 = "key1", KEY2 = "key2", VALUE1 = "value1", VALUE2 = "value2";

    protected MapSampleFactory factory;

    @Override
    @Before
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
        createPersistentStores();
        mapResource.getContents().add(factory.createSampleModel());
        neo4jResource.getContents().add(factory.createSampleModel());
        tinkerResource.getContents().add(factory.createSampleModel());
    }

    @Test
    public void testGetMapStringStringEmptyMapDB() {
        getMapStringStringEmpty(mapResource);
    }

    @Test
    public void testGetMapStringStringEmptyNeo4j() {
        getMapStringStringEmpty(neo4jResource);
    }

    @Test
    public void testGetMapStringStringEmptyTinker() {
        getMapStringStringEmpty(tinkerResource);
    }

    @Test
    public void testPutMapStringStringMapDB() {
        putMapStringString(mapResource);
    }

    @Test
    public void testPutMapStringStringNeo4j() {
        putMapStringString(neo4jResource);
    }

    @Test
    public void testPutMapStringStringTinker() {
        putMapStringString(tinkerResource);
    }

    @Test
    public void testGetMapKVEmptyMapDB() {
        getMapKVEmpty(mapResource);
    }

    @Test
    public void testGetMapKVEmptyNeo4j() {
        getMapKVEmpty(neo4jResource);
    }

    @Test
    public void testGetMapKVEmptyTinker() {
        getMapKVEmpty(tinkerResource);
    }

    @Test
    public void testPutMapKVMapDB() {
        putMapKV(mapResource);
    }

    @Test
    public void testPutMapKVNeo4j() {
        putMapKV(neo4jResource);
    }

    @Test
    public void testPutMapKVTinker() {
        putMapKV(tinkerResource);
    }

    private void getMapStringStringEmpty(PersistentResource persistentResource) {
        SampleModel model = (SampleModel) persistentResource.getContents().get(0);
        assertThat(model.getMap()).isInstanceOf(EMap.class); // "Map field is not an instance of EMap"

        EMap<String, String> map = model.getMap();
        assertThat(map).isEmpty(); // "EMap is not empty"
    }

    private void putMapStringString(PersistentResource persistentResource) {
        SampleModel model = (SampleModel) persistentResource.getContents().get(0);
        EMap<String, String> map = model.getMap();
        map.put(KEY1, VALUE1);
        map.put(KEY2, VALUE2);

        assertThat(map.containsKey(KEY1)).isTrue(); // "Map does not contain KEY1"
        assertThat(map.containsKey(KEY2)).isTrue(); // "Map does not contain KEY2"

        assertThat(map.get(KEY1)).isEqualTo(VALUE1); // "Wrong value for KEY1"
        assertThat(map.get(KEY2)).isEqualTo(VALUE2); // "Wrong value for KEY2"
    }

    private void getMapKVEmpty(PersistentResource persistentResource) {
        SampleModel model = (SampleModel) persistentResource.getContents().get(0);
        assertThat(model.getKvMap()).isInstanceOf(EMap.class); // "KvMap field is not an instance of EMap"

        EMap<K, V> map = model.getKvMap();
        assertThat(map).isEmpty(); // "KvMap is not empty"
    }

    private void putMapKV(PersistentResource persistentResource) {
        SampleModel model = (SampleModel) persistentResource.getContents().get(0);
        EMap<K, V> map = model.getKvMap();

        K k1 = factory.createK();
        k1.setKName(KEY1);
        k1.setKInt(10);

        K k2 = factory.createK();
        k2.setKName(KEY2);
        k2.setKInt(100);

        V v1 = factory.createV();
        v1.setVName(VALUE1);
        v1.setVInt(1);

        V v2 = factory.createV();
        v2.setVName(VALUE2);
        v2.setVInt(5);

        map.put(k1, v1);
        map.put(k2, v2);

        assertThat(map.containsKey(k1)).isTrue(); // "Map does not contain KEY1"
        assertThat(map.containsKey(k2)).isTrue(); // "Map does not contain KEY2"

        assertThat(map.get(k1)).isEqualTo(v1); // "Wrong value for KEY1"
        assertThat(map.get(k2)).isEqualTo(v2); // "Wrong value for KEY2"
    }
}
