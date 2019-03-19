/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.context;

import fr.inria.atlanmod.neoemf.context.AbstractLocalContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the BerkeleyDB implementation.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBerkeleyDbContext extends AbstractLocalContext {

    @Nonnull
    @Override
    public String name() {
        return "BerkeleyDB";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new BerkeleyDbBackendFactory();
    }
}
