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

import java.time.Duration;
import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;

/**
 * Statistics about the performance of a {@link Cache}.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class CacheStats {

    /**
     * The number of cache hits.
     */
    @Nonnegative
    private final long hitCount;

    /**
     * The number of cache misses.
     */
    @Nonnegative
    private final long missCount;

    /**
     * The number of successful cache loads.
     */
    @Nonnegative
    private final long loadSuccessCount;

    /**
     * The number of failed cache loads.
     */
    @Nonnegative
    private final long loadFailureCount;

    /**
     * The total load time (success and failure) in nanoseconds.
     */
    @Nonnegative
    private final long totalLoadTime;

    /**
     * The number of entries evicted from the cache.
     */
    @Nonnegative
    private final long evictionCount;

    /**
     * Constructs a new {@code CacheStats}.
     *
     * @param hitCount         the number of cache hits
     * @param missCount        the number of cache misses
     * @param loadSuccessCount the number of successful cache loads
     * @param loadFailureCount the number of failed cache loads
     * @param totalLoadTime    the total load time (success and failure) in nanoseconds
     * @param evictionCount    the number of entries evicted from the cache
     */
    protected CacheStats(@Nonnegative long hitCount, @Nonnegative long missCount, @Nonnegative long loadSuccessCount, @Nonnegative long loadFailureCount, @Nonnegative long totalLoadTime, @Nonnegative long evictionCount) {
        checkArgument(hitCount >= 0);
        checkArgument(missCount >= 0);
        checkArgument(loadSuccessCount >= 0);
        checkArgument(loadFailureCount >= 0);
        checkArgument(totalLoadTime >= 0);
        checkArgument(evictionCount >= 0);

        this.hitCount = hitCount;
        this.missCount = missCount;
        this.loadSuccessCount = loadSuccessCount;
        this.loadFailureCount = loadFailureCount;
        this.totalLoadTime = totalLoadTime;
        this.evictionCount = evictionCount;
    }

    /**
     * Returns the number of times {@link Cache} lookup methods have returned either a cached or uncached value. This is
     * defined as {@code hitCount + missCount}.
     *
     * @return the {@code hitCount + missCount}
     */
    @Nonnegative
    public long requestCount() {
        return hitCount + missCount;
    }

    /**
     * Returns the number of times {@link Cache} lookup methods have returned a cached value.
     *
     * @return the number of times {@link Cache} lookup methods have returned a cached value
     */
    @Nonnegative
    public long hitCount() {
        return hitCount;
    }

    /**
     * Returns the ratio of cache requests which were hits. This is defined as {@code hitCount / requestCount}, or
     * {@code 1.0} when {@code requestCount == 0}. Note that {@code hitRate + missRate =~ 1.0}.
     *
     * @return the ratio of cache requests which were hits
     */
    @Nonnegative
    public double hitRate() {
        long requestCount = requestCount();
        return (requestCount == 0) ? 1.0 : (double) hitCount / requestCount;
    }

    /**
     * Returns the number of times {@link Cache} lookup methods have returned an uncached (newly loaded) value, or null.
     * Multiple concurrent calls to {@link Cache} lookup methods on an absent value can result in multiple misses, all
     * returning the results of a single cache load operation.
     *
     * @return the number of times {@link Cache} lookup methods have returned an uncached (newly loaded) value, or
     * {@code null}
     */
    @Nonnegative
    public long missCount() {
        return missCount;
    }

    /**
     * Returns the ratio of cache requests which were misses. This is defined as {@code missCount / requestCount}, or
     * {@code 0.0} when {@code requestCount == 0}. Note that {@code hitRate + missRate =~ 1.0}. Cache misses include all
     * requests which weren't cache hits, including requests which resulted in either successful or failed loading
     * attempts, and requests which waited for other threads to finish loading. It is thus the case that
     * {@code missCount &gt;= loadSuccessCount + loadFailureCount}. Multiple concurrent misses for the same key will
     * result in a single load operation.
     *
     * @return the ratio of cache requests which were misses
     */
    @Nonnegative
    public double missRate() {
        long requestCount = requestCount();
        return (requestCount == 0) ? 0.0 : (double) missCount / requestCount;
    }

    /**
     * Returns the total number of times that {@link Cache} lookup methods attempted to load new values. This includes
     * both successful load operations, as well as those that threw exceptions. This is defined as
     * {@code loadSuccessCount + loadFailureCount}.
     *
     * @return the {@code loadSuccessCount + loadFailureCount}
     */
    @Nonnegative
    public long loadCount() {
        return loadSuccessCount + loadFailureCount;
    }

    /**
     * Returns the number of times {@link Cache} lookup methods have successfully loaded a new value. This is always
     * incremented in conjunction with {@link #missCount}, though {@code missCount} is also incremented when an
     * exception is encountered during cache loading (see {@link #loadFailureCount}). Multiple concurrent misses for the
     * same key will result in a single load operation.
     *
     * @return the number of times {@link Cache} lookup methods have successfully loaded a new value
     */
    @Nonnegative
    public long loadSuccessCount() {
        return loadSuccessCount;
    }

    /**
     * Returns the number of times {@link Cache} lookup methods failed to load a new value, either because no value was
     * found or an exception was thrown while loading. This is always incremented in conjunction with {@code missCount},
     * though {@code missCount} is also incremented when cache loading completes successfully
     * (see {@link #loadSuccessCount}). Multiple concurrent misses for the same key will result in a single load
     * operation.
     *
     * @return the number of times {@link Cache} lookup methods failed to load a new value
     */
    @Nonnegative
    public long loadFailureCount() {
        return loadFailureCount;
    }

    /**
     * Returns the ratio of cache loading attempts which threw exceptions. This is defined as
     * {@code loadFailureCount / (loadSuccessCount + loadFailureCount)}, or {@code 0.0} when
     * {@code loadSuccessCount + loadFailureCount == 0}.
     *
     * @return the ratio of cache loading attempts which threw exceptions
     */
    @Nonnegative
    public double loadFailureRate() {
        long loadCount = loadCount();
        return (loadCount == 0) ? 0.0 : (double) loadFailureCount / loadCount;
    }

    /**
     * Returns the total number of nanoseconds the cache has spent loading new values. This can be used to calculate the
     * miss penalty. This value is increased every time {@code loadSuccessCount} or {@code loadFailureCount} is
     * incremented.
     *
     * @return the total number of nanoseconds the cache has spent loading new values
     */
    @Nonnegative
    public Duration totalLoadTime() {
        return Duration.ofNanos(totalLoadTime);
    }

    /**
     * Returns the average time spent loading new values. This is defined as
     * {@code totalLoadTime / (loadSuccessCount + loadFailureCount)}.
     *
     * @return the average time spent loading new values
     */
    @Nonnegative
    public double averageLoadPenalty() {
        long loadCount = loadCount();
        return (loadCount == 0) ? 0.0 : (double) totalLoadTime / loadCount;
    }

    /**
     * Returns the number of times an entry has been evicted. This count does not include manual {@link
     * Cache#invalidate}.
     *
     * @return the number of times an entry has been evicted
     */
    @Nonnegative
    public long evictionCount() {
        return evictionCount;
    }

    /**
     * Returns a new {@code CacheStats} representing the difference between this {@code CacheStats} and {@code other}.
     * Negative values, which aren't supported by {@code CacheStats} will be rounded up to zero.
     *
     * @param other the statistics to subtract with
     *
     * @return the difference between this instance and {@code other}
     */
    @Nonnull
    public CacheStats minus(CacheStats other) {
        return new CacheStats(
                Math.max(0L, hitCount - other.hitCount),
                Math.max(0L, missCount - other.missCount),
                Math.max(0L, loadSuccessCount - other.loadSuccessCount),
                Math.max(0L, loadFailureCount - other.loadFailureCount),
                Math.max(0L, totalLoadTime - other.totalLoadTime),
                Math.max(0L, evictionCount - other.evictionCount));
    }

    /**
     * Returns a new {@code CacheStats} representing the sum of this {@code CacheStats} and {@code other}.
     *
     * @param other the statistics to add with
     *
     * @return the sum of the statistics
     */
    @Nonnull
    public CacheStats plus(CacheStats other) {
        return new CacheStats(
                hitCount + other.hitCount,
                missCount + other.missCount,
                loadSuccessCount + other.loadSuccessCount,
                loadFailureCount + other.loadFailureCount,
                totalLoadTime + other.totalLoadTime,
                evictionCount + other.evictionCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitCount, missCount, loadSuccessCount, loadFailureCount, totalLoadTime, evictionCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!CacheStats.class.isInstance(o)) {
            return false;
        }

        CacheStats that = CacheStats.class.cast(o);
        return hitCount == that.hitCount
                && missCount == that.missCount
                && loadSuccessCount == that.loadSuccessCount
                && loadFailureCount == that.loadFailureCount
                && totalLoadTime == that.totalLoadTime
                && evictionCount == that.evictionCount;
    }

    @Override
    public String toString() {
        return String.format("CacheStats {"
                        + "Hit = %d (%.2f%%), "
                        + "Miss = %d (%.2f%%), "
                        + "Eviction Count = %d"
                        + "}",
                hitCount(),
                hitRate(),
                missCount(),
                missRate(),
                evictionCount());
    }
}
