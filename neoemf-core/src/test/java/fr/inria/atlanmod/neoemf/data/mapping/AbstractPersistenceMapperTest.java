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

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test-case about {@link DataMapper} and its implementations.
 */
// TODO Add tests with `key.position() > size()` and an existing many value/reference
public abstract class AbstractPersistenceMapperTest extends AbstractUnitTest {

    /**
     * The default {@link Id} used to store features.
     */
    protected static Id idBase = Id.getProvider().fromLong(42);

    /**
     * The default single-valued feature bean.
     */
    private final SingleFeatureBean sfBase = SingleFeatureBean.of(idBase, 10);

    /**
     * The default multi-valued feature bean.
     */
    private final ManyFeatureBean mfBase = sfBase.withPosition(0);

    /**
     * The default meta-class bean.
     */
    private final ClassBean cBase = ClassBean.of("Metaclass0", "Uri0");

    // Variables initialization

    private final Id id0 = Id.getProvider().fromLong(0);
    private final Id id1 = Id.getProvider().fromLong(1);

    private final String value0 = "Value0";
    private final String value1 = "Value1";
    private final String value2 = "Value2";
    private final String value3 = "Value3";

    private final Id ref0 = Id.getProvider().fromLong(10);
    private final Id ref1 = Id.getProvider().fromLong(11);
    private final Id ref2 = Id.getProvider().fromLong(12);
    private final Id ref3 = Id.getProvider().fromLong(13);

    /**
     * The {@link DataMapper} used for this test case.
     */
    protected DataMapper mapper;

    /**
     * Creates the {@link DataMapper} to test.
     *
     * @throws IOException if an I/O error occurs
     */
    @Before
    public void createMapper() throws IOException {
        mapper = context().createMapper(file());

        // Defines the meta-classes
        mapper.metaClassFor(idBase, cBase);
        mapper.metaClassFor(ref0, cBase);
        mapper.metaClassFor(ref1, cBase);
        mapper.metaClassFor(ref2, cBase);
        mapper.metaClassFor(ref3, cBase);
    }

    /**
     * Closes the {@link DataMapper}.
     *
     * @throws IOException if an I/O error occurs
     */
    @After
    public void closeMapper() throws IOException {
        if (nonNull(mapper)) {
            mapper.close();
        }
    }

    /**
     * Checks the behavior of {@link DataMapper#close()}. A call to {@link DataMapper#close()} on a closed {@link
     * DataMapper} should do nothing.
     */
    @Test
    public void testCloseThenClose() {
        // First close
        mapper.close();

        // Second close
        assertThat(catchThrowable(() ->
                mapper.close()
        )).isNull();

        // Third close
        assertThat(catchThrowable(() ->
                mapper.close()
        )).isNull();
    }

