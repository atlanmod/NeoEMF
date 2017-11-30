/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.query;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.mapping.ClassAlreadyExistsException;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * Static utility class that provides common queries, and functions used to build queries.
 */
@Static
@ParametersAreNonnullByDefault
public final class CommonQueries {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private CommonQueries() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns a {@link Consumer} that process an already existing meta-class.
     *
     * @param <T> the type of the consumed object
     *
     * @return a consumer
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ClassMapper#metaClassFor(Id, ClassBean)
     */
    @Nonnull
    public static <T> Consumer<T> classAlreadyExists() {
        return Functions.actionConsumer(() -> { throw new ClassAlreadyExistsException(); });
    }

    /**
     * Returns a {@link Callable} that creates the {@link RuntimeException} used when calling {@link
     * fr.inria.atlanmod.neoemf.data.mapping.DataMapper#allInstancesOf(Set)} on an instance that does not support this
     * operation.
     *
     * @return a callable
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ClassMapper#allInstancesOf(ClassBean, boolean)
     * @see fr.inria.atlanmod.neoemf.data.mapping.ClassMapper#allInstancesOf(Set)
     */
    @Nonnull
    public static <V> Observable<V> unsupportedAllInstancesOf() {
        return Observable.error(UnsupportedOperationException::new);
    }

    /**
     * Returns a {@link Maybe} instance into an {@link Optional}.
     *
     * @param maybe the {@code Maybe} to convert
     * @param <R>   the result type
     *
     * @return an {@link Optional} containing the value of {@code maybe}, or {@link Optional#empty()} if {@code maybe}
     * has no value
     */
    @Nonnull
    public static <R> Optional<R> toOptional(Maybe<R> maybe) {
        return maybe.to(v -> Optional.ofNullable(v.blockingGet()));
    }
}
