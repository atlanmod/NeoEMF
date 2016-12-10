package fr.inria.atlanmod.neoemf.tests.util;

import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public interface BackendHelper {

    String name();

    PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException;

    PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException;

    URI createFileUri(File file);

    String uriScheme();

    Class<?> directWriteClass();
}
