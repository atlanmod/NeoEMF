package fr.inria.atlanmod.neoemf.data.hbase.context;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

public class HBaseContext implements Context {

    public static final String NAME = "HBase";

    protected HBaseContext() {
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
        return HBaseURI.SCHEME;
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
        return HBasePersistenceBackendFactory.getInstance();
    }

    @Override
    public URI createURI(URI uri) {
        return HBaseURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return HBaseURI.createFileURI(file);
    }

    @Override
    public Class<?> directWriteClass() {
        return DirectWriteHBaseStore.class;
    }

    private static class Holder {

        private static final Context INSTANCE = new HBaseContext();
    }
}
