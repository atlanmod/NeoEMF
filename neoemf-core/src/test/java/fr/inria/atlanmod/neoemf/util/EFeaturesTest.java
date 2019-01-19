/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import org.atlanmod.commons.AbstractTest;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link EFeatures}.
 */
@ParametersAreNonnullByDefault
class EFeaturesTest extends AbstractTest {

    @Test
    void testIsAttribute() {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EFeatures.isAttribute(attribute)).isTrue();
        assertThat(EFeatures.isAttribute(reference)).isFalse();
    }

    @Test
    void testIsReference() {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EFeatures.isReference(reference)).isTrue();
        assertThat(EFeatures.isReference(attribute)).isFalse();
    }

    @Test
    void testAsAttribute() {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EFeatures.asAttribute(attribute)).isInstanceOf(EAttribute.class);

        assertThat(catchThrowable(() ->
                EFeatures.asAttribute(reference)
        )).isExactlyInstanceOf(ClassCastException.class);
    }

    @Test
    void testAsReference() {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EFeatures.asReference(reference)).isInstanceOf(EReference.class);

        assertThat(catchThrowable(() ->
                EFeatures.asReference(attribute))
        ).isExactlyInstanceOf(ClassCastException.class);
    }
}