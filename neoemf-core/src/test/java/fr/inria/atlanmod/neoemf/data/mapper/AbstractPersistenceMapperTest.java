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
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test case that checks the expected behavior of {@link PersistenceMapper}s and their sub-classes.
 */
public abstract class AbstractPersistenceMapperTest extends AbstractUnitTest {

    /**
     * The minimum size of a multi-valued feature.
     */
    private static final int MIN_SIZE = 10;

    /**
     * The maximum size of a multi-valued feature.
     */
    private static final int MAX_SIZE = 20;

    /**
     * The minimum number of inserted {@link Id} in a {@link PersistenceMapper}.
     */
    private static final int MIN_BOUND = 10;

    /**
     * The maximum number of inserted {@link Id} in a {@link PersistenceMapper}.
     */
    private static final int MAX_BOUND = 20;

    /**
     * The {@link PersistenceMapper} used for this test case.
     */
    private PersistenceMapper mapper;

    /**
     * Creates a new random {@link Id}.
     *
     * @return a new {@link Id}
     */
    private static Id randomId() {
        return StringId.generate();
    }

    /**
     * Creates a new random {@link String}.
     *
     * @return a new {@link String}
     */
    private static String randomString() {
        return UUID.randomUUID().toString();
    }

    /**
     * Creates a new random {@code int} between {@link #MIN_SIZE} and {@link #MAX_SIZE}.
     *
     * @return a new integer
     */
    private static int randomSize() {
        return generate(MIN_SIZE, MAX_SIZE);
    }

    /**
     * Creates a new random {@code int} between {@link #MIN_BOUND} and {@link #MAX_BOUND}.
     *
     * @return a new integer
     */
    private static int randomBound() {
        return generate(MIN_BOUND, MAX_BOUND);
    }

    /**
     * Creates a new random {@code int} between {@link #MIN_BOUND} and {@code max}.
     *
     * @param min the minimum bound
     * @param max the maximum bound
     *
     * @return a new integer
     */
    private static int generate(int min, int max) {
        int value = new Random().nextInt(max);
        value += value < min ? min : 0;
        return value;
    }

    /**
     * Shuffles the given {@code map}.
     *
     * @param map the map to shuffle
     * @param <K> the type of keys maintained by the map
     * @param <V> the type of mapped values
     *
     * @return a new shuffle {@link Map}
     */
    private static <K, V> Map<K, V> shuffle(Map<K, V> map) {
        List<K> list = new ArrayList<>(map.keySet());
        Collections.shuffle(list);

        Map<K, V> shuffleMap = new LinkedHashMap<>();
        list.forEach(k -> shuffleMap.put(k, map.get(k)));

        return shuffleMap;
    }

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

    /**
     * Checks the behavior of {@link PersistenceMapper#has(Id)} and {@link PersistenceMapper#create(Id)}.
     */
    @Test
    public void testHasAfterCreate() {
        Id id = randomId();
        mapper.create(id);
        assertThat(mapper.has(id)).isTrue();
    }

