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

package fr.inria.atlanmod.neoemf.datastore.impl;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.lang.reflect.Field;

public abstract class AbstractPersistenceBackendFactoryTest extends AllTest {

    /**
     * Utility method to retrieve the PersistentStore associated to a store.
     */
    protected PersistenceBackend getInnerBackend(PersistentStore store) {
        // context is the real EStore, which can de decorated.
        PersistentStore context = store.getEStore();
        PersistenceBackend result = null;

        try {
            Field field = AbstractDirectWriteStore.class.getDeclaredField("persistenceBackend");
            field.setAccessible(true);
            result = (PersistenceBackend) field.get(context);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            NeoLogger.error(e);
        }

        return result;
    }
}
