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

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.Iterables;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test case that checks the expected behavior of {@link PersistenceMapper}s and their sub-classes.
 */
// TODO Add tests with `key.position() > size()` and an existing many value/reference
public abstract class AbstractPersistenceMapperTest extends AbstractUnitTest {

    /**
     * The {@link PersistenceMapper} used for this test case.
     */
    private PersistenceMapper mapper;

    /**
     * Creates the {@link PersistenceMapper} to test.
     *
     * @throws IOException if an I/O error occurs
     */
    @Before
    public void createMapper() throws IOException {
        mapper = context().createBackend(file());
    }

    /**
     * Closes the {@link PersistenceMapper}.
     *
     * @throws IOException if an I/O error occurs
     */
    @After
    public void closeMapper() throws IOException {
        if (mapper instanceof Closeable) {
            ((Closeable) mapper).close();
        }
    }

    //region Persistence

    /**
     * Checks the behavior of {@link PersistenceMapper#exists(Id)}.
     */
    @Test
    public void testNotExists() {
        assertThat(mapper.exists(StringId.of("Id0"))).isFalse();
    }

    /**
     * Checks the behavior of {@link PersistenceMapper#exists(Id)} after defining a {@link ClassDescriptor}.
     */
    @Test
    public void testExists() {
        Id id = StringId.of("Id0");
        mapper.metaclassFor(id, ClassDescriptor.of("Metaclass0", "Uri0"));

        assertThat(mapper.exists(id)).isTrue();
    }

    //endregion

