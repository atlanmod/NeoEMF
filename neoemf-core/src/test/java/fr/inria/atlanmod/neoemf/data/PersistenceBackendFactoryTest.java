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
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.option.CommonOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases about {@link PersistenceBackendFactory#createPersistentStore(PersistentResource, PersistenceBackend,
 * Map)}.
 */
public class PersistenceBackendFactoryTest extends AbstractPersistenceBackendFactoryTest implements CoreTest {

    /**
     * Checks the setup of the default store, without any decorator ({@link DirectWriteStore}).
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testNoOption() throws InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.noOption();

        PersistentStore store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link IsSetCachingStoreDecorator}.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testIsSetCachingOption() throws InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheIsSet()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
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
    public void testLoggingOption() throws InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .log()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
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
    public void testSizeCachingOption() throws InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheSizes()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
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
    public void testFeatureCachingOption() throws InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheFeatures()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
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
    public void testLoadedObjectCounterLoggingOption() throws InvalidDataStoreException {
        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .countLoadedObjects()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(LoadedObjectCounterStoreDecorator.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutocommitStoreDecorator} without chuck.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testAutocommitOption() throws InvalidDataStoreException {
        final long expectedChuck = 100_000;

        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .autocommit()
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class);

        long actualChuck = getValue(store, "autocommitChuck", AutocommitStoreDecorator.class, Long.class);
        assertThat(actualChuck).isEqualTo(expectedChuck);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutocommitStoreDecorator} with chuck.
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testAutocommitWithChuckOption() throws InvalidDataStoreException {
        final long expectedChuck = 12_345;

        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .autocommit(expectedChuck)
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class);

        long actualChuck = getValue(store, "autocommitChuck", AutocommitStoreDecorator.class, Long.class);
        assertThat(actualChuck).isEqualTo(expectedChuck);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}.
     * <ul>
     * <li>{@link IsSetCachingStoreDecorator}</li>
     * <li>{@link LoggingStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testIsSetCachingLoggingOptions() throws InvalidDataStoreException {
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
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}.
     * <ul>
     * <li>{@link IsSetCachingStoreDecorator}</li>
     * <li>{@link SizeCachingStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() throws InvalidDataStoreException {
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
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}.
     * <ul>
     * <li>{@link SizeCachingStoreDecorator}</li>
     * <li>{@link FeatureCachingStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testSizeCachingFeatureCachingOptions() throws InvalidDataStoreException {
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
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link PersistenceBackendFactory}.
     * <ul>
     * <li>{@link IsSetCachingStoreDecorator}</li>
     * <li>{@link SizeCachingStoreDecorator}</li>
     * <li>{@link FeatureCachingStoreDecorator}</li>
     * <li>{@link LoggingStoreDecorator}</li>
     * <li>{@link AutocommitStoreDecorator}</li>
     * </ul>
     *
     * @throws InvalidDataStoreException if there is at least one invalid value in options
     */
    @Test
    public void testAllOptions() throws InvalidDataStoreException {
        long expectedChuck = 12_345;

        Map<String, Object> options = CommonOptionsBuilder.newBuilder()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .autocommit(expectedChuck)
                .asMap();

        PersistentStore store;

        store = context().persistenceBackendFactory().createPersistentStore(null, null, options);
        assertThat(store).isInstanceOf(AutocommitStoreDecorator.class);

        long actualChuck = getValue(store, "autocommitChuck", AutocommitStoreDecorator.class, Long.class);
        assertThat(actualChuck).isEqualTo(expectedChuck);

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
        public PersistenceBackendFactory persistenceBackendFactory() {
            try {
                AbstractPersistenceBackendFactory factory = (AbstractPersistenceBackendFactory) super.persistenceBackendFactory();

                when(factory.createPersistentBackend(any(), notNull())).thenReturn(mock(PersistenceBackend.class));
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
        private static class Holder {

            /**
             * The instance of the outer class.
             */
            private static final Context INSTANCE = new MockContext();
        }
    }
}
