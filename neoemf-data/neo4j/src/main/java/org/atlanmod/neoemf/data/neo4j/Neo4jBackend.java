package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.Backend} that is responsible of low-level access to a Paprika database.
 * <p>
 * It wraps an existing Paprika database and provides facilities to create and retrieve elements.
 * <p>
 * <b>Note:</b> Instances of {@code PaprikaBackend} are created by {@link Neo4jBackendFactory} that
 * provides an usable database that can be manipulated by this wrapper.
 *
 * @see Neo4jBackendFactory
 */
@ParametersAreNonnullByDefault
public interface Neo4jBackend extends Backend {

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
