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

import javax.annotation.Nonnull;

/**
 *
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
public class MapDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<MapDbOptionsBuilder, MapDbOptions> {

    /**
     * Instantiates a new {@code MapDbOptionsBuilder}.
     */
    protected MapDbOptionsBuilder() {
    }

    /**
     * Constructs a new {@code MapDbOptionsBuilder} instance.
     * @return a new builder
     */
    @Nonnull
    public static MapDbOptionsBuilder newBuilder() {
        return new MapDbOptionsBuilder();
    }

    /**
     *
     * @return this {@code CommonOptionsBuilder} (for chaining)
     */
    @Nonnull
    public MapDbOptionsBuilder autocommit() {
        return storeOption(MapDbStoreOptions.AUTOCOMMIT);
    }

    /**
     *
     * @return this {@code CommonOptionsBuilder} (for chaining)
     */
    @Nonnull
    public MapDbOptionsBuilder directWrite() {
        return storeOption(MapDbStoreOptions.DIRECT_WRITE);
    }

    /**
     *
     * @return this {@code CommonOptionsBuilder} (for chaining)
     */
    @Nonnull
    public MapDbOptionsBuilder directWriteLists() {
        return storeOption(MapDbStoreOptions.DIRECT_WRITE_LISTS);
    }

    /**
     *
     * @return this {@code CommonOptionsBuilder} (for chaining)
     */
    @Nonnull
    public MapDbOptionsBuilder directWriteIndices() {
        return storeOption(MapDbStoreOptions.DIRECT_WRITE_INDICES);
    }

    /**
     *
     * @return this {@code CommonOptionsBuilder} (for chaining)
     */
    @Nonnull
    public MapDbOptionsBuilder directWriteCacheMany() {
        return storeOption(MapDbStoreOptions.CACHE_MANY);
    }
}
