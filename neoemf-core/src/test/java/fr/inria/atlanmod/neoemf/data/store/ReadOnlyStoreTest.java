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

import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.RxTestUtils.await;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link ReadOnlyStore}.
 */
@ParametersAreNonnullByDefault
class ReadOnlyStoreTest extends AbstractTest {

    private static final Class<? extends Throwable> READONLY_EXCEPTION_TYPE = UnsupportedOperationException.class;

    private Store store;

    @BeforeEach
    void setUp() {
        ImmutableConfig config = BaseConfig.newConfig().readOnly();
        assertThat(config.isReadOnly()).isTrue();

        store = StoreFactory.getInstance().createStore(new NoopBackend(), config);
    }

    @AfterEach
    void tearDown() {
        if (nonNull(store)) {
            store.close();
        }
    }

    @Test
    void testClose() {
        assertThat(
                catchThrowable(() -> store.close())
        ).isNull();
    }

    @Test
    void testSave() {
        await(store.save()).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testContainerOf() {
        await(store.containerOf(mock(Id.class))).assertNoErrors();
    }

    @Test
    void testContainerFor() {
        await(store.containerFor(mock(Id.class), mock(SingleFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveContainer() {
        await(store.removeContainer(mock(Id.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testMetaClassOf() {
        await(store.metaClassOf(mock(Id.class))).assertNoErrors();
    }

    @Test
    void testMetaClassFor() {
        await(store.metaClassFor(mock(Id.class), mock(ClassBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testValueOf() {
        await(store.valueOf(mock(SingleFeatureBean.class))).assertNoErrors();
    }

    @Test
    void testValueFor() {
        await(store.valueFor(mock(SingleFeatureBean.class), mock(Object.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValue() {
        await(store.removeValue(mock(SingleFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOf() {
        await(store.referenceOf(mock(SingleFeatureBean.class))).assertNoErrors();
    }

    @Test
    void testReferenceFor() {
        await(store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReference() {
        await(store.removeReference(mock(SingleFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testValueOfMany() {
        await(store.valueOf(mock(ManyFeatureBean.class))).assertNoErrors();
    }

    @Test
    void testAllValuesOf() {
        await(store.allValuesOf(mock(SingleFeatureBean.class))).assertNoErrors();
    }

    @Test
    void tetsValueForMany() {
        await(store.valueFor(mock(ManyFeatureBean.class), mock(Object.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAddValue() {
        await(store.addValue(mock(ManyFeatureBean.class), mock(Object.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAddAllValues() {
        await(store.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList())).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAppendValue() {
        await(store.appendValue(mock(SingleFeatureBean.class), mock(Object.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAppendAllValues() {
        await(store.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList())).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValueMany() {
        await(store.removeValue(mock(ManyFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllValues() {
        await(store.removeAllValues(mock(SingleFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfValue() {
        await(store.sizeOfValue(mock(SingleFeatureBean.class))).assertNoErrors();
    }

    @Test
    void testReferenceOfMany() {
        await(store.referenceOf(mock(ManyFeatureBean.class))).assertNoErrors();
    }

    @Test
    void testAllReferencesOf() {
        await(store.allReferencesOf(mock(SingleFeatureBean.class))).assertNoErrors();
    }

    @Test
    void testReferenceForMany() {
        await(store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAddReference() {
        await(store.addReference(mock(ManyFeatureBean.class), mock(Id.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() {
        await(store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAppendReference() {
        await(store.appendReference(mock(SingleFeatureBean.class), mock(Id.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() {
        await(store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReferenceMany() {
        await(store.removeReference(mock(ManyFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllReferences() {
        await(store.removeAllReferences(mock(SingleFeatureBean.class))).assertError(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfReference() {
        await(store.sizeOfReference(mock(SingleFeatureBean.class))).assertNoErrors();
    }
}