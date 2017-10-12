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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.Returns;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A test-case about {@link AttributeConverter} and {@link FeatureMapConverter}.
 */
@ParametersAreNonnullByDefault
class AttributeConverterTest extends AbstractTest {

    private AttributeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new AttributeConverter(new FeatureMapConverter(mock(AbstractStoreAdapter.class)));
    }

    @Test
    void testConvertNull() {
        assertThat(converter.convert(null, mock(EAttribute.class))).isNull();
    }

    @Test
    void testConvertPrimitive() {
        Class<?> type = Boolean.class;

        EDataType dataType = mock(EDataType.class);
        when(dataType.getInstanceClass()).thenAnswer(new Returns(type));

        EAttribute attribute = mock(EAttribute.class);
        when(attribute.getEAttributeType()).thenReturn(dataType);

        assertThat(converter.convert(true, attribute)).isNotNull().isEqualTo(true);
    }

    @Test
    void testConvertNonPrimitive() {
        // TODO
    }

    @Test
    void testConvertFeatureMap() {
        // TODO
    }

    @Test
    void testRevertNull() {
        assertThat(converter.revert(null, mock(EAttribute.class))).isNull();
    }

    @Test
    void testRevertPrimitive() {
        Class<?> type = Boolean.class;

        EDataType dataType = mock(EDataType.class);
        when(dataType.getInstanceClass()).thenAnswer(new Returns(type));

        EAttribute attribute = mock(EAttribute.class);
        when(attribute.getEAttributeType()).thenReturn(dataType);

        assertThat(converter.revert(true, attribute)).isNotNull().isEqualTo(true);
    }

    @Test
    void testtRevertNonPrimitive() {
        // TODO
    }

    @Test
    void testRevertFeatureMap() {
        // TODO
    }
}