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

package fr.inria.atlanmod.neoemf.graph.blueprints.datastore;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.AutocommitBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteBlueprintsResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class BlueprintsPersistenceBackendFactoryTest extends AllTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "graphPersistenceBackendFactoryTestFile";

    protected AbstractPersistenceBackendFactory persistenceBackendFactory;
    protected File testFile;
    protected Map<Object, Object> options = new HashMap<>();
    protected List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();

    @Before
    public void setUp() {
        persistenceBackendFactory = new BlueprintsPersistenceBackendFactory();
        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
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

        testFile = null;
    }

    protected PersistenceBackend getInnerBackend(EStore store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        assertThat("Invalid call, can not get the inner backend if the given EStore is not a DirectWriteGraphResourceEStoreImpl", store, instanceOf(DirectWriteBlueprintsResourceEStoreImpl.class));
        Field graphStoreField = DirectWriteBlueprintsResourceEStoreImpl.class.getDeclaredField("graph");
        graphStoreField.setAccessible(true);
        return (PersistenceBackend) graphStoreField.get(store);
    }

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        assertThat("Invalid backend created", transientBackend, instanceOf(BlueprintsPersistenceBackend.class));

        BlueprintsPersistenceBackend graph = (BlueprintsPersistenceBackend) transientBackend;
        assertThat("The base graph is not a TinkerGraph", graph.getBaseGraph(), instanceOf(TinkerGraph.class));
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();

        SearcheableResourceEStore eStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteBlueprintsResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(transientBackend));
    }

    @Test
    public void testCreatePersistentBackendNoOptionNoConfigFile() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());
        assertThat("Invalid backend created", persistentBackend, instanceOf(BlueprintsPersistenceBackend.class));

        BlueprintsPersistenceBackend graph = (BlueprintsPersistenceBackend) persistentBackend;
        assertThat("The base graph is not the default TinkerGraph", graph.getBaseGraph(), instanceOf(TinkerGraph.class));
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, Collections.emptyMap());
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteBlueprintsResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assertThat(innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.DIRECT_WRITE);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invalid EStore created", eStore, instanceOf(DirectWriteBlueprintsResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);

        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.emptyMap());

        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assertThat("Invalid EStore created", eStore, instanceOf(AutocommitBlueprintsResourceEStoreImpl.class));

        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assertThat("The backend in the EStore is not the created one", innerBackend, sameInstance(persistentBackend));
    }

}