    //region Containers

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Same() {
        SingleFeatureBean container = SingleFeatureBean.of(id0, 20);

        // Define the containers
        mapper.containerFor(idBase, container);
        assertThat(mapper.containerOf(idBase)).contains(container);

        mapper.containerFor(id1, container);
        assertThat(mapper.containerOf(id1)).contains(container);

        mapper.removeContainer(idBase);
        mapper.removeContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Different() {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);
        SingleFeatureBean container1 = SingleFeatureBean.of(id0, 21);

        // Define the containers
        mapper.containerFor(idBase, container0);
        assertThat(mapper.containerOf(idBase)).contains(container0);

        mapper.containerFor(id1, container1);
        assertThat(mapper.containerOf(id1)).contains(container1);

        // Replace the existing container
        mapper.containerFor(idBase, container1);
        assertThat(mapper.containerOf(idBase)).contains(container1);

        mapper.removeContainer(idBase);
        mapper.removeContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGet_Container_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(idBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Container_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.containerFor(idBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testRemove_Container() {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);

        // Define the containers
        mapper.containerFor(idBase, container0);
        assertThat(mapper.containerOf(idBase)).contains(container0);

        // Replace the existing container
        mapper.removeContainer(idBase);
        assertThat(mapper.containerOf(idBase)).isNotPresent();

        mapper.removeContainer(idBase);
    }

    //endregion

    //region Metaclasses

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Same() {
        Id id0 = Id.getProvider().fromLong(40);
        Id id1 = Id.getProvider().fromLong(41);

        ClassBean metaClass = ClassBean.of("Metaclass1", "Uri1");

        // Define the meta-classes
        mapper.metaClassFor(id0, metaClass);
        assertThat(mapper.metaClassOf(id0)).contains(metaClass);

        mapper.metaClassFor(id1, metaClass);
        assertThat(mapper.metaClassOf(id1)).contains(metaClass);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Different() {
        Id id0 = Id.getProvider().fromLong(40);

        ClassBean metaClass0 = ClassBean.of("Metaclass1", "Uri1");
        ClassBean metaClass1 = ClassBean.of("Metaclass2", "Uri2");

        // Define the meta-classes
        assertThat(mapper.metaClassFor(id0, metaClass0)).isTrue();
        assertThat(mapper.metaClassOf(id0)).contains(metaClass0);

        // Replace the existing meta-class
        assertThat(mapper.metaClassFor(id0, metaClass1)).isFalse();
        assertThat(mapper.metaClassOf(id0)).contains(metaClass0);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} when the element doesn't exist..
     */
    @Test
    public void testGet_Metaclass_NotDefined() {
        Id id0 = Id.getProvider().fromLong(40);

        assertThat(catchThrowable(() ->
                assertThat(mapper.metaClassOf(id0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassFor(Id, ClassBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Metaclass_Null() {
        Id id0 = Id.getProvider().fromLong(40);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.metaClassFor(id0, null)
        )).isInstanceOf(NullPointerException.class);
    }

    //endregion

    //region Single-value attributes

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)} and {@link
     * ValueMapper#valueFor(SingleFeatureBean, Object)}.
     */
    @Test
    public void testGetSet_Value() {
        // Define values
        mapper.valueFor(sfBase, value0);
        assertThat(mapper.valueOf(sfBase)).contains(value0);

        // Replace the existing value
        assertThat(mapper.valueFor(sfBase, value1)).contains(value0);
        assertThat(mapper.valueOf(sfBase)).contains(value1);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)}.
     */
    @Test
    public void testGet_Value_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(sfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_Value_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(sfBase, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)}.
     */
    @Test
    public void testRemove_Value() {
        mapper.valueFor(sfBase, value0);

        assertThat(mapper.valueOf(sfBase)).isPresent();

        mapper.removeValue(sfBase);

        assertThat(mapper.valueOf(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemove_Value_NotDefined() {
        assertThat(catchThrowable(() ->
                mapper.removeValue(sfBase)
        )).isNull();
    }

    //endregion

    //region Multi-valued attributes

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} and {@link
     * ManyValueMapper#valueFor(ManyFeatureBean, Object)}.
     */
    @Test
    public void testGetSet_ManyValue() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);

        // Replace the existing values
        assertThat(mapper.valueFor(sfBase.withPosition(0), value2)).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value2);

        assertThat(mapper.valueFor(sfBase.withPosition(1), value3)).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testGet_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSet_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(mfBase, value0)).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_ManyValue_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(mfBase, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)}.
     */
    @Test
    public void testGetAll_ManyValue() {
        // Add values in natural order
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        // Post-process the returned Iterable
        List<String> actualValues = mapper.allValuesOf(sfBase);

        assertThat(actualValues).hasSize(3);

        // Check the order of values
        assertThat(actualValues.get(0)).isEqualTo(value0);
        assertThat(actualValues.get(1)).isEqualTo(value1);
        assertThat(actualValues.get(2)).isEqualTo(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)} when the feature doesn't contain
     * any element.
     */
    @Test
    public void testGetAll_Empty() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.allValuesOf(sfBase)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @Test
    public void testAdd_ManyValue() {
        // Add values in natural order
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        // Check all values
        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @Test
    public void testAdd_ManyValue_AnyOrder() {
        // Add values in any order
        assertThat(catchThrowable(() ->
                mapper.addValue(sfBase.withPosition(2), value2)
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testAdd_ManyValue_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addValue(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testAppend_ManyValue() {
        int index;

        // Append values
        index = mapper.appendValue(sfBase, value0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);

        index = mapper.appendValue(sfBase, value1);
        assertThat(index).isEqualTo(1);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testAppend_ManyValue_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendValue(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAddAll_ManyValue_FromStart() {
        mapper.addAllValues(sfBase.withPosition(0), Arrays.asList(value0, value1));

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAddAll_ManyValue_WithOffset() {
        assertThat(catchThrowable(() ->
                mapper.addAllValues(sfBase.withPosition(1), Arrays.asList(value0, value1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @Test
    public void testAddAll_ManyValue_FromMiddle() {
        // Append values
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);

        mapper.addAllValues(sfBase.withPosition(1), Arrays.asList(value2, value3));

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value2);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).contains(value3);
        assertThat(mapper.valueOf(sfBase.withPosition(3))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @Test
    public void testAddAll_ManyValue_FromEnd() {
        mapper.appendValue(sfBase, value0);
        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);

        mapper.addAllValues(sfBase.withPosition(1), Arrays.asList(value1, value2));

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with an empty collection.
     */
    @Test
    public void testAddAll_ManyValue_Empty() {
        mapper.addAllValues(mfBase, Collections.emptyList());

        assertThat(mapper.sizeOfValue(mfBase.withoutPosition())).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAddAll_ManyValue_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.addAllValues(mfBase, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAddAll_ManyValue_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addAllValues(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAppendAll_ManyValue_FromStart() {
        int index;

        index = mapper.appendAllValues(sfBase, Arrays.asList(value0, value1));
        assertThat(index).isEqualTo(0);

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature already
     * has values.
     */
    @Test
    public void testAppendAll_ManyValue_FromEnd() {
        int index;

        index = mapper.appendValue(sfBase, value0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);

        index = mapper.appendAllValues(sfBase, Arrays.asList(value1, value2));
        assertThat(index).isEqualTo(1);

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAll_ManyValue_Empty() {
        mapper.appendAllValues(sfBase, Collections.emptyList());

        assertThat(mapper.sizeOfValue(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAppendAll_ManyValue_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.appendAllValues(sfBase, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAppendAll_ManyValue_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendAllValues(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyValue() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        assertThat(mapper.sizeOfValue(sfBase)).contains(2);

        // Remove values
        mapper.removeValue(sfBase.withPosition(0));
        assertThat(mapper.sizeOfValue(sfBase)).contains(1);

        mapper.removeValue(sfBase.withPosition(0));
        assertThat(mapper.sizeOfValue(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyValue_Before() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        // Remove value, and check the removed value
        assertThat(mapper.removeValue(sfBase.withPosition(0))).contains(value0);

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value2);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyValue_After() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        // Remove value, and check the removed value
        assertThat(mapper.removeValue(sfBase.withPosition(1))).contains(value1);

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value2);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemove_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)}.
     */
    @Test
    public void testRemoveAll_ManyValue() {
        // Initialize values
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        // Check the values
        assertThat(mapper.sizeOfValue(sfBase)).contains(3);

        // Remove all values
        mapper.removeAllValues(sfBase);

        // Check that all element doesn't exist
        assertThat(mapper.valueOf(sfBase.withPosition(0))).isNotPresent();
        assertThat(mapper.valueOf(sfBase.withPosition(1))).isNotPresent();
        assertThat(mapper.valueOf(sfBase.withPosition(2))).isNotPresent();

        // Check the values
        assertThat(mapper.sizeOfValue(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemoveAll_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                mapper.removeAllValues(sfBase)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyValue_Before() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);
        mapper.addValue(sfBase.withPosition(3), value3);

        // Move value, and check the moved value
        assertThat(mapper.moveValue(sfBase.withPosition(0), sfBase.withPosition(1))).contains(value0);

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).contains(value2);
        assertThat(mapper.valueOf(sfBase.withPosition(3))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyValue_After() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);
        mapper.addValue(sfBase.withPosition(3), value3);

        // Move value, and check the moved value
        assertThat(mapper.moveValue(sfBase.withPosition(2), sfBase.withPosition(0))).contains(value2);

        assertThat(mapper.valueOf(sfBase.withPosition(0))).contains(value2);
        assertThat(mapper.valueOf(sfBase.withPosition(1))).contains(value0);
        assertThat(mapper.valueOf(sfBase.withPosition(2))).contains(value1);
        assertThat(mapper.valueOf(sfBase.withPosition(3))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureBean, ManyFeatureBean)} when the value doesn't
     * exist.
     */
    @Test
    public void testMove_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.moveValue(sfBase.withPosition(0), sfBase.withPosition(1))).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testContains_ManyValue() {
        // Initialize values
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        // Check that contains
        assertThat(mapper.containsValue(sfBase, value1)).isTrue();

        // Remove all values
        mapper.removeAllValues(sfBase);

        // Check that doesn't contain
        assertThat(mapper.containsValue(sfBase, value1)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureBean, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testContains_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(sfBase, value0)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureBean, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testContains_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(sfBase, null)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testIndexOf_ManyValue() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);
        mapper.addValue(sfBase.withPosition(3), value0);
        mapper.addValue(sfBase.withPosition(4), value3);

        // Check first index
        assertThat(mapper.indexOfValue(sfBase, value0)).contains(0);

        // Remove the first value
        assertThat(mapper.removeValue(sfBase.withPosition(0))).contains(value0);

        // Check first index
        assertThat(mapper.indexOfValue(sfBase, value0)).contains(3 - 1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureBean, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testIndexOf_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(sfBase, value0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureBean, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testIndexOf_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(sfBase, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testLastIndexOf_ManyValue() {
        // Initialize values
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);
        mapper.addValue(sfBase.withPosition(3), value3);
        mapper.addValue(sfBase.withPosition(4), value1);

        // Check last index
        assertThat(mapper.lastIndexOfValue(sfBase, value1)).contains(4);

        // Remove the last value
        assertThat(mapper.removeValue(sfBase.withPosition(4))).contains(value1);

        // Check last index
        assertThat(mapper.lastIndexOfValue(sfBase, value1)).contains(1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureBean, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testLastIndexOf_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(sfBase, value0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureBean, Object)} when the value is
     * {@code null}.
     */
    @Test
    public void testLastIndexOf_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(sfBase, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)}.
     */
    @Test
    public void testSize_ManyValue() {
        // Initialize values
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        // Check the size
        assertThat(mapper.sizeOfValue(sfBase)).contains(3);

        // Remove a value
        mapper.removeValue(sfBase.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfValue(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testSize_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfValue(sfBase)).isNotPresent()
        )).isNull();
    }

    //endregion

    //region Single-valued references

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureBean)} and {@link
     * ReferenceMapper#referenceFor(SingleFeatureBean, Id)}.
     */
    @Test
    public void testGetSet_Reference() {
        // Define references
        mapper.referenceFor(sfBase, ref0);
        assertThat(mapper.referenceOf(sfBase)).contains(ref0);

        // Replace the existing reference
        assertThat(mapper.referenceFor(sfBase, ref1)).contains(ref0);
        assertThat(mapper.referenceOf(sfBase)).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureBean)}.
     */
    @Test
    public void testGet_Reference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(sfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceFor(SingleFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_Reference_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(sfBase, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#removeReference(SingleFeatureBean)}.
     */
    @Test
    public void testRemove_Reference() {
        mapper.referenceFor(sfBase, ref0);

        assertThat(mapper.referenceOf(sfBase)).isPresent();

        mapper.removeReference(sfBase);

        assertThat(mapper.referenceOf(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#removeReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemove_Reference_NotDefined() {
        assertThat(catchThrowable(() ->
                mapper.removeReference(sfBase)
        )).isNull();
    }

    //endregion

    //region Multi-valued references

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureBean)} and {@link
     * ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)}.
     */
    @Test
    public void testGetSet_ManyReference() {
        // Initialize the references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);

        // Replace the existing references
        assertThat(mapper.referenceFor(sfBase.withPosition(0), ref2)).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref2);

        assertThat(mapper.referenceFor(sfBase.withPosition(1), ref3)).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testGet_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSet_ManyReference_NotDefined() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(mfBase, ref0)).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_ManyReference_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(mfBase, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureBean)}.
     */
    @Test
    public void testGetAll_ManyReference() {
        // Add references in natural order
        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        // Post-process the returned Iterable
        List<Id> actualReferences = MoreIterables.stream(mapper.<String>allReferencesOf(sfBase)).collect(Collectors.toList());

        assertThat(actualReferences).hasSize(3);

        // Check the order of references
        assertThat(actualReferences.get(0)).isEqualTo(ref0);
        assertThat(actualReferences.get(1)).isEqualTo(ref1);
        assertThat(actualReferences.get(2)).isEqualTo(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureBean)} when the feature doesn't
     * contain any element.
     */
    @Test
    public void testGetAll_ManyReference_Empty() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.allReferencesOf(sfBase)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)}.
     */
    @Test
    public void testAdd_ManyReference() {
        // Add references in natural order
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        // Check all references
        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)}.
     */
    @Test
    public void testAdd_ManyReference_AnyOrder() {
        // Add references in any order
        assertThat(catchThrowable(() ->
                mapper.addReference(sfBase.withPosition(2), ref2)
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAdd_ManyReference_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addReference(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testAppend_ManyReference() {
        int index;

        // Append references
        index = mapper.appendReference(sfBase, ref0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);

        index = mapper.appendReference(sfBase, ref1);
        assertThat(index).isEqualTo(1);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAppend_ManyReference_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendReference(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature is
     * not defined yet.
     */
    @Test
    public void testAddAll_ManyReference_FromStart() {
        mapper.addAllReferences(sfBase.withPosition(0), Arrays.asList(ref0, ref1));

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature is
     * not defined yet.
     */
    @Test
    public void testAddAll_ManyReference_WithOffset() {
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(sfBase.withPosition(1), Arrays.asList(ref0, ref1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAll_ManyReference_FromMiddle() {
        // Append references
        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);

        mapper.addAllReferences(sfBase.withPosition(1), Arrays.asList(ref2, ref3));

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref2);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).contains(ref3);
        assertThat(mapper.referenceOf(sfBase.withPosition(3))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAll_ManyReference_FromEnd() {
        mapper.appendReference(sfBase, ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);

        mapper.addAllReferences(sfBase.withPosition(1), Arrays.asList(ref1, ref2));

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAddAll_ManyReference_Empty() {
        mapper.addAllReferences(mfBase, Collections.emptyList());

        assertThat(mapper.sizeOfReference(mfBase.withoutPosition())).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAddAll_ManyReference_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(mfBase, Arrays.asList(ref0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAddAll_ManyReference_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAppendAll_ManyReference_FromStart() {
        int index;

        index = mapper.appendAllReferences(sfBase, Arrays.asList(ref0, ref1));
        assertThat(index).isEqualTo(0);

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} when the feature
     * already has values.
     */
    @Test
    public void testAppendAll_ManyReference_FromEnd() {
        int index;

        index = mapper.appendReference(sfBase, ref0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);

        index = mapper.appendAllReferences(sfBase, Arrays.asList(ref1, ref2));
        assertThat(index).isEqualTo(1);

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAll_ManyReference_Empty() {
        mapper.appendAllReferences(sfBase, Collections.emptyList());

        assertThat(mapper.sizeOfReference(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with a collection
     * that contains a {@code null} element.
     */
    @Test
    public void testAppendAll_ManyReference_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(sfBase, Arrays.asList(ref0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with a {@code
     * null} collection.
     */
    @Test
    public void testAppendAll_ManyReference_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyReference() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        assertThat(mapper.sizeOfReference(sfBase)).contains(2);

        // Remove references
        mapper.removeReference(sfBase.withPosition(0));
        assertThat(mapper.sizeOfReference(sfBase)).contains(1);

        mapper.removeReference(sfBase.withPosition(0));
        assertThat(mapper.sizeOfReference(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyReference_Before() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        // Remove reference, and check the removed reference
        assertThat(mapper.removeReference(sfBase.withPosition(0))).contains(ref0);

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref2);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyReference_After() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        // Remove reference, and check the removed reference
        assertThat(mapper.removeReference(sfBase.withPosition(1))).contains(ref1);

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref2);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemove_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.removeReference(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureBean)}.
     */
    @Test
    public void testRemoveAll_ManyReference() {
        // Initialize references
        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        // Check the references
        assertThat(mapper.sizeOfReference(sfBase)).contains(3);

        // Remove all references
        mapper.removeAllReferences(sfBase);

        // Check that all element doesn't exist
        assertThat(mapper.referenceOf(sfBase.withPosition(0))).isNotPresent();
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).isNotPresent();
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).isNotPresent();

        // Check the references
        assertThat(mapper.sizeOfReference(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureBean)} when the reference
     * doesn't exist.
     */
    @Test
    public void testRemoveAll_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                mapper.removeAllReferences(sfBase)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyReference_Before() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);
        mapper.addReference(sfBase.withPosition(3), ref3);

        // Move reference, and check the moved reference
        assertThat(mapper.moveReference(sfBase.withPosition(0), sfBase.withPosition(1))).contains(ref0);

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).contains(ref2);
        assertThat(mapper.referenceOf(sfBase.withPosition(3))).contains(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyReference_After() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);
        mapper.addReference(sfBase.withPosition(3), ref3);

        // Move reference, and check the moved reference
        assertThat(mapper.moveReference(sfBase.withPosition(2), sfBase.withPosition(0))).contains(ref2);

        assertThat(mapper.referenceOf(sfBase.withPosition(0))).contains(ref2);
        assertThat(mapper.referenceOf(sfBase.withPosition(1))).contains(ref0);
        assertThat(mapper.referenceOf(sfBase.withPosition(2))).contains(ref1);
        assertThat(mapper.referenceOf(sfBase.withPosition(3))).contains(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureBean, ManyFeatureBean)} when the
     * reference doesn't exist.
     */
    @Test
    public void testMove_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.moveReference(sfBase.withPosition(0), sfBase.withPosition(1))).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testContains_ManyReference() {
        // Initialize references
        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        // Check that contains
        assertThat(mapper.containsReference(sfBase, ref1)).isTrue();

        // Remove all references
        mapper.removeAllReferences(sfBase);

        // Check that doesn't contain
        assertThat(mapper.containsReference(sfBase, ref1)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureBean, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testContains_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(sfBase, ref0)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureBean, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testContains_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(sfBase, null)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testIndexOf_ManyReference() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);
        mapper.addReference(sfBase.withPosition(3), ref0);
        mapper.addReference(sfBase.withPosition(4), ref3);

        // Check first index
        assertThat(mapper.indexOfReference(sfBase, ref0)).contains(0);

        // Remove the first reference
        assertThat(mapper.removeReference(sfBase.withPosition(0))).contains(ref0);

        // Check first index
        assertThat(mapper.indexOfReference(sfBase, ref0)).contains(3 - 1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureBean, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testIndexOf_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(sfBase, ref0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureBean, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testIndexOf_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(sfBase, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testLastIndexOf_ManyReference() {
        // Initialize references
        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);
        mapper.addReference(sfBase.withPosition(3), ref3);
        mapper.addReference(sfBase.withPosition(4), ref1);

        // Check last index
        assertThat(mapper.lastIndexOfReference(sfBase, ref1)).contains(4);

        // Remove the last reference
        assertThat(mapper.removeReference(sfBase.withPosition(4))).contains(ref1);

        // Check last index
        assertThat(mapper.lastIndexOfReference(sfBase, ref1)).contains(1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureBean, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testLastIndexOf_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(sfBase, ref0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureBean, Id)} when the reference
     * is {@code null}.
     */
    @Test
    public void testLastIndexOf_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(sfBase, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureBean)}.
     */
    @Test
    public void testSize_ManyReference() {
        // Initialize references
        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        // Check the size
        assertThat(mapper.sizeOfReference(sfBase)).contains(3);

        // Remove a reference
        mapper.removeReference(sfBase.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfReference(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSize_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfReference(sfBase)).isNotPresent()
        )).isNull();
    }

    //endregion
}
