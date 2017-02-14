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

import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface SingleValueMapping {

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    @Nonnull
    <V> Optional<V> valueOf(SingleFeatureKey key);

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
    <V> Optional<V> valueFor(SingleFeatureKey key, V value);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     */
    <V> void unsetValue(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param <V> ???
     *
     * @return ???
     */
    <V> boolean hasValue(SingleFeatureKey key);
}
