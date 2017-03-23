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

import fr.inria.atlanmod.neoemf.option.CommonOptions;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;

import static java.util.Objects.nonNull;

/**
 * An {@link Adapter} on top a an original XMI {@link Resource}.
 */
public class XmiAdapter extends AbstractAdapter {

    public static final String NAME = "xmi";

    private static final String RESOURCE_EXTENSION = "xmi";
    private static final String STORE_EXTENSION = "xmi";  // -> xmi.xmi

    private static final Class<?> EPACKAGE_CLASS = org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.class;

    @SuppressWarnings("unused") // Called dynamically
    public XmiAdapter() {
        super(NAME, RESOURCE_EXTENSION, STORE_EXTENSION, EPACKAGE_CLASS);
    }

    @Override
    public Resource createResource(File file, ResourceSet resourceSet) {
        URI targetUri = URI.createFileURI(file.getAbsolutePath());

        return resourceSet.createResource(targetUri);
    }

    @Override
    public Resource load(File file, CommonOptions options) throws Exception {
        initAndGetEPackage();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        Resource resource = createResource(file, resourceSet);
        resource.load(getOptions());

        return resource;
    }

    @Override
    public void unload(Resource resource) {
        if (nonNull(resource) && resource.isLoaded()) {
            resource.unload();
        }
    }
}
