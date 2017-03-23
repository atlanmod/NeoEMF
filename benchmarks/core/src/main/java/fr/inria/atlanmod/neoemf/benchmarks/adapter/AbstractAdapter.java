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

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.helper.ResourceHelper;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * The abstract implementation of an {@link InternalAdapter}.
 */
abstract class AbstractAdapter implements InternalAdapter {

    /**
     * The name of this adapter.
     */
    protected final String name;

    /**
     * The extension of the adapted resource, used to create the stores.
     */
    protected final String resourceExtension;

    /**
     * The extension of the resource, used for benchmarks.
     */
    protected final String storeExtension;

    /**
     * The class of the {@link EPackage} associated to this adapter.
     */
    protected final Class<?> packageClass;

    /**
     * Constructs a new {@code AbstractAdatper}.
     *
     * @param name              the name of this adapter
     * @param resourceExtension the extension of the adapted resource, used to create the stores
     * @param storeExtension    the extension of the resource, used for benchmarks
     * @param packageClass      the class of the {@link EPackage} associated to this adapter
     */
    protected AbstractAdapter(String name, String resourceExtension, String storeExtension, Class<?> packageClass) {
        this.name = checkNotNull(name);
        this.resourceExtension = checkNotNull(resourceExtension);
        this.storeExtension = checkNotNull(storeExtension);
        this.packageClass = checkNotNull(packageClass);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getResourceExtension() {
        return resourceExtension;
    }

    @Override
    public String getStoreExtension() {
        return storeExtension;
    }

    @Override
    public final EPackage initAndGetEPackage() {
        try {
            EPackage ePackage = (EPackage) packageClass.getMethod("init").invoke(null);
            Log.info("Loading package with uri {0}", ePackage.getNsURI());
            return ePackage;
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getOrCreateResource(String name) throws Exception {
        return ResourceHelper.createResource(name, this);
    }

    @Override
    public File getOrCreateStore(File file) throws Exception {
        return getOrCreateStore(file, false);
    }

    @Override
    public File createTempStore(File file) throws Exception {
        return getOrCreateStore(file, true);
    }

    @Override
    public void save(Resource resource) throws Exception {
        resource.save(getOptions());
    }

    @Override
    public File copy(File file) throws Exception {
        return ResourceHelper.copyStore(file);
    }

    /**
     * Retrieves or creates a {@link Resource} used for benchmarks.
     *
     * @param file      the file to retrieve the resource
     * @param temporary {@code true} if the resource is temporary and must be placed in a temporary folder
     *
     * @return the resource
     */
    private File getOrCreateStore(File file, boolean temporary) {
        File storeFile;

        try {
            if (temporary) {
                storeFile = ResourceHelper.createTempStore(file, this);
            }
            else {
                storeFile = ResourceHelper.createStore(file, this);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (isNull(storeFile) || !storeFile.exists()) {
            throw new IllegalArgumentException("'" + file.getName() + "' does not exist in resource directory");
        }

        return storeFile;
    }
}
