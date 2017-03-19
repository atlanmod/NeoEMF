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

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Map;

public class BerkeleyDbAdapter extends AbstractNeoAdapter {

    public static final String NAME = "neo-berkeleydb";

    private static final String STORE_EXTENSION = "berkeleydb.resource"; // -> neoemf.mapdb.resource

    @SuppressWarnings("unused") // Called dynamically
    public BerkeleyDbAdapter() {
        super(NAME, STORE_EXTENSION);
    }

    @Override
    public Resource createResource(File file, ResourceSet resourceSet) throws Exception {
        BackendFactoryRegistry.register(BerkeleyDbURI.SCHEME, BerkeleyDbBackendFactory.getInstance());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BerkeleyDbURI.SCHEME, PersistentResourceFactory.getInstance());

        URI uri = BerkeleyDbURI.createFileURI(file);

        return resourceSet.createResource(uri);
    }

    @Override
    public Map<String, Object> getOptions() {
        return BerkeleyDbOptions.newBuilder().asMap();
    }
}
