package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;

import org.atlanmod.neoemf.data.neo4j.config.Neo4jConfig;
import org.atlanmod.neoemf.data.neo4j.context.Neo4jContext;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link Neo4jBackendFactory}.
 */
@ParametersAreNonnullByDefault
class Neo4jBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return Neo4jContext.getDefault();
    }

    @Nonnull
    @Override
    protected Stream<Arguments> allMappings() {
        return Stream.of(
                Arguments.of(new Neo4jConfig(), DefaultNeo4jBackend.class)

                // TODO Fill with other mappings
        );
    }
}
