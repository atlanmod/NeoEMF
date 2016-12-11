package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.junit.After;
import org.junit.Before;

import java.io.File;

public abstract class AbstractUnitTest extends AbstractTest {

    private File file;

    protected File file() {
        return file;
    }

    @Before
    public void setUp() throws InvalidDataStoreException, InvalidOptionsException {
        PersistenceBackendFactoryRegistry.register(uriScheme(), persistenceBackendFactory());
        file = tempFile(name());
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    protected abstract String name();

    protected abstract String uriScheme();

    protected abstract PersistenceBackendFactory persistenceBackendFactory() throws InvalidDataStoreException, InvalidOptionsException;
}
