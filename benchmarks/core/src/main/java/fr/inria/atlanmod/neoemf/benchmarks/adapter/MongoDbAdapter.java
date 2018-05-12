package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
 *
 * @see "https://hub.docker.com/_/mongo/"
 */
@AdapterName("mongodb")
@ParametersAreNonnullByDefault
public class MongoDbAdapter extends AbstractPersistentRemoteAdapter {

    @Nonnull
    @Override
    protected ImmutableConfig createConfig() {
        return new MongoDbConfig();
    }

    @Nonnull
    @Override
    protected String getHost() {
        return "localhost";
    }

    @Override
    protected int getPort() {
        return 27017;
    }
}
