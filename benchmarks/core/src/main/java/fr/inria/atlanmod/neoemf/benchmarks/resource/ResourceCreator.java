/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.Workspace;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;

/**
 * A class that provides static methods for {@link Resource} creation.
 */
@ParametersAreNonnullByDefault
public final class ResourceCreator {

    /**
     * Defines whether the store creation must use the NeoEMF importer if available.
     */
    public static boolean useImporter = true;

    @SuppressWarnings("JavaDoc")
    private ResourceCreator() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Copies the given {@code file} to the temporary directory.
     *
     * @param file the file to copy
     *
     * @return the created file
     *
     * @throws IOException if an I/O error occurs during the copy
     */
    @Nonnull
    public static File copyStore(File file) throws IOException {
        Path outputFile = Workspace.newTempDirectory().resolve(file.getName());

        Log.info("Copying {0} to {1}", file, outputFile);

        Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetPath = outputFile.resolve(file.toPath().relativize(dir));
                if (!Files.exists(targetPath)) {
                    Files.createDirectory(targetPath);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                Files.copy(path, outputFile.resolve(file.toPath().relativize(path)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });

        return outputFile.toFile();
    }

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code adapter}, located in a temporary
     * directory.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     * @see Workspace#newTempDirectory()
     */
    @Nonnull
    public static File createTempStore(File resourceFile, Adapter.Internal adapter) throws Exception {
        Path dir = Workspace.newTempDirectory();

        if (useImporter && adapter.supportsMapper()) {
            return createStoreDirect(resourceFile, adapter, dir);
        }
        else {
            return createStore(resourceFile, adapter, dir);
        }
    }

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code targetAdapter}, located in the workspace.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     * @see Workspace#getStoreDirectory()
     */
    @Nonnull
    public static File createStore(File resourceFile, Adapter.Internal adapter) throws Exception {
        Path dir = Workspace.getStoreDirectory();

        if (useImporter && adapter.supportsMapper()) {
            return createStoreDirect(resourceFile, adapter, dir);
        }
        else {
            return createStore(resourceFile, adapter, dir);
        }
    }

    //region NeoEMF

    /**
     * Creates a new {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} from the given {@code resourceFile},
     * and stores it to the given {@code targetAdapter}, located in {@code dir}.
     * <p>
     * This method uses the direct importer from the `neoemf-io` module.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     * @param dir          the location of the adapter
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     */
    @Nonnull
    private static File createStoreDirect(File resourceFile, Adapter.Internal adapter, Path dir) throws Exception {
        Log.info("Creating store with NeoEMF importer");

        ResourceManager.checkValidResource(resourceFile.getName());
        checkArgument(resourceFile.exists(), "Resource '%s' does not exist", resourceFile);

        String targetFileName = ResourceManager.getNameWithoutExtension(resourceFile.getAbsolutePath()) + "." + adapter.getStoreExtension();
        File targetFile = dir.resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.info("Already existing resource: {0}", targetFile);
            return targetFile;
        }

        adapter.initAndGetEPackage();

        Log.info("Importing resource content...");

        try (DataMapper mapper = adapter.createMapper(targetFile)) {
            Migrator.fromXmi(resourceFile)
                    .toMapper(mapper)
                    .migrate();
        }

        return targetFile;
    }

    //endregion

    //region Standard EMF

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code targetAdapter}, located in {@code
     * dir}.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     * @param dir          the location of the adapter
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     */
    @Nonnull
    private static File createStore(File resourceFile, Adapter.Internal adapter, Path dir) throws Exception {
        Log.info("Creating store with standard EMF");

        ResourceManager.checkValidResource(resourceFile.getName());
        checkArgument(resourceFile.exists(), "Resource '%s' does not exist", resourceFile);

        String targetFileName = ResourceManager.getNameWithoutExtension(resourceFile.getAbsolutePath()) + "." + adapter.getStoreExtension();
        File targetFile = dir.resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.info("Already existing resource: {0}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        Log.info("Loading resource from: {0}", resourceFile);

        URI sourceUri = URI.createFileURI(resourceFile.getAbsolutePath());
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
        adapter.save(targetResource);

        targetResource.getContents().addAll(targetRoot);

        Log.info("Saving resource to: {0}", targetResource.getURI());
        adapter.save(targetResource);

        adapter.unload(targetResource);

        return targetFile;
    }

    //endregion

    /**
     * Creates a new pre-configured {@link ResourceSet} able to handle registered extensions.
     *
     * @return a new {@link ResourceSet}
     */
    @Nonnull
    protected static ResourceSet loadResourceSet() {
        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        return resourceSet;
    }
}
