package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Context}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractContext implements Context {

    @Nonnull
    @Override
    public String uriScheme() {
        return Bindings.schemeOf(factory().getClass());
    }

    @Nonnull
    @Override
    public URI createUri(URI uri) {
        return UriBuilder.forScheme(uriScheme()).fromUri(uri);
    }

    @Nonnull
    @Override
    public URI createUri(File file) {
        return UriBuilder.forScheme(uriScheme()).fromFile(file);
    }

    @Nonnull
    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new ContextualResourceBuilder(this, ePackage).persistent().file(file).createResource();
    }

    @Nonnull
    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        if (!factory().supportsTransient()) {
            return createPersistentResource(ePackage, file);
        }
        else {
            return new ContextualResourceBuilder(this, ePackage).file(file).createResource();
        }
    }

    @Nonnull
    @Override
    public PersistentResource loadResource(File file) throws IOException {
        return loadResource(null, file);
    }

    @Nonnull
    @Override
    public PersistentResource loadResource(@Nullable EPackage ePackage, File file) throws IOException {
        return new ContextualResourceBuilder(this, ePackage).file(file).loadResource();
    }

    @Nonnull
    @Override
    public DataMapper createMapper(File file) {
        return new ContextualResourceBuilder(this, null).file(file).createPersistentMapper();
    }

    @Nonnull
    @Override
    public String toString() {
        return name();
    }
}
