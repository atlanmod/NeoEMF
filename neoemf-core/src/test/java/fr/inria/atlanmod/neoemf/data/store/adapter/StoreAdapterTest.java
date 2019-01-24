/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.store.ClosedStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;

import org.atlanmod.commons.AbstractTest;
import org.atlanmod.commons.cache.Cache;
import org.atlanmod.commons.cache.CacheBuilder;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A test-case about {@link AbstractStoreAdapter}.
 */
@ParametersAreNonnullByDefault
class StoreAdapterTest extends AbstractTest {

    @Nonnull
    private static final String TEST_NAME_PATTERN = "[{index}] With {0}";

    private static PersistentEObject object;

    private static Id id;

    private static EClass eClass;

    private AbstractStoreAdapter store;

    @BeforeAll
    static void initMocks() {
        String ePackageUri = "http://MyUri";
        String eClassName = "MyClass";

        EFactory eFactory = mock(EFactory.class);
        when(eFactory.create(any(EClass.class))).thenAnswer((Answer<PersistentEObject>) i -> {
            PersistentEObject result = new DefaultPersistentEObject();
            result.eSetClass(i.getArgument(0));
            return result;
        });

        EPackage ePackage = mock(EPackage.class);
        when(ePackage.getNsURI()).thenReturn(ePackageUri);
        when(ePackage.getEFactoryInstance()).thenReturn(eFactory);

        EPackage.Registry.INSTANCE.put(ePackageUri, ePackage);

        eClass = mock(EClass.class);
        when(eClass.getEPackage()).thenReturn(ePackage);
        when(eClass.getName()).thenReturn(eClassName);

        when(ePackage.getEClassifier(eClassName)).thenReturn(eClass);

        id = Id.getProvider().fromLong(17L);

        object = mock(DefaultPersistentEObject.class);
        when(object.id()).thenReturn(id);
        when(object.eClass()).thenReturn(eClass);
    }

    @BeforeEach
    void initStoreAdapter() {
        Store innerStore = StoreFactory.getInstance().createStore(new DefaultInMemoryBackend(), new BaseConfig<>());

        store = new AbstractStoreAdapter(innerStore, null) {

            @Nonnull
            @Override
            protected Cache<Id, PersistentEObject> getCache() {
                return CacheBuilder.empty();
            }
        };

        store.updateInstanceOf(object);
    }

    @AfterEach
    void closeStoreAdapter() {
        if (nonNull(store)) {
            store.close();
        }
    }

    @Test
    void testClose() {
        assertThat(store.store()).isNotInstanceOf(ClosedStore.class);

        store.close();

        assertThat(store.store()).isExactlyInstanceOf(ClosedStore.class);
    }

    @Test
    void testSave() {
        // TODO
    }

    @Test
    void testResolve_NotDefined() {
        assertThat(
                catchThrowable(() -> store.resolve(Id.getProvider().generate()))
        ).isExactlyInstanceOf(NoSuchElementException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testGetSet_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.set(object, eFeature, -1, value0);

        Object resolvedValue0 = store.get(object, eFeature, -1);
        assertThat(resolvedValue0).isEqualTo(value0);

        store.set(object, eFeature, -1, value1);

        Object resolvedValue1 = store.get(object, eFeature, -1);
        assertThat(resolvedValue1).isEqualTo(value1);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testGetSet_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.set(object, eFeature, 0, value0))
        ).isExactlyInstanceOf(IndexOutOfBoundsException.class);

