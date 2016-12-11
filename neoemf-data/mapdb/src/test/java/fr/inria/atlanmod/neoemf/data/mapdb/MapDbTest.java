package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.Contextual;
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbContext;

public interface MapDbTest extends Contextual {

    @Override
    default Context context() {
        return MapDbContext.get();
    }
}
