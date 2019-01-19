/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.atlanmod.commons.function.Converter;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ReferenceMapper} that provides a default behavior to use {@code M} instead of {@link Id} for references.
 *
 * @param <M> the type of the reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ReferenceAs<M> extends ValueMapper, ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(SingleFeatureBean feature) {
        Converter<Id, M> converter = referenceConverter();

        return this.<M>valueOf(feature)
                .map(converter::revert);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        Converter<Id, M> converter = referenceConverter();

        return this.valueFor(feature, converter.convert(reference))
                .map(converter::revert);
    }

    @Override
    default void removeReference(SingleFeatureBean feature) {
        this.removeValue(feature);
    }

    /**
     * Returns the converter used to transform a reference to the desired type.
     *
     * @return the converter
     */
    @Nonnull
    Converter<Id, M> referenceConverter();
}
