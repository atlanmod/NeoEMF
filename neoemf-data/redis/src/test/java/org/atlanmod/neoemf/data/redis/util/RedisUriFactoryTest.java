package org.atlanmod.neoemf.data.redis.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactoryTest;

import org.atlanmod.neoemf.data.redis.context.RedisContext;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link RedisUriFactory}.
 */
@ParametersAreNonnullByDefault
class RedisUriFactoryTest extends AbstractUriFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return RedisContext.getDefault();
    }
}
