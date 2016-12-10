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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.junit.Test;

import java.io.File;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the only non-abstract method in {@link PersistenceBackendFactory#createPersistentStore(PersistentResource,
 * PersistenceBackend, Map)}
 */
public class PersistenceBackendFactoryTest extends AllPersistenceBackendFactoryTest {

    @Override
    protected String name() {
        return "Core";
    }

    @Override
    protected String uriScheme() {
        return "mock";
    }

    @Override
    protected PersistenceBackendFactory persistenceBackendFactory() throws InvalidDataStoreException, InvalidOptionsException {
        AbstractPersistenceBackendFactory persistenceBackendFactory = mock(AbstractPersistenceBackendFactory.class);
        when(persistenceBackendFactory.createPersistentBackend(any(File.class), any(Map.class))).thenReturn(mock(PersistenceBackend.class));
        when(persistenceBackendFactory.createPersistentStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenCallRealMethod();
        when(persistenceBackendFactory.createSpecificPersistentStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenReturn(mock(PersistentStore.class));
        return persistenceBackendFactory;
    }

    @Test
    public void testNoOptions() throws InvalidDataStoreException, InvalidOptionsException {
        PersistentStore store = persistenceBackendFactory().createPersistentStore(null, null, PersistenceOptionsBuilder.noOption());
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testIsSetCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheIsSet()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .log()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testSizeCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheSizes()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testEStructuralFeatureCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheFeatures()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testLoadedObjectCounterLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .countLoadedObjects()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoadedObjectCounterStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingStoreDecorator} and {@link LoggingStoreDecorator}
     */
    @Test
    public void testIsSetCachingLoggingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheIsSet()
                .log()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingStoreDecorator} and {@link SizeCachingStoreDecorator}
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link SizeCachingStoreDecorator} and {@link FeatureCachingStoreDecorator}
     */
    @Test
    public void testSizeCachingEStructuralFeatureCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheSizes()
                .cacheFeatures()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 4 stores : {@link FeatureCachingStoreDecorator}, {@link IsSetCachingStoreDecorator},
     * {@link LoggingStoreDecorator} and {@link SizeCachingStoreDecorator}
     */
    @Test
    public void testEStructuralFeatureCachingIsSetCachingLoggingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException, InvalidOptionsException {
        Map<String, Object> options = PersistenceOptionsBuilder.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .asMap();

        PersistentStore store;

        store = persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getChildStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }
}
