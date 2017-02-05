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

package fr.inria.atlanmod.neoemf.data.blueprints.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;

/**
 * An abstract {@link PersistenceOptionsBuilder} that provides utility methods to create generic Blueprints options.
 * <p>
 * Created options can be {@link BlueprintsResourceOptions} if they define resource-level features, or {@link
 * BlueprintsStoreOptions} if they define database access feature.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 *
 * @param <B> the "self"-type of this {@link PersistenceOptionsBuilder}
 * @param <O> the type of {@link PersistenceOptions} built by this builder
 *
 * @see BlueprintsResourceOptions
 * @see BlueprintsStoreOptions
 */
public abstract class AbstractBlueprintsOptionsBuilder<B extends AbstractBlueprintsOptionsBuilder<B, O>, O extends AbstractBlueprintsOptions> extends AbstractPersistenceOptionsBuilder<B, O> {

    /**
     * Constructs a new {@code AbstractBlueprintsOptionsBuilder}.
     */
    protected AbstractBlueprintsOptionsBuilder() {
    }

    /**
     * Adds the given {@code graphType} in the created options.
     *
     * @param graphType the type of the Blueprints graph
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsResourceOptions#GRAPH_TYPE
     */
    protected B graph(String graphType) {
        return option(BlueprintsResourceOptions.GRAPH_TYPE, graphType);
    }

    /**
     * Adds the {@code direct-write} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsStoreOptions#DIRECT_WRITE
     * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
     */
    public B directWrite() {
        return storeOption(BlueprintsStoreOptions.DIRECT_WRITE);
    }

    /**
     * Adds the {@code direct-write-cache-many} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsStoreOptions#CACHE_MANY
     * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteCacheManyStore
     */
    public B directWriteCacheMany() {
        return storeOption(BlueprintsStoreOptions.CACHE_MANY);
    }
}
