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
 *
 * @param <M> the type of the reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ReferenceAs<M> extends ValueMapper, ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(SingleFeatureBean key) {
        Converter<Id, M> converter = referenceConverter();

        return this.<M>valueOf(key)
                .map(converter::revert);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        Converter<Id, M> converter = referenceConverter();

        return this.<M>valueFor(key, converter.convert(reference))
                .map(converter::revert);
    }

    @Override
    default void removeReference(SingleFeatureBean key) {
        this.<M>removeValue(key);
    }

    /**
     * Returns the converter used to transform a reference to the desired type.
     *
     * @return the converter
     */
    @Nonnull
    Converter<Id, M> referenceConverter();
}
