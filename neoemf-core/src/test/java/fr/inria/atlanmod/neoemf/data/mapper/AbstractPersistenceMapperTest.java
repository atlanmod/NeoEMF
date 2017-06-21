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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.common.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

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
 * An abstract test-case that checks the behavior of {@link DataMapper}s and their sub-classes.
 */
// TODO Add tests with `key.position() > size()` and an existing many value/reference
public abstract class AbstractPersistenceMapperTest extends AbstractUnitTest {

    /**
     * The default {@link Id} used to store features.
     */
    protected static final Id id0 = StringId.of("Id0");

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
     * Checks the behavior of {@link DataMapper#close()}. A call to {@link DataMapper#close()} on a closed
     * {@link DataMapper} should do nothing.
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
     * SingleFeatureKey)}.
     */
    @Test
    public void testGetSetSameContainer() {
        Id containerId0 = StringId.of("ContainerId0");

        SingleFeatureKey container = SingleFeatureKey.of(containerId0, "Container0");

        Id id1 = StringId.of("Id1");

        // Define the containers
        mapper.containerFor(id0, container);
        assertThat(mapper.containerOf(id0)).contains(container);

        mapper.containerFor(id1, container);
        assertThat(mapper.containerOf(id1)).contains(container);

        mapper.unsetContainer(id0);
        mapper.unsetContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureKey)}.
     */
    @Test
    public void testGetSetDifferentContainer() {
        Id containerId0 = StringId.of("ContainerId0");

        SingleFeatureKey container0 = SingleFeatureKey.of(containerId0, "Container0");
        SingleFeatureKey container1 = SingleFeatureKey.of(containerId0, "Container1");

        Id id1 = StringId.of("Id1");

        // Define the containers
        mapper.containerFor(id0, container0);
        assertThat(mapper.containerOf(id0)).contains(container0);

        mapper.containerFor(id1, container1);
        assertThat(mapper.containerOf(id1)).contains(container1);

        // Replace the existing container
        mapper.containerFor(id0, container1);
        assertThat(mapper.containerOf(id0)).contains(container1);

        mapper.unsetContainer(id0);
        mapper.unsetContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureKey)} with a {@code null} value.
     */
    @Test
    public void testUnsetContainer() {
        Id containerId0 = StringId.of("ContainerId0");

        SingleFeatureKey container0 = SingleFeatureKey.of(containerId0, "Container0");

        // Define the containers
        mapper.containerFor(id0, container0);
        assertThat(mapper.containerOf(id0)).contains(container0);

        // Replace the existing container
        mapper.unsetContainer(id0);
        assertThat(mapper.containerOf(id0)).isNotPresent();

        mapper.unsetContainer(id0);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGetInexistingContainer() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(id0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureKey)} with a {@code null} value.
     */
    @Test
    public void testSetNullContainer() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.containerFor(id0, null)
        )).isInstanceOf(NullPointerException.class);
    }

    //endregion

    //region Metaclasses

    /**
     * Checks the behavior of {@link ClassMapper#metaclassOf(Id)} and {@link ClassMapper#metaclassFor(Id,
     * ClassDescriptor)}.
     */
    @Test
    public void testGetSetSameMetaclass() {
        ClassDescriptor metaclass = ClassDescriptor.of("Metaclass0", "Uri0");

        Id id1 = StringId.of("Id1");

        // Define the metaclasses
        mapper.metaclassFor(id0, metaclass);
        assertThat(mapper.metaclassOf(id0)).contains(metaclass);

        mapper.metaclassFor(id1, metaclass);
        assertThat(mapper.metaclassOf(id1)).contains(metaclass);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaclassOf(Id)} and {@link ClassMapper#metaclassFor(Id,
     * ClassDescriptor)}.
     */
    @Test
    public void testGetSetDifferentMetaclass() {
        ClassDescriptor metaclass0 = ClassDescriptor.of("Metaclass0", "Uri0");
        ClassDescriptor metaclass1 = ClassDescriptor.of("Metaclass1", "Uri1");

        Id id1 = StringId.of("Id1");

        // Define the metaclasses
        mapper.metaclassFor(id0, metaclass0);
        assertThat(mapper.metaclassOf(id0)).contains(metaclass0);

        mapper.metaclassFor(id1, metaclass1);
        assertThat(mapper.metaclassOf(id1)).contains(metaclass1);

        // Replace the existing metaclass
        mapper.metaclassFor(id1, metaclass0);
        assertThat(mapper.metaclassOf(id1)).contains(metaclass0);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaclassOf(Id)} when the element doesn't exist..
     */
    @Test
    public void testGetInexistingMetaclass() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.metaclassOf(id0)).isNotPresent()
        )).isNull();
    }

    //endregion

    //region Single-value attributes

    /**
     * Checks the behavior of {@link ClassMapper#metaclassFor(Id, ClassDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullMetaclass() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.metaclassFor(id0, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureKey)} and {@link
     * ValueMapper#valueFor(SingleFeatureKey, Object)}.
     */
    @Test
    public void testGetSetValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureKey)}.
     */
    @Test
    public void testGetInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(SingleFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(SingleFeatureKey)} and {@link
     * ValueMapper#unsetValue(SingleFeatureKey)}.
     */
    @Test
    public void testIsSetUnsetValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";

        mapper.valueFor(key, value0);

        assertThat(mapper.valueOf(key)).isPresent();
        assertThat(mapper.hasValue(key)).isTrue();

        mapper.unsetValue(key);

        assertThat(mapper.valueOf(key)).isNotPresent();
        assertThat(mapper.hasValue(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(SingleFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testIsSetInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasValue(key)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#unsetValue(SingleFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testUnsetInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.unsetValue(key)
        )).isNull();
    }

    //endregion

    //region Multi-valued attributes

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureKey)} and {@link
     * ManyValueMapper#valueFor(ManyFeatureKey, Object)}.
     */
    @Test
    public void testGetSetManyValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testGetInexistingManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetInexistingManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, "Value0")).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureKey)}.
     */
    @Test
    public void testAllValuesOf() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureKey)}.
     */
    @Test
    public void testAllValuesOfUnorderedAdd() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Add values in any order
        mapper.addValue(key.withPosition(2), value2);
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);

        // Post-process the returned Iterable
        List<String> actualValues = mapper.allValuesOf(key);

        assertThat(actualValues).hasSize(3);

        // Check the order of values
        assertThat(actualValues.get(0)).isEqualTo(value0);
        assertThat(actualValues.get(1)).isEqualTo(value1);
        assertThat(actualValues.get(2)).isEqualTo(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureKey)} when the feature doesn't contain any
     * element.
     */
    @Test
    public void testAllValuesEmpty() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.allValuesOf(key)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#hasAnyValue(SingleFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testIsSetInexistingManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyValue(key.withoutPosition())).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAddValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAnyOrderAddValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        // Add values in any order
        mapper.addValue(key.withPosition(2), value2);
        assertThat(mapper.valueOf(key.withPosition(1))).isNotPresent();

        mapper.addValue(key.withPosition(0), value0);
        assertThat(mapper.valueOf(key.withPosition(1))).isNotPresent();

        mapper.addValue(key.withPosition(1), value1);

        // Check all values
        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testAddNullValue() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addValue(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureKey, Object)}.
     */
    @Test
    public void testAppendValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testAppendNullValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendValue(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAddAllValuesFromStart() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        mapper.addAllValues(key.withPosition(0), Arrays.asList(value0, value1));

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAddAllValuesWithOffset() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        mapper.addAllValues(key.withPosition(1), Arrays.asList(value0, value1));

        assertThat(mapper.valueOf(key.withPosition(0))).isNotPresent();
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(2))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAllValuesFromMiddle() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAllValuesFromEnd() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} with an empty
     * collection.
     */
    @Test
    public void testAddAllValuesEmpty() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        mapper.addAllValues(key, Collections.emptyList());

        assertThat(mapper.sizeOfValue(key.withoutPosition())).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} with a collection
     * that contains {@code null}.
     */
    @Test
    public void testAddAllValuesContainsNull() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        String value0 = "Value0";

        assertThat(catchThrowable(() ->
                mapper.addAllValues(key, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureKey, List)} with a
     * {@code null} collection.
     */
    @Test
    public void testAddAllNullValues() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addAllValues(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureKey, List)} when the feature is not
     * defined yet.
     */
    @Test
    public void testAppendAllValuesFromStart() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";
        String value1 = "Value1";

        int index;

        index = mapper.appendAllValues(key, Arrays.asList(value0, value1));
        assertThat(index).isEqualTo(0);

        assertThat(mapper.valueOf(key.withPosition(0))).contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).contains(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureKey, List)} when the feature already
     * has values.
     */
    @Test
    public void testAppendAllValuesFromEnd() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureKey, List)} with an empty collection.
     */
    @Test
    public void testAppendAllValuesEmpty() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        mapper.appendAllValues(key, Collections.emptyList());

        assertThat(mapper.sizeOfValue(key)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureKey, List)} with a collection that
     * contains {@code null}.
     */
    @Test
    public void testAppendAllValuesContainsNull() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        String value0 = "Value0";

        assertThat(catchThrowable(() ->
                mapper.appendAllValues(key, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureKey, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAppendAllNullValues() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendAllValues(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveValueBefore() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveValueAfter() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testRemoveInexistingValue() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureKey)} and
     * {@link ManyValueMapper#hasAnyValue(SingleFeatureKey)}.
     */
    @Test
    public void testRemoveAllValues() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testRemoveAllInexistingValues() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeAllValues(key)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureKey, ManyFeatureKey)}.
     */
    @Test
    public void testMoveValueBefore() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureKey, ManyFeatureKey)}.
     */
    @Test
    public void testMoveValueAfter() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#moveValue(ManyFeatureKey, ManyFeatureKey)} when the value doesn't
     * exist.
     */
    @Test
    public void testMoveInexistingValue() {
        ManyFeatureKey key0 = ManyFeatureKey.of(id0, "Feature0", 0);
        ManyFeatureKey key1 = key0.withPosition(1);

        assertThat(catchThrowable(() ->
                assertThat(mapper.moveValue(key0, key1)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureKey, Object)}.
     */
    @Test
    public void testContainsValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureKey, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testContainsInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(key, "aaa")).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#containsValue(SingleFeatureKey, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testContainsNullValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(key, null)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureKey, Object)}.
     */
    @Test
    public void testIndexOfValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureKey, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testIndexOfInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(key, "aaa")).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#indexOfValue(SingleFeatureKey, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testIndexOfNullValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureKey, Object)}.
     */
    @Test
    public void testLastIndexOfValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureKey, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testLastIndexOfInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(key, "aaa")).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#lastIndexOfValue(SingleFeatureKey, Object)} when the value is
     * {@code
     * null}.
     */
    @Test
    public void testLastIndexOfNullValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureKey)}.
     */
    @Test
    public void testSizeOfValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

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
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testSizeOfInexistingValue() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfValue(key)).isNotPresent()
        )).isNull();
    }

    //endregion

    //region Single-valued references

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureKey)} and {@link
     * ReferenceMapper#referenceFor(SingleFeatureKey, Id)}.
     */
    @Test
    public void testGetSetReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");

        // Define references
        mapper.referenceFor(key, ref0);
        assertThat(mapper.referenceOf(key)).contains(ref0);

        // Replace the existing reference
        assertThat(mapper.referenceFor(key, ref1)).contains(ref0);
        assertThat(mapper.referenceOf(key)).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureKey)}.
     */
    @Test
    public void testGetInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceFor(SingleFeatureKey, Id)} with a {@code null} reference.
     */
    @Test
    public void testSetNullReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#hasReference(SingleFeatureKey)} and {@link
     * ReferenceMapper#unsetReference(SingleFeatureKey)}.
     */
    @Test
    public void testIsSetUnsetReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");

        mapper.referenceFor(key, ref0);

        assertThat(mapper.referenceOf(key)).isPresent();
        assertThat(mapper.hasReference(key)).isTrue();

        mapper.unsetReference(key);

        assertThat(mapper.referenceOf(key)).isNotPresent();
        assertThat(mapper.hasReference(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#hasReference(SingleFeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testIsSetInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasReference(key)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#unsetReference(SingleFeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testUnsetInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.unsetReference(key)
        )).isNull();
    }

    //endregion

    //region Multi-valued references

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureKey)} and {@link
     * ManyReferenceMapper#referenceFor(ManyFeatureKey, Id)}.
     */
    @Test
    public void testGetSetManyReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");
        Id ref3 = StringId.of("Ref3");

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
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testGetInexistingManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSetInexistingManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, StringId.of("Ref0"))).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSetNullManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureKey)}.
     */
    @Test
    public void testAllReferencesOf() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

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
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureKey)}.
     */
    @Test
    public void testAllReferencesOfUnorderedAdd() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

        // Add references in any order
        mapper.addReference(key.withPosition(2), ref2);
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);

        // Post-process the returned Iterable
        List<Id> actualReferences = MoreIterables.stream(mapper.<String>allReferencesOf(key)).collect(Collectors.toList());

        assertThat(actualReferences).hasSize(3);

        // Check the order of references
        assertThat(actualReferences.get(0)).isEqualTo(ref0);
        assertThat(actualReferences.get(1)).isEqualTo(ref1);
        assertThat(actualReferences.get(2)).isEqualTo(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureKey)} when the feature doesn't
     * contain
     * any element.
     */
    @Test
    public void testAllReferencesEmpty() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.allReferencesOf(key)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#hasAnyReference(SingleFeatureKey)} when the reference doesn't
     * exist.
     */
    @Test
    public void testIsSetInexistingManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyReference(key.withoutPosition())).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureKey, Id)}.
     */
    @Test
    public void testAddReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

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
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureKey, Id)}.
     */
    @Test
    public void testAnyOrderAddReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

        // Add references in any order
        mapper.addReference(key.withPosition(2), ref2);
        assertThat(mapper.referenceOf(key.withPosition(1))).isNotPresent();

        mapper.addReference(key.withPosition(0), ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).isNotPresent();

        mapper.addReference(key.withPosition(1), ref1);

        // Check all references
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAddNullReference() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addReference(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureKey, Id)}.
     */
    @Test
    public void testAppendReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");

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
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAppendNullReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendReference(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAddAllReferencesFromStart() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");

        mapper.addAllReferences(key.withPosition(0), Arrays.asList(ref0, ref1));

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAddAllReferencesWithOffset() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");

        mapper.addAllReferences(key.withPosition(1), Arrays.asList(ref0, ref1));

        assertThat(mapper.referenceOf(key.withPosition(0))).isNotPresent();
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAllReferencesFromMiddle() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");
        Id ref3 = StringId.of("Ref3");

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
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} when the feature
     * already has values.
     */
    @Test
    public void testAddAllReferencesFromEnd() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

        mapper.appendReference(key, ref0);
        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);

        mapper.addAllReferences(key.withPosition(1), Arrays.asList(ref1, ref2));

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).contains(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} with an empty
     * collection.
     */
    @Test
    public void testAddAllReferencesEmpty() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        mapper.addAllReferences(key, Collections.emptyList());

        assertThat(mapper.sizeOfReference(key.withoutPosition())).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} with a collection
     * that contains {@code null}.
     */
    @Test
    public void testAddAllReferencesContainsNull() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        Id ref0 = StringId.of("Ref0");

        assertThat(catchThrowable(() ->
                mapper.addAllReferences(key, Arrays.asList(ref0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureKey, List)} with a
     * {@code null} collection.
     */
    @Test
    public void testAddAllNullReferences() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureKey, List)} when the feature
     * is not defined yet.
     */
    @Test
    public void testAppendAllReferencesFromStart() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");

        int index;

        index = mapper.appendAllReferences(key, Arrays.asList(ref0, ref1));
        assertThat(index).isEqualTo(0);

        assertThat(mapper.referenceOf(key.withPosition(0))).contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).contains(ref1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureKey, List)} when the feature
     * already has values.
     */
    @Test
    public void testAppendAllReferencesFromEnd() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

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
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureKey, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAllReferencesEmpty() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        mapper.appendAllReferences(key, Collections.emptyList());

        assertThat(mapper.sizeOfReference(key)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureKey, List)} with a collection
     * that contains {@code null}.
     */
    @Test
    public void testAppendAllReferencesContainsNull() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");

        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(key, Arrays.asList(ref0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureKey, List)} with a
     * {@code null} collection.
     */
    @Test
    public void testAppendAllNullReferences() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(key, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        // Initialize references
        mapper.addReference(key.withPosition(0), StringId.of("aaa"));
        mapper.addReference(key.withPosition(1), StringId.of("bbb"));
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
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveReferenceBefore() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

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
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveReferenceAfter() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

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
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureKey)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemoveInexistingReference() {
        ManyFeatureKey key = ManyFeatureKey.of(id0, "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeReference(key)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureKey)} and
     * {@link ManyReferenceMapper#hasAnyReference(SingleFeatureKey)}.
     */
    @Test
    public void testRemoveAllReferences() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");

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
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureKey)} when the reference
     * doesn't
     * exist.
     */
    @Test
    public void testRemoveAllInexistingReferences() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeAllReferences(key)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureKey, ManyFeatureKey)}.
     */
    @Test
    public void testMoveReferenceBefore() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");
        Id ref3 = StringId.of("Ref3");

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
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureKey, ManyFeatureKey)}.
     */
    @Test
    public void testMoveReferenceAfter() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");
        Id ref1 = StringId.of("Ref1");
        Id ref2 = StringId.of("Ref2");
        Id ref3 = StringId.of("Ref3");

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
     * Checks the behavior of {@link ManyReferenceMapper#moveReference(ManyFeatureKey, ManyFeatureKey)} when the
     * reference doesn't exist.
     */
    @Test
    public void testMoveInexistingReference() {
        ManyFeatureKey key0 = ManyFeatureKey.of(id0, "Feature0", 0);
        ManyFeatureKey key1 = key0.withPosition(1);

        assertThat(catchThrowable(() ->
                assertThat(mapper.moveReference(key0, key1)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureKey, Id)}.
     */
    @Test
    public void testContainsReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");

        // Initialize references
        mapper.appendReference(key, StringId.of("aaa"));
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, StringId.of("zzz"));

        // Check that contains
        assertThat(mapper.containsReference(key, ref0)).isTrue();

        // Remove all references
        mapper.removeAllReferences(key);

        // Check that doesn't contain
        assertThat(mapper.containsReference(key, ref0)).isFalse();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureKey, Id)} when the reference
     * doesn't
     * exist.
     */
    @Test
    public void testContainsInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(key, StringId.of("aaa"))).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#containsReference(SingleFeatureKey, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testContainsNullReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(key, null)).isFalse()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureKey, Id)}.
     */
    @Test
    public void testIndexOfReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), StringId.of("aaa"));
        mapper.addReference(key.withPosition(2), StringId.of("bbb"));
        mapper.addReference(key.withPosition(3), ref0);
        mapper.addReference(key.withPosition(4), StringId.of("ccc"));

        // Check first index
        assertThat(mapper.indexOfReference(key, ref0)).contains(0);

        // Remove the first reference
        assertThat(mapper.removeReference(key.withPosition(0))).contains(ref0);

        // Check first index
        assertThat(mapper.indexOfReference(key, ref0)).contains(3 - 1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureKey, Id)} when the reference
     * doesn't
     * exist.
     */
    @Test
    public void testIndexOfInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(key, StringId.of("aaa"))).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#indexOfReference(SingleFeatureKey, Id)} when the reference is
     * {@code
     * null}.
     */
    @Test
    public void testIndexOfNullReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureKey, Id)}.
     */
    @Test
    public void testLastIndexOfReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        Id ref0 = StringId.of("Ref0");

        // Initialize references
        mapper.addReference(key.withPosition(0), StringId.of("aaa"));
        mapper.addReference(key.withPosition(1), ref0);
        mapper.addReference(key.withPosition(2), StringId.of("bbb"));
        mapper.addReference(key.withPosition(3), StringId.of("ccc"));
        mapper.addReference(key.withPosition(4), ref0);

        // Check last index
        assertThat(mapper.lastIndexOfReference(key, ref0)).contains(4);

        // Remove the last reference
        assertThat(mapper.removeReference(key.withPosition(4))).contains(ref0);

        // Check last index
        assertThat(mapper.lastIndexOfReference(key, ref0)).contains(1);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureKey, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testLastIndexOfInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(key, StringId.of("aaa"))).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#lastIndexOfReference(SingleFeatureKey, Id)} when the reference
     * is
     * {@code null}.
     */
    @Test
    public void testLastIndexOfNullReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(key, null)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureKey)}.
     */
    @Test
    public void testSizeOfReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        // Initialize references
        mapper.appendReference(key, StringId.of("aaa"));
        mapper.appendReference(key, StringId.of("bbb"));
        mapper.appendReference(key, StringId.of("ccc"));

        // Check the size
        assertThat(mapper.sizeOfReference(key)).contains(3);

        // Remove a reference
        mapper.removeReference(key.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfReference(key)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureKey)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSizeOfInexistingReference() {
        SingleFeatureKey key = SingleFeatureKey.of(id0, "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfReference(key)).isNotPresent()
        )).isNull();
    }

    //endregion
}
