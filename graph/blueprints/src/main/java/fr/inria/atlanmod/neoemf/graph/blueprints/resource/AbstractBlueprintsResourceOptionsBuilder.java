package fr.inria.atlanmod.neoemf.graph.blueprints.resource;

import fr.inria.atlanmod.neoemf.resource.AbstractPersistentResourceOptionsBuilder;

public abstract class AbstractBlueprintsResourceOptionsBuilder<B extends AbstractBlueprintsResourceOptionsBuilder<B>> extends AbstractPersistentResourceOptionsBuilder<B> {

    protected AbstractBlueprintsResourceOptionsBuilder() {
    }

    protected B graph(String graph) {
        return option(BlueprintsResourceOptions.GRAPH_TYPE, graph);
    }

    public B autocommit() {
        return storeOption(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
    }

    public B autocommit(int chunk) {
        storeOption(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
        return option(BlueprintsResourceOptions.AUTOCOMMIT_CHUNK, chunk);
    }

    public B directWrite() {
        return storeOption(BlueprintsResourceOptions.EStoreGraphOption.DIRECT_WRITE);
    }

    public B directWriteCacheMany() {
        return storeOption(BlueprintsResourceOptions.EStoreGraphOption.CACHE_MANY);
    }
}
