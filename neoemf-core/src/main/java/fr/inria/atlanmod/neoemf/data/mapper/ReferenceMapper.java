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

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface ReferenceMapper extends ValueMapper {

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    default Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key);
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
    default Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id);
    }

    /**
     * ???
     *
     * @param key ???
     */
    default void unsetReference(FeatureKey key) {
        unsetValue(key);
    }

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    default boolean hasReference(FeatureKey key) {
        return hasValue(key);
    }
}
