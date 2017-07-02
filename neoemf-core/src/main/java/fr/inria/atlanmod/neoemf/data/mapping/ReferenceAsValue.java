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
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link ReferenceMapper} that processes the references as values with {@link ValueMapper}.
 * <p>
 * All calls are redirected to their equivalent in {@link ValueMapper}.
 */
@ParametersAreNonnullByDefault
public interface ReferenceAsValue extends ValueMapper, ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(SingleFeatureBean key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return valueFor(key, reference);
    }

    @Override
    default void unsetReference(SingleFeatureBean key) {
        unsetValue(key);
    }

    @Override
    default boolean hasReference(SingleFeatureBean key) {
        return hasValue(key);
    }
}
