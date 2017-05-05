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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;

import org.eclipse.emf.common.util.URI;

import java.io.File;

/**
 * A specific {@link Context} for the MapDB implementation.
 */
@FunctionalInterface
public interface MapDbContext extends Context {

    /**
     * Creates a new {@code MapDbContext} with a mapping with indices.
     *
     * @return a new context.
     */
    static Context getWithIndices() {
        return (MapDbContext) () -> MapDbOptions.newBuilder().withIndices();
    }

    /**
     * Creates a new {@code MapDbContext} with a mapping with arrays.
     *
     * @return a new context.
     */
    static Context getWithArrays() {
        return (MapDbContext) () -> MapDbOptions.newBuilder().withArrays();
    }

    /**
     * Creates a new {@code MapDbContext} with a mapping with lists.
     *
     * @return a new context.
     */
    static Context getWithLists() {
        return (MapDbContext) () -> MapDbOptions.newBuilder().withLists();
    }

    @Override
    default String name() {
        return "MapDb";
    }

    @Override
    default BackendFactory factory() {
        return MapDbBackendFactory.getInstance();
    }

    @Override
    default String uriScheme() {
        return MapDbURI.SCHEME;
    }

    @Override
    default URI createUri(URI uri) {
        return MapDbURI.newBuilder().fromUri(uri);
    }

    @Override
    default URI createUri(File file) {
        return MapDbURI.newBuilder().fromFile(file);
    }
}
