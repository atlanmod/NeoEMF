package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.context.Contextual;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.junit.After;
import org.junit.Before;

import java.io.File;

public abstract class AbstractUnitTest extends AbstractTest implements Contextual {

    private File file;

    protected File file() {
        return file;
    }

    @Before
    public final void registerFactories() throws InvalidDataStoreException, InvalidOptionsException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());
        file = newFile(context().name());
    }

    @After
    public final void unregisterFactories() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }
}
