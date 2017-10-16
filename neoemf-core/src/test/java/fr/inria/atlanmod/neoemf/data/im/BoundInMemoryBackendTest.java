/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.CoreContext;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractPersistenceMapperTest;

import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A test-case about {@link BoundInMemoryBackend}.
 */
@ParametersAreNonnullByDefault
class BoundInMemoryBackendTest extends AbstractPersistenceMapperTest {

    @Nonnull
    @Override
    protected Context context() {
        return new CoreContext() {

            @Nonnull
            @Override
            public BackendFactory factory() {
                BackendFactory factory = mock(InMemoryBackendFactory.class);
                when(factory.name()).thenCallRealMethod();
                when(factory.supportsTransient()).thenCallRealMethod();
                when(factory.createBackend(any(URI.class), any(ImmutableConfig.class))).thenAnswer((i) -> new BoundInMemoryBackend(idBase));
                return factory;
            }
        };
    }
}
