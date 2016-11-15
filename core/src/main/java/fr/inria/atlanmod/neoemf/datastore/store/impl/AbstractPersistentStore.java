package fr.inria.atlanmod.neoemf.datastore.store.impl;

import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

public abstract class AbstractPersistentStore implements PersistentStore {

    public AbstractPersistentStore() {
        NeoLogger.info("{0} created", getClass().getSimpleName());
    }
}
