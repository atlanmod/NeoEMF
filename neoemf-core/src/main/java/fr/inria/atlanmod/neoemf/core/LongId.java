package fr.inria.atlanmod.neoemf.core;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * An {@link Id} with a long representation.
 */
@Immutable
@ParametersAreNonnullByDefault
class LongId implements Id {

    /**
     * A string of 16 zeros.
     */
    @Nonnull
    private static final String ZEROS = "0000000000000000";

    /**
     * The long representation of this identifier.
     */
    private final long value;

    /**
     * Constructs a new {@code LongId} with its long representation.
     *
     * @param value the long representation of this identifier
     */
    protected LongId(long value) {
        this.value = value;
    }

    @Override
    public int compareTo(Id id) {
        return Long.compare(toLong(), id.toLong());
    }

    @Override
    public long toLong() {
        return value;
    }

    @Nonnull
    @Override
    public String toHexString() {
        return fixLeadingZeros(Long.toHexString(value));
    }

    /**
     * Adds leading zeros for the specified {@code hexValue}: all hexadecimal representations have the same size.
     *
     * @param hexValue the hexadecimal value
     *
     * @return a string of 16 characters
     */
    @Nonnull
    private String fixLeadingZeros(String hexValue) {
        return hexValue.length() < 16
                ? ZEROS.substring(hexValue.length()) + hexValue
                : hexValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LongId that = LongId.class.cast(o);
        return value == that.value;
    }

    @Nonnull
    @Override
    public String toString() {
        return String.format("Id {%s}", toHexString());
    }
}
