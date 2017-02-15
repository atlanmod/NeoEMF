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

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface MultiValueMapper extends ValueMapper {

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
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> Iterable<V> allValuesOf(FeatureKey key);

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
    <V> void unsetAllValues(FeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    <V> boolean hasAnyValue(FeatureKey key);

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
    <V> void cleanValues(FeatureKey key);

    /**
     * ???
     *
     * @param key   ???
     * @param value ???
     * @param <V>   ???
     *
     * @return ???
     */
    <V> boolean containsValue(FeatureKey key, V value);

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
    <V> OptionalInt indexOfValue(FeatureKey key, V value);

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
    <V> OptionalInt lastIndexOfValue(FeatureKey key, V value);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> OptionalInt sizeOfValue(FeatureKey key);
}
