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

package fr.inria.atlanmod.neoemf.graph.blueprints.datastore;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.impl.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.AutocommitEStoreDecorator;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.store.impl.DirectWriteBlueprintsCacheManyEStore;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.store.impl.DirectWriteBlueprintsEStore;
import fr.inria.atlanmod.neoemf.graph.blueprints.resource.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest {

    private static final String TEST_FILENAME = "graphPersistenceBackendFactoryTestFile";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File testFile;

    private PersistenceBackendFactory persistenceBackendFactory;

    @Before
    public void setUp() {
        persistenceBackendFactory = BlueprintsPersistenceBackendFactory.getInstance();
        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
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

        testFile = null;
    }

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BlueprintsPersistenceBackend.class); // "Invalid backend created"

//        BlueprintsPersistenceBackend graph = (BlueprintsPersistenceBackend) transientBackend;
//        assertThat(graph..getBaseGraph()).isInstanceOf(TinkerGraph.class); // "The base graph is not a TinkerGraph"
    }

    @Test
    public void testCreateTransientEStore() {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();

        PersistentEStore eStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsEStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, transientBackend);
    }

    @Test
    public void testCreatePersistentBackendNoOptionNoConfigFile() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(BlueprintsPersistenceBackend.class); // "Invalid backend created"

//        BlueprintsPersistenceBackend graph = (BlueprintsPersistenceBackend) persistentBackend;
//        assertThat(graph.getBaseGraph()).isInstanceOf(TinkerGraph.class); // "The base graph is not the default TinkerGraph"
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, BlueprintsResourceOptions.newBuilder().asMap());
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsEStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException {
        Map<String, Object> options = BlueprintsResourceOptions.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsEStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreManyCacheOption() throws InvalidDataStoreException {
        Map<String, Object> options = BlueprintsResourceOptions.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsCacheManyEStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionNoBase() throws InvalidDataStoreException {
        Map<String, Object> options = BlueprintsResourceOptions.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitEStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionDirectWriteBase() throws InvalidDataStoreException {
        Map<String, Object> options = BlueprintsResourceOptions.newBuilder()
                .directWrite()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitEStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionCachedManyBase() throws InvalidDataStoreException {
        Map<String, Object> options = BlueprintsResourceOptions.newBuilder()
                .directWriteCacheMany()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, BlueprintsResourceOptions.newBuilder().asMap());

        PersistentEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitEStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    private void assertHasInnerBackend(PersistentEStore store, PersistenceBackend expectedInnerBackend) {
        PersistenceBackend innerBackend = getInnerBackend(store);
        assertThat(innerBackend).isSameAs(expectedInnerBackend); // "The backend in the EStore is not the created one"
    }
}
