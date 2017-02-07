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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.Field;

public abstract class AbstractPersistenceBackendFactoryTest extends AbstractUnitTest {

    private static final String INNER_STORE_FIELDNAME = "store";

    private static final String INNER_BACKEND_FIELDNAME = "backend";

    private static <F> F getField(Object object, String fieldName, Class<?> in, Class<F> out) {
        if (!in.isInstance(object)) {
            throw new IllegalArgumentException();
        }

        try {
            Field storeField = in.getDeclaredField(fieldName);
            storeField.setAccessible(true);
            return out.cast(storeField.get(object));
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected PersistentStore getInnerStore(InternalEObject.EStore store) {
        return getField(store, INNER_STORE_FIELDNAME, AbstractPersistentStoreDecorator.class, PersistentStore.class);
    }

    protected PersistenceBackend getInnerBackend(InternalEObject.EStore store) {
        PersistenceBackend innerBackend;

        try {
            innerBackend = getField(store, INNER_BACKEND_FIELDNAME, DirectWriteStore.class, PersistenceBackend.class);
        }
        catch (IllegalArgumentException e) {
            return getInnerBackend(getInnerStore(store));
        }

        return innerBackend;
    }
}
