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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ManyValueMapper extends ValueMapper {

    /**
     * Retrieves the value of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return an {@link Optional} containing the value, or {@link Optional#empty()} if the key hasn't any value or
     * doesn't exist
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> Optional<V> valueOf(ManyFeatureKey key);

    /**
     * Retrieves all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return an immutable {@link List} containing all values
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> List<V> allValuesOf(SingleFeatureKey key);

    /**
     * Defines the {@code value} of the specified {@code key} at a defined position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @return an {@link Optional} containing the previous value of the {@code key}, or {@link Optional#empty()} if the
     * key has no value before
     *
     * @throws NoSuchElementException if the {@code key} doesn't exist
     * @throws NullPointerException   if any parameter is {@code null}
     * @see #addValue(ManyFeatureKey, Object)
     * @see #appendValue(SingleFeatureKey, Object)
     */
    @Nonnull
    <V> Optional<V> valueFor(ManyFeatureKey key, V value);

    /**
     * Checks whether the specified {@code key} has at least one defined value.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return {@code true} if the {@code key} has a value, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> boolean hasAnyValue(SingleFeatureKey key) {
        return hasValue(key);
    }

    /**
     * Adds the {@code value} to the specified {@code key} at a defined position. If {@code key#position > size} then
     * it creates {@code null} elements to respect the position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    <V> void addValue(ManyFeatureKey key, V value);

    /**
     * Adds the {@code value} to the specified {@code key} at the last position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @return the position to which the value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureKey, Object)
     */
    @Nonnegative
    default <V> int appendValue(SingleFeatureKey key, V value) {
        checkNotNull(key);

        int position = sizeOfValue(key).orElse(0);

        addValue(key.withPosition(position), value);

        return position;
    }

    /**
     * Adds all {@code values} to the specified {@code key} from the last position.
     *
     * @param key    the key identifying the multi-valued attribute
     * @param values the values to add
     * @param <V>    the type of values
     *
     * @return the position to which the first value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureKey, Object)
     * @see #appendValue(SingleFeatureKey, Object)
     */
    @Nonnegative
    default <V> int appendAllValues(SingleFeatureKey key, List<? extends V> values) {
        checkNotNull(key);
        checkNotNull(values);

        int firstPosition = sizeOfValue(key).orElse(0);

        IntStream.range(0, values.size())
                .forEach(i -> addValue(key.withPosition(firstPosition + i), values.get(i)));

        return firstPosition;
    }

    /**
     * Removes the value of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return an {@link Optional} containing the removed value, or {@link Optional#empty()} if the key has no value
     * before
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> Optional<V> removeValue(ManyFeatureKey key);

    /**
     * Removes all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> void removeAllValues(SingleFeatureKey key) {
        unsetValue(key);
    }

    /**
     * Moves the value of the specified {@code source} key to the {@code target} key.
     *
     * @param source the key identifying the multi-valued attribute to move
     * @param target the key identifying the multi-valued attribute where to move the value to
     * @param <V>    the type of value
     *
     * @return an {@link Optional} containing the moved value, or {@link Optional#empty()} if no value has been moved
     */
    @Nonnull
    default <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        checkNotNull(source);
        checkNotNull(target);

        if (Objects.equals(source, target)) {
            return Optional.empty();
        }

        Optional<V> movedValue = removeValue(source);
        movedValue.ifPresent(v -> addValue(target, v));
        return movedValue;
    }

    /**
     * Checks whether the specified {@code key} has the given {@code value}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return {@code true} if the {@code key} has the {@code value}, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> boolean containsValue(SingleFeatureKey key, @Nullable V value) {
        return indexOfValue(key, value).isPresent();
    }

    /**
     * Retrieves the first position of the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return an {@link Optional} containing the first position of the {@code value}, or {@link Optional#empty()}
     * if the {@code key} hasn't the {@code value}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        // TODO Don't browse all values
        return Optional.of(allValuesOf(key).indexOf(value))
                .filter(i -> i >= 0);
    }

    /**
     * Retrieves the last position of the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return an {@link Optional} containing the last position of the {@code value}, or {@link Optional#empty()}
     * if the {@code key} hasn't the {@code value}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        // TODO Don't browse all values
        return Optional.of(allValuesOf(key).lastIndexOf(value))
                .filter(i -> i >= 0);
    }

    /**
     * Returns the number of value of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return an {@link Optional} containing the number of value of the {@code key}, or {@link Optional#empty()}
     * if the {@code key} hasn't any value, or if {@code size == 0}.
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        return Optional.of(allValuesOf(key).size())
                .filter(s -> s != 0);
    }
}
