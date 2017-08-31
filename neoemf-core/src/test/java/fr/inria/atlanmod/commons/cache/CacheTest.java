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

package fr.inria.atlanmod.commons.cache;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link Cache}.
 */
public class CacheTest extends AbstractTest {

    private Cache<Integer, String> cache;

    @Before
    public void setUp() {
        cache = CacheBuilder.builder()
                .weakKeys()
                .softValues()
                .maximumSize(5)
                .recordStats()
                .build();

        assertThat(cache.size()).isEqualTo(0);
    }

    @After
    public void tearDown() {
        cache.invalidateAll();
        cache.cleanUp();
    }

    @Test
    public void createCacheInvalidSize() {
        assertThat(catchThrowable(() -> CacheBuilder.builder().maximumSize(-1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testGetPut() {
        String value0 = "Value0";

        cache.put(0, value0);

        assertThat(cache.get(0)).isEqualTo(value0);
        assertThat(cache.size()).isEqualTo(1);
    }

    @Test
    public void testGetAbsent() {
        assertThat(cache.get(0)).isNull();
    }

    @Test
    public void testGetWithFunction() {
        String prefix = "Value";
        String value0 = prefix + '0';

        assertThat(cache.get(0)).isNull();
        assertThat(cache.size()).isEqualTo(0);

        assertThat(cache.get(0, key -> prefix + key)).isEqualTo(value0);
        assertThat(cache.size()).isEqualTo(1);

        assertThat(cache.get(0)).isEqualTo(value0);
    }

    @Test
    public void testGetWithNullFunction() {
        assertThat(cache.get(0)).isNull();

        assertThat(cache.get(0, key -> null)).isNull();
        assertThat(cache.get(0)).isNull();

        // Do nothing
        cache.refresh(0);
        assertThat(cache.get(0)).isNull();
    }

    @Test
    public void testGetPutAllKeys() {
        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        Map<Integer, String> original = new HashMap<>();
        original.put(0, value0);
        original.put(1, value1);
        original.put(2, value2);

        cache.putAll(original);

        assertThat(cache.size()).isEqualTo(3);

        assertThat(cache.get(0)).isEqualTo(value0);
        assertThat(cache.get(1)).isEqualTo(value1);
        assertThat(cache.get(2)).isEqualTo(value2);

        Map<Integer, String> result = cache.getAll(IntStream.of(0, 2).boxed().collect(Collectors.toList()));

        assertThat(result.get(0)).isEqualTo(value0);
        assertThat(result.get(2)).isEqualTo(value2);
    }

    @Test
    public void testInvalidate() {
        String value0 = "Value0";
        String value1 = "Value1";

        cache.put(0, value0);
        cache.put(1, value1);

        assertThat(cache.get(0)).isEqualTo(value0);
        assertThat(cache.get(1)).isEqualTo(value1);

        assertThat(cache.size()).isEqualTo(2);

        cache.invalidate(0);

        assertThat(cache.get(0)).isNull();
        assertThat(cache.get(1)).isEqualTo(value1);

        assertThat(cache.size()).isEqualTo(1);
    }

    @Test
    public void testInvalidateAllKeys() {
        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        cache.put(0, value0);
        cache.put(1, value1);
        cache.put(2, value2);

        assertThat(cache.size()).isEqualTo(3);

        assertThat(cache.get(0)).isEqualTo(value0);
        assertThat(cache.get(1)).isEqualTo(value1);
        assertThat(cache.get(2)).isEqualTo(value2);

        cache.invalidateAll(IntStream.of(0, 2).boxed().collect(Collectors.toList()));

        assertThat(cache.size()).isEqualTo(1);

        assertThat(cache.get(0)).isNull();
        assertThat(cache.get(1)).isEqualTo(value1);
        assertThat(cache.get(2)).isNull();
    }

    @Test
    public void testInvalidateAll() {
        String value0 = "Value0";
        String value1 = "Value1";

        cache.put(0, value0);
        cache.put(1, value1);

        assertThat(cache.size()).isEqualTo(2);

        assertThat(cache.get(0)).isEqualTo(value0);
        assertThat(cache.get(1)).isEqualTo(value1);

        cache.invalidateAll();

        assertThat(cache.size()).isEqualTo(0);

        assertThat(cache.get(0)).isNull();
        assertThat(cache.get(1)).isNull();
    }

    @Test
    public void asMap() throws Exception {
        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";

        Map<Integer, String> original = new HashMap<>();
        original.put(0, value0);
        original.put(1, value1);
        original.put(2, value2);

        cache.putAll(original);

        Map<Integer, String> result = cache.asMap();

        original.forEach((key, value) -> assertThat(result.get(key)).isEqualTo(value));
    }

    @Test
    public void stats() throws Exception {
        CacheStats stats = cache.stats();
        assertThat(stats).isNotNull();
    }
}