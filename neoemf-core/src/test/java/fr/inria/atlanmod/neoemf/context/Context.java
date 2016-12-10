package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

public interface Context {

    /**
     * Returns the name of this {@code Context}.
     */
    String name();

    /**
     * Creates a new {@link PersistentResource} with a persistent backend.
     */
    PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException;

    /**
     * Creates a new {@link PersistentResource} with a transient backend.
     */
    PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException;

    /**
     * Creates a new {@link URI}.
     *
     * @see fr.inria.atlanmod.neoemf.util.PersistenceURI#createFileURI(File, String)
     */
    URI createFileUri(File file);

    /**
     * Returns the URI scheme of this {@code Context}.
     */
    String uriScheme();

    Class<?> directWriteClass();
}
