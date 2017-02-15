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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface MultiReferenceMapper extends ReferenceMapper, MultiValueMapper {

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    default Optional<Id> referenceOf(MultiFeatureKey key) {
        return valueOf(key);
    }

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    default Iterable<Id> allReferencesOf(FeatureKey key) {
        return allValuesOf(key);
    }

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    default Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
        return valueFor(key, id);
    }

    /**
     * ???
     *
     * @param key ???
     */
    default void unsetAllReferences(FeatureKey key) {
        unsetAllValues(key);
    }

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    default boolean hasAnyReference(FeatureKey key) {
        return hasAnyValue(key);
    }

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     */
    default void addReference(MultiFeatureKey key, Id id) {
        addValue(key, id);
    }

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     */
    default void appendReference(FeatureKey key, Id id) {
        addReference(key.withPosition(sizeOfReference(key).orElse(0)), id);
    }

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    default Optional<Id> removeReference(MultiFeatureKey key) {
        return removeValue(key);
    }

    /**
     * ???
     *
     * @param key ???
     */
    default void removeAllReferences(FeatureKey key) {
        removeAllValues(key);
    }

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    default boolean containsReference(FeatureKey key, Id id) {
        return containsValue(key, id);
    }

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    default OptionalInt indexOfReference(FeatureKey key, Id id) {
        return indexOfValue(key, id);
    }

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    default OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return lastIndexOfValue(key, id);
    }

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    default OptionalInt sizeOfReference(FeatureKey key) {
        return sizeOfValue(key);
    }
}
