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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface MultiReferenceMapping {

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    Optional<Id> referenceOf(MultiFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    Optional<Id> referenceFor(MultiFeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     */
    void unsetAllReferences(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    boolean hasAnyReference(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     */
    void addReference(MultiFeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    Optional<Id> removeReference(MultiFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     */
    void cleanReferences(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    boolean containsReference(SingleFeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    OptionalInt indexOfReference(SingleFeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    OptionalInt lastIndexOfReference(SingleFeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    Iterable<Id> referencesAsList(SingleFeatureKey key);

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    OptionalInt sizeOfReference(SingleFeatureKey key);
}
