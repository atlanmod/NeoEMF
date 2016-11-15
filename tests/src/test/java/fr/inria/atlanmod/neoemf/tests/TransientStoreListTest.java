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

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.store.impl.AbstractTransientStore;
import fr.inria.atlanmod.neoemf.issues.Issue7Test;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test cases that check List method consistency for the {@link AbstractTransientStore} class.
 * Issue #7 reported the error for isEmpty method {@link Issue7Test}
 */
public class TransientStoreListTest extends AllTest {

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
        assertThat(model.getContentObjects()).isNotNull(); // "Accessed list is null"
    }

    @Test
    public void testAdd() {
        assertThat(model.getContentObjects().add(mapFactory.createSampleModelContentObject())).isTrue(); // "Adding valid item to the list returns false"
    }

    @Test
    public void testAddAllCollection() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assertThat(model.getContentObjects().addAll(list)).isTrue(); // "Adding valid item list returns false"
    }

    @Test
    public void testAddAllCollectionIndex() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assertThat(model.getContentObjects().addAll(0, list)).isTrue(); // "Adding valid item list at a given index returns false"
    }

    @Test
    public void testClear() {
        model.getContentObjects().clear();
        assertThat(model.getContentObjects()).isEmpty(); // "List isn't empty after clean"
    }

    @Test
    public void testContains() {
        assertThat(model.getContentObjects().contains(mapFactory.createSampleModelContentObject())).isFalse(); // "Accessed list contains the created element"
    }

    @Test
    public void testContainsAll() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assertThat(model.getContentObjects().containsAll(list)).isFalse(); // "Accessed list contains the given collection"
    }

    @Test
    public void testEquals() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(mapFactory.createSampleModelContentObject());
        list.add(mapFactory.createSampleModelContentObject());
        assertThat(model.getContentObjects().equals(list)).isFalse(); // "Accessed list is equal to the given collection"
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() {
        model.getContentObjects().get(0);
    }

    @Test
    public void testHashCode() {
        //noinspection ResultOfMethodCallIgnored
        model.getContentObjects().hashCode();
    }

    @Test
    public void testIndexOf() {
        assertThat(model.getContentObjects().indexOf(mapFactory.createSampleModelContentObject())).isEqualTo(-1); // "IndexOf returns a wrong value"
    }

    @Test
    public void testIsEmpty() {
        assertThat(model.getContentObjects().isEmpty()).isTrue(); // "Accessed list is not empty"
    }

    @Test
    public void testIterator() {
        model.getContentObjects().iterator();
    }

    @Test
    public void testLastIndexOf() {
        assertThat(model.getContentObjects().lastIndexOf(mapFactory.createSampleModelContentObject())).isEqualTo(-1); // "LastIndexOf returns a wrong value"
    }

    @Test
    public void testListIterator() {
        model.getContentObjects().listIterator();
    }

    @Test
    public void testListIteratorIndex() {
        model.getContentObjects().listIterator(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorInvalidIndex() {
        model.getContentObjects().listIterator(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIndex() {
        model.getContentObjects().move(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveObject() {
        model.getContentObjects().move(0, mapFactory.createSampleModelContentObject());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        model.getContentObjects().remove(0);
    }

    @Test
    public void testRemoveObject() {
        model.getContentObjects().remove(mapFactory.createSampleModelContentObject());
    }

    @Test
    public void testRemoveAllCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().removeAll(collection);
    }

    @Test
    public void testRemoveAllEmptyCollection() {
        model.getContentObjects().removeAll(new ArrayList<SampleModelContentObject>());
    }

    @Test
    public void testRetainAllCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().retainAll(collection);
    }

    @Test
    public void testRetainAllEmptyCollection() {
        model.getContentObjects().retainAll(new ArrayList<>());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetInvalidIndex() {
        model.getContentObjects().set(0, mapFactory.createSampleModelContentObject());
    }

    @Test
    public void testSetValidIndex() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        model.getContentObjects().set(0, mapFactory.createSampleModelContentObject());
    }

    @Test(expected = ClassCastException.class)
    public void testSetInvalidObject() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        model.getContentObjects().set(0, (SampleModelContentObject) mapFactory.createSampleModel());
    }

    @Test
    public void testSizeEmptyList() {
        assertThat(model.getContentObjects().size()).isEqualTo(0);
    }

    @Test
    public void testSize() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assertThat(model.getContentObjects().size()).isEqualTo(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListInvalidIndexes() {
        model.getContentObjects().subList(0, 1);
    }

    @Test
    public void testSubList() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assertThat(model.getContentObjects().subList(0, 1).size()).isEqualTo(1);
    }

    @Test
    public void testToArrayEmptyList() {
        assertThat(model.getContentObjects().toArray().length).isEqualTo(0);
    }

    @Test
    public void testToArray() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assertThat(model.getContentObjects().toArray().length).isEqualTo(2);
    }

    @Test
    public void testToArrayParameterEmptyList() {
        Object[] array = new Object[0];
        assertThat(model.getContentObjects().toArray(array)).containsExactly(array);
    }

    @Test
    public void testToArrayParameter() {
        Object[] array = new Object[2];
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assertThat(model.getContentObjects().toArray(array).length).isEqualTo(2);
    }

    @Test
    public void testToStringEmptyList() {
        //noinspection ResultOfMethodCallIgnored
        model.getContentObjects().toString();
    }

    @Test
    public void testToString() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(mapFactory.createSampleModelContentObject());
        collection.add(mapFactory.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        //noinspection ResultOfMethodCallIgnored
        model.getContentObjects().toString();
    }
}
