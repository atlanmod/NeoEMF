package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
 */
@AdapterName("mongodb")
@ParametersAreNonnullByDefault
public class MongoDbAdapter extends AbstractPersistentAdapter {

    @Nonnull
    @Override
    protected ImmutableConfig createConfig() {
        return new MongoDbConfig();
    }

    @Nonnull
    @Override
    public DataMapper createMapper(File file, ImmutableConfig config) {
        ImmutableConfig mergedConfig = new BaseConfig<>().merge(config).merge(createConfig());

        //TODO Get configuration from file
        Backend backend = getFactory().createBackend(URI.createURI("neo-mongodb://localhost:27017/testbenchmark"), mergedConfig);
        return StoreFactory.getInstance().createStore(backend, mergedConfig);
    }
}
