/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the core.
 */
@ParametersAreNonnullByDefault
public abstract class InMemoryContext extends AbstractLocalContext {

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    @Nonnull
    public static Context get() {
        return new InMemoryContext() {
        };
    }

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

    @Nonnull
    @Override
    public ImmutableConfig config() {
        return new InMemoryConfig();
    }

    @Override
    public boolean isPersistent() {
        return false;
    }
}
