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

package fr.inria.atlanmod.neoemf.data.mapdb.context;

import fr.inria.atlanmod.neoemf.context.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.context.ResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * A specific {@link ResourceBuilder} for the MapDB implementation.
 */
public class MapDbResourceBuilder extends AbstractResourceBuilder<MapDbResourceBuilder> {

    /**
     * Constructs a new {@code MapDbResourceBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public MapDbResourceBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();

        if (!PersistenceBackendFactoryRegistry.isRegistered(MapDbURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbPersistenceBackendFactory.getInstance());
        }
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    @Override
    public MapDbResourceBuilder uri(URI uri) {
        this.uri = MapDbURI.createURI(uri);
        return me();
    }

    @Override
    public MapDbResourceBuilder file(File file) {
        this.uri = MapDbURI.createFileURI(file);
        return me();
    }
}
