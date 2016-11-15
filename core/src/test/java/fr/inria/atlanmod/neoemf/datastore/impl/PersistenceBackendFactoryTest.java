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

package fr.inria.atlanmod.neoemf.datastore.impl;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.CachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.FeatureCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.IsSetCachingEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.LoadedObjectCounterEStoreDecorator;
import fr.inria.atlanmod.neoemf.datastore.store.impl.LoggingEStoreDecorator;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceOptions;

import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the only non-abstract method in {@link PersistenceBackendFactory#createPersistentEStore(PersistentResource,
 * PersistenceBackend, Map)}
 */
public class PersistenceBackendFactoryTest extends AllTest {

    private static final String SEARCHEABLE_RESOURCE_ESTORE_NAME = PersistentEStore.class.getSimpleName();

    private static final String MOCK = "mock";

    private final AbstractPersistenceBackendFactory persistenceBackendFactory = mock(AbstractPersistenceBackendFactory.class);
    private final PersistentEStore mockPersistentEStore = mock(PersistentEStore.class);
    private final PersistenceBackend mockPersistentBackend = mock(PersistenceBackend.class);

    @Before
    public void setUp() throws InvalidDataStoreException {
        when(persistenceBackendFactory.createPersistentBackend(any(File.class), any(Map.class))).thenReturn(mockPersistentBackend);
        when(persistenceBackendFactory.createPersistentEStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenCallRealMethod();
        when(persistenceBackendFactory.internalCreatePersistentEStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenReturn(mockPersistentEStore);

        PersistenceBackendFactoryRegistry.unregisterAll();
        PersistenceBackendFactoryRegistry.register(MOCK, persistenceBackendFactory);
    }

    private PersistentEStore getChildStore(EStore store) throws SecurityException, IllegalArgumentException {
        assertThat(store).isInstanceOf(PersistentEStore.class); // "Invalid call, can not get the child store if the given one is not a DelegatedResourceEStoreImpl"
        return ((PersistentEStore) store).getEStore();
    }

    @Test
    public void testNoOptions() throws InvalidDataStoreException {
        PersistentEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, PersistentResourceOptions.newBuilder().asMap());
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testIsSetCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheIsSet()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(IsSetCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .log()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoggingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testSizeCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheSizes()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(CachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testEStructuralFeatureCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheFeatures()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(FeatureCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testLoadedObjectCounterLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .countLoadedObjects()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoadedObjectCounterEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingEStoreDecorator} and {@link LoggingEStoreDecorator}
     */
    @Test
    public void testIsSetCachingLoggingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheIsSet()
                .log()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoggingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingEStoreDecorator} and {@link CachingEStoreDecorator}
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(CachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link CachingEStoreDecorator} and {@link FeatureCachingEStoreDecorator}
     */
    @Test
    public void testSizeCachingEStructuralFeatureCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheSizes()
                .cacheFeatures()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(CachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(FeatureCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 4 stores : {@link FeatureCachingEStoreDecorator}, {@link IsSetCachingEStoreDecorator},
     * {@link LoggingEStoreDecorator} and {@link CachingEStoreDecorator}
     */
    @Test
    public void testEStructuralFeatureCachingIsSetCachingLoggingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = PersistentResourceOptions.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .asMap();

        PersistentEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoggingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(CachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(FeatureCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingEStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }
}
