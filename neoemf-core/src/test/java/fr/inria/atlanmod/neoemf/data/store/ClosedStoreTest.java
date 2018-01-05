/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.RxTestUtils.await;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link ClosedStore}.
 */
@ParametersAreNonnullByDefault
class ClosedStoreTest extends AbstractTest {

    private static final Class<? extends Throwable> CLOSED_EXCEPTION_TYPE = IllegalStateException.class;

    private Store store;

    @BeforeEach
    void setUp() {
        store = new ClosedStore();
    }

    @Test
    void testClose() {
        assertThat(
                catchThrowable(() -> store.close())
        ).isNull();
    }

    @Test
    void testSave() {
        await(store.save()).assertNoErrors();
    }

    @Test
    void testContainerOf() {
        await(store.containerOf(mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testContainerFor() {
        await(store.containerFor(mock(Id.class), mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveContainer() {
        await(store.removeContainer(mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testMetaClassOf() {
        await(store.metaClassOf(mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testMetaClassFor() {
        await(store.metaClassFor(mock(Id.class), mock(ClassBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testValueOf() {
        await(store.valueOf(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testValueFor() {
        await(store.valueFor(mock(SingleFeatureBean.class), mock(Object.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValue() {
        await(store.removeValue(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOf() {
        await(store.referenceOf(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceFor() {
        await(store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReference() {
        await(store.removeReference(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testValueOfMany() {
        await(store.valueOf(mock(ManyFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAllValuesOf() {
        await(store.allValuesOf(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void tetsValueForMany() {
        await(store.valueFor(mock(ManyFeatureBean.class), mock(Object.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAddValue() {
        await(store.addValue(mock(ManyFeatureBean.class), mock(Object.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAddAllValues() {
        await(store.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList())).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAppendValue() {
        await(store.appendValue(mock(SingleFeatureBean.class), mock(Object.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAppendAllValues() {
        await(store.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList())).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValueMany() {
        await(store.removeValue(mock(ManyFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllValues() {
        await(store.removeAllValues(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfValue() {
        await(store.sizeOfValue(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOfMany() {
        await(store.referenceOf(mock(ManyFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAllReferencesOf() {
        await(store.allReferencesOf(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceForMany() {
        await(store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAddReference() {
        await(store.addReference(mock(ManyFeatureBean.class), mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() {
        await(store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testAppendReference() {
        await(store.appendReference(mock(SingleFeatureBean.class), mock(Id.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() {
        await(store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReferenceMany() {
        await(store.removeReference(mock(ManyFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllReferences() {
        await(store.removeAllReferences(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfReference() {
        await(store.sizeOfReference(mock(SingleFeatureBean.class))).assertError(CLOSED_EXCEPTION_TYPE);
    }
}