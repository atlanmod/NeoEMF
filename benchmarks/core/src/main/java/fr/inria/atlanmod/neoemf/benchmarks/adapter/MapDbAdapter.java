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

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
 */
@ParametersAreNonnullByDefault
public class MapDbAdapter extends AbstractNeoAdapter {

    public static final String NAME = "neo-mapdb";

    private static final String STORE_EXTENSION = "mapdb.resource"; // -> neoemf.mapdb.resource

    @SuppressWarnings("unused") // Called dynamically
    public MapDbAdapter() {
        super(NAME, STORE_EXTENSION);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return MapDbBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    public Resource createResource(File file, ResourceSet resourceSet) {
        BackendFactoryRegistry.register(MapDbUri.SCHEME, MapDbBackendFactory.getInstance());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbUri.SCHEME, PersistentResourceFactory.getInstance());

        URI uri = MapDbUri.builder().fromFile(file);

        return resourceSet.createResource(uri);
    }

    @Nonnull
    @Override
    public Map<String, Object> getOptions() {
        return MapDbOptions.builder().asMap();
    }
}
