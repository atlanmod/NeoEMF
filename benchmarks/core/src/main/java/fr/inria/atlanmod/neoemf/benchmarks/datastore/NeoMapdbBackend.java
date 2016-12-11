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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Map;

public class NeoMapdbBackend extends AbstractNeoBackend {

    public static final String NAME = "neo-mapdb";

    private static final String STORE_EXTENSION = "mapdb.resource"; // -> neoemf.mapdb.resource

    public NeoMapdbBackend() {
        super(NAME, STORE_EXTENSION);
    }

    @Override
    public Resource createResource(File file, ResourceSet resourceSet) throws Exception {
        PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());

        URI uri = MapDbURI.createFileURI(file);

        return resourceSet.createResource(uri);
    }

    @Override
    public Map<String, Object> getOptions() {
        return MapDbOptionsBuilder.newBuilder()
                .directWrite()
                .autocommit()
                .asMap();
    }
}
