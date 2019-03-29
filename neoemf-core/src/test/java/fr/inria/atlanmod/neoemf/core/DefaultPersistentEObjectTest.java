/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.data.store.adapter.TransientStoreAdapter;

import org.atlanmod.commons.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link DefaultPersistentEObject}.
 */
@ParametersAreNonnullByDefault
class DefaultPersistentEObjectTest extends AbstractTest {

    @Test
    void testIdGeneration() {
        // Created without an undefined Id
        PersistentEObject object = new DefaultPersistentEObject();

        // Id generated on first call
        assertThat(object.id()).isNotSameAs(Id.UNDEFINED);
    }

    @Test
    void testStoreGeneration() {
        // Created without any store
        PersistentEObject object = new DefaultPersistentEObject();

        // Store created on first call
        assertThat(object.eStore()).isNotNull().isInstanceOf(TransientStoreAdapter.class);
        object.eStore().close();
    }

    @Test
    void testHashCode() {
        Id id0 = Id.getProvider().fromLong(17L);
        Id id1 = Id.getProvider().fromLong(44L);

        PersistentEObject o0 = new DefaultPersistentEObject(id0);
        PersistentEObject o1 = new DefaultPersistentEObject(id0);
        PersistentEObject o2 = new DefaultPersistentEObject(id1);

        assertThat(o0.hashCode()).isEqualTo(Objects.hash(id0));
        assertThat(o1.hashCode()).isEqualTo(Objects.hash(id0));
        assertThat(o2.hashCode()).isEqualTo(Objects.hash(id1));
    }

    @Test
    void testEquals() {
        Id id = Id.getProvider().fromLong(17L);

        PersistentEObject o0 = new DefaultPersistentEObject(id);
        PersistentEObject o1 = new DefaultPersistentEObject(id);
        PersistentEObject o2 = new DefaultPersistentEObject();

        assertThat(o0).isEqualTo(o1);
        assertThat(o0).isNotEqualTo(o2);
        assertThat(o1).isNotEqualTo(o2);

        assertThat(o0).isEqualTo(o0);
        assertThat(o0).isNotEqualTo(null);
        assertThat(o0).isNotEqualTo(mock(Object.class));
    }
}
