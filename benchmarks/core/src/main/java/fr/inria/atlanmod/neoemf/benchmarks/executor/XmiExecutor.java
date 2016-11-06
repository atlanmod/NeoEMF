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

package fr.inria.atlanmod.neoemf.benchmarks.executor;

import fr.inria.atlanmod.neoemf.benchmarks.Creator;
import fr.inria.atlanmod.neoemf.benchmarks.creator.XmiCreator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XmiExecutor extends AbstractExecutor {

    @Override
    protected Creator getCreator() {
        return XmiCreator.getInstance();
    }

    @Override
    public void createResource() throws IOException {
        URI uri = URI.createFileURI(getPath());

        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        resource = resourceSet.createResource(uri);

        Map<String, Object> loadOpts = new HashMap<>();
        resource.load(loadOpts);
    }

    @Override
    public void destroyResource() {
        if (resource != null && resource.isLoaded()) {
            resource.unload();
        }
    }
}
