/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Storable;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Resource} that provides efficient model-level operations that are not accessible using the standard EMF
 * API.
 * <p>
 * For example, {@link #allInstancesOf(EClass)} is a utility method that computes efficiently all the instances of a
 * given type by delegating the operation to the underlying database, that can benefits of its internal optimizations
 * and indices.
 */
@ParametersAreNonnullByDefault
public interface PersistentResource extends Resource, Resource.Internal, Storable, Closeable, Iterable<PersistentEObject> {

    /**
     * The identifier of the root element in a {@code PersistentResource}.
     */
    @Nonnull
    Id ROOT_ID = Id.getProvider().fromLong(0);

    /**
     * The name of the property used by the {@link #ROOT_ID} element to define its content.
     */
    @Nonnegative
    int ROOT_REFERENCE_ID = RESOURCE__CONTENTS;

    /**
     * The name of the property used by the {@link #ROOT_ID} element to define its content.
     */
    @Nonnull
    String ROOT_REFERENCE_NAME = "eContents";

    @Override
    void close();

    /**
     * Saves the resource using the specified configuration.
     *
     * @param config the save configuration
     *
     * @throws IOException if an I/O error occurs during saving
     * @see #save(Map)
     */
    void save(ImmutableConfig config) throws IOException;

    /**
     * Loads the resource using the specified configuration.
     *
     * @param config the load configuration
     *
     * @throws IOException if an I/O error occurs during loading
     * @see #load(Map)
     */
    void load(ImmutableConfig config) throws IOException;

    /**
     * Computes the set of instances of the given {@link EClass} (including its sub-types).
     * <p>
     * This method behaves like: {@code allInstancesOf(EClass, false)}.
     *
     * @param eClass the {@link EClass} for which look for instances
     *
     * @return all the instances of the given {@link EClass} from the resource
     *
     * @see #allInstancesOf(EClass, boolean)
     */
    @Nonnull
    default <T extends EObject> Iterable<T> allInstancesOf(EClass eClass) {
        return allInstancesOf(eClass, false);
    }

    /**
     * Computes the set of instances of the given {@link EClass}.
     *
     * @param eClass the {@link EClass} for which look for instances
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return if {@code true} then the method returns only the strict instances of the given {@link EClass}, otherwise
     * it also returns the instances of the sub-types of {@code eClass}.
     */
    @Nonnull
    <T extends EObject> Iterable<T> allInstancesOf(EClass eClass, boolean strict);

    /**
     * Returns an iterator on the direct content of this resource.
     *
     * @return an iterator
     *
     * @see #getContents()
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    default Iterator<PersistentEObject> iterator() {
        return Iterator.class.cast(getContents().iterator());
    }
}
