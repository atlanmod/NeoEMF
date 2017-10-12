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

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.DefaultTransientBackend;
import fr.inria.atlanmod.neoemf.data.store.ClosedStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A test-case about {@link AbstractStoreAdapter}.
 */
@ParametersAreNonnullByDefault
// TODO Add tests for FeatureMaps
class StoreAdapterTest extends AbstractTest {

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
        Store innerStore = StoreFactory.getInstance().createStore(new DefaultTransientBackend(), BaseConfig.newConfig());

        store = new AbstractStoreAdapter(innerStore, null) {

            @Nonnull
            @Override
            protected Cache<Id, PersistentEObject> cache() {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testGetSet_Single(EStructuralFeature eFeature, Object value0, Object value1) {
        store.set(object, eFeature, -1, value0);

        Object resolvedValue0 = store.get(object, eFeature, -1);
        assertThat(resolvedValue0).isEqualTo(value0);

        store.set(object, eFeature, -1, value1);

        Object resolvedValue1 = store.get(object, eFeature, -1);
        assertThat(resolvedValue1).isEqualTo(value1);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testGetSet_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testIsSetUnset_Single(EStructuralFeature eFeature, Object value0) {
        assertThat(store.isSet(object, eFeature)).isFalse();

        store.set(object, eFeature, -1, value0);
        assertThat(store.isSet(object, eFeature)).isTrue();

        store.unset(object, eFeature);
        assertThat(store.isSet(object, eFeature)).isFalse();
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testIsSetUnset_Many(EStructuralFeature eFeature, Object value0, Object value1) {
        assertThat(store.isSet(object, eFeature)).isFalse();

        store.add(object, eFeature, 0, value0);
        assertThat(store.isSet(object, eFeature)).isTrue();

        store.add(object, eFeature, 1, value1);
        assertThat(store.isSet(object, eFeature)).isTrue();

        store.unset(object, eFeature);
        assertThat(store.isSet(object, eFeature)).isFalse();
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testIsEmpty_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.isEmpty(object, eFeature))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testIsEmpty_Many(EStructuralFeature eFeature, Object value0) {
        assertThat(store.isEmpty(object, eFeature)).isTrue();

        store.add(object, eFeature, 0, value0);
        assertThat(store.isEmpty(object, eFeature)).isFalse();

        store.remove(object, eFeature, 0);
        assertThat(store.isEmpty(object, eFeature)).isTrue();
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testSize_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.size(object, eFeature))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testSize_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testContains_Single(EStructuralFeature eFeature, Object value0) {
        assertThat(
                catchThrowable(() -> store.contains(object, eFeature, value0))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testContains_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testIndexOf_Single(EStructuralFeature eFeature, Object value0) {
        assertThat(
                catchThrowable(() -> store.indexOf(object, eFeature, value0))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testIndexOf_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testLastIndexOf_Single(EStructuralFeature eFeature, Object value0) {
        assertThat(
                catchThrowable(() -> store.lastIndexOf(object, eFeature, value0))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testLastIndexOf_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testAdd_Single(EStructuralFeature eFeature, Object value0) {
        assertThat(
                catchThrowable(() -> store.add(object, eFeature, 0, value0))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testAdd_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testRemove_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.remove(object, eFeature, 0))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testRemove_Many(EStructuralFeature eFeature, Object value0, Object value1) {
        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        assertThat(store.get(object, eFeature, 0)).isEqualTo(value0);
        assertThat(store.get(object, eFeature, 1)).isEqualTo(value1);

        assertThat(store.remove(object, eFeature, 0)).isEqualTo(value0);
        assertThat(store.get(object, eFeature, 0)).isEqualTo(value1);

        assertThat(store.remove(object, eFeature, 0)).isEqualTo(value1);
        assertThat(store.isEmpty(object, eFeature)).isTrue();
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testMove_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.move(object, eFeature, 1, 0))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testMove_Many(EStructuralFeature eFeature, Object value0, Object value1) {
        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        assertThat(store.move(object, eFeature, 1, 0)).isEqualTo(value0);

        assertThat(store.get(object, eFeature, 0)).isEqualTo(value1);
        assertThat(store.get(object, eFeature, 1)).isEqualTo(value0);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testClear_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.clear(object, eFeature))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testClear_Many(EStructuralFeature eFeature, Object value0, Object value1) {
        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        assertThat(store.size(object, eFeature)).isEqualTo(2);

        store.clear(object, eFeature);

        assertThat(store.isEmpty(object, eFeature)).isTrue();
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testToArray_Single(EStructuralFeature eFeature, Object value0) {
        assertThat(store.toArray(object, eFeature)).isEmpty();

        store.set(object, eFeature, -1, value0);

        Object[] resolvedArray = store.toArray(object, eFeature);
        assertThat(resolvedArray).hasSize(1);
        assertThat(resolvedArray[0]).isEqualTo(value0);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testToArray_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

        store.removeContainment(object);
        assertThat(store.getContainer(object)).isNull();
        assertThat(store.getContainingFeature(object)).isNull();
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testGetAll_Single(EStructuralFeature eFeature, Object value0) {
        store.set(object, eFeature, -1, value0);

        List<Object> resolvedList = store.getAll(object, eFeature);
        assertThat(resolvedList).hasSize(1);
        assertThat(resolvedList.get(0)).isEqualTo(value0);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testGetAll_Many(EStructuralFeature eFeature, Object value0, Object value1) {
        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        List<Object> resolvedList = store.getAll(object, eFeature);
        assertThat(resolvedList).hasSize(2);
        assertThat(resolvedList.get(0)).isEqualTo(value0);
        assertThat(resolvedList.get(1)).isEqualTo(value1);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testSetAll_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.setAll(object, eFeature, Collections.emptyList()))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testSetAll_Many(EStructuralFeature eFeature, Object value0, Object value1) {
        store.add(object, eFeature, -1, value0);
        store.add(object, eFeature, -1, value1);

        store.setAll(object, eFeature, Arrays.asList(value1, value0, value0));

        List<Object> resolvedList = store.getAll(object, eFeature);
        assertThat(resolvedList).hasSize(3);
        assertThat(resolvedList.get(0)).isEqualTo(value1);
        assertThat(resolvedList.get(1)).isEqualTo(value0);
        assertThat(resolvedList.get(2)).isEqualTo(value0);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(SingleProvider.class)
    void testAddAll_Single(EStructuralFeature eFeature) {
        assertThat(
                catchThrowable(() -> store.addAll(object, eFeature, 0, Collections.emptyList()))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] With {3}")
    @ArgumentsSource(ManyProvider.class)
    void testAddAll_Many(EStructuralFeature eFeature, Object value0, Object value1) {
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

        protected static Object value0 = "MyValue0";
        protected static Object value1 = "MyValue1";

        protected static DefaultPersistentEObject objectRef0;
        protected static DefaultPersistentEObject objectRef1;

        static {
            // Initialize references
            objectRef0 = mock(DefaultPersistentEObject.class);
            when(objectRef0.id()).thenReturn(Id.getProvider().fromLong(44L));
            when(objectRef0.eClass()).thenReturn(eClass);

            objectRef1 = mock(DefaultPersistentEObject.class);
            when(objectRef1.id()).thenReturn(Id.getProvider().fromLong(45L));
            when(objectRef1.eClass()).thenReturn(eClass);
        }
    }

    /**
     * An {@link ArgumentsProvider} with single-valued features.
     */
    @ParametersAreNonnullByDefault
    static final class SingleProvider extends AllProvider {

        private static EAttribute singleAttribute;
        private static EReference singleReference;

        static {
            // Initialize attributes
            final EDataType eDataType = mock(EDataType.class);
            when(eDataType.getInstanceClass()).thenAnswer(new Returns(String.class));

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
                    Arguments.of(singleAttribute, value0, value1, "EAttribute"),
                    Arguments.of(singleReference, objectRef0, objectRef1, "EReference")
            );
        }
    }

    /**
     * An {@link ArgumentsProvider} with many-valued features.
     */
    @ParametersAreNonnullByDefault
    static final class ManyProvider extends AllProvider {

        private static EAttribute manyAttribute;
        private static EReference manyReference;

        static {
            // Initialize attributes
            final EDataType eDataType = mock(EDataType.class);
            when(eDataType.getInstanceClass()).thenAnswer(new Returns(String.class));

            manyAttribute = mock(EAttribute.class);
            when(manyAttribute.isMany()).thenReturn(true);
            when(manyAttribute.getEAttributeType()).thenReturn(eDataType);

            manyReference = mock(EReference.class);
            when(manyReference.isMany()).thenReturn(true);
            when(manyReference.isContainment()).thenReturn(false);
        }

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(manyAttribute, value0, value1, "EAttribute"),
                    Arguments.of(manyReference, objectRef0, objectRef1, "EReference")
            );
        }
    }
}