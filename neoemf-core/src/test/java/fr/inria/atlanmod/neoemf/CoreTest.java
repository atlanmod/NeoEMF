package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.Contextual;
import fr.inria.atlanmod.neoemf.context.CoreContext;

public interface CoreTest extends Contextual {

    @Override
    default Context context() {
        return CoreContext.get();
    }
}
