package fr.inria.atlanmod.neoemf.data.mongodb.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The default {@link AbstractMongoDbContext}.
 */
@ParametersAreNonnullByDefault
public class MongoDbDefaultContext extends AbstractMongoDbContext {

    @Nonnull
    @Override
    public ImmutableConfig config() {
        return new MongoDbConfig();
    }
}
