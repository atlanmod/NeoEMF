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
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
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
     * The minimum integer for {@link #randomInt(int, int)}.
     */
    private static final int MIN_BOUND = 10;

    /**
     * The maximum integer for {@link #randomInt(int, int)}.
     */
    private static final int MAX_BOUND = 20;

    /**
     * The {@link PersistenceMapper} used for this test case.
     */
    private PersistenceMapper mapper;

    /**
     * Returns a random {@link Id}.
     *
     * @return a random {@link Id}
     */
    private static Id randomId() {
        return StringId.generate();
    }

    /**
     * Returns a random {@link String}.
     *
     * @return a random {@link String}
     */
    private static String randomString() {
        return UUID.randomUUID().toString();
    }

    /**
     * Returns a pseudo-random {@code int} value between {@link #MIN_BOUND} and {@link #MAX_BOUND} (exclusive).
     *
     * @return a pseudo-random {@code int}
     */
    private static int randomInt() {
        return randomInt(MIN_BOUND, MAX_BOUND);
    }

    /**
     * Returns a pseudo-random {@code int} value between {@code origin} and {@code bound} (exclusive).
     *
     * @param origin the least value returned
     * @param bound  the upper bound (exclusive)
     *
     * @return a pseudo-random {@code int}
     */
    private static int randomInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
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
     * Checks the behavior of {@link PersistenceMapper#exists(Id)}.
     */
    @Test
    public void testNotExists() {
        assertThat(mapper.exists(randomId())).isFalse();
    }

    /**
     * Checks the behavior of {@link PersistenceMapper#exists(Id)} after defining a {@link MetaclassDescriptor}.
     */
    @Test
    public void testExists() {
        Id id = randomId();
        mapper.metaclassFor(id, MetaclassDescriptor.of(randomString(), randomString()));

        assertThat(mapper.exists(id)).isTrue();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * ContainerDescriptor)}.
     */
    @Test
    public void testGetSetSameContainer() {
        List<Id> all = new ArrayList<>();

        ContainerDescriptor container = ContainerDescriptor.of(randomId(), randomString());

        for (int nb = 0; nb < randomInt(); nb++) {
            all.add(randomId());
        }

        all.forEach(id -> mapper.containerFor(id, container));

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

        for (int nb = 0; nb < randomInt(); nb++) {
            allContainers.put(randomId(), ContainerDescriptor.of(randomId(), randomString()));
        }

        allContainers.forEach((id, container) -> mapper.containerFor(id, container));

        allContainers = shuffle(allContainers);

        allContainers.forEach((id, container) -> assertThat(mapper.containerOf(id)).isPresent().hasValue(container));
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGetInexistingContainer() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(randomId())).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, ContainerDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullContainer() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.containerFor(randomId(), null))
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

        for (int nb = 0; nb < randomInt(); nb++) {
            all.add(randomId());
        }

        all.forEach(id -> mapper.metaclassFor(id, metaclass));

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

        for (int nb = 0; nb < randomInt(); nb++) {
            allMetaclasses.put(randomId(), MetaclassDescriptor.of(randomString(), randomString()));
        }

        allMetaclasses.forEach((id, metaclass) -> mapper.metaclassFor(id, metaclass));

        allMetaclasses = shuffle(allMetaclasses);

        allMetaclasses.forEach((id, metaclass) -> assertThat(mapper.metaclassOf(id)).isPresent().hasValue(metaclass));
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassOf(Id)} when the element doesn't exist..
     */
    public void testGetInexistingMetaclass() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.metaclassOf(randomId())).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MetaclassMapper#metaclassFor(Id, MetaclassDescriptor)} with a {@code null} value.
     */
    @Test
    public void testSetNullMetaclass() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                mapper.metaclassFor(randomId(), null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(FeatureKey)} and {@link ValueMapper#valueFor(FeatureKey,
     * Object)}.
     */
    @Test
    public void testGetSetValue() {
        Map<FeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            allValues.put(FeatureKey.of(randomId(), randomString()), randomString());
        }

        allValues.forEach((key, value) -> mapper.valueFor(key, value));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(FeatureKey.of(randomId(), randomString()))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(FeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullValue() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(FeatureKey.of(randomId(), randomString()), null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#hasValue(FeatureKey)} and {@link ValueMapper#unsetValue(FeatureKey)}.
     */
    @Test
    public void testIsSetUnsetValue() {
        Map<FeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            allValues.put(FeatureKey.of(randomId(), randomString()), randomString());
        }

        allValues.forEach((key, value) -> mapper.valueFor(key, value));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.hasValue(FeatureKey.of(randomId(), randomString()))).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#unsetValue(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testUnsetInexistingValue() {
        assertThat(catchThrowable(() ->
                mapper.unsetValue(FeatureKey.of(randomId(), randomString())))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueOf(ManyFeatureKey)} and {@link
     * MultiValueMapper#valueFor(ManyFeatureKey, Object)}.
     */
    @Test
    public void testGetSetManyValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < randomInt(); position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.addValue(key, randomString()));
        allValues.forEach((key, value) -> mapper.valueFor(key, value));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueOf(ManyFeatureKey.of(randomId(), randomString(), randomInt()))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetInexistingManyValue() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(ManyFeatureKey.of(randomId(), randomString(), randomInt()), randomString())).isNotPresent())
        ).isInstanceOf(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#valueFor(ManyFeatureKey, Object)} with a {@code null} value.
     */
    @Test
    public void testSetNullManyValue() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() ->
                assertThat(mapper.valueFor(ManyFeatureKey.of(randomId(), randomString(), randomInt()), null)).isNotPresent())
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)}.
     */
    @Test
    public void testAllValuesOf() {
        Map<FeatureKey, List<String>> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < randomInt(); position++) {
                String value = randomString();

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

            IntStream.range(0, expectedValues.size())
                    .forEach(i -> assertThat(actualValues.get(i)).isEqualTo(expectedValues.get(i)));
        });
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#allValuesOf(FeatureKey)} when the feature doesn't contain any
     * element.
     */
    @Test
    public void testAllValuesEmpty() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.allValuesOf(FeatureKey.of(randomId(), randomString()))).isNotNull().isEmpty())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#hasAnyValue(FeatureKey)} and {@link
     * MultiValueMapper#unsetAllValues(FeatureKey)}.
     */
    @Test
    public void testIsSetUnsetManyValue() {
        Map<FeatureKey, Integer> allFeatures = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();
            allFeatures.put(key, size);

            for (int position = 0; position < size; position++) {
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
        assertThat(catchThrowable(() ->
                assertThat(mapper.hasAnyValue(ManyFeatureKey.of(randomId(), randomString(), randomInt()))).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#unsetAllValues(FeatureKey)} when the value doesn't exist.
     */
    @Test
    public void testUnsetInexistingManyValue() {
        assertThat(catchThrowable(() ->
                mapper.unsetAllValues(FeatureKey.of(randomId(), randomString())))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#addValue(ManyFeatureKey, Object)}.
     */
    @Test
    public void testAddValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.addValue(key, value));

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

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();

            allSizes.put(key, size);

            List<Integer> positions = IntStream.range(0, size).boxed().collect(Collectors.toList());
            Collections.shuffle(positions);

            for (Integer position : positions) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.addValue(key, value));

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
        ManyFeatureKey key = ManyFeatureKey.of(randomId(), randomString(), randomInt());

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

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.appendValue(key.withoutPosition(), value));

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
        FeatureKey key = FeatureKey.of(randomId(), randomString());

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

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.addValue(key, value));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(ManyFeatureKey.of(randomId(), randomString(), randomInt()))).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#removeAllValues(FeatureKey)}.
     */
    @Test
    public void testRemoveAllValues() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.addValue(key, value));

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
        assertThat(catchThrowable(() ->
                mapper.removeAllValues(FeatureKey.of(randomId(), randomString())))
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)}.
     */
    @Test
    public void testContainsValue() {
        Map<ManyFeatureKey, String> allValues = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < randomInt(); position++) {
                allValues.put(key.withPosition(position), randomString());
            }
        }

        allValues.forEach((key, value) -> mapper.addValue(key, value));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(FeatureKey.of(randomId(), randomString()), randomString())).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#containsValue(FeatureKey, Object)} when the value is {@code null}.
     */
    @Test
    public void testContainsNullValue() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containsValue(FeatureKey.of(randomId(), randomString()), null)).isFalse())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)}.
     */
    @Test
    public void testIndexOfValue() {
        int size = randomInt();

        int nbValues = size / 4;

        List<String> values = IntStream.range(0, nbValues)
                .mapToObj(i -> randomString())
                .collect(Collectors.toList());

        Map<FeatureKey, Map<String, Integer>> allIndexOf = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < size; position++) {
                String value = values.get(randomInt(0, nbValues));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(FeatureKey.of(randomId(), randomString()), randomString())).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#indexOfValue(FeatureKey, Object)} when the value is {@code null}.
     */
    @Test
    public void testIndexOfNullValue() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.indexOfValue(FeatureKey.of(randomId(), randomString()), null)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)}.
     */
    @Test
    public void testLastIndexOfValue() {
        int size = randomInt();

        int nbValues = size / 4;

        List<String> values = IntStream.range(0, nbValues)
                .mapToObj(i -> randomString())
                .collect(Collectors.toList());

        Map<FeatureKey, Map<String, Integer>> allIndexOf = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());

            for (int position = 0; position < size; position++) {
                String value = values.get(randomInt(0, nbValues));

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
        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(FeatureKey.of(randomId(), randomString()), randomString())).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#lastIndexOfValue(FeatureKey, Object)} when the value is {@code
     * null}.
     */
    @Test
    public void testLastIndexOfNullValue() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.lastIndexOfValue(FeatureKey.of(randomId(), randomString()), null)).isNotPresent())
        ).isNull();
    }

    /**
     * Checks the behavior of {@link MultiValueMapper#sizeOfValue(FeatureKey)}.
     */
    @Test
    public void testSizeOfValue() {
        Map<FeatureKey, Integer> allSizes = new LinkedHashMap<>();

        for (int nb = 0; nb < randomInt(); nb++) {
            FeatureKey key = FeatureKey.of(randomId(), randomString());
            int size = randomInt();

            allSizes.put(key, size);

            for (int position = 0; position < size; position++) {
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
        assertThat(catchThrowable(() ->
                assertThat(mapper.sizeOfValue(FeatureKey.of(randomId(), randomString()))).isNotPresent())
        ).isNull();
    }
}
