package ${package}.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.AbstractLocalContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import ${package}.${databaseName}BackendFactory;
import ${package}.config.${databaseName}Config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the ${databaseName} implementation.
 */
@ParametersAreNonnullByDefault
public abstract class ${databaseName}Context extends AbstractLocalContext {

    /**
     * Creates a new {@code BerkeleyDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new ${databaseName}Context() {
            @Nonnull
            @Override
            public ImmutableConfig config() {
                return new ${databaseName}Config();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "${databaseName}";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new ${databaseName}BackendFactory();
    }
}
