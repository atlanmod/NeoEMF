/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
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
