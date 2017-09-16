package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StoreCreator} that uses standard EMF for store creation.
 */
@ParametersAreNonnullByDefault
public final class StandardStoreCreator implements StoreCreator {

    @Nonnull
    @Override
    public File getOrCreateStore(File file, PersistenceOptions options, Adapter.Internal adapter, Path dir) throws IOException {
        File targetFile = dir.resolve(StoreCreator.getTargetFileName(file, adapter)).toFile();

        if (targetFile.exists()) {
            return targetFile;
        }

        Log.info("Creating store with standard EMF");

        ResourceSet resourceSet = Stores.loadResourceSet();

        Log.info("Loading resource from: {0}", file);

        URI sourceUri = URI.createFileURI(file.getAbsolutePath());
        Resource sourceResource = resourceSet.createResource(sourceUri);

        adapter.initAndGetEPackage();

        Map<String, Object> loadOpts = new HashMap<>();
        if (Objects.equals("zxmi", sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, true);
        }
        sourceResource.load(loadOpts);

        Log.info("Copying resource content...");

        Collection<EObject> targetRoot = EcoreUtil.copyAll(sourceResource.getContents());
        sourceResource.unload();

        Log.info("Migrating resource content...");

        Resource targetResource = adapter.createResource(targetFile, resourceSet);
        adapter.save(targetResource, options);

        targetResource.getContents().addAll(targetRoot);

        Log.info("Saving resource to: {0}", targetResource.getURI());
        adapter.save(targetResource, options);

        adapter.unload(targetResource);

        return targetFile;
    }
}
