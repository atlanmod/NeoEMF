/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.Timeout;
import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.internal.functions.Functions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test-case about {@link DataMapper} and its implementations.
 */
@Timeout(timeout = 2)
@ParametersAreNonnullByDefault
@SuppressWarnings("ConstantConditions") // Test with `@Nonnull`
public abstract class AbstractDataMapperTest extends AbstractUnitTest {

    /**
     * The default {@link Id} used to store features.
     */
    protected static Id idBase = Id.getProvider().fromLong(42);

    /**
     * The default single-valued feature bean.
     */
    private static SingleFeatureBean sfBase = SingleFeatureBean.of(idBase, 5);

    /**
     * The default multi-valued feature bean.
     */
    private static ManyFeatureBean mfBase = sfBase.withPosition(0);

    /**
     * The default meta-class bean.
     */
    private static ClassBean cBase = ClassBean.of("Metaclass0", EcorePackage.eNS_URI);

    // Variables initialization

    private static Id id0 = Id.getProvider().fromLong(0);
    private static Id id1 = Id.getProvider().fromLong(1);

    private static String valueDummy = "DUMMY";
    private static Id refDummy = Id.getProvider().fromLong(0xf);

    /**
     * The {@link DataMapper} used for this test case.
     */
    protected DataMapper mapper;

    /**
     * Creates the {@link DataMapper} to test.
     */
    @BeforeEach
    void createMapper() throws IOException {
        mapper = context().createMapper(currentTempFile());

        updateInstanceOf(idBase);
    }

    /**
     * Closes the {@link DataMapper}.
     */
    @AfterEach
    void closeMapper() {
        Optional.ofNullable(mapper).ifPresent(DataMapper::close);
    }

    /**
     * Defines the meta-class of the given {@code references}.
     *
     * @param references the references to update
     */
    private void updateInstanceOf(Id... references) {
        Arrays.stream(references).forEach(i -> mapper.metaClassFor(i, cBase)
                .onErrorComplete(Functions.isInstanceOf(ClassAlreadyExistsException.class))
                .blockingAwait());
    }

    /**
     * Checks the behavior of {@link DataMapper#close()}. A call to {@link DataMapper#close()} on a closed {@link
     * DataMapper} should do nothing.
     */
    @Test
    public void testCloseThenClose() {
        // First close
        mapper.close();

        // Second close
        assertThat(catchThrowable(() ->
                mapper.close()
        )).isNull();

        // Third close
        assertThat(catchThrowable(() ->
                mapper.close()
        )).isNull();
    }

