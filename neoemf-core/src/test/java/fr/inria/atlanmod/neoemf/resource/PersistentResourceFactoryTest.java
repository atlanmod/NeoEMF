/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackend;

import org.atlanmod.commons.AbstractTest;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A test-case about {@link PersistentResourceFactory}.
 */
@ParametersAreNonnullByDefault
class PersistentResourceFactoryTest extends AbstractTest {

    @Test
    void createResourceRegistered() {
        PersistentResourceFactory resourceFactory = PersistentResourceFactory.getInstance();

        URI uri = mock(URI.class);
        when(uri.scheme()).thenReturn("mock0");

        Backend backend = mock(InMemoryBackend.class);

        BackendFactory backendFactory = mock(BackendFactory.class);
        when(backendFactory.createBackend(any(URI.class), any(ImmutableConfig.class))).thenReturn(backend);

        BackendFactoryRegistry.getInstance().register(uri.scheme(), backendFactory);

        PersistentResource resource = resourceFactory.createResource(uri);
        assertThat(resource).isNotNull();
        assertThat(resource.getURI()).isSameAs(uri);
        assertThat(resource.eStore()).isNotNull();

        BackendFactoryRegistry.getInstance().unregister(uri.scheme());
    }

    @Test
    void createResourceNotRegistered() {
        PersistentResourceFactory factory = PersistentResourceFactory.getInstance();

        URI uri = mock(URI.class);
        when(uri.scheme()).thenReturn("mock0");

        BackendFactoryRegistry.getInstance().unregister(uri.scheme());

        assertThat(
                catchThrowable(() -> factory.createResource(uri))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}