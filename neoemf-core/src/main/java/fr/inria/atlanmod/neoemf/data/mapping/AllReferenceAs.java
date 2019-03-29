/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;

import org.atlanmod.commons.function.Converter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ReferenceMapper} and a {@link ManyReferenceMapper} that converts all references before processing them as
 * values.
 *
 * @param <M> the type of references after mapping
 *
 * @see ReferenceAs
 * @see ManyReferenceAs
 */
@ParametersAreNonnullByDefault
public interface AllReferenceAs<M> extends ReferenceAs<M>, ManyReferenceAs<M> {

    @Nonnull
    @Override
    default Converter<Id, M> manyReferenceConverter() {
        return referenceConverter();
    }
}
