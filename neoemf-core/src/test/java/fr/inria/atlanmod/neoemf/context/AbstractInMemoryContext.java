/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the core.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractInMemoryContext extends AbstractLocalContext {

    @Nonnull
    @Override
    public String name() {
        return "In-Memory";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new InMemoryBackendFactory();
    }

    @Override
    public boolean isPersistent() {
        return false;
    }
}
