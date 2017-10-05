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
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link StatsRecordStore}.
 */
@ParametersAreNonnullByDefault
public class StatsRecordStoreTest extends AbstractTest {

    private static final long CALLS_COUNT = 2L;

    private Store store;

    @BeforeEach
    void setUp() {
        ImmutableConfig config = BaseConfig.newConfig().recordStats();

        store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
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
     * Calls the {@code runnable}, and checks the results in the resulting {@link StoreStats}.
     */
    private void assertRecorded(Runnable runnable, String name, long count) {
        for (int i = 0; i < count; i++){
            runnable.run();
        }

        StoreStats stats = store.stats();
        assertThat(stats.methodCalls()).containsOnly(createEntry(name, count));
    }

    @Test
    public void testNoStats() throws Exception {
        ImmutableConfig config = BaseConfig.newConfig();

        Store store = StoreFactory.getInstance().createStore(mock(Backend.class), config);

        // Do a call
        store.save();

        StoreStats stats = store.stats();
        assertThat(stats).isNotNull();
        assertThat(stats.methodCalls()).isEmpty();
    }

    @Test
    public void testClose() throws Exception {
        store.close();

        StoreStats stats = store.stats();
        assertThat(stats.methodCalls()).isEmpty();
    }

    @Test
    public void testSave() throws Exception {
        assertRecorded(() -> store.save(), "save", CALLS_COUNT);
    }

    @Test
    public void testContainerOf() throws Exception {
        assertRecorded(() -> store.containerOf(mock(Id.class)), "containerOf", CALLS_COUNT);
    }

    @Test
    public void testContainerFor() throws Exception {
        assertRecorded(() -> store.containerFor(mock(Id.class), mock(SingleFeatureBean.class)), "containerFor", CALLS_COUNT);
    }

    @Test
    public void testRemoveContainer() throws Exception {
        assertRecorded(() -> store.removeContainer(mock(Id.class)), "removeContainer", CALLS_COUNT);
    }

    @Test
    public void testMetaClassOf() throws Exception {
        assertRecorded(() -> store.metaClassOf(mock(Id.class)), "metaClassOf", CALLS_COUNT);
    }

    @Test
    public void testmMetaClassFor() throws Exception {
        assertRecorded(() -> store.metaClassFor(mock(Id.class), mock(ClassBean.class)), "metaClassFor", CALLS_COUNT);
    }

    @Test
    public void testValueOf() throws Exception {
        assertRecorded(() -> store.valueOf(mock(SingleFeatureBean.class)), "valueOf", CALLS_COUNT);
    }

    @Test
    public void testValueFor() throws Exception {
        assertRecorded(() -> store.valueFor(mock(SingleFeatureBean.class), mock(Object.class)), "valueFor", CALLS_COUNT);
    }

    @Test
    public void testRemoveValue() throws Exception {
        assertRecorded(() -> store.removeValue(mock(SingleFeatureBean.class)), "removeValue", CALLS_COUNT);
    }

    @Test
    public void testReferenceOf() throws Exception {
        assertRecorded(() -> store.referenceOf(mock(SingleFeatureBean.class)), "referenceOf", CALLS_COUNT);
    }

    @Test
    public void testReferenceFor() throws Exception {
        assertRecorded(() -> store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class)), "referenceFor", CALLS_COUNT);
    }

    @Test
    public void testRemoveReference() throws Exception {
        assertRecorded(() -> store.removeReference(mock(SingleFeatureBean.class)), "removeReference", CALLS_COUNT);
    }

    @Test
    public void testValueOfMany() throws Exception {
        assertRecorded(() -> store.valueOf(mock(ManyFeatureBean.class)), "valueOf", CALLS_COUNT);
    }

    @Test
    public void testAllValuesOf() throws Exception {
        assertRecorded(() -> store.allValuesOf(mock(SingleFeatureBean.class)), "allValuesOf", CALLS_COUNT);
    }

    @Test
    public void tetsValueForMany() throws Exception {
        assertRecorded(() -> store.valueFor(mock(ManyFeatureBean.class), mock(Object.class)), "valueFor", CALLS_COUNT);
    }

    @Test
    public void testAddValue() throws Exception {
        assertRecorded(() -> store.addValue(mock(ManyFeatureBean.class), mock(Object.class)), "addValue", CALLS_COUNT);
    }

    @Test
    public void testAddAllValues() throws Exception {
        assertRecorded(() -> store.addAllValues(mock(ManyFeatureBean.class), mock(List.class)), "addAllValues", CALLS_COUNT);
    }

    @Test
    public void testAppendValue() throws Exception {
        assertRecorded(() -> store.appendValue(mock(SingleFeatureBean.class), mock(Object.class)), "appendValue", CALLS_COUNT);
    }

    @Test
    public void testAppendAllValues() throws Exception {
        assertRecorded(() -> store.appendAllValues(mock(SingleFeatureBean.class), mock(List.class)), "appendAllValues", CALLS_COUNT);
    }

    @Test
    public void testRemoveValueMany() throws Exception {
        assertRecorded(() -> store.removeValue(mock(ManyFeatureBean.class)), "removeValue", CALLS_COUNT);
    }

    @Test
    public void testRemoveAllValues() throws Exception {
        assertRecorded(() -> store.removeAllValues(mock(SingleFeatureBean.class)), "removeAllValues", CALLS_COUNT);
    }

    @Test
    public void testSizeOfValue() throws Exception {
        assertRecorded(() -> store.sizeOfValue(mock(SingleFeatureBean.class)), "sizeOfValue", CALLS_COUNT);
    }

    @Test
    public void testReferenceOfMany() throws Exception {
        assertRecorded(() -> store.referenceOf(mock(SingleFeatureBean.class)), "referenceOf", CALLS_COUNT);
    }

    @Test
    public void testAllReferencesOf() throws Exception {
        assertRecorded(() -> store.allReferencesOf(mock(SingleFeatureBean.class)), "allReferencesOf", CALLS_COUNT);
    }

    @Test
    public void testReferenceForMany() throws Exception {
        assertRecorded(() -> store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class)), "referenceFor", CALLS_COUNT);
    }

    @Test
    public void testAddReference() throws Exception {
        assertRecorded(() -> store.addReference(mock(ManyFeatureBean.class), mock(Id.class)), "addReference", CALLS_COUNT);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddAllReferences() throws Exception {
        assertRecorded(() -> store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class)), "addAllReferences", CALLS_COUNT);
    }

    @Test
    public void testAppendReference() throws Exception {
        assertRecorded(() -> store.appendReference(mock(SingleFeatureBean.class), mock(Id.class)), "appendReference", CALLS_COUNT);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAppendAllReferences() throws Exception {
        assertRecorded(() -> store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class)), "appendAllReferences", CALLS_COUNT);
    }

    @Test
    public void testRemoveReferenceMany() throws Exception {
        assertRecorded(() -> store.removeReference(mock(ManyFeatureBean.class)), "removeReference", CALLS_COUNT);
    }

    @Test
    public void testRemoveAllReferences() throws Exception {
        assertRecorded(() -> store.removeAllReferences(mock(SingleFeatureBean.class)), "removeAllReferences", CALLS_COUNT);
    }

    @Test
    public void testSizeOfReference() throws Exception {
        assertRecorded(() -> store.sizeOfReference(mock(SingleFeatureBean.class)), "sizeOfReference", CALLS_COUNT);
    }
}