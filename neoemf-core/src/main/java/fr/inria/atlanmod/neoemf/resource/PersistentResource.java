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

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.store.Storable;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

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
public interface PersistentResource extends Resource, Resource.Internal, Storable, Closeable {

    /**
     * The identifier of the root element in a {@code PersistentResource}.
     */
    Id ROOT_ID = StringId.of("ROOT");

    /**
     * The name of the property used by the {@link #ROOT_ID} element to define its content.
     */
    String ROOT_REFERENCE_NAME = "eContents";

    @Override
    void close();

    @Override
    void save(Map<?, ?> options) throws IOException;

    @Override
    void load(Map<?, ?> options) throws IOException;

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
    default Iterable<EObject> allInstancesOf(EClass eClass) {
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
    Iterable<EObject> allInstancesOf(EClass eClass, boolean strict);
}
