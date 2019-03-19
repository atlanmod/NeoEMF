package ${package};

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapperTest;

import ${package}.context.${databaseName}Context;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link Default${databaseName}Backend}.
 */
@ParametersAreNonnullByDefault
class Default${databaseName}BackendTest extends AbstractDataMapperTest {

    @Nonnull
    @Override
    protected Context context() {
        return ${databaseName}Context.getDefault();
    }
}
