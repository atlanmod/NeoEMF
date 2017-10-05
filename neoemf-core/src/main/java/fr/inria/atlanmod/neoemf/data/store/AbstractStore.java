/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
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

    @Nonnull
    @Override
    public StoreStats stats() {
        return next().stats();
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
