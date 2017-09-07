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
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperDecorator;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Store} wrapper that delegates method calls to an internal {@link Store}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStore extends AbstractMapperDecorator<Store> implements Store {

    /**
     * Constructs a new {@code AbstractStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected AbstractStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next().backend();
    }

    @Override
    public int hashCode() {
        return Objects.hash(backend());
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || AbstractStore.class.isInstance(o)) {
            return false;
        }

        AbstractStore that = AbstractStore.class.cast(o);
        return Objects.equals(backend(), that.backend());
    }
}
