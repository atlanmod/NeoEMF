package ${package}.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import ${package}.${databaseName}BackendFactory;

import org.osgi.service.component.annotations.Component;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriFactory} that creates ${databaseName} specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see ${databaseName}BackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@Component(service = UriFactory.class)
@FactoryBinding(factory = ${databaseName}BackendFactory.class)
@ParametersAreNonnullByDefault
public class ${databaseName}UriFactory extends AbstractUriFactory {

    /**
     * Constructs a new {@code ${databaseName}UriFactory}.
     */
    public ${databaseName}UriFactory() {
        super(true, false);
    }
}
