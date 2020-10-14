package org.atlanmod.neoemf.data.redis.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractLocalContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import org.atlanmod.neoemf.data.redis.RedisBackendFactory;
import org.atlanmod.neoemf.data.redis.config.RedisConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the Redis implementation.
 */
@ParametersAreNonnullByDefault
public abstract class RedisContext extends AbstractLocalContext {

    /**
     * Creates a new {@code BerkeleyDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new RedisContext() {
            @Nonnull
            @Override
            public ImmutableConfig config() {
                return new RedisConfig();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "Redis";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new RedisBackendFactory();
    }
}
