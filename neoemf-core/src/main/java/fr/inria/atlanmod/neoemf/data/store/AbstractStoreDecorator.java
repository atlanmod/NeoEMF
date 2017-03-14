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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractMapperDecorator;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Store} wrapper that delegates method calls to an internal {@link Store}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStoreDecorator extends AbstractMapperDecorator<Store> implements Store {

    /**
     * Constructs a new {@code AbstractStoreDecorator} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected AbstractStoreDecorator(Store store) {
        super(store);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public PersistentResource resource() {
        return next().resource();
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next().backend();
    }
}
