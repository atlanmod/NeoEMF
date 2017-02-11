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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.AbstractTestBuilder;
import fr.inria.atlanmod.neoemf.TestBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;

/**
 * A specific {@link TestBuilder} for the MapDB implementation.
 */
public class MapDbTestBuilder extends AbstractTestBuilder<MapDbTestBuilder> {

    /**
     * Constructs a new {@code MapDbTestBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public MapDbTestBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();

        PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, MapDbBackendFactory.getInstance());

        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    @Override
    public MapDbTestBuilder uri(URI uri) {
        this.uri = MapDbURI.createURI(uri);
        return me();
    }

    @Override
    public MapDbTestBuilder file(File file) {
        this.uri = MapDbURI.createFileURI(file);
        return me();
    }

    @Override
    public PersistenceBackend createBackend() throws IOException {
        return MapDbBackendFactory.getInstance().createPersistentBackend(uri, resourceOptions);
    }
}
