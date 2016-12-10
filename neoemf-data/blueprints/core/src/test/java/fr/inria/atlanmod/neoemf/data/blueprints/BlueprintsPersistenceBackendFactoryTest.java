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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.data.AllPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsCacheManyStore;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.junit.Test;

import java.util.Map;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class BlueprintsPersistenceBackendFactoryTest extends AllPersistenceBackendFactoryTest {

    @Override
    protected String name() {
        return "Blueprints";
    }

    @Override
    protected String uriScheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory persistenceBackendFactory() {
        return BlueprintsPersistenceBackendFactory.getInstance();
    }

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = persistenceBackendFactory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BlueprintsPersistenceBackend.class); // "Invalid backend created"

        // TODO Need to test further the nature of the Blueprints engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, IllegalAccessException {
        PersistenceBackend transientBackend = persistenceBackendFactory().createTransientBackend();

        PersistentStore eStore = persistenceBackendFactory().createTransientStore(null, transientBackend);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, transientBackend);
    }

    @Test
    public void testCreatePersistentBackendNoOptionNoConfigFile() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(BlueprintsPersistenceBackend.class); // "Invalid backend created"

        // TODO Need to test further the nature of the Blueprints engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, InvalidOptionsException, NoSuchFieldException, IllegalAccessException {
        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, BlueprintsOptionsBuilder.newBuilder().asMap());
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, InvalidOptionsException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreManyCacheOption() throws InvalidDataStoreException, InvalidOptionsException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsCacheManyStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionNoBase() throws InvalidDataStoreException, InvalidOptionsException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionDirectWriteBase() throws InvalidDataStoreException, InvalidOptionsException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWrite()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionCachedManyBase() throws InvalidDataStoreException, InvalidOptionsException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }
}
