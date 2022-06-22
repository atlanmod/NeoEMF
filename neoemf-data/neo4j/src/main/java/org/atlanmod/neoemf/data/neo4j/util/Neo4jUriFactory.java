package org.atlanmod.neoemf.data.neo4j.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.atlanmod.neoemf.data.neo4j.Neo4jBackendFactory;

import org.osgi.service.component.annotations.Component;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriFactory} that creates a Neo4j specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see Neo4jBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@Component(service = UriFactory.class)
@FactoryBinding(factory = Neo4jBackendFactory.class)
@ParametersAreNonnullByDefault
public class Neo4jUriFactory extends AbstractUriFactory {

    /**
     * Constructs a new {@code Neo4jUriFactory}.
     */
    public Neo4jUriFactory() {
        super(true, false);
    }
}
