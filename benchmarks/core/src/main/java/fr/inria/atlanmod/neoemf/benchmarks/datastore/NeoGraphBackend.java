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

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions.EStoreGraphOption;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeoGraphBackend extends AbstractNeoBackend {

    public static final String NAME = "neo-graph";

    private static final String STORE_EXTENSION = "tinker.resource"; // -> neoemf.tinker.resource

    public NeoGraphBackend() {
        this(NAME, STORE_EXTENSION);
    }

    protected NeoGraphBackend(String name, String storeExtension) {
        super(name, storeExtension);
    }

    @Override
    public Resource createResource(File file, ResourceSet resourceSet) throws Exception {
        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

        URI uri = NeoBlueprintsURI.createNeoGraphURI(file);

        return resourceSet.createResource(uri);
    }

    @Override
    public Map<Object, Object> getOptions() {
        Map<Object, Object> options = new HashMap<>();
        List<StoreOption> storeOptions = new ArrayList<>();
        storeOptions.add(EStoreGraphOption.AUTOCOMMIT);
        options.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
        return options;
    }
}
