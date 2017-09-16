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

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.Workspace;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A class that provides static methods for store creation.
 * <p>
 * Stores are {@link Resource}s used to execute queries.
 */
@Static
@ParametersAreNonnullByDefault
public final class Stores {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
    }

    @SuppressWarnings("JavaDoc")
    private Stores() {
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
     * @param resourceFile    the resource file
     * @param adapter         the adapter where to store the resource
     * @param useDirectImport {@code true} if the direct import must be used
     *
     * @return the created resourceFile
     *
     * @throws IOException if a error occurs during the creation of the store
     * @see Workspace#newTempDirectory()
     */
    @Nonnull
    public static File createTempStore(File resourceFile, PersistenceOptions options, Adapter.Internal adapter, boolean useDirectImport) throws IOException {
        Path dir = Workspace.newTempDirectory();

        StoreCreator creator = useDirectImport && adapter.supportsMapper()
                ? new DirectStoreCreator()
                : new StandardStoreCreator();

        return creator.getOrCreateStore(resourceFile, options, adapter, dir);
    }

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code targetAdapter}, located in the workspace.
     *
     * @param resourceFile    the resource file
     * @param adapter         the adapter where to store the resource
     * @param useDirectImport {@code true} if the direct import must be used
     *
     * @return the created resourceFile
     *
     * @throws IOException if a error occurs during the creation of the store
     * @see Workspace#getStoreDirectory()
     */
    @Nonnull
    public static File getOrCreateStore(File resourceFile, PersistenceOptions options, Adapter.Internal adapter, boolean useDirectImport) throws IOException {
        Path dir = Workspace.getStoreDirectory();

        StoreCreator creator = useDirectImport && adapter.supportsMapper()
                ? new DirectStoreCreator()
                : new StandardStoreCreator();

        return creator.getOrCreateStore(resourceFile, options, adapter, dir);
    }

    /**
     * Creates a new pre-configured {@link ResourceSet} able to handle registered extensions.
     *
     * @return a new {@link ResourceSet}
     */
    @Nonnull
    protected static ResourceSet loadResourceSet() {
        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        return new ResourceSetImpl();
    }
}
