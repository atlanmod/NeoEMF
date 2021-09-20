package org.atlanmod.neoemf.data.neo4j.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;

import org.atlanmod.neoemf.data.neo4j.Neo4jBackendFactory;

import org.atlanmod.neoemf.data.neo4j.Neo4jBackend;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates Neo4j specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@Component(service = Config.class, scope = ServiceScope.PROTOTYPE)
@FactoryBinding(factory = Neo4jBackendFactory.class)
@ParametersAreNonnullByDefault
public class Neo4jConfig extends BaseConfig<Neo4jConfig> {

    /**
     * Constructs a new {@code Neo4jConfig}.
     */
    public Neo4jConfig() {
        withDefault();

        // TODO Declare all default values
    }

    /**
     * Defines the mapping to use for the created {@link Neo4jBackend}.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    protected Neo4jConfig withDefault() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.neo4j.DefaultNeo4jBackend", false);
    }

    // TODO Add mapping declarations

    // TODO Add methods specific to your database
}
