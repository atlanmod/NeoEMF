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

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbListsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapDbPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements MapDbTest {

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(backend).isInstanceOf(MapDbPersistenceBackend.class); // "Invalid back-end created"

        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();

        PersistentStore store = context().persistenceBackendFactory().createTransientStore(null, backend);
        assertThat(store).isInstanceOf(DirectWriteMapDbStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentBackendNoOption() throws InvalidDataStoreException {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());
        assertThat(backend).isInstanceOf(MapDbPersistenceBackend.class); // "Invalid back-end created"

        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, MapDbOptionsBuilder.newBuilder().asMap());
        assertThat(store).isInstanceOf(DirectWriteMapDbStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapDbStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithListsOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWriteLists()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapDbListsStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithIndexesOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWriteIndices()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapDbIndicesStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreCachedManyOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapDbCacheManyStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    /**
     * Test if {@link PersistenceBackendFactory#copyBackend} creates the persistent datastores from the transient ones.
     * Only empty back-ends are tested.
     */
    @Test
    public void testCopyBackend() throws InvalidDataStoreException {
        PersistenceBackend transientBackend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapDbPersistenceBackend.class); // "Transient back-end is not an instance of MapDbPersistenceBackend"
        MapDbPersistenceBackend transientMap = (MapDbPersistenceBackend) transientBackend;

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(MapDbPersistenceBackend.class); // "Persistent back-end is not an instance of MapDbPersistenceBackend"

        MapDbPersistenceBackend persistentMap = (MapDbPersistenceBackend) persistentBackend;

        context().persistenceBackendFactory().copyBackend(transientMap, persistentMap);

        for (String tKey : transientMap.getAll().keySet()) {
            assertThat(persistentMap.getAll()).containsKey(tKey); // "Persistent back-end does not contain the key"
            assertThat(persistentMap.getAll().get(tKey)).isEqualTo(transientMap.get(tKey)); // "Persistent back-end structure %s is not equal to transient one"
        }
    }
}