        store.add(object, eFeature, 0, value0);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value0);

        store.set(object, eFeature, 0, value1);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value1);

        store.set(object, eFeature, 0, null);
        assertThat(store.isSet(object, eFeature)).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testIsSetUnset_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.isSet(object, eFeature)).isFalse();

        store.set(object, eFeature, -1, value0);
        assertThat(store.isSet(object, eFeature)).isTrue();

        store.unset(object, eFeature);
        assertThat(store.isSet(object, eFeature)).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testIsSetUnset_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.isSet(object, eFeature)).isFalse();

        store.add(object, eFeature, 0, value0);
        assertThat(store.isSet(object, eFeature)).isTrue();

        store.add(object, eFeature, 1, value1);
        assertThat(store.isSet(object, eFeature)).isTrue();

        store.unset(object, eFeature);
        assertThat(store.isSet(object, eFeature)).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testIsEmpty_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.isEmpty(object, eFeature))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testIsEmpty_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.isEmpty(object, eFeature)).isTrue();

        store.add(object, eFeature, 0, value0);
        assertThat(store.isEmpty(object, eFeature)).isFalse();

        store.remove(object, eFeature, 0);
        assertThat(store.isEmpty(object, eFeature)).isTrue();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testSize_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.size(object, eFeature))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testSize_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.size(object, eFeature)).isEqualTo(0);

        store.add(object, eFeature, 0, value0);
        assertThat(store.size(object, eFeature)).isEqualTo(1);

        store.add(object, eFeature, 1, value1);
        assertThat(store.size(object, eFeature)).isEqualTo(2);

        store.remove(object, eFeature, 0);
        assertThat(store.size(object, eFeature)).isEqualTo(1);

        store.unset(object, eFeature);
        assertThat(store.size(object, eFeature)).isEqualTo(0);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testContains_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.contains(object, eFeature, value0))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testContains_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.contains(object, eFeature, null)).isFalse();

        assertThat(store.contains(object, eFeature, value0)).isFalse();
        assertThat(store.contains(object, eFeature, value1)).isFalse();

        store.add(object, eFeature, 0, value0);
        assertThat(store.contains(object, eFeature, value0)).isTrue();
        assertThat(store.contains(object, eFeature, value1)).isFalse();

        store.add(object, eFeature, 1, value1);
        assertThat(store.contains(object, eFeature, value0)).isTrue();
        assertThat(store.contains(object, eFeature, value1)).isTrue();

        store.remove(object, eFeature, 0);
        assertThat(store.contains(object, eFeature, value0)).isFalse();
        assertThat(store.contains(object, eFeature, value1)).isTrue();

        store.unset(object, eFeature);
        assertThat(store.contains(object, eFeature, value0)).isFalse();
        assertThat(store.contains(object, eFeature, value1)).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testIndexOf_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.indexOf(object, eFeature, value0))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testIndexOf_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.indexOf(object, eFeature, null)).isEqualTo(-1);

        assertThat(store.indexOf(object, eFeature, value0)).isEqualTo(-1);
        assertThat(store.indexOf(object, eFeature, value1)).isEqualTo(-1);

        store.add(object, eFeature, 0, value0);
        store.add(object, eFeature, 1, value1);
        store.add(object, eFeature, 2, value0);
        store.add(object, eFeature, 3, value1);

        assertThat(store.indexOf(object, eFeature, value0)).isEqualTo(0);
        assertThat(store.indexOf(object, eFeature, value1)).isEqualTo(1);

        store.remove(object, eFeature, 0);

        assertThat(store.indexOf(object, eFeature, value0)).isEqualTo(1);
        assertThat(store.indexOf(object, eFeature, value1)).isEqualTo(0);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testLastIndexOf_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.lastIndexOf(object, eFeature, value0))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testLastIndexOf_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.lastIndexOf(object, eFeature, null)).isEqualTo(-1);

        assertThat(store.lastIndexOf(object, eFeature, value0)).isEqualTo(-1);
        assertThat(store.lastIndexOf(object, eFeature, value1)).isEqualTo(-1);

        store.add(object, eFeature, 0, value0);
        store.add(object, eFeature, 1, value1);
        store.add(object, eFeature, 2, value0);
        store.add(object, eFeature, 3, value1);

        assertThat(store.lastIndexOf(object, eFeature, value0)).isEqualTo(2);
        assertThat(store.lastIndexOf(object, eFeature, value1)).isEqualTo(3);

        store.remove(object, eFeature, 3);

        assertThat(store.lastIndexOf(object, eFeature, value0)).isEqualTo(2);
        assertThat(store.lastIndexOf(object, eFeature, value1)).isEqualTo(1);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testAdd_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.add(object, eFeature, 0, value0))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testAdd_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.isEmpty(object, eFeature)).isTrue();

        assertThat(
                catchThrowable(() -> store.add(object, eFeature, 10, value0))
        ).isExactlyInstanceOf(IndexOutOfBoundsException.class);

        store.add(object, eFeature, 0, value0);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value0);

        store.add(object, eFeature, -1, value1);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value0);
        assertThat(store.get(object, eFeature, 1)).isEqualTo(value1);

        store.add(object, eFeature, 0, value1);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value1);
        assertThat(store.get(object, eFeature, 1)).isEqualTo(value0);
        assertThat(store.get(object, eFeature, 2)).isEqualTo(value1);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testRemove_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.remove(object, eFeature, 0))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testRemove_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        assertThat(store.get(object, eFeature, 0)).isEqualTo(value0);
        assertThat(store.get(object, eFeature, 1)).isEqualTo(value1);

        assertThat(store.remove(object, eFeature, 0)).isEqualTo(value0);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value1);

        assertThat(store.remove(object, eFeature, 0)).isEqualTo(value1);
        assertThat(store.isEmpty(object, eFeature)).isTrue();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testMove_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.move(object, eFeature, 1, 0))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testMove_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        assertThat(store.move(object, eFeature, 1, 0)).isEqualTo(value0);

        assertThat(store.get(object, eFeature, 0)).isEqualTo(value1);
        assertThat(store.get(object, eFeature, 1)).isEqualTo(value0);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testClear_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.clear(object, eFeature))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testClear_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        assertThat(store.size(object, eFeature)).isEqualTo(2);

        store.clear(object, eFeature);

        assertThat(store.isEmpty(object, eFeature)).isTrue();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testToArray_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.toArray(object, eFeature)).isEmpty();

        store.set(object, eFeature, -1, value0);

        Object[] resolvedArray = store.toArray(object, eFeature);
        assertThat(resolvedArray).hasSize(1);
        assertThat(resolvedArray[0]).isEqualTo(value0);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testToArray_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.toArray(object, eFeature)).isEmpty();

        store.addAll(object, eFeature, -1, Arrays.asList(value0, value1));

        Object[] resolvedArray0 = store.toArray(object, eFeature);
        assertThat(resolvedArray0).hasSize(2);
        assertThat(resolvedArray0[0]).isEqualTo(value0);
        assertThat(resolvedArray0[1]).isEqualTo(value1);

        Object[] baseArray = new Object[2];
        Object[] resolvedArray1 = store.toArray(object, eFeature, baseArray);
        assertThat(resolvedArray1).hasSize(2);
        assertThat(resolvedArray1[0]).isEqualTo(value0);
        assertThat(resolvedArray1[1]).isEqualTo(value1);
        assertThat(resolvedArray1).isSameAs(baseArray);
    }

    @Test
    void testGetSetContainer() {
        EReference eContainingReference = mock(EReference.class);
        when(eClass.getEStructuralFeature(0)).thenReturn(eContainingReference);
        when(eClass.getFeatureID(eContainingReference)).thenReturn(0);

        PersistentEObject container0 = mock(DefaultPersistentEObject.class);
        when(container0.id()).thenReturn(Id.getProvider().fromLong(44L));
        when(container0.eClass()).thenReturn(eClass);

        PersistentEObject container1 = mock(DefaultPersistentEObject.class);
        when(container1.id()).thenReturn(Id.getProvider().fromLong(45L));
        when(container1.eClass()).thenReturn(eClass);

        store.updateContainment(object, eContainingReference, container0);
        assertThat(store.getContainer(object)).isEqualTo(container0);
        assertThat(store.getContainingFeature(object)).isEqualTo(eContainingReference);

        store.updateContainment(object, eContainingReference, container1);
        assertThat(store.getContainer(object)).isEqualTo(container1);
        assertThat(store.getContainingFeature(object)).isEqualTo(eContainingReference);

        store.updateContainment(object, eContainingReference, container1);
        assertThat(store.getContainer(object)).isEqualTo(container1);
        assertThat(store.getContainingFeature(object)).isEqualTo(eContainingReference);

        store.removeContainment(object);
        assertThat(store.getContainer(object)).isNull();
        assertThat(store.getContainingFeature(object)).isNull();
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testGetAll_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.set(object, eFeature, -1, value0);

        List<Object> resolvedList = store.getAll(object, eFeature);
        assertThat(resolvedList).hasSize(1);
        assertThat(resolvedList.get(0)).isEqualTo(value0);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testGetAll_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        List<Object> resolvedList = store.getAll(object, eFeature);
        assertThat(resolvedList).hasSize(2);
        assertThat(resolvedList.get(0)).isEqualTo(value0);
        assertThat(resolvedList.get(1)).isEqualTo(value1);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testSetAll_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.setAll(object, eFeature, Collections.emptyList()))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testSetAll_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        store.setAll(object, eFeature, Arrays.asList(value1, value0, value0));

        List<Object> resolvedList = store.getAll(object, eFeature);
        assertThat(resolvedList).hasSize(3);
        assertThat(resolvedList.get(0)).isEqualTo(value1);
        assertThat(resolvedList.get(1)).isEqualTo(value0);
        assertThat(resolvedList.get(2)).isEqualTo(value0);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(SingleProvider.class)
    void testAddAll_Single(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(
                catchThrowable(() -> store.addAll(object, eFeature, 0, Collections.emptyList()))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = TEST_NAME_PATTERN)
    @ArgumentsSource(ManyProvider.class)
    void testAddAll_Many(FeatureWrapper feature, Object value0, Object value1) {
        EStructuralFeature eFeature = feature.getFeature();

        assertThat(store.addAll(object, eFeature, -1, Collections.emptyList())).isEqualTo(0);

        assertThat(store.addAll(object, eFeature, -1, Arrays.asList(value0, value1))).isEqualTo(0);

        List<Object> resolvedList0 = store.getAll(object, eFeature);
        assertThat(resolvedList0).hasSize(2);
        assertThat(resolvedList0.get(0)).isEqualTo(value0);
        assertThat(resolvedList0.get(1)).isEqualTo(value1);

        assertThat(store.addAll(object, eFeature, 1, Arrays.asList(value1, value0))).isEqualTo(1);

        List<Object> resolvedList1 = store.getAll(object, eFeature);
        assertThat(resolvedList1).hasSize(4);
        assertThat(resolvedList1.get(0)).isEqualTo(value0);
        assertThat(resolvedList1.get(1)).isEqualTo(value1);
        assertThat(resolvedList1.get(2)).isEqualTo(value0);
        assertThat(resolvedList1.get(3)).isEqualTo(value1);
    }

    @Test
    void testResolveUpdateInstanceOf() {
        // Resolve the instance of the object
        Optional<EClass> resolvedClass = store.resolveInstanceOf(id);
        assertThat(resolvedClass).contains(eClass);

        // Resolve and rebuild the object
        PersistentEObject resolvedObject = store.resolve(id);
        assertThat(resolvedObject).isEqualTo(object);
        assertThat(resolvedObject.id()).isEqualTo(id);
        assertThat(resolvedObject.eClass()).isEqualTo(eClass);
    }

    @Test
    void testResolveUpdateInstanceOf_NotDefined() {
        assertThat(
                catchThrowable(() -> store.resolve(Id.getProvider().generate()))
        ).isExactlyInstanceOf(NoSuchElementException.class);
    }

    /**
     * An abstract {@link ArgumentsProvider} that initializes values.
     */
    @ParametersAreNonnullByDefault
    static abstract class AllProvider implements ArgumentsProvider {

        protected final Object value0 = "MyValue0";
        protected final Object value1 = "MyValue1";

        protected final DefaultPersistentEObject reference0;
        protected final DefaultPersistentEObject reference1;

        AllProvider() {
            // Initialize references
            reference0 = mock(DefaultPersistentEObject.class);
            when(reference0.id()).thenReturn(Id.getProvider().fromLong(44L));
            when(reference0.eClass()).thenReturn(eClass);

            reference1 = mock(DefaultPersistentEObject.class);
            when(reference1.id()).thenReturn(Id.getProvider().fromLong(45L));
            when(reference1.eClass()).thenReturn(eClass);
        }
    }

    /**
     * An {@link ArgumentsProvider} with single-valued features.
     */
    @ParametersAreNonnullByDefault
    static final class SingleProvider extends AllProvider {

        private final EAttribute singleAttribute;
        private final EReference singleReference;

        SingleProvider() {
            super();

            // Initialize attributes
            final EDataType eDataType = mock(EDataType.class);
            when(eDataType.getInstanceClass()).thenAnswer(new Returns(String.class));
            when(eDataType.getInstanceClassName()).thenReturn(String.class.getTypeName());

            singleAttribute = mock(EAttribute.class);
            when(singleAttribute.isMany()).thenReturn(false);
            when(singleAttribute.getEAttributeType()).thenReturn(eDataType);

            // Initialize references
            singleReference = mock(EReference.class);
            when(singleReference.isMany()).thenReturn(false);
            when(singleReference.isContainment()).thenReturn(false);
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new FeatureWrapper(singleAttribute, "EAttribute"), value0, value1),
                    Arguments.of(new FeatureWrapper(singleReference, "EReference"), reference0, reference1)
            );
        }
    }

    /**
     * An {@link ArgumentsProvider} with many-valued features.
     */
    @ParametersAreNonnullByDefault
    static final class ManyProvider extends AllProvider {

        private final EAttributeImpl manyAttribute;
        private final EReferenceImpl manyReference;

        private final EAttribute featureMapAttribute;

        private final FeatureMap.Entry featureMapValue0;
        private final FeatureMap.Entry featureMapValue1;

        private final FeatureMap.Entry featureMapReference0;
        private final FeatureMap.Entry featureMapReference1;

        ManyProvider() {
            super();

            final EFactory eFactory = mock(EFactoryImpl.class);

            final EPackage ePackage = mock(EPackage.class);
            when(ePackage.getEFactoryInstance()).thenReturn(eFactory);

            // Initialize attributes
            final EDataType eDataType = mock(EDataType.class);
            when(eDataType.getInstanceClass()).thenAnswer(new Returns(String.class));
            when(eDataType.getInstanceClassName()).thenReturn(String.class.getTypeName());
            when(eDataType.getEPackage()).thenReturn(ePackage);

            when(eFactory.convertToString(eq(eDataType), eq(value0))).thenReturn(value0.toString());
            when(eFactory.convertToString(eq(eDataType), eq(value1))).thenReturn(value1.toString());
            when(eFactory.createFromString(eq(eDataType), eq(value0.toString()))).thenReturn(value0);
            when(eFactory.createFromString(eq(eDataType), eq(value1.toString()))).thenReturn(value1);

            // The containing class of the FeatureMap attribute
            final EClass eContainingClass = mock(EClass.class);

            // The type of features
            final EClassifier eType = mock(EClassifier.class);
            when(eType.isInstance(any())).thenReturn(true);

            manyAttribute = mock(EAttributeImpl.class);
            when(manyAttribute.getName()).thenReturn("attr");
            when(manyAttribute.isMany()).thenReturn(true);
            when(manyAttribute.getEAttributeType()).thenReturn(eDataType);
            when(eContainingClass.getEStructuralFeature(eq("attr"))).thenReturn(manyAttribute);

            // Get around FeatureMapUtil.createEntry()
            final FeatureMap.Entry.Internal entryProtoValue = new EStructuralFeatureImpl.SimpleFeatureMapEntry(manyAttribute, null);
            when(manyAttribute.getFeatureMapEntryPrototype()).thenReturn(entryProtoValue);
            when(manyAttribute.getEType()).thenReturn(eType);

            // Initialize references
            manyReference = mock(EReferenceImpl.class);
            when(manyReference.getName()).thenReturn("ref");
            when(manyReference.isMany()).thenReturn(true);
            when(manyReference.isContainment()).thenReturn(false);
            when(eContainingClass.getEStructuralFeature(eq("ref"))).thenReturn(manyReference);

            // Get around FeatureMapUtil.createEntry()
            final FeatureMap.Entry.Internal entryProtoReference = new EStructuralFeatureImpl.SimpleFeatureMapEntry(manyReference, null);
            when(manyReference.getFeatureMapEntryPrototype()).thenReturn(entryProtoReference);
            when(manyReference.getEType()).thenReturn(eType);

            // Initialize feature maps
            final EDataType eDataTypeFeatureMap = mock(EDataType.class);
            when(eDataTypeFeatureMap.getInstanceClass()).thenAnswer(new Returns(FeatureMap.Entry.class));
            when(eDataTypeFeatureMap.getInstanceClassName()).thenReturn(FeatureMap.Entry.class.getTypeName());

            featureMapAttribute = mock(EAttributeImpl.class);
            when(featureMapAttribute.isMany()).thenReturn(true);
            when(featureMapAttribute.getEAttributeType()).thenReturn(eDataTypeFeatureMap);
            when(featureMapAttribute.getEContainingClass()).thenReturn(eContainingClass);

            featureMapValue0 = mock(FeatureMap.Entry.class);
            when(featureMapValue0.getEStructuralFeature()).thenReturn(manyAttribute);
            when(featureMapValue0.getValue()).thenReturn(value0);

            featureMapValue1 = mock(FeatureMap.Entry.class);
            when(featureMapValue1.getEStructuralFeature()).thenReturn(manyAttribute);
            when(featureMapValue1.getValue()).thenReturn(value1);

            featureMapReference0 = mock(FeatureMap.Entry.class);
            when(featureMapReference0.getEStructuralFeature()).thenReturn(manyReference);
            when(featureMapReference0.getValue()).thenReturn(reference0);

            featureMapReference1 = mock(FeatureMap.Entry.class);
            when(featureMapReference1.getEStructuralFeature()).thenReturn(manyReference);
            when(featureMapReference1.getValue()).thenReturn(reference1);
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(new FeatureWrapper(manyAttribute, "EAttribute"), value0, value1),
                    Arguments.of(new FeatureWrapper(manyReference, "EReference"), reference0, reference1),
                    Arguments.of(new FeatureWrapper(featureMapAttribute, "FeatureMap[EAttribute]"), featureMapValue0, featureMapValue1),
                    Arguments.of(new FeatureWrapper(featureMapAttribute, "FeatureMap[EReference]"), featureMapReference0, featureMapReference1)
            );
        }
    }
}