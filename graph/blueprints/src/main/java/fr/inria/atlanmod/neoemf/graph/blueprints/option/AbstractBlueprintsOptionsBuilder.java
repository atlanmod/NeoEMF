package fr.inria.atlanmod.neoemf.graph.blueprints.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public abstract class AbstractBlueprintsOptionsBuilder<B extends AbstractBlueprintsOptionsBuilder<B>> extends AbstractPersistenceOptionsBuilder<B> {

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
