/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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
import fr.inria.atlanmod.neoemf.data.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbListsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapDbPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest {

    @Override
    protected String name() {
        return "MapDb";
    }

    @Override
    protected String uriScheme() {
        return MapDbURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory persistenceBackendFactory() {
        return MapDbPersistenceBackendFactory.getInstance();
    }

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = persistenceBackendFactory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapDbPersistenceBackend.class); // "Invalid backend created"

        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend transientBackend = persistenceBackendFactory().createTransientBackend();

        PersistentStore eStore = persistenceBackendFactory().createTransientStore(null, transientBackend);
        assertThat(eStore).isInstanceOf(DirectWriteMapDbStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, transientBackend);
    }

    @Test
    public void testCreatePersistentBackendNoOption() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(MapDbPersistenceBackend.class); // "Invalid backend created"

        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, MapDbOptionsBuilder.newBuilder().asMap());
        assertThat(eStore).isInstanceOf(DirectWriteMapDbStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapDbStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithListsOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWriteLists()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapDbListsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithIndexesOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWriteIndices()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapDbIndicesStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreCachedManyOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapDbOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapDbCacheManyStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    /**
     * Test if {@link PersistenceBackendFactory#copyBackend} creates the persistent data stores from the transient ones.
     * Only empty backends are tested.
     */
    @Test
    public void testCopyBackend() throws InvalidDataStoreException {
        PersistenceBackend transientBackend = persistenceBackendFactory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapDbPersistenceBackend.class); // "Transient backend is not an instance of MapDbPersistenceBackend"
        MapDbPersistenceBackend transientMap = (MapDbPersistenceBackend) transientBackend;

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), MapDbOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(MapDbPersistenceBackend.class); // "Persistent backend is not an instance of MapDbPersistenceBackend"

        MapDbPersistenceBackend persistentMap = (MapDbPersistenceBackend) persistentBackend;
        persistenceBackendFactory().copyBackend(transientMap, persistentMap);
        for (String tKey : transientMap.getAll().keySet()) {
            assertThat(persistentMap.getAll()).containsKey(tKey); // "Persistent backend does not contain the key"
            assertThat(persistentMap.getAll().get(tKey)).isEqualTo(transientMap.get(tKey)); // "Persistent backend structure %s is not equal to transient one"
        }
    }
}
