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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements BlueprintsTest {

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(backend).isInstanceOf(BlueprintsBackend.class); // "Invalid back-end created"

        // TODO Need to test further the nature of the Blueprints engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, IllegalAccessException {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();

        PersistentStore store = context().persistenceBackendFactory().createTransientStore(null, backend);
        assertThat(store).isInstanceOf(DirectWriteStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentBackendNoOptionNoConfigFile() throws InvalidDataStoreException {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());
        assertThat(backend).isInstanceOf(BlueprintsBackend.class); // "Invalid back-end created"

        // TODO Need to test further the nature of the Blueprints engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, IllegalAccessException {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, BlueprintsOptionsBuilder.noOption());
        assertThat(store).isInstanceOf(DirectWriteStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreManyCacheOption() throws InvalidDataStoreException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionNoBase() throws InvalidDataStoreException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionDirectWriteBase() throws InvalidDataStoreException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWrite()
                .autocommit()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOptionCachedManyBase() throws InvalidDataStoreException, NoSuchFieldException, IllegalAccessException {
        Map<String, Object> options = BlueprintsOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .autocommit()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BlueprintsOptionsBuilder.noOption());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }
}
