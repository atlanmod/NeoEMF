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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * An object capable of mapping features represented as a set of key/value pair.
 */
public interface PersistenceMapper {

    /**
     * Retrieves the value of a given {@link FeatureKey}.
     *
     * @param key the {@link FeatureKey} to look for
     *
     * @return an {@link Object} representing the value associated to the given {@code key}, or {@code null} if it isn't
     * in the database
     */
    <T> Optional<T> valueOf(FeatureKey key);

    /**
     * Retrieves the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #valueOf(FeatureKey)} but it uses multi-valued {@link Map} to retrieve the
     * element at the given index directly instead of returning the entire {@link Collection}.
     *
     * @param key the {@link MultivaluedFeatureKey} to get the value from
     *
     * @return an {@link Object} representing the value associated to the given {@code key}
     */
    <T> Optional<T> valueOf(MultivaluedFeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    Optional<Id> referenceOf(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    Optional<Id> referenceOf(MultivaluedFeatureKey key);

    /**
     * Sets the value of a given {@link FeatureKey}.
     *
     * @param key   the {@link FeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return the old value
     */
    <T> Optional<T> valueFor(FeatureKey key, T value);

    /**
     * Stores the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #valueFor(FeatureKey, Object)} but it uses the multi-valued {@link Map} that
     * stores indices explicitly.
     *
     * @param key   the {@link MultivaluedFeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return the old value
     */
    <T> Optional<T> valueFor(MultivaluedFeatureKey key, T value);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    Optional<Id> referenceFor(FeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id);

    /**
     * Unsets the value of a given {@link FeatureKey} from the database, and unset it ({@link #hasValue(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to unset
     *
     * @return an {@link Object} representing the unset value, or {@code null} if it hasn't been found
     */
    void unsetValue(FeatureKey key);

    /**
     * Removes the value of a given {@link FeatureKey} from the database, and unset it ({@link #hasValue(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to remove
     *
     * @return an {@link Object} representing the removed value, {@code null} if it hasn't been found
     */
    void unsetAllValues(FeatureKey key);

    /**
     *
     *
     * @param key
     */
    void unsetReference(FeatureKey key);

    /**
     *
     *
     * @param key
     */
    void unsetAllReferences(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the value is set, {@code false} otherwise
     */
    boolean hasValue(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the feature is set, {@code false} otherwise
     */
    boolean hasAnyValue(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    boolean hasReference(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    boolean hasAnyReference(FeatureKey key);

    /**
     *
     *
     * @param key
     * @param value
     */
    <T> void addValue(MultivaluedFeatureKey key, T value);

    /**
     *
     *
     * @param key
     * @param id
     */
    void addReference(MultivaluedFeatureKey key, Id id);

    /**
     *
     * @param key
     * @return
     */
    <T> Optional<T> removeValue(MultivaluedFeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    Optional<Id> removeReference(MultivaluedFeatureKey key);

    /**
     *
     * @param key
     */
    void cleanValues(FeatureKey key);

    /**
     *
     *
     * @param key
     */
    void cleanReferences(FeatureKey key);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    <T> boolean containsValue(FeatureKey key, T value);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    boolean containsReference(FeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @param value
     * @return
     */
    <T> OptionalInt indexOfValue(FeatureKey key, T value);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    OptionalInt indexOfReference(FeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @param value
     * @return
     */
    <T> OptionalInt lastIndexOfValue(FeatureKey key, T value);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    OptionalInt lastIndexOfReference(FeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @return
     */
    <T> Iterable<T> valuesAsList(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    Iterable<Id> referencesAsList(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    <T> OptionalInt sizeOf(FeatureKey key);
}
