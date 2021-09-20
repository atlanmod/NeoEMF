package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.Backend} that is responsible of low-level access to a Neo4j database.
 * <p>
 * It wraps an existing Neo4j database and provides facilities to create and retrieve elements.
 * <p>
 * <b>Note:</b> Instances of {@code Neo4jBackend} are created by {@link Neo4jBackendFactory} that
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
