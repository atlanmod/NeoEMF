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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperDecorator;
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link StoreFactory}.
 */
@ParametersAreNonnullByDefault
public class StoreFactoryTest extends AbstractTest {

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
        if (!in.isInstance(object)) {
            throw new IllegalArgumentException();
        }

        try {
            Field storeField = in.getDeclaredField(fieldName);
            storeField.setAccessible(true);
            return out.cast(storeField.get(object));
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e); // Should never happen
        }
    }

    /**
     * Checks the setup of the default store, without any decorator ({@link DirectWriteStore}).
     */
    @Test
    public void testNoOption() throws Exception {
        Map<String, Object> options = CommonOptions.noOption();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the default store, without any decorator ({@link DirectWriteStore}).
     */
    @Test
    public void testAlreadyDefinedOption() throws Exception {
        assertThat(catchThrowable(() -> CommonOptions.builder().autoSave().autoSave()))
                .isExactlyInstanceOf(InvalidOptionException.class);
    }

    /**
     * Checks the setup of the {@link LogStore}.
     */
    @Test
    public void testLoggingOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .log()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(LogStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link SizeCacheStore}.
     */
    @Test
    public void testSizeCachingOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .cacheSizes()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(SizeCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link FeatureCacheStore}.
     */
    @Test
    public void testFeatureCachingOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .cacheFeatures()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(FeatureCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link ContainerCacheStore}.
     */
    @Test
    public void testContainerCachingOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .cacheContainers()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(ContainerCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link ClassCacheStore}.
     */
    @Test
    public void testMetaclassCachingOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .cacheMetaClasses()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(ClassCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link StatsRecordStore}.
     */
    @Test
    public void testStatsCachingOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .recordStats()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(StatsRecordStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSaveStore} without chunk.
     */
    @Test
    public void testAutoSaveOption() throws Exception {
        Map<String, Object> options = CommonOptions.builder()
                .autoSave()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(AutoSaveStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSaveStore} with chunk.
     */
    @Test
    public void testAutoSaveWithChunkOption() throws Exception {
        final long expectedChunk = 12_345;

        Map<String, Object> options = CommonOptions.builder()
                .autoSave(expectedChunk)
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(AutoSaveStore.class);

        long actualChunk = getValue(store, "chunk", AutoSaveStore.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSaveStore} with negative chunk.
     */
    @Test
    public void testAutoSaveWithNegativeChunkOption() throws Exception {
        assertThat(catchThrowable(() -> CommonOptions.builder().autoSave(-2)))
                .isExactlyInstanceOf(InvalidOptionException.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}.
     */
    @Test
    public void testAllOptions() throws Exception {
        long expectedChunk = 12_345;

        Map<String, Object> options = CommonOptions.builder()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .autoSave(expectedChunk)
                .cacheContainers()
                .cacheMetaClasses()
                .recordStats()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(StatsRecordStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(LogStore.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(AutoSaveStore.class);

        long actualChunk = getValue(store, "chunk", AutoSaveStore.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(ClassCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(ContainerCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(SizeCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(FeatureCacheStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
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
