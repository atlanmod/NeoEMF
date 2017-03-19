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

import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;

import static java.util.Objects.nonNull;

abstract class AbstractNeoBackend extends AbstractBackend {

    private static final String RESOURCE_EXTENSION = "neoemf";

    private static final Class<?> EPACKAGE_CLASS = org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.class;

    protected AbstractNeoBackend(String name, String storeExtension) {
        super(name, RESOURCE_EXTENSION, storeExtension, EPACKAGE_CLASS);
    }

    @Override
    public Resource load(File file, CommonOptions options) throws Exception {
        initAndGetEPackage();

        Resource resource = createResource(file, new ResourceSetImpl());
        resource.load(options.withOptions(getOptions()).asMap());

        return resource;
    }

    @Override
    public void unload(Resource resource) {
        if (nonNull(resource) && resource.isLoaded()) {
            if (resource instanceof PersistentResource) {
                PersistentResource persistentResource = (PersistentResource) resource;
                persistentResource.close();
            }
            else {
                resource.unload();
            }
        }
    }
}
