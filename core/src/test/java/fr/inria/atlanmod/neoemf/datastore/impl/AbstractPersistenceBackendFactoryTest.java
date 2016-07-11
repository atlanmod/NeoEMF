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

package fr.inria.atlanmod.neoemf.datastore.impl;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.estores.DelegatedEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.EStructuralFeatureCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoadedObjectCounterLoggingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoggingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the only non-abstract method in {@link PersistenceBackendFactory#createPersistentEStore(PersistentResource, PersistenceBackend, Map)}
 */
public class AbstractPersistenceBackendFactoryTest extends AllTest {

    private static final String SEARCHEABLE_RESOURCE_ESTORE_NAME = SearcheableResourceEStore.class.getSimpleName();

    private static final String MOCK = "mock";

    private AbstractPersistenceBackendFactory persistenceBackendFactory = mock(AbstractPersistenceBackendFactory.class);
    private SearcheableResourceEStore mockPersistentEStore = mock(SearcheableResourceEStore.class);
    private PersistenceBackend mockPersistentBackend = mock(PersistenceBackend.class);
    private Map<Object, Object> options = new HashMap<>();
    private List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();

    @Before
    public void setUp() throws InvalidDataStoreException {
        when(persistenceBackendFactory.createPersistentBackend(any(File.class), any(Map.class))).thenReturn(mockPersistentBackend);
        when(persistenceBackendFactory.createPersistentEStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenCallRealMethod();
        when(persistenceBackendFactory.internalCreatePersistentEStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenReturn(mockPersistentEStore);

        PersistenceBackendFactoryRegistry.unregisterAll();
        PersistenceBackendFactoryRegistry.register(MOCK, persistenceBackendFactory);
        options.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
    }

    @After
    public void tearDown() {
        options.clear();
    }

    private SearcheableResourceEStore getChildStore(EStore store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        assertThat(store).isInstanceOf(DelegatedEStore.class); // "Invalid call, can not get the child store if the given one is not a DelegatedResourceEStoreImpl"
        return ((DelegatedEStore<?>) store).getEStore();
    }

    @Test
    public void testNoOptions() throws InvalidDataStoreException {
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, Collections.emptyMap());
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testIsSetCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(IsSetCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoggingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testSizeCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(SizeCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testEStructuralFeatureCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(EStructuralFeatureCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    @Test
    public void testLoadedObjectCounterLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoadedObjectCounterLoggingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingDelegatedEStoreImpl} and {@link LoggingDelegatedEStoreImpl}
     */
    @Test
    public void testIsSetCachingLoggingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoggingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingDelegatedEStoreImpl} and {@link SizeCachingDelegatedEStoreImpl}
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(SizeCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link SizeCachingDelegatedEStoreImpl} and {@link EStructuralFeatureCachingDelegatedEStoreImpl}
     */
    @Test
    public void testSizeCachingEStructuralFeatureCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(SizeCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(EStructuralFeatureCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 4 stores : {@link EStructuralFeatureCachingDelegatedEStoreImpl}, {@link IsSetCachingDelegatedEStoreImpl},
     * {@link LoggingDelegatedEStoreImpl} and {@link SizeCachingDelegatedEStoreImpl}
     */
    @Test
    public void testEStructuralFeatureCachingIsSetCachingLoggingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);

        SearcheableResourceEStore store;

        store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assertThat(store).isInstanceOf(LoggingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SizeCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(EStructuralFeatureCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingDelegatedEStoreImpl.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SearcheableResourceEStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(SEARCHEABLE_RESOURCE_ESTORE_NAME);
    }
}
