/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.config.InvalidConfigException;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperDecorator;
import fr.inria.atlanmod.neoemf.data.store.listener.RecordingStoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreStats;

import org.atlanmod.commons.AbstractTest;
import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.log.Level;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.atlanmod.commons.Preconditions.checkInstanceOf;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link StoreFactory}.
 */
@ParametersAreNonnullByDefault
class StoreFactoryTest extends AbstractTest {

    /**
     * The field name describing the inner {@link Store} in a {@link AbstractStore}.
     */
    private static final String INNER_MAPPER_FIELDNAME = "next";

    /**
     * Retrieves the value of a field, identified by its {@code fieldName}, in the given {@code object}.
     *
     * @param object    the object where to look for the field
     * @param fieldName the name of the field to retrieve the value
     * @param in        the type of the {@code object}
     * @param out       the type of the expected value
     * @param <I>       the type of the actual value
     * @param <O>       the type of the expected value
     *
     * @return the value
     */
    @Nonnull
    private static <I, O> O getValue(Object object, String fieldName, Class<I> in, Class<O> out) {
        checkInstanceOf(object, in, "object must be instance of %s", in.getName());

        try {
            Field storeField = in.getDeclaredField(fieldName);
            storeField.setAccessible(true);
            return out.cast(storeField.get(object));
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }

    /**
     * Checks the setup of the default store, without any decorator ({@link NoopStore}).
     */
    @Test
    void testNoStore() {
        ImmutableConfig config = new BaseConfig<>();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link fr.inria.atlanmod.neoemf.data.store.listener.LoggingStoreListener}.
     */
    @Test
    void testLogging() {
        ImmutableConfig config = new BaseConfig<>().log();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(ListeningStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link SizeCachingStore}.
     */
    @Test
    void testSizeCaching() {
        ImmutableConfig config = new BaseConfig<>().cacheSizes();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(SizeCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link FeatureCachingStore}.
     */
    @Test
    void testFeatureCaching() {
        ImmutableConfig config = new BaseConfig<>().cacheFeatures();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(FeatureCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link ContainerCachingStore}.
     */
    @Test
    void testContainerCaching() {
        ImmutableConfig config = new BaseConfig<>().cacheContainers();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(ContainerCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link ClassCachingStore}.
     */
    @Test
    void testMetaclassCaching() {
        ImmutableConfig config = new BaseConfig<>().cacheMetaClasses();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(ClassCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link RecordingStoreListener}.
     */
    @Test
    void testStatsCaching() {
        ImmutableConfig config = new BaseConfig<>().recordStats(new StoreStats());

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(ListeningStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSavingStore} without chunk.
     */
    @Test
    void testAutoSave() {
        ImmutableConfig config = new BaseConfig<>().autoSave();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(AutoSavingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSavingStore} with chunk.
     */
    @Test
    void testAutoSaveWithChunk() {
        final long expectedChunk = 12_345;

        ImmutableConfig config = new BaseConfig<>().autoSave(expectedChunk);

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(AutoSavingStore.class);

        long actualChunk = getValue(store, "chunk", AutoSavingStore.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSavingStore} with negative chunk.
     */
    @Test
    void testAutoSaveWithNegativeChunk() {
        assertThat(catchThrowable(() -> new BaseConfig<>().autoSave(-2))).isExactlyInstanceOf(InvalidConfigException.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}.
     */
    @Test
    void testAllStores() {
        long expectedChunk = 12_345;

        ImmutableConfig config = new BaseConfig<>()
                .cacheSizes()
                .cacheFeatures()
                .log(Level.DEBUG)
                .autoSave(expectedChunk)
                .cacheContainers()
                .cacheMetaClasses()
                .readOnly()
                .recordStats(new StoreStats());

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
        assertThat(store).isInstanceOf(AutoSavingStore.class);

        long actualChunk = getValue(store, "chunk", AutoSavingStore.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(ListeningStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(ReadOnlyStore.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(AbstractCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(AbstractCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(AbstractCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(AbstractCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(NoopStore.class);
    }

    /**
     * Retrieves the inner {@link Store} in the given {@code store}.
     *
     * @param store the store where to look for the inner store
     *
     * @return the inner store
     */
    private Store getInnerStore(Store store) {
        return getValue(store, INNER_MAPPER_FIELDNAME, AbstractMapperDecorator.class, Store.class);
    }
}
