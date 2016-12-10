package fr.inria.atlanmod.neoemf.tests.util;

import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class MapDbBackendHelper implements BackendHelper {

    public static final String NAME = "MapDb";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new MapDbResourceBuilder(ePackage).persistent().file(file).build();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new MapDbResourceBuilder(ePackage).file(file).build();
    }

    @Override
    public URI createFileUri(File file) {
        return MapDbURI.createFileURI(file);
    }

    @Override
    public String uriScheme() {
        return MapDbURI.SCHEME;
    }

    @Override
    public Class<?> directWriteClass() {
        return DirectWriteMapDbStore.class;
    }
}
