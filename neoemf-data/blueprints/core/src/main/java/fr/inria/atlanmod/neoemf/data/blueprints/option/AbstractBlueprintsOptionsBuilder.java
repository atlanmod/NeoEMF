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

import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsCacheManyStore;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

/**
 * An abstract option builder that extends {@link AbstractPersistenceOptionsBuilder} by providing 
 * utility methods to create generic Blueprints options.
 * <p>
 * Created options can be {@link BlueprintsResourceOptions} if they define resource-level features, or
 * {@link BlueprintsStoreOptions} if they define datastore access feature.
 * @param <B> the builder subclass returned by each method
 * @param <O> the {@link AbstractBlueprintsOptions} subclass handled by the builder
 * 
 * @see BlueprintsResourceOptions
 * @see BlueprintsStoreOptions
 */
public abstract class AbstractBlueprintsOptionsBuilder<B extends AbstractBlueprintsOptionsBuilder<B, O>, O extends AbstractBlueprintsOptions> extends AbstractPersistenceOptionsBuilder<B, O> {

    /**
     * Creates a new builder
     */
    protected AbstractBlueprintsOptionsBuilder() {
    }

    /**
     * Add the given {@code graphType} in the created options
     * @param graphType the type of the Blueprints graph
     * @return the builder
     * 
     * @see BlueprintsResourceOptions#GRAPH_TYPE
     */
    protected B graph(String graphType) {
        return option(BlueprintsResourceOptions.GRAPH_TYPE, graphType);
    }

    /**
     * Add the {@code autocommit} feature in the created options
     * @return the builder
     * 
     * @see BlueprintsStoreOptions#AUTOCOMMIT
     */
    public B autocommit() {
        return storeOption(BlueprintsStoreOptions.AUTOCOMMIT);
    }

    /**
     * Add the {@code autocommit} feature with the given {@code chunk} size in the created options
     * @param chunk the number of database operations between each commit
     * @return the builder
     * 
     * @see BlueprintsStoreOptions#AUTOCOMMIT
     */
    public B autocommit(int chunk) {
        storeOption(BlueprintsStoreOptions.AUTOCOMMIT);
        return option(BlueprintsResourceOptions.AUTOCOMMIT_CHUNK, chunk);
    }

    /**
     * Add the {@code directWrite} feature in the created options. Created resource will embed a
     * {@link DirectWriteBlueprintsStore} to interact with the database
     * @return the builder
     * 
     * @see BlueprintsStoreOptions#DIRECT_WRITE
     * @see DirectWriteBlueprintsStore
     */
    public B directWrite() {
        return storeOption(BlueprintsStoreOptions.DIRECT_WRITE);
    }

    /**
     * Add the {@code directWriteCacheMany} feature in the created options. Created resource will embed a
     * {@link DirectWriteBlueprintsCacheManyStore} to interact with the database
     * @return the builder
     * 
     * @see BlueprintsStoreOptions#CACHE_MANY
     * @see DirectWriteBlueprintsCacheManyStore
     */
    public B directWriteCacheMany() {
        return storeOption(BlueprintsStoreOptions.CACHE_MANY);
    }
}
