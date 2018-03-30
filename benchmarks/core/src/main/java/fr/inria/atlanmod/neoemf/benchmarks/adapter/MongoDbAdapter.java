package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class MongoDbAdapter extends AbstractAdapter {
    /**
     * Constructs a new {@code AbstractAdatper}.
     *
     * @param resourceExtension the extension of the adapted resource, used to create the stores
     * @param storeExtension    the extension of the resource, used for benchmarks
     * @param packageClass      the class of the {@link EPackage} associated to this adapter
     */
    protected MongoDbAdapter(String resourceExtension, String storeExtension, Class<?> packageClass) {
        super(resourceExtension, storeExtension, packageClass);
    }

    @Nonnull
    @Override
    public Resource load(File file, ImmutableConfig config) throws IOException {
        return null;
    }

    @Override
    public void unload(Resource resource) {

    }

    @Nonnull
    @Override
    public Resource createResource(File file, ResourceSet resourceSet) {
        return null;
    }
}
