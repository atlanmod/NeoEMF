/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that create {@link fr.inria.atlanmod.neoemf.data.im.InMemoryBackend}
 * instances.
 */
@ParametersAreNonnullByDefault
public class InMemoryBackendFactory extends AbstractBackendFactory<InMemoryConfig> {

    /**
     * The literal description of the factory.
     */
    private static final String NAME = "im"; // In-Memory

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static BackendFactory getInstance() {
        return new InMemoryBackendFactory();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean supportsTransient() {
        return true;
    }

    @Nonnull
    @Override
    public Backend createBackend(URI uri, ImmutableConfig baseConfig) {
        return new DefaultInMemoryBackend();
    }
}
