package org.atlanmod.neoemf.data.neo4j.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractLocalContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import org.atlanmod.neoemf.data.neo4j.Neo4jBackendFactory;
import org.atlanmod.neoemf.data.neo4j.config.Neo4jConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the Paprika implementation.
 */
@ParametersAreNonnullByDefault
public abstract class PaprikaContext extends AbstractLocalContext {

    /**
     * Creates a new {@code BerkeleyDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new PaprikaContext() {
            @Nonnull
            @Override
            public ImmutableConfig config() {
                return new Neo4jConfig();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "Paprika";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new Neo4jBackendFactory();
    }
}
