package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context.BlueprintsNeo4jContext;

public interface BlueprintsNeo4jTest extends BlueprintsTest {

    @Override
    default Context context() {
        return BlueprintsNeo4jContext.get();
    }
}
