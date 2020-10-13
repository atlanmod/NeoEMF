package org.atlanmod.neoemf.data.redis;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;

import org.atlanmod.neoemf.data.redis.config.RedisConfig;
import org.atlanmod.neoemf.data.redis.context.RedisContext;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link RedisBackendFactory}.
 */
@ParametersAreNonnullByDefault
class RedisBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return RedisContext.getDefault();
    }

    @Nonnull
    @Override
    protected Stream<Arguments> allMappings() {
        return Stream.of(
                Arguments.of(new RedisConfig(), DefaultRedisBackend.class)

                // TODO Fill with other mappings
        );
    }
}
