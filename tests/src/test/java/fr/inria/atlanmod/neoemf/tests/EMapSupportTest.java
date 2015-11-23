/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.tests;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V;

public class EMapSupportTest extends AllBackendTest{

    
    
    protected MapSampleFactory factory;
    
    @Before
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
        super.createPersistentStores();
        mapResource.getContents().add(factory.createSampleModel());
        neo4jResource.getContents().add(factory.createSampleModel());
        tinkerResource.getContents().add(factory.createSampleModel());
        
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetMapStringStringEmptyMapDB() {
        SampleModel model = (SampleModel)mapResource.getContents().get(0);
        assert model.getMap() instanceof EMap : "Map field is not an instance of EMap";
        EMap<String,String> map = model.getMap();
        assert map.isEmpty() : "EMap is not empty";
    }
    
    @Test
    public void testGetMapStringStringEmptyNeo4j() {
        SampleModel model = (SampleModel)neo4jResource.getContents().get(0);
        assert model.getMap() instanceof EMap : "Map field is not an instance of EMap";
        EMap<String, String> map = model.getMap();
        assert map.isEmpty() : "EMap is not empty";
    }
    
    @Test
    public void testGetMapStringStringEmptyTinker() {
        SampleModel model = (SampleModel)tinkerResource.getContents().get(0);
        assert model.getMap() instanceof EMap : "Map field is not an instance of java EMap";
        EMap<String, String> map = model.getMap();
        assert map.isEmpty() : "EMap is not empty";
    }
    
    @Test
    public void testPutMapStringStringMapDB() {
        SampleModel model = (SampleModel)mapResource.getContents().get(0);
        EMap<String,String> map = model.getMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assert map.containsKey("key1") : "Map does not contain key1";
        assert map.containsKey("key2") : "Map does not contain key2";
        assert map.get("key1").equals("value1") : "Wrong value for key1";
        assert map.get("key2").equals("value2") : "Wrong  value for key2";
    }
    
    @Test
    public void testPutMapStringStringNeo4j() {
        SampleModel model = (SampleModel)neo4jResource.getContents().get(0);
        EMap<String,String> map = model.getMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assert map.containsKey("key1") : "Map does not contain key1";
        assert map.containsKey("key2") : "Map does not contain key2";
        assert map.get("key1").equals("value1") : "Wrong value for key1";
        assert map.get("key2").equals("value2") : "Wrong  value for key2";
    }
    
    @Test
    public void testPutMapStringStringTinker() {
        SampleModel model = (SampleModel)tinkerResource.getContents().get(0);
        EMap<String,String> map = model.getMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assert map.containsKey("key1") : "Map does not contain key1";
        assert map.containsKey("key2") : "Map does not contain key2";
        assert map.get("key1").equals("value1") : "Wrong value for key1";
        assert map.get("key2").equals("value2") : "Wrong  value for key2";
    }
    
    @Test
    public void testGetMapKVEmptyMapDB() {
        SampleModel model = (SampleModel)mapResource.getContents().get(0);
        assert model.getKvMap() instanceof EMap : "KvMap field is not an instance of EMap";
        EMap<K,V> map = model.getKvMap();
        assert map.isEmpty() : "KvMap is not empty";
    }
    
    @Test
    public void testGetMapKVEmptyNeo4j() {
        SampleModel model = (SampleModel)neo4jResource.getContents().get(0);
        assert model.getKvMap() instanceof EMap : "KvMap field is not an instance of EMap";
        EMap<K,V> map = model.getKvMap();
        assert map.isEmpty() : "KvMap is not empty";
    }
    
    @Test
    public void testGetMapKVEmptyTinker() {
        SampleModel model = (SampleModel)tinkerResource.getContents().get(0);
        assert model.getKvMap() instanceof EMap : "KvMap field is not an instance of EMap";
        EMap<K,V> map = model.getKvMap();
        assert map.isEmpty() : "KvMap is not empty";
    }
    
    @Test
    public void testPutMapKVMapDB() {
        SampleModel model = (SampleModel)mapResource.getContents().get(0);
        EMap<K,V> map = model.getKvMap();
        K k1 = factory.createK();
        k1.setKName("key1");
        k1.setKInt(10);
        K k2 = factory.createK();
        k2.setKName("key2");
        k2.setKInt(100);
        V v1 = factory.createV();
        v1.setVName("value1");
        v1.setVInt(1);
        V v2 = factory.createV();
        v2.setVName("value2");
        v2.setVInt(5);
        map.put(k1, v1);
        map.put(k2, v2);
        assert map.containsKey(k1) : "Map does not contain key1";
        assert map.containsKey(k2) : "Map does not contain key2";
        assert map.get(k1).equals(v1) : "Wrong value for key1";
        assert map.get(k2).equals(v2) : "Wrong value for key2";
    }
    
    @Test
    public void testPutMapKVNeo4j() {
        SampleModel model = (SampleModel)neo4jResource.getContents().get(0);
        EMap<K,V> map = model.getKvMap();
        K k1 = factory.createK();
        k1.setKName("key1");
        k1.setKInt(10);
        K k2 = factory.createK();
        k2.setKName("key2");
        k2.setKInt(100);
        V v1 = factory.createV();
        v1.setVName("value1");
        v1.setVInt(1);
        V v2 = factory.createV();
        v2.setVName("value2");
        v2.setVInt(5);
        map.put(k1, v1);
        map.put(k2, v2);
        assert map.containsKey(k1) : "Map does not contain key1";
        assert map.containsKey(k2) : "Map does not contain key2";
        assert map.get(k1).equals(v1) : "Wrong value for key1";
        assert map.get(k2).equals(v2) : "Wrong value for key2";
    }
    
    @Test
    public void testPutMapKVTinker() {
        SampleModel model = (SampleModel)tinkerResource.getContents().get(0);
        EMap<K,V> map = model.getKvMap();
        K k1 = factory.createK();
        k1.setKName("key1");
        k1.setKInt(10);
        K k2 = factory.createK();
        k2.setKName("key2");
        k2.setKInt(100);
        V v1 = factory.createV();
        v1.setVName("value1");
        v1.setVInt(1);
        V v2 = factory.createV();
        v2.setVName("value2");
        v2.setVInt(5);
        map.put(k1, v1);
        map.put(k2, v2);
        assert map.containsKey(k1) : "Map does not contain key1";
        assert map.containsKey(k2) : "Map does not contain key2";
        assert map.get(k1).equals(v1) : "Wrong value for key1";
        assert map.get(k2).equals(v2) : "Wrong value for key2";
    }

}
