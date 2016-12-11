package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;

public class CoreContext implements Context {

    public static final String NAME = "Core";

    protected CoreContext() {
    }

    public static Context get() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String uriScheme() {
        return "mock";
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        return mock(AbstractPersistenceBackendFactory.class);
    }

    @Override
    public URI createURI(URI uri) {
        return PersistenceURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return PersistenceURI.createFileURI(file, uriScheme());
    }

    @Override
    public Class<?> directWriteClass() {
        throw new UnsupportedOperationException();
    }

    private static class Holder {

        private static final Context INSTANCE = new CoreContext();
    }
}
