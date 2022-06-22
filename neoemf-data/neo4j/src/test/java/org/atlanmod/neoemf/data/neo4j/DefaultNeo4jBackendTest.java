package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapperTest;

import org.atlanmod.neoemf.data.neo4j.context.PaprikaContext;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link DefaultNeo4jBackend}.
 */
@ParametersAreNonnullByDefault
class DefaultNeo4jBackendTest extends AbstractDataMapperTest {

    @Nonnull
    @Override
    protected Context context() {
        return PaprikaContext.getDefault();
    }
}
