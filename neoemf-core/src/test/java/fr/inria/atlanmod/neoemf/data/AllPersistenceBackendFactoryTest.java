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

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.eclipse.emf.ecore.InternalEObject;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AllPersistenceBackendFactoryTest extends AllTest {

    private File file;

    public File file() {
        return file;
    }

    @Before
    public void setUp() throws InvalidDataStoreException, InvalidOptionsException {
        PersistenceBackendFactoryRegistry.register(uriScheme(), persistenceBackendFactory());
        file = tempFile(name());
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    protected abstract String name();

    protected abstract String uriScheme();

    protected abstract PersistenceBackendFactory persistenceBackendFactory() throws InvalidDataStoreException, InvalidOptionsException;

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
