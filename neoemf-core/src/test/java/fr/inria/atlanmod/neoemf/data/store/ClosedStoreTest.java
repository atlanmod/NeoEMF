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
import io.reactivex.subscribers.TestSubscriber;

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

    private void assertHasError(TestObserver<?> observer) throws InterruptedException {
        observer.await().assertError(CLOSED_EXCEPTION_TYPE);
    }

    private void assertHasError(TestSubscriber<?> subscriber) throws InterruptedException {
        subscriber.await().assertError(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    void testClose() {
        assertThat(
                catchThrowable(() -> store.close())
        ).isNull();
    }

    @Test
    void testSave() throws InterruptedException {
        store.save().test().await().assertNoErrors().assertComplete();
    }

    @Test
    void testContainerOf() throws InterruptedException {
        assertHasError(store.containerOf(mock(Id.class)).test());
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
        assertHasError(store.metaClassOf(mock(Id.class)).test());
    }

    @Test
    void testMetaClassFor() throws InterruptedException {
        assertHasError(store.metaClassFor(mock(Id.class), mock(ClassBean.class)).test());
    }

    @Test
    void testValueOf() throws InterruptedException {
        assertHasError(store.valueOf(mock(SingleFeatureBean.class)).test());
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
        assertHasError(store.referenceOf(mock(SingleFeatureBean.class)).test());
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
        assertHasError(store.valueOf(mock(ManyFeatureBean.class)).test());
    }

    @Test
    void testAllValuesOf() throws InterruptedException {
        assertHasError(store.allValuesOf(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void tetsValueForMany() throws InterruptedException {
        assertHasError(store.valueFor(mock(ManyFeatureBean.class), mock(Object.class)).test());
    }

    @Test
    void testAddValue() throws InterruptedException {
        assertHasError(store.addValue(mock(ManyFeatureBean.class), mock(Object.class)).test());
    }

    @Test
    void testAddAllValues() throws InterruptedException {
        assertHasError(store.addAllValues(mock(ManyFeatureBean.class), Collections.emptyList()).test());
    }

    @Test
    void testAppendValue() throws InterruptedException {
        assertHasError(store.appendValue(mock(SingleFeatureBean.class), mock(Object.class)).test());
    }

    @Test
    void testAppendAllValues() throws InterruptedException {
        assertHasError(store.appendAllValues(mock(SingleFeatureBean.class), Collections.emptyList()).test());
    }

    @Test
    void testRemoveValueMany() throws InterruptedException {
        assertHasError(store.removeValue(mock(ManyFeatureBean.class)).test());
    }

    @Test
    void testRemoveAllValues() throws InterruptedException {
        assertHasError(store.removeAllValues(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testSizeOfValue() throws InterruptedException {
        assertHasError(store.sizeOfValue(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testReferenceOfMany() throws InterruptedException {
        assertHasError(store.referenceOf(mock(ManyFeatureBean.class)).test());
    }

    @Test
    void testAllReferencesOf() throws InterruptedException {
        assertHasError(store.allReferencesOf(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testReferenceForMany() throws InterruptedException {
        assertHasError(store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class)).test());
    }

    @Test
    void testAddReference() throws InterruptedException {
        assertHasError(store.addReference(mock(ManyFeatureBean.class), mock(Id.class)).test());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAddAllReferences() throws InterruptedException {
        assertHasError(store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class)).test());
    }

    @Test
    void testAppendReference() throws InterruptedException {
        assertHasError(store.appendReference(mock(SingleFeatureBean.class), mock(Id.class)).test());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAppendAllReferences() throws InterruptedException {
        assertHasError(store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class)).test());
    }

    @Test
    void testRemoveReferenceMany() throws InterruptedException {
        assertHasError(store.removeReference(mock(ManyFeatureBean.class)).test());
    }

    @Test
    void testRemoveAllReferences() throws InterruptedException {
        assertHasError(store.removeAllReferences(mock(SingleFeatureBean.class)).test());
    }

    @Test
    void testSizeOfReference() throws InterruptedException {
        assertHasError(store.sizeOfReference(mock(SingleFeatureBean.class)).test());
    }
}