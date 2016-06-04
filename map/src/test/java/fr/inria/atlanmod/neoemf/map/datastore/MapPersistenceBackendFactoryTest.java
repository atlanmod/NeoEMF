/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.map.datastore;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.AutocommitMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.CachedManyDirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceWithListsEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapWithIndexesResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.map.resources.MapResourceOptions;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class MapPersistenceBackendFactoryTest extends AllTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "mapPersistenceBackendFactoryTest";

    private AbstractPersistenceBackendFactory persistenceBackendFactory;
    private File testFolder;
    private File testFile;
    private Map<Object, Object> options = new HashMap<>();
    private List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();

    @Before
    public void setUp() {
        persistenceBackendFactory = new MapPersistenceBackendFactory();
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
        assertThat("Invalid backend created", transientBackend, instanceOf(MapPersistenceBackend.class));
        // Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();

        SearcheableResourceEStore eStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteMapResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapResourceEStoreImpl) eStore);
        assertThat(innerBackend, sameInstance(transientBackend));
    }

    @Test
    public void testCreatePersistentBackendNoOption() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());
        assertThat("Invalid backend created", persistentBackend, instanceOf(MapPersistenceBackend.class));
        // Need to test further the nature of the MapDB engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, Collections.emptyMap());
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteMapResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapResourceEStoreImpl) eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteMapResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapResourceEStoreImpl) eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithListsOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE_WITH_LISTS);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteMapResourceWithListsEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapResourceWithListsEStoreImpl) eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithIndexesOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE_WITH_INDEXES);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteMapWithIndexesResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapWithIndexesResourceEStoreImpl) eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.AUTOCOMMIT);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invaild EStore created", eStore, instanceOf(AutocommitMapResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapResourceEStoreImpl) eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreCachedManyOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(MapResourceOptions.EStoreMapOption.CACHED_MANY);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invalid EStore created", eStore, instanceOf(CachedManyDirectWriteMapResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend((DirectWriteMapResourceEStoreImpl) eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    /**
     * Test if {@link AbstractPersistenceBackendFactory#copyBackend} creates the persistent
     * data stores from the transient ones. Only empty backends are tested.
     *
     * @throws InvalidDataStoreException
     */
    @Test
    public void testCopyBackend() throws InvalidDataStoreException {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        assertThat("Transient backend is not an instance of MapPersistenceBackend", transientBackend, instanceOf(MapPersistenceBackend.class));

        MapPersistenceBackend transientMap = (MapPersistenceBackend) transientBackend;
        //SearcheableResourceEStore transientEStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());
        assertThat("Persistent backend is not an instance of MapPersistenceBackend", persistentBackend, instanceOf(MapPersistenceBackend.class));

        MapPersistenceBackend persistentMap = (MapPersistenceBackend) persistentBackend;
        //SearcheableResourceEStore persistentEStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        persistenceBackendFactory.copyBackend(transientMap, persistentMap);
        for (String tKey : transientMap.getAll().keySet()) {
            assertThat("Persistent backend does not contain the key " + tKey, persistentMap.getAll(), hasKey(tKey));
            assertThat("Persistent backend structure " + tKey + " is not equal to transient one", persistentMap.getAll().get(tKey), is(transientMap.get(tKey)));
        }
    }

    private PersistenceBackend getInnerBackend(DirectWriteMapResourceEStoreImpl store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field mapStoreField = FieldUtils.getField(DirectWriteMapResourceEStoreImpl.class, "db", true);
        return (PersistenceBackend) mapStoreField.get(store);
    }

    private PersistenceBackend getInnerBackend(DirectWriteMapResourceWithListsEStoreImpl store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field mapStoreField = FieldUtils.getField(DirectWriteMapResourceWithListsEStoreImpl.class, "db", true);
        return (PersistenceBackend) mapStoreField.get(store);
    }

    private PersistenceBackend getInnerBackend(DirectWriteMapWithIndexesResourceEStoreImpl store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field mapStoreField = FieldUtils.getField(DirectWriteMapWithIndexesResourceEStoreImpl.class, "db", true);
        return (PersistenceBackend) mapStoreField.get(store);
    }
}
