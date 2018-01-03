/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test-case about {@link DataMapper} and its implementations.
 * <p>
 * Because the behavior of attributes and references must be identical, these test-cases use {@link DataMapperRedirector}s
 * that redirect every calls according to a {@link RedirectionType} given as parameter by the {@link ArgumentsProvider}.
 */
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
     * Defines the meta-class of the given {@code references}.
     *
     * @param references the references to update
     */
    private void updateInstanceOf(Id... references) {
        Arrays.stream(references).forEach(i -> mapper.metaClassFor(i, cBase));
    }

    /**
     * Closes the {@link DataMapper}.
     */
    @AfterEach
    void closeMapper() {
        if (nonNull(mapper)) {
            mapper.close();
        }
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

        mapper.containerFor(idBase, container);
        assertThat(mapper.containerOf(idBase)).contains(container);

        mapper.containerFor(id1, container);
        assertThat(mapper.containerOf(id1)).contains(container);

        mapper.removeContainer(idBase);
        mapper.removeContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} and {@link ContainerMapper#containerFor(Id,
     * SingleFeatureBean)}.
     */
    @Test
    public void testGetSet_Container_Different() {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);
        SingleFeatureBean container1 = SingleFeatureBean.of(id0, 21);

        mapper.containerFor(idBase, container0);
        assertThat(mapper.containerOf(idBase)).contains(container0);

        mapper.containerFor(id1, container1);
        assertThat(mapper.containerOf(id1)).contains(container1);

        mapper.containerFor(idBase, container1);
        assertThat(mapper.containerOf(idBase)).contains(container1);

        mapper.removeContainer(idBase);
        mapper.removeContainer(id1);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerOf(Id)} when the element doesn't exist.
     */
    @Test
    public void testGet_Container_NotDefined() {
        assertThat(catchThrowable(() ->
                assertThat(mapper.containerOf(idBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Container_Null() {
        assertThat(catchThrowable(() ->
                mapper.containerFor(idBase, null)
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ContainerMapper#containerFor(Id, SingleFeatureBean)} with a {@code null} value.
     */
    @Test
    public void testRemove_Container() {
        SingleFeatureBean container0 = SingleFeatureBean.of(id0, 20);

        mapper.containerFor(idBase, container0);
        assertThat(mapper.containerOf(idBase)).contains(container0);

        mapper.removeContainer(idBase);
        assertThat(mapper.containerOf(idBase)).isNotPresent();

        mapper.removeContainer(idBase);
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

        assertThat(mapper.metaClassFor(id0, metaClass)).isTrue();
        assertThat(mapper.metaClassOf(id0)).contains(metaClass);

        assertThat(mapper.metaClassFor(id1, metaClass)).isTrue();
        assertThat(mapper.metaClassOf(id1)).contains(metaClass);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} and {@link ClassMapper#metaClassFor(Id, ClassBean)}.
     */
    @Test
    public void testGetSet_Metaclass_Different() {
        Id id0 = Id.getProvider().fromLong(40);

        ClassBean metaClass0 = ClassBean.of("Metaclass1", "Uri1");
        ClassBean metaClass1 = ClassBean.of("Metaclass2", "Uri2");

        assertThat(mapper.metaClassFor(id0, metaClass0)).isTrue();
        assertThat(mapper.metaClassOf(id0)).contains(metaClass0);

        assertThat(mapper.metaClassFor(id0, metaClass1)).isFalse();
        assertThat(mapper.metaClassOf(id0)).contains(metaClass0);
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassOf(Id)} when the element doesn't exist..
     */
    @Test
    public void testGet_Metaclass_NotDefined() {
        Id id0 = Id.getProvider().fromLong(40);

        assertThat(catchThrowable(() ->
                assertThat(mapper.metaClassOf(id0)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ClassMapper#metaClassFor(Id, ClassBean)} with a {@code null} value.
     */
    @Test
    public void testSet_Metaclass_Null() {
        Id id0 = Id.getProvider().fromLong(40);

        assertThat(catchThrowable(() ->
                mapper.metaClassFor(id0, null)
        )).isInstanceOf(NullPointerException.class);
    }

    //endregion

    //region Single-value features

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)} and {@link
     * ValueMapper#valueFor(SingleFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetSet_Single(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.set(sfBase, value0);
        assertThat(m.get(sfBase)).contains(value0);

        assertThat(m.set(sfBase, value1)).contains(value0);
        assertThat(m.get(sfBase)).contains(value1);
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueOf(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGet_Single_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.get(sfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ValueMapper#valueFor(SingleFeatureBean, Object)} with a {@code null} value.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSet_Single_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.set(sfBase, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Single(RedirectionType type, Object value0) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.set(sfBase, value0);
        assertThat(m.get(sfBase)).isPresent();

        m.remove(sfBase);
        assertThat(m.get(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ValueMapper#removeValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Single_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.remove(sfBase)
        )).isNull();
    }

    //endregion

    //region Multi-valued features

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} and {@link
     * ManyValueMapper#valueFor(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetSet_Many(RedirectionType type, Object value0, Object value1, Object value2, Object value3) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1));

        assertThat(m.set(sfBase.withPosition(0), value2)).contains(value0);
        assertThat(m.get(sfBase.withPosition(0))).contains(value2);

        assertThat(m.set(sfBase.withPosition(1), value3)).contains(value1);
        assertThat(m.get(sfBase.withPosition(1))).contains(value3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueOf(ManyFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGet_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.get(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} when the reference doesn't
     * exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSet_Many_NotDefined(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.set(sfBase.withPosition(0), value0)).isNotPresent()
        )).isInstanceOf(NoSuchElementException.class);

        m.add(sfBase.withPosition(0), value0);

        // FIXME Must always throws a NoSuchElementException (IndexOutOfBoundsException with generic mappers)
        assertThat(catchThrowable(() ->
                assertThat(m.set(sfBase.withPosition(1), value1)).isNotPresent()
        )).isNotNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#valueFor(ManyFeatureBean, Object)} with a {@code null} value.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSet_Many_Null(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.set(mfBase, null)).isNotPresent()
        )).isInstanceOf(NullPointerException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetAll_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1, value2));

        List<Object> actualValues = m.getAll(sfBase).collect(Collectors.toList());
        assertThat(actualValues).hasSize(3);

        assertThat(actualValues.get(0)).isEqualTo(value0);
        assertThat(actualValues.get(1)).isEqualTo(value1);
        assertThat(actualValues.get(2)).isEqualTo(value2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#allValuesOf(SingleFeatureBean)} when the feature doesn't contain
     * any element.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testGetAll_Many_Empty(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.getAll(sfBase)).isNotNull().isEmpty()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAdd_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.add(sfBase.withPosition(0), value0);
        m.add(sfBase.withPosition(1), value1);
        m.add(sfBase.withPosition(2), value2);

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);
        assertThat(m.get(sfBase.withPosition(2))).contains(value2);

        assertThat(m.sizeOf(sfBase)).contains(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addValue(ManyFeatureBean, Object)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAdd_Many_OverSize(RedirectionType type, Object value0) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.add(sfBase.withPosition(2), value0)
        )).isInstanceOf(IndexOutOfBoundsException.class);
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

        int index;

        index = m.append(sfBase, value0);
        assertThat(index).isEqualTo(0);
        assertThat(m.get(sfBase.withPosition(0))).contains(value0);

        index = m.append(sfBase, value1);
        assertThat(index).isEqualTo(1);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);
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

        m.addAll(sfBase.withPosition(0), Arrays.asList(value0, value1));

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);

        assertThat(m.sizeOf(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature is not
     * defined yet.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_WithOffset(RedirectionType type, Object value0, Object value1) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.addAll(sfBase.withPosition(1), Arrays.asList(value0, value1))
        )).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_FromMiddle(RedirectionType type, Object value0, Object value1, Object value2, Object value3) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.append(sfBase, value0);
        m.append(sfBase, value1);

        m.addAll(sfBase.withPosition(1), Arrays.asList(value2, value3));

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value2);
        assertThat(m.get(sfBase.withPosition(2))).contains(value3);
        assertThat(m.get(sfBase.withPosition(3))).contains(value1);

        assertThat(m.sizeOf(sfBase)).contains(4);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} when the feature already has
     * values.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_FromEnd(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.append(sfBase, value0);

        m.addAll(sfBase.withPosition(1), Arrays.asList(value1, value2));

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);
        assertThat(m.get(sfBase.withPosition(2))).contains(value2);

        assertThat(m.sizeOf(sfBase)).contains(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#addAllValues(ManyFeatureBean, List)} with an empty collection.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAddAll_Many_Empty(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.addAll(mfBase, Collections.emptyList());

        assertThat(m.sizeOf(mfBase.withoutPosition())).isNotPresent();
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

        int index;

        index = m.appendAll(sfBase, Arrays.asList(value0, value1));
        assertThat(index).isEqualTo(0);

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);

        assertThat(m.sizeOf(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} when the feature already
     * has values.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_FromEnd(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        int index;

        index = m.append(sfBase, value0);
        assertThat(index).isEqualTo(0);
        assertThat(m.get(sfBase.withPosition(0))).contains(value0);

        index = m.appendAll(sfBase, Arrays.asList(value1, value2));
        assertThat(index).isEqualTo(1);

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);
        assertThat(m.get(sfBase.withPosition(2))).contains(value2);

        assertThat(m.sizeOf(sfBase)).contains(3);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#appendAllValues(SingleFeatureBean, List)} with an empty
     * collection.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testAppendAll_Many_Empty(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Collections.emptyList());

        assertThat(m.sizeOf(sfBase)).isNotPresent();
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

        m.appendAll(sfBase, Arrays.asList(value0, value1));

        m.remove(sfBase.withPosition(0));
        assertThat(m.sizeOf(sfBase)).contains(1);

        m.remove(sfBase.withPosition(0));
        assertThat(m.sizeOf(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_Before(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1, value2));

        assertThat(m.remove(sfBase.withPosition(0))).contains(value0);

        assertThat(m.get(sfBase.withPosition(0))).contains(value1);
        assertThat(m.get(sfBase.withPosition(1))).contains(value2);
        assertThat(m.get(sfBase.withPosition(2))).isNotPresent();

        assertThat(m.sizeOf(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_After(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1, value2));

        assertThat(m.remove(sfBase.withPosition(1))).contains(value1);

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value2);
        assertThat(m.get(sfBase.withPosition(2))).isNotPresent();

        assertThat(m.sizeOf(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_Last(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1, value2));

        assertThat(m.remove(sfBase.withPosition(2))).contains(value2);

        assertThat(m.get(sfBase.withPosition(0))).contains(value0);
        assertThat(m.get(sfBase.withPosition(1))).contains(value1);
        assertThat(m.get(sfBase.withPosition(2))).isNotPresent();

        assertThat(m.sizeOf(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeValue(ManyFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemove_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.remove(mfBase)).isNotPresent()
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemoveAll_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1, value2));

        m.removeAll(sfBase);

        assertThat(m.get(sfBase.withPosition(0))).isNotPresent();
        assertThat(m.get(sfBase.withPosition(1))).isNotPresent();
        assertThat(m.get(sfBase.withPosition(2))).isNotPresent();

        assertThat(m.sizeOf(sfBase)).isNotPresent();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#removeAllValues(SingleFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testRemoveAll_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                m.removeAll(sfBase)
        )).isNull();
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSize_Many(RedirectionType type, Object value0, Object value1, Object value2) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        m.appendAll(sfBase, Arrays.asList(value0, value1, value2));
        assertThat(m.sizeOf(sfBase)).contains(3);

        m.remove(sfBase.withPosition(1));
        assertThat(m.sizeOf(sfBase)).contains(2);
    }

    /**
     * Checks the behavior of {@link ManyValueMapper#sizeOfValue(SingleFeatureBean)} when the value doesn't exist.
     */
    @ParameterizedTest
    @ArgumentsSource(ParametersProvider.class)
    public void testSize_Many_NotDefined(RedirectionType type) {
        DataMapperRedirector m = new DataMapperRedirector(mapper, type);

        assertThat(catchThrowable(() ->
                assertThat(m.sizeOf(sfBase)).isNotPresent()
        )).isNull();
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
