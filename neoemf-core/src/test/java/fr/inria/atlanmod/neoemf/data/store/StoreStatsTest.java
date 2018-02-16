/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link StoreStats}.
 */
@ParametersAreNonnullByDefault
class StoreStatsTest extends AbstractTest {

    @Test
    void testMethodCalls() {
        Map<String, AtomicLong> rawMap = new HashMap<>();
        rawMap.put("AAA", new AtomicLong(5));
        rawMap.put("BBB", new AtomicLong(2));
        rawMap.put("CCC", new AtomicLong(10));

        StoreStats stats = new StoreStats(rawMap);

        // Map is now ordered (descending order)
        Iterator<Map.Entry<String, Long>> resultSet = stats.methodCalls().entrySet().iterator();

        Map.Entry<String, Long> next = resultSet.next();
        assertThat(next.getKey()).isEqualTo("CCC");
        assertThat(next.getValue()).isEqualTo(10);

        next = resultSet.next();
        assertThat(next.getKey()).isEqualTo("AAA");
        assertThat(next.getValue()).isEqualTo(5);

        next = resultSet.next();
        assertThat(next.getKey()).isEqualTo("BBB");
        assertThat(next.getValue()).isEqualTo(2);

        assertThat(resultSet.hasNext()).isFalse();
    }
}