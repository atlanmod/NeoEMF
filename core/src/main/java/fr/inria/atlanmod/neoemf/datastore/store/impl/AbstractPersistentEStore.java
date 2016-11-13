package fr.inria.atlanmod.neoemf.datastore.store.impl;

import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

public abstract class AbstractPersistentEStore implements PersistentEStore {

    public AbstractPersistentEStore() {
        NeoLogger.info("{0} created", getClass().getSimpleName());
    }
}
