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

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link StoreFactory}.
 */
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
    public void testNoOption() {
        Map<String, Object> options = CommonOptions.noOption();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the default store, without any decorator ({@link DirectWriteStore}).
     */
    @Test
    public void testAlreadyDefinedOption() {
        assertThat(catchThrowable(() -> CommonOptions.builder().autoSave().autoSave()))
                .isExactlyInstanceOf(InvalidOptionException.class);
    }

    /**
     * Checks the setup of the {@link IsSetCachingStore}.
     */
    @Test
    public void testIsSetCachingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheIsSet()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(IsSetCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link LoggingStore}.
     */
    @Test
    public void testLoggingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .log()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(LoggingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link SizeCachingStore}.
     */
    @Test
    public void testSizeCachingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheSizes()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(SizeCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link FeatureCachingStore}.
     */
    @Test
    public void testFeatureCachingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheFeatures()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(FeatureCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link ContainerCachingStore}.
     */
    @Test
    public void testContainerCachingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheContainers()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(ContainerCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link ClassCachingStore}.
     */
    @Test
    public void testMetaclassCachingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheMetaClasses()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(ClassCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link StatsStore}.
     */
    @Test
    public void testStatsCachingOption() {
        Map<String, Object> options = CommonOptions.builder()
                .recordStats()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isInstanceOf(StatsStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks the setup of the {@link AutoSaveStore} without chunk.
     */
    @Test
    public void testAutoSaveOption() {
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
    public void testAutoSaveWithChunkOption() {
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
    public void testAutoSaveWithNegativeChunkOption() {
        assertThat(catchThrowable(() -> CommonOptions.builder().autoSave(-2)))
                .isExactlyInstanceOf(InvalidOptionException.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}. <ul>
     * <li>{@link IsSetCachingStore}</li> <li>{@link LoggingStore}</li> </ul>
     */
    @Test
    public void testIsSetCachingLoggingOptions() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheIsSet()
                .log()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(LoggingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(IsSetCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}. <ul>
     * <li>{@link IsSetCachingStore}</li> <li>{@link SizeCachingStore}</li> </ul>
     */
    @Test
    public void testIsSetCachingSizeCachingOptions() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheIsSet()
                .cacheSizes()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(SizeCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(IsSetCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}. <ul>
     * <li>{@link SizeCachingStore}</li> <li>{@link FeatureCachingStore}</li> </ul>
     */
    @Test
    public void testSizeCachingFeatureCachingOptions() {
        Map<String, Object> options = CommonOptions.builder()
                .cacheSizes()
                .cacheFeatures()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(SizeCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(FeatureCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(DirectWriteStore.class);
    }

    /**
     * Checks store containment order (depend on the instantiation policy defined in {@link BackendFactory}. <ul>
     * <li>{@link IsSetCachingStore}</li> <li>{@link SizeCachingStore}</li> <li>{@link FeatureCachingStore}</li>
     * <li>{@link LoggingStore}</li> <li>{@link AutoSaveStore}</li> </ul>
     */
    @Test
    public void testAllOptions() {
        long expectedChunk = 12_345;

        Map<String, Object> options = CommonOptions.builder()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .log()
                .autoSave(expectedChunk)
                .cacheContainers()
                .cacheMetaClasses()
                .recordStats()
                .asMap();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), options);
        assertThat(store).isExactlyInstanceOf(StatsStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(LoggingStore.class);

        store = getInnerStore(store);
        assertThat(store).isInstanceOf(AutoSaveStore.class);

        long actualChunk = getValue(store, "chunk", AutoSaveStore.class, Long.class);
        assertThat(actualChunk).isEqualTo(expectedChunk);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(ClassCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(ContainerCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(SizeCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(IsSetCachingStore.class);

        store = getInnerStore(store);
        assertThat(store).isExactlyInstanceOf(FeatureCachingStore.class);

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
