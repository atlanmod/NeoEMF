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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link DataMapper} to establish a mapping between a {@link Resource} and a {@link Backend}.
 */
@ParametersAreNonnullByDefault
public interface Store extends DataMapper {

    /**
     * Returns the resource to store and access.
     *
     * @return the resource
     */
    @Nullable
    Resource resource();

    /**
     * Defines the resource to store and access.
     *
     * @param resource the resource
     */
    void resource(@Nullable Resource resource);

    /**
     * Checks whether this store is attached to a {@link PersistentResource}.
     *
     * @return {@code true} if this store is attached, {@code false} otherwise
     */
    default boolean isPersistent() {
        return resource() instanceof PersistentResource;
    }

    /**
     * Returns the back-end where data are stored.
     *
     * @return the back-end
     */
    @Nonnull
    Backend backend();

    /**
     * Checks whether this store supports auto-save.
     *
     * @return {@code true} if this store supports auto-save, {@code false} otherwise
     *
     * @see AutoSaveStoreDecorator
     */
    default boolean isAutoSave() {
        return false;
    }

    /**
     * Checks whether this store is read-only.
     *
     * @return {@code true} if this store is read-only, {@code false} otherwise
     *
     * @see ReadOnlyStoreDecorator
     */
    default boolean isReadOnly() {
        return false;
    }
}
