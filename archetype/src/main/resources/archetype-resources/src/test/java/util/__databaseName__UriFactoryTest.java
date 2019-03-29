package ${package}.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactoryTest;

import ${package}.context.${databaseName}Context;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link ${databaseName}UriFactory}.
 */
@ParametersAreNonnullByDefault
class ${databaseName}UriFactoryTest extends AbstractUriFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return ${databaseName}Context.getDefault();
    }
}
