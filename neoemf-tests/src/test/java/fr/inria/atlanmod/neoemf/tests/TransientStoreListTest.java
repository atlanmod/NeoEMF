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

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.tests.issues.Issue7Test;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * Test cases that check {@link List} method consistency for the {@link DirectWriteStore} class.
 * <p>
 * Issue #7 reported the error for the {@code isEmpty()} method {@link Issue7Test}
 */
public class TransientStoreListTest extends AbstractTest {

    private static final MapSampleFactory EFACTORY = MapSampleFactory.eINSTANCE;

    @SuppressWarnings("unused")
    private static final MapSamplePackage EPACKAGE = MapSamplePackage.eINSTANCE;

    private SampleModel model;

    @Before
    public final void initModel() {
        model = EFACTORY.createSampleModel();
    }

    @Test
    public void testListNotNull() {
        assertThat(model.getContentObjects()).isNotNull();
    }

    @Test
    public void testAdd() {
        assertThat(model.getContentObjects().add(EFACTORY.createSampleModelContentObject())).isTrue();
    }

    @Test
    public void testAddAllCollection() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(EFACTORY.createSampleModelContentObject());
        list.add(EFACTORY.createSampleModelContentObject());
        assertThat(model.getContentObjects().addAll(list)).isTrue();
    }

    @Test
    public void testAddAllCollectionIndex() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(EFACTORY.createSampleModelContentObject());
        list.add(EFACTORY.createSampleModelContentObject());
        assertThat(model.getContentObjects().addAll(0, list)).isTrue();
    }

    @Test
    public void testClear() {
        model.getContentObjects().clear();
        assertThat(model.getContentObjects()).isEmpty();
    }

    @Test
    public void testContains() {
        assertThat(model.getContentObjects().contains(EFACTORY.createSampleModelContentObject())).isFalse();
    }

    @Test
    public void testContainsAll() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(EFACTORY.createSampleModelContentObject());
        list.add(EFACTORY.createSampleModelContentObject());
        assertThat(model.getContentObjects().containsAll(list)).isFalse();
    }

    @Test
    public void testEquals() {
        List<SampleModelContentObject> list = new ArrayList<>();
        list.add(EFACTORY.createSampleModelContentObject());
        list.add(EFACTORY.createSampleModelContentObject());
        assertThat(Objects.equals(model.getContentObjects(), list)).isFalse();
    }

    @Test
    public void testGet() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().get(0));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testHashCode() {
        //noinspection ResultOfMethodCallIgnored
        model.getContentObjects().hashCode();
    }

    @Test
    public void testIndexOf() {
        assertThat(model.getContentObjects().indexOf(EFACTORY.createSampleModelContentObject())).isEqualTo(-1);
    }

    @Test
    public void testIsEmpty() {
        assertThat(model.getContentObjects().isEmpty()).isTrue();
    }

    @Test
    public void testIterator() {
        model.getContentObjects().iterator();
    }

    @Test
    public void testLastIndexOf() {
        assertThat(model.getContentObjects().lastIndexOf(EFACTORY.createSampleModelContentObject())).isEqualTo(-1);
    }

    @Test
    public void testListIterator() {
        model.getContentObjects().listIterator();
    }

    @Test
    public void testListIteratorIndex() {
        model.getContentObjects().listIterator(0);
    }

    @Test
    public void testListIteratorInvalidIndex() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().listIterator(1));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testMoveIndex() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().move(0, 0));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testMoveObject() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().move(0, EFACTORY.createSampleModelContentObject()));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testRemoveInvalidIndex() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().remove(0));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testRemoveObject() {
        model.getContentObjects().remove(EFACTORY.createSampleModelContentObject());
    }

    @Test
    public void testRemoveAllCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
        model.getContentObjects().removeAll(collection);
    }

    @Test
    public void testRemoveAllEmptyCollection() {
        model.getContentObjects().removeAll(new ArrayList<SampleModelContentObject>());
    }

    @Test
    public void testRetainAllCollection() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
        model.getContentObjects().retainAll(collection);
    }

    @Test
    public void testRetainAllEmptyCollection() {
        model.getContentObjects().retainAll(new ArrayList<>());
    }

    @Test
    public void testSetInvalidIndex() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().set(0, EFACTORY.createSampleModelContentObject()));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testSetValidIndex() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        model.getContentObjects().set(0, EFACTORY.createSampleModelContentObject());
    }

    @Test
    public void testSetInvalidObject() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);

        Throwable thrown = catchThrowable(() -> model.getContentObjects().set(0, SampleModelContentObject.class.cast(EFACTORY.createSampleModel())));
        assertThat(thrown).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testSizeEmptyList() {
        assertThat(model.getContentObjects().size()).isEqualTo(0);
    }

    @Test
    public void testSize() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        assertThat(model.getContentObjects().size()).isEqualTo(2);
    }

    @Test
    public void testSubListInvalidIndexes() {
        Throwable thrown = catchThrowable(() -> model.getContentObjects().subList(0, 1));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testSubList() {
        Collection<SampleModelContentObject> collection = new ArrayList<>();
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
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
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
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
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
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
        collection.add(EFACTORY.createSampleModelContentObject());
        collection.add(EFACTORY.createSampleModelContentObject());
        model.getContentObjects().addAll(collection);
        //noinspection ResultOfMethodCallIgnored
        model.getContentObjects().toString();
    }
}
