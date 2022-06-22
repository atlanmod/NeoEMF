package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import org.atlanmod.neoemf.data.neo4j.config.Neo4jConfig;

import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.osgi.service.component.annotations.Component;

import java.net.URL;
import java.nio.file.Files;
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
     * Constructs a new {@code PaprikaBackendFactory}.
     */
    public Neo4jBackendFactory() {
        super("paprika");
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, Neo4jConfig config) throws Exception {
        if (!directory.toFile().exists()) {
            Files.createDirectories(directory);
        }

        final DatabaseManagementService service = new DatabaseManagementServiceBuilder(directory).build();

        return createMapper(config.getMapping(), service);
    }
}
