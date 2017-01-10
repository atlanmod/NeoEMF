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

public abstract class AbstractBlueprintsOptionsBuilder<B extends AbstractBlueprintsOptionsBuilder<B, O>, O extends AbstractBlueprintsOptions> extends AbstractPersistenceOptionsBuilder<B, O> {

    protected AbstractBlueprintsOptionsBuilder() {
    }

    protected B graph(String graph) {
        return option(BlueprintsResourceOptions.GRAPH_TYPE, graph);
    }

    public B autocommit() {
        return storeOption(BlueprintsStoreOptions.AUTOCOMMIT);
    }

    public B autocommit(int chunk) {
        storeOption(BlueprintsStoreOptions.AUTOCOMMIT);
        return option(BlueprintsResourceOptions.AUTOCOMMIT_CHUNK, chunk);
    }

    public B directWrite() {
        return storeOption(BlueprintsStoreOptions.DIRECT_WRITE);
    }

    public B directWriteCacheMany() {
        return storeOption(BlueprintsStoreOptions.CACHE_MANY);
    }
}
