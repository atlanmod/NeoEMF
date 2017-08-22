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

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

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
     * @return an {@link Optional} containing the meta-class, or {@link Optional#empty()} if the {@code id} has no
     * defined meta-class.
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    Optional<ClassBean> metaClassOf(Id id);

    /**
     * Stores the {@code metaClass} for the specified {@code id}.
     *
     * @param id        the {@link Id} of the element
     * @param metaClass the containing element's meta-class information to store
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    void metaClassFor(Id id, ClassBean metaClass);

    /**
     * Checks whether the specified {@code id} has a defined meta-class.
     *
     * @param id the {@link Id} of the element
     *
     * @return {@code true} if the {@code id} has a meta-class, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code id} is {@code null}
     */
    default boolean hasMetaclass(Id id) {
        return metaClassOf(id).isPresent();
    }

    /**
     * Retrieves all instances of the given {@code metaClass}.
     *
     * @param metaClass the meta-class to compute the instances of
     * @param strict    {@code true} if the lookup searches for strict instances
     *
     * @return a {@link Iterable} containing the instances of the {@code metaClass}
     *
     * @throws UnsupportedOperationException if the mapper doesn't support the lookup of all instances
     */
    @Nonnull
    default Iterable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        throw new UnsupportedOperationException("This back-end doesn't support the lookup of all instances");
    }
}
