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
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ReferenceMapper} that provides a default behavior to use {@link T} instead of {@link Id} for
 * references. This behavior is specified by the {@link #mapReference(Id)} and {@link #unmapReference(T)} methods.
 */
@ParametersAreNonnullByDefault
public interface ReferenceWith<T> extends ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(SingleFeatureKey key) {
        return this.<T>valueOf(key)
                .map(this::unmapReference);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        return this.<T>valueFor(key, mapReference(reference))
                .map(this::unmapReference);
    }

    @Nonnull
    T mapReference(Id value);

    @Nonnull
    Id unmapReference(T value);
}
