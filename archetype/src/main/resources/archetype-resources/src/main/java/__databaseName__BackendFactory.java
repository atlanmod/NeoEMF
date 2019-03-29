package ${package};

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import ${package}.config.${databaseName}Config;

import org.osgi.service.component.annotations.Component;

import java.net.URL;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link ${databaseName}Backend} instances.
 */
@Component(service = BackendFactory.class)
@ParametersAreNonnullByDefault
public class ${databaseName}BackendFactory extends AbstractBackendFactory<${databaseName}Config> {

    /**
     * Constructs a new {@code ${databaseName}BackendFactory}.
     */
    public ${databaseName}BackendFactory() {
        super("${databaseName.toLowerCase()}");
    }

    @Nonnull
    @Override
    protected Backend createLocalBackend(Path directory, ${databaseName}Config config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }

    @Nonnull
    @Override
    protected Backend createRemoteBackend(URL url, ${databaseName}Config config) throws Exception {
        final boolean isReadOnly = config.isReadOnly();

        // TODO Start/Create the database

        return createMapper(config.getMapping());
    }
}
