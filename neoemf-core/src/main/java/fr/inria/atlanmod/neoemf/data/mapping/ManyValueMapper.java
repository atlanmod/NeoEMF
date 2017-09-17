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

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
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
    <V> Optional<V> valueOf(ManyFeatureBean key);

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
    <V> List<V> allValuesOf(SingleFeatureBean key);

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
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnull
    <V> Optional<V> valueFor(ManyFeatureBean key, V value);

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
    default <V> boolean hasAnyValue(SingleFeatureBean key) {
        return sizeOfValue(key).isPresent();
    }

    /**
     * Adds the {@code value} to the specified {@code key} at a defined position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    <V> void addValue(ManyFeatureBean key, V value);

    /**
     * Adds all the {@code collection} to the specified {@code key} from the position of the {@code key}.
     *
     * @param key        the key identifying the multi-valued attribute
     * @param collection the values to add
     * @param <V>        the type of value
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    default <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int firstPosition = key.position();

        IntStream.range(0, collection.size())
                .forEach(i -> addValue(key.withPosition(firstPosition + i), collection.get(i)));
    }

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
     * @see #addValue(ManyFeatureBean, Object)
     */
    @Nonnegative
    default <V> int appendValue(SingleFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        int position = sizeOfValue(key).orElse(0);

        addValue(key.withPosition(position), value);

        return position;
    }

    /**
     * Adds all the {@code collection} to the specified {@code key} from the last position.
     *
     * @param key        the key identifying the multi-valued attribute
     * @param collection the values to add
     * @param <V>        the type of collection
     *
     * @return the position to which the first value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnegative
    default <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        int firstPosition = sizeOfValue(key).orElse(0);

        addAllValues(key.withPosition(firstPosition), collection);

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
    <V> Optional<V> removeValue(ManyFeatureBean key);

    /**
     * Removes all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> void removeAllValues(SingleFeatureBean key) {
        removeValue(key);
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
    default <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
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
    default <V> boolean containsValue(SingleFeatureBean key, @Nullable V value) {
        return indexOfValue(key, value).isPresent();
    }

    /**
     * Retrieves the first position of the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return an {@link Optional} containing the first position of the {@code value}, or {@link Optional#empty()} if
     * the {@code key} hasn't the {@code value}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

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
     * @return an {@link Optional} containing the last position of the {@code value}, or {@link Optional#empty()} if the
     * {@code key} hasn't the {@code value}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        return Optional.of(allValuesOf(key).lastIndexOf(value))
                .filter(i -> i >= 0);
    }

    /**
     * Returns the number of value of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return an {@link Optional} containing the number of value of the {@code key}, or {@link Optional#empty()} if the
     * {@code key} hasn't any value, or if {@code size == 0}.
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return Optional.of(allValuesOf(key).size())
                .filter(s -> s != 0);
    }
}
