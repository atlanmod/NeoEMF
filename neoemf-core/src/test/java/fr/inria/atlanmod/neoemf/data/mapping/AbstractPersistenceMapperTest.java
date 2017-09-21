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
import fr.inria.atlanmod.neoemf.core.StringId;
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
    protected static final Id id0 = StringId.generate("Id0");

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
        Id containerId0 = StringId.generate("ContainerId0");

        SingleFeatureBean container = SingleFeatureBean.of(containerId0, "Container0");

        Id id1 = StringId.generate("Id1");

        // Define the containers
        mapper.containerFor(id0, container);
        assertThat(mapper.containerOf(id0)).contains(container);

        mapper.containerFor(id1, container);
        assertThat(mapper.containerOf(id1)).contains(container);

        mapper.removeContainer(id0);
        mapper.removeContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Different() {
        Id containerId0 = StringId.generate("ContainerId0");

        SingleFeatureBean container0 = SingleFeatureBean.of(containerId0, "Container0");
        SingleFeatureBean container1 = SingleFeatureBean.of(containerId0, "Container1");

        Id id1 = StringId.generate("Id1");

        // Define the containers
        mapper.containerFor(id0, container0);
        assertThat(mapper.containerOf(id0)).contains(container0);

        mapper.containerFor(id1, container1);
        assertThat(mapper.containerOf(id1)).contains(container1);

        // Replace the existing container
        mapper.containerFor(id0, container1);
        assertThat(mapper.containerOf(id0)).contains(container1);

        mapper.removeContainer(id0);
        mapper.removeContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGet_Container_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(id0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Container_Null() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.containerFor(id0, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testRemove_Container() {
        Id containerId0 = StringId.generate("ContainerId0");

        SingleFeatureBean container0 = SingleFeatureBean.of(containerId0, "Container0");

        // Define the containers
        mapper.containerFor(id0, container0);
        assertThat(mapper.containerOf(id0)).contains(container0);

        // Replace the existing container
        mapper.removeContainer(id0);
        assertThat(mapper.containerOf(id0)).isNotPresent();

        mapper.removeContainer(id0);
    }

    //endregion

    //region Metaclasses

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Same() {
        ClassBean metaClass = ClassBean.of("Metaclass0", "Uri0");

        Id id1 = StringId.generate("Id1");

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
        ClassBean metaClass0 = ClassBean.of("Metaclass0", "Uri0");
        ClassBean metaClass1 = ClassBean.of("Metaclass1", "Uri1");

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.metaClassOf(id0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassFor(Id, ClassBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Metaclass_Null() {
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
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        // Define values
        mapper.valueFor(key, value0);
        assertThat(mapper.valueOf(key)).contains(value0);

        // Replace the existing value
        assertThat(mapper.valueFor(key, value1)).contains(value0);
        assertThat(mapper.valueOf(key)).contains(value1);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)}.
     */
    @Test
    public void testGet_Value_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_Value_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(SingleFeatureBean)} and {@link
     * ValueMapper#removeValue(SingleFeatureBean)}.
     */
    @Test
    public void testHasRemove_Value() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";

        mapper.valueFor(key, value0);

        assertThat(mapper.valueOf(key)).isPresent();
        assertThat(mapper.hasValue(key)).isTrue();

        mapper.removeValue(key);

        assertThat(mapper.valueOf(key)).isNotPresent();
        assertThat(mapper.hasValue(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testHas_Value_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasValue(key)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemove_Value_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeValue(key)
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
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";
        String value3 = "Value3";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);

        // Replace the existing values
        assertThat(mapper.valueFor(key.withPosition(0), value2)).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(0))).contains(value2);

        assertThat(mapper.valueFor(key.withPosition(1), value3)).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testGet_ManyValue_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_ManyValue_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, "Value0")).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_ManyValue_Null() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)}.
     */
    @Test
    public void testGetAll_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Add values in natural order
        mapper.appendValue(key, value0);
        mapper.appendValue(key, value1);
        mapper.appendValue(key, value2);

        // Post-process the returned Iterable
        List<String> actualValues = mapper.allValuesOf(key);

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
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.allValuesOf(key)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#hasAnyValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testHasAny_ManyValue_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyValue(key.withoutPosition())).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @Test
    public void testAdd_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Add values in natural order
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);

        // Check all values
        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @Test
    public void testAdd_ManyValue_AnyOrder() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value2 = "Value2";

        // Add values in any order
        assertThat(catchThrowable(() ->
                mapper.addValue(key.withPosition(2), value2)
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testAdd_ManyValue_Null() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addValue(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testAppend_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        int index;

        // Append values
        index = mapper.appendValue(key, value0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);

        index = mapper.appendValue(key, value1);
        assertThat(index).isEqualTo(1);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testAppend_ManyValue_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendValue(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAddAll_ManyValue_FromStart() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        mapper.addAllValues(key.withPosition(0), Arrays.asList(value0, value1));

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAddAll_ManyValue_WithOffset() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        assertThat(catchThrowable(() ->
                mapper.addAllValues(key.withPosition(1), Arrays.asList(value0, value1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @Test
    public void testAddAll_ManyValue_FromMiddle() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";
        String value3 = "Value3";

        // Append values
        mapper.appendValue(key, value0);
        mapper.appendValue(key, value1);

        mapper.addAllValues(key.withPosition(1), Arrays.asList(value2, value3));

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value2);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value3);
        assertThat(mapper.valueOf(key.withPosition(3))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @Test
    public void testAddAll_ManyValue_FromEnd() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        mapper.appendValue(key, value0);
        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);

        mapper.addAllValues(key.withPosition(1), Arrays.asList(value1, value2));

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with an empty collection.
     */
    @Test
    public void testAddAll_ManyValue_Empty() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        mapper.addAllValues(key, Collections.emptyList());

        assertThat(mapper.sizeOfValue(key.withoutPosition())).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAddAll_ManyValue_WithNull() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        String value0 = "Value0";

        assertThat(catchThrowable(() ->
                mapper.addAllValues(key, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAddAll_ManyValue_Null() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addAllValues(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAppendAll_ManyValue_FromStart() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        int index;

        index = mapper.appendAllValues(key, Arrays.asList(value0, value1));
        assertThat(index).isEqualTo(0);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature already
     * has values.
     */
    @Test
    public void testAppendAll_ManyValue_FromEnd() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        int index;

        index = mapper.appendValue(key, value0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);

        index = mapper.appendAllValues(key, Arrays.asList(value1, value2));
        assertThat(index).isEqualTo(1);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAll_ManyValue_Empty() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        mapper.appendAllValues(key, Collections.emptyList());

        assertThat(mapper.sizeOfValue(key)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAppendAll_ManyValue_WithNull() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";

        assertThat(catchThrowable(() ->
                mapper.appendAllValues(key, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAppendAll_ManyValue_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendAllValues(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        // Initialize values
        mapper.addValue(key.withPosition(0), "aaa");
        mapper.addValue(key.withPosition(1), "bbb");
        assertThat(mapper.sizeOfValue(key)).contains(2);
        assertThat(mapper.hasAnyValue(key)).isTrue();

        // Remove values
        mapper.removeValue(key.withPosition(0));
        assertThat(mapper.sizeOfValue(key)).contains(1);
        assertThat(mapper.hasAnyValue(key)).isTrue();

        mapper.removeValue(key.withPosition(0));
        assertThat(mapper.sizeOfValue(key)).isNotPresent();
        assertThat(mapper.hasAnyValue(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyValue_Before() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);

        // Remove value, and check the removed value
        assertThat(mapper.removeValue(key.withPosition(0))).contains(value0);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value2);
        assertThat(mapper.valueOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyValue_After() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);

        // Remove value, and check the removed value
        assertThat(mapper.removeValue(key.withPosition(1))).contains(value1);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value2);
        assertThat(mapper.valueOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemove_ManyValue_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)} and {@link
     * ManyValueMapper#hasAnyValue(SingleFeatureBean)}.
     */
    @Test
    public void testRemoveAll_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Initialize values
        mapper.appendValue(key, value0);
        mapper.appendValue(key, value1);
        mapper.appendValue(key, value2);

        // Check the values
        assertThat(mapper.hasAnyValue(key)).isTrue();
        assertThat(mapper.sizeOfValue(key)).contains(3);

        // Remove all values
        mapper.removeAllValues(key);

        // Check that all element doesn't exist
        assertThat(mapper.valueOf(key.withPosition(0))).isNotPresent();
        assertThat(mapper.valueOf(key.withPosition(1))).isNotPresent();
        assertThat(mapper.valueOf(key.withPosition(2))).isNotPresent();

        // Check the values
        assertThat(mapper.hasAnyValue(key)).isFalse();
        assertThat(mapper.sizeOfValue(key)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemoveAll_ManyValue_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeAllValues(key)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyValue_Before() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";
        String value3 = "Value3";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);
        mapper.addValue(key.withPosition(3), value3);

        // Move value, and check the moved value
        assertThat(mapper.moveValue(key.withPosition(0), key.withPosition(1))).contains(value0);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value2);
        assertThat(mapper.valueOf(key.withPosition(3))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyValue_After() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";
        String value3 = "Value3";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);
        mapper.addValue(key.withPosition(3), value3);

        // Move value, and check the moved value
        assertThat(mapper.moveValue(key.withPosition(2), key.withPosition(0))).contains(value2);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value2);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(3))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureBean, ManyFeatureBean)} when the value doesn't
     * exist.
     */
    @Test
    public void testMove_ManyValue_NotDefined() {
        ManyFeatureBean key0 = ManyFeatureBean.of(id0, "Feature0", 0);
        ManyFeatureBean key1 = key0.withPosition(1);

        assertThat(catchThrowable(() ->
                assertThat(mapper.moveValue(key0, key1)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testContains_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";

        // Initialize values
        mapper.appendValue(key, "aaa");
        mapper.appendValue(key, value0);
        mapper.appendValue(key, "zzz");

        // Check that contains
        assertThat(mapper.containsValue(key, value0)).isTrue();

        // Remove all values
        mapper.removeAllValues(key);

        // Check that doesn't contain
        assertThat(mapper.containsValue(key, value0)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureBean, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testContains_ManyValue_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(key, "aaa")).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureBean, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testContains_ManyValue_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(key, null)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testIndexOf_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), "aaa");
        mapper.addValue(key.withPosition(2), "bbb");
        mapper.addValue(key.withPosition(3), value0);
        mapper.addValue(key.withPosition(4), "ccc");

        // Check first index
        assertThat(mapper.indexOfValue(key, value0)).contains(0);

        // Remove the first value
        assertThat(mapper.removeValue(key.withPosition(0))).contains(value0);

        // Check first index
        assertThat(mapper.indexOfValue(key, value0)).contains(3 - 1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureBean, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testIndexOf_ManyValue_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(key, "aaa")).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureBean, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testIndexOf_ManyValue_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureBean, Object)}.
     */
    @Test
    public void testLastIndexOf_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        String value0 = "Value0";

        // Initialize values
        mapper.addValue(key.withPosition(0), "aaa");
        mapper.addValue(key.withPosition(1), value0);
        mapper.addValue(key.withPosition(2), "bbb");
        mapper.addValue(key.withPosition(3), "ccc");
        mapper.addValue(key.withPosition(4), value0);

        // Check last index
        assertThat(mapper.lastIndexOfValue(key, value0)).contains(4);

        // Remove the last value
        assertThat(mapper.removeValue(key.withPosition(4))).contains(value0);

        // Check last index
        assertThat(mapper.lastIndexOfValue(key, value0)).contains(1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureBean, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testLastIndexOf_ManyValue_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(key, "aaa")).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureBean, Object)} when the value is
     * {@code null}.
     */
    @Test
    public void testLastIndexOf_ManyValue_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)}.
     */
    @Test
    public void testSize_ManyValue() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        // Initialize values
        mapper.appendValue(key, "aaa");
        mapper.appendValue(key, "bbb");
        mapper.appendValue(key, "ccc");

        // Check the size
        assertThat(mapper.sizeOfValue(key)).contains(3);

        // Remove a value
        mapper.removeValue(key.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfValue(key)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testSize_ManyValue_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfValue(key)).isNotPresent()
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
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");

        // Define references
        mapper.referenceFor(key, ref0);
        assertThat(mapper.referenceOf(key)).contains(ref0);

        // Replace the existing reference
        assertThat(mapper.referenceFor(key, ref1)).contains(ref0);
        assertThat(mapper.referenceOf(key)).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureBean)}.
     */
    @Test
    public void testGet_Reference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceFor(SingleFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_Reference_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#hasReference(SingleFeatureBean)} and {@link
     * ReferenceMapper#removeReference(SingleFeatureBean)}.
     */
    @Test
    public void testHasRemove_Reference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");

        mapper.referenceFor(key, ref0);

        assertThat(mapper.referenceOf(key)).isPresent();
        assertThat(mapper.hasReference(key)).isTrue();

        mapper.removeReference(key);

        assertThat(mapper.referenceOf(key)).isNotPresent();
        assertThat(mapper.hasReference(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#hasReference(SingleFeatureBean)} when the reference doesn't exist.
     */
    @Test
    public void testHas_Reference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasReference(key)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#removeReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemove_Reference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeReference(key)
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
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");
        Id ref3 = StringId.generate("Ref3");

        // Initialize the references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);

        // Replace the existing references
        assertThat(mapper.referenceFor(key.withPosition(0), ref2)).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref2);

        assertThat(mapper.referenceFor(key.withPosition(1), ref3)).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testGet_ManyReference_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_ManyReference_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, StringId.generate("Ref0"))).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_ManyReference_Null() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureBean)}.
     */
    @Test
    public void testGetAll_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        // Add references in natural order
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, ref1);
        mapper.appendReference(key, ref2);

        // Post-process the returned Iterable
        List<Id> actualReferences = MoreIterables.stream(mapper.<String>allReferencesOf(key)).collect(Collectors.toList());

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
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.allReferencesOf(key)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#hasAnyReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testHasAny_ManyReference_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyReference(key.withoutPosition())).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)}.
     */
    @Test
    public void testAdd_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        // Add references in natural order
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);

        // Check all references
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)}.
     */
    @Test
    public void testAdd_ManyReference_AnyOrder() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref2 = StringId.generate("Ref2");

        // Add references in any order
        assertThat(catchThrowable(() ->
                mapper.addReference(key.withPosition(2), ref2)
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAdd_ManyReference_Null() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addReference(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testAppend_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");

        int index;

        // Append references
        index = mapper.appendReference(key, ref0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);

        index = mapper.appendReference(key, ref1);
        assertThat(index).isEqualTo(1);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAppend_ManyReference_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendReference(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature is
     * not defined yet.
     */
    @Test
    public void testAddAll_ManyReference_FromStart() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");

        mapper.addAllReferences(key.withPosition(0), Arrays.asList(ref0, ref1));

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature is
     * not defined yet.
     */
    @Test
    public void testAddAll_ManyReference_WithOffset() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");

        assertThat(catchThrowable(() ->
                mapper.addAllReferences(key.withPosition(1), Arrays.asList(ref0, ref1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAll_ManyReference_FromMiddle() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");
        Id ref3 = StringId.generate("Ref3");

        // Append references
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, ref1);

        mapper.addAllReferences(key.withPosition(1), Arrays.asList(ref2, ref3));

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref3);
        assertThat(mapper.referenceOf(key.withPosition(3))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAll_ManyReference_FromEnd() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        mapper.appendReference(key, ref0);
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);

        mapper.addAllReferences(key.withPosition(1), Arrays.asList(ref1, ref2));

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAddAll_ManyReference_Empty() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        mapper.addAllReferences(key, Collections.emptyList());

        assertThat(mapper.sizeOfReference(key.withoutPosition())).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAddAll_ManyReference_WithNull() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        Id ref0 = StringId.generate("Ref0");

        assertThat(catchThrowable(() ->
                mapper.addAllReferences(key, Arrays.asList(ref0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAddAll_ManyReference_Null() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAppendAll_ManyReference_FromStart() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");

        int index;

        index = mapper.appendAllReferences(key, Arrays.asList(ref0, ref1));
        assertThat(index).isEqualTo(0);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} when the feature
     * already has values.
     */
    @Test
    public void testAppendAll_ManyReference_FromEnd() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        int index;

        index = mapper.appendReference(key, ref0);
        assertThat(index).isEqualTo(0);
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);

        index = mapper.appendAllReferences(key, Arrays.asList(ref1, ref2));
        assertThat(index).isEqualTo(1);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAll_ManyReference_Empty() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        mapper.appendAllReferences(key, Collections.emptyList());

        assertThat(mapper.sizeOfReference(key)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with a collection
     * that contains a {@code null} element.
     */
    @Test
    public void testAppendAll_ManyReference_WithNull() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");

        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(key, Arrays.asList(ref0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with a {@code
     * null} collection.
     */
    @Test
    public void testAppendAll_ManyReference_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        // Initialize references
        mapper.addReference(key.withPosition(0), StringId.generate("aaa"));
        mapper.addReference(key.withPosition(1), StringId.generate("bbb"));
        assertThat(mapper.sizeOfReference(key)).contains(2);
        assertThat(mapper.hasAnyReference(key)).isTrue();

        // Remove references
        mapper.removeReference(key.withPosition(0));
        assertThat(mapper.sizeOfReference(key)).contains(1);
        assertThat(mapper.hasAnyReference(key)).isTrue();

        mapper.removeReference(key.withPosition(0));
        assertThat(mapper.sizeOfReference(key)).isNotPresent();
        assertThat(mapper.hasAnyReference(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyReference_Before() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);

        // Remove reference, and check the removed reference
        assertThat(mapper.removeReference(key.withPosition(0))).contains(ref0);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @Test
    public void testRemove_ManyReference_After() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);

        // Remove reference, and check the removed reference
        assertThat(mapper.removeReference(key.withPosition(1))).contains(ref1);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemove_ManyReference_NotDefined() {
        ManyFeatureBean key = ManyFeatureBean.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeReference(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureBean)} and {@link
     * ManyReferenceMapper#hasAnyReference(SingleFeatureBean)}.
     */
    @Test
    public void testRemoveAll_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");

        // Initialize references
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, ref1);
        mapper.appendReference(key, ref2);

        // Check the references
        assertThat(mapper.hasAnyReference(key)).isTrue();
        assertThat(mapper.sizeOfReference(key)).contains(3);

        // Remove all references
        mapper.removeAllReferences(key);

        // Check that all element doesn't exist
        assertThat(mapper.referenceOf(key.withPosition(0))).isNotPresent();
        assertThat(mapper.referenceOf(key.withPosition(1))).isNotPresent();
        assertThat(mapper.referenceOf(key.withPosition(2))).isNotPresent();

        // Check the references
        assertThat(mapper.hasAnyReference(key)).isFalse();
        assertThat(mapper.sizeOfReference(key)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureBean)} when the reference
     * doesn't exist.
     */
    @Test
    public void testRemoveAll_ManyReference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeAllReferences(key)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyReference_Before() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");
        Id ref3 = StringId.generate("Ref3");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);
        mapper.addReference(key.withPosition(3), ref3);

        // Move reference, and check the moved reference
        assertThat(mapper.moveReference(key.withPosition(0), key.withPosition(1))).contains(ref0);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(3))).contains(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureBean, ManyFeatureBean)}.
     */
    @Test
    public void testMove_ManyReference_After() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");
        Id ref1 = StringId.generate("Ref1");
        Id ref2 = StringId.generate("Ref2");
        Id ref3 = StringId.generate("Ref3");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);
        mapper.addReference(key.withPosition(3), ref3);

        // Move reference, and check the moved reference
        assertThat(mapper.moveReference(key.withPosition(2), key.withPosition(0))).contains(ref2);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(3))).contains(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureBean, ManyFeatureBean)} when the
     * reference doesn't exist.
     */
    @Test
    public void testMove_ManyReference_NotDefined() {
        ManyFeatureBean key0 = ManyFeatureBean.of(id0, "Feature0", 0);
        ManyFeatureBean key1 = key0.withPosition(1);

        assertThat(catchThrowable(() ->
                assertThat(mapper.moveReference(key0, key1)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testContains_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");

        // Initialize references
        mapper.appendReference(key, StringId.generate("aaa"));
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, StringId.generate("zzz"));

        // Check that contains
        assertThat(mapper.containsReference(key, ref0)).isTrue();

        // Remove all references
        mapper.removeAllReferences(key);

        // Check that doesn't contain
        assertThat(mapper.containsReference(key, ref0)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureBean, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testContains_ManyReference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(key, StringId.generate("aaa"))).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureBean, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testContains_ManyReference_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(key, null)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testIndexOf_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), StringId.generate("aaa"));
        mapper.addReference(key.withPosition(2), StringId.generate("bbb"));
        mapper.addReference(key.withPosition(3), ref0);
        mapper.addReference(key.withPosition(4), StringId.generate("ccc"));

        // Check first index
        assertThat(mapper.indexOfReference(key, ref0)).contains(0);

        // Remove the first reference
        assertThat(mapper.removeReference(key.withPosition(0))).contains(ref0);

        // Check first index
        assertThat(mapper.indexOfReference(key, ref0)).contains(3 - 1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureBean, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testIndexOf_ManyReference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(key, StringId.generate("aaa"))).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureBean, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testIndexOf_ManyReference_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureBean, Id)}.
     */
    @Test
    public void testLastIndexOf_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        Id ref0 = StringId.generate("Ref0");

        // Initialize references
        mapper.addReference(key.withPosition(0), StringId.generate("aaa"));
        mapper.addReference(key.withPosition(1), ref0);
        mapper.addReference(key.withPosition(2), StringId.generate("bbb"));
        mapper.addReference(key.withPosition(3), StringId.generate("ccc"));
        mapper.addReference(key.withPosition(4), ref0);

        // Check last index
        assertThat(mapper.lastIndexOfReference(key, ref0)).contains(4);

        // Remove the last reference
        assertThat(mapper.removeReference(key.withPosition(4))).contains(ref0);

        // Check last index
        assertThat(mapper.lastIndexOfReference(key, ref0)).contains(1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureBean, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testLastIndexOf_ManyReference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(key, StringId.generate("aaa"))).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureBean, Id)} when the reference
     * is {@code null}.
     */
    @Test
    public void testLastIndexOf_ManyReference_Null() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureBean)}.
     */
    @Test
    public void testSize_ManyReference() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        // Initialize references
        mapper.appendReference(key, StringId.generate("aaa"));
        mapper.appendReference(key, StringId.generate("bbb"));
        mapper.appendReference(key, StringId.generate("ccc"));

        // Check the size
        assertThat(mapper.sizeOfReference(key)).contains(3);

        // Remove a reference
        mapper.removeReference(key.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfReference(key)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSize_ManyReference_NotDefined() {
        SingleFeatureBean key = SingleFeatureBean.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfReference(key)).isNotPresent()
        )).isNull();
    }

    //endregion
}
