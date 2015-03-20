package fr.inria.atlanmod.neoemf.core;

/**
 * Created by sunye on 19/03/15.
 */
public abstract class AbstractPersistentObject implements PersistentObject {

    private Id id;

    @Override
    public Id id() {
        return id;
    }
}
