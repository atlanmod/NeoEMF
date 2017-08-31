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

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ReferenceMapper} that provides a default behavior to use {@link M} instead of {@link Id} for references.
 * This behavior is specified by the {@link #referenceConverter()} method.
 *
 * @param <M> the type of the reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ReferenceWith<M> extends ValueMapper, ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(SingleFeatureBean key) {
        Converter<Id, M> func = referenceConverter();

        return this.<M>valueOf(key)
                .map(func::doBackward);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        Converter<Id, M> func = referenceConverter();

        return this.<M>valueFor(key, func.doForward(reference))
                .map(func::doBackward);
    }

    @Override
    default void unsetReference(SingleFeatureBean key) {
        unsetValue(key);
    }

    @Override
    default boolean hasReference(SingleFeatureBean key) {
        return referenceOf(key).isPresent();
    }

    @Nonnull
    Converter<Id, M> referenceConverter();
}
