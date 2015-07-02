/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.datastore;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.DelegatedResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.EStructuralFeatureCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoadedObjectCounterLoggingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoggingDelegatedResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

/**
 * Test cases for the only non-abstract method in @see{AbstractPersistenceBackendFactory::createPersistentEStore} 
 *
 */
public class AbstractPersistenceBackendFactoryTest {
    
    private AbstractPersistenceBackendFactory persistenceBackendFactory = mock(AbstractPersistenceBackendFactory.class);
    private SearcheableResourceEStore mockPersistentEStore = mock(SearcheableResourceEStore.class);
    private PersistenceBackend mockPersistentBackend = mock(PersistenceBackend.class);
    @SuppressWarnings("rawtypes")
    private Map options = new HashMap();
    private List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<PersistentResourceOptions.StoreOption>();
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws InvalidDataStoreException {
        when(persistenceBackendFactory.createPersistentBackend(any(File.class), any(Map.class))).thenReturn(mockPersistentBackend);
        when(persistenceBackendFactory.createPersistentEStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenCallRealMethod();
        when(persistenceBackendFactory.internalCreatePersistentEStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenReturn(mockPersistentEStore);
        PersistenceBackendFactoryRegistry.getFactories().clear();
        PersistenceBackendFactoryRegistry.getFactories().put("mock", persistenceBackendFactory);
        options.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
    }
    
    @After
    public void tearDown() {
        options.clear();
    }
    
    private SearcheableResourceEStore getChildStore(EStore store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        assert store instanceof DelegatedResourceEStoreImpl : "Invalid call, can not get the child store if the given one is not a DelegatedResourceEStoreImpl";
        Field childStoreField = DelegatedResourceEStoreImpl.class.getDeclaredField("eStore");
        childStoreField.setAccessible(true);
        return (SearcheableResourceEStore)childStoreField.get(store);
    }
    
    @Test
    public void testNoOptions() throws InvalidDataStoreException {
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, Collections.EMPTY_MAP);
        assert store instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert store.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    @Test
    public void testIsSetCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof IsSetCachingDelegatedEStoreImpl;
        SearcheableResourceEStore childStore = getChildStore(store);
        assert childStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert childStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    @Test
    public void testLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof LoggingDelegatedResourceEStoreImpl;
        SearcheableResourceEStore childStore = getChildStore(store);
        assert childStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert childStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    @Test
    public void testSizeCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof SizeCachingDelegatedEStoreImpl;
        SearcheableResourceEStore childStore = getChildStore(store);
        assert childStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert childStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    @Test
    public void testEStructuralFeatureCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof EStructuralFeatureCachingDelegatedEStoreImpl;
        SearcheableResourceEStore childStore = getChildStore(store);
        assert childStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert childStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    @Test
    public void testLoadedObjectCounterLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof LoadedObjectCounterLoggingDelegatedEStoreImpl;
        SearcheableResourceEStore childStore = getChildStore(store);
        assert childStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert childStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    /**
     * Test store containment order (depend on the instantiation policy defined in @see{AbstractPersistenceBackendFactory}
     * 2 stores : @see{IsSetCachingDelegatedEStoreImpl} and @see{LoggingDelegatedEStoreImpl}
     */
    @Test
    public void testIsSetCachingLoggingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof LoggingDelegatedResourceEStoreImpl;
        SearcheableResourceEStore loggingChildStore = getChildStore(store);
        assert loggingChildStore instanceof IsSetCachingDelegatedEStoreImpl;
        SearcheableResourceEStore isSetCachingChildStore = getChildStore(loggingChildStore);
        assert isSetCachingChildStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert isSetCachingChildStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    /**
     * Test store containment order (depend on the instantiation policy defined in @see{AbstractPersistenceBackendFactory}
     * 2 stores : @see{IsSetCachingDelegatedEStoreImpl} and @see{SizeCachingDelegatedEStoreImpl}
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof SizeCachingDelegatedEStoreImpl;
        SearcheableResourceEStore sizeCachingChildStore = getChildStore(store);
        assert sizeCachingChildStore instanceof IsSetCachingDelegatedEStoreImpl;
        SearcheableResourceEStore isSetCachingChildStore = getChildStore(sizeCachingChildStore);
        assert isSetCachingChildStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert isSetCachingChildStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    /**
     * Test store containment order (depend on the instantiation policy defined in @see{AbstractPersistenceBackendFactory}
     * 2 stores : @see{SizeCachingDelegatedEStoreImpl} and @see{EStructuralFeatureCachingDelegatedEStoreImpl}
     */
    @Test
    public void testSizeCachingEStructuralFeatureCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof SizeCachingDelegatedEStoreImpl;
        SearcheableResourceEStore sizeCachingChildStore = getChildStore(store);
        assert sizeCachingChildStore instanceof EStructuralFeatureCachingDelegatedEStoreImpl;
        SearcheableResourceEStore eStructuralFeatureCachingChildStore = getChildStore(sizeCachingChildStore);
        assert eStructuralFeatureCachingChildStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert eStructuralFeatureCachingChildStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }
    
    /**
     * Test store containment order (depend on the instantiation policy defined in @see{AbstractPersistenceBackendFactory}
     * 4 stores : @see{EStructuralFeatureCachingDelegatedEStoreImpl}, @see{IsSetCachingDelegatedEStoreImpl}, 
     * @see{LoggingDelegatedResourceEStoreImpl}, and @see{SizeCachingDelegatedEStoreImpl}
     */
    @Test
    public void testEStructuralFeatureCachingIsSetCachingLoggingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);
        storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);
        SearcheableResourceEStore store = persistenceBackendFactory.createPersistentEStore(null, mockPersistentBackend, options);
        assert store instanceof LoggingDelegatedResourceEStoreImpl;
        SearcheableResourceEStore loggingChildStore = getChildStore(store);
        assert loggingChildStore instanceof SizeCachingDelegatedEStoreImpl;
        SearcheableResourceEStore sizeCachingChildStore = getChildStore(loggingChildStore);
        assert sizeCachingChildStore instanceof EStructuralFeatureCachingDelegatedEStoreImpl;
        SearcheableResourceEStore eStructuralFeatureCachingChildStore = getChildStore(sizeCachingChildStore);
        assert eStructuralFeatureCachingChildStore instanceof IsSetCachingDelegatedEStoreImpl;
        SearcheableResourceEStore isSetCachingChildStore = getChildStore(eStructuralFeatureCachingChildStore);
        assert isSetCachingChildStore instanceof SearcheableResourceEStore;
        // Ensure this is the mock that is returned by checking the real class name
        assert isSetCachingChildStore.getClass().getSimpleName().contains("SearcheableResourceEStore");
    }

}
