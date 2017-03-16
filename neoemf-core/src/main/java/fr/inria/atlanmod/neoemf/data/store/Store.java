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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link DataMapper} to establish a mapping between {@link PersistentResource}s and {@link Backend}s.
 */
@ParametersAreNonnullByDefault
public interface Store extends DataMapper {

    /**
     * Returns the {@link PersistentResource} to store and access.
     *
     * @return the resource to store and access
     */
    @Nullable
    PersistentResource resource();

    /**
     * Checks whether this store is attached to a {@link PersistentResource}.
     * <p>
     * A store is only attached in a persistent context.
     *
     * @return {@code true} if this store is attached to a resource, {@code false} otherwise
     */
    default boolean isAttached() {
        return nonNull(resource());
    }

    /**
     * Returns the {@link Backend} where data are stored.
     *
     * @return the backend where data are stored
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
