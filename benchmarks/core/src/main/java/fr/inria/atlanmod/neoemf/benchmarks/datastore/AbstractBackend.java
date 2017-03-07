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

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.helper.BackendHelper;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

abstract class AbstractBackend implements Backend, InternalBackend {

    protected final String name;

    protected final String resourceExtension;
    protected final String storeExtension;

    protected final Class<?> packageClass;

    protected AbstractBackend(String name, String resourceExtension, String storeExtension, Class<?> packageClass) {
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
    public EPackage initAndGetEPackage() throws Exception {
        EPackage ePackage = (EPackage) packageClass.getMethod("init").invoke(null);
        Log.info("Loading package with uri {0}", ePackage.getNsURI());
        return ePackage;
    }

    @Override
    public File getOrCreateResource(String resourceName) throws Exception {
        return BackendHelper.createResource(resourceName, this);
    }

    @Override
    public File getOrCreateStore(File resourceFile) throws Exception {
        return getOrCreateStore(resourceFile, false);
    }

    @Override
    public File createTempStore(File resourceFile) throws Exception {
        return getOrCreateStore(resourceFile, true);
    }

    @Override
    public void save(Resource resource) throws Exception {
        resource.save(getOptions());
    }

    @Override
    public File copy(File storeLocation) throws Exception {
        return BackendHelper.copyStore(storeLocation);
    }

    protected File getOrCreateStore(File resourceFile, boolean temporary) throws Exception {
        File storeFile;

        if (temporary) {
            storeFile = BackendHelper.createTempStore(resourceFile, this);
        }
        else {
            storeFile = BackendHelper.createStore(resourceFile, this);
        }

        if (isNull(storeFile) || !storeFile.exists()) {
            throw new IllegalArgumentException("'" + resourceFile.getName() + "' does not exist in resource directory");
        }

        return storeFile;
    }
}
