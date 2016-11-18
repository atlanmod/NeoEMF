package fr.inria.atlanmod.neoemf.hbase.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public class HBaseOptionsBuilder extends AbstractPersistenceOptionsBuilder<HBaseOptionsBuilder> {

    protected HBaseOptionsBuilder() {
    }

    public static HBaseOptionsBuilder newBuilder() {
        return new HBaseOptionsBuilder();
    }

    public HBaseOptionsBuilder readOnly() {
        return option(HBaseResourceOptions.READ_ONLY, true);
    }
}
