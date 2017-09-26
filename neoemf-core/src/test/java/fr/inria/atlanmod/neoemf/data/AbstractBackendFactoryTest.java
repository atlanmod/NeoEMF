/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract test-cases about {@link BackendFactory} and its implementations.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactoryTest extends AbstractUnitTest {

    /**
     * Check the creation of chaining between the default {@link Store}s and a {@link TransientBackend}.
     */
    @Test
    public void testCreateTransientStore() {
        Backend backend = context().factory().createTransientBackend();

        Store store = StoreFactory.getInstance().createStore(backend, context().optionsBuilder().asMap());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }

    /**
     * Check the creation of chaining between the default {@link Store}s and a {@link PersistentBackend}.
     */
    @Test
    public void testCreatePersistentStore() {
        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), context().optionsBuilder().asMap());

        Store store = StoreFactory.getInstance().createStore(backend, context().optionsBuilder().asMap());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }

    /**
     * Checks the creation of a {@link TransientBackend}, specific for each implementation.
     */
    @Test
    public abstract void testCreateTransientBackend();

    /**
     * Checks the creation of the default {@link PersistentBackend}, specific for each implementation.
     */
    @Test
    public abstract void testCreateDefaultPersistentBackend();

    /**
     * Checks the copy of a {@link Backend} to another.
     */
    @Test
    public abstract void testCopyBackend();
}
