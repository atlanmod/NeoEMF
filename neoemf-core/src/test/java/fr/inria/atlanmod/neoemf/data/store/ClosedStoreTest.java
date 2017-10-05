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

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link ClosedStore}.
 */
@ParametersAreNonnullByDefault
public class ClosedStoreTest extends AbstractTest {

    private static final Class<? extends Throwable> CLOSED_EXCEPTION_TYPE = ClosedStoreException.class;

    private Store store;

    @BeforeEach
    void setUp() {
        store = new ClosedStore();
    }

    @Test
    public void testClose() throws Exception {
        assertThat(
                catchThrowable(() -> store.close())
        ).isNull();
    }

    @Test
    public void testSave() throws Exception {
        assertThat(
                catchThrowable(() -> store.save())
        ).isNull();
    }

    @Test
    public void testContainerOf() throws Exception {
        assertThat(
                catchThrowable(() -> store.containerOf(mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testContainerFor() throws Exception {
        assertThat(
                catchThrowable(() -> store.containerFor(mock(Id.class), mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveContainer() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeContainer(mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testMetaClassOf() throws Exception {
        assertThat(
                catchThrowable(() -> store.metaClassOf(mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testmMetaClassFor() throws Exception {
        assertThat(
                catchThrowable(() -> store.metaClassFor(mock(Id.class), mock(ClassBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testValueOf() throws Exception {
        assertThat(
                catchThrowable(() -> store.valueOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testValueFor() throws Exception {
        assertThat(
                catchThrowable(() -> store.valueFor(mock(SingleFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveValue() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeValue(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testReferenceOf() throws Exception {
        assertThat(
                catchThrowable(() -> store.referenceOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testReferenceFor() throws Exception {
        assertThat(
                catchThrowable(() -> store.referenceFor(mock(SingleFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveReference() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeReference(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testValueOfMany() throws Exception {
        assertThat(
                catchThrowable(() -> store.valueOf(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAllValuesOf() throws Exception {
        assertThat(
                catchThrowable(() -> store.allValuesOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void tetsValueForMany() throws Exception {
        assertThat(
                catchThrowable(() -> store.valueFor(mock(ManyFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAddValue() throws Exception {
        assertThat(
                catchThrowable(() -> store.addValue(mock(ManyFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAddAllValues() throws Exception {
        assertThat(
                catchThrowable(() -> store.addAllValues(mock(ManyFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAppendValue() throws Exception {
        assertThat(
                catchThrowable(() -> store.appendValue(mock(SingleFeatureBean.class), mock(Object.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAppendAllValues() throws Exception {
        assertThat(
                catchThrowable(() -> store.appendAllValues(mock(SingleFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveValueMany() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeValue(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveAllValues() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeAllValues(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testSizeOfValue() throws Exception {
        assertThat(
                catchThrowable(() -> store.sizeOfValue(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testReferenceOfMany() throws Exception {
        assertThat(
                catchThrowable(() -> store.referenceOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAllReferencesOf() throws Exception {
        assertThat(
                catchThrowable(() -> store.allReferencesOf(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testReferenceForMany() throws Exception {
        assertThat(
                catchThrowable(() -> store.referenceFor(mock(ManyFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAddReference() throws Exception {
        assertThat(
                catchThrowable(() -> store.addReference(mock(ManyFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddAllReferences() throws Exception {
        assertThat(
                catchThrowable(() -> store.addAllReferences(mock(ManyFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testAppendReference() throws Exception {
        assertThat(
                catchThrowable(() -> store.appendReference(mock(SingleFeatureBean.class), mock(Id.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAppendAllReferences() throws Exception {
        assertThat(
                catchThrowable(() -> store.appendAllReferences(mock(SingleFeatureBean.class), mock(List.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveReferenceMany() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeReference(mock(ManyFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testRemoveAllReferences() throws Exception {
        assertThat(
                catchThrowable(() -> store.removeAllReferences(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }

    @Test
    public void testSizeOfReference() throws Exception {
        assertThat(
                catchThrowable(() -> store.sizeOfReference(mock(SingleFeatureBean.class)))
        ).isExactlyInstanceOf(CLOSED_EXCEPTION_TYPE);
    }
}