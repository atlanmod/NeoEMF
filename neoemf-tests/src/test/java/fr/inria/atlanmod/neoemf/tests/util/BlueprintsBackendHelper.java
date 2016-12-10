package fr.inria.atlanmod.neoemf.tests.util;

import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class BlueprintsBackendHelper implements BackendHelper {

    public static final String NAME = "Tinker";

    @Override
    public String name() {
        return NAME;
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
    public URI createFileUri(File file) {
        return BlueprintsURI.createFileURI(file);
    }

    @Override
    public String uriScheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    public Class<?> directWriteClass() {
        return DirectWriteBlueprintsStore.class;
    }
}
