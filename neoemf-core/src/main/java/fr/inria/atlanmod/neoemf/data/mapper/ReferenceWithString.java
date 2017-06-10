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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A {@link ReferenceMapper} that provides a default behavior to use {@link String} instead of {@link Id} for
 * references. This behavior is specified by the {@link #toString(Id)} and {@link #fromString(String)} methods.
 */
@ParametersAreNonnullByDefault
public interface ReferenceWithString extends ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(this::fromString);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return this.valueFor(key, toString(reference))
                .map(this::fromString);
    }

    /**
     * Converts a reference as a {@link String}.
     *
     * @param value the value of convert
     *
     * @return the converted reference
     */
    default String toString(Id value) {
        return checkNotNull(value).toString();
    }

    /**
     * Converts a reference as an {@link Id}.
     *
     * @param value the value to convert
     *
     * @return the converted reference
     */
    default Id fromString(String value) {
        return StringId.of(checkNotNull(value));
    }
}
