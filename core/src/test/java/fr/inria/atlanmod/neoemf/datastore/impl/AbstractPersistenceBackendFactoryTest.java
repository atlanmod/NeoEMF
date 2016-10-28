package fr.inria.atlanmod.neoemf.datastore.impl;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AbstractDirectWriteResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.lang.reflect.Field;

public abstract class AbstractPersistenceBackendFactoryTest extends AllTest {

    /**
     * Utility method to retrieve the PersistentEStore associated to a store.
     */
    protected PersistenceBackend getInnerBackend(PersistentEStore store) {
        // context is the real EStore, which can de decorated.
        PersistentEStore context = store.getEStore();
        PersistenceBackend result = null;

        try {
            Field field = AbstractDirectWriteResourceEStore.class.getDeclaredField("persistenceBackend");
            field.setAccessible(true);
            result = (PersistenceBackend) field.get(context);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            NeoLogger.error(e);
        }

        return result;
    }
}
