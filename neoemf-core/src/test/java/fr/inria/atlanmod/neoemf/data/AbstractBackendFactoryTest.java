/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackend;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;
import fr.inria.atlanmod.neoemf.data.im.util.InMemoryUri;
import fr.inria.atlanmod.neoemf.data.store.NoopStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract test-cases about {@link BackendFactory} and its implementations.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactoryTest extends AbstractUnitTest {

    /**
     * Check the creation of chaining between the default {@link Store}s and a {@link InMemoryBackend}.
     */
    @Test
    public void testCreateTransientStore() throws IOException {
        try (Backend backend = InMemoryBackendFactory.getInstance().createBackend(InMemoryUri.builder().fromFile(currentTempFile()), InMemoryConfig.newConfig())) {
            try (Store store = StoreFactory.getInstance().createStore(backend, context().config())) {
                assertThat(store).isInstanceOf(NoopStore.class);

                assertThat(store.backend()).isSameAs(backend);
            }
        }
    }

    /**
     * Check the creation of chaining between the default {@link Store}s and a {@link Backend}.
     */
    @Test
    public void testCreatePersistentStore() throws IOException {
        try (Backend backend = context().factory().createBackend(context().createUri(currentTempFile()), context().config())) {
            try (Store store = StoreFactory.getInstance().createStore(backend, context().config())) {
                assertThat(store).isInstanceOf(NoopStore.class);

                assertThat(store.backend()).isSameAs(backend);
            }
        }
    }

    /**
     * Checks the copy of a {@link Backend} to another.
     */
    @Test
    public void testCopyBackend() throws IOException {
        ImmutableConfig config = context().config();

        File file = currentTempFile();
        try (Backend transientBackend = InMemoryBackendFactory.getInstance().createBackend(InMemoryUri.builder().fromFile(file), InMemoryConfig.newConfig())) {
            try (Backend persistentBackend = context().factory().createBackend(context().createUri(currentTempFile()), config)) {
                transientBackend.copyTo(persistentBackend);
            }
        }
    }
}
