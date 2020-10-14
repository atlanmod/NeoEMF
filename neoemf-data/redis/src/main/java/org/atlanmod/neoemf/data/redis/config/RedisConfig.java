package org.atlanmod.neoemf.data.redis.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;

import org.atlanmod.neoemf.data.redis.RedisBackendFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates Redis specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@Component(service = Config.class, scope = ServiceScope.PROTOTYPE)
@FactoryBinding(factory = RedisBackendFactory.class)
@ParametersAreNonnullByDefault
public class RedisConfig extends BaseConfig<RedisConfig> {

    /**
     * Constructs a new {@code RedisConfig}.
     */
    public RedisConfig() {
        withDefault();

        // TODO Declare all default values
    }

    /**
     * Defines the mapping to use for the created {@link org.atlanmod.neoemf.data.redis.RedisBackend}.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    protected RedisConfig withDefault() {
        return setMappingWithCheck("org.atlanmod.neoemf.data.redis.DefaultRedisBackend", false);
    }

    // TODO Add mapping declarations

    // TODO Add methods specific to your database
}
