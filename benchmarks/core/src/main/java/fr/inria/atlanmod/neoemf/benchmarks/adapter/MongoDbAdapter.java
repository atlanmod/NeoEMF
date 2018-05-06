package fr.inria.atlanmod.neoemf.benchmarks.adapter;


import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import org.eclipse.emf.common.util.URI;

import javax.annotation.Nonnull;
import java.io.File;
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

    @Override
    public DataMapper createMapper(File file, ImmutableConfig config) {
        ImmutableConfig mergedConfig = BaseConfig.newConfig().merge(config).merge(getOptions());

        //TODO Get configuration from file
        Backend backend = getFactory().createBackend(URI.createURI("neo-mongodb://localhost:27017/testbenchmark"), mergedConfig);
        return StoreFactory.getInstance().createStore(backend, mergedConfig);
    }
}
