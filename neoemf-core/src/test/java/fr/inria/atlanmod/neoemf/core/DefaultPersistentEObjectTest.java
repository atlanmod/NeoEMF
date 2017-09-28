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
import fr.inria.atlanmod.neoemf.data.store.adapter.TransientStoreAdapter;

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link DefaultPersistentEObject}.
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentEObjectTest extends AbstractTest {

    @Test
    public void testIdGeneration() throws Exception {
        // Created without an undefined Id
        PersistentEObject object = new DefaultPersistentEObject();

        // Id generated on first call
        assertThat(object.id()).isNotSameAs(Id.UNDEFINED);
    }

    @Test
    public void testStoreGeneration() throws Exception {
        // Created without any store
        PersistentEObject object = new DefaultPersistentEObject();

        // Store created on first call
        assertThat(object.eStore()).isNotNull().isInstanceOf(TransientStoreAdapter.class);
        object.eStore().close();
    }
}
