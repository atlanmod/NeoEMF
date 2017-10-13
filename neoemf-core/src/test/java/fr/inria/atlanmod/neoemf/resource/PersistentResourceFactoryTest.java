/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.TransientBackend;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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

        Backend backend = mock(TransientBackend.class);

        BackendFactory backendFactory = mock(BackendFactory.class);
        when(backendFactory.createTransientBackend()).thenReturn(backend);

        BackendFactoryRegistry.getInstance().register(uri.scheme(), backendFactory);

        PersistentResource resource = resourceFactory.createResource(uri);
        assertThat(resource).isNotNull();
        assertThat(resource.getURI()).isSameAs(uri);
        assertThat(resource.eStore()).isNotNull();
        assertThat(resource.eStore().store().backend()).isSameAs(backend);

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