    //region Containers

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * ContainerDescriptor)}.
     */
    @Test
    public void testGetSetSameContainer() {
        Id containerId1 = StringId.of("ContainerId0");

        ContainerDescriptor container = ContainerDescriptor.of(containerId1, "Container0");

        Id id0 = StringId.of("Id0"), id1 = StringId.of("Id1");

        // Define the containers
        mapper.containerFor(id0, container);
        assertThat(mapper.containerOf(id0)).isPresent().contains(container);

        mapper.containerFor(id1, container);
        assertThat(mapper.containerOf(id1)).isPresent().contains(container);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * ContainerDescriptor)}.
     */
    @Test
    public void testGetSetDifferentContainer() {
        Id containerId1 = StringId.of("ContainerId0");

        ContainerDescriptor container0 = ContainerDescriptor.of(containerId1, "Container0"),
                container1 = ContainerDescriptor.of(containerId1, "Container1");

        Id id0 = StringId.of("Id0"), id1 = StringId.of("Id1");

        // Define the containers
        mapper.containerFor(id0, container0);
        assertThat(mapper.containerOf(id0)).isPresent().contains(container0);

        mapper.containerFor(id1, container1);
        assertThat(mapper.containerOf(id1)).isPresent().contains(container1);

        // Replace the existing container
        mapper.containerFor(id1, container0);
        assertThat(mapper.containerOf(id1)).isPresent().contains(container0);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGetInexistingContainer() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(StringId.of("Id0"))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, ContainerDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullContainer() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.containerFor(StringId.of("Id0"), null))
        ).isInstanceOf(NullPointerException.class);
    }

    //endregion

    //region Metaclasses

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} and {@link MetaclassMapper#metaclassFor(Id,
     * ClassDescriptor)}.
     */
    @Test
    public void testGetSetSameMetaclass() {
        ClassDescriptor metaclass = ClassDescriptor.of("Metaclass0", "Uri0");

        Id id0 = StringId.of("Id0"), id1 = StringId.of("Id1");

        // Define the metaclasses
        mapper.metaclassFor(id0, metaclass);
        assertThat(mapper.metaclassOf(id0)).isPresent().contains(metaclass);

        mapper.metaclassFor(id1, metaclass);
        assertThat(mapper.metaclassOf(id1)).isPresent().contains(metaclass);
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} and {@link MetaclassMapper#metaclassFor(Id,
     * ClassDescriptor)}.
     */
    @Test
    public void testGetSetDifferentMetaclass() {
        ClassDescriptor metaclass0 = ClassDescriptor.of("Metaclass0", "Uri0"),
                metaclass1 = ClassDescriptor.of("Metaclass1", "Uri1");

        Id id0 = StringId.of("Id0"), id1 = StringId.of("Id1");

        // Define the metaclasses
        mapper.metaclassFor(id0, metaclass0);
        assertThat(mapper.metaclassOf(id0)).isPresent().contains(metaclass0);

        mapper.metaclassFor(id1, metaclass1);
        assertThat(mapper.metaclassOf(id1)).isPresent().contains(metaclass1);

        // Replace the existing metaclass
        mapper.metaclassFor(id1, metaclass0);
        assertThat(mapper.metaclassOf(id1)).isPresent().contains(metaclass0);
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} when the element doesn't exist..
     */
    public void testGetInexistingMetaclass() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.metaclassOf(StringId.of("Id0"))).isNotPresent())
        ).isNull();
    }

    //endregion

    //region Single-value attributes

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassFor(Id, ClassDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullMetaclass() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.metaclassFor(StringId.of("Id0"), null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(FeatureKey)} and {@link ValueMapper#valueFor(FeatureKey,
     * Object)}.
     */
    @Test
    public void testGetSetValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1";

        // Define values
        mapper.valueFor(key, value0);
        assertThat(mapper.valueOf(key)).isPresent().contains(value0);

        // Replace the existing value
        assertThat(mapper.valueFor(key, value1)).isPresent().contains(value0);
        assertThat(mapper.valueOf(key)).isPresent().contains(value1);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(FeatureKey)}.
     */
    @Test
    public void testGetInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(key)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(FeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(FeatureKey)} and {@link ValueMapper#unsetValue(FeatureKey)}.
     */
    @Test
    public void testIsSetUnsetValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0";

        mapper.valueFor(key, value0);

        assertThat(mapper.valueOf(key)).isPresent();
        assertThat(mapper.hasValue(key)).isTrue();

        mapper.unsetValue(key);

        assertThat(mapper.valueOf(key)).isNotPresent();
        assertThat(mapper.hasValue(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testIsSetInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasValue(key)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#unsetValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testUnsetInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                mapper.unsetValue(key))
        ).isNull();
    }

    //endregion

    //region Multi-valued attributes

    /**
     * Checks the behavior of {@link MultiValueMapper#valueOf(ManyFeatureKey)} and {@link
     * MultiValueMapper#valueFor(ManyFeatureKey, Object)}.
     */
    @Test
    public void testGetSetManyValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2", value3 = "Value3";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);

        // Replace the existing values
        assertThat(mapper.valueFor(key.withPosition(0), value2)).isPresent().contains(value0);
        assertThat(mapper.valueOf(key.withPosition(0))).isPresent().contains(value2);

        assertThat(mapper.valueFor(key.withPosition(1), value3)).isPresent().contains(value1);
        assertThat(mapper.valueOf(key.withPosition(1))).isPresent().contains(value3);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueOf(ManyFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testGetInexistingManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(key)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetInexistingManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, "Value0")).isNotPresent())
        ).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(key, null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)}.
     */
    @Test
    public void testAllValuesOf() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Add values in natural order
        mapper.appendValue(key, value0);
        mapper.appendValue(key, value1);
        mapper.appendValue(key, value2);

        // Post-process the returned Iterable
        List<String> actualValues = Iterables.stream(mapper.<String>allValuesOf(key)).collect(Collectors.toList());

        assertThat(actualValues).hasSize(3);

        // Check the order of values
        assertThat(actualValues.get(0)).isEqualTo(value0);
        assertThat(actualValues.get(1)).isEqualTo(value1);
        assertThat(actualValues.get(2)).isEqualTo(value2);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)}.
     */
    @Test
    public void testAllValuesOfUnorderedAdd() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Add values in any order
        mapper.addValue(key.withPosition(2), value2);
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);

        // Post-process the returned Iterable
        List<String> actualValues = Iterables.stream(mapper.<String>allValuesOf(key)).collect(Collectors.toList());

        assertThat(actualValues).hasSize(3);

        // Check the order of values
        assertThat(actualValues.get(0)).isEqualTo(value0);
        assertThat(actualValues.get(1)).isEqualTo(value1);
        assertThat(actualValues.get(2)).isEqualTo(value2);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)} when the feature doesn't contain any
     * element.
     */
    @Test
    public void testAllValuesEmpty() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.allValuesOf(key)).isNotNull().isEmpty())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#hasAnyValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testIsSetInexistingManyValue() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyValue(key)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAddValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Add values in natural order
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);

        // Check all values
        assertThat(mapper.valueOf(key.withPosition(0))).isPresent().contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).isPresent().contains(value1);
        assertThat(mapper.valueOf(key.withPosition(2))).isPresent().contains(value2);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAnyOrderAddValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Add values in any order
        mapper.addValue(key.withPosition(2), value2);
        assertThat(mapper.valueOf(key.withPosition(1))).isNotPresent();

        mapper.addValue(key.withPosition(0), value0);
        assertThat(mapper.valueOf(key.withPosition(1))).isNotPresent();

        mapper.addValue(key.withPosition(1), value1);

        // Check all values
        assertThat(mapper.valueOf(key.withPosition(0))).isPresent().contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).isPresent().contains(value1);
        assertThat(mapper.valueOf(key.withPosition(2))).isPresent().contains(value2);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testAddNullValue() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addValue(key, null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#appendValue(FeatureKey, Object)}.
     */
    @Test
    public void testAppendValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1";

        // Append values
        mapper.appendValue(key, value0);
        assertThat(mapper.valueOf(key.withPosition(0))).isPresent().contains(value0);

        mapper.appendValue(key, value1);
        assertThat(mapper.valueOf(key.withPosition(1))).isPresent().contains(value1);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#appendValue(FeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testAppendNullValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> mapper.appendValue(key, null))).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        // Initialize values
        mapper.addValue(key.withPosition(0), "aaa");
        mapper.addValue(key.withPosition(1), "bbb");
        assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(2);
        assertThat(mapper.hasAnyValue(key)).isTrue();

        // Remove values
        mapper.removeValue(key.withPosition(0));
        assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(1);
        assertThat(mapper.hasAnyValue(key)).isTrue();

        mapper.removeValue(key.withPosition(0));
        assertThat(mapper.sizeOfValue(key)).isNotPresent();
        assertThat(mapper.hasAnyValue(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemovedValueBefore() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);

        // Remove value, and check the removed value
        assertThat(mapper.removeValue(key.withPosition(0))).isPresent().contains(value0);

        assertThat(mapper.valueOf(key.withPosition(0))).isPresent().contains(value1);
        assertThat(mapper.valueOf(key.withPosition(1))).isPresent().contains(value2);
        assertThat(mapper.valueOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemovedValueAfter() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), value1);
        mapper.addValue(key.withPosition(2), value2);

        // Remove value, and check the removed value
        assertThat(mapper.removeValue(key.withPosition(1))).isPresent().contains(value1);

        assertThat(mapper.valueOf(key.withPosition(0))).isPresent().contains(value0);
        assertThat(mapper.valueOf(key.withPosition(1))).isPresent().contains(value2);
        assertThat(mapper.valueOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeValue(ManyFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testRemoveInexistingValue() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(key)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeAllValues(FeatureKey)} and
     * {@link MultiValueMapper#hasAnyValue(FeatureKey)}.
     */
    @Test
    public void testRemoveAllValues() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0", value1 = "Value1", value2 = "Value2";

        // Initialize values
        mapper.appendValue(key, value0);
        mapper.appendValue(key, value1);
        mapper.appendValue(key, value2);

        // Check the values
        assertThat(mapper.hasAnyValue(key)).isTrue();
        assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(3);

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
     * Checks the behavior of {@link MultiValueMapper#removeAllValues(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testRemoveAllInexistingValues() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeAllValues(key))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)}.
     */
    @Test
    public void testContainsValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

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
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)} when the value doesn't exist.
     */
    @Test
    public void testContainsInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(key, "aaa")).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)} when the value is {@code null}.
     */
    @Test
    public void testContainsNullValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(key, null)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)}.
     */
    @Test
    public void testIndexOfValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0";

        // Initialize values
        mapper.addValue(key.withPosition(0), value0);
        mapper.addValue(key.withPosition(1), "aaa");
        mapper.addValue(key.withPosition(2), "bbb");
        mapper.addValue(key.withPosition(3), value0);
        mapper.addValue(key.withPosition(4), "ccc");

        // Check first index
        assertThat(mapper.indexOfValue(key, value0)).isPresent().hasValue(0);

        // Remove the first value
        assertThat(mapper.removeValue(key.withPosition(0))).isPresent().contains(value0);

        // Check first index
        assertThat(mapper.indexOfValue(key, value0)).isPresent().hasValue(3 - 1);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)} when the value doesn't exist.
     */
    @Test
    public void testIndexOfInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(key, "aaa")).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)} when the value is {@code null}.
     */
    @Test
    public void testIndexOfNullValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(key, null)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)}.
     */
    @Test
    public void testLastIndexOfValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        String value0 = "Value0";

        // Initialize values
        mapper.addValue(key.withPosition(0), "aaa");
        mapper.addValue(key.withPosition(1), value0);
        mapper.addValue(key.withPosition(2), "bbb");
        mapper.addValue(key.withPosition(3), "ccc");
        mapper.addValue(key.withPosition(4), value0);

        // Check last index
        assertThat(mapper.lastIndexOfValue(key, value0)).isPresent().hasValue(4);

        // Remove the last value
        assertThat(mapper.removeValue(key.withPosition(4))).isPresent().contains(value0);

        // Check last index
        assertThat(mapper.lastIndexOfValue(key, value0)).isPresent().hasValue(1);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testLastIndexOfInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(key, "aaa")).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testLastIndexOfNullValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(key, null)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#sizeOfValue(FeatureKey)}.
     */
    @Test
    public void testSizeOfValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        // Initialize values
        mapper.appendValue(key, "aaa");
        mapper.appendValue(key, "bbb");
        mapper.appendValue(key, "ccc");

        // Check the size
        assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(3);

        // Remove a value
        mapper.removeValue(key.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(2);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#sizeOfValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testSizeOfInexistingValue() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfValue(key)).isNotPresent())
        ).isNull();
    }

    //endregion

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //region Single-valued references

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(FeatureKey)} and {@link
     * ReferenceMapper#referenceFor(FeatureKey, Id)}.
     */
    @Test
    public void testGetSetReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1");

        // Define references
        mapper.referenceFor(key, ref0);
        assertThat(mapper.referenceOf(key)).isPresent().contains(ref0);

        // Replace the existing reference
        assertThat(mapper.referenceFor(key, ref1)).isPresent().contains(ref0);
        assertThat(mapper.referenceOf(key)).isPresent().contains(ref1);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(FeatureKey)}.
     */
    @Test
    public void testGetInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(key)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceFor(FeatureKey, Id)} with a {@code null} reference.
     */
    @Test
    public void testSetNullReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#hasReference(FeatureKey)} and {@link
     * ReferenceMapper#unsetReference(FeatureKey)}.
     */
    @Test
    public void testIsSetUnsetReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0");

        mapper.referenceFor(key, ref0);

        assertThat(mapper.referenceOf(key)).isPresent();
        assertThat(mapper.hasReference(key)).isTrue();

        mapper.unsetReference(key);

        assertThat(mapper.referenceOf(key)).isNotPresent();
        assertThat(mapper.hasReference(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#hasReference(FeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testIsSetInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasReference(key)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#unsetReference(FeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testUnsetInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                mapper.unsetReference(key))
        ).isNull();
    }

    //endregion

    //region Multi-valued references

    /**
     * Checks the behavior of {@link MultiReferenceMapper#referenceOf(ManyFeatureKey)} and {@link
     * MultiReferenceMapper#referenceFor(ManyFeatureKey, Id)}.
     */
    @Test
    public void testGetSetManyReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2"), ref3 = StringId.of("Ref3");

        // Initialize the references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);

        // Replace the existing references
        assertThat(mapper.referenceFor(key.withPosition(0), ref2)).isPresent().contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(0))).isPresent().contains(ref2);

        assertThat(mapper.referenceFor(key.withPosition(1), ref3)).isPresent().contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(1))).isPresent().contains(ref3);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#referenceOf(ManyFeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testGetInexistingManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceOf(key)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#referenceFor(ManyFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSetInexistingManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, StringId.of("Ref0"))).isNotPresent())
        ).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#referenceFor(ManyFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSetNullManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.referenceFor(key, null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#allReferencesOf(FeatureKey)}.
     */
    @Test
    public void testAllReferencesOf() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Add references in natural order
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, ref1);
        mapper.appendReference(key, ref2);

        // Post-process the returned Iterable
        List<Id> actualReferences = Iterables.stream(mapper.<String>allReferencesOf(key)).collect(Collectors.toList());

        assertThat(actualReferences).hasSize(3);

        // Check the order of references
        assertThat(actualReferences.get(0)).isEqualTo(ref0);
        assertThat(actualReferences.get(1)).isEqualTo(ref1);
        assertThat(actualReferences.get(2)).isEqualTo(ref2);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#allReferencesOf(FeatureKey)}.
     */
    @Test
    public void testAllReferencesOfUnorderedAdd() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Add references in any order
        mapper.addReference(key.withPosition(2), ref2);
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);

        // Post-process the returned Iterable
        List<Id> actualReferences = Iterables.stream(mapper.<String>allReferencesOf(key)).collect(Collectors.toList());

        assertThat(actualReferences).hasSize(3);

        // Check the order of references
        assertThat(actualReferences.get(0)).isEqualTo(ref0);
        assertThat(actualReferences.get(1)).isEqualTo(ref1);
        assertThat(actualReferences.get(2)).isEqualTo(ref2);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#allReferencesOf(FeatureKey)} when the feature doesn't contain
     * any element.
     */
    @Test
    public void testAllReferencesEmpty() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.allReferencesOf(key)).isNotNull().isEmpty())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#hasAnyReference(FeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testIsSetInexistingManyReference() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyReference(key)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#addReference(ManyFeatureKey, Id)}.
     */
    @Test
    public void testAddReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Add references in natural order
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);

        // Check all references
        assertThat(mapper.referenceOf(key.withPosition(0))).isPresent().contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).isPresent().contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).isPresent().contains(ref2);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#addReference(ManyFeatureKey, Id)}.
     */
    @Test
    public void testAnyOrderAddReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Add references in any order
        mapper.addReference(key.withPosition(2), ref2);
        assertThat(mapper.referenceOf(key.withPosition(1))).isNotPresent();

        mapper.addReference(key.withPosition(0), ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).isNotPresent();

        mapper.addReference(key.withPosition(1), ref1);

        // Check all references
        assertThat(mapper.referenceOf(key.withPosition(0))).isPresent().contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).isPresent().contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(2))).isPresent().contains(ref2);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#addReference(ManyFeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAddNullReference() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.addReference(key, null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#appendReference(FeatureKey, Id)}.
     */
    @Test
    public void testAppendReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1");

        // Append references
        mapper.appendReference(key, ref0);
        assertThat(mapper.referenceOf(key.withPosition(0))).isPresent().contains(ref0);

        mapper.appendReference(key, ref1);
        assertThat(mapper.referenceOf(key.withPosition(1))).isPresent().contains(ref1);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#appendReference(FeatureKey, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAppendNullReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> mapper.appendReference(key, null))).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#removeReference(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        // Initialize references
        mapper.addReference(key.withPosition(0), StringId.of("aaa"));
        mapper.addReference(key.withPosition(1), StringId.of("bbb"));
        assertThat(mapper.sizeOfReference(key)).isPresent().hasValue(2);
        assertThat(mapper.hasAnyReference(key)).isTrue();

        // Remove references
        mapper.removeReference(key.withPosition(0));
        assertThat(mapper.sizeOfReference(key)).isPresent().hasValue(1);
        assertThat(mapper.hasAnyReference(key)).isTrue();

        mapper.removeReference(key.withPosition(0));
        assertThat(mapper.sizeOfReference(key)).isNotPresent();
        assertThat(mapper.hasAnyReference(key)).isFalse();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#removeReference(ManyFeatureKey)}.
     */
    @Test
    public void testRemovedReferenceBefore() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);

        // Remove reference, and check the removed reference
        assertThat(mapper.removeReference(key.withPosition(0))).isPresent().contains(ref0);

        assertThat(mapper.referenceOf(key.withPosition(0))).isPresent().contains(ref1);
        assertThat(mapper.referenceOf(key.withPosition(1))).isPresent().contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#removeReference(ManyFeatureKey)}.
     */
    @Test
    public void testRemovedReferenceAfter() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), ref1);
        mapper.addReference(key.withPosition(2), ref2);

        // Remove reference, and check the removed reference
        assertThat(mapper.removeReference(key.withPosition(1))).isPresent().contains(ref1);

        assertThat(mapper.referenceOf(key.withPosition(0))).isPresent().contains(ref0);
        assertThat(mapper.referenceOf(key.withPosition(1))).isPresent().contains(ref2);
        assertThat(mapper.referenceOf(key.withPosition(2))).isNotPresent();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#removeReference(ManyFeatureKey)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemoveInexistingReference() {
        ManyFeatureKey key = ManyFeatureKey.of(StringId.of("Id0"), "Feature0", 0);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeReference(key)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#removeAllReferences(FeatureKey)} and
     * {@link MultiReferenceMapper#hasAnyReference(FeatureKey)}.
     */
    @Test
    public void testRemoveAllReferences() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0"), ref1 = StringId.of("Ref1"), ref2 = StringId.of("Ref2");

        // Initialize references
        mapper.appendReference(key, ref0);
        mapper.appendReference(key, ref1);
        mapper.appendReference(key, ref2);

        // Check the references
        assertThat(mapper.hasAnyReference(key)).isTrue();
        assertThat(mapper.sizeOfReference(key)).isPresent().hasValue(3);

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
     * Checks the behavior of {@link MultiReferenceMapper#removeAllReferences(FeatureKey)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemoveAllInexistingReferences() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                mapper.removeAllReferences(key))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#containsReference(FeatureKey, Id)}.
     */
    @Test
    public void testContainsReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

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
     * Checks the behavior of {@link MultiReferenceMapper#containsReference(FeatureKey, Id)} when the reference doesn't
     * exist.
     */
    @Test
    public void testContainsInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(key, StringId.of("aaa"))).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#containsReference(FeatureKey, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testContainsNullReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsReference(key, null)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#indexOfReference(FeatureKey, Id)}.
     */
    @Test
    public void testIndexOfReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0");

        // Initialize references
        mapper.addReference(key.withPosition(0), ref0);
        mapper.addReference(key.withPosition(1), StringId.of("aaa"));
        mapper.addReference(key.withPosition(2), StringId.of("bbb"));
        mapper.addReference(key.withPosition(3), ref0);
        mapper.addReference(key.withPosition(4), StringId.of("ccc"));

        // Check first index
        assertThat(mapper.indexOfReference(key, ref0)).isPresent().hasValue(0);

        // Remove the first reference
        assertThat(mapper.removeReference(key.withPosition(0))).isPresent().contains(ref0);

        // Check first index
        assertThat(mapper.indexOfReference(key, ref0)).isPresent().hasValue(3 - 1);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#indexOfReference(FeatureKey, Id)} when the reference doesn't
     * exist.
     */
    @Test
    public void testIndexOfInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(key, StringId.of("aaa"))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#indexOfReference(FeatureKey, Id)} when the reference is {@code
     * null}.
     */
    @Test
    public void testIndexOfNullReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfReference(key, null)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#lastIndexOfReference(FeatureKey, Id)}.
     */
    @Test
    public void testLastIndexOfReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        Id ref0 = StringId.of("Ref0");

        // Initialize references
        mapper.addReference(key.withPosition(0), StringId.of("aaa"));
        mapper.addReference(key.withPosition(1), ref0);
        mapper.addReference(key.withPosition(2), StringId.of("bbb"));
        mapper.addReference(key.withPosition(3), StringId.of("ccc"));
        mapper.addReference(key.withPosition(4), ref0);

        // Check last index
        assertThat(mapper.lastIndexOfReference(key, ref0)).isPresent().hasValue(4);

        // Remove the last reference
        assertThat(mapper.removeReference(key.withPosition(4))).isPresent().contains(ref0);

        // Check last index
        assertThat(mapper.lastIndexOfReference(key, ref0)).isPresent().hasValue(1);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#lastIndexOfReference(FeatureKey, Id)} when the reference
     * doesn't exist.
     */
    @Test
    public void testLastIndexOfInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(key, StringId.of("aaa"))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#lastIndexOfReference(FeatureKey, Id)} when the reference is
     * {@code null}.
     */
    @Test
    public void testLastIndexOfNullReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfReference(key, null)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#sizeOfReference(FeatureKey)}.
     */
    @Test
    public void testSizeOfReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        // Initialize references
        mapper.appendReference(key, StringId.of("aaa"));
        mapper.appendReference(key, StringId.of("bbb"));
        mapper.appendReference(key, StringId.of("ccc"));

        // Check the size
        assertThat(mapper.sizeOfReference(key)).isPresent().hasValue(3);

        // Remove a reference
        mapper.removeReference(key.withPosition(1));

        // Check the size
        assertThat(mapper.sizeOfReference(key)).isPresent().hasValue(2);
    }

    /**
     * Checks the behavior of {@link MultiReferenceMapper#sizeOfReference(FeatureKey)} when the reference doesn't exist.
     */
    @Test
    public void testSizeOfInexistingReference() {
        FeatureKey key = FeatureKey.of(StringId.of("Id0"), "Feature0");

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfReference(key)).isNotPresent())
        ).isNull();
    }

    //endregion
}
