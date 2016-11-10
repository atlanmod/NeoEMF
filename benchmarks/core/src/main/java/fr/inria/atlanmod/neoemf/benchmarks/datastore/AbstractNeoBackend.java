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

import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;

import static java.util.Objects.isNull;

abstract class AbstractNeoBackend extends AbstractBackend {

    private static final String RESOURCE_EXTENSION = "neoemf";
    private static final Class<?> EPACKAGE_CLASS = org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.class;

    @Override
    public String getResourceExtension() {
        return RESOURCE_EXTENSION;
    }

    @Override
    public EPackage getEPackage() throws Exception {
        return (EPackage) EPACKAGE_CLASS.getMethod("init").invoke(null);
    }

    @Override
    public Resource load(File file) throws Exception {
        getEPackage();

        Resource resource = createResource(file, new ResourceSetImpl());
        resource.load(getLoadOptions());

        return resource;
    }

    @Override
    public void unload(Resource resource) {
        if (!isNull(resource) && resource.isLoaded()) {
            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
    }
}
