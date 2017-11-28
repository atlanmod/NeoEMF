/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.internal.functions.Functions;

/**
 * A {@link Store} wrapper that caches containers.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class ContainerCacheStore extends AbstractCacheStore<Id, SingleFeatureBean> {

    /**
     * Constructs a new {@code ContainerCacheStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    protected ContainerCacheStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        Maybe<SingleFeatureBean> ifEmptyFunc = super.containerOf(id)
                .doOnSuccess(c -> cache.putIfAbsent(id, c));

        return Maybe.fromCallable(() -> cache.get(id))
                .switchIfEmpty(ifEmptyFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        Maybe<SingleFeatureBean> ifEmptyFunc = super.containerFor(id, container)
                .doOnComplete(() -> cache.put(id, container))
                .toMaybe();

        return Maybe.fromCallable(() -> cache.get(id))
                .filter(Functions.equalsWith(container)) // Ensure the container is the same
                .switchIfEmpty(ifEmptyFunc)
                .ignoreElement()
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        return super.removeContainer(id)
                .doOnComplete(() -> cache.invalidate(id))
                .cache();
    }
}