    //region Containers

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Same() throws InterruptedException {
        SingleFeatureBean container = SingleFeatureBean.of(id0, 20);

        mapper.containerFor(idBase, container).test().await().assertComplete();
        mapper.containerOf(idBase).test().await().assertValue(container);

        mapper.containerFor(id1, container).test().await().assertComplete();
        mapper.containerOf(id1).test().await().assertValue(container);

        mapper.removeContainer(idBase).test().await().assertComplete();
        mapper.removeContainer(id1).test().await().assertComplete();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Different() throws InterruptedException {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);
        SingleFeatureBean container1 = SingleFeatureBean.of(id0, 21);

        mapper.containerFor(idBase, container0).test().await().assertComplete();
        mapper.containerOf(idBase).test().await().assertValue(container0);

        mapper.containerFor(id1, container1).test().await().assertComplete();
        mapper.containerOf(id1).test().await().assertValue(container1);

        mapper.containerFor(idBase, container1).test().await().assertComplete();
        mapper.containerOf(idBase).test().await().assertValue(container1);

        mapper.removeContainer(idBase).test().await().assertComplete();
        mapper.removeContainer(id1).test().await().assertComplete();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGet_Container_NotDefined() throws InterruptedException {
        mapper.containerOf(idBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Container_Null() {
        assertThat(catchThrowable(() ->
                mapper.containerFor(idBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testRemove_Container() throws InterruptedException {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);

        mapper.containerFor(idBase, container0).test().await().assertComplete();
        mapper.containerOf(idBase).test().await().assertValue(container0);

        mapper.removeContainer(idBase).test().await().assertComplete();
        mapper.containerOf(idBase).test().await().assertComplete().assertNoValues();

        mapper.removeContainer(idBase).test().await().assertComplete();
    }

    //endregion

    //region Metaclasses

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Same() throws InterruptedException {
        Id id0 = Id.getProvider().fromLong(40);
        Id id1 = Id.getProvider().fromLong(41);

        ClassBean metaClass = ClassBean.of("Metaclass1", "Uri1");

        mapper.metaClassFor(id0, metaClass).test().await().assertComplete();
        mapper.metaClassOf(id0).test().await().assertValue(metaClass);

        mapper.metaClassFor(id1, metaClass).test().await().assertComplete();
        mapper.metaClassOf(id1).test().await().assertValue(metaClass);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Different() throws InterruptedException {
        Id id0 = Id.getProvider().fromLong(40);

        ClassBean metaClass0 = ClassBean.of("Metaclass1", "Uri1");
        ClassBean metaClass1 = ClassBean.of("Metaclass2", "Uri2");

        mapper.metaClassFor(id0, metaClass0).test().await().assertComplete();
        mapper.metaClassOf(id0).test().await().assertValue(metaClass0);

        mapper.metaClassFor(id0, metaClass1).test().await().assertError(ClassAlreadyExistsException.class);
        mapper.metaClassOf(id0).test().await().assertValue(metaClass0);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} when the element doesn't exist..
     */
    @Test
    public void testGet_Metaclass_NotDefined() throws InterruptedException {
        Id id0 = Id.getProvider().fromLong(40);

        mapper.metaClassOf(id0).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassFor(Id, ClassBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Metaclass_Null() {
        Id id0 = Id.getProvider().fromLong(40);

        assertThat(catchThrowable(() ->
                mapper.metaClassFor(id0, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    //endregion

    //region Single-value attributes

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)} and {@link
     * ValueMapper#valueFor(SingleFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testGetSet_Value(Object value0, Object value1) throws InterruptedException {
        mapper.valueFor(sfBase, value0).test().await().assertNoValues();
        mapper.valueOf(sfBase).test().await().assertValue(value0);

        mapper.valueFor(sfBase, value1).test().await().assertValue(value0);
        mapper.valueOf(sfBase).test().await().assertValue(value1);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)}.
     */
    @Test
    public void testGet_Value_NotDefined() throws InterruptedException {
        mapper.valueOf(sfBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_Value_Null() {
        assertThat(catchThrowable(() ->
                mapper.valueFor(sfBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testRemove_Value(Object value0) throws InterruptedException {
        mapper.valueFor(sfBase, value0).test().await().assertNoValues();
        mapper.valueOf(sfBase).test().await().assertValue(value0);

        mapper.removeValue(sfBase).test().await().assertComplete();
        mapper.valueOf(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemove_Value_NotDefined() throws InterruptedException {
        mapper.removeValue(sfBase).test().await().assertNoErrors().assertComplete();
    }

    //endregion

    //region Multi-valued attributes

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} and {@link
     * ManyValueMapper#valueFor(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testGetSet_ManyValue(Object value0, Object value1, Object value2, Object value3) throws InterruptedException {
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);

        mapper.valueFor(sfBase.withPosition(0), value2).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value2);

        mapper.valueFor(sfBase.withPosition(1), value3).test().await().assertValue(value1);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testGet_ManyValue_NotDefined() throws InterruptedException {
        mapper.valueOf(mfBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSet_ManyValue_NotDefined() throws InterruptedException {
        mapper.valueFor(mfBase, valueDummy).test().await().assertError(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testSet_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                mapper.valueFor(mfBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testGetAll_ManyValue(Object value0, Object value1, Object value2) {
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        List<Object> actualValues = mapper.allValuesOf(sfBase).toList().blockingGet();
        assertThat(actualValues).hasSize(3);

        assertThat(actualValues.get(0)).isEqualTo(value0);
        assertThat(actualValues.get(1)).isEqualTo(value1);
        assertThat(actualValues.get(2)).isEqualTo(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)} when the feature doesn't contain
     * any element.
     */
    @Test
    public void testGetAll_Empty() throws InterruptedException {
        mapper.allValuesOf(sfBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAdd_ManyValue(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertValue(value2);

        mapper.sizeOfValue(sfBase).test().await().assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAdd_ManyValue_OverSize(@SuppressWarnings("unused") Object value0, @SuppressWarnings("unused") Object value1, Object value2) {
        assertThat(catchThrowable(() ->
                mapper.addValue(sfBase.withPosition(2), value2)
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testAdd_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                mapper.addValue(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAppend_ManyValue(Object value0, Object value1) throws InterruptedException {
        int index;

        index = mapper.appendValue(sfBase, value0);
        assertThat(index).isEqualTo(0);
        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);

        index = mapper.appendValue(sfBase, value1);
        assertThat(index).isEqualTo(1);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @Test
    public void testAppend_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                mapper.appendValue(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAddAll_ManyValue_FromStart(Object value0, Object value1) throws InterruptedException {
        mapper.addAllValues(sfBase.withPosition(0), Arrays.asList(value0, value1));

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);

        mapper.sizeOfValue(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAddAll_ManyValue_WithOffset(Object value0, Object value1) {
        assertThat(catchThrowable(() ->
                mapper.addAllValues(sfBase.withPosition(1), Arrays.asList(value0, value1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAddAll_ManyValue_FromMiddle(Object value0, Object value1, Object value2, Object value3) throws InterruptedException {
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);

        mapper.addAllValues(sfBase.withPosition(1), Arrays.asList(value2, value3));

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value2);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertValue(value3);
        mapper.valueOf(sfBase.withPosition(3)).test().await().assertValue(value1);

        mapper.sizeOfValue(sfBase).test().await().assertValue(4);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAddAll_ManyValue_FromEnd(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.appendValue(sfBase, value0);
        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);

        mapper.addAllValues(sfBase.withPosition(1), Arrays.asList(value1, value2));

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertValue(value2);

        mapper.sizeOfValue(sfBase).test().await().assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with an empty collection.
     */
    @Test
    public void testAddAll_ManyValue_Empty() throws InterruptedException {
        mapper.addAllValues(mfBase, Collections.emptyList());

        mapper.sizeOfValue(mfBase.withoutPosition()).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAddAll_ManyValue_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.addAllValues(mfBase, Arrays.asList(valueDummy, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAddAll_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                mapper.addAllValues(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAppendAll_ManyValue_FromStart(Object value0, Object value1) throws InterruptedException {
        int index;

        index = mapper.appendAllValues(sfBase, Arrays.asList(value0, value1));
        assertThat(index).isEqualTo(0);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);

        mapper.sizeOfValue(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature already
     * has values.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testAppendAll_ManyValue_FromEnd(Object value0, Object value1, Object value2) throws InterruptedException {
        int index;

        index = mapper.appendValue(sfBase, value0);
        assertThat(index).isEqualTo(0);
        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);

        index = mapper.appendAllValues(sfBase, Arrays.asList(value1, value2));
        assertThat(index).isEqualTo(1);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertValue(value2);

        mapper.sizeOfValue(sfBase).test().await().assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAll_ManyValue_Empty() throws InterruptedException {
        mapper.appendAllValues(sfBase, Collections.emptyList());

        mapper.sizeOfValue(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAppendAll_ManyValue_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.appendAllValues(sfBase, Arrays.asList(valueDummy, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAppendAll_ManyValue_Null() {
        assertThat(catchThrowable(() ->
                mapper.appendAllValues(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testRemove_ManyValue(Object value0, Object value1) throws InterruptedException {
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.sizeOfValue(sfBase).test().await().assertValue(2);

        mapper.removeValue(sfBase.withPosition(0));
        mapper.sizeOfValue(sfBase).test().await().assertValue(1);

        mapper.removeValue(sfBase.withPosition(0));
        mapper.sizeOfValue(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testRemove_ManyValue_Before(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        assertThat(mapper.removeValue(sfBase.withPosition(0))).contains(value0);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value1);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value2);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfValue(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testRemove_ManyValue_After(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        assertThat(mapper.removeValue(sfBase.withPosition(1))).contains(value1);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value2);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfValue(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testRemove_ManyValue_Last(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.addValue(sfBase.withPosition(0), value0);
        mapper.addValue(sfBase.withPosition(1), value1);
        mapper.addValue(sfBase.withPosition(2), value2);

        assertThat(mapper.removeValue(sfBase.withPosition(2))).contains(value2);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertValue(value0);
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertValue(value1);
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfValue(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemove_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.removeValue(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testRemoveAll_ManyValue(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        mapper.sizeOfValue(sfBase).test().await().assertValue(3);

        mapper.removeAllValues(sfBase);

        mapper.valueOf(sfBase.withPosition(0)).test().await().assertNoValues();
        mapper.valueOf(sfBase.withPosition(1)).test().await().assertNoValues();
        mapper.valueOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfValue(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testRemoveAll_ManyValue_NotDefined() {
        assertThat(catchThrowable(() ->
                mapper.removeAllValues(sfBase)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ValueProvider.class)
    public void testSize_ManyValue(Object value0, Object value1, Object value2) throws InterruptedException {
        mapper.appendValue(sfBase, value0);
        mapper.appendValue(sfBase, value1);
        mapper.appendValue(sfBase, value2);

        mapper.sizeOfValue(sfBase).test().await().assertValue(3);

        mapper.removeValue(sfBase.withPosition(1));

        mapper.sizeOfValue(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @Test
    public void testSize_ManyValue_NotDefined() throws InterruptedException {
        mapper.sizeOfValue(sfBase).test().await().assertNoErrors().assertNoValues();
    }

    //endregion

    //region Single-valued references

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureBean)} and {@link
     * ReferenceMapper#referenceFor(SingleFeatureBean, Id)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testGetSet_Reference(Id ref0, Id ref1) throws InterruptedException {
        updateInstanceOf(ref0, ref1);

        mapper.referenceFor(sfBase, ref0).test().await().assertNoValues();
        mapper.referenceOf(sfBase).test().await().assertValue(ref0);

        mapper.referenceFor(sfBase, ref1).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase).test().await().assertValue(ref1);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceOf(SingleFeatureBean)}.
     */
    @Test
    public void testGet_Reference_NotDefined() throws InterruptedException {
        mapper.referenceOf(sfBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#referenceFor(SingleFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_Reference_Null() {
        assertThat(catchThrowable(() ->
                mapper.referenceFor(sfBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#removeReference(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testRemove_Reference(Id ref0) throws InterruptedException {
        updateInstanceOf(ref0);

        mapper.referenceFor(sfBase, ref0).test().await().assertNoValues();
        mapper.referenceOf(sfBase).test().await().assertValue(ref0);

        mapper.removeReference(sfBase).test().await().assertComplete();
        mapper.referenceOf(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ReferenceMapper#removeReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemove_Reference_NotDefined() throws InterruptedException {
        mapper.removeReference(sfBase).test().await().assertNoErrors().assertComplete();
    }

    //endregion

    //region Multi-valued references

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureBean)} and {@link
     * ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testGetSet_ManyReference(Id ref0, Id ref1, Id ref2, Id ref3) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2, ref3);

        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);

        mapper.referenceFor(sfBase.withPosition(0), ref2).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref2);

        mapper.referenceFor(sfBase.withPosition(1), ref3).test().await().assertValue(ref1);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceOf(ManyFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testGet_ManyReference_NotDefined() throws InterruptedException {
        mapper.referenceOf(mfBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSet_ManyReference_NotDefined() throws InterruptedException {
        mapper.referenceFor(mfBase, refDummy).test().await().assertError(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#referenceFor(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testSet_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                mapper.referenceFor(mfBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testGetAll_ManyReference(Id ref0, Id ref1, Id ref2) {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        List<Id> actualReferences = mapper.allReferencesOf(sfBase).toList().blockingGet();
        assertThat(actualReferences).hasSize(3);

        assertThat(actualReferences.get(0)).isEqualTo(ref0);
        assertThat(actualReferences.get(1)).isEqualTo(ref1);
        assertThat(actualReferences.get(2)).isEqualTo(ref2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#allReferencesOf(SingleFeatureBean)} when the feature doesn't
     * contain any element.
     */
    @Test
    public void testGetAll_ManyReference_Empty() throws InterruptedException {
        mapper.allReferencesOf(sfBase).test().await().assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAdd_ManyReference(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertValue(ref2);

        mapper.sizeOfReference(sfBase).test().await().assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAdd_ManyReference_OverSize(@SuppressWarnings("unused") Id ref0, @SuppressWarnings("unused") Id ref1, Id ref2) {
        updateInstanceOf(ref2);

        assertThat(catchThrowable(() ->
                mapper.addReference(sfBase.withPosition(2), ref2)
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addReference(ManyFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAdd_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                mapper.addReference(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureBean, Id)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAppend_ManyReference(Id ref0, Id ref1) throws InterruptedException {
        updateInstanceOf(ref0, ref1);

        int index;

        index = mapper.appendReference(sfBase, ref0);
        assertThat(index).isEqualTo(0);
        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);

        index = mapper.appendReference(sfBase, ref1);
        assertThat(index).isEqualTo(1);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendReference(SingleFeatureBean, Id)} with a {@code null}
     * reference.
     */
    @Test
    public void testAppend_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                mapper.appendReference(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature is
     * not defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAddAll_ManyReference_FromStart(Id ref0, Id ref1) throws InterruptedException {
        updateInstanceOf(ref0, ref1);

        mapper.addAllReferences(sfBase.withPosition(0), Arrays.asList(ref0, ref1));

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature is
     * not defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAddAll_ManyReference_WithOffset(Id ref0, Id ref1) {
        updateInstanceOf(ref0, ref1);

        assertThat(catchThrowable(() ->
                mapper.addAllReferences(sfBase.withPosition(1), Arrays.asList(ref0, ref1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature
     * already has values.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAddAll_ManyReference_FromMiddle(Id ref0, Id ref1, Id ref2, Id ref3) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2, ref3);

        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);

        mapper.addAllReferences(sfBase.withPosition(1), Arrays.asList(ref2, ref3));

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref2);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertValue(ref3);
        mapper.referenceOf(sfBase.withPosition(3)).test().await().assertValue(ref1);

        mapper.sizeOfReference(sfBase).test().await().assertValue(4);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} when the feature
     * already has values.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAddAll_ManyReference_FromEnd(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.appendReference(sfBase, ref0);
        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);

        mapper.addAllReferences(sfBase.withPosition(1), Arrays.asList(ref1, ref2));

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertValue(ref2);

        mapper.sizeOfReference(sfBase).test().await().assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAddAll_ManyReference_Empty() throws InterruptedException {
        mapper.addAllReferences(mfBase, Collections.emptyList());

        mapper.sizeOfReference(mfBase.withoutPosition()).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @Test
    public void testAddAll_ManyReference_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(mfBase, Arrays.asList(refDummy, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#addAllReferences(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @Test
    public void testAddAll_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                mapper.addAllReferences(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} when the feature
     * is not defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAppendAll_ManyReference_FromStart(Id ref0, Id ref1) throws InterruptedException {
        updateInstanceOf(ref0, ref1);

        int index;

        index = mapper.appendAllReferences(sfBase, Arrays.asList(ref0, ref1));
        assertThat(index).isEqualTo(0);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} when the feature
     * already has values.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testAppendAll_ManyReference_FromEnd(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        int index;

        index = mapper.appendReference(sfBase, ref0);
        assertThat(index).isEqualTo(0);
        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);

        index = mapper.appendAllReferences(sfBase, Arrays.asList(ref1, ref2));
        assertThat(index).isEqualTo(1);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertValue(ref2);

        mapper.sizeOfReference(sfBase).test().await().assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @Test
    public void testAppendAll_ManyReference_Empty() throws InterruptedException {
        mapper.appendAllReferences(sfBase, Collections.emptyList());

        mapper.sizeOfReference(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with a collection
     * that contains a {@code null} element.
     */
    @Test
    public void testAppendAll_ManyReference_WithNull() {
        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(sfBase, Arrays.asList(refDummy, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#appendAllReferences(SingleFeatureBean, List)} with a {@code
     * null} collection.
     */
    @Test
    public void testAppendAll_ManyReference_Null() {
        assertThat(catchThrowable(() ->
                mapper.appendAllReferences(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testRemove_ManyReference(Id ref0, Id ref1) throws InterruptedException {
        updateInstanceOf(ref0, ref1);

        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.sizeOfReference(sfBase).test().await().assertValue(2);

        mapper.removeReference(sfBase.withPosition(0));
        mapper.sizeOfReference(sfBase).test().await().assertValue(1);

        mapper.removeReference(sfBase.withPosition(0));
        mapper.sizeOfReference(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testRemove_ManyReference_Before(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        assertThat(mapper.removeReference(sfBase.withPosition(0))).contains(ref0);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref1);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref2);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testRemove_ManyReference_After(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        assertThat(mapper.removeReference(sfBase.withPosition(1))).contains(ref1);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref2);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testRemove_ManyReference_Last(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.addReference(sfBase.withPosition(0), ref0);
        mapper.addReference(sfBase.withPosition(1), ref1);
        mapper.addReference(sfBase.withPosition(2), ref2);

        assertThat(mapper.removeReference(sfBase.withPosition(2))).contains(ref2);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertValue(ref0);
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertValue(ref1);
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeReference(ManyFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testRemove_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.removeReference(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testRemoveAll_ManyReference(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        mapper.sizeOfReference(sfBase).test().await().assertValue(3);

        mapper.removeAllReferences(sfBase);

        mapper.referenceOf(sfBase.withPosition(0)).test().await().assertNoValues();
        mapper.referenceOf(sfBase.withPosition(1)).test().await().assertNoValues();
        mapper.referenceOf(sfBase.withPosition(2)).test().await().assertNoValues();

        mapper.sizeOfReference(sfBase).test().await().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#removeAllReferences(SingleFeatureBean)} when the reference
     * doesn't exist.
     */
    @Test
    public void testRemoveAll_ManyReference_NotDefined() {
        assertThat(catchThrowable(() ->
                mapper.removeAllReferences(sfBase)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ReferenceProvider.class)
    public void testSize_ManyReference(Id ref0, Id ref1, Id ref2) throws InterruptedException {
        updateInstanceOf(ref0, ref1, ref2);

        mapper.appendReference(sfBase, ref0);
        mapper.appendReference(sfBase, ref1);
        mapper.appendReference(sfBase, ref2);

        mapper.sizeOfReference(sfBase).test().await().assertValue(3);

        mapper.removeReference(sfBase.withPosition(1));

        mapper.sizeOfReference(sfBase).test().await().assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyReferenceMapper#sizeOfReference(SingleFeatureBean)} when the reference doesn't
     * exist.
     */
    @Test
    public void testSize_ManyReference_NotDefined() throws InterruptedException {
        mapper.sizeOfReference(sfBase).test().await().assertNoErrors().assertNoValues();
    }

    //endregion

    /**
     * An {@link ArgumentsProvider} with values of different kinds.
     */
    @ParametersAreNonnullByDefault
    public static final class ValueProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("Value0", "Value1", "Value2", "Value3")
            );
        }
    }

    /**
     * An {@link ArgumentsProvider} with {@link Id}s of different kinds.
     */
    @ParametersAreNonnullByDefault
    public static final class ReferenceProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(Stream.of(0xa, 0xb, 0xc, 0xd).map(Id.getProvider()::fromLong).toArray())
            );
        }
    }
}
