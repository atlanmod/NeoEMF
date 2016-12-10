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

package fr.inria.atlanmod.neoemf.tests.util;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;

class MapDbResourceBuilder extends AbstractResourceBuilder {

    public MapDbResourceBuilder(EPackage ePackage) {
        super(ePackage);
        initMapBuilder();
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();
        initMapBuilder();
    }

    @Override
    public MapDbResourceBuilder uri(URI uri) {
        this.uri = MapDbURI.createURI(uri);
        return this;
    }

    @Override
    public MapDbResourceBuilder file(File file) {
        this.uri = MapDbURI.createFileURI(file);
        return this;
    }

    private void initMapBuilder() {
        if (!PersistenceBackendFactoryRegistry.isRegistered(MapDbURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());
    }
}
