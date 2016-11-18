/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

abstract class AbstractBackend implements Backend, InternalBackend {

    private static final Logger log = LogManager.getLogger();

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
        log.info("Loading package with uri '{}'", ePackage.getNsURI());
        return ePackage;
    }

    @Override
    public File createResource(String resourceName) throws Exception {
        return BackendHelper.createResource(resourceName, this);
    }

    @Override
    public File createStore(File resourceFile, boolean temporary) throws Exception {
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

    @Override
    public void save(Resource resource) throws Exception {
        resource.save(getOptions());
    }

    @Override
    public File copy(File storeLocation) throws Exception {
        return BackendHelper.copyStore(storeLocation);
    }
}
