/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * A {@link Store} wrapper that caches {@link ClassBean}s.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class ClassCacheStore extends AbstractCacheStore<Id, ClassBean> {

    /**
     * Constructs a new {@code ClassCacheStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    protected ClassCacheStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        Callable<ClassBean> getFunc = () -> cache.get(id);

        Consumer<ClassBean> setFunc = m -> cache.put(id, m);

        Maybe<ClassBean> ifEmptyFunc = super.metaClassOf(id)
                .doOnSuccess(setFunc);

        return Maybe.fromCallable(getFunc)
                .switchIfEmpty(ifEmptyFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        Callable<Boolean> existsFunc = () -> cache.contains(id);

        Action setFunc = () -> cache.put(id, metaClass);

        Completable ifEmptyFunc = super.metaClassFor(id, metaClass)
                .doOnComplete(setFunc);

        return Maybe.fromCallable(existsFunc)
                .filter(Functions.equalsWith(true))
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .switchIfEmpty(ifEmptyFunc.toMaybe())
                .ignoreElement()
                .cache();
    }
}
