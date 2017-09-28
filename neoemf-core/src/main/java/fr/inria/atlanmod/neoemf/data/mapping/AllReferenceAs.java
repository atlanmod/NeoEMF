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
