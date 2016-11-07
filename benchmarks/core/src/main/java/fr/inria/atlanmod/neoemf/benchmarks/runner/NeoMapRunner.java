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

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.Creator;
import fr.inria.atlanmod.neoemf.benchmarks.io.NeoMapCreator;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NeoMapRunner extends AbstractNeoRunner {

    @Override
    protected Map<Object, Object> getLoadOptions() {
        Map<Object, Object> loadOpts = new HashMap<>();

//      List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();
//		storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
//      storeOptions.add(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING);
//      storeOptions.add(PersistentResourceOptions.EStoreOption.IS_SET_CACHING);
//      storeOptions.add(PersistentResourceOptions.EStoreOption.SIZE_CACHING);
//      loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);

        return loadOpts;
    }

    @Override
    public void initResource() throws IOException {
        PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

        URI uri = NeoMapURI.createNeoMapURI(new File(getCurrentPath()));

        org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

        resource = resourceSet.createResource(uri);

        resource.load(getLoadOptions());
    }

    @Override
    protected Creator getCreator() {
        return NeoMapCreator.getInstance();
    }
}
