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
public interface MultiReferenceMapper extends MultiValueMapper {

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
     *
     * @return ???
     */
    @Nonnull
    Iterable<Id> allReferencesOf(FeatureKey key);

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
    void unsetAllReferences(FeatureKey key);

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    boolean hasAnyReference(FeatureKey key);

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
    void cleanReferences(FeatureKey key);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    boolean containsReference(FeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    OptionalInt indexOfReference(FeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     * @param id  ???
     *
     * @return ???
     */
    @Nonnull
    OptionalInt lastIndexOfReference(FeatureKey key, Id id);

    /**
     * ???
     *
     * @param key ???
     *
     * @return ???
     */
    @Nonnull
    OptionalInt sizeOfReference(FeatureKey key);
}
