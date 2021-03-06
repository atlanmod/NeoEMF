/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbIndicesContext;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactoryTest;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BerkeleyDbUriFactory}.
 */
@ParametersAreNonnullByDefault
class BerkeleyDbUriFactoryTest extends AbstractUriFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return new BerkeleyDbIndicesContext();
    }
}
