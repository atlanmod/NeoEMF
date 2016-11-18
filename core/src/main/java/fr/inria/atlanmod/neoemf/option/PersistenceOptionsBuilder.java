package fr.inria.atlanmod.neoemf.option;

public class PersistenceOptionsBuilder extends AbstractPersistenceOptionsBuilder<PersistenceOptionsBuilder> {

    protected PersistenceOptionsBuilder() {
    }

    public static PersistenceOptionsBuilder newBuilder() {
        return new PersistenceOptionsBuilder();
    }
}