    /**
     * Checks the behavior of {@link PersistenceMapper#has(Id)} and {@link PersistenceMapper#create(Id)} after defining
     * the {@link MetaclassDescriptor}.
     */
    @Test
    public void testHasAfterMetaclassFor() {
        Id id = randomId();
        mapper.create(id);
        mapper.metaclassFor(id, MetaclassDescriptor.of(randomString(), randomString()));
        assertThat(mapper.has(id)).isTrue();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * ContainerDescriptor)}.
     */
    @Test
    public void testGetSetSameContainer() {
        List<Id> all = new ArrayList<>();

        Id root = randomId();
        if (!mapper.has(root)) {
            mapper.create(root);
        }

        ContainerDescriptor container = ContainerDescriptor.of(root, randomString());

        for (int nb = 0; nb < randomBound(); nb++) {
            all.add(randomId());
        }

        all.forEach(id -> {
            if (!mapper.has(id)) {
                mapper.create(id);
            }
            mapper.containerFor(id, container);
        });

        Collections.shuffle(all);

        all.forEach(id -> assertThat(mapper.containerOf(id)).isPresent().hasValue(container));
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * ContainerDescriptor)}.
     */
    @Test
    public void testGetSetDifferentContainer() {
        Map<Id, ContainerDescriptor> allContainers = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            Id root = randomId();
            if (!mapper.has(root)) {
                mapper.create(root);
            }
            allContainers.put(randomId(), ContainerDescriptor.of(root, randomString()));
        }

        allContainers.forEach((id, container) -> {
            if (!mapper.has(id)) {
                mapper.create(id);
            }
            mapper.containerFor(id, container);
        });

        allContainers = shuffle(allContainers);

        allContainers.forEach((id, container) -> assertThat(mapper.containerOf(id)).isPresent().hasValue(container));
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGetInexistingContainer() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(id)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, ContainerDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullContainer() {
        Id id = randomId();
        mapper.create(id);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.containerFor(id, null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} and {@link MetaclassMapper#metaclassFor(Id,
     * MetaclassDescriptor)}.
     */
    @Test
    public void testGetSetSameMetaclass() {
        List<Id> all = new ArrayList<>();

        MetaclassDescriptor metaclass = MetaclassDescriptor.of(randomString(), randomString());
        for (int nb = 0; nb < randomBound(); nb++) {
            all.add(randomId());
        }

        all.forEach(id -> {
            if (!mapper.has(id)) {
                mapper.create(id);
            }
            mapper.metaclassFor(id, metaclass);
        });

        Collections.shuffle(all);

        all.forEach(id -> assertThat(mapper.metaclassOf(id)).isPresent().hasValue(metaclass));
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} and {@link MetaclassMapper#metaclassFor(Id,
     * MetaclassDescriptor)}.
     */
    @Test
    public void testGetSetDifferentMetaclass() {
        Map<Id, MetaclassDescriptor> allMetaclasses = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            allMetaclasses.put(randomId(), MetaclassDescriptor.of(randomString(), randomString()));
        }

        allMetaclasses.forEach((id, metaclass) -> {
            if (!mapper.has(id)) {
                mapper.create(id);
            }
            mapper.metaclassFor(id, metaclass);
        });

        allMetaclasses = shuffle(allMetaclasses);

        allMetaclasses.forEach((id, metaclass) -> assertThat(mapper.metaclassOf(id)).isPresent().hasValue(metaclass));
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} when the element doesn't exist..
     */
    public void testGetInexistingMetaclass() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.metaclassOf(id)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassFor(Id, MetaclassDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullMetaclass() {
        Id id = randomId();
        mapper.create(id);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.metaclassFor(id, null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(FeatureKey)} and {@link ValueMapper#valueFor(FeatureKey,
     * Object)}.
     */
    @Test
    public void testGetSetValue() {
        Map<FeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            allValues.put(FeatureKey.of(randomId(), randomString()), randomString());
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.valueFor(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            assertThat(mapper.valueOf(key)).isPresent().hasValue(value);
            assertThat(mapper.valueFor(key, randomString())).isPresent().hasValue(value);
        });
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(FeatureKey)}.
     */
    @Test
    public void testGetInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(FeatureKey.of(id, randomString()))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(FeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullValue() {
        Id id = randomId();
        mapper.create(id);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(FeatureKey.of(id, randomString()), null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(FeatureKey)} and {@link ValueMapper#unsetValue(FeatureKey)}.
     */
    @Test
    public void testIsSetUnsetValue() {
        Map<FeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            allValues.put(FeatureKey.of(randomId(), randomString()), randomString());
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.valueFor(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            assertThat(mapper.valueOf(key)).isPresent();
            assertThat(mapper.hasValue(key)).isTrue();

            mapper.unsetValue(key);

            assertThat(mapper.valueOf(key)).isNotPresent();
            assertThat(mapper.hasValue(key)).isFalse();
        });
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testIsSetInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasValue(FeatureKey.of(id, randomString()))).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#unsetValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testUnsetInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                mapper.unsetValue(FeatureKey.of(id, randomString())))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueOf(ManyFeatureKey)} and {@link
     * MultiValueMapper#valueFor(ManyFeatureKey, Object)}.
     */
    @Test
    public void testGetSetManyValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < randomSize(); position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.valueFor(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            assertThat(mapper.valueOf(key)).isPresent().hasValue(value);
            assertThat(mapper.valueFor(key, randomString())).isPresent().hasValue(value);
        });
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueOf(ManyFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testGetInexistingManyValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(ManyFeatureKey.of(id, randomString(), 15))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullManyValue() {
        Id id = randomId();
        mapper.create(id);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(ManyFeatureKey.of(id, randomString(), 15), null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)}.
     */
    @Test
    public void testAllValuesOf() {
        Map<FeatureKey, List<String>> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < randomSize(); position++) {
                String value = randomString();

                if (!mapper.has(key.id())) {
                    mapper.create(key.id());
                }
                mapper.addValue(key.withPosition(position), value);

                List<String> values = allValues.getOrDefault(key, new ArrayList<>());
                values.add(position, value);
                allValues.put(key, values);
            }
        }

        // FIXME 'assertThat(Iterable<>).containsExactly(Iterable<>)' seems buggy: it doesn't return the real size
        allValues.forEach((key, expectedValues) -> {
            List<String> actualValues = StreamSupport.stream(mapper.<String>allValuesOf(key).spliterator(), false)
                    .collect(Collectors.toList());

            assertThat(actualValues).hasSameSizeAs(expectedValues);

            IntStream.range(0, expectedValues.size()).forEach(i -> assertThat(actualValues.get(i)).isEqualTo(expectedValues.get(i)));
        });
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)} when the feature doesn't contain any
     * element.
     */
    @Test
    public void testAllValuesEmpty() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.allValuesOf(FeatureKey.of(id, randomString()))).isNotNull().isEmpty())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#hasAnyValue(FeatureKey)} and {@link
     * MultiValueMapper#unsetAllValues(FeatureKey)}.
     */
    @Test
    public void testIsSetUnsetManyValue() {
        Map<FeatureKey, Integer> allFeatures = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();
            allFeatures.put(key, size);

            for (int position = 0; position < size; position++) {
                if (!mapper.has(key.id())) {
                    mapper.create(key.id());
                }
                mapper.addValue(key.withPosition(position), randomString());
            }
        }

        allFeatures = shuffle(allFeatures);

        allFeatures.forEach((key, size) -> {
            assertThat(mapper.hasAnyValue(key)).isTrue();
            assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(size);

            mapper.unsetAllValues(key);

            assertThat(mapper.valueOf(key)).isNotPresent();
            assertThat(mapper.hasAnyValue(key)).isFalse();
            assertThat(mapper.sizeOfValue(key)).isNotPresent();
        });
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#hasAnyValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testIsSetInexistingManyValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyValue(ManyFeatureKey.of(id, randomString(), 15))).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#unsetAllValues(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testUnsetInexistingManyValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                mapper.unsetAllValues(FeatureKey.of(id, randomString())))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAddValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.addValue(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            assertThat(mapper.valueOf(key)).isPresent().hasValue(value);
            assertThat(mapper.hasAnyValue(key.withoutPosition())).isTrue();
        });

        allSizes.forEach((key, size) -> assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(size));
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAnyOrderAddValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();

            allSizes.put(key, size);

            List<Integer> positions = IntStream.range(0, size).boxed().collect(Collectors.toList());
            Collections.shuffle(positions);

            for (Integer position : positions) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.addValue(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            assertThat(mapper.valueOf(key)).isPresent().hasValue(value);
            assertThat(mapper.hasAnyValue(key.withoutPosition())).isTrue();
        });

        allSizes.forEach((key, size) -> assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(size));
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testAddNullValue() {
        Id id = randomId();
        mapper.create(id);

        ManyFeatureKey key = ManyFeatureKey.of(id, randomString(), 17);

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
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.appendValue(key.withoutPosition(), value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            assertThat(mapper.valueOf(key)).isPresent().hasValue(value);
            assertThat(mapper.hasAnyValue(key.withoutPosition())).isTrue();
        });

        allSizes.forEach((key, size) -> assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(size));
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#appendValue(FeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testAppendNullValue() {
        Id id = randomId();
        mapper.create(id);

        FeatureKey key = FeatureKey.of(id, randomString());

        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> mapper.appendValue(key, null))).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeValue(ManyFeatureKey)}.
     */
    @Test
    public void testRemoveValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.addValue(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            int size = allSizes.get(key.withoutPosition());

            assertThat(mapper.hasAnyValue(key.withoutPosition())).isTrue();
            assertThat(mapper.sizeOfValue(key.withoutPosition())).isPresent().hasValue(size);

            // TODO Test removed value
            mapper.removeValue(key);

            size = --size;
            allSizes.put(key.withoutPosition(), size);

            if (size <= 0) {
                assertThat(mapper.sizeOfValue(key.withoutPosition())).isNotPresent();
                assertThat(mapper.hasAnyValue(key.withoutPosition())).isFalse();
            }
            else {
                assertThat(mapper.sizeOfValue(key.withoutPosition())).isPresent().hasValue(size);
            }
        });
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeValue(ManyFeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testRemoveInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(ManyFeatureKey.of(id, randomString(), 15))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeAllValues(FeatureKey)}.
     */
    @Test
    public void testRemoveAllValues() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.addValue(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> {
            int size = allSizes.get(key.withoutPosition());

            if (size > 0) {
                assertThat(mapper.hasAnyValue(key.withoutPosition())).isTrue();
                assertThat(mapper.sizeOfValue(key.withoutPosition())).isPresent().hasValue(size);

                mapper.removeAllValues(key.withoutPosition());

                allSizes.put(key.withoutPosition(), 0);
            }

            assertThat(mapper.sizeOfValue(key.withoutPosition())).isNotPresent();
            assertThat(mapper.hasAnyValue(key.withoutPosition())).isFalse();
        });
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeAllValues(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testRemoveAllInexistingValues() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() -> mapper.removeAllValues(FeatureKey.of(id, randomString())))).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)}.
     */
    @Test
    public void testContainsValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < randomSize(); position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> {
            if (!mapper.has(key.id())) {
                mapper.create(key.id());
            }
            mapper.addValue(key, value);
        });

        allValues = shuffle(allValues);

        allValues.forEach((key, value) -> assertThat(mapper.containsValue(key.withoutPosition(), value)).isTrue());

        allValues.entrySet().stream()
                .map(e -> e.getKey().withoutPosition())
                .distinct()
                .forEach(k -> mapper.removeAllValues(k));

        allValues.forEach((key, value) -> assertThat(mapper.containsValue(key.withoutPosition(), value)).isFalse());
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)} when the value doesn't exist.
     */
    @Test
    public void testContainsInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(FeatureKey.of(id, randomString()), randomString())).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)}.
     */
    @Test
    public void testIndexOfValue() {
        int size = randomSize();

        int nbValues = size / 4;

        List<String> values = IntStream.range(0, nbValues)
                .mapToObj(i -> randomString())
                .collect(Collectors.toList());

        Map<FeatureKey, Map<String, Integer>> allIndexOf = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < size; position++) {
                String value = values.get(generate(0, nbValues));

                if (!mapper.has(key.id())) {
                    mapper.create(key.id());
                }
                mapper.addValue(key.withPosition(position), value);

                Map<String, Integer> indices = allIndexOf.getOrDefault(key, new LinkedHashMap<>());
                int firstIndex = indices.containsKey(value) ? Math.min(indices.get(value), position) : position;
                indices.put(value, firstIndex);
                allIndexOf.put(key, indices);
            }
        }

        allIndexOf = shuffle(allIndexOf);

        allIndexOf.forEach((key, indices) ->
                indices.forEach((value, firstIndex) ->
                        assertThat(mapper.indexOfValue(key, value)).isPresent().hasValue(firstIndex)));
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)} when the value doesn't exist.
     */
    @Test
    public void testIndexOfInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() -> assertThat(mapper.indexOfValue(FeatureKey.of(id, randomString()), randomString())).isNotPresent())).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)}.
     */
    @Test
    public void testLastIndexOfValue() {
        int size = randomSize();

        int nbValues = size / 4;

        List<String> values = IntStream.range(0, nbValues)
                .mapToObj(i -> randomString())
                .collect(Collectors.toList());

        Map<FeatureKey, Map<String, Integer>> allIndexOf = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < size; position++) {
                String value = values.get(generate(0, nbValues));

                if (!mapper.has(key.id())) {
                    mapper.create(key.id());
                }
                mapper.addValue(key.withPosition(position), value);

                Map<String, Integer> indices = allIndexOf.getOrDefault(key, new LinkedHashMap<>());
                int lastIndex = indices.containsKey(value) ? Math.max(indices.get(value), position) : position;
                indices.put(value, lastIndex);
                allIndexOf.put(key, indices);
            }
        }

        allIndexOf = shuffle(allIndexOf);

        allIndexOf.forEach((key, indices) ->
                indices.forEach((value, lastIndex) ->
                        assertThat(mapper.lastIndexOfValue(key, value)).isPresent().hasValue(lastIndex)));
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)} when the value doesn't
     * exist.
     */
    @Test
    public void testLastIndexOfInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(FeatureKey.of(id, randomString()), randomString())).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#sizeOfValue(FeatureKey)}.
     */
    @Test
    public void testSizeOfValue() {
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomBound(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomSize();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                if (!mapper.has(key.id())) {
                    mapper.create(key.id());
                }
                mapper.addValue(key.withPosition(position), randomString());
            }
        }

        allSizes = shuffle(allSizes);

        allSizes.forEach((key, size) ->
                assertThat(mapper.sizeOfValue(key)).isPresent().hasValue(size));
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#sizeOfValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testSizeOfInexistingValue() {
        Id id = randomId();
        mapper.create(id);

        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfValue(FeatureKey.of(id, randomString()))).isNotPresent())
        ).isNull();
    }
}
