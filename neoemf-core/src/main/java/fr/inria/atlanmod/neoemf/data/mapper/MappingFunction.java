package fr.inria.atlanmod.neoemf.data.mapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that converts primitives values.
 *
 * @param <I> the type of the input value
 * @param <O> the type of the ouput value
 *
 * @see ReferenceWith
 * @see ManyValueWith
 * @see ManyReferenceWith
 */
@ParametersAreNonnullByDefault
public interface MappingFunction<I, O> {

    /**
     * Maps the input value.
     *
     * @param i the input value to map
     *
     * @return the mapped value
     */
    @Nonnull
    O map(I i);

    /**
     * Unmaps the output value.
     *
     * @param o the output value to unmap
     *
     * @return the unmapped value
     */
    @Nonnull
    I unmap(O o);
}
