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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteCachedMapStore;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStore;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithIndices;
import fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithLists;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BerkeleyDbPersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements BerkeleyDbTest {

    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(backend).isInstanceOf(BerkeleyDbPersistenceBackend.class); // "Invalid back-end created"

        // TODO Need to test further the nature of the BerkeleyDB engine
    }

    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend backend = context().persistenceBackendFactory().createTransientBackend();

        PersistentStore store = context().persistenceBackendFactory().createTransientStore(null, backend);
        assertThat(store).isInstanceOf(DirectWriteMapStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentBackendNoOption() throws InvalidDataStoreException {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());
        assertThat(backend).isInstanceOf(BerkeleyDbPersistenceBackend.class); // "Invalid back-end created"

        // TODO Need to test further the nature of the BerkeleyDB engine
    }

    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, BerkeleyDbOptionsBuilder.newBuilder().asMap());
        assertThat(store).isInstanceOf(DirectWriteMapStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = BerkeleyDbOptionsBuilder.newBuilder()
                .directWrite()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithListsOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = BerkeleyDbOptionsBuilder.newBuilder()
                .directWriteLists()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapStoreWithLists.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreDirectWriteWithIndexesOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = BerkeleyDbOptionsBuilder.newBuilder()
                .directWriteIndices()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteMapStoreWithIndices.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = BerkeleyDbOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    @Test
    public void testCreatePersistentEStoreCachedManyOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, Object> options = BerkeleyDbOptionsBuilder.newBuilder()
                .directWriteCacheMany()
                .asMap();

        PersistenceBackend backend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, backend, options);
        assertThat(store).isInstanceOf(DirectWriteCachedMapStore.class); // "Invalid EStore created"

        assertThat(getInnerBackend(store)).isSameAs(backend);
    }

    /**
     * Test if {@link PersistenceBackendFactory#copyBackend} creates the persistent datastores from the transient ones.
     * Only empty back-ends are tested.
     */
    @Test
    @Ignore
    public void testCopyBackend() throws InvalidDataStoreException {
        PersistenceBackend transientBackend = context().persistenceBackendFactory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BerkeleyDbPersistenceBackend.class); // "Transient back-end is not an instance of BerkeleyDbPersistenceBackend"
        BerkeleyDbPersistenceBackend transientMap = (BerkeleyDbPersistenceBackend) transientBackend;

        PersistenceBackend persistentBackend = context().persistenceBackendFactory().createPersistentBackend(file(), BerkeleyDbOptionsBuilder.newBuilder().asMap());
        assertThat(persistentBackend).isInstanceOf(BerkeleyDbPersistenceBackend.class); // "Persistent back-end is not an instance of BerkeleyDbPersistenceBackend"

        BerkeleyDbPersistenceBackend persistentMap = (BerkeleyDbPersistenceBackend) persistentBackend;

        context().persistenceBackendFactory().copyBackend(transientMap, persistentMap);

        for (String tKey : transientMap.getAll().keySet()) {
            assertThat(persistentMap.getAll()).containsKey(tKey); // "Persistent back-end does not contain the key"
            assertThat(persistentMap.getAll().get(tKey)).isEqualTo(transientMap.get(tKey)); // "Persistent back-end structure %s is not equal to transient one"
        }
    }

    @Test
    public void testTransientBackend() {
        final int TIMES = 1000;

        try (BerkeleyDbPersistenceBackend backend = (BerkeleyDbPersistenceBackend) context().persistenceBackendFactory().createTransientBackend()) {
            IntStream.range(0, TIMES).forEach(i -> {
                FeatureKey key = FeatureKey.of(StringId.of("object" + i), "name" + i);
                assertThat(backend.valueFor(key, "value" + i)).isPresent();
            });
        }

        try (BerkeleyDbPersistenceBackend other = (BerkeleyDbPersistenceBackend) context().persistenceBackendFactory().createTransientBackend()) {
            IntStream.range(0, TIMES).forEach(i -> {
                FeatureKey key = FeatureKey.of(StringId.of("object" + i), "name" + i);
                assertThat(other.hasValue(key)).isFalse();
            });
        }
    }
}
