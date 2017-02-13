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
 *
 */
public interface MultiValueMapping {

    @Nonnull
    <V> Optional<V> valueOf(MultiFeatureKey key);

    @Nonnull
    <V> Optional<V> valueFor(MultiFeatureKey key, V value);

    <V> void unsetAllValues(SingleFeatureKey key);

    <V> boolean hasAnyValue(SingleFeatureKey key);

    <V> void addValue(MultiFeatureKey key, V value);

    @Nonnull
    <V> Optional<V> removeValue(MultiFeatureKey key);

    <V> void cleanValues(SingleFeatureKey key);

    <V> boolean containsValue(SingleFeatureKey key, V value);

    @Nonnull
    <V> OptionalInt indexOfValue(SingleFeatureKey key, V value);

    @Nonnull
    <V> OptionalInt lastIndexOfValue(SingleFeatureKey key, V value);

    @Nonnull
    <V> Iterable<V> valuesAsList(SingleFeatureKey key);

    @Nonnull
    <V> OptionalInt sizeOfValue(SingleFeatureKey key);
}
