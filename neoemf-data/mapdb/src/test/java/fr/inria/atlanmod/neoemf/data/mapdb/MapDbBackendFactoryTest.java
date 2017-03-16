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
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbTest;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.CommonOptions;

import org.junit.Test;

import java.util.Map;

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

        //noinspection ConstantConditions
        Store store = context().factory().createStore(backend, null, CommonOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }

    @Test
    public void testCreateDefaultPersistentBackend() {
        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), MapDbOptions.noOption());
        assertThat(backend).isInstanceOf(MapDbBackendIndices.class);
    }

    @Test
    public void testCreateIndicesPersistentBackend() {
        Map<String, Object> options = MapDbOptions.newBuilder()
                .withIndices()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), options);
        assertThat(backend).isInstanceOf(MapDbBackendIndices.class);
    }

    @Test
    public void testCreateArraysPersistentBackend() {
        Map<String, Object> options = MapDbOptions.newBuilder()
                .withArrays()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), options);
        assertThat(backend).isInstanceOf(MapDbBackendArrays.class);
    }

    @Test
    public void testCreateListsPersistentBackend() {
        Map<String, Object> options = MapDbOptions.newBuilder()
                .withLists()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), options);
        assertThat(backend).isInstanceOf(MapDbBackendLists.class);
    }

    @Test
    public void testCreatePersistentStore() {
        Backend backend = context().factory().createPersistentBackend(context().createUri(file()), MapDbOptions.noOption());

        //noinspection ConstantConditions
        Store store = context().factory().createStore(backend, null, MapDbOptions.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class);

        assertThat(store.backend()).isSameAs(backend);
    }

    /**
     * Checks if {@link Backend#copyTo} creates the persistent datastores from the transient ones.
     * Only empty back-ends are tested.
     */
    @Test
    public void testCopyBackend() {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapDbBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(file()), MapDbOptions.noOption());
        assertThat(persistentBackend).isInstanceOf(MapDbBackend.class);

        transientBackend.copyTo(persistentBackend);
    }
}
