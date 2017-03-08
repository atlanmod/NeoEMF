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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping features, containers and metaclasses represented as a set of key/value pair.
 *
 * @see ContainerMapper
 * @see MetaclassMapper
 * @see ValueMapper
 * @see ReferenceMapper
 * @see MultiValueMapper
 * @see MultiReferenceMapper
 */
@ParametersAreNonnullByDefault
public interface PersistenceMapper extends Closeable, ContainerMapper, MetaclassMapper, ValueMapper, MultiValueMapper, ReferenceMapper, MultiReferenceMapper {

    /**
     * Saves all changes made on this mapper since the last call.
     */
    void save();

    /**
     * Cleanly closes this mapper and releases any system resources associated with it. If it is already closed then
     * invoking this method has no effect.
     * <p>
     * All modifications are saved before closing.
     */
    @Override
    void close();

    /**
     * Copies all the contents from this mapper to the {@code target}.
     *
     * @param target the mapper to copy the database contents to
     */
    void copyTo(PersistenceMapper target);

    /**
     * Checks whether the specified {@code id} already exists in this {@code PersistenceMapper}.
     *
     * @param id the identifier to check
     *
     * @return {@code true} if the {@code id} exists, {@code false} otherwise.
     */
    boolean exists(Id id);

    /**
     * Back-end specific computation of {@link Resource#getAllContents()}.
     *
     * @param eClass the class to compute the instances of
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return an {@link Object} containing the back-end specific objects corresponding to the instances of the {@link
     * EClass}
     *
     * @throws UnsupportedOperationException if the back-end doesn't support the lookup of all instances
     */
    @Nonnull
    default Iterable<Id> allInstances(EClass eClass, boolean strict) {
        throw new UnsupportedOperationException("This back-end doesn't support the lookup of all instances");
    }
}
