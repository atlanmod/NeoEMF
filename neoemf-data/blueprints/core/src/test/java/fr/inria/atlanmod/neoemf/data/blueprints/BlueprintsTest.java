package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.Contextual;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;

public interface BlueprintsTest extends Contextual {

    @Override
    default Context context() {
        return BlueprintsContext.get();
    }
}
