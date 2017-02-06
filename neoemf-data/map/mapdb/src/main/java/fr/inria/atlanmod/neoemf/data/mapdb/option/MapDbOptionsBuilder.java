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

package fr.inria.atlanmod.neoemf.data.mapdb.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;

import javax.annotation.Nonnull;

/**
 * A {@link PersistenceOptionsBuilder} that creates MapDB specific options.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
public class MapDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<MapDbOptionsBuilder, MapDbOptions> {

    /**
     * Constructs a new {@code MapDbOptionsBuilder}.
     *
     * @note This constructor is protected for API consistency purpose, to create a new builder use {@link
     * #newBuilder()}
     */
    protected MapDbOptionsBuilder() {
    }

    /**
     * Creates a new {@code MapDbOptionsBuilder}.
     *
     * @return a new builder
     */
    @Nonnull
    public static MapDbOptionsBuilder newBuilder() {
        return new MapDbOptionsBuilder();
    }

    /**
     * Adds the {@code direct-write} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see MapDbStoreOptions#DIRECT_WRITE
     * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
     */
    @Nonnull
    @Deprecated
    public MapDbOptionsBuilder directWrite() {
        return storeOption(MapDbStoreOptions.DIRECT_WRITE);
    }

    /**
     * Adds the {@code direct-write-with-lists} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see MapDbStoreOptions#DIRECT_WRITE_LISTS
     */
    @Nonnull
    @Deprecated
    public MapDbOptionsBuilder directWriteLists() {
        return storeOption(MapDbStoreOptions.DIRECT_WRITE_LISTS);
    }

    /**
     * Adds the {@code direct-write-with-indices} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see MapDbStoreOptions#DIRECT_WRITE_ARRAYS
     */
    @Nonnull
    @Deprecated
    public MapDbOptionsBuilder directWriteArrays() {
        return storeOption(MapDbStoreOptions.DIRECT_WRITE_ARRAYS);
    }

    /**
     * Adds the {@code direct-write-cache-many} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see MapDbStoreOptions#CACHE_MANY
     */
    @Nonnull
    @Deprecated
    public MapDbOptionsBuilder directWriteCacheMany() {
        return storeOption(MapDbStoreOptions.CACHE_MANY);
    }
}
