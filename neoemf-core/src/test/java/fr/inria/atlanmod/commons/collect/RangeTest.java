package fr.inria.atlanmod.commons.collect;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeTest extends AbstractTest {

    @Test
    public void testOpen() {
        Range<Integer> r0 = Range.open(0, 2);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isFalse();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isFalse();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testClosed() {
        Range<Integer> r0 = Range.closed(0, 2);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isTrue();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isTrue();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testOpenClosed() {
        Range<Integer> r0 = Range.openClosed(0, 2);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isFalse();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isTrue();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testClosedOpen() {
        Range<Integer> r0 = Range.closedOpen(0, 2);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isTrue();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isFalse();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testSingleton() {
        Range<Integer> r0 = Range.singleton(1);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isFalse();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isFalse();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testAtLeast() {
        Range<Integer> r0 = Range.atLeast(1);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isFalse();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isTrue();
        assertThat(r0.contains(3)).isTrue();
    }

    @Test
    public void testAtMost() {
        Range<Integer> r0 = Range.atMost(1);

        assertThat(r0.contains(-1)).isTrue();
        assertThat(r0.contains(0)).isTrue();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isFalse();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testGreaterThan() {
        Range<Integer> r0 = Range.greaterThan(1);

        assertThat(r0.contains(-1)).isFalse();
        assertThat(r0.contains(0)).isFalse();
        assertThat(r0.contains(1)).isFalse();
        assertThat(r0.contains(2)).isTrue();
        assertThat(r0.contains(3)).isTrue();
    }

    @Test
    public void testLessThan() {
        Range<Integer> r0 = Range.lessThan(1);

        assertThat(r0.contains(-1)).isTrue();
        assertThat(r0.contains(0)).isTrue();
        assertThat(r0.contains(1)).isFalse();
        assertThat(r0.contains(2)).isFalse();
        assertThat(r0.contains(3)).isFalse();
    }

    @Test
    public void testAll() {
        Range<Integer> r0 = Range.all();

        assertThat(r0.contains(-1)).isTrue();
        assertThat(r0.contains(0)).isTrue();
        assertThat(r0.contains(1)).isTrue();
        assertThat(r0.contains(2)).isTrue();
        assertThat(r0.contains(3)).isTrue();
    }

    @Test
    public void testIntersection() {
        Range<Integer> r0 = Range.closed(0, 2);
        Range<Integer> r1 = Range.closed(1, 3);

        Range<Integer> r01 = Range.intersection(r0, r1);

        assertThat(r01.contains(0)).isFalse();
        assertThat(r01.contains(1)).isTrue();
        assertThat(r01.contains(2)).isTrue();
        assertThat(r01.contains(3)).isFalse();
    }

    @Test
    public void testUnion() {
        Range<Integer> r0 = Range.closed(0, 2);
        Range<Integer> r1 = Range.closed(1, 3);

        Range<Integer> r01 = Range.union(r0, r1);

        assertThat(r01.contains(0)).isTrue();
        assertThat(r01.contains(1)).isTrue();
        assertThat(r01.contains(2)).isTrue();
        assertThat(r01.contains(3)).isTrue();
    }

    @Test
    public void testDifference() {
        Range<Integer> r0 = Range.closed(0, 2);
        Range<Integer> r1 = Range.closed(1, 3);
        Range<Integer> r01 = Range.difference(r0, r1);

        assertThat(r01.contains(0)).isTrue();
        assertThat(r01.contains(1)).isFalse();
        assertThat(r01.contains(2)).isFalse();
        assertThat(r01.contains(3)).isTrue();
    }
}
