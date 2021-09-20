package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import org.atlanmod.neoemf.data.neo4j.config.Neo4jConfig;

import org.osgi.service.component.annotations.Component;

import java.net.URL;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link Neo4jBackend} instances.
 */
@Component(service = BackendFactory.class)
@ParametersAreNonnullByDefault
public class Neo4jBackendFactory extends AbstractBackendFactory<Neo4jConfig> {

    /**
     * Constructs a new {@code Neo4jBackendFactory}.
     */
    public Neo4jBackendFactory() {
        super("neo4j");
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, Neo4jConfig config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }

    @Nonnull
    @Override
    protected Backend createRemoteBackend(URL url, Neo4jConfig config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }
}
