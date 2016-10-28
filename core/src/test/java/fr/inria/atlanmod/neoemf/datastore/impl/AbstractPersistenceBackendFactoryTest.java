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
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AbstractDirectWriteResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.lang.reflect.Field;

public abstract class AbstractPersistenceBackendFactoryTest extends AllTest {

    /**
     * Utility method to retrieve the PersistentEStore associated to a store.
     */
    protected PersistenceBackend getInnerBackend(PersistentEStore store) {
        // context is the real EStore, which can de decorated.
        PersistentEStore context = store.getEStore();
        PersistenceBackend result = null;

        try {
            Field field = AbstractDirectWriteResourceEStore.class.getDeclaredField("persistenceBackend");
            field.setAccessible(true);
            result = (PersistenceBackend) field.get(context);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            NeoLogger.error(e);
        }

        return result;
    }
}
