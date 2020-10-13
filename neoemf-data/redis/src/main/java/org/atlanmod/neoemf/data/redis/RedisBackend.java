package org.atlanmod.neoemf.data.redis;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.Backend} that is responsible of low-level access to a Redis database.
 * <p>
 * It wraps an existing Redis database and provides facilities to create and retrieve elements.
 * <p>
 * <b>Note:</b> Instances of {@code RedisBackend} are created by {@link RedisBackendFactory} that
 * provides an usable database that can be manipulated by this wrapper.
 *
 * @see RedisBackendFactory
 */
@ParametersAreNonnullByDefault
public interface RedisBackend extends Backend {

    @Override
    default boolean isPersistent() {
        // TODO Implement this method
        return true;
    }

    @Override
    default boolean isDistributed() {
        // TODO Implement this method
        return false;
    }
}
