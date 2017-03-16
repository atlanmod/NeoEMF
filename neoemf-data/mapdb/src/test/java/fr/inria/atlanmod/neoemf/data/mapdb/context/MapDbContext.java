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
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

import org.eclipse.emf.common.util.URI;

import java.io.File;

/**
 * A specific {@link Context} for the MapDB implementation.
 */
public abstract class MapDbContext implements Context {

    /**
     * Creates a new {@code MapDbContext} with a mapping with indices.
     *
     * @return a new context.
     */
    public static Context getWithIndices() {
        return new MapDbContext() {
            @Override
            public AbstractPersistenceOptionsBuilder<?, ?> optionsBuilder() {
                return MapDbOptions.newBuilder().withIndices();
            }
        };
    }

    /**
     * Creates a new {@code MapDbContext} with a mapping with arrays.
     *
     * @return a new context.
     */
    public static Context getWithArrays() {
        return new MapDbContext() {
            @Override
            public AbstractPersistenceOptionsBuilder<?, ?> optionsBuilder() {
                return MapDbOptions.newBuilder().withArrays();
            }
        };
    }

    /**
     * Creates a new {@code MapDbContext} with a mapping with lists.
     *
     * @return a new context.
     */
    public static Context getWithLists() {
        return new MapDbContext() {
            @Override
            public AbstractPersistenceOptionsBuilder<?, ?> optionsBuilder() {
                return MapDbOptions.newBuilder().withLists();
            }
        };
    }

    @Override
    public String name() {
        return "MapDb";
    }

    @Override
    public BackendFactory factory() {
        return MapDbBackendFactory.getInstance();
    }

    @Override
    public String uriScheme() {
        return MapDbURI.SCHEME;
    }

    @Override
    public URI createUri(URI uri) {
        return MapDbURI.createURI(uri);
    }

    @Override
    public URI createUri(File file) {
        return MapDbURI.createFileURI(file);
    }
}
