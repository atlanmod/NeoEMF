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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.CoreTest;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.CoreContext;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.option.CommonOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the only non-abstract method in {@link PersistenceBackendFactory#createPersistentStore(PersistentResource,
 * PersistenceBackend, Map)}
 */
public class PersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements CoreTest {

    @Test
    public void testNoOptions() throws InvalidDataStoreException {
        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, null, CommonOptionsBuilder.noOption());
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testIsSetCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheIsSet()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .log()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testSizeCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheSizes()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testEStructuralFeatureCachingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheFeatures()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Test
    public void testLoadedObjectCounterLoggingOption() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .countLoadedObjects()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoadedObjectCounterStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingStoreDecorator} and {@link LoggingStoreDecorator}
     */
    @Test
    public void testIsSetCachingLoggingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheIsSet()
                .log()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link IsSetCachingStoreDecorator} and {@link SizeCachingStoreDecorator}
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    /**
     * Test store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}
     * 2 stores : {@link SizeCachingStoreDecorator} and {@link FeatureCachingStoreDecorator}
     */
    @Test
    public void testSizeCachingEStructuralFeatureCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheSizes()
                .cacheFeatures()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getInnerStore(store);
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
    public void testEStructuralFeatureCachingIsSetCachingLoggingSizeCachingOptions() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(PersistentStore.class);

        // Ensure this is the mock that is returned by checking the real class name
        assertThat(store.getClass().getSimpleName()).contains(PersistentStore.class.getSimpleName());
    }

    @Override
    public Context context() {
        return MockContext.get();
    }

    private static final class MockContext extends CoreContext {

        protected MockContext() {
        }

        public static Context get() {
            return Holder.INSTANCE;
        }

        @Override
        public PersistenceBackendFactory persistenceBackendFactory() {
            try {
                AbstractPersistenceBackendFactory persistenceBackendFactory = (AbstractPersistenceBackendFactory) super.persistenceBackendFactory();
                when(persistenceBackendFactory.createPersistentBackend(any(File.class), any(Map.class))).thenReturn(mock(PersistenceBackend.class));
                when(persistenceBackendFactory.createPersistentStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenCallRealMethod();
                when(persistenceBackendFactory.createSpecificPersistentStore(any(PersistentResource.class), any(PersistenceBackend.class), any(Map.class))).thenReturn(mock(PersistentStore.class));
                return persistenceBackendFactory;
            }
            catch (InvalidDataStoreException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        private static class Holder {

            /**
             * The instance of the outer class.
             */
            private static final Context INSTANCE = new MockContext();
        }
    }
}
