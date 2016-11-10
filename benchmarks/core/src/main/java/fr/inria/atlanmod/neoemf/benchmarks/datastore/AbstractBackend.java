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

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

import static java.util.Objects.isNull;

abstract class AbstractBackend implements Backend, InternalBackend {

    @Override
    public File create(String name) throws Exception {
        File resourceFile = BackendHelper.createResourceFrom(name, this);
        File storeFile = BackendHelper.createStoreFrom(resourceFile, this);

        if (isNull(storeFile) || !storeFile.exists()) {
            throw new IllegalArgumentException("'" + name + ".xmi' does not exist in resource directory");
        }

        return storeFile;
    }

    @Override
    public void save(Resource resource) throws Exception {
        resource.save(getSaveOptions());
    }

    @Override
    public File copy(File inputFile) throws Exception {
        return BackendHelper.copyStoreFrom(inputFile);
    }
}
