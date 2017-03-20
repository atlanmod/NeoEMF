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

package fr.inria.atlanmod.neoemf.util.cache;

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the specific behavior of {@link CacheStats}.
 */
public class CacheStatsTest extends AbstractTest {

    private CacheStats stats;

    @Before
    public void setUp() throws Exception {
        stats = new CacheStats(30, 10, 15, 5, 2000, 2);

    }

    @Test
    public void testRequestCount() throws Exception {
        assertThat(stats.requestCount()).isEqualTo(40);
    }

    @Test
    public void testHitCount() throws Exception {
        assertThat(stats.hitCount()).isEqualTo(30);
    }

    @Test
    public void testHitRate() throws Exception {
        assertThat(stats.hitRate()).isEqualTo(0.75);
    }

    @Test
    public void testMissCount() throws Exception {
        assertThat(stats.missCount()).isEqualTo(10);
    }

    @Test
    public void testMissRate() throws Exception {
        assertThat(stats.missRate()).isEqualTo(0.25);
    }

    @Test
    public void testLoadCount() throws Exception {
        assertThat(stats.loadCount()).isEqualTo(20);
    }

    @Test
    public void testLoadSuccessCount() throws Exception {
        assertThat(stats.loadSuccessCount()).isEqualTo(15);
    }

    @Test
    public void testLoadFailureCount() throws Exception {
        assertThat(stats.loadFailureCount()).isEqualTo(5);
    }

    @Test
    public void testLoadFailureRate() throws Exception {
        assertThat(stats.loadFailureRate()).isEqualTo(0.25);
    }

    @Test
    public void testTotalLoadTime() throws Exception {
        assertThat(stats.totalLoadTime()).isEqualByComparingTo(Duration.ofNanos(2000));
    }

    @Test
    public void testAverageLoadPenalty() throws Exception {
        assertThat(stats.averageLoadPenalty()).isEqualTo(100);
    }

    @Test
    public void testEvictionCount() throws Exception {
        assertThat(stats.evictionCount()).isEqualTo(2);
    }

    @Test
    public void testMinus() throws Exception {
        CacheStats result = stats.minus(stats);

        assertThat(stats).isNotEqualTo(result);
        assertThat(stats.hashCode()).isNotEqualTo(result.hashCode());

        assertThat(result.requestCount()).isEqualTo(0);
        assertThat(result.hitCount()).isEqualTo(0);
        assertThat(result.hitRate()).isEqualTo(1.0);
        assertThat(result.missCount()).isEqualTo(0);
        assertThat(result.missRate()).isEqualTo(0.0);
        assertThat(result.loadCount()).isEqualTo(0);
        assertThat(result.loadSuccessCount()).isEqualTo(0);
        assertThat(result.loadFailureCount()).isEqualTo(0);
        assertThat(result.loadFailureRate()).isEqualTo(0.0);
        assertThat(result.totalLoadTime()).isEqualByComparingTo(Duration.ofNanos(0));
        assertThat(result.averageLoadPenalty()).isEqualTo(0);
        assertThat(result.evictionCount()).isEqualTo(0);
    }

    @Test
    public void testPlus() throws Exception {
        CacheStats result = stats.plus(stats);

        assertThat(stats).isNotEqualTo(result);
        assertThat(stats.hashCode()).isNotEqualTo(result.hashCode());

        assertThat(result.requestCount()).isEqualTo(80);
        assertThat(result.hitCount()).isEqualTo(60);
        assertThat(result.hitRate()).isEqualTo(0.75);
        assertThat(result.missCount()).isEqualTo(20);
        assertThat(result.missRate()).isEqualTo(0.25);
        assertThat(result.loadCount()).isEqualTo(40);
        assertThat(result.loadSuccessCount()).isEqualTo(30);
        assertThat(result.loadFailureCount()).isEqualTo(10);
        assertThat(result.loadFailureRate()).isEqualTo(0.25);
        assertThat(result.totalLoadTime()).isEqualByComparingTo(Duration.ofNanos(4000));
        assertThat(result.averageLoadPenalty()).isEqualTo(100);
        assertThat(result.evictionCount()).isEqualTo(4);
    }
}