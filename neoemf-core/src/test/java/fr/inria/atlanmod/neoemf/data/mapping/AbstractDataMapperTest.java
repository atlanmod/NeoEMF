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

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.neoemf.util.RxTestUtils.await;
import static fr.inria.atlanmod.neoemf.util.RxTestUtils.submit;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test-case about {@link DataMapper} and its implementations.
 */
@Timeout(timeout = 2)
@ParametersAreNonnullByDefault
@SuppressWarnings("ConstantConditions") // Test with `@Nonnull`
// TODO Use async for multi-valued features
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
        Flowable.fromArray(references)
                .map(i -> mapper.metaClassFor(i, cBase))
                .map(c -> c.onErrorComplete(Functions.isInstanceOf(ClassAlreadyExistsException.class)))
                .map(Completable::subscribe)
                .subscribe();
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
    public void testGetSet_Container_Same() {
        SingleFeatureBean container = SingleFeatureBean.of(id0, 20);

        submit(mapper.containerFor(idBase, container));
        await(mapper.containerOf(idBase)).assertValue(container);

        submit(mapper.containerFor(id1, container));
        await(mapper.containerOf(id1)).assertValue(container);

        submit(mapper.removeContainer(idBase));
        submit(mapper.removeContainer(id1));

        await(mapper.containerOf(idBase)).assertNoValues();
        await(mapper.containerOf(id1)).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Different() {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);
        SingleFeatureBean container1 = SingleFeatureBean.of(id0, 21);

        submit(mapper.containerFor(idBase, container0));
        await(mapper.containerOf(idBase)).assertValue(container0);

        submit(mapper.containerFor(id1, container1));
        await(mapper.containerOf(id1)).assertValue(container1);

        submit(mapper.containerFor(idBase, container1));
        await(mapper.containerOf(idBase)).assertValue(container1);

        submit(mapper.removeContainer(idBase));
        submit(mapper.removeContainer(id1));

        await(mapper.containerOf(idBase)).assertNoValues();
        await(mapper.containerOf(id1)).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGet_Container_NotDefined() {
        await(mapper.containerOf(idBase)).assertNoErrors().assertNoValues();
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
    public void testRemove_Container() {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);

        submit(mapper.containerFor(idBase, container0));
        await(mapper.containerOf(idBase)).assertValue(container0);

        submit(mapper.removeContainer(idBase));
        await(mapper.containerOf(idBase)).assertComplete().assertNoValues();

        await(mapper.removeContainer(idBase)).assertNoErrors();
    }

    //endregion

    //region Metaclasses

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Same() {
        Id id0 = Id.getProvider().fromLong(40);
        Id id1 = Id.getProvider().fromLong(41);

        ClassBean metaClass = ClassBean.of("Metaclass1", "Uri1");

        submit(mapper.metaClassFor(id0, metaClass));
        await(mapper.metaClassOf(id0)).assertValue(metaClass);

        submit(mapper.metaClassFor(id1, metaClass));
        await(mapper.metaClassOf(id1)).assertValue(metaClass);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Different() {
        Id id0 = Id.getProvider().fromLong(40);

        ClassBean metaClass0 = ClassBean.of("Metaclass1", "Uri1");
        ClassBean metaClass1 = ClassBean.of("Metaclass2", "Uri2");

        submit(mapper.metaClassFor(id0, metaClass0));
        await(mapper.metaClassOf(id0)).assertValue(metaClass0);

        await(mapper.metaClassFor(id0, metaClass1)).assertError(ClassAlreadyExistsException.class);
        await(mapper.metaClassOf(id0)).assertValue(metaClass0);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} when the element doesn't exist..
     */
    @Test
    public void testGet_Metaclass_NotDefined() {
        Id id0 = Id.getProvider().fromLong(40);

        await(mapper.metaClassOf(id0)).assertNoErrors().assertNoValues();
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
    @ArgumentsSource(ParametersProvider.class)
    public void testGetSet_Single(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        submit(m.set(sfBase, value0));
        await(m.get(sfBase)).assertValue(value0);

        submit(m.set(sfBase, value1));
        await(m.get(sfBase)).assertValue(value1);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGet_Single_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.get(sfBase)).assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSet_Single_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.set(sfBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Single(RedirectionType type, Object value0) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        submit(m.set(sfBase, value0));
        await(m.get(sfBase)).assertValue(value0);

        submit(m.remove(sfBase));
        await(m.get(sfBase)).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Single_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.remove(sfBase)).assertNoErrors().assertComplete();
    }

    //endregion

    //region Multi-valued attributes

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} and {@link
     * ManyValueMapper#valueFor(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetSet_Many(RedirectionType type, Object value0, Object value1, Object value2, Object value3) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1))).assertComplete();

        await(m.set(sfBase.withPosition(0), value2)).assertComplete();
        await(m.get(sfBase.withPosition(0))).assertValue(value2);

        await(m.set(sfBase.withPosition(1), value3)).assertComplete();
        await(m.get(sfBase.withPosition(1))).assertValue(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGet_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.get(mfBase)).assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} when the reference doesn't
     * exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSet_Many_NotDefined(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.set(sfBase.withPosition(0), value0)).assertError(NoSuchElementException.class);

        await(m.add(sfBase.withPosition(0), value0)).assertComplete();

        await(m.set(sfBase.withPosition(1), value1)).assertError(NoSuchElementException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSet_Many_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.set(mfBase, null)
        )).isExactlyInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetAll_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1, value2))).assertComplete();

        await(m.getAll(sfBase)).assertValueCount(3).assertValues(value0, value1, value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)} when the feature doesn't contain
     * any element.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetAll_Many_Empty(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.getAll(sfBase)).assertNoErrors().assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAdd_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.add(sfBase.withPosition(0), value0)).assertComplete();
        await(m.add(sfBase.withPosition(1), value1)).assertComplete();
        await(m.add(sfBase.withPosition(2), value2)).assertComplete();

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);
        await(m.get(sfBase.withPosition(2))).assertValue(value2);

        await(m.sizeOf(sfBase)).assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAdd_Many_OverSize(RedirectionType type, Object value0) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        try {
            await(m.add(sfBase.withPosition(2), value0)).assertError(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAdd_Many_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.add(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppend_Many(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.append(sfBase, value0)).assertValue(0);
        await(m.get(sfBase.withPosition(0))).assertValue(value0);

        await(m.append(sfBase, value1)).assertValue(1);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);

        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendValue(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppend_Many_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.append(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_FromStart(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.addAll(sfBase.withPosition(0), Arrays.asList(value0, value1))).assertComplete();

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);

        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_WithOffset(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        try {
            await(m.addAll(sfBase.withPosition(1), Arrays.asList(value0, value1))).assertError(IndexOutOfBoundsException.class);
        }
        catch (IndexOutOfBoundsException expected) {
            // Expected
        }
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_FromMiddle(RedirectionType type, Object value0, Object value1, Object value2, Object value3) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.append(sfBase, value0)).assertComplete();
        await(m.append(sfBase, value1)).assertComplete();

        await(m.addAll(sfBase.withPosition(1), Arrays.asList(value2, value3))).assertComplete();

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value2);
        await(m.get(sfBase.withPosition(2))).assertValue(value3);
        await(m.get(sfBase.withPosition(3))).assertValue(value1);

        await(m.sizeOf(sfBase)).assertValue(4);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_FromEnd(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.append(sfBase, value0)).assertComplete();

        await(m.addAll(sfBase.withPosition(1), Arrays.asList(value1, value2))).assertComplete();

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);
        await(m.get(sfBase.withPosition(2))).assertValue(value2);

        await(m.sizeOf(sfBase)).assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with an empty collection.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_Empty(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.addAll(mfBase, Collections.emptyList())).assertComplete();

        await(m.sizeOf(mfBase.withoutPosition())).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_WithNull(RedirectionType type, Object value0) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.addAll(mfBase, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with a {@code null}
     * collection.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.addAll(mfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_FromStart(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1))).assertValue(0);

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);

        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature already
     * has values.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_FromEnd(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.append(sfBase, value0)).assertValue(0);
        await(m.get(sfBase.withPosition(0))).assertValue(value0);

        await(m.appendAll(sfBase, Arrays.asList(value1, value2))).assertValue(1);

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);
        await(m.get(sfBase.withPosition(2))).assertValue(value2);

        await(m.sizeOf(sfBase)).assertValue(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_Empty(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Collections.emptyList())).assertValue(0);

        await(m.sizeOf(sfBase)).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a collection that
     * contains a {@code null} element.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_WithNull(RedirectionType type, Object value0) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.appendAll(sfBase, Arrays.asList(value0, null))
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with a {@code null}
     * collection.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.appendAll(sfBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1))).assertComplete();

        await(m.remove(sfBase.withPosition(0))).assertValue(true);
        await(m.sizeOf(sfBase)).assertValue(1);

        await(m.remove(sfBase.withPosition(0))).assertValue(true);
        await(m.sizeOf(sfBase)).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_Before(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1, value2))).assertComplete();

        await(m.remove(sfBase.withPosition(0))).assertValue(true);

        await(m.get(sfBase.withPosition(0))).assertValue(value1);
        await(m.get(sfBase.withPosition(1))).assertValue(value2);
        await(m.get(sfBase.withPosition(2))).assertNoValues();

        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_After(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1, value2))).assertComplete();

        await(m.remove(sfBase.withPosition(1))).assertValue(true);

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value2);
        await(m.get(sfBase.withPosition(2))).assertNoValues();

        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_Last(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1, value2))).assertComplete();

        await(m.remove(sfBase.withPosition(2))).assertValue(true);

        await(m.get(sfBase.withPosition(0))).assertValue(value0);
        await(m.get(sfBase.withPosition(1))).assertValue(value1);
        await(m.get(sfBase.withPosition(2))).assertNoValues();

        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.remove(mfBase)).assertNoErrors().assertValue(false);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemoveAll_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1, value2))).assertComplete();

        await(m.removeAll(sfBase)).assertComplete();

        await(m.get(sfBase.withPosition(0))).assertNoValues();
        await(m.get(sfBase.withPosition(1))).assertNoValues();
        await(m.get(sfBase.withPosition(2))).assertNoValues();

        await(m.sizeOf(sfBase)).assertNoValues();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemoveAll_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.removeAll(sfBase)).assertNoErrors().assertComplete();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSize_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.appendAll(sfBase, Arrays.asList(value0, value1, value2))).assertComplete();
        await(m.sizeOf(sfBase)).assertValue(3);

        await(m.remove(sfBase.withPosition(1))).assertValue(true);
        await(m.sizeOf(sfBase)).assertValue(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSize_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        await(m.sizeOf(sfBase)).assertNoErrors().assertNoValues();
    }

    //endregion

    /**
     * An {@link ArgumentsProvider} that with values defined by {@link RedirectionType type}.
     */
    @ParametersAreNonnullByDefault
    public static final class ParametersProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(RedirectionType.ATTRIBUTE,
                            "Value0",
                            "Value1",
                            "Value2",
                            "Value3"
                    ),
                    Arguments.of(RedirectionType.REFERENCE,
                            Id.getProvider().fromLong(0xa),
                            Id.getProvider().fromLong(0xb),
                            Id.getProvider().fromLong(0xc),
                            Id.getProvider().fromLong(0xd)
                    )
            );
        }
    }
}
