package fr.inria.atlanmod.commons.collect;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A range (or "interval") defines the boundaries around a contiguous span of values of some {@link Comparable} type.
 *
 * @param <C> the type of this range
 */
@Immutable
@ParametersAreNonnullByDefault
@FunctionalInterface
public interface Range<C extends Comparable<C>> {

    /**
     * Creates a {@code Range} that contains all values strictly greater than {@code lower} and strictly less than
     * {@code upper}.
     *
     * @param lower the lower bound of the range (exclusive)
     * @param upper the upper bound of the range (exclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if {@code lower} is greater than {@code upper}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> open(C lower, C upper) {
        checkNotNull(lower);
        checkNotNull(upper);
        checkArgument(lower.compareTo(upper) <= 0);
        return v -> v.compareTo(lower) > 0 && v.compareTo(upper) < 0;
    }

    /**
     * Creates a {@code Range} that contains all values greater than or equal to {@code lower} and less than or equal to
     * {@code upper}.
     *
     * @param lower the lower bound of the range (inclusive)
     * @param upper the upper bound of the range (inclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if {@code lower} is greater than {@code upper}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> closed(C lower, C upper) {
        checkNotNull(lower);
        checkNotNull(upper);
        checkArgument(lower.compareTo(upper) <= 0);
        return v -> v.compareTo(lower) >= 0 && v.compareTo(upper) <= 0;
    }

    /**
     * Creates a {@code Range} that contains all values strictly greater than {@code lower} and less than or equal to
     * {@code upper}.
     *
     * @param lower the lower bound of the range (exclusive)
     * @param upper the upper bound of the range (inclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if {@code lower} is greater than {@code upper}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> openClosed(C lower, C upper) {
        checkNotNull(lower);
        checkNotNull(upper);
        checkArgument(lower.compareTo(upper) <= 0);
        return v -> v.compareTo(lower) > 0 && v.compareTo(upper) <= 0;
    }

    /**
     * Creates a {@code Range} that contains all values greater than or equal to {@code lower} and strictly less than
     * {@code upper}.
     *
     * @param lower the lower bound of the range (inclusive)
     * @param upper the upper bound of the range (exclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if {@code lower} is greater than {@code upper}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> closedOpen(C lower, C upper) {
        checkNotNull(lower);
        checkNotNull(upper);
        checkArgument(lower.compareTo(upper) <= 0);
        return v -> v.compareTo(lower) >= 0 && v.compareTo(upper) < 0;
    }

    /**
     * Creates a {@code Range} that contains only the given {@code value}. The returned range is closed on both ends.
     *
     * @param value the unique value of the range
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #closed(Comparable, Comparable)
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> singleton(C value) {
        return closed(value, value);
    }

    /**
     * Creates a {@code Range} that contains all values greater than or equal to {@code lower}.
     *
     * @param lower the lower bound of the range (inclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> atLeast(C lower) {
        checkNotNull(lower);
        return v -> v.compareTo(lower) >= 0;
    }

    /**
     * Creates a {@code Range} that contains all values less than or equal to {@code upper}.
     *
     * @param upper the upper bound of the range (inclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> atMost(C upper) {
        checkNotNull(upper);
        return v -> v.compareTo(upper) <= 0;
    }

    /**
     * Creates a {@code Range} that contains all values strictly greater than {@code lower}.
     *
     * @param lower the lower bound of the range (exclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> greaterThan(C lower) {
        checkNotNull(lower);
        return v -> v.compareTo(lower) > 0;
    }

    /**
     * Creates a {@code Range} that contains all values strictly less than {@code upper}.
     *
     * @param upper the upper bound of the range (exclusive)
     * @param <C>   the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> lessThan(C upper) {
        checkNotNull(upper);
        return v -> v.compareTo(upper) < 0;
    }

    /**
     * Creates a {@code Range} that contains every value of type {@link C}.
     *
     * @param <C> the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> all() {
        return v -> true;
    }

    /**
     * Creates a {@code Range} that does not contain any value.
     *
     * @param <C> the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> empty() {
        return v -> false;
    }

    /**
     * Creates a {@code Range} that contains all values enclosed by both ranges.
     * <p>
     * The intersection exists if and only if the two ranges are connected.
     *
     * @param r1  the first range
     * @param r2  the second range
     * @param <C> the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see Boolean#logicalAnd(boolean, boolean)
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> and(Range<C> r1, Range<C> r2) {
        checkNotNull(r1);
        checkNotNull(r2);
        return v -> Boolean.logicalAnd(r1.contains(v), r2.contains(v));
    }

    /**
     * Creates a {@code Range} that contains all values enclosed by at least one range.
     *
     * @param r1  the first range
     * @param r2  the second range
     * @param <C> the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see Boolean#logicalOr(boolean, boolean)
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> or(Range<C> r1, Range<C> r2) {
        checkNotNull(r1);
        checkNotNull(r2);
        return v -> Boolean.logicalOr(r1.contains(v), r2.contains(v));
    }

    /**
     * Creates a {@code Range} that contains all values enclosed by one range, but not another.
     *
     * @param r1  the first range
     * @param r2  the second range
     * @param <C> the type of the range
     *
     * @return a new instance of {@code Range}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see Boolean#logicalXor(boolean, boolean)
     */
    @Nonnull
    static <C extends Comparable<C>> Range<C> xor(Range<C> r1, Range<C> r2) {
        checkNotNull(r1);
        checkNotNull(r2);
        return v -> Boolean.logicalXor(r1.contains(v), r2.contains(v));
    }

    /**
     * Returns {@code true} if {@code value} is within the bounds of this range.
     *
     * @param value the value to be tested for inclusion
     *
     * @return {@code true} if {@code value} is within the bounds of this range
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    boolean contains(C value);

    /**
     * Returns {@code true} if every element in values is contained in this range.
     *
     * @param values the value to be tested for inclusion
     *
     * @return {@code true} if every element in values is contained in this range
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #contains(Comparable)
     */
    default boolean containsAll(Iterable<? extends C> values) {
        checkNotNull(values);
        return MoreIterables.stream(values).allMatch(this::contains);
    }
}
