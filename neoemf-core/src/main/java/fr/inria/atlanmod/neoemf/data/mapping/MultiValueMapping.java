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

import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface MultiValueMapping {

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> Optional<V> valueOf(MultiFeatureKey key);

    /**
     * ???
     *
     * @param key   ???
     * @param value ???
     * @param <V>   ???
     *
     * @return ???
     */
    @Nonnull
    <V> Optional<V> valueFor(MultiFeatureKey key, V value);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     */
    <V> void unsetAllValues(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    <V> boolean hasAnyValue(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key   ???
     * @param value ???
     * @param <V>   ???
     */
    <V> void addValue(MultiFeatureKey key, V value);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> Optional<V> removeValue(MultiFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     */
    <V> void cleanValues(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key   ???
     * @param value ???
     * @param <V>   ???
     *
     * @return ???
     */
    <V> boolean containsValue(SingleFeatureKey key, V value);

    /**
     * ???
     *
     * @param key   ???
     * @param value ???
     * @param <V>   ???
     *
     * @return ???
     */
    @Nonnull
    <V> OptionalInt indexOfValue(SingleFeatureKey key, V value);

    /**
     * ???
     *
     * @param key   ???
     * @param value ???
     * @param <V>   ???
     *
     * @return ???
     */
    @Nonnull
    <V> OptionalInt lastIndexOfValue(SingleFeatureKey key, V value);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> Iterable<V> valuesAsList(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> OptionalInt sizeOfValue(SingleFeatureKey key);
}
