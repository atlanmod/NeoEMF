/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ManyValueMapper extends ValueMapper {

    /**
     * Retrieves the value of the specified {@code feature} at a defined position.
     *
     * @param feature the bean identifying the multi-valued attribute
     *
     * @return an {@link Optional} containing the value, or {@link Optional#empty()} if the feature hasn't any value or
     * doesn't exist
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    <V> Optional<V> valueOf(ManyFeatureBean feature);

    /**
     * Retrieves all values of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued attribute
     *
     * @return an immutable ordered {@link Stream} over all values
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    <V> Stream<V> allValuesOf(SingleFeatureBean feature);

    /**
     * Defines the {@code value} of the specified {@code feature} at a defined position.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param value   the value to set
     * @param <V>     the type of value
     *
     * @return an {@link Optional} containing the previous value of the {@code feature}, or {@link Optional#empty()} if
     * the feature has no value before
     *
     * @throws NoSuchElementException if the {@code feature} doesn't exist
     * @throws NullPointerException   if any parameter is {@code null}
     * @implSpec This method is intended to modify an existing value. If the {@code feature} is not defined,
     * implementations should not add the value, but throw a {@link NoSuchElementException}.
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnull
    <V> Optional<V> valueFor(ManyFeatureBean feature, V value);

    /**
     * Adds the {@code value} to the specified {@code feature} at a defined position.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param value   the value to add
     * @param <V>     the type of value
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code feature#position() > size}
     */
    <V> void addValue(ManyFeatureBean feature, V value);

    /**
     * Adds all the {@code collection} to the specified {@code feature} from the position of the {@code feature}.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param collection  the values to add
     * @param <V>     the type of value
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code feature#position() > size}
     */
    <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection);

    /**
     * Adds the {@code value} to the specified {@code feature} at the last position.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param value   the value to add
     * @param <V>     the type of value
     *
     * @return the position to which the value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureBean, Object)
     */
    @Nonnegative
    default <V> int appendValue(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        int position = sizeOfValue(feature).orElse(0);

        addValue(feature.withPosition(position), value);

        return position;
    }

    /**
     * Adds all the {@code collection} to the specified {@code feature} from the last position.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param collection  the values to add
     * @param <V>     the type of values
     *
     * @return the position to which the first value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnegative
    default <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");

        int firstPosition = sizeOfValue(feature).orElse(0);

        addAllValues(feature.withPosition(firstPosition), collection);

        return firstPosition;
    }

    /**
     * Removes the value of the specified {@code feature} at a defined position.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param <V>     the type of value
     *
     * @return an {@link Optional} containing the removed value, or {@link Optional#empty()} if the feature has no value
     * before
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    <V> Optional<V> removeValue(ManyFeatureBean feature);

    /**
     * Removes all values of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued attribute
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    void removeAllValues(SingleFeatureBean feature);

    /**
     * Returns the number of value of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued attribute
     *
     * @return an {@link Optional} containing the number of value of the {@code feature}, or {@link Optional#empty()} if
     * the {@code feature} hasn't any value, or if {@code size == 0}.
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    @Nonnegative
    Optional<Integer> sizeOfValue(SingleFeatureBean feature);
}
