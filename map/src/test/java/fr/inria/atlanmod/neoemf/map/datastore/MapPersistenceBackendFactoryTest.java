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
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AutocommitEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.CachedManyDirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapWithIndexesResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapWithListsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.resources.MapResourceOptions;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "mapPersistenceBackendFactoryTest";

    private PersistenceBackendFactory persistenceBackendFactory;
    private File testFolder;
    private File testFile;
    private final Map<Object, Object> options = new HashMap<>();
    private final List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();

    @Before
    public void setUp() {
        persistenceBackendFactory = MapPersistenceBackendFactory.getInstance();
        PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, persistenceBackendFactory);
        testFolder = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
        try {
            Files.createDirectories(testFolder.toPath());
        } catch (IOException e) {
            NeoLogger.error(e);
        }
        testFile = new File(testFolder + "/db");
        options.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();

        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            } catch (IOException e) {
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

        PersistentEStore eStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        assertThat(eStore).isInstanceOf(DirectWriteMapResourceEStoreImpl.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, transientBackend);
    }

    @Test
    public void testCreatePersistentBackendNoOption() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());
        assertThat(persistentBackend).isInstanceOf(MapPersistenceBackend.class); // "Invalid backend created"
        // TODO Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, Collections.emptyMap());
        assertThat(eStore).isInstanceOf(DirectWriteMapResourceEStoreImpl.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapResourceEStoreImpl.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithListsOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE_WITH_LISTS);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapWithListsResourceEStoreImpl.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithIndexesOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE_WITH_INDEXES);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteMapWithIndexesResourceEStoreImpl.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.AUTOCOMMIT);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitEStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreCachedManyOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.CACHED_MANY);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(CachedManyDirectWriteMapResourceEStoreImpl.class); // "Invalid EStore created"

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
        //PersistentEStore transientEStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());
        assertThat(persistentBackend).isInstanceOf(MapPersistenceBackend.class); // "Persistent backend is not an instance of MapPersistenceBackend"

        MapPersistenceBackend persistentMap = (MapPersistenceBackend) persistentBackend;
        //PersistentEStore persistentEStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        persistenceBackendFactory.copyBackend(transientMap, persistentMap);
        for (String tKey : transientMap.getAll().keySet()) {
            assertThat(persistentMap.getAll()).containsKey(tKey); // "Persistent backend does not contain the key"
            assertThat(persistentMap.getAll().get(tKey)).isEqualTo(transientMap.get(tKey)); // "Persistent backend structure %s is not equal to transient one"
        }
    }

    private void assertHasInnerBackend(PersistentEStore store, PersistenceBackend expectedInnerBackend) {
        PersistenceBackend innerBackend = getInnerBackend(store);
        assertThat(innerBackend).isSameAs(expectedInnerBackend); // "The backend in the EStore is not the created one"
    }
}
