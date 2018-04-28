package fr.inria.atlanmod.neoemf.benchmarks.adapter;


import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import javax.annotation.Nonnull;
import java.util.Map;


public class MongoDbAdapter extends AbstractNeoAdapter {

    /**
     * Constructs a new {@code AbstractNeoAdapter}.
     *
     * @param storeExtension the extension of the resource, used for benchmarks
     */
    protected MongoDbAdapter(String storeExtension) {
        super("mongoDb." + storeExtension);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return MongoDbBackendFactory.getInstance();
    }

    public MongoDbAdapter()
    {
        super("mongodb");
    }

    @Nonnull
    @Override
    public Map<String, ?> getOptions() {
        return MongoDbConfig.newConfig().toMap();
    }

}
