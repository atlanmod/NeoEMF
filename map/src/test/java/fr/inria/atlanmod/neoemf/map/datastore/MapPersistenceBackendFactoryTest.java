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

package fr.inria.atlanmod.neoemf.map.datastore;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.DirectWriteMapCacheManyStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.DirectWriteMapIndicesStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.DirectWriteMapListsStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.impl.DirectWriteMapStore;
import fr.inria.atlanmod.neoemf.map.option.MapOptionsBuilder;
import fr.inria.atlanmod.neoemf.map.util.MapURI;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest {

    private static final String TEST_FILENAME = "mapPersistenceBackendFactoryTest";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File testFolder;
    private File testFile;

    private PersistenceBackendFactory persistenceBackendFactory;

    @Before
    public void setUp() {
        persistenceBackendFactory = MapPersistenceBackendFactory.getInstance();
        PersistenceBackendFactoryRegistry.register(MapURI.SCHEME, persistenceBackendFactory);
        testFolder = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
        try {
            Files.createDirectories(testFolder.toPath());
        }
        catch (IOException e) {
            NeoLogger.error(e);
        }
        testFile = new File(testFolder + "/db");
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();

        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            }
            catch (IOException e) {
                NeoLogger.warn(e);
            }
        }

        testFolder = null;
        testFile = null;
    }

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapPersistenceBackend.class); // "Invalid backend created"
        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();

        PersistentStore eStore = persistenceBackendFactory.createTransientStore(null, transientBackend);
        assertThat(eStore).isInstanceOf(DirectWriteMapStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, transientBackend);
    }

    @Test
    public void testCreatePersistentBackendNoOption() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(MapPersistenceBackend.class); // "Invalid backend created"
        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, MapOptionsBuilder.newBuilder().asMap());
        assertThat(eStore).isInstanceOf(DirectWriteMapStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithListsOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapOptionsBuilder.newBuilder()
                .directWriteLists()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapListsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithIndexesOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapOptionsBuilder.newBuilder()
                .directWriteIndices()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapIndicesStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreCachedManyOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidOptionsException {
        Map<String, Object> options = MapOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapCacheManyStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    /**
     * Test if {@link PersistenceBackendFactory#copyBackend} creates the persistent data stores from the transient ones.
     * Only empty backends are tested.
     */
    @Test
    public void testCopyBackend() throws InvalidDataStoreException {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        assertThat(transientBackend).isInstanceOf(MapPersistenceBackend.class); // "Transient backend is not an instance of MapPersistenceBackend"

        MapPersistenceBackend transientMap = (MapPersistenceBackend) transientBackend;
        //PersistentStore transientEStore = persistenceBackendFactory.createTransientStore(null, transientBackend);
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, MapOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(MapPersistenceBackend.class); // "Persistent backend is not an instance of MapPersistenceBackend"

        MapPersistenceBackend persistentMap = (MapPersistenceBackend) persistentBackend;
        //PersistentStore persistentEStore = persistenceBackendFactory.createPersistentStore(null, persistentBackend, options);
        persistenceBackendFactory.copyBackend(transientMap, persistentMap);
        for (String tKey : transientMap.getAll().keySet()) {
            assertThat(persistentMap.getAll()).containsKey(tKey); // "Persistent backend does not contain the key"
            assertThat(persistentMap.getAll().get(tKey)).isEqualTo(transientMap.get(tKey)); // "Persistent backend structure %s is not equal to transient one"
        }
    }

    private void assertHasInnerBackend(PersistentStore store, PersistenceBackend expectedInnerBackend) {
        PersistenceBackend innerBackend = getInnerBackend(store);
        assertThat(innerBackend).isSameAs(expectedInnerBackend); // "The backend in the EStore is not the created one"
    }
}
