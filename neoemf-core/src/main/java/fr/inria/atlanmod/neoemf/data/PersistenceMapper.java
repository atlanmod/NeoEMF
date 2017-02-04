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

/**
 * An object capable of mapping features represented as a set of key/value pair.
 */
public interface PersistenceMapper {

    //region Single-valued attribute

    /**
     * Retrieves the value of a given {@link FeatureKey}.
     *
     * @param key the {@link FeatureKey} to look for
     *
     * @return an {@link Object} representing the value associated to the given {@code key}, or {@code null} if it isn't
     * in the database
     */
    Object getValue(FeatureKey key);

    /**
     * Sets the value of a given {@link FeatureKey}.
     *
     * @param key   the {@link FeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return the old value
     */
    Object setValue(FeatureKey key, Object value);

    /**
     * Unsets the value of a given {@link FeatureKey} from the database, and unset it ({@link #hasValue(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to unset
     *
     * @return an {@link Object} representing the unset value, or {@code null} if it hasn't been found
     */
    void unsetValue(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the value is set, {@code false} otherwise
     */
    boolean hasValue(FeatureKey key);

    //endregion

    //region Single-valued reference

    /**
     *
     *
     * @param key
     * @return
     */
    Id getReference(FeatureKey key);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    Id setReference(FeatureKey key, Id id);

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
     * @return
     */
    boolean hasReference(FeatureKey key);

    //endregion

    //region Multi-valued attribute

    /**
     * Retrieves the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #getValue(FeatureKey)} but it uses multi-valued {@link Map} to retrieve the
     * element at the given index directly instead of returning the entire {@link Collection}.
     *
     * @param key the {@link MultivaluedFeatureKey} to get the value from
     *
     * @return an {@link Object} representing the value associated to the given {@code key}
     */
    Object getValueAtIndex(MultivaluedFeatureKey key);

    /**
     * Stores the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #setValue(FeatureKey, Object)} but it uses the multi-valued {@link Map} that
     * stores indices explicitly.
     *
     * @param key   the {@link MultivaluedFeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return the old value
     */
    Object setValueAtIndex(MultivaluedFeatureKey key, Object value);

    /**
     * Removes the value of a given {@link FeatureKey} from the database, and unset it ({@link
     * #hasValue(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to remove
     *
     * @return an {@link Object} representing the removed value, {@code null} if it hasn't been found
     */
    void unsetValueAtIndex(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the feature is set, {@code false} otherwise
     */
    boolean hasValueAtIndex(FeatureKey key);

    /**
     *
     *
     * @param key
     * @param value
     */
    void addValue(MultivaluedFeatureKey key, Object value);

    /**
     *
     * @param key
     * @return
     */
    Object removeValue(MultivaluedFeatureKey key);

    /**
     *
     * @param key
     */
    void cleanValue(FeatureKey key);

    /**
     *
     * @param key
     * @return
     */
    Iterable<Object> valueAsList(FeatureKey key);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    boolean containsValue(FeatureKey key, Object value);

    /**
     *
     *
     * @param key
     * @param value
     * @return
     */
    int indexOfValue(FeatureKey key, Object value);

    /**
     *
     *
     * @param key
     * @param value
     * @return
     */
    int lastIndexOfValue(FeatureKey key, Object value);

    /**
     *
     *
     * @param key
     * @return
     */
    Iterable<Object> valueAtIndexAsList(FeatureKey key);

    //endregion

    //region Multi-valued reference

    /**
     *
     *
     * @param key
     * @return
     */
    Id getReferenceAtIndex(MultivaluedFeatureKey key);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    Id setReferenceAtIndex(MultivaluedFeatureKey key, Id id);

    /**
     *
     *
     * @param key
     */
    void unsetReferenceAtIndex(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    boolean hasReferenceAtIndex(FeatureKey key);

    /**
     *
     *
     * @param key
     * @param id
     */
    void addReference(MultivaluedFeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @return
     */
    Id removeReference(MultivaluedFeatureKey key);

    /**
     *
     *
     * @param key
     */
    void cleanReference(FeatureKey key);

    /**
     *
     *
     * @param key
     * @return
     */
    Iterable<Id> referenceAsList(FeatureKey key);

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
     * @param id
     * @return
     */
    int indexOfReference(FeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @param id
     * @return
     */
    int lastIndexOfReference(FeatureKey key, Id id);

    /**
     *
     *
     * @param key
     * @return
     */
    Iterable<Id> referenceAtIndexAsList(FeatureKey key);

    //endregion

    /**
     *
     *
     * @param key
     * @return
     */
    int sizeOf(FeatureKey key);
}
