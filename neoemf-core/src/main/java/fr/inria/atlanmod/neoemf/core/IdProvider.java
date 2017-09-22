package fr.inria.atlanmod.neoemf.core;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A provider of {@link Id} instances.
 */
@ParametersAreNonnullByDefault
public interface IdProvider {

    /**
     * Creates a new {@link Id} from a long representation.
     *
     * @param value the long representation of the new identifier
     *
     * @return a new instance of an {@link Id}
     *
     * @see Id#toLong()
     */
    @Nonnull
    Id fromLong(long value);

    /**
     * Creates a new {@link Id} from an hexadecimal representation.
     *
     * @param value the hexadecimal representation of the new identifier
     *
     * @return a new instance of an {@link Id}
     *
     * @throws IllegalArgumentException if the {@code value} does not represent an hexadecimal value
     * @see Id#toHexString()
     */
    @Nonnull
    Id fromHexString(String value);

    /**
     * Generates a new instance of an {@link Id} initialized with a random value.
     *
     * @return a new instance of an {@link Id}
     */
    @Nonnull
    Id generate();

    /**
     * Generates a new instance of an {@link Id} from a base {@code value}.
     * <p>
     * Several calls to this method with the same {@code value} should return the same identifier.
     *
     * @param value the base value of the identifier
     *
     * @return a new instance of an {@link Id}
     */
    @Nonnull
    Id generate(String value);
}
