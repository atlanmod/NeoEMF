/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.commons.AbstractTest;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link EObjects}.
 */
@ParametersAreNonnullByDefault
public class EObjectsTest extends AbstractTest {

    @Test
    public void testIsAttribute() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.isAttribute(attribute)).isTrue();
        assertThat(EObjects.isAttribute(reference)).isFalse();
    }

    @Test
    public void testIsReference() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.isReference(reference)).isTrue();
        assertThat(EObjects.isReference(attribute)).isFalse();
    }

    @Test
    public void testAsAttribute() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.asAttribute(attribute)).isInstanceOf(EAttribute.class);

        assertThat(catchThrowable(() ->
                EObjects.asAttribute(reference)
        )).isExactlyInstanceOf(ClassCastException.class);
    }

    @Test
    public void testAsReference() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.asReference(reference)).isInstanceOf(EReference.class);

        assertThat(catchThrowable(() ->
                EObjects.asReference(attribute))
        ).isExactlyInstanceOf(ClassCastException.class);
    }
}