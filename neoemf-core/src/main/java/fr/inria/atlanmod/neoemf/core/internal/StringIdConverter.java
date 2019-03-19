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
 * An {@link fr.inria.atlanmod.neoemf.core.IdConverter} to use a hexadecimal representation instead of {@link Id}.
 */
@ParametersAreNonnullByDefault
public class StringIdConverter extends AbstractIdConverter<String> {

    @Override
    public String convert(Id id) {
        return id.toHexString();
    }

    @Override
    public Id revert(String s) {
        return provider.fromHexString(s);
    }
}
