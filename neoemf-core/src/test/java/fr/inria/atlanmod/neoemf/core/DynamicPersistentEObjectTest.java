/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import org.atlanmod.commons.AbstractTest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case about dynamic {@link PersistentEObject}, accross a proxy.
 */
@ParametersAreNonnullByDefault
class DynamicPersistentEObjectTest extends AbstractTest {

    @Test
    void testProxyCreation() {
        EObject dynamicObject = new DynamicEObjectImpl();
        PersistentEObject object = PersistentEObject.from(dynamicObject);

        assertThat(object)
                .isNotNull()
                .isInstanceOf(PersistentEObject.class);
    }

    @Test
    void testCallCommonMethod() {
        EObject dynamicObject = new DynamicEObjectImpl();
        PersistentEObject object = PersistentEObject.from(dynamicObject);

        // Call without exceptions
        assertThat(object.eResource()).isNull();
        assertThat(object.eContainer()).isNull();
    }

    @Test
    void testCallDynamicMethod() {
        EObject dynamicObject = new DynamicEObjectImpl();
        PersistentEObject object = PersistentEObject.from(dynamicObject);

        // New method signatures
        assertThat(
                catchThrowable(object::id)
        ).isExactlyInstanceOf(UnsupportedOperationException.class);

        assertThat(
                catchThrowable(object::resource)
        ).isExactlyInstanceOf(UnsupportedOperationException.class);

        // Overridden method signature
        assertThat(
                catchThrowable(object::eStore)
        ).isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
