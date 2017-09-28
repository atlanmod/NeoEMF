/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.AbstractTest;

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
public class DynamicPersistentEObjectTest extends AbstractTest {

    @Test
    public void testProxyCreation() throws Exception {
        EObject dynamicObject = new DynamicEObjectImpl();
        PersistentEObject object = PersistentEObject.from(dynamicObject);

        assertThat(object).isNotNull();
        assertThat(object).isInstanceOf(PersistentEObject.class);
    }

    @Test
    public void testCallCommonMethod() throws Exception {
        EObject dynamicObject = new DynamicEObjectImpl();
        PersistentEObject object = PersistentEObject.from(dynamicObject);

        // Call without exceptions
        assertThat(object.eResource()).isNull();
        assertThat(object.eContainer()).isNull();
    }

    @Test
    public void testCallDynamicMethod() throws Exception {
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
