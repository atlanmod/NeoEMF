package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.Contextual;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;

public interface HBaseTest extends Contextual {

    @Override
    default Context context() {
        return HBaseContext.get();
    }
}
