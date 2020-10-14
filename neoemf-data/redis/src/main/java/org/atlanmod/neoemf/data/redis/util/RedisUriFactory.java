package org.atlanmod.neoemf.data.redis.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.atlanmod.neoemf.data.redis.RedisBackendFactory;

import org.osgi.service.component.annotations.Component;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriFactory} that creates Redis specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see RedisBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@Component(service = UriFactory.class)
@FactoryBinding(factory = RedisBackendFactory.class)
@ParametersAreNonnullByDefault
public class RedisUriFactory extends AbstractUriFactory {

    /**
     * Constructs a new {@code RedisUriFactory}.
     */
    public RedisUriFactory() {
        super(true, false);
    }
}
