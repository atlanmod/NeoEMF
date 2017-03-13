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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapDbBackendFactoryTest extends AbstractBackendFactoryTest implements MapDbTest {

    @Test
    public void testCreateTransientBackend() {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(MapDbBackend.class);
    }

    @Test
    public void testCreateTransientStore() {
        Backend backend = context().factory().createTransientBackend();

        Store store = context().factory().createTransientStore(null, backend);
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentBackend() {
        Backend backend = context().factory().createPersistentBackend(context().createFileURI(file()), MapDbOptions.noOption());
        assertThat(backend).isInstanceOf(MapDbBackend.class);
    }

    @Test
    public void testCreatePersistentStore() {
        Backend backend = context().factory().createPersistentBackend(context().createFileURI(file()), MapDbOptions.noOption());

        Store store = context().factory().createPersistentStore(null, backend, MapDbOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    /**
     * Checks if {@link Backend#copyTo} creates the persistent datastores from the transient ones.
     * Only empty back-ends are tested.
     */
    @Test
    public void testCopyBackend() {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapDbBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createFileURI(file()), MapDbOptions.noOption());
        assertThat(persistentBackend).isInstanceOf(MapDbBackend.class);

        transientBackend.copyTo(persistentBackend);
    }
}
