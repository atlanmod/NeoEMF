package fr.inria.atlanmod.neoemf.datastore.store;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

public abstract class AbstractPersistentStore implements PersistentStore {

    public AbstractPersistentStore() {
        NeoLogger.info("{0} created", getClass().getSimpleName());
    }
}
