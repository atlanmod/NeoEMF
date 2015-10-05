/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.test.commons;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;

public class MapResourceBuilder extends AbstractResourceBuilder {

    public MapResourceBuilder(EPackage ePackage) {
        super(ePackage);
        if(!PersistenceBackendFactoryRegistry.getFactories().containsKey(NeoMapURI.NEO_MAP_SCHEME)) {
            PersistenceBackendFactoryRegistry.getFactories().put(NeoMapURI.NEO_MAP_SCHEME, new MapPersistenceBackendFactory());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, new PersistentResourceFactoryImpl());
    }
    
    @Override
    public MapResourceBuilder uri(URI uri) {
        this.uri = NeoMapURI.createNeoMapURI(uri);
        return this;
    }
    
    @Override
    public MapResourceBuilder file(File file) {
        this.uri = NeoMapURI.createNeoMapURI(file);
        return this;
    }
    
}
