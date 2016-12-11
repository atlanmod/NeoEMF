/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractPersistenceBackendFactoryTest extends AbstractUnitTest {

    /**
     * Utility method to retreive the {@link PersistentStore} included in the given {@code store}.
     */
    protected PersistentStore getChildStore(InternalEObject.EStore store) throws SecurityException, IllegalArgumentException {
        assertThat(store).isInstanceOf(PersistentStore.class); // "Invalid call, can not get the child store if the given one is not a DelegatedResourceEStoreImpl"
        return ((PersistentStore) store).getEStore();
    }

    protected void assertHasInnerBackend(PersistentStore store, PersistenceBackend expectedInnerBackend) throws NoSuchFieldException, IllegalAccessException {
        PersistentStore context = store.getEStore();
        PersistenceBackend innerBackend;

        Field field = AbstractDirectWriteStore.class.getDeclaredField("persistenceBackend");
        field.setAccessible(true);
        innerBackend = (PersistenceBackend) field.get(context);

        assertThat(innerBackend).isSameAs(expectedInnerBackend); // "The backend in the EStore is not the created one"
    }
}
