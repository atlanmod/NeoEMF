/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.AbstractIdConverter;
import fr.inria.atlanmod.neoemf.core.Id;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link fr.inria.atlanmod.neoemf.core.IdConverter} to use a long representation instead of {@link Id}.
 */
@ParametersAreNonnullByDefault
public class LongIdConverter extends AbstractIdConverter<Long> {

    @Override
    public Long convert(Id id) {
        return id.toLong();
    }

    @Override
    public Id revert(Long aLong) {
        return provider.fromLong(aLong);
    }
}
