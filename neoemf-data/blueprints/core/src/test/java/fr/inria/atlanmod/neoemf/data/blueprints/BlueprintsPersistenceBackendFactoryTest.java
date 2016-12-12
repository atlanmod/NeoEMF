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

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsCacheManyStore;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements BlueprintsTest {

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BlueprintsPersistenceBackend.class); // "Invalid backend created"

        // TODO Need to test further the nature of the Blueprints engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, IllegalAccessException {
        PersistenceBackend transientBackend = context().persistenceBackendFactory().createTransientBackend();

        PersistentStore eStore = context().persistenceBackendFactory().createTransientStore(null, transientBackend);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, transientBackend);
    }

    @Test
    public void testCreatePersistentBackendNoOptionNoConfigFile() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(BlueprintsPersistenceBackend.class); // "Invalid backend created"

        // TODO Need to test further the nature of the Blueprints engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, InvalidOptionException, NoSuchFieldException, IllegalAccessException {
        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = context().persistenceBackendFactory().createPersistentStore(null, persistentBackend, BlueprintsOptionsBuilder.newBuilder().asMap());
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, InvalidOptionException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = context().persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreManyCacheOption() throws InvalidDataStoreException, InvalidOptionException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = context().persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(DirectWriteBlueprintsCacheManyStore.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionNoBase() throws InvalidDataStoreException, InvalidOptionException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = context().persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionDirectWriteBase() throws InvalidDataStoreException, InvalidOptionException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWrite()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = context().persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionCachedManyBase() throws InvalidDataStoreException, InvalidOptionException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .autocommit()
                .asMap();

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.newBuilder().asMap());

        PersistentStore eStore = context().persistenceBackendFactory().createPersistentStore(null, persistentBackend, options);
        assertThat(eStore).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertHasInnerBackend(eStore, persistentBackend);
    }
}
