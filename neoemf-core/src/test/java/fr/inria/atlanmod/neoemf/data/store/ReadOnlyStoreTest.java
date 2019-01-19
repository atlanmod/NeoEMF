/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.atlanmod.commons.AbstractTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

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
        ImmutableConfig config = new BaseConfig<>().readOnly();
        assertThat(config.isReadOnly()).isTrue();

        store = StoreFactory.getInstance().createStore(mock(Backend.class), config);
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
        assertThat(
                catchThrowable(() -> store.save())
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testContainerOf() {
        assertThat(
                catchThrowable(() -> store.containerOf(mock(Id.class)))
        ).isNull();
    }

    @Test
    void testContainerFor() {
        assertThat(
                catchThrowable(() -> store.containerFor(mock(Id.class), mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveContainer() {
        assertThat(
                catchThrowable(() -> store.removeContainer(mock(Id.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testMetaClassOf() {
        assertThat(
                catchThrowable(() -> store.metaClassOf(mock(Id.class)))
        ).isNull();
    }

    @Test
    void testmMetaClassFor() {
        assertThat(
                catchThrowable(() -> store.metaClassFor(mock(Id.class), mock(ClassBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testValueOf() {
        assertThat(
                catchThrowable(() -> store.valueOf(mock(SingleFeatureBean.class)))
        ).isNull();
    }

    @Test
    void testValueFor() {
        assertThat(
                catchThrowable(() -> store.valueFor(mock(SingleFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveValue() {
        assertThat(
                catchThrowable(() -> store.removeValue(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testReferenceOf() {
        assertThat(
                catchThrowable(() -> store.referenceOf(mock(SingleFeatureBean.class)))
        ).isNull();
    }

    @Test
    void testReferenceFor() {
        assertThat(
                catchThrowable(() -> store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testRemoveReference() {
        assertThat(
                catchThrowable(() -> store.removeReference(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
    }

    @Test
    void testValueOfMany() {
        assertThat(
                catchThrowable(() -> store.valueOf(mock(ManyFeatureBean.class)))
        ).isNull();
    }

    @Test
    void testAllValuesOf() {
        assertThat(
                catchThrowable(() -> store.allValuesOf(mock(SingleFeatureBean.class)))
        ).isNull();
    }

    @Test
    void tetsValueForMany() {
        assertThat(
                catchThrowable(() -> store.valueFor(mock(ManyFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
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
    void testSizeOfValue() {
        assertThat(
                catchThrowable(() -> store.sizeOfValue(mock(SingleFeatureBean.class)))
        ).isNull();
    }

    @Test
    void testReferenceOfMany() {
        assertThat(
                catchThrowable(() -> store.referenceOf(mock(SingleFeatureBean.class)))
        ).isNull();
    }

    @Test
    void testAllReferencesOf() {
        assertThat(
                catchThrowable(() -> store.allReferencesOf(mock(SingleFeatureBean.class)))
        ).isNull();
    }

    @Test
    void testReferenceForMany() {
        assertThat(
                catchThrowable(() -> store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(READONLY_EXCEPTION_TYPE);
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
    void testSizeOfReference() {
        assertThat(
                catchThrowable(() -> store.sizeOfReference(mock(SingleFeatureBean.class)))
        ).isNull();
    }
}