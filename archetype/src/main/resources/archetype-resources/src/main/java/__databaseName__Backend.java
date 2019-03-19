package ${package};

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.Backend} that is responsible of low-level access to a ${databaseName} database.
 * <p>
 * It wraps an existing ${databaseName} database and provides facilities to create and retrieve elements.
 * <p>
 * <b>Note:</b> Instances of {@code ${databaseName}Backend} are created by {@link ${databaseName}BackendFactory} that
 * provides an usable database that can be manipulated by this wrapper.
 *
 * @see ${databaseName}BackendFactory
 */
@ParametersAreNonnullByDefault
public interface ${databaseName}Backend extends Backend {

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
