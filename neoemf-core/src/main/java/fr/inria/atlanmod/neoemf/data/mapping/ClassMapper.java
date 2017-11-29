/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * An object capable of mapping meta-classes.
 *
 * @see ClassBean
 */
@ParametersAreNonnullByDefault
public interface ClassMapper {

    /**
     * Retrieves the meta-class for the specified {@code id}.
     *
     * @param id the {@link Id} of the element
     *
     * @return the deferred computation to execute, that may contains the meta-class.
     */
    @Nonnull
    Maybe<ClassBean> metaClassOf(Id id);

    /**
     * Stores the {@code metaClass} for the specified {@code id}, only if a meta-class is not already defined for the
     * {@code id}.
     *
     * @param id        the {@link Id} of the element
     * @param metaClass the containing element's meta-class information to store
     *
     * @return the deferred computation to execute, that contains a {@link ClassAlreadyExistsException} if a meta-class
     * is already defined for {@code id}
     *
     * @see CommonQueries#classAlreadyExists()
     */
    @Nonnull
    Completable metaClassFor(Id id, ClassBean metaClass);

    /**
     * Retrieves all instances of the given {@code metaClass}.
     *
     * @param metaClass the meta-class to compute the instances of
     * @param strict    {@code true} if the lookup searches for strict instances
     *
     * @return the deferred computation to execute, that may contains the instances of the {@code metaClass} or an
     * {@link UnsupportedOperationException} if this mapper does not support this operation
     */
    @Nonnull
    Observable<Id> allInstancesOf(ClassBean metaClass, boolean strict);

    /**
     * Retrieves all instances of the given {@code metaClasses}.
     *
     * @param metaClasses the meta-classes to compute the instances of
     *
     * @return the deferred computation to execute, that may contains the instances of the {@code metaClass} or an
     * {@link UnsupportedOperationException} if this mapper does not support this operation
     */
    @Nonnull
    Observable<Id> allInstancesOf(Set<ClassBean> metaClasses);
}
