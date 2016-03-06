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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject;

/**
 * Test cases that check List method consistency for the @see{TransientEStoreImpl} class.
 * Issue #7 reported the error for isEmpty method @see{Issue7Test}
 */
public class TransientEStoreListTest {

    protected SampleModel model;
    protected MapSamplePackage mapPackage;
    protected MapSampleFactory mapFactory;
    
    @Before
    public void setUp() {
        mapPackage = MapSamplePackage.eINSTANCE;
        mapFactory = MapSampleFactory.eINSTANCE;
        this.model = mapFactory.createSampleModel();
    }
    
    @Test
    public void testListNotNull() {
        assert model.getContentObjects() != null : "Accessed list is null";
    }
    
    @Test
    public void testAdd() {
        assert model.getContentObjects().add(mapFactory.createSampleModelContentObject()) : "Adding valid item to the list returns false";
    }
    
    @Test
    public void testAddAllCollection() {
        List<SampleModelContentObject> list = new ArrayList<SampleModelContentObject>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assert model.getContentObjects().addAll(list) : "Adding valid item list returns false";
    }
    
    @Test
    public void testAddAllCollectionIndex() {
        List<SampleModelContentObject> list = new ArrayList<SampleModelContentObject>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assert model.getContentObjects().addAll(0,list) : "Adding valid item list at a given index returns false";
    }
    
    @Test
    public void testClear() {
        model.getContentObjects().clear();
    }
    
    @Test
    public void testContains() {
        assert !model.getContentObjects().contains(mapFactory.createSampleModelContentObject()) : "Accessed list contains the created element";
    }
    
    @Test
    public void testContainsAll() {
        List<SampleModelContentObject> list = new ArrayList<SampleModelContentObject>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assert !model.getContentObjects().containsAll(list) : "Accessed list contains the given collection";
    }
    
    @Test
    public void testEquals() {
        List<SampleModelContentObject> list = new ArrayList<SampleModelContentObject>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assert !model.getContentObjects().equals(list) : "Accessed list is equal to the given collection";
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGet() {
        model.getContentObjects().get(0);
    }
    
    @Test
    public void testHashCode() {
        model.getContentObjects().hashCode();
    }
    
    @Test
    public void testIndexOf() {
        assert model.getContentObjects().indexOf(mapFactory.createSampleModelContentObject()) == -1 : "IndexOf returns a wrong value";
    }
    
    @Test
    public void testIsEmpty() {
        assert model.getContentObjects().isEmpty() : "Accessed list is not empty";
    }
    
    @Test
    public void testIterator() {
        model.getContentObjects().iterator();
    }
    
    @Test
    public void testLastIndexOf() {
        assert model.getContentObjects().lastIndexOf(mapFactory.createSampleModelContentObject()) == -1 : "LastIndexOf returns a wrong value";
    }
    
    @Test
    public void testListIterator() {
        model.getContentObjects().listIterator();
    }
    
    @Test
    public void testListIteratorIndex() {
        model.getContentObjects().listIterator(0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testListIteratorInvalidIndex() {
        model.getContentObjects().listIterator(1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testMoveIndex() {
        model.getContentObjects().move(0, 0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testMoveObject() {
        model.getContentObjects().move(0, mapFactory.createSampleModelContentObject());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        model.getContentObjects().remove(0);
    }
    
    @Test
    public void testRemoveObject() {
        model.getContentObjects().remove(mapFactory.createSampleModelContentObject());
    }
    
    @Test
    public void testRemoveAllCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().removeAll(collection);
    }
    
    @Test
    public void testRemoveAllEmptyCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        model.getContentObjects().removeAll(collection);
    }
    
    public void testRetainAllCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().retainAll(collection);
    }
    
    public void testRetainAllEmptyCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        model.getContentObjects().retainAll(collection);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetInvalidIndex() {
        model.getContentObjects().set(0, mapFactory.createSampleModelContentObject());
    }
    
    public void testSetValidIndex() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        model.getContentObjects().set(0, mapFactory.createSampleModelContentObject());
    }
    
    @Test(expected=ClassCastException.class)
    public void testSetInvalidObject() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        model.getContentObjects().set(0, (SampleModelContentObject)mapFactory.createSampleModel());
    }
    
    @Test
    public void testSizeEmptyList() {
        assert model.getContentObjects().size() == 0;
    }
    
    @Test
    public void testSize() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assert model.getContentObjects().size() == 2 : "Wrong size value " + model.getContentObjects().size() + "(expected " + 2 +")";
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testSubListInvalidIndexes() {
        model.getContentObjects().subList(0, 1);
    }
    
    @Test
    public void testSubList() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assert model.getContentObjects().subList(0, 1).size() == 1;
    }
    
    @Test
    public void testToArrayEmptyList() {
        assert model.getContentObjects().toArray().length == 0;
    }
    
    @Test
    public void testToArray() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assert model.getContentObjects().toArray().length == 2;
    }
    
    @Test
    public void testToArrayParameterEmptyList() {
        Object[] array = new Object[0];
        model.getContentObjects().toArray(array);
    }
    
    @Test
    public void testToArrayParameter() {
        Object[] array = new Object[2];
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assert model.getContentObjects().toArray(array).length == 2;
    }
    
    @Test
    public void testToStringEmptyList() {
        model.getContentObjects().toString();
    }
    
    @Test
    public void testToString() {
        Collection<SampleModelContentObject> collection = new ArrayList<SampleModelContentObject>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        model.getContentObjects().toString();
    }
    
}
