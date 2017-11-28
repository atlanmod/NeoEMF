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

import io.reactivex.observers.TestObserver;

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

    private void assertHasError(TestObserver<?> observer) throws InterruptedException {
        observer.await().assertError(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testClose() {
        assertThat(
                catchThrowable(() -> invalidBackend.close())
        ).isNull();
    }

    @Test
    void testSave() {
        assertThat(
                catchThrowable(() -> invalidBackend.save())
        ).isNull();
    }

    @Test
    void testContainerOf() throws InterruptedException {
        assertHasError(invalidBackend.containerOf(mock(Id.class)).test());
    }

    @Test
    void testContainerFor() throws InterruptedException {
        assertHasError(invalidBackend.containerFor(mock(Id.class), mock(SingleFeatureBean.class)).test().await().assertError(INVALID_EXCEPTION_TYPE));
    }

    @Test
    void testRemoveContainer() throws InterruptedException {
        assertHasError(invalidBackend.removeContainer(mock(Id.class)).test().await().assertError(INVALID_EXCEPTION_TYPE));
    }

    @Test
    void testMetaClassOf() throws InterruptedException {
        assertHasError(invalidBackend.metaClassOf(mock(Id.class)).test());
    }

    @Test
    void testmMetaClassFor() throws InterruptedException {
        assertHasError(invalidBackend.metaClassFor(mock(Id.class), mock(ClassBean.class)).test());
    }

    @Test
    void testValueOf() {
        assertThat(
                catchThrowable(() -> invalidBackend.valueOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testValueFor() {
        assertThat(
                catchThrowable(() -> invalidBackend.valueFor(mock(SingleFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValue() {
        assertThat(
                catchThrowable(() -> invalidBackend.removeValue(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOf() {
        assertThat(
                catchThrowable(() -> invalidBackend.referenceOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceFor() {
        assertThat(
                catchThrowable(() -> invalidBackend.referenceFor(mock(SingleFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReference() {
        assertThat(
                catchThrowable(() -> invalidBackend.removeReference(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testValueOfMany() {
        assertThat(
                catchThrowable(() -> invalidBackend.valueOf(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAllValuesOf() {
        assertThat(
                catchThrowable(() -> invalidBackend.allValuesOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void tetsValueForMany() {
        assertThat(
                catchThrowable(() -> invalidBackend.valueFor(mock(ManyFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAddValue() {
        assertThat(
                catchThrowable(() -> invalidBackend.addValue(mock(ManyFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAddAllValues() {
        assertThat(
                catchThrowable(() -> invalidBackend.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList()))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAppendValue() {
        assertThat(
                catchThrowable(() -> invalidBackend.appendValue(mock(SingleFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAppendAllValues() {
        assertThat(
                catchThrowable(() -> invalidBackend.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList()))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValueMany() {
        assertThat(
                catchThrowable(() -> invalidBackend.removeValue(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllValues() {
        assertThat(
                catchThrowable(() -> invalidBackend.removeAllValues(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfValue() {
        assertThat(
                catchThrowable(() -> invalidBackend.sizeOfValue(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOfMany() {
        assertThat(
                catchThrowable(() -> invalidBackend.referenceOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAllReferencesOf() {
        assertThat(
                catchThrowable(() -> invalidBackend.allReferencesOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceForMany() {
        assertThat(
                catchThrowable(() -> invalidBackend.referenceFor(mock(ManyFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAddReference() {
        assertThat(
                catchThrowable(() -> invalidBackend.addReference(mock(ManyFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() {
        assertThat(
                catchThrowable(() -> invalidBackend.addAllReferences(mock(ManyFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testAppendReference() {
        assertThat(
                catchThrowable(() -> invalidBackend.appendReference(mock(SingleFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() {
        assertThat(
                catchThrowable(() -> invalidBackend.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReferenceMany() {
        assertThat(
                catchThrowable(() -> invalidBackend.removeReference(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllReferences() {
        assertThat(
                catchThrowable(() -> invalidBackend.removeAllReferences(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfReference() {
        assertThat(
                catchThrowable(() -> invalidBackend.sizeOfReference(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(INVALID_EXCEPTION_TYPE);
    }
}