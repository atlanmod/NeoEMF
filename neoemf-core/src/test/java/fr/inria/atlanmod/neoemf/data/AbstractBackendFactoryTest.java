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
import fr.inria.atlanmod.neoemf.data.store.AbstractStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;

import java.lang.reflect.Field;

/**
 * Abstract test cases for {@link BackendFactory}s that provides methods to retrieve private fields.
 */
public abstract class AbstractBackendFactoryTest extends AbstractUnitTest {

    /**
     * The field name describing the inner {@link Store} in a {@link AbstractStoreDecorator}.
     */
    private static final String INNER_STORE_FIELDNAME = "next";

    /**
     * The field name describing the inner {@link Backend} in a {@link DirectWriteStore}.
     */
    private static final String INNER_BACKEND_FIELDNAME = "backend";

    /**
     * Retrieves the value of a field, identified by its {@code fieldName}, in the given {@code object}.
     *
     * @param object    the object where to look for the field
     * @param fieldName the name of the field to retrieve the value
     * @param in        the type of the {@code object}
     * @param out       the type of the expected value
     * @param <I>       the type of the actual value
     * @param <O>       the type of the expected value
     *
     * @return the value
     */
    protected static <I, O> O getValue(Object object, String fieldName, Class<I> in, Class<O> out) {
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

    /**
     * Retrieves the inner {@link Store} in the given {@code store}.
     *
     * @param store the store where to look for the inner store
     *
     * @return the inner store
     */
    protected Store getInnerStore(Store store) {
        return getValue(store, INNER_STORE_FIELDNAME, AbstractStoreDecorator.class, Store.class);
    }

    /**
     * Retrieves the inner {@link Backend} in the given {@code store}.
     *
     * @param store the store where to look for the inner back-end
     *
     * @return the inner back-end
     */
    protected Backend getInnerBackend(Store store) {
        return store.backend();
    }
}
