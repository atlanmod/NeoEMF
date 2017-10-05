/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.benchmarks.resource.Resources;
import fr.inria.atlanmod.neoemf.benchmarks.resource.Stores;
import fr.inria.atlanmod.neoemf.config.Config;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkState;

/**
 * The abstract implementation of an {@link Adapter.Internal}.
 */
@ParametersAreNonnullByDefault
abstract class AbstractAdapter implements Adapter.Internal {

    /**
     * The extension of the adapted resource, used to create the stores.
     */
    private final String resourceExtension;

    /**
     * The extension of the resource, used for benchmarks.
     */
    private final String storeExtension;

    /**
     * The class of the {@link EPackage} associated to this adapter.
     */
    private final Class<?> packageClass;

    /**
     * Constructs a new {@code AbstractAdatper}.
     *
     * @param resourceExtension the extension of the adapted resource, used to create the stores
     * @param storeExtension    the extension of the resource, used for benchmarks
     * @param packageClass      the class of the {@link EPackage} associated to this adapter
     */
    protected AbstractAdapter(String resourceExtension, String storeExtension, Class<?> packageClass) {
        this.resourceExtension = checkNotNull(resourceExtension);
        this.storeExtension = checkNotNull(storeExtension);
        this.packageClass = checkNotNull(packageClass);
    }

    @Nonnull
    @Override
    public String getResourceExtension() {
        return resourceExtension;
    }

    @Nonnull
    @Override
    public String getStoreExtension() {
        return storeExtension;
    }

    @Nonnull
    @Override
    public final EPackage initAndGetEPackage() {
        try {
            return (EPackage) packageClass.getMethod("init").invoke(null);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public File getOrCreateResource(String name) throws IOException {
        return Resources.getOrCreateResource(name, this);
    }

    @Nonnull
    @Override
    public File getOrCreateStore(File file, Config config, boolean useDirectImport) throws IOException {
        return getOrCreateStore(file, config, useDirectImport, false);
    }

    @Nonnull
    @Override
    public File createTempStore(File file, Config config, boolean useDirectImport) throws IOException {
        return getOrCreateStore(file, config, useDirectImport, true);
    }

    @Override
    public void save(Resource resource, Config config) throws IOException {
        resource.save(config.merge(getOptions()).toMap());
    }

    @Nonnull
    @Override
    public File copy(File file) throws IOException {
        return Stores.copyStore(file);
    }

    /**
     * Retrieves or creates a {@link Resource} used for benchmarks.
     *
     * @param file      the file to retrieve the resource
     * @param temporary {@code true} if the resource is temporary and must be placed in a temporary folder
     *
     * @return the resource
     */
    @Nonnull
    private File getOrCreateStore(File file, Config config, boolean useDirectImport, boolean temporary) throws IOException {
        File storeFile;

        if (temporary) {
            storeFile = Stores.createTempStore(file, config, this, useDirectImport);
        }
        else {
            storeFile = Stores.getOrCreateStore(file, config, this, useDirectImport);
        }

        checkState(storeFile.exists(), "'%s' does not exist in resource directory", file.getName());
        return storeFile;
    }
}
