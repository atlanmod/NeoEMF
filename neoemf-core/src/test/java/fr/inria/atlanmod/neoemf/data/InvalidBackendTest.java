/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

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
 * A test-case about {@link InvalidBackend}.
 */
@ParametersAreNonnullByDefault
class InvalidBackendTest extends AbstractTest {

    private static final Class<? extends Throwable> INVALID_EXCEPTION_TYPE = UnsupportedOperationException.class;

    private Backend invalidBackend;

    @BeforeEach
    void setUp() {
        invalidBackend = new InvalidBackend("");
    }

    @Test
    void testClose() {
        assertThat(
                catchThrowable(() -> invalidBackend.close())
        ).isNull();
    }

    @Test
    void testSave() {
        await(invalidBackend.save()).assertNoErrors();
    }

    @Test
    void testContainerOf() {
        await(invalidBackend.containerOf(mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testContainerFor() {
        await(invalidBackend.containerFor(mock(Id.class), mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveContainer() {
        await(invalidBackend.removeContainer(mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testMetaClassOf() {
        await(invalidBackend.metaClassOf(mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testmMetaClassFor() {
        await(invalidBackend.metaClassFor(mock(Id.class), mock(ClassBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testValueOf() {
        await(invalidBackend.valueOf(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testValueFor() {
        await(invalidBackend.valueFor(mock(SingleFeatureBean.class), mock(Object.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValue() {
        await(invalidBackend.removeValue(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOf() {
        await(invalidBackend.referenceOf(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceFor() {
        await(invalidBackend.referenceFor(mock(SingleFeatureBean.class), mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReference() {
        await(invalidBackend.removeReference(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testValueOfMany() {
        await(invalidBackend.valueOf(mock(ManyFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAllValuesOf() {
        await(invalidBackend.allValuesOf(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void tetsValueForMany() {
        await(invalidBackend.valueFor(mock(ManyFeatureBean.class), mock(Object.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAddValue() {
        await(invalidBackend.addValue(mock(ManyFeatureBean.class), mock(Object.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAddAllValues() {
        await(invalidBackend.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList())).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAppendValue() {
        await(invalidBackend.appendValue(mock(SingleFeatureBean.class), mock(Object.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAppendAllValues() {
        await(invalidBackend.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList())).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValueMany() {
        await(invalidBackend.removeValue(mock(ManyFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllValues() {
        await(invalidBackend.removeAllValues(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfValue() {
        await(invalidBackend.sizeOfValue(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOfMany() {
        await(invalidBackend.referenceOf(mock(ManyFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAllReferencesOf() {
        await(invalidBackend.allReferencesOf(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceForMany() {
        await(invalidBackend.referenceFor(mock(ManyFeatureBean.class), mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAddReference() {
        await(invalidBackend.addReference(mock(ManyFeatureBean.class), mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() {
        await(invalidBackend.addAllReferences(mock(ManyFeatureBean.class), mock(List.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAppendReference() {
        await(invalidBackend.appendReference(mock(SingleFeatureBean.class), mock(Id.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() {
        await(invalidBackend.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReferenceMany() {
        await(invalidBackend.removeReference(mock(ManyFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllReferences() {
        await(invalidBackend.removeAllReferences(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfReference() {
        await(invalidBackend.sizeOfReference(mock(SingleFeatureBean.class))).assertError(INVALID_EXCEPTION_TYPE);
    }
}