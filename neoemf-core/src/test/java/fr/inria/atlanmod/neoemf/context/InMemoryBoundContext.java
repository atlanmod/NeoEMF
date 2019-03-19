/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.im.BoundInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A {@link InMemoryDefaultContext} bound to a unique {@link Id}.
 */
@ParametersAreNonnullByDefault
public class InMemoryBoundContext extends InMemoryDefaultContext {

    /**
     * The {@link Id} bound to this context.
     */
    @Nonnull
    private final Id id;

    /**
     * Constructs a new {@code InMemoryBoundContext}.
     *
     * @param id the id bound to this context
     */
    public InMemoryBoundContext(Id id) {
        this.id = id;
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        BackendFactory factory = mock(InMemoryBackendFactory.class);
        when(factory.supportsTransient()).thenCallRealMethod();
        when(factory.createBackend(any(URI.class), any(ImmutableConfig.class))).thenAnswer((i) -> new BoundInMemoryBackend(id));
        return factory;
    }
}
