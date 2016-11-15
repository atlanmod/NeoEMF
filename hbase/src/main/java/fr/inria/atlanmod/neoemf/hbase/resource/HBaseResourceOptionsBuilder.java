package fr.inria.atlanmod.neoemf.hbase.resource;

import fr.inria.atlanmod.neoemf.resource.AbstractPersistentResourceOptionsBuilder;

public class HBaseResourceOptionsBuilder extends AbstractPersistentResourceOptionsBuilder<HBaseResourceOptionsBuilder> {

    protected HBaseResourceOptionsBuilder() {
    }

    public HBaseResourceOptionsBuilder readOnly() {
        return option(HBaseResourceOptions.READ_ONLY, true);
    }
}
