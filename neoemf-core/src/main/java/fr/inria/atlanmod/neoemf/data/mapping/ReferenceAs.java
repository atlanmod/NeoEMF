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
import fr.inria.atlanmod.neoemf.core.StringId;
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
public interface ReferenceAs<M> extends ValueMapper, ReferenceMapper {

    /**
     * The default converter that simply returns the original {@link Id}.
     */
    @Nonnull
    Converter<Id, Id> IDENTITY_CONVERTER = Converter.identity();

    /**
     * The default converter to use {@link String} instead of {@link Id}.
     */
    @Nonnull
    Converter<Id, String> DEFAULT_CONVERTER = Converter.from(Id::toString, StringId::of);

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

    @Override
    default boolean hasReference(SingleFeatureBean key) {
        return this.<M>hasValue(key);
    }

    @Nonnull
    Converter<Id, M> referenceConverter();
}
