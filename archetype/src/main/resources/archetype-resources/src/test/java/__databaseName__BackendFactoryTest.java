package ${package};

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;

import ${package}.config.${databaseName}Config;
import ${package}.context.${databaseName}Context;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link ${databaseName}BackendFactory}.
 */
@ParametersAreNonnullByDefault
class ${databaseName}BackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return ${databaseName}Context.getDefault();
    }

    @Nonnull
    @Override
    protected Stream<Arguments> allMappings() {
        return Stream.of(
                Arguments.of(new ${databaseName}Config(), Default${databaseName}Backend.class)

                // TODO Fill with other mappings
        );
    }
}
