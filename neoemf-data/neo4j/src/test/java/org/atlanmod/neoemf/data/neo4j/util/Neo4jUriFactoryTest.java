package org.atlanmod.neoemf.data.neo4j.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactoryTest;

import org.atlanmod.neoemf.data.neo4j.context.PaprikaContext;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link Neo4jUriFactory}.
 */
@ParametersAreNonnullByDefault
class Neo4jUriFactoryTest extends AbstractUriFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return PaprikaContext.getDefault();
    }
}
