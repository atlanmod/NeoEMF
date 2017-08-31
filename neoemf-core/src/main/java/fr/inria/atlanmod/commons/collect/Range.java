package fr.inria.atlanmod.commons.collect;

import javax.annotation.concurrent.Immutable;

@Immutable
@FunctionalInterface
public interface Range<C extends Comparable<C>> {

    /**
     * Creates a {@code Range} that contains all values strictly greater than {@code lower} and strictly less than
     * {@code upper}.
     *
     * @param lower
     * @param upper
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> open(C lower, C upper) {
        return v -> v.compareTo(lower) > 0 && v.compareTo(upper) < 0;
    }

    /**
     * Creates a {@code Range} that contains all values greater than or equal to {@code lower} and less than or equal to
     * {@code upper}.
     *
     * @param lower
     * @param upper
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> closed(C lower, C upper) {
        return v -> v.compareTo(lower) >= 0 && v.compareTo(upper) <= 0;
    }

    /**
     * Creates a {@code Range} that contains all values strictly greater than {@code lower} and less than or equal to
     * {@code upper}.
     *
     * @param lower
     * @param upper
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> openClosed(C lower, C upper) {
        return v -> v.compareTo(lower) > 0 && v.compareTo(upper) <= 0;
    }

    /**
     * Creates a {@code Range} that contains all values greater than or equal to {@code lower} and strictly less than
     * {@code upper}.
     *
     * @param lower
     * @param upper
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> closedOpen(C lower, C upper) {
        return v -> v.compareTo(lower) >= 0 && v.compareTo(upper) < 0;
    }

    /**
     * Creates a {@code Range} that contains only the given {@code value}.
     *
     * @param value
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> singleton(C value) {
        return closed(value, value);
    }

    /**
     * Creates a {@code Range} that contains all values greater than or equal to {@code endPoint}.
     *
     * @param endPoint
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> atLeast(C endPoint) {
        return v -> v.compareTo(endPoint) >= 0;
    }

    /**
     * Creates a {@code Range} that contains all values less than or equal to {@code endPoint}.
     *
     * @param endPoint
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> atMost(C endPoint) {
        return v -> v.compareTo(endPoint) <= 0;
    }

    /**
     * Creates a {@code Range} that contains all values strictly greater than {@code endPoint}.
     *
     * @param endPoint
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> greaterThan(C endPoint) {
        return v -> v.compareTo(endPoint) > 0;
    }

    /**
     * Creates a {@code Range} that contains all values strictly less than {@code endPoint}.
     *
     * @param endPoint
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> lessThan(C endPoint) {
        return v -> v.compareTo(endPoint) < 0;
    }

    /**
     * Creates a {@code Range} that contains every value of type {@link C}.
     *
     * @param <C>
     *
     * @return
     */
    static <C extends Comparable<C>> Range<C> all() {
        return v -> true;
    }

    /**
     * Creates a {@code Range} that contains ...
     *
     * @param r1
     * @param r2
     * @param <C>
     *
     * @return
     *
     * @see Boolean#logicalAnd(boolean, boolean)
     */
    static <C extends Comparable<C>> Range<C> intersection(Range<C> r1, Range<C> r2) {
        return v -> Boolean.logicalAnd(r1.contains(v), r2.contains(v));
    }

    /**
     * Creates a {@code Range} that contains ...
     *
     * @param r1
     * @param r2
     * @param <C>
     *
     * @return
     *
     * @see Boolean#logicalOr(boolean, boolean)
     */
    static <C extends Comparable<C>> Range<C> union(Range<C> r1, Range<C> r2) {
        return v -> Boolean.logicalOr(r1.contains(v), r2.contains(v));
    }

    /**
     * Creates a {@code Range} that contains ...
     *
     * @param r1
     * @param r2
     * @param <C>
     *
     * @return
     *
     * @see Boolean#logicalXor(boolean, boolean)
     */
    static <C extends Comparable<C>> Range<C> difference(Range<C> r1, Range<C> r2) {
        return v -> Boolean.logicalXor(r1.contains(v), r2.contains(v));
    }

    /**
     * Returns {@code true} if {@code value} is within the bounds of this range.
     *
     * @param value
     *
     * @return
     */
    boolean contains(C value);

    /**
     * Returns {@code true} if every element in values is contained in this range.
     *
     * @param values
     *
     * @return
     *
     * @see #contains(Comparable)
     */
    default boolean containsAll(Iterable<? extends C> values) {
        return MoreIterables.stream(values).allMatch(this::contains);
    }
}
