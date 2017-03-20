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
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the specific behavior of {@link CacheStats}.
 */
public class CacheStatsTest extends AbstractTest {

    private CacheStats stats;

    @Before
    public void setUp() {
        stats = new CacheStats(30, 10, 15, 5, 2000, 2);
    }

    @Test
    public void testConstructor() {
        Throwable thrown0 = catchThrowable(() -> new CacheStats(0, 0, 0, 0, 0, 0));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> new CacheStats(-1, 0, 0, 0, 0, 0));
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class);

        Throwable thrown2 = catchThrowable(() -> new CacheStats(0, -1, 0, 0, 0, 0));
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class);

        Throwable thrown3 = catchThrowable(() -> new CacheStats(0, 0, -1, 0, 0, 0));
        assertThat(thrown3).isInstanceOf(IllegalArgumentException.class);

        Throwable thrown4 = catchThrowable(() -> new CacheStats(0, 0, 0, -1, 0, 0));
        assertThat(thrown4).isInstanceOf(IllegalArgumentException.class);

        Throwable thrown5 = catchThrowable(() -> new CacheStats(0, 0, 0, 0, -1, 0));
        assertThat(thrown5).isInstanceOf(IllegalArgumentException.class);

        Throwable thrown6 = catchThrowable(() -> new CacheStats(0, 0, 0, 0, 0, -1));
        assertThat(thrown6).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testRequestCount() {
        assertThat(stats.requestCount()).isEqualTo(40);
    }

    @Test
    public void testHitCount() {
        assertThat(stats.hitCount()).isEqualTo(30);
    }

    @Test
    public void testHitRate() {
        assertThat(stats.hitRate()).isEqualTo(0.75);
    }

    @Test
    public void testMissCount() {
        assertThat(stats.missCount()).isEqualTo(10);
    }

    @Test
    public void testMissRate() {
        assertThat(stats.missRate()).isEqualTo(0.25);
    }

    @Test
    public void testLoadCount() {
        assertThat(stats.loadCount()).isEqualTo(20);
    }

    @Test
    public void testLoadSuccessCount() {
        assertThat(stats.loadSuccessCount()).isEqualTo(15);
    }

    @Test
    public void testLoadFailureCount() {
        assertThat(stats.loadFailureCount()).isEqualTo(5);
    }

    @Test
    public void testLoadFailureRate() {
        assertThat(stats.loadFailureRate()).isEqualTo(0.25);
    }

    @Test
    public void testTotalLoadTime() {
        assertThat(stats.totalLoadTime()).isEqualByComparingTo(Duration.ofNanos(2000));
    }

    @Test
    public void testAverageLoadPenalty() {
        assertThat(stats.averageLoadPenalty()).isEqualTo(100);
    }

    @Test
    public void testEvictionCount() {
        assertThat(stats.evictionCount()).isEqualTo(2);
    }

    @Test
    public void testMinus() {
        CacheStats result = stats.minus(stats);

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(result)).isFalse();

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
    public void testPlus() {
        CacheStats result = stats.plus(stats);

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(result)).isFalse();
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

    @Test
    public void testEquals() {
        //noinspection EqualsWithItself, EqualsReplaceableByObjectsCall
        assertThat(stats.equals(stats)).isTrue();

        //noinspection ObjectEqualsNull, EqualsReplaceableByObjectsCall
        assertThat(stats.equals(null)).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(new CacheStats(0, 10, 15, 5, 2000, 2))).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(new CacheStats(30, 0, 15, 5, 2000, 2))).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(new CacheStats(30, 10, 0, 5, 2000, 2))).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(new CacheStats(30, 10, 15, 0, 2000, 2))).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(new CacheStats(30, 10, 15, 5, 0, 2))).isFalse();

        //noinspection EqualsReplaceableByObjectsCall
        assertThat(stats.equals(new CacheStats(30, 10, 15, 5, 2000, 0))).isFalse();
    }

    @Test
    public void testToString() {
        assertThat(stats.toString());
    }
}