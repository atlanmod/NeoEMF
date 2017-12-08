/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.NoopBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.RxTestUtils.await;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link StatsRecordStore}.
 */
@ParametersAreNonnullByDefault
class StatsRecordStoreTest extends AbstractTest {

    private static final long CALLS_COUNT = 2L;

    private Store store;

    @BeforeEach
    void setUp() {
        ImmutableConfig config = BaseConfig.newConfig().recordStats();

        store = StoreFactory.getInstance().createStore(new NoopBackend(), config);
    }

    @AfterEach
    void tearDown() {
        if (nonNull(store)) {
            store.close();
        }
    }

    /**
     * Creates a new {@link Map.Entry} instance.
     */
    @Nonnull
    private Map.Entry<String, Long> createEntry(String key, Long value) {
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    /**
     * Calls the {@code before}, and checks the results in the resulting {@link StoreStats}.
     */
    private void assertRecorded(Runnable before, String name, long count) {
        for (int i = 0; i < count; i++) {
            before.run();
        }

        StoreStats stats = store.stats();
        assertThat(stats.methodCalls()).containsOnly(createEntry(name, count));
    }

    @Test
    void testNoStats() {
        ImmutableConfig config = BaseConfig.newConfig();

        Store store = StoreFactory.getInstance().createStore(new NoopBackend(), config);

        // Do a call
        await(store.containerOf(mock(Id.class))).assertComplete();

        StoreStats stats = store.stats();
        assertThat(stats).isNotNull();
        assertThat(stats.methodCalls()).isEmpty();
    }

    @Test
    void testContainerOf() {
        assertRecorded(() -> await(store.containerOf(mock(Id.class))), "containerOf", CALLS_COUNT);
    }

    @Test
    void testContainerFor() {
        assertRecorded(() -> await(store.containerFor(mock(Id.class), mock(SingleFeatureBean.class))), "containerFor", CALLS_COUNT);
    }

    @Test
    void testRemoveContainer() {
        assertRecorded(() -> await(store.removeContainer(mock(Id.class))), "removeContainer", CALLS_COUNT);
    }

    @Test
    void testMetaClassOf() {
        assertRecorded(() -> await(store.metaClassOf(mock(Id.class))), "metaClassOf", CALLS_COUNT);
    }

    @Test
    void testMetaClassFor() {
        assertRecorded(() -> await(store.metaClassFor(mock(Id.class), mock(ClassBean.class))), "metaClassFor", CALLS_COUNT);
    }

    @Test
    void testValueOf() {
        assertRecorded(() -> await(store.valueOf(mock(SingleFeatureBean.class))), "valueOf", CALLS_COUNT);
    }

    @Test
    void testValueFor() {
        assertRecorded(() -> await(store.valueFor(mock(SingleFeatureBean.class), mock(Object.class))), "valueFor", CALLS_COUNT);
    }

    @Test
    void testRemoveValue() {
        assertRecorded(() -> await(store.removeValue(mock(SingleFeatureBean.class))), "removeValue", CALLS_COUNT);
    }

    @Test
    void testReferenceOf() {
        assertRecorded(() -> await(store.referenceOf(mock(SingleFeatureBean.class))), "referenceOf", CALLS_COUNT);
    }

    @Test
    void testReferenceFor() {
        assertRecorded(() -> await(store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class))), "referenceFor", CALLS_COUNT);
    }

    @Test
    void testRemoveReference() {
        assertRecorded(() -> await(store.removeReference(mock(SingleFeatureBean.class))), "removeReference", CALLS_COUNT);
    }

    @Test
    void testValueOfMany() {
        assertRecorded(() -> await(store.valueOf(mock(ManyFeatureBean.class))), "valueOf", CALLS_COUNT);
    }

    @Test
    void testAllValuesOf() {
        assertRecorded(() -> await(store.allValuesOf(mock(SingleFeatureBean.class))), "allValuesOf", CALLS_COUNT);
    }

    @Test
    void tetsValueForMany() {
        assertRecorded(() -> await(store.valueFor(mock(ManyFeatureBean.class), mock(Object.class))), "valueFor", CALLS_COUNT);
    }

    @Test
    void testAddValue() {
        assertRecorded(() -> await(store.addValue(mock(ManyFeatureBean.class), mock(Object.class))), "addValue", CALLS_COUNT);
    }

    @Test
    void testAddAllValues() {
        assertRecorded(() -> await(store.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList())), "addAllValues", CALLS_COUNT);
    }

    @Test
    void testAppendValue() {
        assertRecorded(() -> await(store.appendValue(mock(SingleFeatureBean.class), mock(Object.class))), "appendValue", CALLS_COUNT);
    }

    @Test
    void testAppendAllValues() {
        assertRecorded(() -> await(store.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList())), "appendAllValues", CALLS_COUNT);
    }

    @Test
    void testRemoveValueMany() {
        assertRecorded(() -> await(store.removeValue(mock(ManyFeatureBean.class))), "removeValue", CALLS_COUNT);
    }

    @Test
    void testRemoveAllValues() {
        assertRecorded(() -> await(store.removeAllValues(mock(SingleFeatureBean.class))), "removeAllValues", CALLS_COUNT);
    }

    @Test
    void testSizeOfValue() {
        assertRecorded(() -> await(store.sizeOfValue(mock(SingleFeatureBean.class))), "sizeOfValue", CALLS_COUNT);
    }

    @Test
    void testReferenceOfMany() {
        assertRecorded(() -> await(store.referenceOf(mock(ManyFeatureBean.class))), "referenceOf", CALLS_COUNT);
    }

    @Test
    void testAllReferencesOf() {
        assertRecorded(() -> await(store.allReferencesOf(mock(SingleFeatureBean.class))), "allReferencesOf", CALLS_COUNT);
    }

    @Test
    void testReferenceForMany() {
        assertRecorded(() -> await(store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class))), "referenceFor", CALLS_COUNT);
    }

    @Test
    void testAddReference() {
        assertRecorded(() -> await(store.addReference(mock(ManyFeatureBean.class), mock(Id.class))), "addReference", CALLS_COUNT);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() {
        assertRecorded(() -> await(store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class))), "addAllReferences", CALLS_COUNT);
    }

    @Test
    void testAppendReference() {
        assertRecorded(() -> await(store.appendReference(mock(SingleFeatureBean.class), mock(Id.class))), "appendReference", CALLS_COUNT);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() {
        assertRecorded(() -> await(store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class))), "appendAllReferences", CALLS_COUNT);
    }

    @Test
    void testRemoveReferenceMany() {
        assertRecorded(() -> await(store.removeReference(mock(ManyFeatureBean.class))), "removeReference", CALLS_COUNT);
    }

    @Test
    void testRemoveAllReferences() {
        assertRecorded(() -> await(store.removeAllReferences(mock(SingleFeatureBean.class))), "removeAllReferences", CALLS_COUNT);
    }

    @Test
    void testSizeOfReference() {
        assertRecorded(() -> await(store.sizeOfReference(mock(SingleFeatureBean.class))), "sizeOfReference", CALLS_COUNT);
    }
}