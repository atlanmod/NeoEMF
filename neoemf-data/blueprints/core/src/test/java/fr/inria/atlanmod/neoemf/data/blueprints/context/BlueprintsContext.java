package fr.inria.atlanmod.neoemf.data.blueprints.context;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

public class BlueprintsContext implements Context {

    public static final String NAME = "Tinker";

    protected BlueprintsContext() {
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
        return BlueprintsURI.SCHEME;
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsResourceBuilder(ePackage).tinkerGraph().persistent().file(file).build();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsResourceBuilder(ePackage).tinkerGraph().file(file).build();
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        return BlueprintsPersistenceBackendFactory.getInstance();
    }

    @Override
    public URI createURI(URI uri) {
        return BlueprintsURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return BlueprintsURI.createFileURI(file);
    }

    @Override
    public Class<?> directWriteClass() {
        return DirectWriteBlueprintsStore.class;
    }

    private static class Holder {

        private static final Context INSTANCE = new BlueprintsContext();
    }
}
