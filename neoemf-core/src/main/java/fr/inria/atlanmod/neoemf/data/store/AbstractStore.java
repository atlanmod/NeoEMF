/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapperChain;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Store} wrapper that delegates method calls to an internal {@link Store}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStore extends AbstractDataMapperChain<Store> implements Store, Comparable<AbstractStore> {

    /**
     * The order of this store in the chain.
     */
    private final int order;

    /**
     * Constructs a new {@code AbstractStore} with the given {@code order}.
     * <p>
     * The smaller the order, the sooner this store will be visited.
     *
     * @param order the order of this store in the chain
     */
    protected AbstractStore(int order) {
        this.order = order;
    }

    @Override
    protected void next(Store next) {
        // This method is present for the StoreFactory
        super.next(next);
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next().backend();
    }

    @Override
    public int compareTo(AbstractStore o) {
        return order - o.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    @Override
    public boolean equals(@Nullable Object o) {
        return this == o || o != null && getClass() == o.getClass();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '(' + order + ')';
    }
}
