package ${package}.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;

import ${package}.${databaseName}BackendFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates ${databaseName} specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@Component(service = Config.class, scope = ServiceScope.PROTOTYPE)
@FactoryBinding(factory = ${databaseName}BackendFactory.class)
@ParametersAreNonnullByDefault
public class ${databaseName}Config extends BaseConfig<${databaseName}Config> {

    /**
     * Constructs a new {@code ${databaseName}Config}.
     */
    public ${databaseName}Config() {
        withDefault();

        // TODO Declare all default values
    }

    /**
     * Defines the mapping to use for the created {@link ${package}.${databaseName}Backend}.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    protected ${databaseName}Config withDefault() {
        return setMappingWithCheck("${package}.Default${databaseName}Backend", false);
    }

    // TODO Add mapping declarations

    // TODO Add methods specific to your database
}
