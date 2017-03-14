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

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.CoreContext;
import fr.inria.atlanmod.neoemf.CoreTest;
import fr.inria.atlanmod.neoemf.data.store.AutoSaveStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases about {@link BackendFactory#createPersistentStore(PersistentResource, Backend, Map)}.
 */
public class PersistentBackendFactoryTest extends AbstractBackendFactoryTest implements CoreTest {

    /**
     * Checks the setup of the default store, without any decorator ({@link DirectWriteStore}).
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testNoOption() {
        Map<String, Object> options = CommonOptions.noOption();

        Store store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link IsSetCachingStoreDecorator}.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testIsSetCachingOption() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheIsSet()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link LoggingStoreDecorator}.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testLoggingOption() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .log()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link SizeCachingStoreDecorator}.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testSizeCachingOption() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheSizes()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link FeatureCachingStoreDecorator}.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testFeatureCachingOption() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheFeatures()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link LoadedObjectCounterStoreDecorator}.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testLoadedObjectCounterLoggingOption() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .countLoadedObjects()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoadedObjectCounterStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSaveStoreDecorator} without chunk.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testAutoSaveOption() {
        final long expectedChunk = 100_000;

        Map<String, Object> options = CommonOptions.newBuilder()
                .autoSave()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(AutoSaveStoreDecorator.class);

        long actualChunk = getValue(store, "autoSaveChunk", AutoSaveStoreDecorator.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSaveStoreDecorator} with chunk.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testAutoSaveWithChunkOption() {
        final long expectedChunk = 12_345;

        Map<String, Object> options = CommonOptions.newBuilder()
                .autoSave(expectedChunk)
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(AutoSaveStoreDecorator.class);

        long actualChunk = getValue(store, "autoSaveChunk", AutoSaveStoreDecorator.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}.
     * <ul>
     * <li>{@link IsSetCachingStoreDecorator}</li>
     * <li>{@link LoggingStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testIsSetCachingLoggingOptions() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheIsSet()
                .log()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}.
     * <ul>
     * <li>{@link IsSetCachingStoreDecorator}</li>
     * <li>{@link SizeCachingStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}.
     * <ul>
     * <li>{@link SizeCachingStoreDecorator}</li>
     * <li>{@link FeatureCachingStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testSizeCachingFeatureCachingOptions() {
        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheSizes()
                .cacheFeatures()
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}.
     * <ul>
     * <li>{@link IsSetCachingStoreDecorator}</li>
     * <li>{@link SizeCachingStoreDecorator}</li>
     * <li>{@link FeatureCachingStoreDecorator}</li>
     * <li>{@link LoggingStoreDecorator}</li>
     * <li>{@link AutoSaveStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testAllOptions() {
        long expectedChunk = 12_345;

        Map<String, Object> options = CommonOptions.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .autoSave(expectedChunk)
                .asMap();

        Store store;

        store = context().factory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(AutoSaveStoreDecorator.class);

        long actualChunk = getValue(store, "autoSaveChunk", AutoSaveStoreDecorator.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(LoggingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(SizeCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(FeatureCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(IsSetCachingStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    @Override
    public Context context() {
        return MockContext.get();
    }

    /**
     * A specific {@link Context} for this test-case.
     */
    private static final class MockContext extends CoreContext {

        /**
         * Constructs a new {@code MockContext}.
         */
        protected MockContext() {
        }

        /**
         * Returns the instance of this class.
         *
         * @return the instance of this class.
         */
        public static Context get() {
            return Holder.INSTANCE;
        }

        @Override
        public BackendFactory factory() {
            try {
                BackendFactory factory = super.factory();

                when(factory.createPersistentBackend(any(), notNull())).thenReturn(mock(PersistentBackend.class));
                when(factory.createPersistentStore(any(), any(), notNull())).thenCallRealMethod();

                return factory;
            }
            catch (InvalidDataStoreException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            private static final Context INSTANCE = new MockContext();
        }
    }
}
