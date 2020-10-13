package org.atlanmod.neoemf.data.redis;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import org.atlanmod.neoemf.data.redis.config.RedisConfig;

import org.osgi.service.component.annotations.Component;

import java.net.URL;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link RedisBackend} instances.
 */
@Component(service = BackendFactory.class)
@ParametersAreNonnullByDefault
public class RedisBackendFactory extends AbstractBackendFactory<RedisConfig> {

    /**
     * Constructs a new {@code RedisBackendFactory}.
     */
    public RedisBackendFactory() {
        super("redis");
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, RedisConfig config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }

    @Nonnull
    @Override
    protected Backend createRemoteBackend(URL url, RedisConfig config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }
}
