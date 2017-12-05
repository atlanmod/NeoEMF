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

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

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

    private void assertHasError(TestObserver<?> observer) throws InterruptedException {
        observer.await().assertError(READONLY_EXCEPTION_TYPE);
    }

    private void assertNoError(TestObserver<?> observer) throws InterruptedException {
        observer.await().assertNoErrors();
    }

    private void assertNoError(TestSubscriber<?> subscriber) throws InterruptedException {
        subscriber.await().assertNoErrors();
    }

    @Test
    void testClose() {
        assertThat(
                catchThrowable(() -> store.close())
        ).isNull();
    }

    @Test
    void testSave() {
        assertThat(
                catchThrowable(() -> store.save())
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testContainerOf() throws InterruptedException {
        assertNoError(store.containerOf(mock(Id.class)).test());
    }

    @Test
    void testContainerFor() throws InterruptedException {
        assertHasError(store.containerFor(mock(Id.class), mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testRemoveContainer() throws InterruptedException {
        assertHasError(store.removeContainer(mock(Id.class)).test());
    }

    @Test
    void testMetaClassOf() throws InterruptedException {
        assertNoError(store.metaClassOf(mock(Id.class)).test());
    }

    @Test
    void testMetaClassFor() throws InterruptedException {
        assertHasError(store.metaClassFor(mock(Id.class), mock(ClassBean.class)).test());
    }

    @Test
    void testValueOf() throws InterruptedException {
        assertNoError(store.valueOf(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testValueFor() throws InterruptedException {
        assertHasError(store.valueFor(mock(SingleFeatureBean.class), mock(Object.class)).test());
    }

    @Test
    void testRemoveValue() throws InterruptedException {
        assertHasError(store.removeValue(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testReferenceOf() throws InterruptedException {
        assertNoError(store.referenceOf(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testReferenceFor() throws InterruptedException {
        assertHasError(store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class)).test());
    }

    @Test
    void testRemoveReference() throws InterruptedException {
        assertHasError(store.removeReference(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testValueOfMany() throws InterruptedException {
        assertNoError(store.valueOf(mock(ManyFeatureBean.class)).test());
    }

    @Test
    void testAllValuesOf() throws InterruptedException {
        assertNoError(store.allValuesOf(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void tetsValueForMany() throws InterruptedException {
        assertHasError(store.valueFor(mock(ManyFeatureBean.class), mock(Object.class)).test());
    }

    @Test
    void testAddValue() {
        assertThat(
                catchThrowable(() -> store.addValue(mock(ManyFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAddAllValues() {
        assertThat(
                catchThrowable(() -> store.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList()))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAppendValue() {
        assertThat(
                catchThrowable(() -> store.appendValue(mock(SingleFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAppendAllValues() {
        assertThat(
                catchThrowable(() -> store.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList()))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValueMany() {
        assertThat(
                catchThrowable(() -> store.removeValue(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllValues() {
        assertThat(
                catchThrowable(() -> store.removeAllValues(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfValue() throws InterruptedException {
        assertNoError(store.sizeOfValue(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testReferenceOfMany() throws InterruptedException {
        assertNoError(store.referenceOf(mock(ManyFeatureBean.class)).test());
    }

    @Test
    void testAllReferencesOf() throws InterruptedException {
        assertNoError(store.allReferencesOf(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testReferenceForMany() throws InterruptedException {
        assertHasError(store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class)).test());
    }

    @Test
    void testAddReference() {
        assertThat(
                catchThrowable(() -> store.addReference(mock(ManyFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() {
        assertThat(
                catchThrowable(() -> store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testAppendReference() {
        assertThat(
                catchThrowable(() -> store.appendReference(mock(SingleFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() {
        assertThat(
                catchThrowable(() -> store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReferenceMany() {
        assertThat(
                catchThrowable(() -> store.removeReference(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveAllReferences() {
        assertThat(
                catchThrowable(() -> store.removeAllReferences(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testSizeOfReference() throws InterruptedException {
        assertNoError(store.sizeOfReference(mock(SingleFeatureBean.class)).test());
    }
}