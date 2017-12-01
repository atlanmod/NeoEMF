/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * A {@link ReferenceMapper} that provides a default behavior to use {@link M} instead of {@link Id} for references.
 *
 * @param <M> the type of the reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ReferenceAs<M> extends ValueMapper, ReferenceMapper {

    @Nonnull
    @Override
    default Maybe<Id> referenceOf(SingleFeatureBean key) {
        Converter<Id, M> converter = referenceConverter();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .cache();
    }

    @Nonnull
    @Override
    default Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        Converter<Id, M> converter = referenceConverter();

        return this.valueFor(key, converter.convert(reference))
                .map(converter::revert)
                .cache();
    }

    @Nonnull
    @Override
    default Completable removeReference(SingleFeatureBean key) {
        return this.removeValue(key);
    }

    /**
     * Returns the converter used to transform a reference to the desired type.
     *
     * @return the converter
     */
    @Nonnull
    Converter<Id, M> referenceConverter();
}
